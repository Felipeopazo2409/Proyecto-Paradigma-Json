package paquete;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class FileUtil {
	
	private static final String JSON_FILE = "Trabajadores.json";
	
	public static void write(JSONArray arreglo) {
		
		FileWriter file;
		Gson gson = new Gson();
		String json = gson.toJson(arreglo);
		try {
			file = new FileWriter(JSON_FILE);
			file.write(json);
			file.flush();
			file.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public static JSONArray read() throws IOException, ParseException {
		
		JSONParser jsonParser = new JSONParser();
		
		FileReader reader = new FileReader(JSON_FILE);
			
		Object obj = jsonParser.parse(reader);
		
		String content = new String(Files.readAllBytes(Paths.get(JSON_FILE)));
		JSONObject empleadosLeidos1 = new JSONObject(content);
		System.out.println("empleadosLeidos1: " + empleadosLeidos1);
		empleadosLeidos1.get("myArrayList");
		
		
		System.out.println("empleadosLeidos1.get(\"myArrayList\"): " + empleadosLeidos1.get("myArrayList"));
		
		return (JSONArray) empleadosLeidos1.get("myArrayList");
		
			
	}
	
	

}
