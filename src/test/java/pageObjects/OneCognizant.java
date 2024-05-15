package pageObjects;


import java.io.IOException;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;

public class OneCognizant extends BeCognizant {
	
	public static void oneCognizantPage() throws InterruptedException, IOException {

		//Handling Windows to let the driver switch to the OneCognizant tab from BeCognizant tab
		String parent = driver.getWindowHandle();
		Set<String> winHandles = driver.getWindowHandles();
		for (String handle : winHandles) {
			if (!handle.equals(parent)) {
				driver.switchTo().window(handle);
			}
		}
		Thread.sleep(10000);
		
		System.out.println("Current WebPage:"+driver.getCurrentUrl());
		
		js.executeScript("window.scrollBy(1500,1000)");
		Thread.sleep(5000);
		
		driver.findElement(By.className("viewAllHotAppsBtn")).click();
		Thread.sleep(5000);
		
		//AlphabetCard Screenshot
		MakeBorder(driver.findElement(By.className("a2zCharHolderDiv")));Thread.sleep(1000);TakeScreenshot("AlphabetCard");
		
		String alphabetsText = driver.findElement(By.className("a2zCharHolderDiv")).getText();
		
		//Formatting the fetched data from the alphabet card into testable text
		alphabetsText = alphabetsText.replace("#", "");
		alphabetsText = alphabetsText.replace("\n", " ");
		
		//Tests to validate the alphabets
		String alphabetValidator = (!alphabetsText.matches("[^A-Z]"))?"\nCaptured Alphabets:"+alphabetsText+"\nPass\n":"\nCaptured Alphabets:"+alphabetsText+"\nFail\n";
		System.out.println(alphabetValidator);
		
		alphabetsText = alphabetsText.replace("X", "");
		alphabetsText = alphabetsText.replace("Y", "");
		alphabetsText = alphabetsText.replace(" ", "");
		Thread.sleep(5000);
		
		//Letting the program choose a random letter to continue testing
		Random rand = new Random();
		char randomChar = alphabetsText.charAt(rand.nextInt(alphabetsText.length()));
		String randomLetter = Character.toString(randomChar);
		System.out.println("\nApp details for alphabet: " + randomLetter);
		driver.findElement(By.xpath("//*[@aria-label='Filter apps starts with alphabet " + randomLetter + "']")).click();
		Thread.sleep(5000);
		
		//Apps Screenshot
		MakeBorder(driver.findElement(By.cssSelector("#div_appFilteredList > div")));Thread.sleep(1000);TakeScreenshot("Apps");
		String allApps = driver.findElement(By.cssSelector("#div_appFilteredList > div")).getText();
		
		//Producing all the application names in the concole
		System.out.println("\n"+allApps);
 
	}

}
