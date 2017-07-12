package com.napa.online.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.napa.online.util.XLS_Reader;

public class DataUtil {

	public static Object[][] getData(XLS_Reader xls, String testCaseName){
		String sheetName=RunConfig.TESTDATA_SHEET;
		// reads data for only testCaseName
		
		int testStartRowNum=1;
		//System.out.println(xls.getCellData(sheetName, 0, testStartRowNum)); 
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)){
			testStartRowNum++;
		}
		System.out.println("Test starts from row - "+ testStartRowNum);
		
		
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		RunConfig.DATA_START_ROW_NUM= dataStartRowNum;
		System.out.println("Test Data starts from row - "+ dataStartRowNum);
		
		// calculate rows of data
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
		//System.out.println("Total rows are  - "+rows );
		
		//calculate total cols
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		//System.out.println("Total cols are  - "+cols );
		Object[][] data = new Object[rows][1];
		//read the data
		int rNum;
		int dataRow=0;
		Hashtable<String,String> table=null;
		for(rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key=xls.getCellData(sheetName,cNum,colStartRowNum);
				String value= xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
				// 0,0 0,1 0,2
				//1,0 1,1
			}
			data[dataRow][0] =table;
			dataRow++;
			
		}
		RunConfig.TC_ROW_NUM = rNum;
		System.out.println("RunConfig.TC_ROW_NUM: "+RunConfig.TC_ROW_NUM);
		return data;
	}
	
	public static boolean isTestExecutable(XLS_Reader xls, String testCaseName){
		int rows = xls.getRowCount(RunConfig.TESTCASES_SHEET);
		int rNum;
		int tcrNum;
		for(rNum=2;rNum<=rows;rNum++){
			String tcid = xls.getCellData(RunConfig.TESTCASES_SHEET, "TestCaseID", rNum);
			if(tcid.equalsIgnoreCase(testCaseName)){
				String runmode = xls.getCellData(RunConfig.TESTCASES_SHEET, "Runmode", rNum);
				if(runmode.equalsIgnoreCase("Yes")){
					return true;
					
				}
				else
					return false;

			}
		}
		//RunConfig.TC_ROW_NUM = rNum;
		
		return false;
	}
	
	public static boolean setResultsData(XLS_Reader xls, String sheetName, int rowNum, int colNum, String value){
		/*System.out.println("sheetName - "+ sheetName);
		System.out.println("colName - "+ colNum);
		System.out.println("rowNum - "+ rowNum);
		System.out.println("data - "+ data);*/		
		 
			boolean updated = xls.setCellData(sheetName, rowNum, colNum, value);
			if(updated)				
					return true;
				else
					return false;
			
		}
		
	
	
	public static String getCommonData(XLS_Reader xls, String cDataKey){
		
		String sheetName= RunConfig.COMMONDATA_SHEET;
		
		int rows = xls.getRowCount(sheetName);
		for(int rNum=2;rNum<=rows;rNum++){
			String key = xls.getCellData(sheetName, "CDataKey", rNum);
			if(key.equalsIgnoreCase(cDataKey)){
				String cDataValue = xls.getCellData(sheetName, "CDataValue", rNum);
				return cDataValue;
			}
		}		
		return "";
	}
	
	/*public static void setResultsData(XLWriter xlw, String sheetName,
			String content, int row, int col){
		xlw.updateXLData(sheetName, content, row, col);
		
	}*/
	
	
}
