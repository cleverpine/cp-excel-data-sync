package com.cleverpine.exceldatasync.util;


import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.mapper.ExcelExportCustomMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.cleverpine.exceldatasync.util.ReflectionUtils.createInstance;


public final class ExcelHelper {
    private ExcelHelper() {
        // Should not instantiate this class
    }

    public static final Map<Class<?>, Function<Cell, ?>> CELL_VALUE_FUNCTION_MAP = createCellValueMap();

    private static final Map<Class<? extends ExcelExportCustomMapper>, ExcelExportCustomMapper<?>> MAPPER_CACHE = new HashMap<>();

    public static Boolean mapBooleanFromBinaryAnswer(String binaryAnswer) {
        if (binaryAnswer == null) {
            return null;
        }

        switch (binaryAnswer) {
            case "Yes", "Ja" -> {
                return true;
            }
            case "No", "Nein" -> {
                return false;
            }
            default -> {
                return null;
            }
        }
    }

    public static Boolean getBooleanValueFromCell(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case BOOLEAN -> {
                return cell.getBooleanCellValue();
            }
            case STRING -> {
                return mapBooleanFromBinaryAnswer(cell.getStringCellValue());
            }
            case FORMULA -> {
                Cell evaluatedCell = cell.getSheet().getWorkbook().getCreationHelper()
                        .createFormulaEvaluator().evaluateInCell(cell);
                if (evaluatedCell.getCellType() == CellType.BOOLEAN) {
                    return evaluatedCell.getBooleanCellValue();
                }
                return null;
            }
            default -> {
                return null;
            }
        }
    }

    public static BigDecimal getBigDecimalValueFromCell(Cell cell) {
        var doubleValue = getDoubleValueFromCell(cell);
        if (doubleValue == null) {
            return null;
        }
        return BigDecimal.valueOf(doubleValue);
    }

    public static Double getDoubleValueFromCell(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case NUMERIC -> {
                return cell.getNumericCellValue();
            }
            case FORMULA -> {
                Cell evaluatedCell = cell.getSheet().getWorkbook().getCreationHelper()
                        .createFormulaEvaluator().evaluateInCell(cell);
                if (evaluatedCell.getCellType() == CellType.NUMERIC) {
                    return evaluatedCell.getNumericCellValue();
                }
                return null;
            }
            case STRING -> {
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            default -> {
                return null;
            }
        }
    }

    public static Integer getIntegerValueFromCell(Cell cell) {
        var doubleValue = getDoubleValueFromCell(cell);
        if (doubleValue == null) {
            return null;
        }
        return doubleValue.intValue();
    }

    public static String getStringValueFromCell(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING -> {
                return cell.getStringCellValue();
            }
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Convert date to a string format you prefer
                    return new java.text.SimpleDateFormat("MM/dd/yyyy").format(cell.getDateCellValue());
                } else {
                    return Integer.toString((int) cell.getNumericCellValue());
                }
            }
            case BOOLEAN -> {
                return Boolean.toString(cell.getBooleanCellValue());
            }
            case FORMULA -> {
                return getStringValueFromCell(cell.getSheet().getWorkbook().getCreationHelper()
                        .createFormulaEvaluator().evaluateInCell(cell));
            }
            default -> {
                return "";
            }
        }
    }

    public static Object getValueFromCell(Field excelColumnField, Map<Class<?>, Function<Cell, ?>> cellValueFunctionMap, Cell cell,
            Class<? extends ExcelExportCustomMapper> mapperClass) {
        Object value;
        if (mapperClass != ExcelExportCustomMapper.class) {
            value = getValueFromString(mapperClass, cell.getStringCellValue());
        } else {
            value = cellValueFunctionMap.get(excelColumnField.getType()).apply(cell);
        }
        return value;
    }

    public static Object getValueFromString(Class<? extends ExcelExportCustomMapper> excelMapperClass, String value) {
        var mapper = MAPPER_CACHE.computeIfAbsent(excelMapperClass, key -> createInstance(key, Constants.MAPPER));

        return mapper.fromString(value);
    }

    @SuppressWarnings("unchecked")
    public static String getMapperValue(Object obj, Field field) throws Exception {
        if (field.isAnnotationPresent(ExcelColumn.class)) {
            var annotation = field.getAnnotation(ExcelColumn.class);

            var mapper = (ExcelExportCustomMapper<Boolean>) MAPPER_CACHE.computeIfAbsent(annotation.mapper(),
                    key -> createInstance(key, Constants.MAPPER));

            var fieldValue = (Boolean) field.get(obj);

            return mapper.toString(fieldValue);
        }

        return null;
    }

    private static Map<Class<?>, Function<Cell, ?>> createCellValueMap() {
        return Map.of(
                String.class, ExcelHelper::getStringValueFromCell,
                Boolean.class, ExcelHelper::getBooleanValueFromCell,
                BigDecimal.class, ExcelHelper::getBigDecimalValueFromCell,
                Double.class, ExcelHelper::getDoubleValueFromCell,
                Integer.class, ExcelHelper::getIntegerValueFromCell
        );
    }

}
