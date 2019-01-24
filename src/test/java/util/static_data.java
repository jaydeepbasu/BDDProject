package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;


public class static_data
{
	public static String url = "";
	public static WebDriver driver = null;
	public static String environment = "";
	public static WebDriverWait wait;
	
	public static String pom_sheet = "Page Object Model";
	public static String pre_config_sheet = "Pre-Configurations";
	public static String url_sheet = "URL";
	public static String executable_tags = "Executable Tags";
	
	public static int identifier_type_flag = 1;
	public static int identifier_value_flag = 2;
	
	public static int long_sleep_time = 0;
	public static int short_sleep_time = 0;
	
	public static String today_date = "";
	
	public static String currentRunningScenarioName = "";
	public static Scenario currentRunningScenario = null;
	public static String currentRunningFeatureName = "";
	
	//public static String user_dir = System.getProperty("user.dir");
	//public static String file_path = user_dir+"\\Test Data\\Test_Data.xlsx";
	public static String PreConfigureFilePath = "./PreConfigurations/PreConfigurations.xlsx";
	public static String TestDataFilePath = "./Test Data/";
	
	public static String capturescreenshot="";	
	public static String browser="";
	
	public static String BaseReportingDirectory = "";
	//public static String FinalReportingDirectory = "";

	public static boolean execution_start_counter=false;
	//public static boolean execution_stop_counter=false;
	//public static int counter=1;
	
	public static String tagname = "";
}
