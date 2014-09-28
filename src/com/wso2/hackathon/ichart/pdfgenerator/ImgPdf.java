package com.wso2.hackathon.ichart.pdfgenerator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
public class ImgPdf {
 
    public static void main(String[] args) throws FileNotFoundException, IOException {
 
        try {
              OutputStream file = new FileOutputStream(new File("E:\\PDF_Java4s.pdf"));
              Document document = new Document();
              PdfWriter.getInstance(document, file);

                 Image image = Image.getInstance ("src/Images/test.jpg");
                 image.scaleAbsolute(120f, 60f); 
                 Image image2 = Image.getInstance ("src/Images/test2.jpg");
                 image2.scaleAbsolute(120f, 60f);
                 Image image3 = Image.getInstance ("src/Images/test3.png");
                 image3.scaleAbsolute(120f, 60f);
 
                 List list=new List(true,30);
                 list.add(new ListItem("Java"));
                 list.add(new ListItem("Php"));
                 list.add(new ListItem("Some Thing..."));        
 
                 Chunk chunk=new Chunk("Welecome you all...");
                 chunk.setUnderline(+1f,-2f);
                 Chunk chunk1=new Chunk("Hello");
                 chunk1.setUnderline(+4f,-8f);
                 chunk1.setBackground(new BaseColor (17, 46, 193));      
 
                 document.open();                  
 
                    document.add(image);
                    
                    document.add(Chunk.NEWLINE);
                    
                    document.add(image2);
 
                    document.add(Chunk.NEWLINE); 
                    
                    document.add(image3);
                    
                    document.add(Chunk.NEWLINE); 
 
                    document.add(new Paragraph("Dear user"));
                    document.add(new Paragraph("Document Generated On - "+new Date().toString()));    
 
                    document.add(chunk);
                    document.add(chunk1);
 
                    document.add(Chunk.NEWLINE);                                  
 
                    document.newPage();            
                    document.add(list); 
 
                 document.close();
 
                 file.close();
 
            System.out.println("Pdf created successfully..");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        File file1 = new File("E:\\PDF_Java4s.pdf");
    	 
        FileInputStream fis = new FileInputStream(file1);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            //Logger.getLogger(genJpeg.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
}
