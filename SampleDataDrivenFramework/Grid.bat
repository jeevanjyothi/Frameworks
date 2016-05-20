@echo off

start /d "D:\sfdc-automation\SalesforceGridAutomationSuite\Driver_chrome" java -jar selenium-server-standalone-2.42.2.jar -role hub


start /d "D:\sfdc-automation\SalesforceGridAutomationSuite\Driver_chrome" java -jar selenium-server-standalone-2.42.2.jar -role webdriver -hub  http://localhost:4444/grid/register -port 5556 -browser browserName=firefox,maxInstances=5


start /d "D:\sfdc-automation\SalesforceGridAutomationSuite\Driver_chrome" java -Dwebdriver.ie.driver="D:\sfdc-automation\SalesforceGridAutomationSuite\Driver_chrome\IEDriverServer.exe" -jar selenium-server-standalone-2.42.2.jar -role webdriver -hub  http://localhost:4444/grid/register -port 5557 -browser "browserName=internet explorer,version=9,platform=WINDOWS,maxInstances=5"


start /d "D:\sfdc-automation\SalesforceGridAutomationSuite\Driver_chrome" java -Dwebdriver.chrome.driver="D:\sfdc-automation\SalesforceGridAutomationSuite\Driver_chrome\chromedriver.exe" -jar selenium-server-standalone-2.42.2.jar -role webdriver -hub  http://localhost:4444/grid/register -port 5558 -browser browserName=chrome,maxInstances=5


