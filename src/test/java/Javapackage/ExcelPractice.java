package Javapackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPractice {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream File=new FileInputStream("D:\\Selenium Docs\\InputFiles of Testng\\Employee1.xlsx");
		Workbook wb=new XSSFWorkbook(File);
		Sheet sh=wb.getSheetAt(0);
		int RowCount=sh.getLastRowNum();
		for(int i=0;i<=RowCount;i++)
		{
			Row row=sh.getRow(i);
			for(int j=0; j<row.getLastCellNum(); j++){
				Cell cell=row.getCell(j);
				System.out.print(cell+" ");
				
				
			}
			System.out.print("\n");
		}
			
		
	}

}
