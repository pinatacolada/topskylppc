package fuamanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class main {
	private ArrayList<Area> areas = new ArrayList<Area>();
	private ArrayList<CategoryDef> categories = new ArrayList<CategoryDef>();
	
	public static void main(String[] args) {
		

	}
	
	

	private ArrayList<Area> loadAreas() {
		
		return areas;
	}
	
	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				if(line.contains("CATEGORYDEF")) {
					CategoryDef catdef = new CategoryDef(line);
					categories.add(catdef);
				}
				else if(line.contains("AREA")){
					Area a = new Area(line,br);
					
				}
			
				
				
				
				
				
				
				
				
				//resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}
}
