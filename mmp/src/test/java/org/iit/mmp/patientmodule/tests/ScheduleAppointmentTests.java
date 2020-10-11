package org.iit.mmp.patientmodule.tests;

import java.util.HashMap;
import org.iit.mmp.base.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;


  public class ScheduleAppointmentTests extends HelperClass {
	
	
	public ScheduleAppointmentTests() {
		super();
		
	}


	HelperClass helperObj;
	ScheduleAppointmentPage sPage;
	
	
   /*
    *  Adding testcases to the project and changing the date for appointment
    */
	
	@Test
	public void validateAppointmentDetails() throws Exception
	{
		
		createDriverInstance();
		invokeWebApplication("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		login("ria1","Ria12345");
		navigateToAModule("Schedule Appointment");
		sPage = new ScheduleAppointmentPage(driver);
		HashMap<String,String> hMap =sPage.scheduleAppointment("Dr.Charlie","20/10/2020");
		navigateToAModule("HOME");
		boolean hresult = sPage.validateAppointmentDetails(hMap);
		Assert.assertTrue(hresult);
		navigateToAModule("Schedule Appointment");
		Thread.sleep(3000);
		boolean sresult = sPage.validateAppointmentSchedulePage(hMap);
		Assert.assertTrue(sresult);
		closeDriver();
		
	}
		 


}
