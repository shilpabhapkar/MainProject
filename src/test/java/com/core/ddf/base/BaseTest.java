package com.core.ddf.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.core.ddf.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



/**
 * Contains All the tests 
 */
public class BaseTest 
{
	public SoftAssert softAssert =new SoftAssert();
	
	public WebDriver driver;
	public Properties prop;
	public ExtentReports extentReports=ExtentManager.getInstance();;
	public ExtentTest extentTest;
   public void openBrowser(String browserType){
	   if(prop==null){
		   prop=new Properties();
		   try{
			   FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//projectConfiguration.properties");
			   prop.load(fis);
		   }catch(Exception e){
			  e.printStackTrace(); 
		   }
		   
	   }
	   if(browserType.equals("chrome")){
		   System.setProperty("webdriver.chrome.driver", "H:\\drivers\\chromedriver.exe");
		   driver=new ChromeDriver();
		   
	   }else if(browserType.equals("firefox")){
		  driver=new FirefoxDriver(); 
	   }
	   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
   }
 public void navigate(String urlKey){
	   driver.get(prop.getProperty(urlKey));
	   
   }
 public void click(String locatorKey){
	//driver.findElement(By.xpath(prop.getProperty(xpathEleKey))).click();
	 getElement(locatorKey).click();
	 }
 public void type(String xpathEleKey, String data){
	driver.findElement(By.xpath(prop.getProperty(xpathEleKey))).sendKeys(data);   
 }
 public WebElement getElement(String locatorKey){
	 WebElement e=null;
	 try{
		if(locatorKey.endsWith("_xpath")){
			e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		}
		else{
			reportFail("Locator incorrect: "+locatorKey);
			Assert.fail("Fail test: "+locatorKey);
		}
	 }catch(Exception ex){
		 reportFail(ex.getMessage());
		 ex.printStackTrace();
		 Assert.fail("Fail test: "+ex.getMessage());
		 
	 }
	 return e;
	 }
  //*********************validations*********************** 
 public boolean verifyTitle(){
	return false;
	   
 }
 public boolean verifyText(String locatorKey, String expectedTextKey){
	 String actualText=getElement(locatorKey).getText().trim();
	 String expectedText=System.getProperty(expectedTextKey);
	 if(actualText.equals(expectedText))
		 return true;
	 else
	return false;
	   
 }
 public boolean isElementPresent(String locatorKey){
	 List<WebElement> webelementList=null;
	 if(locatorKey.endsWith("_xpath")){
		 webelementList=driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		}
		else{
			reportFail("Locator incorrect: "+locatorKey);
			Assert.fail("Fail test: "+locatorKey);
		}
	 if(webelementList.size()==0)
	return false;
	 else
		 return true;
	 
 }

 //*********reporting***********************
 public void reportPass(String msg){	
	 extentTest.log(LogStatus.PASS, msg);
	 }
 public void reportFail(String msg){
	 extentTest.log(LogStatus.FAIL, msg);
	 takeSC();
	 Assert.fail(msg);
 }
 public void reportSoftAssertFail(String msg){
	 extentTest.log(LogStatus.FAIL, msg);
	 takeSC();
	 softAssert.fail(msg);
 }
 public void takeSC(){		
	 File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 Date date=new Date();
		String screenshotFileName=date.toString().replace(":", "_").replace(" ", "_")+".png";
	
	 try{
		 FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFileName));
	 }catch(IOException e){
		 e.printStackTrace();
		 
	 }
	 //put sc in report
	 extentTest.log(LogStatus.FAIL, "Screenshot->"+extentTest.addScreenCapture(System.getProperty("user.dir")+"//screenshots//"+screenshotFileName));
		
 }
}
