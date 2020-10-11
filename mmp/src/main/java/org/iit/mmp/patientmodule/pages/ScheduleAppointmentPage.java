package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.mmp.base.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {
	
	WebDriver driver;
	HelperClass helperObj;
	
	private By createAppointmentButton = By.xpath("//input[@value='Create new appointment']");
	private By datePickerID = By.id("datepicker");
	private By timeID = By.id("time");
	By anyPlace = By.xpath("//body");
	private By continueButton= By.xpath("//button[@id='ChangeHeatName']");
	private By symID = By.xpath("//textarea[@id='sym']");
	
	// Home page
	By tableRowXpath =By.xpath("//table[@class='table']/tbody/tr[1]") ;
		
   //Schedule Appointment Page
	By dateOfAppointmentSP = By.xpath("(//h3[@class='panel-title'])[2]");
	By timeSP = By.xpath("//a[contains(text(),'Time :')]");
	By symptomsSP = By.xpath("//a[contains(text(),'Symptoms:')]");
	By doctorNameSP =  By.xpath("//a[contains(text(),'Provider:')]");
		
	

	
	public ScheduleAppointmentPage(WebDriver driver) {
		 
		 this.driver = driver;
		
	}

	

	public HashMap<String, String> scheduleAppointment(String doctorName,String appointmentDate) throws Exception
	{
	 
		HashMap<String,String> hMap = new HashMap<String,String>();
		driver.findElement(createAppointmentButton).click();
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
	
	public boolean validateAppointmentDetails(HashMap<String,String> hMap)
	{
		boolean result =false;
		//helperObj.navigateToAModule("HOME");
		String rowText = driver.findElement(tableRowXpath ).getText();
		String appointDetails[]  = rowText.split(" ");
		
		System.out.println("Home Page");
		System.out.println(hMap.get("appointmentDate").trim().equals(appointDetails[0].trim()));
		System.out.println( hMap.get("time").trim().equals(appointDetails[1].trim()));
		System.out.println(hMap.get("sym").trim().equals(appointDetails[2].trim()));
		System.out.println( hMap.get("doctorName").trim().contains(appointDetails[3].trim()));
		
		if	(hMap.get("appointmentDate").trim().equals(appointDetails[0].trim())
		    &&  hMap.get("time").trim().equals(appointDetails[1].trim())
		    &&  hMap.get("sym").trim().equals(appointDetails[2].trim())
		    &&  hMap.get("doctorName").trim().contains(appointDetails[3].trim()))
		{
			result = true;
		}
		return result;
	}
	
	public boolean validateAppointmentSchedulePage(HashMap<String,String> hMap)
	{
		boolean result = false;
		//helperObj.navigateToAModule("Schedule Appointment");
		WebElement we1 = driver.findElement(timeSP);
		WebElement we2 = driver.findElement(doctorNameSP);
		WebElement we3 = driver.findElement(symptomsSP);
		WebElement w4=driver.findElement(dateOfAppointmentSP);
		
		String appTime[] = we1.getText().trim().split(":\\s*");
		String providerName[] =we2.getText().split(":");
		String symptoms[] = we3.getText().split(":");
		String date = w4.getText();
		
		System.out.println("Schedule Appointment Page");
		System.out.println( hMap.get("appointmentDate").equals(date.trim()));
		System.out.println( hMap.get("time").equals(appTime[1].trim()));
		System.out.println( hMap.get("sym").equals(symptoms[1].trim()));
		System.out.println( hMap.get("doctorName").contains(providerName[1]));
		
        
		if( hMap.get("appointmentDate").equals(date.trim())
				&& hMap.get("time").equals(appTime[1].trim())
				&& hMap.get("sym").equals(symptoms[1].trim())
				&& hMap.get("doctorName").contains(providerName[1]))  
		{
			result = true;
		}

		return result;
	}
	
	

	
		


}
