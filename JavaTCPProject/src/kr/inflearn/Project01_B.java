package kr.inflearn;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Project01_B {

	public static void main(String[] args) {
		
		JSONArray students = new JSONArray();
		// TODO Auto-generated method stub
		JSONObject student = new JSONObject();
		student.put("name", "홍길동");
		student.put("phone", "010-9001-4663");
		student.put("address", "서울");
		
		students.put(student);
		
		student = new JSONObject();
		student.put("name", "나길동");
		student.put("phone", "010-2222-2222");
		student.put("address", "부산");
		
		students.put(student);
		
		JSONObject object = new JSONObject();
		
		object.put("students", students);
		
		System.out.println(object.toString(1));
	}

}
