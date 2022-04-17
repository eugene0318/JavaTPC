package defaultPackage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import kr.inflearn.ExcelDAO;
import kr.inflearn.ExcelVO;

public class Project03_F {

	public static void main(String[] args) {
		ExcelDAO dao = new ExcelDAO();
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		try {
			System.out.println("입력처리(I)/종료(E):");
			String sw = br.readLine();
			switch (sw) {
			case "I":
				dao.excel_input();
				break;
			case "E":
				System.out.println("프로그램 종료");
				System.exit(0);
				break;
			default:
				System.out.println("I or E input");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
