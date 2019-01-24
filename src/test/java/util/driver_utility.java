package util;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class driver_utility 
{
	//static String user_dir = System.getProperty("user.dir");
	
	public static void start_sessionInstance()
	{
		try
		{
			//Pre_Run.test();
			String BrowserUnderTest = excel_utility.getcellvaluebyName("Browser Under Test",static_data.pre_config_sheet);
			
			if(static_data.browser.equalsIgnoreCase("Chrome") && static_data.driver==null)
			{
				System.out.println("*******************Opening Chrome Browser for execution*******************");
				
				String ChromeDriverPath = excel_utility.getcellvaluebyName("Chrome Driver Path",static_data.pre_config_sheet);
				System.setProperty("webdriver.chrome.driver",ChromeDriverPath);
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("disable-popup-blocking");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
				capabilities.setCapability(ChromeOptions.CAPABILITY,options);
				
				static_data.driver = new ChromeDriver(capabilities);
				//static_data.driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities);
				
				static_data.driver.manage().window().maximize();
				
				static_data.wait = new WebDriverWait(static_data.driver,static_data.long_sleep_time);
			}
			
			else if(static_data.browser.equalsIgnoreCase("Firefox") && static_data.driver==null)
			{
				System.out.println("*******************Opening Firefox Browser for execution*******************");
				
				String FirefoxDriverPath = excel_utility.getcellvaluebyName("Firefox Driver Path",static_data.pre_config_sheet);
				System.setProperty("webdriver.firefox.marionette",FirefoxDriverPath);
				static_data.driver = new FirefoxDriver();
				static_data.driver.manage().window().maximize();
				
				static_data.wait = new WebDriverWait(static_data.driver,static_data.long_sleep_time);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
				
	}

	
	public static void close_sessionInstance()
	{
		System.out.println("*******************Closing the Browser*******************");
		
		static_data.driver.close();
		static_data.driver.quit();
		static_data.driver=null;
		static_data.wait=null;
	}

}

