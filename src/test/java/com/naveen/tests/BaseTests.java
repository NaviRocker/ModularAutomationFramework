package com.naveen.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.naveen.pages.LoginPage;

import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenshotUtils;

public class BaseTests {
	
	CommonDriver cmnDriver;
	String url;
	
	WebDriver driver;
	
	LoginPage loginpage;
	
	String configFileName;
	String pwd;
	
	Properties configProperty;
	
	String reportFilename;
	ReportUtils reportUtils;
	
	ScreenshotUtils screenshot;
	
	@BeforeSuite
	public void preSetup() throws Exception {
		pwd = System.getProperty("user.dir");
		
		configFileName = pwd + "/config/config.properties";
		reportFilename = pwd + "/reports/NaveenTests.html";
		
		configProperty = ConfigUtils.readProperties(configFileName);
		reportUtils = new ReportUtils(reportFilename);
	
	}
	
	@BeforeClass
	public void setup() throws Exception{
		url = configProperty.getProperty("baseURL");
		
		String browserType = configProperty.getProperty("browserType");
		
		cmnDriver = new CommonDriver(browserType);
		driver = cmnDriver.getDriver();
		loginpage = new LoginPage(driver);
		screenshot = new ScreenshotUtils(driver);
		cmnDriver.navigateToURL(url);
	}
	
	@AfterMethod
	public void postTestAction(ITestResult result) throws Exception {
		String testcasename = result.getName();
		long executionTime = System.currentTimeMillis();
		
		String screenshotFilename = pwd + "/screenshots/" + 
				testcasename + executionTime +".jpeg";
		
		if(result.getStatus() == ITestResult.FAILURE) {
			reportUtils.addTestLog(Status.FAIL, "One or More Steps Failed");
			screenshot.captureAndSaveScreenshot(screenshotFilename);
			reportUtils.attachScreenshotToReport(screenshotFilename);
			
		}
	}
	
	@AfterClass
	public void tearDown() {
		cmnDriver.closeAllBrowser();

	}
	
	@AfterSuite
	public void postTeardown() {
		reportUtils.flushReport();
	}
	
	
	

}
