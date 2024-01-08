package com.cleverpine.exceldatasync.util;

import com.cleverpine.exceldatasync.annotations.ExcelMapper;
import com.cleverpine.exceldatasync.exception.ReflectionException;
import com.cleverpine.exceldatasync.mapper.ExcelCustomMapper;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;


import static com.cleverpine.exceldatasync.util.ExcelCellParsingHelper.evaluateInCell;
import static com.cleverpine.exceldatasync.util.ExcelCellParsingHelper.parseDateOrInteger;
import static com.cleverpine.exceldatasync.util.ExcelCellParsingHelper.parseDoubleOrDefault;
import static com.cleverpine.exceldatasync.util.ExcelCellParsingHelper.parseYesNoToBoolean;

@UtilityClass
public final class ExcelValueMapper {

    private static final Map<Class<?>, Function<Cell, ?>> FUNCTION_CACHE;
    private static final Map<Class<? extends ExcelCustomMapper<?>>, ExcelCustomMapper<?>> MAPPER_CACHE = new ConcurrentHashMap<>();

    static {
        Map<Class<?>, Function<Cell, ?>> map = new ConcurrentHashMap<>();
        map.put(String.class, ExcelValueMapper::mapString);
        map.put(Boolean.class, ExcelValueMapper::mapBoolean);
        map.put(Double.class, ExcelValueMapper::mapDouble);
        map.put(Integer.class, ExcelValueMapper::mapInteger);
        map.put(BigDecimal.class, ExcelValueMapper::mapBigDecimal);
        FUNCTION_CACHE = map;
    }

    public static <I> I createInstance(Class<I> classToInstantiate) {
        try {
            return classToInstantiate.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new ReflectionException(String.format(Constants.FAILED_TO_INSTANTIATE, classToInstantiate.getSimpleName()), e);
        }
    }

    public static Object mapCell(Cell cell, Class<?> fieldType, Optional<ExcelMapper> mapperAnnotation) {
        if (mapperAnnotation.isPresent()) {
            Class<? extends ExcelCustomMapper<?>> mapperClass = mapperAnnotation.get().mapper();
            ExcelCustomMapper<?> mapper = MAPPER_CACHE.computeIfAbsent(mapperClass, ExcelValueMapper::createInstance);
            String cellValue = cell.getStringCellValue();
            return mapper.fromString(cellValue);
        }

        return FUNCTION_CACHE.get(fieldType).apply(cell);
    }

    public static String mapString(Cell cell) {
        if (cell == null) {
            return "";
        }

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> Boolean.toString(cell.getBooleanCellValue());
            case FORMULA -> mapString(evaluateInCell(cell));
            case NUMERIC -> parseDateOrInteger(cell);
            default -> "";
        };
    }

    public static Boolean mapBoolean(Cell cell) {
        if (cell == null) {
            return null;
        }

        return switch (cell.getCellType()) {
            case BOOLEAN -> cell.getBooleanCellValue();
            case FORMULA -> mapBoolean(evaluateInCell(cell));
            case STRING -> parseYesNoToBoolean(cell);
            default -> null;
        };
    }

    public static Double mapDouble(Cell cell) {
        if (cell == null) {
            return null;
        }

        return switch (cell.getCellType()) {
            case NUMERIC -> cell.getNumericCellValue();
            case FORMULA -> mapDouble(evaluateInCell(cell));
            case STRING -> parseDoubleOrDefault(cell, null);
            default -> null;
        };
    }

    public static Integer mapInteger(Cell cell) {
        Double doubleValue = mapDouble(cell);
        if (doubleValue == null) {
            return null;
        }

        return doubleValue.intValue();
    }

    public static BigDecimal mapBigDecimal(Cell cell) {
        Double doubleValue = mapDouble(cell);
        if (doubleValue == null) {
            return null;
        }

        return BigDecimal.valueOf(doubleValue);
    }

}
