package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.mmp.base.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver driver;
	HelperClass helperObj;
	 
	// Home page
	By tableRowXpath =By.xpath("//table[@class='table']/tbody/tr[1]") ;
	
	//Schedule Appointment Page
	By dateOfAppointmentSP = By.xpath("(//h3[@class='panel-title'])[2]");
	By timeSP = By.xpath("//a[contains(text(),'Time :')]");
	By symptomsSP = By.xpath("//a[contains(text(),'Symptoms:')]");
	By doctorNameSP =  By.xpath("//a[contains(text(),'Provider:')]");
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		helperObj = new HelperClass(driver);
	}
	
	
	public boolean validateAppointmentDetails(HashMap<String,String> hMap)
	{
		boolean result =false;
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
