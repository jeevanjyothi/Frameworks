package com.automation.qa.driver;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.qa.dataprovider.Config;


public class RemoteBrowserProvider implements IDriverProvider{
	public static String browserName;
	Config conf=new Config(System.getProperty("user.dir")+"\\config\\config.properties");
	public RemoteBrowserProvider(){
		
	}

	@Override
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		String browser=conf.getValue("driver.browsername");
		return getBrowser(browser);
	}

	@Override
	public WebDriver getDriver(String browserName) {
		// TODO Auto-generated method stub
		return getBrowser(browserName);
	}
	
	public WebDriver getBrowser(String Browser){
		
		// Launches Browser
				DesiredCapabilities caps=null;
				if(Browser.equalsIgnoreCase("firefox"))
					{

					caps=DesiredCapabilities.firefox();
					caps.setPlatform(org.openqa.selenium.Platform.ANY);
					caps.setBrowserName("firefox");
					browserName=caps.getBrowserName();
					System.out.println("browserName:"+browserName);
					
					}
				else if(Browser.equalsIgnoreCase("chrome"))
					{
					
					caps=DesiredCapabilities.chrome();
					caps.setPlatform(org.openqa.selenium.Platform.ANY);
					caps.setBrowserName("chrome");
					browserName=caps.getBrowserName();
					System.out.println("browserName:"+browserName);
					}
				else if(Browser.equalsIgnoreCase("ie"))
					{
					caps=DesiredCapabilities.internetExplorer();
					caps.setBrowserName("iexplore");
					caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
					browserName=caps.getBrowserName();
					}
				else if(Browser.equalsIgnoreCase("opera"))
					{
					caps=DesiredCapabilities.opera();
					caps.setPlatform(org.openqa.selenium.Platform.ANY);
					browserName=caps.getBrowserName();
					System.out.println("browserName:"+browserName);
					}
				else if(Browser.equalsIgnoreCase("safari"))
					{
					caps=DesiredCapabilities.safari();
					caps.setPlatform(org.openqa.selenium.Platform.ANY);
					browserName=caps.getBrowserName();
					System.out.println("browserName:"+browserName);
					}
				
				
				RemoteWebDriver rwd = null;
				try {
					rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				WebDriver wd=new Augmenter().augment(rwd);
				return wd;
		
				
	}

	@Override
	public String getBrowserName() {
		// TODO Auto-generated method stub
		return browserName;
	}

}
