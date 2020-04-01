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
	
	private static void loadAreas(File file) throws IOException {
		// TODO Auto-generated method stub
		//readFromInputStream(input);
		try{
			Scanner reader = new Scanner(file);
			String line = reader.nextLine();
			
			if(!line.startsWith("//") && line.contains("CATEGORYDEF:")) {
				CategoryDef catdef = new CategoryDef(line);
				categories.add(catdef);
				System.out.println("Category definition "+catdef.getName()+" loaded");
				line = reader.nextLine();
			}
			else if(!line.startsWith("//") && line.contains("AREA:T:")){
				//line = reader.nextLine();
				System.out.println("Loading line "+line);
				Area a = new Area(line, reader);
				areas.add(a);
				line = reader.nextLine();
			}	
			
			while (reader.hasNextLine()){
				line = reader.nextLine();
				if(!line.startsWith("//") && line.contains("CATEGORYDEF:")) {
					CategoryDef catdef = new CategoryDef(line);
					categories.add(catdef);
					System.out.println("Category definition "+catdef.getName()+" loaded");
				}
				else if(!line.startsWith("//") && line.contains("AREA:T:")){
					//line = reader.nextLine();
					Area a = new Area(line, reader);
					areas.add(a);
					System.out.println("Loaded area "+a.getName());
					//System.out.println("Area "+a.getName()+" loaded with "+a.getCoordinates().size()+" coordinates. Line says: "+line);
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


