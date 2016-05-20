package com.automation.qa.driver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.automation.qa.dataprovider.Config;

public class BrowserProvider implements IDriverProvider{
	String nameBrowser;
	//public static String browserName;
	Config conf=new Config(System.getProperty("user.dir")+"\\config\\config.properties");
	WebDriver driver;
	
	@Override
	public WebDriver getDriver() {
			// TODO Auto-generated method stub
			if (driver==null){
				String browser=conf.getValue("driver.browsername");
				return getBrowser(browser);
		}
		return driver;
	}

	@Override
	public WebDriver getDriver(String browserName) {
		// TODO Auto-generated method stub
		return getBrowser(browserName);
	}
	
	public WebDriver getBrowser(String browser){
		if(driver==null){
			if(browser.equalsIgnoreCase("firefox")){
				driver=new FirefoxDriver();
				return driver;
			}
			else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", conf.getValue("chrome.path"));
				return new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", conf.getValue("ie.path"));
				return new InternetExplorerDriver();
			}
			else if(browser.equalsIgnoreCase("safari")){
				return new SafariDriver();
			}
			else if(browser.equalsIgnoreCase("html")){
				return new HtmlUnitDriver();
			}
		}
		return driver;
	}

	@Override
	public String getBrowserName() {
		// TODO Auto-generated method stub
		return nameBrowser;
	}


}
