package qa.utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import qa.base.SeleniumDriver;
import qa.utility.enums.PetType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static qa.utility.ExcelConstant.*;
import static qa.utility.enums.PetType.CAT;
import static qa.utility.enums.PetType.DOG;


public class Utils extends SeleniumDriver {

    private static final Logger logger = LogManager.getLogger(Utils.class);

    public Utils(String excelPath, String sheetName) throws IOException {
        File fileName = new File(excelPath);
        FileInputStream fileStream = new FileInputStream(fileName);
        workbook = new XSSFWorkbook(fileStream);
    }
    public static String getPetTypeName(PetType petType) {
        String type = "";

        switch (petType) {
            case DOG:
                type = "Dog";
                break;
            case CAT:
                type = "Cat";
                break;
            default:
                Assert.fail("Incorrect type of pet specified in the feature file!");
        }
        return type;
    }

    public static PetType getPetType(String petType) {
        if ("Dog".equals(petType)) {
            return DOG;
        } else if ("Cat".equals(petType)) {
            return CAT;
        } else {
            Assert.fail("Incorrect type of pet specified in the feature file!");
        }
        return null;
    }

    public static void print(String msg) {
        System.out.println(msg);
    }

    public static void printSeparator() {
        print("========================================================");
    }

    public static String getCurrentDate(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.UK);
        return dateFormat.format(new Timestamp(System.currentTimeMillis()));

    }

    public static String getTimeStamp() {
        return getCurrentDate("dd MMMM yy, HH:mm");
    }

    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    /**
     * @return This @DataProvider provides data to the @Test method by
     * reading the data from excel sheet
     * @throws IOException
     */
    @DataProvider
    public static Object[][] readExcelDataRegister() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook(fileInputStream);//get my workbook

        ExcelConstant.workSheet = workbook.getSheet(SheetName); // get my sheet from workbook

        XSSFRow Row = ExcelConstant.workSheet.getRow(0);//get my Row which start from 0

        int RowNum = ExcelConstant.workSheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum = Row.getLastCellNum(); // get last ColNum

        Object[][] Data = new Object[RowNum - 1][ColNum]; // pass my  count data in array

        for (int i = 0; i < RowNum - 1; i++) //Loop work for Rows
        {
            XSSFRow row = ExcelConstant.workSheet.getRow(i + 1);

            for (int j = 0; j < ColNum; j++) //Loop work for colNum
            {
                if (row == null)
                    Data[i][j] = "There is no data in that row";
                else {
                    XSSFCell cell = row.getCell(j);
                    if (cell == null)
                        Data[i][j] = "No data found"; //if it get Null value it pass no data
                    else {
                        String value = formatter.formatCellValue(cell);
                        Data[i][j] = value; //This formatter get my all values as string i.e integer, float all type data value
                    }
                }
            }
        }

        return Data;
    }

    @DataProvider
    public static Object[][] readExcelPolicyNumber() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook(fileInputStream);//get my workbook

        ExcelConstant.workSheet = workbook.getSheet(SheetName2); // get my sheet from workbook

        XSSFRow Row = ExcelConstant.workSheet.getRow(1);//get my Row which start from 0

        int RowNum = ExcelConstant.workSheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum = Row.getLastCellNum(); // get last ColNum

        Object[][] Data = new Object[RowNum - 1][ColNum]; // pass my  count data in array

        for (int i = 0; i < RowNum - 1; i++) //Loop work for Rows
        {
            XSSFRow row = ExcelConstant.workSheet.getRow(i + 1);

            for (int j = 0; j < ColNum; j++) //Loop work for colNum
            {
                if (row == null)
                    Data[i][j] = "There is no data in that row";
                else {
                    XSSFCell cell = row.getCell(j);
                    if (cell == null)
                        Data[i][j] = "No data found"; //if it get Null value it pass no data
                    else {
                        String value = formatter.formatCellValue(cell);
                        Data[i][j] = value; //This formatter get my all values as string i.e integer, float all type data value
                    }
                }
            }
        }

        return Data;

    }
//    @DataProvider
//    public static Object[][] getLoginDetails(){
//
//
//
//    }

}


