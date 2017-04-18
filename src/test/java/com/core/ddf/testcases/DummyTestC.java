package com.core.ddf.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.core.ddf.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class DummyTestC extends BaseTest{
	@Test
	public void testB(){
		extentTest=extentReports.startTest("DummyTestC");
		extentTest.log(LogStatus.INFO, "Start");
		extentTest.log(LogStatus.FAIL, "Screenshot->"+extentTest.addScreenCapture("H:\\JavaProject\\DataDrivenFramework\\DataDrivenFramework\\screenshots\\test.png"));
	}
	@AfterTest
	public void quit(){
		extentReports.endTest(extentTest);
		extentReports.flush();
	}
}
