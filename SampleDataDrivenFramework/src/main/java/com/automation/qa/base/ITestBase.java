package com.automation.qa.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public interface ITestBase {
	@BeforeClass
	public void setUp();
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browserName);
	public WebDriver getDriver();
	public void tearDown();

}
