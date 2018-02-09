package Javapackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelPracticeTest {
	
	
	
	
@Test(dataProvider="ExcelPractice")

void ExcelDataPrac(String Name,String Id)
{
	
	System.out.print("Employeename is "+Name);
    System.out.print("EmpId is "+Id);
    System.out.println("\n");
}





  @DataProvider(name="ExcelPractice")
	
	  public static Object[][] Exceldata() throws IOException
  {
	  

		FileInputStream File=new FileInputStream("D:\\Selenium Docs\\InputFiles of Testng\\Employee1.xlsx");
		Workbook wb=new XSSFWorkbook(File);
		Sheet sh=wb.getSheetAt(0);
		int RowCount=sh.getLastRowNum();
		Row firstrow=sh.getRow(0);
		int ColCount=firstrow.getLastCellNum();
		Object[][] obj=new Object[RowCount][ColCount];
		for(int i=1;i<=RowCount;i++)
		{
			Row row=sh.getRow(i);
			for(int j=0; j<row.getLastCellNum(); j++){
				Cell cell=row.getCell(j);
				//System.out.print(cell+" ");
				
				obj[i-1][j]=cell.getStringCellValue();
				
				System.out.println(obj);
		
			}
			System.out.print("\n");
		}
			
		return obj;
	}

  }

