package com.music.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class UploadFileAndFindGenere {
	WebDriver driver;
	@Test
	public void TestCase() throws AWTException, InterruptedException {
		System.out.println("Os Name : "+System.getProperty("os.name"));
                System.out.println("Path : "+System.getProperty("user.dir")+"/");
		if(System.getProperty("os.name").toLowerCase().contains("windows")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/"+"chromedriver.exe");
		}else if(System.getProperty("os.name").toLowerCase().contains("linux")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/"+"chromedriver");
		}
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8001/");
		System.out.println("Title of the page : "+driver.getTitle());
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Upload File']/parent::span")));
		driver.findElement(By.xpath("//span[text()='Upload File']/parent::span")).click();
		
		Thread.sleep(5000);		
		
		System.out.println("Path : "+System.getProperty("user.dir")+"/"+"file_example_WAV_2MG.wav");
		
		//put path to your image in a clipboard
	    StringSelection ss = new StringSelection(System.getProperty("user.dir")+"/"+"file_example_WAV_2MG.wav");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

	    //imitate mouse events like ENTER, CTRL+C, CTRL+V
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Cancel']/parent::button[@style='display: none;']")));
	    driver.findElement(By.xpath("//button[@id='find']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Pop']/parent::button")));
	    
	    Thread.sleep(5000);
	    
	    driver.quit();
	}
	
}
