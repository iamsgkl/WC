package testBase;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.BeCognizant;
import pageObjects.OneCognizant;
import utilities.WriteExcelData;

public class BaseClass {
	
	public static WebDriver driver; //static driver to traverse different methods
	public static JavascriptExecutor js; //JavaScript Executor for scrolls and injections
	public static String browser="";
	
	//Method to invoke driver
	@Parameters("browserName") //browserName is fetched as a Parameter from the testng.xml file
	@BeforeTest
	public void setup(String browserName)throws Exception {
		
		WriteExcelData.excelData(); //WriteExcelData is invoked so that it updates the current time and date into the excel sheet before testing
		Thread.sleep(2000);
		System.out.println("\n\n=====================================================================\nCurrently, Test cases are being tested in "+browserName+"\n======================================================================\n");
    	
    	if(browserName.equalsIgnoreCase("chrome")) {
    		
    		System.setProperty("webdriver.chrome.com", "user.dir/chrome.exe");
    		driver = new ChromeDriver();
    		browser=browserName;
    		
    	}
    	
    	else if(browserName.equalsIgnoreCase("firefox")) {
    		
    		System.setProperty("webdriver.firefox.com", "user.dir/geckodriver.exe");
    		driver = new FirefoxDriver();
    		browser=browserName;
    		
    	}
    	
    	else if(browserName.equalsIgnoreCase("edge")) {
    		
    		System.setProperty("webdriver.edge.com", "user.dir/edgedriver.exe");
    		driver = new EdgeDriver();
    		browser=browserName;
    		
    	}
		
	}
	
	@Test(dataProvider="testData", priority=0) //The test data is fetched using a DataProvider TestNG feature from a DataProvider method
	public void beCognizant(String baseUrl, String localTime, String localDate, String londonTime, String londonDate, String londonBehind, String nyTime, String nyDate, String nyBehind, String localPlus, String londonPlus, String nyPlus) throws InterruptedException, IOException {
		BeCognizant.beCognizantPage(baseUrl, localTime, localDate, londonTime, londonDate, londonBehind, nyTime, nyDate, nyBehind, localPlus, londonPlus, nyPlus);
	}
	
	@Test(priority=1)
	public void oneCognizant() throws InterruptedException, IOException {
		OneCognizant.oneCognizantPage();
	}
	
	
	
	//DataProvider method where the data is fetched from an Excel Sheet and parsed into the Test methods
		@DataProvider(name="testData")
		public Object[][] testData()throws IOException, FileNotFoundException {
			
			File file = new File("ExcelData/testdata.xlsx");
			FileInputStream fIS = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fIS);
			XSSFSheet sheet = wb.getSheetAt(0);
			
			String arr[][] = new String[5][5];
			
			for(int i=0;i<=3;i++) {
				
				for(int j=0;j<=4;j++) {
					
					arr[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
					
				}
			
			}
			
			wb.close();
			
			return new Object[][] {{arr[0][1], arr[1][1], arr[1][2], arr[2][1], arr[2][2], arr[2][3], arr[3][1], arr[3][2], arr[3][3], arr[1][4], arr[2][4], arr[3][4]}};
			
		}
		
		//Method to take screenshots
		public static void TakeScreenshot(String FileName) throws IOException {
				File File = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        FileUtils.copyFile(File,new File("TestScreenshots/"+ FileName + "_" + browser + ".jpeg"));
		    }

		    //Method to inject a border on the particular element before screenshots
		    public static void MakeBorder(WebElement Element) {
		        JavascriptExecutor js = (JavascriptExecutor)driver;
		        js.executeScript("arguments[0].style.border = '3px solid red'",Element);
		    }
	
	//Terminates the driver
		@AfterTest
		public void tearDown() {
			
			driver.quit();//quit closes the window of the browser
			System.out.println("\n"+browser+" Tests are over.");
			
		}
		
		@AfterSuite
		public void reports() throws IOException {
			
			File file = new File("test-output/emailable-report.html");
			
			Desktop desktop = Desktop.getDesktop();
	        if(file.exists()) 
	        	desktop.open(file);
			
		}

}
