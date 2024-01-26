package com.cleverpine.exceldatasync.util;

import com.cleverpine.exceldatasync.annotations.ExcelMapper;
import com.cleverpine.exceldatasync.exception.ReflectionException;
import com.cleverpine.exceldatasync.mapper.ExcelCustomMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;


import static com.cleverpine.exceldatasync.util.ExcelCellParsingHelper.parseDateOrNumber;
import static com.cleverpine.exceldatasync.util.ExcelCellParsingHelper.parseNumericOrNull;
import static com.cleverpine.exceldatasync.util.ExcelCellParsingHelper.parseYesNoToBoolean;

@UtilityClass
public final class ExcelValueMapper {

    private static final Map<Class<?>, Function<Cell, ?>> FUNCTION_CACHE;
    private static final Map<Class<? extends ExcelCustomMapper<?>>, ExcelCustomMapper<?>> MAPPER_CACHE = new ConcurrentHashMap<>();

    static {
        Map<Class<?>, Function<Cell, ?>> map = new ConcurrentHashMap<>();
        map.put(String.class, ExcelValueMapper::mapString);
        map.put(boolean.class, ExcelValueMapper::mapBoolean);
        map.put(Boolean.class, ExcelValueMapper::mapBoolean);
        map.put(double.class, ExcelValueMapper::mapDouble);
        map.put(Double.class, ExcelValueMapper::mapDouble);
        map.put(int.class, ExcelValueMapper::mapInteger);
        map.put(Integer.class, ExcelValueMapper::mapInteger);
        map.put(Long.class, ExcelValueMapper::mapLong);
        map.put(long.class, ExcelValueMapper::mapLong);
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

    public static <T> String mapCellValue(T value, ExcelMapper mapperAnnotation) {
        var mapperClass = mapperAnnotation.mapper();
        ExcelCustomMapper<T> mapper = (ExcelCustomMapper<T>) MAPPER_CACHE.computeIfAbsent(mapperClass, ExcelValueMapper::createInstance);
        return mapper.toString(value);
    }

    public static String mapString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING, FORMULA -> cell.getStringCellValue().trim();
            case BOOLEAN -> Boolean.toString(cell.getBooleanCellValue());
            case NUMERIC -> parseDateOrNumber(cell);
            default -> "";
        };
    }

    public static Boolean mapBoolean(Cell cell) {
        return switch (cell.getCellType()) {
            case BOOLEAN, FORMULA -> cell.getBooleanCellValue();
            case STRING -> parseYesNoToBoolean(cell);
            default -> null;
        };
    }

    public static Double mapDouble(Cell cell) {
        return switch (cell.getCellType()) {
            case NUMERIC, FORMULA -> cell.getNumericCellValue();
            case STRING -> parseNumericOrNull(cell);
            default -> null;
        };
    }

    public static Integer mapInteger(Cell cell) {
        Double doubleValue = mapDouble(cell);
        if (doubleValue == null) {
            return null;
        }

        return (int) Math.rint(doubleValue);
    }

    public static Long mapLong(Cell cell) {
        Double doubleValue = mapDouble(cell);
        if (doubleValue == null) {
            return null;
        }

        return BigDecimal.valueOf(doubleValue).setScale(0, RoundingMode.HALF_UP).longValue();
    }
}
