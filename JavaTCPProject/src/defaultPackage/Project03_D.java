package defaultPackage;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.text.html.HTML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import kr.inflearn.ExcelVO;

public class Project03_D {

	public static void main(String[] args) {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("책제목:");
			String title = br.readLine();
			
			System.out.println("책저자:");
			String  author= br.readLine();
			 
			System.out.println("책출판사:");
			String company = br.readLine();
			
			ExcelVO vo = new ExcelVO(title, author, company);
			getIsbnImage(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private static void getIsbnImage(ExcelVO vo) {
		// TODO Auto-generated method stub
		try {
			String openApi = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl="
					+URLEncoder.encode(vo.getTitle(), "UTF-8")
					+ "&d+auth="+URLEncoder.encode(vo.getAuthor(), "UTF-8")
					+"&d+publ="+URLEncoder.encode(vo.getCompany(), "UTF-8");
			URL url = new URL(openApi);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", "4ibvf7a7s4");
			con.setRequestProperty("X-Naver-Client-Secret", "pcfd4vM5IVLhIpr1dgfWZyqVDIQZoCD6o3tCkQwx");
			
			int responseCode=con.getResponseCode();
			
			BufferedReader br1 = null;
			if(responseCode == 200) {
				br1=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			}else {
				br1 = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br1.readLine()) !=null) {
				response.append(inputLine);
			}
			br1.close();
			System.out.println(response.toString());
			//isbn, image 
			Document doc = Jsoup.parse(response.toString());
			System.out.println(doc.toString());
			Element total =doc.select("title").first();
			System.out.println(total.text());
			if (total.text().equals("0")) {
				Element isbn=doc.select("isbn").first();
				 String isbnStr=isbn.text();
				 //System.out.println(isbnStr);		 
				 String isbn_find=isbnStr.split(" ")[1];
				 vo.setIsbn(isbn_find);
				 //-------------------------------------
				 //Element img=doc.select("img").first();
				 //System.out.println(img.toString());
				 String imgDoc=doc.toString();
				 String imgTag=imgDoc.substring(imgDoc.indexOf("<img>")+5);
				 //System.out.println(imgTag); // http://~~~~~~~~~~~~~~
				 String imgURL=imgTag.substring(0, imgTag.indexOf("?"));
				 System.out.println(imgURL);
				 String fileName=imgURL.substring(imgURL.lastIndexOf("/")+1);
				 System.out.println(fileName);
				 vo.setImgurl(fileName);
				 
				 System.out.println(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
