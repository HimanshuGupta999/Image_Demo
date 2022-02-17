package Upload;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelConfig {

    //Making Global Variable for our workbook//
    XSSFWorkbook wb;
    XSSFSheet sheet1;

    //Accepting the Excel path through parameter and loading the Excel file//
    public ExcelConfig(String excelpath) {
        try {
            //creating new object
            File src = new File(excelpath);
            FileInputStream fis = new FileInputStream(src);

            wb = new XSSFWorkbook(fis);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Fetching data from Excel file and reading number of rows,column in Excel file//

    public String getData(int sheetNumber, int row, int column) {
        sheet1 = wb.getSheetAt(0);
        String data = sheet1.getRow(row).getCell(column).getStringCellValue();
        return data;
    }
    //for multiple values
   /* public String getData(int sheetNumber, int row, int column) {
        XSSFSheet sheet1 = wb.getSheetAt(sheetNumber);
        if (sheet1.getRow(row).getCell(column).getCellType() == CellType.STRING)
            return sheet1.getRow(row).getCell(column).getStringCellValue();
        else if (sheet1.getRow(row).getCell(column).getCellType() == CellType.NUMERIC)
            return String.valueOf(sheet1.getRow(row).getCell(column).getNumericCellValue());
        else
            throw new RuntimeException("no value found");
    }

    //Reading number of rows in Excel file//
    public int getRowCount(int sheetIndex) {
        int row = wb.getSheetAt(sheetIndex).getLastRowNum();
        row = row + 1;
        return row;
}*/
}
