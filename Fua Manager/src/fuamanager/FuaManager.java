package fuamanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Polygon;


public class FuaManager {
	public static final String DAILY = "0";
	public static final String MONDAY = "1";
	public static final String TUESDAY = "2";
	public static final String WEDNESDAY = "3";
	public static final String THURSDAY = "4";
	public static final String FRIDAY = "5";
	public static final String SATURDAY = "6";
	public static final String SUNDAY = "7";
	private static final int CONNECT_TIMEOUT = 0;
	private static final int READ_TIMEOUT = 0;
	private static final String FUAFILE_URL = "https://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1Og8rGguyGgNR1DUpGW7Oq9k4_Xw";
	private static final String DOWNLOADED_FUAFILE_NAME = "ESPAÇO AÉREO.kml.xml";
	
	static ArrayList<Area> areas = new ArrayList<Area>();
	static ArrayList<FuaArea> fuaAreas = new ArrayList<FuaArea>();
	static ArrayList<CategoryDef> categories = new ArrayList<CategoryDef>();

	public static void main(String[] args) throws IOException, JAXBException {
		// TODO Auto-generated method stub
		
		
		if( args[0].contains("-L")) {
		    loadAreas(new File("TopSkyAreas.txt"));
		}
		
		else if( args[0].contains("-M")) {
			loadAreas(new File("TopSkyAreas.txt"));
			createFuaAreas(args);
			exportFuaAreas();
		}
		else if( args[0].contains("-A")) {
			loadAreas(new File("TopSkyAreas.txt"));
			downloadFua();
			exportFuaAreas();
		}
		

	}
	
	private static void createFuaAreas(String[] args) {
		// TODO Auto-generated method stub
		//LPD10:1400:1800:0:24000:SFL250
		
		String[] trimmedArgs = Arrays.copyOfRange(args, 1, args.length);//this is to remove the -M
		
		for(String part : trimmedArgs) {
			String[] elements = part.split("[\\:]");
			
			String areaName = elements[0];
			Area area = findAreabyName(areaName);
			
			if(area != null) {//if area exists
				String startHour = elements[1];
				String endHour = elements[2];
				int low = Integer.parseInt(elements[3]);
				int high = Integer.parseInt(elements[4]);
				String label = elements[5];
				
				
				FuaArea fa = new FuaArea(area, startHour, endHour, low, high, label);
				fuaAreas.add(fa);
				System.out.println("FUA Area schedule "+fa.getArea().getName()+" loaded");
			}
			else {
				System.out.println("Area "+areaName+" does not exist, FUA schedule not added.");
			}
		}
		System.out.println("All "+fuaAreas.size()+" FUA schedules processed.");	
	}
	
	private static void exportFuaAreas() {
		//LPD10:1400:1800:0:24000:SFL250
		try {
		      FileWriter fw = new FileWriter("TopSkyAreasManual.txt");
		      for(FuaArea fa : fuaAreas) {
		    	fw.write(fa.printFuaArea()+"\n");
				}
		      fw.close();
		      System.out.println("Successfully exported FUA to TopSky");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private static void loadAreas(File file) throws IOException {
		//readFromInputStream(input);
		try{
			Scanner reader = new Scanner(file);
			String line = reader.nextLine();
			
			if(!line.startsWith("//") && line.contains("CATEGORYDEF:")) {
				CategoryDef catdef = new CategoryDef(line);
				categories.add(catdef);
				//System.out.println("Category definition "+catdef.getName()+" loaded");
				line = reader.nextLine();
			}
			else if(!line.startsWith("//") && line.contains("AREA:T:")){
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
					//System.out.println("Category definition "+catdef.getName()+" loaded");
				}
				else if(!line.startsWith("//") && line.contains("AREA:T:")){
					//TODO see if area is already in the list
					Area a = new Area(line, reader);
					areas.add(a);
					//System.out.println("Loaded area "+a.getName());
					//System.out.println("Area "+a.getName()+" loaded with "+a.getCoordinates().size()+" coordinates. Line says: "+line);
				}
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("TopSkyAreas.txt not found!");
			e.printStackTrace();
		}
		System.out.println("Areas loading finished with "+categories.size()+" categories and "+areas.size()+" areas");
	}
	
	/**
	 * Searches for an Area in the Areas list
	 * @param name Name of the Area
	 * @return The Area object. Null if not found.
	 */
	private static Area findAreabyName(String name) {
	    Area result = null;
	    
		for (Area a : areas) {
	        if (a.getName().equals(name)) {
	            result = a;
	            break;
	        }
	    }
	    
	    return result;
	}

	private static void downloadFua() throws JAXBException, IOException {
		File fuaFile = new File(DOWNLOADED_FUAFILE_NAME);
		
		FileUtils.copyURLToFile(new URL(FUAFILE_URL), fuaFile, CONNECT_TIMEOUT, READ_TIMEOUT);
		parseFua(fuaFile);
		
	}
	
	public static void parseFua(File fuaFile) throws JAXBException {
	    
	    Kml unmarshal = Kml.unmarshal(fuaFile);
	    Document document = (Document) unmarshal.getFeature();
	    Folder areasRestrictas = (Folder) document.getFeature().get(6);//AREAS RESTRICTAS
	    System.out.println(areasRestrictas.getName());

	    int folderSize = areasRestrictas.getFeature().size();
	    // loop over all countries / Placemarks
	    for (int i = 0; i < folderSize; i++) {
	    	Placemark placemark = (Placemark) areasRestrictas.getFeature().get(i);
	    	System.out.println(placemark.getDescription());
	    }
	}



}


