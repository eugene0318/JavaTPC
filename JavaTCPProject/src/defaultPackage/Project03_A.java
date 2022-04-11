package defaultPackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javafx.scene.control.Cell;
import kr.inflearn.ExcelVO;

public class Project03_A {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "bookList.xls";
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
			showExcelData(data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void showExcelData(List<ExcelVO> data) {
		for(ExcelVO vo :  data) {
			System.out.println(vo);
		}
		
	}

}
