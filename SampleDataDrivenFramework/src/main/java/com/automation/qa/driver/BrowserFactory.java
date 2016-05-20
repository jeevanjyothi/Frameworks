package com.automation.qa.driver;

import com.automation.qa.dataprovider.Config;


public class BrowserFactory extends DriverTypeProvider{
	
	Config conf=new Config(System.getProperty("user.dir")+"\\config\\config.properties");
	 public IDriverProvider browser;
	
	/**
	 * It will return type of Browser. If it is a remote browser return remote browser otherwise local browser
	 * @return
	 */
	@Override
	public IDriverProvider getBrowserType(){
		if(conf.getValue("driver.browsertype").equalsIgnoreCase("local")){
			browser=new BrowserProvider();
			
			return browser;
			
		}
		else if(conf.getValue("driver.browsertype").equalsIgnoreCase("remote")){
			browser= new RemoteBrowserProvider();
			
			 return browser;
			 
		}
		return null;
	}
	
	/**
	 * to get driver type. It will return different types of driver as per the requirement
	 */
	@Override
	public IBrowserProvider getDriverType() {
		// TODO Auto-generated method stub
		if(conf.getValue("driver.drivertype").equalsIgnoreCase("local")){
			System.out.println("local driver called");
			return new LocalDriver(null);
		}
		else if(conf.getValue("driver.drivertype").equalsIgnoreCase("remote")){
			System.out.println("remote driver called");
			return new RemoteDriver(null);
		}
		else if (conf.getValue("driver.drivertype").equalsIgnoreCase("thread")){
			System.out.println("thread driver called");
			return new ThreadDriver(null);
		}
		
		return null;
	}
	

}
