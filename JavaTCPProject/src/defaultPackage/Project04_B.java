package defaultPackage;

import java.io.FileOutputStream;

import org.jsoup.nodes.Document;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_B {

	public static void main(String[] args) {
		com.itextpdf.text.Document doc= new com.itextpdf.text.Document();
		try {
			FileOutputStream fos = new FileOutputStream("paragrapDemo.pdf");
			PdfWriter.getInstance(doc, fos);
			doc.open();
			
			String content = "your word is a lamp";
			Paragraph par1 = new Paragraph(32);
			par1.setSpacingBefore(50);
			par1.setSpacingAfter(50);
			
			for(int i=0; i<20; i++	) {
				Chunk chunk = new Chunk(content);
				par1.add(chunk);
				
			}
			doc.add(par1);
			
			Paragraph par2 = new Paragraph();
			for(int i=0; i<10; i++) {
				par2.add(new Chunk(content));
			}
			doc.add(par2);
			doc.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
