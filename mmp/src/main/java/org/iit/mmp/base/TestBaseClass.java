package org.iit.mmp.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseClass {
	
	protected WebDriver driver;
	public void createDriverInstance()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();//Initialization
		driver.manage().window().maximize();
	}


}
