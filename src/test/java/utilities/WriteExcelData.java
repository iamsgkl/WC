package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelData {
	
	public static void excelData()throws Exception {
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("baseURL");
		sheet.getRow(0).createCell(1).setCellValue("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
		sheet.getRow(0).createCell(2).setCellValue(" ");
		sheet.getRow(0).createCell(3).setCellValue(" ");
		sheet.getRow(0).createCell(4).setCellValue(" ");
		

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		
		//BangaloreTime
		LocalTime localTime = LocalTime.now();
		LocalDate localDate = LocalDate.now();
		sheet.createRow(1);
		sheet.getRow(1).createCell(0).setCellValue("Bangalore Time");
		sheet.getRow(1).createCell(1).setCellValue(localTime.format(timeFormatter));
		sheet.getRow(1).createCell(2).setCellValue(localDate.format(dateFormatter));
		sheet.getRow(1).createCell(3).setCellValue(" ");
		sheet.getRow(1).createCell(4).setCellValue(localTime.plusMinutes(1).format(timeFormatter));
		
		//LondonTime
		LocalTime londonTime = LocalTime.now(ZoneId.of("Europe/London"));
		LocalDate londonDate = LocalDate.now(ZoneId.of("Europe/London"));
		sheet.createRow(2);
		sheet.getRow(2).createCell(0).setCellValue("London Time");
		sheet.getRow(2).createCell(1).setCellValue(londonTime.format(timeFormatter));
		sheet.getRow(2).createCell(2).setCellValue(londonDate.format(dateFormatter));
		sheet.getRow(2).createCell(3).setCellValue("4h30mbehind");
		sheet.getRow(2).createCell(4).setCellValue(londonTime.plusMinutes(1).format(timeFormatter));
		
		//NYTime
		LocalTime nyTime = LocalTime.now(ZoneId.of("America/New_York"));
		LocalDate nyDate = LocalDate.now(ZoneId.of("America/New_York"));
		sheet.createRow(3);
		sheet.getRow(3).createCell(0).setCellValue("New York Time");
		sheet.getRow(3).createCell(1).setCellValue(nyTime.format(timeFormatter));
		sheet.getRow(3).createCell(2).setCellValue(nyDate.format(dateFormatter));
		sheet.getRow(3).createCell(3).setCellValue("9h30mbehind");
		sheet.getRow(3).createCell(4).setCellValue(nyTime.plusMinutes(1).format(timeFormatter));
		
		
		
		File file = new File("ExcelData/testdata.xlsx");
		
		FileOutputStream fOS = new FileOutputStream(file);
		wb.write(fOS);
		wb.close();
		
	}
	
}
