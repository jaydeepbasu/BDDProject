package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class excel_utility 
{

	static XSSFWorkbook workbook;
	static Row row;
	static Cell cell;	
			
	//static String user_dir = System.getProperty("user.dir");
	//static String file_path = user_dir+"\\Test Data\\Test_Data.xlsx";
		

	public static String getcellvaluebyName(String arg,String sheetname)
	{		
		System.out.println("*********Extracting cell value of "+arg+"************");
		
		String cred="";
		
		try 
		{
			workbook = new XSSFWorkbook(static_data.PreConfigureFilePath);
	
			XSSFSheet sh = workbook.getSheet(sheetname);
			
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				row=sh.getRow(i);
				cell=row.getCell(0);
				if(cell.toString().equals(arg))
				{
				   cred=row.getCell(1).toString();	
				}
			}
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return cred;
		
	}
		
	
	
	public static List<String> getalltagnames(String sheetname)
	{		
		List<String> tags = new ArrayList<>();
		int commacounter = 0;
		
		try 
		{
			workbook = new XSSFWorkbook(static_data.PreConfigureFilePath);
	
			XSSFSheet sh = workbook.getSheet(sheetname);
			
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				row=sh.getRow(i);
				cell=row.getCell(1);
				if(cell.toString().equals("Y") && !cell.toString().equals(null))
				{
				   if(commacounter==0)
				   {
					   tags.add('"'+"@"+row.getCell(0).toString()+'"');	
				   }
				   else
				   {
					   tags.add(","+'"'+"@"+row.getCell(0).toString()+'"');	
				   }
				   
				   commacounter++;
				}
			}
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return tags;
		
	}
	
		
	
	
	/*public static String getcellvaluebycolumnname(String columnName,String sheetname)
	{		
		String column_data="";
		
		try 
		{
			workbook = new XSSFWorkbook(static_data.TestDataFilePath);
	
			XSSFSheet sh = workbook.getSheet(sheetname);
			
			for(int k=1;k<=sh.getLastRowNum();k++)
			{
				row=sh.getRow(k);
				cell=row.getCell(0);
				if(cell.toString().equalsIgnoreCase(static_data.currentRunningFeatureName))
				{
					for(int i=1;i<=sh.getLastRowNum();i++)
					{
						row=sh.getRow(i);
						cell=row.getCell(1);
						if(cell.toString().equalsIgnoreCase(static_data.currentRunningScenarioName))
						{
							for(int j=0;j<sh.getRow(i).getLastCellNum();j++)
							{
								row=sh.getRow(0);
								cell=row.getCell(j);
								if(cell.toString().equalsIgnoreCase(columnName))
								{
									row=sh.getRow(i);
									column_data=row.getCell(j).toString();
									break;
								}
							}
							break;
						}						
					}
					break;
				}
			}
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
			return column_data;
		}
		
		return column_data;
		
	}*/
	
	
	public static String getTestDataByFieldName(String fieldName)
	{		
		System.out.println("*********Extracting test data value of "+fieldName+"************");
		
		String field_data="";
		
		try 
		{
			workbook = new XSSFWorkbook(static_data.TestDataFilePath);
	
			XSSFSheet sh = workbook.getSheet(static_data.currentRunningFeatureName);
			
			ScenarioName:
			for(int k=1;k<=sh.getLastRowNum();k++)
			{
				row=sh.getRow(k);
				cell=row.getCell(1);
				if(cell.toString().equals(""))
				{
					continue;
				}
				if(cell.toString().equalsIgnoreCase(static_data.currentRunningScenarioName))
				{
					for(int j=k;j<=sh.getLastRowNum();j++)
					{
						row=sh.getRow(j);
						cell=row.getCell(2);
						if(cell.toString().equals(""))
						{
							break;
						}
						if(cell.toString().equalsIgnoreCase(fieldName))
						{
							row=sh.getRow(j);
							field_data=row.getCell(3).toString();
							System.out.println(field_data);
							break ScenarioName;
						}
					 }					 
						 						
				  }					 
					
			   }
			
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
			return field_data;
		}
		
		return field_data;
		
	}
	
	
	
	public static String read_pom(String param,String pomsheet_nm,int flag)
	{		
		System.out.println("*********Extracting pom data value of "+param+"************");
		
		String pom = "";
		
		try 
		{
			workbook = new XSSFWorkbook(static_data.PreConfigureFilePath);
			
			XSSFSheet sh = workbook.getSheet(pomsheet_nm);
			
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				row=sh.getRow(i);
				cell=row.getCell(0);
				if(cell.toString().equals(param))
				{
				   pom=row.getCell(flag).toString();	
				}
			}
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
								
		return pom;
	}
	
	
/*	public static String[] read_pre_configure_data()
	{		
		String[] pre_config_data = new String[100];
		
		try 
		{
			workbook = new XSSFWorkbook(file_path);
			
			XSSFSheet sh = workbook.getSheet("Pre-Configurations");
			
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				row=sh.getRow(i);
				cell=row.getCell(0);
				
				if(cell.toString().equals("Environment to Execute"))
				{
					static_data.environment = row.getCell(1).toString();					
					pre_config_data[0] = read_url(row.getCell(1).toString());				
				}
				
			}
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
								
		return pre_config_data;
		
	}*/
	
	
	/*private static String read_url(String arg)
	{		
		String url = "";
		
		try 
		{
			workbook = new XSSFWorkbook(file_path);
			
			XSSFSheet sh = workbook.getSheet("URL");
			
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				row=sh.getRow(i);
				cell=row.getCell(0);
				
				if(cell.toString().equals(arg))
				{
					url=row.getCell(1).toString();	
				}
			}
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
								
		return url;
		
	}*/
	
	
	
	
	
	public static void writecellvaluebyName(String arg,String sheetname)
	{		
		System.out.println("*********Writing cell value of "+arg+"************");
		
		//String cred="";
		
		try 
		{
			workbook = new XSSFWorkbook(static_data.PreConfigureFilePath);
	
			XSSFSheet sh = workbook.getSheet(sheetname);
			
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				row=sh.getRow(i);
				cell=row.getCell(0);
				if(cell.toString().equals(arg))
				{
				   row.createCell(1).setCellValue("xyz");
				}
			}
			
			FileOutputStream fileOut = new FileOutputStream(static_data.PreConfigureFilePath);
			workbook.write(fileOut);
			fileOut.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//return cred;
		
	}
	
	
}

