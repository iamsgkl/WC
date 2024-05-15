package pageObjects;



import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class BeCognizant extends BaseClass {
	
	public WebElement worldClockElement;
	
	
	public static void beCognizantPage(String baseUrl, String localTime, String localDate, String londonTime, String londonDate, String londonBehind, String nyTime, String nyDate, String nyBehind, String localPlus, String londonPlus, String nyPlus) throws InterruptedException, IOException {

		js = (JavascriptExecutor)driver;
		
		driver.get(baseUrl);
		System.out.println("Current WebPage:"+driver.getCurrentUrl());
		driver.manage().window().maximize();
		Thread.sleep(10000);
		
		driver.findElement(By.id("O365_HeaderRightRegion")).click();
		Thread.sleep(5000);
		String userName = driver.findElement(By.xpath("//*[@id=\"mectrl_currentAccount_primary\"]")).getText();
		
		//Screenshot is taken using the following statement
		//UserName Screenshot
		MakeBorder(driver.findElement(By.xpath("//*[@id=\"mectrl_currentAccount_primary\"]")));Thread.sleep(1000);TakeScreenshot("UserName");
		
		String userMail = driver.findElement(By.xpath("//*[@id=\"mectrl_currentAccount_secondary\"]")).getText();
		
		//UserMail Screenshot
		MakeBorder(driver.findElement(By.xpath("//*[@id=\"mectrl_currentAccount_secondary\"]")));Thread.sleep(1000);TakeScreenshot("UserMail");
		
		//UserName and UserMail are fetched and produced in the console
		System.out.println("\n\nUSER INFORMATION\n---------------------------");
		System.out.println("Name: "+userName);
		System.out.println("Email: "+userMail);
		System.out.println("---------------------------");
		
		driver.findElement(By.id("O365_HeaderRightRegion")).click();
		Thread.sleep(5000);
		
		//Using JavaScript Executor to scroll till it finds the World Clock element
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//*[@id=\"c24ff0ed-b166-42e5-b7d5-57c9a9e573cb\"]/div/div/div/p/a")));
		Thread.sleep(3000);
		
		//WorldClock Screenshot
		MakeBorder(driver.findElement(By.xpath("//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div")));Thread.sleep(1000);TakeScreenshot("WorldClock");
		
		//Text from World CLock element is fetched and formatted into testable text
		WebElement worldClockElement = driver.findElement(By.xpath("//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div"));
		String worldClockText = worldClockElement.getText();
		worldClockText = worldClockText.replace(" ", "");
		worldClockText = worldClockText.replace(",", "\n");
		String[] worldClockList = worldClockText.split("\n");
		
		//Tests for the World Clock text validations
		String worldClockValidator = (worldClockList[0].equals("WorldClock"))?"\nWorld Clock section is displayed\n":"\nWorld Clock section is not displayed\n";
		System.out.println(worldClockValidator);
		
		String localTimeValidator = (worldClockList[3].equalsIgnoreCase(localTime))?"\nBeCognizant Local Time:"+worldClockList[3]+"\nSystem Local Time:"+localTime+"\nPASS\n":((worldClockList[3].equalsIgnoreCase(localPlus))?"\nBeCognizant Local Time:"+worldClockList[3]+"\nSystem Local Time:"+localPlus+"\nPASS\n":"\nBeCognizant Local Time:"+worldClockList[3]+"\nSystem Local Time:"+localTime+"\nFAIL\n");
		System.out.println(localTimeValidator);
		
		String localDateValidator = (worldClockList[5].equalsIgnoreCase(localDate))?"\nBeCognizant Local Date:"+worldClockList[5]+"\nSystem Local Date:"+localDate+"\nPASS\n":"\nBeCognizant Local Date:"+worldClockList[5]+"\nSystem Local Date:"+localDate+"\nFAIL\n";
		System.out.println(localDateValidator);
		
		String londonTimeValidator = (worldClockList[8].equalsIgnoreCase(londonTime))?"\nBeCognizant London Time:"+worldClockList[8]+"\nSystem London Time:"+londonTime+"\nPASS\n":((worldClockList[8].equalsIgnoreCase(londonPlus))?"\nBeCognizant London Time:"+worldClockList[8]+"\nSystem London Time:"+localPlus+"\nPASS\n":"\nBeCognizant London Time:"+worldClockList[8]+"\nSystem London Time:"+londonTime+"\nFAIL\n");
		System.out.println(londonTimeValidator);
		
		String londonBehindValidator = (worldClockList[9].equalsIgnoreCase(londonBehind))?"\nBeCognizant London Difference:"+worldClockList[9]+"\nSystem London Difference:"+londonBehind+"\nPASS\n":"\nBeCognizant London Difference:"+worldClockList[9]+"\nSystem London Difference:"+londonBehind+"\nFAIL\n";
		System.out.println(londonBehindValidator);
		
		String londonDateValidator = (worldClockList[11].equalsIgnoreCase(londonDate))?"\nBeCognizant London Date:"+worldClockList[11]+"\nSystem London Date:"+londonDate+"\nPASS\n":"\nBeCognizant London Date:"+worldClockList[11]+"\nSystem London Date:"+londonDate+"\nFAIL\n";
		System.out.println(londonDateValidator);
		
		String nyTimeValidator = (worldClockList[14].equalsIgnoreCase(nyTime))?"\nBeCognizant NY Time:"+worldClockList[14]+"\nSystem NY Time:"+nyTime+"\nPASS\n":((worldClockList[14].equalsIgnoreCase(nyPlus))?"\nBeCognizant NY Time:"+worldClockList[14]+"\nSystem NY Time:"+nyPlus+"\nPASS\n":"\nBeCognizant London Time:"+worldClockList[14]+"\nSystem NY Time:"+nyTime+"\nFAIL\n");
		System.out.println(nyTimeValidator);
		
		String nyBehindValidator = (worldClockList[15].equalsIgnoreCase(nyBehind))?"\nBeCognizant NY Difference:"+worldClockList[15]+"\nSystem NY Difference:"+nyBehind+"\nPASS\n":"\nBeCognizant NY Difference:"+worldClockList[15]+"\nSystem NY Difference:"+nyBehind+"\nFAIL\n";
		System.out.println(nyBehindValidator);
		
		String nyDateValidator = (worldClockList[17].equalsIgnoreCase(nyDate))?"\nBeCognizant NY Date:"+worldClockList[17]+"\nSystem NY Date:"+nyDate+"\nPASS\n":"\nBeCognizant NY Date:"+worldClockList[17]+"\nSystem NY Date:"+nyDate+"\nFAIL\n";
		System.out.println(nyDateValidator);
		
		Thread.sleep(10000);
		
		//Using JavaScript Executor till it finds the OneCognizant element
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.linkText("OneCognizant")));
		driver.findElement(By.linkText("OneCognizant")).click();
		Thread.sleep(5000);
		
	}
	
	
	
	
}