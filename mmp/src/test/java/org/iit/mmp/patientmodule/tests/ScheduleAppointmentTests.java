package org.iit.mmp.patientmodule.tests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.iit.mmp.base.HelperClass;
import org.iit.mmp.patientmodule.pages.HomePage;
import org.iit.mmp.patientmodule.pages.LoginPage;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentTests {
	
WebDriver driver; 

   /*
    *  Adding testcases to the project and changing the date for appointment
    */
	
	@Test
	public void validateAppointmentDetails() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();//Initialization 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		HelperClass helperObj = new HelperClass(driver);
		helperObj.invokeWebApplication("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		LoginPage lPage = new LoginPage(driver);
		lPage.login("ria1","Ria12345");
		helperObj.navigateToAModule("Schedule Appointment");
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		HashMap<String,String> hMap =sPage.scheduleAppointment("Dr.Charlie","20/10/2020");
		HomePage hPage = new HomePage(driver);
		boolean hresult = hPage.validateAppointmentDetails(hMap);
		Assert.assertTrue(hresult);
		helperObj.navigateToAModule("Schedule Appointment");
		Thread.sleep(3000);
		boolean sresult = hPage.validateAppointmentSchedulePage(hMap);
		Assert.assertTrue(sresult);
		
	}
		 


}
