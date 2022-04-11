package kr.inflearn;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.stream.JsonToken;

public class Project01_C {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String src = "Info.json";
		//IO -> Stream
		InputStream is = Project01_C.class.getResourceAsStream(src);
		if(is == null) {
			throw new NullPointerException("cannot find resource file");
		}
		JSONTokener tokener = new JSONTokener(is);
		JSONObject object = new JSONObject(tokener);
		JSONArray students = object.getJSONArray("students"); //JSONArray의 key값:students
		for(int i=0; i<students.length(); i++) {
			JSONObject student = (JSONObject)students.get(i); //upcasting
			System.out.println(student.get("address"));
		}
	}

}
