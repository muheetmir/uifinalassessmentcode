package com.qa.automation.mystoreapplication.excelreader;

import java.io.IOException;
import com.atmecs.falcon.automation.util.parser.XlsReader;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;


public class ExcelReaderWithDataProvider {
	private ReportLogService report = new ReportLogServiceImpl(ExcelReaderWithDataProvider.class); 
	XlsReader xlsReader = new XlsReader();
	
	public Object[][] Registrationdata(String testDataFilePath,String testDataSheetName){
		   
	    try {
	    xlsReader.setPath(testDataFilePath);
	    } catch (IOException ioException) {
	    report.error("IOExeption occured as " + ioException.getMessage());
	    }
	    int rowCount = xlsReader.getRowCount(testDataSheetName);
	    int colcount=xlsReader.getColumnCount(testDataSheetName);
	    Object[][] data=new Object[rowCount][xlsReader.getColumnCount(testDataSheetName)];

	    for(int i=0;i<rowCount;i++) {
	    for(int j=0;j<colcount;j++) {
	    data[i][j]= xlsReader.getCellDataByColumnIndex(testDataSheetName, j, (i+1));
	}
	}

	return data;
	}

}
