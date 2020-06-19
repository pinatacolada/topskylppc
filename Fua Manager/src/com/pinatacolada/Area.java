package com.pinatacolada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author rtpso
 * unused
 */
public class Area {
	private String name;
	private String category;
	private ALabel label;
	private VLimit limits;
	//private ArrayList <Point> coordinates;//TODO
	private ArrayList <FuaCoordinate> coordinates = new ArrayList<FuaCoordinate>();
	private BufferLat apwbl;
	private BufferVert apwbv;
	private String usertext;
	private ArrayList <Activation> active = new ArrayList<Activation>();
	private AreaBound bound; 
	private boolean  msaw;
	private boolean  apw;
	private boolean  sap;
	private BufferLat sapl;
	private BufferVert sapv;
	private ArrayList <String> comments = new ArrayList<String>();
	private boolean amc = false;
	private String notam;

	//TODO figure this out eventually
	//Factory spatialContextFactory = new SpatialContextFactory();
	//private SpatialContext spatialContext = spatialContextFactory.newSpatialContext();
	//private ShapeFactory shapeFactory =spatialContext.getShapeFactory();
	
	/**
	 * @param name Area name
	 * @param category Area category
	 * @param coordinates List containing FuaCoordinates defining the area
	 */
	public Area(String name, String category, ALabel label, VLimit limits, BufferLat apwbl, BufferVert apwbv, String usertext, ArrayList<Activation> active, AreaBound bound, Boolean msaw, Boolean apw, Boolean sap, BufferLat sapl, BufferVert sapv, String notam, ArrayList<FuaCoordinate> coordinates) {
		super();
		this.setName(name);
		this.category = category;
		this.label = label;
		this.setLimits(limits);
		this.apwbl = apwbl;
		this.apwbv = apwbv;
		this.usertext = usertext;
		this.setActive(active);
		this.bound = bound;
		this.msaw = msaw;
		this.apw = apw;
		this.sap = sap;
		this.sapl = sapl;
		this.sapv = sapv;
		this.notam = notam;
		
		for(FuaCoordinate loc : coordinates) {
			System.out.println(loc.lat+":"+loc.lon);
			this.coordinates.add(loc);
		}
	}

	/**
	 * Creates an area based on the input read from TopSkyAreas.txt, expecting the formats supported by the plugin.
	 * @param line Line as read from the file
	 * @param sc BufferedReader used to read TopSkyAreas.txt
	 * @throws IOException
	 */
	public Area(String line, Scanner sc) throws IOException {
		//AREA:T:LPD10
		String[] firstLine = line.split("[\\:]");
		setName(firstLine[2]);
		
		while (sc.hasNext()){
			line = sc.nextLine();
			String[] parts = line.split("[\\:]");
			
			if(!line.isBlank()) {
				if(line.contains("CATEGORY")) {
//					CATEGORY:AMCRS
					category = parts[1];
					if(category.contains("AMC")) {
						setAmc(true);
					}
					if(category.contains("NOTAM")) {
						setNotam(name);
					}
				}
				else if(line.contains("LABEL")) {
//					LABEL:N038.48.64.000:W008.48.06.000:SFL250
					FuaCoordinate c = new FuaCoordinate(parts[1],parts[2]);
					label = new ALabel(c,parts[3]);
				}
				else if(line.contains("GROUP")) {
					//TODO
					unsupportedFunction(line);
				}
				else if(line.contains("USERTEXT")){
//					USERTEXT:SFL250
					usertext = parts[1];
				}
				else if(line.contains("ACTIVE")){
					switch(parts[1]) {
						case "1": getActive().add( new Activation() ); break;
						case "NOTAM": getActive().add( new NotamAct(parts[2],parts[3]) ); break;
						case "RWY": getActive().add( new RwyAct(parts) ); break;
						default: getActive().add( new SchedAct(parts) );
					}
				}
				else if(line.contains("BOUND")) {
					//BOUND:C:Lat:Lon:Radius
					bound = new AreaBound(parts[2],parts[3],parts[4]);
					
				}
				else if(line.contains("LIMITS")){
//					LIMITS:0:240
					setLimits(new VLimit(Integer.parseInt(parts[2].replaceAll("\\s+","")),Integer.parseInt(parts[1].replaceAll("\\s+",""))));
				}
				else if(line.contains("NOMSAW")) {
					msaw = false;
				}
				else if(line.contains("NOAPW")) {
					apw = false;
				}			
				else if(line.contains("APW_BUFFER_LAT")){
					//APW_BUFFER_LAT:0.0:0.0:0.0
					apwbl = new BufferLat(parts[1],parts[2],parts[3]);
				}
				else if(line.contains("APW_BUFFER_VERT")) {
					//APW_BUFFER_VERT:0:0:0
					apwbv = new BufferVert(parts[1],parts[2],parts[3]);
				}
				else if(line.contains("NOSAP")) {
					sap = false;
				}
				else if(line.contains("SAP_BUFFER_LAT")) {
					sapl = new BufferLat(parts[1],parts[2],parts[3]);
				}
				else if(line.contains("SAP_BUFFER_VERT")) {
					sapv = new BufferVert(parts[1],parts[2],parts[3]);
				}
				else if(line.contains("//")){
					//A0308/20 -  AIRSPACE RESERVATION USAFE2020 WILL TAKE PLACE AREA - NEMO
					comments.add(line.replace("//", ""));
				}
				else{//only coordinates left
					if( !line.isBlank() || line.length() < 26) {
						getCoordinates().add(new FuaCoordinate(line));
					}
					while(sc.hasNext()) {
						line = sc.nextLine();
						if((line.isBlank() || line.contains("AREA:T:")) || line.length() < 26) {
							break;
						}
						getCoordinates().add(new FuaCoordinate(line));
					}
					break;
				}
				line = "";
			}
		}	
	}
	
	

	private void unsupportedFunction(String s) {
		System.out.println("Function not supported. Line causing issue: "+s);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList <FuaCoordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList <FuaCoordinate> coordinates) {
		this.coordinates = coordinates;
	}
	
	public void toggleAMC() {
		if(isAmc()) {
			setAmc(false);
		}
		else {
			setAmc(true);
		}
	}

	public boolean isAmc() {
		return amc;
	}

	public void setAmc(boolean amc) {
		this.amc = amc;
	}
	
	public boolean isNotam() {
		if(notam != null ||!notam.isBlank()) {
			return true;
		}
		return false;
	}

	public void setNotam(String text) {
		this.notam = text;
	}
	
	public String getNotam() {
		return notam;
	}

	public ArrayList <Activation> getActive() {
		return active;
	}

	public void setActive(ArrayList <Activation> active) {
		this.active = active;
	}
	
	public String printTopSky() {
		
		////A4283/19
		//AREA:T:A4283
		//ACTIVE:1111:1112:1234567:1800:1900
		//ACTIVE:1113:1115:1234567:1800:2359
		//CATEGORY:NOTAM
		//LABEL:N039.22.00.000:W008.16.00.000:SFL140
		////USERTEXT:SFL140
		//LIMITS:0:130
		//N039.24.35.000 W008.21.35.000 
		//N039.24.35.000 W008.16.08.000 
		//N039.24.35.000 W008.16.08.000 
		
		
		String print = "";
		
		print += "//"+getName()+"\n";
		print += "AREA:T:"+getName()+"\n";
		
		for(Activation a : active) {
			print += a.printTopSky() + "\n";
		}
		
		print += "CATEGORY:" + category +"\n";
		
		print += label.printTopSky() + "\n";
		
		print += getLimits().printTopSky() + "\n";
		
		for(FuaCoordinate c : coordinates) {
			print += c.printTopsky() + "\n";
		}

		
		
		System.out.println(print);
		
		
		return print;
		
	}

	public VLimit getLimits() {
		return limits;
	}

	public void setLimits(VLimit limits) {
		this.limits = limits;
	}
}
