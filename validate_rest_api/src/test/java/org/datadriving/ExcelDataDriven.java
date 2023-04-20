package org.datadriving;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataDriven {

	String projectDir = FileSystems.getDefault().getPath(".").toString();
	
	//Identify Testcases column by scanning the entire 1st row
	//once column is identified, then scan entire testcase column to identify testcase row
	//after you grab testcase row = pull all the data of that row and feed into test
	
	public ArrayList<Object> getData(String excelRelFromProjRoot, String sheetName, String columnName, String columnRowItem) throws IOException
	{
		//fileInputStream argument
				ArrayList<Object> dataList=new ArrayList<>();
				
				FileInputStream fis=new FileInputStream(projectDir+"/"+excelRelFromProjRoot);
				XSSFWorkbook workbook=new XSSFWorkbook(fis);
				
				int sheets=workbook.getNumberOfSheets();
				for(int i=0;i<sheets;i++)
				{
					if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
							{
					XSSFSheet sheet=workbook.getSheetAt(i);
					
					//Identify Testcases column by scanning the entire identified row from the gotten index position
					 Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
					Row firstrow= rows.next();
					Iterator<Cell> ce=firstrow.cellIterator();//row is collection of cells
					int k=0;
					int column = 0;

				while(ce.hasNext())
				{
					Cell value=ce.next();
					if(value.getStringCellValue().equalsIgnoreCase(columnName))
					{
						column=k;
					}
					k++;
				}
				System.out.println("Queried column header is found at index " + column);
				
				//once column header is identified then scan entire columns to identify the needed row to get data from
				while(rows.hasNext())
				{
					Row row=rows.next();
					if(row.getCell(column).getStringCellValue().equalsIgnoreCase(columnRowItem))
					{
						//after you grab the row = pull all the data of that row (from the gotten index position)
						Iterator<Cell>  cv=row.cellIterator();
						while(cv.hasNext())
						{
						Cell cell=	cv.next();
//						if(cell.getCellTypeEnum()==CellType.STRING)
//						{
//
//						dataList.add(cell.getStringCellValue());
//						}
//						else{
//
//							dataList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
//
//						}
							dataList.add(cell);
						}
					}
				}
								System.out.println("Given column row item does not exist, Hence data array is empty");
							}
				}
				return dataList;
	}
}
