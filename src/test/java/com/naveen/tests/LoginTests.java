package com.naveen.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class LoginTests extends BaseTests {
	
	@Parameters({"username", "userPassword"})
	@Test
	public void verifyUserLoginWithCorrectCredentials(String username, String password) {
		reportUtils.createATestCase("Verify User Login With Correct Credentials");
		reportUtils.addTestLog(Status.INFO, "Performing Login");
		
		loginpage.loginToApplication(username, password);
		
		String expectedTitle = "Guru99 Bank Manager HomePage";
		String actualTitle = cmnDriver.getTitleOfThePage();
		
		reportUtils.addTestLog(Status.INFO, "Comparing Expected and Actual Title");
		Assert.assertEquals(actualTitle, expectedTitle);
		
		
		
	}

}
