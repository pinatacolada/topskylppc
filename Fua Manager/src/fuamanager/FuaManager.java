package fuamanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FuaManager {
	static ArrayList<Area> areas = new ArrayList<Area>();
	static ArrayList<CategoryDef> categories = new ArrayList<CategoryDef>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		if( args[0].contains("-L")) {
		    loadAreas(new File("TopSkyAreas.txt"));
		}
		

	}
	
	public void init(){
        FuaManager fua = new FuaManager();
    }
	
	

	private static void loadAreas(File file) throws IOException {
		// TODO Auto-generated method stub
		//readFromInputStream(input);
		try{
			Scanner reader = new Scanner(file);
			String line;
				
			while (reader.hasNextLine()){
				line = reader.nextLine();
					
				if(!line.startsWith("//") && line.contains("CATEGORYDEF:")) {
					CategoryDef catdef = new CategoryDef(line);
					categories.add(catdef);
					System.out.println("Category definition "+catdef.getName()+" loaded");
				}
				else if(!line.startsWith("//") && line.contains("AREA:T:")){
					Area a = new Area(line, reader);
					areas.add(a);
					System.out.println("Area "+a.getName()+" loaded");
				}
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("TopSkyAreas.txt not found!");
			e.printStackTrace();
		}
		System.out.println("Areas loading finished with "+areas.size()+" areas");
	}
}


