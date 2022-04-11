package defaultPackage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.inflearn.BookDTO;

public class Project01_A {

	public static void main(String[] args) {
		
		//1. object(BookDTO)->json : .toJson
		BookDTO dto = new BookDTO("자바", 21000, "에이콘", 670);
		Gson g = new Gson();
		String json = g.toJson(dto);
		//System.out.println(json);
		
		//2. Json(String) -> Object(BookDTO) : .fromJson(object, class);
		BookDTO dto1 =g.fromJson(json, BookDTO.class);
		//System.out.println(dto1);
		
		
		//3. Object(List<BookDTO>) -> json(String)
		List<BookDTO> list = new ArrayList<BookDTO>();
		list.add( new BookDTO("java1", 121000, "에어컨1", 5701));
		list.add( new BookDTO("java2", 221000, "에어컨2", 5701));
		list.add( new BookDTO("java3", 321000, "에어컨3", 5702));
		
		String listJson =g.toJson(list);
		//System.out.println(listJson);
		
		//4. json(String) -> Object(List<BookDTO>)
		List<BookDTO> list1 =g.fromJson(listJson, new TypeToken<List<BookDTO>>() {}.getType());
		for(BookDTO vo : list1) {
			System.out.println(vo);
		}
		
	}

}
