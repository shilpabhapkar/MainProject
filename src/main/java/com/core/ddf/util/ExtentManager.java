package com.core.ddf.util;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	public static ExtentReports extentReports;
	public static ExtentReports getInstance(){
		Date date=new Date();
		String fileName=date.toString().replace(":", "_").replace(" ", "_")+".html";
		if(extentReports==null){
			extentReports= new ExtentReports(System.getProperty("user.dir")+"\\reports\\"+fileName+"",true,DisplayOrder.NEWEST_FIRST);
			extentReports.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			extentReports.addSystemInfo("Selenium version", "2.45.0").addSystemInfo("Environment","QA");
		}
		return extentReports;
	}
}
