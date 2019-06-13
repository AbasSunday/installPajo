package qa.utility;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConstant {

    public static XSSFWorkbook workbook;
    public static XSSFSheet workSheet;
    public static DataFormatter formatter = new DataFormatter();
    public static String file_location = System.getProperty("user.dir") + "/src/resources/testData/RegistrationDetails.xlsx";
    public static String SheetName = "Registration";
    public static String SheetName2 = "PolicyNumber";
    public String Res;
    //Write obj1 = new Write();
    public int DataSet = -1;


}
