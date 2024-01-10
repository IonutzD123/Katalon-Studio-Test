package client
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Thor {

	//Parameters
	String authentication_username = findTestData('Client/Authentication_Credentials').getValue('Authentication_Username',  1)
	String authentication_password = findTestData('Client/Authentication_Credentials').getValue('Authentication_Password',  1)
	String login_username = findTestData('Client/Login_Credentials').getValue('Login_Username',  1)
	String login_password = findTestData('Client/Login_Credentials').getValue('Login_Password',  1)

	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	final java.util.Random rand = new java.util.Random();
	final Set<String> identifiers = new HashSet<String>();


	//Opens web page window
	@Keyword
	def openUrl() {
		WebUI.authenticate(GlobalVariable.URL, authentication_username, authentication_password, 0, FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(1)
	}

	//Logs into Application
	@Keyword
	def loginToApp() {
		WebUI.click(findTestObject('Object Repository/sign_In_Button'), FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/sign_In_With_Mail_Button'), FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/sign_In_With_Mail_Email_Address_Field'), login_username, FailureHandling.STOP_ON_FAILURE)
		WebUI.sendKeys(findTestObject('Object Repository/sign_In_With_Mail_Password_Field'), login_password, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Object Repository/continue_Button'), FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(1)
	}

	//Verifies the user name
	@Keyword
	def verifyUserName(String expectedResult) {
		String actualResult = WebUI.getText(findTestObject('Object Repository/user_Name'), FailureHandling.STOP_ON_FAILURE)

		if(actualResult == expectedResult) {
			assert true
		} else {
			assert false
		}
	}

	//Logs out from Application
	@Keyword
	def logoutFromApp() {
		WebUI.click(findTestObject('Object Repository/user_Name'), FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/logout_Button'), FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(1)
	}

	@Keyword
	def createRandomStartupName() {
		StringBuilder builder = new StringBuilder();
		while(builder.toString().length() == 0) {
			int length = rand.nextInt(5)+5;
			for(int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if(identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	@Keyword
	def addStartup(String startupColumnName) {
		WebUI.click(findTestObject('Object Repository/add_Your_Menu_Button'), FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Object Repository/startup_Button'), FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForElementVisible(findTestObject('Object Repository/add_Startup_Name_Window_Title'), 5)

		String startupName = findTestData('Data Files/Client/Startups').getValue(startupColumnName, 1)
		println(startupName)

		WebUI.sendKeys(findTestObject('Object Repository/organization_Name_Field'), startupName, FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForElementVisible(findTestObject('Object Repository/create_Organisation_Button'), 10)
		WebUI.click(findTestObject('Object Repository/create_Organisation_Button'), FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForElementVisible(findTestObject('Object Repository/invite_People_Cancel_Button'), 10)
		WebUI.click(findTestObject('Object Repository/invite_People_Cancel_Button'), FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForElementVisible(findTestObject('Object Repository/cover_Title'), 10)

		String actualStartupName = WebUI.getText(findTestObject('Object Repository/cover_Title'), FailureHandling.STOP_ON_FAILURE)

		WebUI.verifyEqual(actualStartupName, startupName)
		WebUI.click(findTestObject('Object Repository/add_A_Tagline_Button'), FailureHandling.STOP_ON_FAILURE)

		String taglineName = findTestData('Data Files/Client/Startups').getValue(startupColumnName, 2)

		WebUI.waitForElementVisible(findTestObject('Object Repository/add_Tagline_Field'), 10)
		WebUI.sendKeys(findTestObject('Object Repository/add_Tagline_Field'), taglineName, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Object Repository/close_Add_Tagline_Window'), FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(1)
		WebUI.refresh()
		WebUI.waitForElementVisible(findTestObject('Object Repository/cover_Title'), 10)

		String actualTaglineName = WebUI.getText(findTestObject('Object Repository/add_A_Tagline_Button'), FailureHandling.STOP_ON_FAILURE)

		WebUI.verifyEqual(actualTaglineName, taglineName)
	}

}


