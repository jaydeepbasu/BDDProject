package frameworkSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.excel_utility;
import util.static_data;

public class PostRunSetup 
{
	static String FinalReportingDirectory = "";
	
	public static void main(String[] args) 
	{
		prepareSourceFolder();
		copyreportingDirectory();
		String ReportZipPath = ReportstoZip.reporting_zip(FinalReportingDirectory);
		ReportsToEmail.reporting_email(ReportZipPath);
			    
	}
	
	public static void prepareSourceFolder()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		static_data.today_date = dateFormat.format(date);
		
		static_data.environment = excel_utility.getcellvaluebyName("Environment Under Test",static_data.pre_config_sheet);
		static_data.BaseReportingDirectory = excel_utility.getcellvaluebyName("Log Path",static_data.pre_config_sheet);
		
		FinalReportingDirectory = static_data.BaseReportingDirectory+"/"+static_data.environment+"/"+static_data.today_date;
	}
	
	
    public static void copyreportingDirectory()
    {
        File srcFolder = new File("./Reports/test-report");
        File destFolder = new File(FinalReportingDirectory);

        if(!srcFolder.exists())
        {

        	System.out.println(srcFolder + "  Folder does not exists");
               //just exit
            System.exit(0);
        }
        else
        {

               try
               {
                    copyDirectory(srcFolder,destFolder);
               }
               catch(IOException e)
                {
                        e.printStackTrace();
                        System.exit(0);
                }
            }
        
        
        System.out.println("#############################################################");
        System.out.println("####### Reports are backed up at : "+destFolder.getAbsolutePath());
    }

    public static void copyDirectory(File src , File target) throws IOException 
    {
        if (src.isDirectory()) 
        {
            if (!target.exists()) 
            {
                target.mkdirs();
            }

            String[] children = src.list();
            for (int i=0; i<children.length; i++) 
            {
                copyDirectory(new File(src, children[i]),new File(target, children[i]));
            }
        }
        // if Directory exists then only files copy
        else 
        {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(target);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            
            while ((len = in.read(buf)) > 0) 
            {
               out.write(buf, 0, len);
            }
            
            in.close();
            out.close();

            }

    }    

}
