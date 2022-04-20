package defaultPackage;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_D {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("ImageScaleing.pdf"));
			doc.open();
			
			String fileName="googlelogo.png";
			Image image=Image.getInstance(fileName);
			doc.add(image);
			
			fileName="googlelogo.png";
			image=Image.getInstance(fileName);
			image.scaleAbsolute(200f, 200f);
			doc.add(image);
			
			String url="https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
			image=Image.getInstance(url);
			image.scalePercent(200f);
			doc.add(image);
	
			image=Image.getInstance(url);
			image.scaleToFit(100f, 200f);
			doc.add(image);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
