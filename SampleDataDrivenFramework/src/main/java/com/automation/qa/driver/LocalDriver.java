package com.automation.qa.driver;

import org.openqa.selenium.WebDriver;

public class LocalDriver implements IBrowserProvider{

	private WebDriver driver;
	public LocalDriver(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	@Override
	public WebDriver getCurrentDriver(IDriverProvider driverProvider) {
		// TODO Auto-generated method stub
		if(driver==null){
			driver=driverProvider.getDriver();
		}
		return driver;
	}

	@Override
	public WebDriver getCurrentDriver(IDriverProvider driverProvider,
			String browserName) {
		// TODO Auto-generated method stub
		if(driver==null){
			driver=driverProvider.getDriver(browserName);
		}
		return driver;
	}

}
