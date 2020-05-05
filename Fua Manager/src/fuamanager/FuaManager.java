package fuamanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBException;
import org.xml.sax.*;


public class FuaManager {
	public static final String DAILY = "0";
	public static final String MONDAY = "1";
	public static final String TUESDAY = "2";
	public static final String WEDNESDAY = "3";
	public static final String THURSDAY = "4";
	public static final String FRIDAY = "5";
	public static final String SATURDAY = "6";
	public static final String SUNDAY = "7";
	public static final int TRANSITION_LEVEL = 50;

	private static final int CONNECT_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 10000;
	private static final String FUAFILE_URL = "https://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1Og8rGguyGgNR1DUpGW7Oq9k4_Xw";
	private static final String DOWNLOADED_FUAFILE_NAME = Integer.toString(LocalDate.now().getMonthValue())+Integer.toString(LocalDate.now().getDayOfMonth())+"fua.xml";


	static ArrayList<Area> areas = new ArrayList<Area>();
	static ArrayList<FuaArea> fuaAreas = new ArrayList<FuaArea>();
	static ArrayList<CategoryDef> categories = new ArrayList<CategoryDef>();
	static ArrayList<String> amcAreas = new ArrayList<String>();

	public static void main(String[] args) throws IOException, JAXBException, ParserConfigurationException, SAXException {

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
			FuaXMLKml kml = downloadFua();
			loadFua(kml);
			exportFuaAreas();
		}


	}

	private static void createFuaAreas(String[] args) {
		//LPD10:1400:1800:0:24000:SFL250

		String[] trimmedArgs = Arrays.copyOfRange(args, 1, args.length);//this is to remove the -M

		for(String part : trimmedArgs) {
			String[] elements = part.split("[\\:]");

			String areaName = elements[0];
			Area area = findAreabyNameStrict(areaName);

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
		System.out.println("Areas loading finished with "+categories.size()+" categories and "+areas.size()+" areas");
	}


	/**
	 * Searches for an Area in the Areas list
	 * @param name The exact name of the Area. Areas which have AMC appended to their name will return the AMC variant. Use strict variant of this method if this isnt intended
	 * @return The Area object. Null if not found.
	 */
	private static Area findAreabyName(String name) {
		Area result = null;

		name = name.replace("-", "");

		if(name.contains("LPR60B")) {
			name = "LPR60BAMC";
		}
		if(name.contains("LPR42B")) {
			name = "LPR42BAMC";
		}


		for (Area a : areas) {
			if (a.getName().equals(name)) {
				result = a;
				break;
			}
		}

		return result;
	}

	/**
	 * Searches for an Area in the Areas list
	 * @param name The exact name of the Area
	 * @return The Area object. Null if not found.
	 */
	private static Area findAreabyNameStrict(String name) {
		Area result = null;

		for (Area a : areas) {
			if (a.getName().equals(name)) {
				result = a;
				break;
			}
		}

		return result;
	}

	private static FuaXMLKml downloadFua() throws JAXBException, IOException, ParserConfigurationException, SAXException {
		File fuaFile = new File(DOWNLOADED_FUAFILE_NAME);

		FileUtils.copyURLToFile(new URL(FUAFILE_URL), fuaFile, CONNECT_TIMEOUT, READ_TIMEOUT);
		return parseFua(fuaFile);

	}
	private static void loadFua(FuaXMLKml kml) {
		FuaXMLDocument fua = kml.getDocument();
		FuaXMLFolder notam = fua.getFolderByName("NOTAM E OUTRAS AREAS");//TODO
		FuaXMLFolder lpr = fua.getFolderByName("AREAS RESTRITAS");
		FuaXMLFolder lpd = fua.getFolderByName("AREAS PERIGOSAS");
		FuaXMLFolder lpp = fua.getFolderByName("AREAS PROIBIDAS");
		FuaXMLFolder lptra = fua.getFolderByName("AREAS TEMPORARIAMENTE RESTRITAS");


		System.out.println("AMC PROCESSING");
		
		loadFuaXMLFolder(lpr);
		loadFuaXMLFolder(lpd);
		loadFuaXMLFolder(lpp);
		loadFuaXMLFolder(lptra);
	
	}

	public static FuaXMLKml parseFua(File fuaFile) throws JAXBException, ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		FuaXMLHandler fuaHandler = new FuaXMLHandler();

		saxParser.parse(fuaFile, fuaHandler);
		FuaXMLKml fua = fuaHandler.getKml();

		return fua;

	}

	public static void loadFuaXMLFolder(FuaXMLFolder folder) {
		if(folder != null && folder.getPlacemarks().size() > 0) {
			for(FuaXMLPlacemark place : folder.getPlacemarks()) {

				Area a = findAreabyName(place.getName());

				if(a.isAmc()) {
					ArrayList<SchedAct> acts = place.toSchedAct();

					for(SchedAct act : acts) {
						FuaArea areaManual = new FuaArea(a, act, act.getLimits(), act.getUserText());
						fuaAreas.add(areaManual);
						System.out.println(areaManual.printFuaArea());
					}
				}
			}
		}
	}
	
}

