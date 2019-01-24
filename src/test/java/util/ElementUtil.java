package util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.cucumber.listener.Reporter;

import cucumber.api.PendingException;
import cucumber.api.Scenario;

public class ElementUtil 
{
	private static Scenario myScenario;
	
	static Actions action = new Actions(static_data.driver);  
	
	public static WebElement focusOnElement(String identifier,String value)
	{
		WebElement ele = null;

			if(identifier.equalsIgnoreCase("id"))
			{
				ele = static_data.driver.findElement(By.id(value));
			}
			
			else if(identifier.equalsIgnoreCase("name"))
			{
				ele = static_data.driver.findElement(By.name(value));
			}
			
			else if(identifier.equalsIgnoreCase("className"))
			{
				ele = static_data.driver.findElement(By.className(value));
			}
			
			else if(identifier.equalsIgnoreCase("xpath"))
			{
				ele = static_data.driver.findElement(By.xpath(value));
			}
			
			else if(identifier.equalsIgnoreCase("linkText"))
			{
				ele = static_data.driver.findElement(By.linkText(value));
			}
			
			HighlightMyElement(ele);	
			
		return ele;	
		
	}
	
	
	public static List<WebElement> focusOnElements(String identifier,String value)
	{
		List<WebElement> ele = null;

			if(identifier.equalsIgnoreCase("id"))
			{
				ele = static_data.driver.findElements(By.id(value));
			}
			
			else if(identifier.equalsIgnoreCase("name"))
			{
				ele = static_data.driver.findElements(By.name(value));
			}
			
			else if(identifier.equalsIgnoreCase("className"))
			{
				ele = static_data.driver.findElements(By.className(value));
			}
			
			else if(identifier.equalsIgnoreCase("xpath"))
			{
				ele = static_data.driver.findElements(By.xpath(value));
			}
			
			else if(identifier.equalsIgnoreCase("linkText"))
			{
				ele = static_data.driver.findElements(By.linkText(value));
			}
				
			
		return ele;	
		
	}
	
	
	public static void wait_presenceofElementlocated(String identifier,String value)
	{

			if(identifier.equalsIgnoreCase("id"))
			{
				static_data.wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
			}
			
			else if(identifier.equalsIgnoreCase("name"))
			{
				static_data.wait.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
			}
			
			else if(identifier.equalsIgnoreCase("className"))
			{
				static_data.wait.until(ExpectedConditions.presenceOfElementLocated(By.className(value)));
			}
			
			else if(identifier.equalsIgnoreCase("xpath"))
			{
				static_data.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
			}
			
			else if(identifier.equalsIgnoreCase("linkText"))
			{
				static_data.wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));
			}
		
	}
	
	
	public static void selectdropdown(String selectby,String identifiertype,String identifiervalue,String value_to_select)
	{
		Select dropdown = null;

			if(selectby.equalsIgnoreCase("selectByVisibleText"))
			{
				dropdown= new Select(focusOnElement(identifiertype, identifiervalue));
				dropdown.selectByVisibleText(value_to_select);
			}
			
			else if(selectby.equalsIgnoreCase("selectByValue"))
			{
				dropdown= new Select(ElementUtil.focusOnElement(identifiertype, identifiervalue));
				dropdown.selectByValue(value_to_select);
			}	
			
			else if(selectby.equalsIgnoreCase("selectByIndex"))
			{
				dropdown= new Select(ElementUtil.focusOnElement(identifiertype, identifiervalue));
				dropdown.selectByIndex(Integer.parseInt(value_to_select));
			}	
			
		
	}
	
	
	public static boolean is_element_present(String identifier,String value)
	{
		try
		{
			if(identifier.equalsIgnoreCase("id"))
			{
				static_data.driver.findElement(By.id(value));
			}	
			
			else if(identifier.equalsIgnoreCase("name"))
			{
				static_data.driver.findElement(By.name(value));
			}
			
			else if(identifier.equalsIgnoreCase("className"))
			{
				static_data.driver.findElement(By.className(value));
			}
			
			else if(identifier.equalsIgnoreCase("xpath"))
			{
				static_data.driver.findElement(By.xpath(value));
			}
			
			else if(identifier.equalsIgnoreCase("linkText"))
			{
				static_data.driver.findElement(By.linkText(value));
			}

		}
		catch(Exception e)
		{
			return false;
		}
				
	    return true;
	}
	
		
	public static void mouseHoverAnElement(String identifiertype,String identifiervalue)
	{     
        action.moveToElement(focusOnElement(identifiertype, identifiervalue)).build().perform();
	}
	
	
	public static String getPopupTitle()
	{
		String popupTitle = "";
		String mainWindow = static_data.driver.getWindowHandle();
		
		if(static_data.driver.getWindowHandles().size()>1)		
		{
			Set<String> allWindowHandles = static_data.driver.getWindowHandles();
			for (String currentWindowHandle : allWindowHandles) 
			{
				if (!currentWindowHandle.equals(mainWindow)) 
				{
					popupTitle = static_data.driver.switchTo().window(currentWindowHandle).getTitle();
					break;
				}
				
			}
		}
		static_data.driver.switchTo().window(mainWindow);
		
		return popupTitle;
				
	}
	
	public static void closePopup()
	{
		// To get the main window handle
		String mainWindow = static_data.driver.getWindowHandle();
		
		Set<String> allWindowHandles = static_data.driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) 
		{
			if (!currentWindowHandle.equals(mainWindow)) 
			{
				static_data.driver.switchTo().window(currentWindowHandle);
				static_data.driver.close();
			}
		}
		
		static_data.driver.switchTo().window(mainWindow);
	}
		
	
	public static void HighlightMyElement(WebElement element)
	{
		for(int i=0;i<10;i++)
		{
			JavascriptExecutor jse = (JavascriptExecutor)static_data.driver;
			jse.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "color: orange; border: 4px solid orange;");
			jse.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "color: pink; border: 4px solid pink;");
			jse.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "color: yellow; border: 4px solid yellow;");
			jse.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "");
		}
	}
	
	
	
	
	/*************************Log Writing for Cucumber BDD Reporting**************************/
	
/*	public static void writeMessagetoLog(String message)
	{
		try
		{
			Field f = static_data.currentRunningScenario.getClass().getDeclaredField("reporter");
			f.setAccessible(true);
			
			static_data.currentRunningScenario.write(message);
			static_data.currentRunningScenario.write("\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	
	
	/*************************Screenshot for Cucumber BDD Reporting**************************/
	/*public static void takeSnapShot() throws Exception
	{
		long millisecond = System.currentTimeMillis();		
		String ScreenshotDirectory = static_data.BaseReportingDirectory+"/"+static_data.environment+"/"+static_data.today_date+"/"+"screenshot";
		String ScreenshotPath = ScreenshotDirectory+"/"+"portal_"+millisecond+".png";
		String ScreenshotLogPath = "./screenshot/portal_"+millisecond+".png";
		
		//String ScreenshotPath = System.getProperty("user.dir")+"\\"+"Reports"+"\\"+static_data.environment+"\\"+static_data.today_date+"\\screenshot"+"\\portal_"+System.currentTimeMillis()+".png";
		
		if(static_data.capturescreenshot.equals("Yes"))
		{		
			//File file = new File(static_data.BaseReportingDirectory+"/"+static_data.environment+"/"+static_data.today_date+"/"+"screenshot");
			File file = new File(ScreenshotDirectory);
			boolean success = false;
			if (!file.exists())
			{
				success = file.mkdirs();
			}
			if (success)
			  System.out.println("Directory successfully created");
		    else
		      System.out.println("Directory already exist");
			
			
	        TakesScreenshot scrShot =((TakesScreenshot)static_data.driver);
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        File DestFile=new File(ScreenshotPath);
	        FileUtils.copyFile(SrcFile, DestFile);
	        
	        ElementUtil.writeMessagetoLog("<a href=\""+ScreenshotLogPath+"\""+" target="+"\""+"_blank"+"\""+">"+"Screenshot"+"</a>");
		}
        
    }*/
	
	
	
	
	/*************************Screenshot for Extent Report**************************/
	
	/*public static void takeSnapShot()
	{
		String ScreenshotLogPath = "";
		
		try
		{
			long millisecond = System.currentTimeMillis();		
			String ScreenshotDirectory = static_data.BaseReportingDirectory+"/"+static_data.environment+"/"+static_data.today_date+"/"+"screenshot";
			String ScreenshotPath = ScreenshotDirectory+"/"+"portal_"+millisecond+".png";
			ScreenshotLogPath = "./screenshot/portal_"+millisecond+".png";
			
			//String ScreenshotPath = System.getProperty("user.dir")+"\\"+"Reports"+"\\"+static_data.environment+"\\"+static_data.today_date+"\\screenshot"+"\\portal_"+System.currentTimeMillis()+".png";
			
			if(static_data.capturescreenshot.equals("Yes"))
			{		
				//File file = new File(static_data.BaseReportingDirectory+"/"+static_data.environment+"/"+static_data.today_date+"/"+"screenshot");
				File file = new File(ScreenshotDirectory);
				boolean success = false;
				if (!file.exists())
				{
					success = file.mkdirs();
				}
				if (success)
				  System.out.println("Directory successfully created");
			    else
			      System.out.println("Directory already exist");
				
				
		        TakesScreenshot scrShot =((TakesScreenshot)static_data.driver);
		        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		        File DestFile=new File(ScreenshotPath);
		        FileUtils.copyFile(SrcFile, DestFile);
		        
		        //Reporter.addScreenCaptureFromPath(ScreenshotLogPath);
		        
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
        
    }*/
	
	
	
	/****************************Screenshot and log writing for Cluecumber Report Plugin*********************************/
	
	public static void writeMessagetoLog(String message)
	{
		try
		{
			Field f = static_data.currentRunningScenario.getClass().getDeclaredField("reporter");
			f.setAccessible(true);
			
			static_data.currentRunningScenario.write(message);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static void takeSnapShot()
	{
		long millisecond = System.currentTimeMillis();	
		
		String ScreenshotDirectory = "./Reports/test-report/screenshot";
		String ScreenshotPath = ScreenshotDirectory+"/"+"portal_"+millisecond+".png";
		String ScreenshotLogPath = "./screenshot/portal_"+millisecond+".png";
		
		//String ScreenshotPath = System.getProperty("user.dir")+"\\"+"Reports"+"\\"+static_data.environment+"\\"+static_data.today_date+"\\screenshot"+"\\portal_"+System.currentTimeMillis()+".png";
		
		
			//File file = new File(static_data.BaseReportingDirectory+"/"+static_data.environment+"/"+static_data.today_date+"/"+"screenshot");
			File file = new File(ScreenshotDirectory);
			boolean success = false;
			if (!file.exists())
			{
				success = file.mkdirs();
			}
			if (success)
			  System.out.println("Directory successfully created");
		    else
		      System.out.println("Directory already exist");
			
			
	        TakesScreenshot scrShot =((TakesScreenshot)static_data.driver);
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        File DestFile=new File(ScreenshotPath);
	        try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        writeMessagetoLog("<a href=\""+ScreenshotLogPath+"\""+" target="+"\""+"_blank"+"\""+">"+"SCREENSHOT"+"</a>");
	        		
        
    }
		
	
	
	public static void hibernate(Thread t)
	{		
		try 
		{
			Thread.sleep(static_data.short_sleep_time);
		} 
		catch (InterruptedException e) 
		{
			System.out.println("Error encountered while executing Page Load Time");
		}
	}
	
	
	public static void hibernate(WebDriver driver)
	{
		try
		{
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>()
			{
				public Boolean apply(WebDriver driver)
				{
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			
			static_data.wait.until(pageLoadCondition);
		}
		catch(Exception e)
		{
			hibernate(Thread.currentThread());
			hibernate(Thread.currentThread());
		}

	}
	
	
	public static boolean hibernate(WebDriver driver,final String title)
	{
		try
		{
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>()
			{
				public Boolean apply(WebDriver driver)
				{
					return driver.getTitle().equals(title);
				}
			};
			
			static_data.wait.until(pageLoadCondition);
			
			return true;
		}
		catch(Exception e)
		{
			hibernate(Thread.currentThread());
			return false;
		}

	}
	
	
	public static boolean hibernate(final String identifier,final String value)
	{
		try
		{
			ExpectedCondition<WebElement> pageLoadCondition = new ExpectedCondition<WebElement>()
			{
				public WebElement apply(WebDriver driver)
				{					
					return ElementUtil.focusOnElement(identifier, value);
				}
			};
			
			static_data.wait.until(pageLoadCondition);
			
			return true;
						
		}
		catch(Exception e)
		{
			hibernate(Thread.currentThread());
			
			return false;
			
		}

	}
	
	
	public static void hibernate(String identifier,String value,String func)
	{
		try
		{
			switch(func)
			{
				case "clickable" : 
						static_data.wait.until(ExpectedConditions.elementToBeClickable(ElementUtil.focusOnElement(identifier, value)));
				break;
				
				case "presenceOfElement" : 
						static_data.wait.until(ExpectedConditions.presenceOfElementLocated((By) ElementUtil.focusOnElement(identifier, value)));
				break;
				
				case "visibilityOfElement" : 
						static_data.wait.until(ExpectedConditions.visibilityOf(ElementUtil.focusOnElement(identifier, value)));
				break;
					
			}
			
						
		}
		catch(Exception e)
		{
			hibernate(Thread.currentThread());
			
		}

	}
	
	
}
