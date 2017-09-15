package com.vogella.maven.jpgtopdf;

/**
 * @author John
 */

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

/*
 * JPG To PDF converter
 * 
 * MAVEN:
 * <dependency>
 *   <groupId>com.itextpdf</groupId>
 *   <artifactId>itextpdf</artifactId>
 *   <version>5.0.6</version>
 * </dependency>
 * 
 * Link:
 * https://howtodoinjava.com/apache-commons/create-pdf-files-in-java-itext-tutorial/
 * http://tutorials.jenkov.com/java-itext/image.html
 * 
 */

class Main {

	public static void main(String[] args) {
		Document document = new Document();
		
		System.out.println("JPG to PDF converter");
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please enter the JPG folder path (default: ./):");
		String jpgPath = sc.nextLine();
		if(jpgPath.isEmpty()){
			jpgPath = "./";
		}
		
		System.out.print("Please enter the PDF document name (default: ./newDocument.pdf):");
		String pdfName = sc.nextLine();
		if(pdfName.isEmpty()){
			pdfName = "newDocument.pdf";
		}
		sc.close();
		
		try
		{

		    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfName));
		    document.open();
		 
		    File imgFolder = new File(jpgPath);
		    
		    int numberOfPage = 0;
		    for (File fileEntry : imgFolder.listFiles()) {
		    	if (fileEntry.isFile() && fileEntry.getName().endsWith(".jpg")){
		    
		    		//Add Image
		    		Image imagePage = Image.getInstance(fileEntry.getPath());
		    
		    		//Fixed Positioning
		    		imagePage.setAbsolutePosition(0f, 0f);
		    
		    		//Scale to new height and new width of image
		    		imagePage.scaleAbsolute(document.getPageSize().getWidth(),document.getPageSize().getHeight());
		    		//Add to document
		    		document.add(imagePage);
		    		
		    		numberOfPage++;
		    		// Next page
		    		document.newPage();
		    	}
		    
		    }
		    
		    document.close();
		    writer.close();
		    
		    File pdfFile = new File(pdfName);
		    
		    System.out.printf("%d image(s) are converted to %s (%.2f kByte).%n",numberOfPage,pdfName,pdfFile.length()/1000.0);
		    
		} catch (Exception e) {
			
		    e.printStackTrace();
		}

	}

}
