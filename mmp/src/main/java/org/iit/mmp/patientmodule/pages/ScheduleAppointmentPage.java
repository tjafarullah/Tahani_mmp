package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.mmp.base.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {
	
	private WebDriver driver;
	HelperClass helperObj;
	
	private By datePickerID = By.id("datepicker");
	private By timeID = By.id("time");
	By anyPlace = By.xpath("//body");
	private By continueButton= By.xpath("//button[@id='ChangeHeatName']");
	private By symID = By.xpath("//textarea[@id='sym']");
	

	
	public ScheduleAppointmentPage(WebDriver driver) {
		 
		 this.driver = driver;
		 helperObj = new HelperClass(driver);
	}

	public HashMap<String, String> scheduleAppointment(String doctorName,String appointmentDate) throws Exception
	{
	 
		HashMap<String,String> hMap = new HashMap<String,String>();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[text()='"+doctorName+"']/ancestor::ul/following-sibling::button")).click();
		//Thread.sleep(3000);
		driver.switchTo().frame("myframe");
		driver.findElement(datePickerID).sendKeys(appointmentDate);
		String time = "10Am";
		new Select(driver.findElement(timeID)).selectByVisibleText(time);
		driver.findElement(anyPlace).click();
		Thread.sleep(3000);
		driver.findElement(continueButton).click();
		String sym="Fever";
		driver.findElement(symID).sendKeys(sym);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		hMap.put("doctorName", doctorName);
		hMap.put("appointmentDate", appointmentDate);
		hMap.put("time", time);
		hMap.put("sym", sym);
		return hMap;
	}

	
		


}
