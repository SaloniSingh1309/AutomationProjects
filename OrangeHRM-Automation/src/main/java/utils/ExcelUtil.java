package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    private static Workbook workbook;
    private static Sheet sheet;

    public static void setExcelFile(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    public static int getCellCount(int rowNum) {
        return sheet.getRow(rowNum).getLastCellNum();
    }

    public static String getCellData(int rowNum, int colNum) {
        Cell cell = sheet.getRow(rowNum).getCell(colNum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
        setExcelFile(filePath, sheetName);
        int rowCount = getRowCount();
        int colCount = getCellCount(0);

        Object[][] data = new Object[rowCount][colCount];
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }
}
