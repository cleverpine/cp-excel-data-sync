package com.cleverpine.exceldatasync.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ExcelColumnMapper {

    private static final Map<String, Integer> excelColumnMap = new ConcurrentHashMap<>();

    static {
        int columnNumber = 0;
        String columnLetter = "A";

        // Generate Excel column mapping up to 16383 = XFD (last possible column)
        int lastColumn = 16383;

        while (columnNumber <= lastColumn) {
            excelColumnMap.put(columnLetter, columnNumber);
            columnNumber++;
            columnLetter = getNextColumn(columnLetter);
        }
    }

    public static int getColumnNumber(String columnLabel) {
        return excelColumnMap.get(columnLabel);
    }

    private static String getNextColumn(String currentColumn) {
        char[] chars = currentColumn.toCharArray();
        int carry = 1;

        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            int value = c - 'A' + carry;

            if (value >= 26) {
                carry = 1;
                value %= 26;
            } else {
                carry = 0;
            }

            chars[i] = (char) ('A' + value);

            if (carry == 0) {
                break;
            }
        }

        if (carry == 1) {
            return "A" + new String(chars);
        }

        return new String(chars);
    }
}
