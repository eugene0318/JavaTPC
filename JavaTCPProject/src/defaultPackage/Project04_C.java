package defaultPackage;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_C {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("ImageDemo.pdf"));
			doc.open();
			String filName = "inflearn.png";
			Image image = Image.getInstance(filName);
			doc.add(image);
			
			String url = "https://cdn.inflearn.com/assets/images/main/ufo2.png";
			image= Image.getInstance(url);
			doc.add(image);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			doc.close();
		}
	}

}
