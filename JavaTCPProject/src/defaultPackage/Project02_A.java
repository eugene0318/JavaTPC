package defaultPackage;

import java.net.URL;

import javax.lang.model.element.Element;
import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class Project02_A {

	public static void main(String[] args) {
		// Jsoup API
		String url = "https://sports.news.naver.com/wfootball/index.nhn";
		org.jsoup.nodes.Document doc = null; 
		try {
			doc = Jsoup.connect(url).get(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		Elements element= doc.select("div.home_news");
		String title =element.select("h2").text().substring(0, 4);
		System.out.println("=============================");
		System.out.println(title);
		System.out.println("=============================");
		for(org.jsoup.nodes.Element el : element.select("li")) {
			System.out.println(el.text());
		}
		System.out.println("=============================");
	}

}
