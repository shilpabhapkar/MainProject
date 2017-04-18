package com.core.ddf.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.core.ddf.base.BaseTest;
import com.core.ddf.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DummyTestB extends BaseTest{
	@Test
	public void testB(){
		
		extentTest=extentReports.startTest("DummyTestB");
		extentTest.log(LogStatus.INFO, "Opening Browser");
		openBrowser("chrome");
		extentTest.log(LogStatus.INFO, "Navigating to URL");
		navigate("appUrl");
		extentTest.log(LogStatus.INFO, "Clicking on SIGN IN");
		click("signin_xpath");
		if(!verifyText("signinTextPath_xpath","signinText"))
			reportSoftAssertFail("text not matching");
			
		extentTest.log(LogStatus.INFO, "Typing email");
		if(!isElementPresent("email_xpath"))
			reportFail("element does not match");
		type("email_xpath", "shilpabhapkar10");
		extentTest.log(LogStatus.INFO, "Submiting form");
		click("submit_xpath");
		verifyTitle();
		reportFail("Titel does not match");
		extentTest.log(LogStatus.INFO, "Form Submited");
		takeSC();
	}
	@AfterTest
	public void quit(){
		softAssert.assertAll();
		extentReports.endTest(extentTest);
		extentReports.flush();
	}
}
