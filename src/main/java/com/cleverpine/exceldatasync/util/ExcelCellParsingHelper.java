package com.cleverpine.exceldatasync.util;

import java.text.SimpleDateFormat;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

@UtilityClass
public final class ExcelCellParsingHelper {

    public static Boolean parseYesNoToBoolean(Cell cell) {
        String binaryAnswer = cell.getStringCellValue();
        return switch (binaryAnswer) {
            case "Yes", "Ja" -> true;
            case "No", "Nein" -> false;
            default -> null;
        };
    }

    public static String parseDateOrInteger(Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            return new SimpleDateFormat("MM/dd/yyyy").format(cell.getDateCellValue());
        }
        return Integer.toString((int) cell.getNumericCellValue());
    }

    public static Double parseDoubleOrDefault(Cell cell, Double defaultValue) {
        try {
            return Double.parseDouble(cell.getStringCellValue());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static Cell evaluateInCell(Cell cell) {
        return cell.getSheet()
                .getWorkbook()
                .getCreationHelper()
                .createFormulaEvaluator()
                .evaluateInCell(cell);
    }

}
