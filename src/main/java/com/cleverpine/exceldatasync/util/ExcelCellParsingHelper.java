package com.cleverpine.exceldatasync.util;

import java.text.SimpleDateFormat;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

@UtilityClass
public final class ExcelCellParsingHelper {

    public static String parseDateOrNumber(Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            return new SimpleDateFormat("MM/dd/yyyy").format(cell.getDateCellValue());
        }

        return Integer.toString((int) cell.getNumericCellValue());
    }

    // TODO: Extract custom mapper for this
    public static Boolean parseYesNoToBoolean(Cell cell) {
        String binaryAnswer = cell.getStringCellValue().toUpperCase();
        return switch (binaryAnswer) {
            case "TRUE", "YES", "JA" -> true;
            case "FALSE", "NO", "NEIN" -> false;
            default -> null;
        };
    }

    public static Double parseNumericOrNull(Cell cell) {
        try {
            return Double.parseDouble(cell.getStringCellValue());
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
