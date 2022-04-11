package defaultPackage;

import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import kr.inflearn.DownloadBroker;


public class Project02_B {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BosyMatter?qt_ty=QT1";
		BufferedReader br =new  BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("[입력->년(yyyy)-월(mm)-일(dd)]:");
			String bible=br.readLine();
			url=url+"&Base_de="+bible+"&bibleType=1";
			System.out.println("================================");
			Document doc=Jsoup.connect(url).post();
			Element bible_text=doc.select(".bible_text").first();
			System.out.println(bible_text.text());
			
			Element bibleinfo_box=doc.select(".bibleinfo_box").first();
			System.out.println(bibleinfo_box.text());
			
			Elements liList=doc.select(".body_list > li");
			for(Element li : liList) {
				System.out.println(li.select(".num").first().text());
				System.out.println(li.select(".info").first().text());
			}
			//리소스 다운로드(mp3, image)
//			
//			Element tag=doc.select("source").first();
//			String dPath=tag.attr("src").trim(); //공백 제거 
//			System.out.println(dPath);
//			String fileName = dPath.substring(dPath.lastIndexOf("/")+1);
//			

			Element tag=doc.select(".img > img").first();
			String dPath="https://sum.su.or.kr:8888" + tag.attr("src").trim(); //공백 제거 
			System.out.println(dPath); //https://sum.su.or.kr:8888/attach/X07/855430cf9d434d5a97f43b7796bcaee9.jpg
			String fileName = dPath.substring(dPath.lastIndexOf("/")+1);
			Runnable r = new DownloadBroker(dPath, fileName);
			Thread dLoad = new Thread(r);
			dLoad.start();
			for(int i=0; i<10; i++) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.print(" "+(i+1));
			}
			System.out.println();
			System.out.println("==========================");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
