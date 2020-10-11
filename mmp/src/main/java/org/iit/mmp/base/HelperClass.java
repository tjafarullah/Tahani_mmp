package org.iit.mmp.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelperClass {
	
	protected WebDriver driver;
	protected HelperClass helperObj;
	
	private By usernameID = By.id("username");
	private By passwordID = By.id("password");
	private By submitName = By.name("submit");
	
		
	public void createDriverInstance()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();//Initialization
	
	}
	
	public void navigateToAModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')] ")).click();
	}
	
	public void invokeWebApplication(String url)
	{
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void login(String uname,String pword)
	{
	 
		driver.findElement(usernameID).sendKeys(uname);
		driver.findElement(passwordID).sendKeys(pword);
		driver.findElement(submitName).click();
	}
	
	public void closeDriver() 
	{
		
		driver.close();
	}

	
	
	
	


}
