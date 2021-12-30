package com.genericlib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileLib {
	public String getDataFromProperty(String path,String key) throws Throwable{
		FileInputStream ip =new FileInputStream(path);
		Properties prop=new Properties();
		prop.load(ip);
		return prop.getProperty(key);
	}

	public static String readstringfromexcel(String sheetname,int rowno,int cellno) throws EncryptedDocumentException, IOException, InvalidFormatException, IllegalStateException  {
		File abspath=new File("./src/test/resources/TestDataSemiCab.xlsx");
		FileInputStream fis=new FileInputStream(abspath);
		Workbook workbook = WorkbookFactory.create(fis);
		String data = workbook.getSheet(sheetname).getRow(rowno).getCell(cellno).getStringCellValue();
		return data;
	}
		
		public static double readnumericvaluefromexcel(String sheetname,int rowno,int cellno) throws EncryptedDocumentException, IOException, InvalidFormatException {
			File abspath=new File("./src/test/resources/TestDataSemiCab.xlsx");
			FileInputStream fis=new FileInputStream(abspath);
			Workbook workbook = WorkbookFactory.create(fis);
			double data = workbook.getSheet(sheetname).getRow(rowno).getCell(cellno).getNumericCellValue();
			return data;
		}

	
	}

