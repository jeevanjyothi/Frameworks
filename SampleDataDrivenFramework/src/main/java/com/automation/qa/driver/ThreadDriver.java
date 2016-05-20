package com.automation.qa.driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.automation.qa.listener.EventListener;

public class ThreadDriver implements IBrowserProvider{
	
	ThreadLocal<WebDriver> threadDriver=new ThreadLocal<WebDriver>();
	private WebDriver driver;
	public ThreadDriver(WebDriver driver){
		this.driver=driver;
	}

	@Override
	public WebDriver getCurrentDriver(IDriverProvider driverProvider) {
		// TODO Auto-generated method stub
		driver=threadDriver.get();
		//threadDriver.set(driver);
		if(driver==null){
			driver=driverProvider.getDriver();
			threadDriver.set(driver);
			WebDriverEventListener eventListener = new EventListener();
			driver = new EventFiringWebDriver(driver);
	        ((EventFiringWebDriver) driver).register(eventListener);
		}
		return driver;
	}

	@Override
	public WebDriver getCurrentDriver(IDriverProvider driverProvider,String browserName) {
		// TODO Auto-generated method stub
		driver=threadDriver.get();
		//threadDriver.set(driver);
		if(driver==null){
			driver=driverProvider.getDriver(browserName);
			threadDriver.set(driver);
			WebDriverEventListener eventListener = new EventListener();
			driver = new EventFiringWebDriver(driver);
	        ((EventFiringWebDriver) driver).register(eventListener);
		}
		return driver;
	}
	

}
