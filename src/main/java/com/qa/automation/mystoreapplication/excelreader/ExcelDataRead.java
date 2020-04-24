package com.qa.automation.mystoreapplication.excelreader;

import java.io.IOException;
import com.atmecs.falcon.automation.util.parser.XlsReader;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;


public class ExcelDataRead {
	private ReportLogService report = new ReportLogServiceImpl(ExcelDataRead.class); 
	XlsReader xlsReader = new XlsReader();
	
	public String[] readData(String testDataFilePath,String testDataSheetName,String ColumnName){
		   
	    try {
	    xlsReader.setPath(testDataFilePath);
	    } catch (IOException ioException) {
	    report.error("IOExeption occured as " + ioException.getMessage());
	    }
	    int rowcount = xlsReader.getRowCount(testDataSheetName);
	    String[] data=new String[rowcount];
	    for(int i=0;i<rowcount;i++) {
	    data[i]=xlsReader.getCellDataByColumnName(testDataSheetName, ColumnName, (i+1));   
	    }
	
	return data;
	}
	
	

}
