package defaultPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import kr.inflearn.ExcelVO;

public class Project04_F {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "isbn.xls";
		List<ExcelVO> data = new ArrayList<ExcelVO>();
		
		try(FileInputStream fis = new FileInputStream(fileName)) {
			HSSFWorkbook workbook=new HSSFWorkbook(fis);
			HSSFSheet sheet=workbook.getSheetAt(0);
			Iterator<Row> rows=sheet.rowIterator();
			rows.next();
			String[] imsi = new String[5];
			while (rows.hasNext()) {
				HSSFRow row=(HSSFRow)rows.next(); //HSSFSheet -> HSSFRow (downcast)
				Iterator<org.apache.poi.ss.usermodel.Cell> cells =row.cellIterator();
				int i=0;
				while (cells.hasNext()) {
					HSSFCell cell=(HSSFCell)cells.next(); //HSSFRow-> HSSFCell (downcast)
					imsi[i] = cell.toString();
					i++;
				}
				//묶고(vo)->담고(list)
				ExcelVO vo = new ExcelVO(imsi[0], imsi[1], imsi[2], imsi[3], imsi[4]);
				data.add(vo);
			}
			pdf_maker(data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void pdf_maker(List<ExcelVO> data) {
		String[] headers = new String[] {"제목","저자","출판사","이미지"};
		Document doc = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(new File("bookList.pdf")));
			doc.open();
			
			BaseFont bFont = BaseFont.createFont("NANUMMYEONGJO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED	);
			Font fontHeader = new Font(bFont, 12);
			Font fontRow = new Font(bFont, 10);
			
			PdfPTable table =new PdfPTable(data.size());
			for(String header : headers) {
				PdfPCell cell = new PdfPCell();
				cell.setGrayFill(0.9f);
				cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
				table.addCell(cell);
			}
			table.completeRow();
			
			for(ExcelVO vo : data) {
				Phrase phrase=new Phrase(vo.getTitle(), fontRow);
				   table.addCell(new PdfPCell(phrase));
				   
				   phrase=new Phrase(vo.getAuthor(), fontRow);
				   table.addCell(new PdfPCell(phrase));
				   
				   phrase=new Phrase(vo.getCompany(), fontRow);
				   table.addCell(new PdfPCell(phrase));
				   
				   Image image=Image.getInstance(vo.getImgurl());
				   table.addCell(image);
				
				table.completeRow();
			}
			doc.addTitle("pdf table demo");
			doc.add(table);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			doc.close();
		}
	}

}
