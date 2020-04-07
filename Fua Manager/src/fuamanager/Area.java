package fuamanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.ShapeFactory;


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

	//TODO figure this out eventually
	//Factory spatialContextFactory = new SpatialContextFactory();
	//private SpatialContext spatialContext = spatialContextFactory.newSpatialContext();
	//private ShapeFactory shapeFactory =spatialContext.getShapeFactory();
	
	/**
	 * @param name Area name
	 * @param category Area category
	 * @param coordinates List containing FuaCoordinates defining the area
	 */
	public Area(String name, String category, FuaCoordinate[] coordinates) {
		super();
		this.setName(name);
		this.category = category;
		label = null;
		limits = null;
		apwbl = null;
		apwbv = null;
		usertext = null;
		active = null;
		bound = null;
		msaw = true;
		apw = true;
		sap = true;
		sapl = null;
		sapv = null;
		
		for(FuaCoordinate loc : coordinates) {
			this.getCoordinates().add(loc);
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
		
		while (sc.hasNext() && !line.isBlank() == false){
			String[] parts = line.split("[\\:]");
			if(!line.isBlank()) {
				if(line.contains("CATEGORY")) {
//					CATEGORY:AMCRS
					category = parts[1];	
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
						case "1": active.add( new Activation() ); break;
						case "NOTAM": active.add( new NotamAct(parts[2],parts[3]) ); break;
						case "RWY": active.add( new RwyAct(parts) ); break;
						default: active.add( new SchedAct(parts) );
					}
				}
				else if(line.contains("BOUND")) {
					//BOUND:C:Lat:Lon:Radius
					bound = new AreaBound(parts[2],parts[3],parts[4]);
					
				}
				else if(line.contains("LIMITS")){
//					LIMITS:0:240
					limits = new VLimit(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
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
					line = sc.nextLine();
					
				}
				
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
}
