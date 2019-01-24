package StepDef;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.cucumber.listener.ExtentProperties;
//import com.cucumber.listener.Reporter;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import util.ElementUtil;
import util.driver_utility;
import util.excel_utility;
import util.static_data;

public class Login_StepDefination 
{	
	WebElement ele = null;
	WebDriverWait wait = null;

	@Given("^The user opens the URL$")
	public void the_user_opens_the_URL()
	{
		try
		{
			System.out.println("Navigating to URL :"+static_data.url+"");			
			driver_utility.start_sessionInstance();
			//if(!ElementUtil.hibernate(static_data.driver,"Login"))
			static_data.driver.navigate().to(static_data.url);
		}
		catch(WebDriverException e)
		{
			System.out.println("Error encountered while trying to open the URL");
			try
			{
				System.out.println("Retrying to open the URL");				
				driver_utility.close_sessionInstance();
				driver_utility.start_sessionInstance();
				static_data.driver.navigate().to(static_data.url);
			}
			catch(Exception e1)
			{
				System.out.println("Error encountered while trying to open the URL");
				System.out.println("Retrying to open the URL");	
				static_data.driver=null;
				static_data.wait=null;
				driver_utility.start_sessionInstance();
				static_data.driver.navigate().to(static_data.url);
			}

		}
		
	}

		
	@When("^The user enters \"([^\"]*)\" in \"([^\"]*)\" page$")
	public void the_user_enters_in_page(String arg1, String arg2)
	{
	    String identifier_type = "";
	    String identifier_value = "";
	    
	    String cred = excel_utility.getTestDataByFieldName(arg1);
	    
		identifier_type = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_type_flag);
		identifier_value = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_value_flag);
	    
	    try
		{					
								
			//ElementUtil.wait_presenceofElementlocated(identifier_type, identifier_value);
			
			ElementUtil.focusOnElement(identifier_type, identifier_value).sendKeys(cred);
	    }
		
		catch(Exception e)
		{			
			ElementUtil.takeSnapShot();
			System.out.println("Exception is "+e);
			the_user_logs_out_of_the_portal();
			Assert.fail("The field : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+static_data.driver.getTitle()+" page.");
			
			/*************For BDD Reporting Format***********************
			throw new PendingException("The field : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+static_data.driver.getTitle()+" page.");
			************************************************************/
		}
	    
	    ElementUtil.takeSnapShot();

	 }
	
	
	@Given("^The user clicks on \"([^\"]*)\" button in \"([^\"]*)\" page.$")
	public void the_user_clicks_on_button(String arg1,String arg2)
	{
		String identifier_type = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_type_flag);
		String identifier_value = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_value_flag);
				
		try
		{
			ElementUtil.focusOnElement(identifier_type, identifier_value).click();
					
			ElementUtil.takeSnapShot();			
		}
		catch(Exception e)
		{
			ElementUtil.takeSnapShot();			
			System.out.println("Exception is "+e);
			the_user_logs_out_of_the_portal();
			Assert.fail("The button : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+arg2+" page.");
			
			//throw new PendingException("The button : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+arg2+" page.");
		}
		
		
	}
	
	@Given("^The user clicks on \"([^\"]*)\" link in \"([^\"]*)\" page.$")
	public void the_user_clicks_on_link_in_page(String arg1,String arg2)
	{		
		String identifier_type = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_type_flag);
		String identifier_value = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_value_flag);
		
		try
		{
			if(identifier_type.equals("") || identifier_value.equals(""))
			{
				ElementUtil.focusOnElement("linkText", arg2).click();
			}
			else
			{
				ElementUtil.hibernate(identifier_type, identifier_value,"clickable");
				ElementUtil.focusOnElement(identifier_type, identifier_value).click();

			}
		}
		catch(Exception e)
		{			
			ElementUtil.takeSnapShot();
			System.out.println("Exception is "+e);
			the_user_logs_out_of_the_portal();
			Assert.fail("The link : "+arg1+" is not present in "+arg2+" page.");
			
			//throw new PendingException("The link : "+arg1+" is not present in "+arg2+" page.");
		}
		
		ElementUtil.takeSnapShot();
	}


	@Then("^The user should get \"([^\"]*)\" message in \"([^\"]*)\" section.$")
	public void The_user_should_get_message_in_section(String arg1,String arg2)
	{
		String identifier_type = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_type_flag);
		String identifier_value = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_value_flag);
		
		String expected_message = "";
		String actual_message = "";

		try
		{			
			expected_message = excel_utility.getTestDataByFieldName(arg1);
			if(expected_message.equals(""))
			{
				expected_message = arg1; 
			}
			
			actual_message = ElementUtil.focusOnElement(identifier_type, identifier_value).getText();
				
			Assert.assertEquals("Message retrieved from "+arg2+" section is "+actual_message+" which is not matching with the expected "+expected_message, expected_message.equalsIgnoreCase(actual_message), true);
		}
		catch(Exception e1)
		{		
			ElementUtil.takeSnapShot();		
			System.out.println("Exception is "+e1);
			the_user_logs_out_of_the_portal();
			Assert.fail("The message : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+static_data.driver.getTitle()+" section.");
			
			//throw new PendingException("The message : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+static_data.driver.getTitle()+" section.");
		}
		
		ElementUtil.takeSnapShot();
		
		
	}
	
	
/*	@Given("^The user closes the browser$")
	public void the_user_closes_the_browser()
	{
		static_data.driver.close();
		static_data.driver.quit();	  		
	}*/
	
	@Given("^The user waits for sometime$")
	public void the_user_waits_for_sometime() throws Throwable
	{
		ElementUtil.hibernate(Thread.currentThread());
	}
	
	
	@Given("^The user checks whether \"([^\"]*)\" field is \"([^\"]*)\" in \"([^\"]*)\" page\\.$")
	public void the_user_checks_whether_field_is_in_page(String arg1,String arg2,String arg3)
	{
		String identifier_type = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_type_flag);
		String identifier_value = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_value_flag);
		
		try
		{
		
			if(arg2.equals("present"))
			{
				Boolean expected = true;
				Boolean result = ElementUtil.is_element_present(identifier_type, identifier_value);
				Assert.assertEquals("The field : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+arg3+" page.", expected, result);
			}
			else if (arg2.equals("not present"))
			{
				Boolean expected = false;
				Boolean result = ElementUtil.is_element_present(identifier_type, identifier_value);
				Assert.assertEquals("The field : "+arg1+" with "+identifier_type+" : "+identifier_value+" is present in "+arg3+" page.", expected, result);
			}
			
			else if (arg2.equals("enabled"))
			{
				Boolean expected = true;
				Boolean result = ElementUtil.focusOnElement(identifier_type, identifier_value).isEnabled();
				Assert.assertEquals("The field : "+arg1+" with "+identifier_type+" : "+identifier_value+" is in disabled status in "+arg3+" page.", expected, result);
			}
			
			else if (arg2.equals("disabled"))
			{
				Boolean expected = false;
				Boolean result = ElementUtil.focusOnElement(identifier_type, identifier_value).isEnabled();
				Assert.assertEquals("The field : "+arg1+" with "+identifier_type+" : "+identifier_value+" is in enabled status in "+arg3+" page.", expected, result);
			}
		}
		catch(Exception e)
		{
			ElementUtil.takeSnapShot();		
			System.out.println("Exception is "+e);
			the_user_logs_out_of_the_portal();
			Assert.fail("The field : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+arg3+" page.");
			
			//throw new PendingException("The field : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+arg3+" page.");
		}
		
		
		ElementUtil.takeSnapShot();
	}
	
	
	@Given("^The user checks whether \"([^\"]*)\" button is \"([^\"]*)\" in \"([^\"]*)\" page\\.$")
	public void the_user_checks_whether_button_is_in_page(String arg1,String arg2,String arg3)
	{
		String identifier_type = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_type_flag);
		String identifier_value = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_value_flag);
		
		try
		{
			if(arg2.equals("present"))
			{
				Boolean expected = true;
				Boolean result = ElementUtil.is_element_present(identifier_type, identifier_value);
				Assert.assertEquals("The button : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+arg3+" page.", expected, result);
			}
			else if (arg2.equals("not present"))
			{
				Boolean expected = false;
				Boolean result = ElementUtil.is_element_present(identifier_type, identifier_value);
				Assert.assertEquals("The button : "+arg1+" with "+identifier_type+" : "+identifier_value+" is present in "+arg3+" page.", expected, result);
			}
			
			else if (arg2.equals("enabled"))
			{
				Boolean expected = true;
				Boolean result = ElementUtil.focusOnElement(identifier_type, identifier_value).isEnabled();
				Assert.assertEquals("The button : "+arg1+" with "+identifier_type+" : "+identifier_value+" is in disabled status in "+arg3+" page.", expected, result);
			}
			
			else if (arg2.equals("disabled"))
			{
				Boolean expected = false;
				Boolean result = ElementUtil.focusOnElement(identifier_type, identifier_value).isEnabled();
				Assert.assertEquals("The button : "+arg1+" with "+identifier_type+" : "+identifier_value+" is in enabled status in "+arg3+" page.", expected, result);
			}
		}
		
		catch(Exception e)
		{
			ElementUtil.takeSnapShot();	
			System.out.println("Exception is "+e);
			the_user_logs_out_of_the_portal();
			Assert.fail("The button : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+arg3+" page.");
			
			//throw new PendingException("The button : "+arg1+" with "+identifier_type+" : "+identifier_value+" is not present in "+arg3+" page.");
		}
		
		ElementUtil.takeSnapShot();
	}
	
	
	@Given("^The user selects value from \"([^\"]*)\" in \"([^\"]*)\" page\\.$")
	public void the_user_selects_value_from_in_page(String arg1,String arg2)
	{
		String identifier_type_arg1 = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_type_flag);
		String identifier_value_arg1 = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_value_flag);
		
		String value_to_select = excel_utility.getTestDataByFieldName(arg1);
		
		try
		{
			List<WebElement> listOptions = ElementUtil.focusOnElements(identifier_type_arg1, identifier_value_arg1);
			for(WebElement option : listOptions)
			{
				if(value_to_select.equals(option.getText()))
				{
					option.click();
					break;
				}
			}
		}
		catch(Exception e)
		{
			ElementUtil.takeSnapShot();	
			System.out.println("Exception is "+e);
			the_user_logs_out_of_the_portal();
			Assert.fail("The field : "+arg1+" with "+identifier_type_arg1+" : "+identifier_value_arg1+" is not present in "+arg2+" page.");
			
			//throw new PendingException("The field : "+arg1+" with "+identifier_type_arg1+" : "+identifier_value_arg1+" is not present in "+arg2+" page.");
		}
		
		ElementUtil.takeSnapShot();
		
	}
	
	
	@Given("^The user selects value from \"([^\"]*)\" dropdown by \"([^\"]*)\" in \"([^\"]*)\" page\\.$")
	public void the_user_selects_value_from_dropdown_in_page(String arg1,String arg2,String arg3) 
	{
		String identifier_type_arg1 = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_type_flag);
		String identifier_value_arg1 = excel_utility.read_pom(arg1,static_data.pom_sheet,static_data.identifier_value_flag);
		
		try
		{			
			Select sel = new Select(ElementUtil.focusOnElement(identifier_type_arg1,identifier_value_arg1));
			
			if(arg2.equals("value"))
			{
				String value_to_select = excel_utility.getTestDataByFieldName(arg1);		
				sel.selectByValue(value_to_select);
			}
			
			else if(arg2.equals("visible text"))
			{
				String value_to_select = excel_utility.getTestDataByFieldName(arg1);
				sel.selectByVisibleText(value_to_select);
			}
			
			else if(arg2.equals("index"))
			{
				int value_to_select = Integer.parseInt(excel_utility.getTestDataByFieldName(arg1));
				sel.selectByIndex(value_to_select);
			}
		}
		
		catch(Exception e)
		{
			ElementUtil.takeSnapShot();		
			System.out.println("Exception is "+e);
			the_user_logs_out_of_the_portal();
			Assert.fail("The field : "+arg1+" with "+identifier_type_arg1+" : "+identifier_value_arg1+" is not present in "+arg2+" page.");
			
			//throw new PendingException("The field : "+arg1+" with "+identifier_type_arg1+" : "+identifier_value_arg1+" is not present in "+arg2+" page.");
		}
		
		ElementUtil.takeSnapShot();
		
	}
	
	
	@Given("^The user has logged in to the portal as \"([^\"]*)\"\\.$")
	public void the_user_has_logged_in_to_the_portal_as(String arg1)
	{
		if(arg1.equals("GuestUser"))
		{
			the_user_opens_the_URL();
			the_user_enters_in_page("Guest Username", "Login");
			the_user_enters_in_page("Guest Password", "Login");
			the_user_clicks_on_button("Login","Login");
			the_user_will_be_redirected_to_page("My Account");
		}

	}
	
	
	@When("^User navigate to \"([^\"]*)\" tab\\.$")
	public void user_navigate_to_tab(String arg1)
	{
		try
		{
			
			ElementUtil.focusOnElement("linkText", arg1).click();
		}
		catch(Exception e)
		{
			ElementUtil.takeSnapShot();		
			System.out.println("Exception is "+e);
			the_user_logs_out_of_the_portal();
			Assert.fail("The tab : "+arg1+" is not present in "+static_data.driver.getTitle()+" page.");
			
			//throw new PendingException("The tab : "+arg1+" is not present in "+static_data.driver.getTitle()+" page.");
		}
	}

	@Then("^Verify the user will be redirected to \"([^\"]*)\" page\\.$")
	public void the_user_will_be_redirected_to_page(String landingPage)
	{		
		ElementUtil.hibernate(static_data.driver,landingPage);
		ElementUtil.takeSnapShot();
		Assert.assertEquals("The user was expected to reach to "+landingPage+" but reached to "+static_data.driver.getTitle(), static_data.driver.getTitle().equals(landingPage), true);
		
	}
	
	
	@Given("^The user logs out of the portal\\.$")
	public void the_user_logs_out_of_the_portal()
	{
		String ProfileName_identifier_type = excel_utility.read_pom("Profile Name",static_data.pom_sheet,static_data.identifier_type_flag);
		String ProfileName_identifier_value = excel_utility.read_pom("Profile Name",static_data.pom_sheet,static_data.identifier_value_flag);
		
		String Logout_identifier_type = excel_utility.read_pom("Logout",static_data.pom_sheet,static_data.identifier_type_flag);
		String Logout_identifier_value = excel_utility.read_pom("Logout",static_data.pom_sheet,static_data.identifier_value_flag);
		
		try
		{
			ElementUtil.hibernate(ProfileName_identifier_type, ProfileName_identifier_value,"clickable");
			ElementUtil.focusOnElement(ProfileName_identifier_type, ProfileName_identifier_value).click();
			
			ElementUtil.hibernate(Logout_identifier_type, Logout_identifier_value,"clickable");
			ElementUtil.focusOnElement(Logout_identifier_type, Logout_identifier_value).click();

		}
		catch (Exception e) 
		{
			ElementUtil.takeSnapShot();
			driver_utility.close_sessionInstance();
			//e.printStackTrace();
		}
		
	}
	
/*	@Given("^The user closes the browser\\.$")
	public void the_user_closes_the_browser()
	{
		driver_utility.close_sessionInstance();
		System.out.println("**************HELLOOOOOOOOOOOOO****************************");
	}*/
	
	
}
