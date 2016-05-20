package com.automation.qa.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.qa.dataprovider.Config;
import com.automation.qa.driver.DriverFactory;


public class TestBase {
	protected WebDriver driver;
	protected DriverFactory driverFactory=new DriverFactory();
	Config conf=new Config(System.getProperty("user.dir") + "/config/config.properties");
	
    public WebDriver getDriver() {
    	return driver;
    }


    @Parameters("browser")
    @BeforeTest
	
	public void setUp(@Optional()String browserName) {
		// TODO Auto-generated method stub
    	
    	if(conf.getValue("tesng.parameter").equalsIgnoreCase("YES")){
    		driver=driverFactory.getDriver(browserName);
    	}
    	else{
    		driver=driverFactory.getDriver();
    	}
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
	}


	
	@AfterTest
	public void tearDown() {
		// TODO Auto-generated method stub
		if (driver != null)
	    {
	            try
	            {
	                driver.quit();
	            }
	            catch (WebDriverException e) {
	                System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
	                System.out.println(e);
	            }

	    }
		
	}

}
