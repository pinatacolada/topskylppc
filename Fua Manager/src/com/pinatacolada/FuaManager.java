package com.pinatacolada;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public static final ArrayList<String> FUA_FOLDERS = new ArrayList<String>(Arrays.asList("AREAS LP-R", "AREAS LP-D", "LP-P", "AREAS  LP-TRA", "NOTAM E OUTRAS AREAS"));//, "NOTAM E OUTRAS AREAS"

	private static final int CONNECT_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 10000;
	private static final String FUAFILE_URL = "https://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1Og8rGguyGgNR1DUpGW7Oq9k4_Xw";
	private static final String DOWNLOADED_FUAFILE_NAME = Integer.toString(LocalDate.now().getMonthValue())+Integer.toString(LocalDate.now().getDayOfMonth())+"fua.xml";

	private static final String NOTAM_URL = "https://www.notams.faa.gov/dinsQueryWeb/queryRetrievalMapAction.do";
	private static final String NOTAM_PARAM = "reportType=Raw&retrieveLocId=lppc&actionType=notamRetrievalByICAOs&submit=NOTAMs";
	

	static ArrayList<Area> areas = new ArrayList<Area>();
	static ArrayList<FuaArea> fuaAreas = new ArrayList<FuaArea>();
	static ArrayList<CategoryDef> categories = new ArrayList<CategoryDef>();
	static ArrayList<String> amcAreas = new ArrayList<String>();
	static ArrayList<Notam> notams = new ArrayList<Notam>();

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
			//loadNotams();//TODO
			exportFuaAreas();
		}


	}

	private static void loadNotams() throws IOException {

		URL url = new URL(NOTAM_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");

		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(NOTAM_PARAM);
		out.flush();
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer raw = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			raw.append(inputLine);
			raw.append("\n");
			
		}
		in.close();
		
		con.disconnect();
		
			
		Matcher m = Pattern.compile("<PRE\\b[^>]*>(.*?)<\\/PRE>", Pattern.DOTALL).matcher(raw);
		while (m.find()) {
			String match = m.group();
			match = match.replaceAll("</PRE>", "");
			match = match.replaceAll("<PRE>", "");
			
			Notam n = new Notam(match);
			notams.add(n);			
		}
		
		for(Notam n : notams) {
			if(n.getqCode().contains("QRALW")){//YO THIS IS AN AIRSPACE RESERVATION THIS KINDA LOOKS IMPORTANT
				System.out.println(n.printNotam());
				Area notamArea = findAreabyName(n.getId());
				if(notamArea == null) {
					System.out.println("HEY DOESNT EXIST NEED TO CREATE MEH");
					//A1311/20 NOTAMN
//					Q) LPPC/QRALW/IV/NBO/W /005/035/3940N00955W030
//					A) LPPC B) 2006010514 C) 2008312350
//					D) MON-FRI SR-2350//TODO
//					E) AIRSPACE RESERVATION AREA 2B WILL TAKE PLACE AREA BOUNDED BY:
//					400710N 0100535W - 400443N 0093654W - 394950N 0093634W - 
//					391200N 0095425W - 391200N 0100420W - 400710N 0100535W.
//					F) 500FT AMSL G) 3500FT AMSL
//					CREATED: 05 May 2020 11:39:00 
//					SOURCE: EUECYIYN
					
					//maybe! prolly not, nah, maybe
					
					BufferVert sapv = null;
					BufferLat sapl = null;
					Boolean sap = true;
					Boolean apw = true;
					Boolean msaw = true;
					AreaBound bound = null;//TODO
					ArrayList<Activation> active = new ArrayList<Activation>();
					active.add(new NotamAct(n.getFir(), "TEMPORARY KEYWORD FOR FINDING"));//TODO
					String usertext = null;
					BufferVert apwbv = null;
					BufferLat apwbl = null;
					VLimit limits = n.getLimits();
					ALabel label = new ALabel(n.getCoord(), limits.printSfl());
					String category = "NOTAM";
					String name = n.getId();
					String notam = n.getId();
					
					
					Area a = findAreabyName(n.getId().substring(0, 5));
					ArrayList<FuaCoordinate> coordinates = new ArrayList<FuaCoordinate>();
					
					if(a != null) {
						System.out.println("ARREBENTA A BOLHA");
						coordinates = a.getCoordinates();
					}
										
					notamArea = new Area(name, category, label, limits, apwbl, apwbv, usertext, active, bound, msaw, apw, sap, sapl, sapv, notam, coordinates);
					areas.add(notamArea);
					
					notamArea.printTopSky();
				}
			}
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
	public static Area findAreabyName(String name) {
		name = name.replaceAll("\\s+","");
		name = name.replace("-", "");

		if(name.contains("LPR60B")) {
			name = "LPR60BAMC";
		}
		if(name.contains("LPR42B")) {
			name = "LPR42BAMC";
		}

		return findAreabyNameStrict(name);
	}

	/**
	 * Searches for an Area in the Areas list
	 * @param name The exact name of the Area
	 * @return The Area object. Null if not found.
	 */
	private static Area findAreabyNameStrict(String name) {
		Area result = null;
		for (Area a : areas) {
			if (a.getName().contains(name)) {
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

		System.out.println("AMC PROCESSING");

		for(String folderName : FUA_FOLDERS) {
			FuaXMLFolder folder = fua.getFolderByName(folderName);
			loadFuaXMLFolder(folder);
			System.out.println("bbpbp");
		}
		System.out.println("AMC PROCESSING ENDED");

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
			if(folder.getName().contains("removemeremovemeNOTAM")){//TODO
				for(FuaXMLPlacemark place : folder.getPlacemarks()) {
					//regex [A-Z]\d\d\d\d
					Matcher m = Pattern.compile("[A-Z]\\d\\d\\d\\d").matcher(place.getDescription());
					while(m.find()) {
						String notamId = m.group();
						System.out.println("searching "+notamId);
						Area a = findAreabyName(notamId);
						if(a == null && (place.getDescription().contains("RESERVATION") || place.getDescription().contains("PARACHUTE"))) {
							System.out.println("CREATING AREA "+notamId);


							String areaSfl = null;
							FuaCoordinate labelCoordinate = null;
							ALabel label = new ALabel(labelCoordinate, areaSfl);

							ArrayList<Activation> activations = new ArrayList<Activation>();
							activations.add( new NotamAct("LPPC", "PLACEMARK KEYWORD"));

							VLimit limits = null;

							String[] bits = place.getDescription().split("<br>");
							for(String s : bits) {
								if(s.contains("/")) {
									String[] stringLimits = s.split("/");
									String low = stringLimits[0];
									String high = stringLimits[1];

									limits = new VLimit(low, high);
								}
							}

							ArrayList<FuaCoordinate> coordinates = place.getCoordinates();
							Area area = new Area(notamId, "NOTAM", label , limits , null, null, null, activations, null, false, false, false, null, null, notamId, coordinates);
							areas.add(area);	
						}
					}
				}
			}
			else {
				for(FuaXMLPlacemark place : folder.getPlacemarks()) {
					Area a = findAreabyName(place.getName());

					if(a!=null && a.isAmc()) {
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
}


