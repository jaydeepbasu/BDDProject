package StepDef;

import java.awt.List;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.BeforeClass;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import util.driver_utility;
import util.excel_utility;
import util.static_data;

public class General 
{
	
/*	@BeforeTestRun
	public static void beforeallscenario() throws Exception
	{
		//Pre_Run.test();
		System.out.println("**************HIIIIIIIIIIIIIIIIIIIIIIIIIIIII****************************");
	}*/
	
	//static String[] pre_config_data = new String[100];	
	
	@Before
	public void Retrieving_Feature_Scenario_Name(Scenario scenario)
	{		
		static_data.currentRunningScenarioName = scenario.getName();		
		static_data.currentRunningScenario = scenario;
		
		static_data.currentRunningFeatureName = scenario.getId().split(";")[0].replace("-"," ");
		
		System.out.println("*************Current Running Feature is : "+static_data.currentRunningFeatureName+"*******************");
		System.out.println("*************Current Running Scenario is : "+static_data.currentRunningScenarioName+"*******************");	
		
		Collection<String> tag_names = scenario.getSourceTagNames();
		ArrayList taglist = new ArrayList(tag_names);
		static_data.tagname = taglist.toString();
		
	}
	
	@Before
	public void DataSetup()
	{
		if(!static_data.execution_start_counter)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date date = new Date();
			static_data.today_date = dateFormat.format(date);
			
			static_data.environment = excel_utility.getcellvaluebyName("Environment Under Test",static_data.pre_config_sheet);
			static_data.url = excel_utility.getcellvaluebyName(static_data.environment,static_data.url_sheet);
			static_data.capturescreenshot = excel_utility.getcellvaluebyName("Capture Screenshot",static_data.pre_config_sheet);
			static_data.browser = excel_utility.getcellvaluebyName("Browser Under Test",static_data.pre_config_sheet);
			static_data.BaseReportingDirectory = excel_utility.getcellvaluebyName("Log Path",static_data.pre_config_sheet);
			static_data.short_sleep_time = Integer.parseInt(excel_utility.getcellvaluebyName("Short Page Load Time",static_data.pre_config_sheet));
			static_data.long_sleep_time = Integer.parseInt(excel_utility.getcellvaluebyName("Long Page Load Time",static_data.pre_config_sheet));
			static_data.TestDataFilePath = static_data.TestDataFilePath + static_data.environment + ".xlsx";	
								
			static_data.execution_start_counter=true;
		}
	}
	
	@After
	public void Closing_Driver_Instance()
	{		
		if(static_data.driver!=null)
			driver_utility.close_sessionInstance();										
					
	}
	
	
/*	public void createReportingDirectory()
	{
		System.out.println(static_data.FinalReportingDirectory);
		
		File file = new File(static_data.FinalReportingDirectory);
        if (!file.exists()) 
        {
            if (file.mkdirs()) 
            {
                System.out.println("Reporting Directory is created!");
            } 
            else 
            {
                System.out.println("Failed to create Reporting Directory!");
            }
        }
	}*/
	
}
