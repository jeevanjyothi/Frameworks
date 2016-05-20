package com.automation.qa.util;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import com.automation.qa.dataprovider.Config;
import com.automation.qa.dataprovider.Xls_Reader;




/**
 * @author himadri.das
 * 
 */
public class CommonMethod {
	
	 Config conf;
	 
	// -------------------------------------------------------------------------
		/**
		 * creating constructor and loading properties file
		 * @param filePath
		 */
	 public CommonMethod(String filePath){
		 
		 conf=new Config(filePath);
	 }
	 
	 

	// -------------------------------------------------------------------------
	/**
	 * Retrieve popup text message.
	 * 
	 * @param WebDriver
	 *            driver
	 * @return
	 */
	public static String getPopupMessage(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.accept();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}
		System.out.println("message" + message);
		return message;
	}

	// -------------------------------------------------------------------------
	/**
	 * Canceling popup
	 * 
	 * @param driver
	 * @return
	 */
	public static String cancelPopupMessageBox(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.dismiss();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}

		return message;
	}

	// -------------------------------------------------------------------------
	/**
	 * Check hover message text
	 * 
	 * @param driver
	 * @param by
	 * 
	 * @return string
	 * @throws IOException
	 */
	public  String checkHoverMessage(WebDriver driver,
			String objectLocater) {
		String tooltip = findElement(driver, objectLocater).getAttribute("title");
		System.out.println(tooltip);
		return tooltip;
	}

	// -------------------------------------------------------------------------
	/**
	 * Select radio button
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void selectRadioButton(WebDriver driver,
			String objectLocater, String value)  {
		List<WebElement> select = findElements(driver, objectLocater);

		for (WebElement radio : select) {
			if (radio.getAttribute("value").equalsIgnoreCase(value)) {
				radio.click();

			}
		}
	}

	// -------------------------------------------------------------------------
	/**
	 * Select multiple check boxes
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void selectCheckboxes(WebDriver driver, String objectLocater,
			String value)  {

		List<WebElement> abc = findElements(driver, objectLocater);
		List<String> list = new ArrayList<String>(Arrays.asList(value
				.split(",")));

		for (String check : list) {
			for (WebElement chk : abc) {
				if (chk.getAttribute("value").equalsIgnoreCase(check)) {
					chk.click();
				}
			}
		}
	}

	// -------------------------------------------------------------------------

	/**
	 * Select drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void selectDropdown(WebDriver driver, String objectLocater,
			String value) {
		new Select(findElement(driver, objectLocater))
				.selectByVisibleText(value);
	}

	// -------------------------------------------------------------------------

	/**
	 * Select auto-suggest search drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void selectSearchDropdown(WebDriver driver,
			String objectLocater, String value) {
		findElement(driver, objectLocater).click();
		findElement(driver, objectLocater).sendKeys(value);
		findElement(driver, objectLocater).sendKeys(Keys.TAB);
	}

	// -------------------------------------------------------------------------

	/**
	 * Upload file
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws IOException
	 * 
	 */
	public void uploadFile(WebDriver driver, String objectLocater,
			String value)  {
		findElement(driver, objectLocater).sendKeys(value);
	}

	// -------------------------------------------------------------------------

	/**
	 * Takes controls on new tab
	 * 
	 * @param driver
	 * 
	 */
	public static void handleNewTab(WebDriver driver) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		// String window0 = (String) allWindowHandles.toArray()[1];
		Iterator<String> iter = allWindowHandles.iterator();
		int size = allWindowHandles.size();
		String window0 = null;
		for (int i = 0; i < size; i++) {
			window0 = iter.next();
		}

		driver.switchTo().window(window0);
	}

	// -------------------------------------------------------------------------

	/**
	 * Takes control on parent window
	 * 
	 * @param driver
	 */
	public static void handleParentTab(WebDriver driver) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		String window0 = (String) allWindowHandles.toArray()[0];
		driver.switchTo().window(window0);
	}

	public static void handleChildParentTab(WebDriver driver) 
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
		String window0 = (String) allWindowHandles.toArray()[1];
		driver.switchTo().window(window0);
	}

	
	// -------------------------------------------------------------------------
	/**
	 * Helper method: looks through a list of WebElements, to find the first
	 * WebElement with matching text
	 * 
	 * @param elements
	 * @param text
	 * 
	 * @return WebElement or null
	 */
	public static WebElement findElementByText(List<WebElement> elements,
			String text) {
		WebElement result = null;
		for (WebElement element : elements) {
			element.getText().trim();
			if (text.equalsIgnoreCase(element.getText().trim())) {
				result = element;
				break;
			}
		}
		return result;
	}

	// -------------------------------------------------------------------------
	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param by
	 * @return
	 * @throws IOException
	 */
	public boolean isElementPresent(final WebDriver driver,
			String objectLocater)  {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (findElements(driver, objectLocater).size() != 0) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;

		} else {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			return false;
		}
	}

	// -------------------------------------------------------------------------
	/**
	 * Compact way to verify if an element is on the page. we can pass WebElemnt
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */

	public static boolean isElementPresent(final WebDriver driver,
			List<WebElement> objectLocater)  {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (objectLocater.size() != 0) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;

		} else {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}

	// -------------------------------------------------------------------------
	/**
	 * Downloads a file from the defined url, and saves it into the
	 * OutputDatafolder, using the filename defined
	 * 
	 * @param href
	 * @param fileName
	 */
	public static void downloadFile(String href, String fileName)
			 {

		URL url = null;
		URLConnection con = null;
		int i;

		try {
			url = new URL(href);

			con = url.openConnection();
			File file = new File(".//OutputData//" + fileName);
			BufferedInputStream bis = new BufferedInputStream(con.getInputStream());

			BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file));
			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
			bos.flush();
			bis.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------
	/**
	 * To click on the element
	 * 
	 * @param driver
	 * @param objectLocater
	 * @throws IOException
	 */
	public void click(final WebDriver driver, String objectLocater){
		explicitWait(driver, 60, objectLocater);
		findElement(driver, objectLocater).click();
	}
	


	// -------------------------------------------------------------------------
	/**
	 * to click on the element
	 * 
	 * @param driver
	 * @param we
	 */
	public static void click(WebDriver driver, WebElement we) {
		we.click();
	}

	// -------------------------------------------------------------------------
	/**
	 * Click on element
	 * 
	 * @param driver
	 * @param objectLocater
	 * @param value
	 * @throws IOException
	 */

	public void sendKeys(final WebDriver driver, String objectLocater,String value) {
		findElement(driver, objectLocater).sendKeys(value);
	}
	
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @param value
	 */
	public void clearAndSendKeys(final WebDriver driver, String objectLocater,String value) {
		
		findElement(driver, objectLocater).clear();
		findElement(driver, objectLocater).sendKeys(value);
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @throws IOException
	 */
	public void getAllLink(final WebDriver driver, String objectLocater)
			 {
		List<WebElement> list = findElements(driver, objectLocater);
		Iterator<WebElement> itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println("links list--->" + itr.next());
		}

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @throws IOException
	 */
	public void clear(WebDriver driver, String objectLocater)
	{
		findElement(driver, objectLocater).clear();
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public  WebElement findElement(final WebDriver driver,
			String objectLocater) {

		String objecttypeandvalues = conf.getValue(objectLocater);

		String[] splits = objecttypeandvalues.split("~");

		// String[] splits = objectLocater.split(":");
		String objecttype = splits[0];
		String objectvalue = splits[1];
	
		if (objecttype.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(objectvalue));
		} else if (objecttype.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(objectvalue));

		} else if (objecttype.equalsIgnoreCase("name")) {
			return driver.findElement(By.name(objectvalue));
		} else if (objecttype.equalsIgnoreCase("class")) {
			return driver.findElement(By.className(objectvalue));
		} else if (objecttype.equalsIgnoreCase("tagname")) {
			return driver.findElement(By.tagName(objectvalue));
		} else if (objecttype.equalsIgnoreCase("link")) {
			return driver.findElement(By.linkText(objectvalue));
		} else if (objecttype.equalsIgnoreCase("css")) {
			return driver.findElement(By.cssSelector(objectvalue));
		}
		return null;

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public  List<WebElement> findElements(final WebDriver driver,
			String objectLocater) {
		

		String objecttypeandvalues = conf.getValue(objectLocater);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];
		System.out.println("obj type: " + objecttype);
		String objectvalue = splits[1];
		System.out.println("obj val: " + objectvalue);

		if (objecttype.equalsIgnoreCase("id")) {

			return driver.findElements(By.id(objectvalue));

		} else if (objecttype.equalsIgnoreCase("xpath")) {
			return driver.findElements(By.xpath(objectvalue));

		} else if (objecttype.equalsIgnoreCase("name")) {
			return driver.findElements(By.name(objectvalue));
		} else if (objecttype.equalsIgnoreCase("class")) {
			return driver.findElements(By.className(objectvalue));
		} else if (objecttype.equalsIgnoreCase("tagname")) {
			return driver.findElements(By.tagName(objectvalue));
		} else if (objecttype.equalsIgnoreCase("css")) {
			return driver.findElements(By.cssSelector(objectvalue));
		}
		return null;

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @return
	 */
	public static String getTitle(WebDriver driver) {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  String getText(WebDriver driver, String objectLocater)
			 {
		// TODO Auto-generated method stub

		return findElement(driver, objectLocater).getText();

	}
	
	public  String getText(WebDriver driver,WebElement wb){
		return wb.getText();
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  String getValue(WebDriver driver, String objectLocater)
			 {
		return findElement(driver, objectLocater).getAttribute("value");
	}

	// -------------------------------------------------------------------------
	/**
	 * waiting for elements explicitly
	 * 
	 * @param driver
	 * @param timeOutInSeconds
	 * @param objectLocater
	 * @throws IOException
	 */
	public void explicitWait(WebDriver driver, int timeOutInSeconds,
			String objectLocater) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		String objecttypeandvalues = conf.getValue(objectLocater);

		String[] splits = objecttypeandvalues.split("~");
		// String[] splits = objectLocater.split(":");
		String objecttype = splits[0];
		System.out.println("obj type: " + objecttype);
		String objectvalue = splits[1];
		System.out.println("obj val: " + objectvalue);

		if (objecttype.equalsIgnoreCase("id")) {

			wait.until(ExpectedConditions.elementToBeClickable(By
					.id(objectvalue)));

		} else if (objecttype.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(objectvalue)));

		} else if (objecttype.equalsIgnoreCase("name")) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.name(objectvalue)));
		} else if (objecttype.equalsIgnoreCase("class")) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.className(objectvalue)));
		} else if (objecttype.equalsIgnoreCase("tagname")) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.tagName(objectvalue)));
		} else if (objecttype.equalsIgnoreCase("css")) {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.cssSelector(objectvalue)));
		}

	}
	
	/**
	 * waiting for elements explicitly
	 * 
	 * @param driver
	 * @param timeOutInSeconds
	 * @param objectLocater
	 * @throws IOException
	 */
	public  void explicitWaitElementToBeVisible(WebDriver driver, int timeOutInSeconds,String objectLocater)  {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(findElement(driver, objectLocater)));
	}


	// -------------------------------------------------------------------------
	/**
	 * removing space between strings
	 * 
	 * @param str
	 * @return
	 */
	public static String removeBetweenSpaceFromString(String str) {

		String st = str.replaceAll("\\s+", "");
		return st;
	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param groupOfObjectLocators
	 * @return
	 * @throws IOException
	 */
	public  int verifyingTextFieldsGUI(final WebDriver driver,
			String[] groupOfObjectLocators)  {
		int i = 0;
		int value = 0;

		for (; i < groupOfObjectLocators.length; i++) {

			value = displayedElements(driver, groupOfObjectLocators[i]) + value;

			// value++;

		}

		System.out.println("Count Of TextFields From CommonMethod Class is : "
				+ value);

		// return displayedElements(driver, groupOfObjectLocators[i]);

		return value;

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  int displayedElements(final WebDriver driver,
			String objectLocater)  {
		int count = 0;

		WebElement element = findElement(driver, objectLocater);

		if (element.isDisplayed()) {

			count++;

		}

		System.out
				.println("Count for displayed Elements In CommonMethod Class is : "
						+ count);

		return count;

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  int listOfItems(final WebDriver driver, String objectLocater)
			 {
		int count = 0;

		List<WebElement> listOfItems = findElements(driver, objectLocater);

		for (int i = 0; i < listOfItems.size(); i++) {

			// System.out.println(dropdown_items.get(i).getText());
			if (listOfItems.get(i).isDisplayed()) {
				count++;

			}
		}

		System.out.println("Count from ListOfItems In CommonMethod Class is : "
				+ count);

		return count;

	}

	// -------------------------------------------------------------------------
	/**
	 * 
	 * @param driver
	 * @param objectLocater
	 * @return
	 * @throws IOException
	 */
	public  String[] gettingTextFromListItems(final WebDriver driver,String objectLocater)  {

		List<WebElement> elements = findElements(driver, objectLocater);

		String[] listOfItems = new String[elements.size()];

		int i = 0;

		for (WebElement e : elements) {
			listOfItems[i] = e.getText();

			i++;
		}

		System.out.println("Size of the  String Array From Common Method is : "+ listOfItems.length);

		return listOfItems;
	}

	
	// -------------------------------------------------------------------------
	/**
	 * This block is used to switching to a new frame
	 *  
	 * @param driver
	 * @param frame
	 */
	public static void switchToFrame(WebDriver driver, WebElement frame){
		driver.switchTo().frame(frame);
	}
	// -------------------------------------------------------------------------
	/**
	 * This block is used to switching back to the old frame
	 *  
	 * @param driver
	 * @param frame
	 */
	public static void switchBackToDefault(WebDriver driver){
		driver.switchTo().defaultContent();
	}
	
	public void clickByJavaScript(final WebDriver driver,String objectLocater){
		explicitWait(driver, 45, objectLocater);
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",
                        findElement(driver,objectLocater));
 }
	
	public String getCurrentWindowHandle(WebDriver driver) {
		try {
			return driver.getWindowHandle();
		} catch (Exception e) {
			throw new RuntimeException(
					"Exception while procesing getCurrentWindowHandle: ", e);
		}
	}
	
	// To verify if the test case is executable or not
    public void isTestCaseExecutable(String methodName, Xls_Reader xlsString, Hashtable<String, String> data){
      //  APPLICATION_LOGS.info(">>>Starting execution of:'" + methodName + "'<<<");
        // Skips the test case if runmode is set to N
        if (!TestUtil.isTestCaseExecutable(methodName, xlsString)) {
           throw new SkipException("Skipping the test '" + methodName
                    + "' as testcase Runmode is set to: NO");
        }
        // Skips the testcase based on the runmode in Test Data sheet
        if (!data.get("RunMode").equals("Y")) {
        	 throw new SkipException("Skipping the execution of '" + methodName
                    + "' as Runmode of test data is set to: NO");
        }
    }
	
}
