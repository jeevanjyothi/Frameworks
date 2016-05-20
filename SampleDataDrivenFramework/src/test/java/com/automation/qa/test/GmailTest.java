package com.automation.qa.test;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.qa.base.TestBase;
import com.automation.qa.dataprovider.Xls_Reader;
import com.automation.qa.util.CommonMethod;
import com.automation.qa.util.TestUtil;

public class GmailTest extends TestBase
{
	Xls_Reader smokeTest = new Xls_Reader(System.getProperty("user.dir") + "/TestData/smoke.xls");
    CommonMethod commonM = new CommonMethod(System.getProperty("user.dir") + "/locator/gmail.properties");
	@DataProvider
    public Object[][] getDataForValidLogin() {
    	return TestUtil.getData(smokeTest,"TestData","testLoginWithValidCredentials");
    }
	
    @Test(dataProvider = "getDataForValidLogin", priority=-1)
	public void testLoginWithValidCredentials(
			Hashtable<String, String> data) throws InterruptedException {
  
    	driver.get("https://mail.google.com/");
    	Thread.sleep(2000);
    	commonM.clearAndSendKeys(driver, "email", data.get("username"));
    	commonM.click(driver, "next");
    	Thread.sleep(2000);
    	commonM.clearAndSendKeys(driver, "passwd", data.get("password"));
    	commonM.click(driver, "submit");
    	Thread.sleep(2000);
    }
}
