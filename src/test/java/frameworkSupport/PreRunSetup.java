package frameworkSupport;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class PreRunSetup 
{
	public static void main(String[] args) 
	{
		deleteReportingDirectory();

	}
	
	public static void deleteReportingDirectory()
	{	
		
		String SRC_FOLDER = "./Reports/test-report";
		
		File directory = new File(SRC_FOLDER);
		 
    	//make sure directory exists
    	if(!directory.exists())
    	{
 
           System.out.println("Directory does not exist.");
           System.exit(0);
 
        }
    	else
    	{
 
           try
           {        	   
        	   FileUtils.cleanDirectory(directory);     
           }
           catch(IOException e)
           {
               e.printStackTrace();
               System.exit(0);
           }
        }
 
    	System.out.println("Existing Reporting Directory at "+directory.getAbsolutePath()+" cleaned successfully.");
		
	}
	

}