package fuamanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

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
	private ArrayList <FuaCoordinate> coordinates;//TODO
	private BufferLat apwbl;
	private BufferVert apwbv;
	private String usertext;
	private Activation active;
	private AreaBound bound; 
	private boolean  msaw;
	private boolean  apw;
	private boolean  sap;
	private BufferLat sapl;
	private BufferVert sapv;

	//TODO figure this out eventually
	//Factory spatialContextFactory = new SpatialContextFactory();
	//private SpatialContext spatialContext = spatialContextFactory.newSpatialContext();
	//private ShapeFactory shapeFactory =spatialContext.getShapeFactory();
	
	public Area(String name, String category, FuaCoordinate[] coordinates) {
		super();
		this.name = name;
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
			this.coordinates.add(loc);
		}
	}

	public Area(String line, BufferedReader br) throws IOException {
		//AREA:T:LPD10
		String[] firstLine = line.split("[\\:]");
		name=firstLine[2];
		
		while ((line = br.readLine()).contains("AREA") == false){
			String[] parts = line.split("[\\:]");
			if(line.contains("CATEGORY")) {
//				CATEGORY:AMCRS
				category = parts[1];	
			}
			else if(line.contains("LABEL")) {
//				LABEL:N038.48.64.000:W008.48.06.000:SFL250
				FuaCoordinate c = new FuaCoordinate(parts[1],parts[2]);
				label = new ALabel(c,parts[3]);
			}
			else if(line.contains("GROUP")) {
				//TODO
				unsupportedFunction(line);
			}
			else if(line.contains("USERTEXT")){
//				USERTEXT:SFL250
				usertext = parts[1];
			}
			else if(line.contains("ACTIVE")){
				switch(parts[1]) {
					case "1": active = new Activation(); break;
					case "NOTAM": active = new NotamAct(parts[2],parts[3]); break;
					case "RWY": active = new RwyAct(parts); break;
					default: active = new SchedAct(parts);
				}
			}
			else if(line.contains("BOUND")) {
				//BOUND:C:Lat:Lon:Radius
				bound = new AreaBound(parts[2],parts[3],parts[4]);
				
			}
			else if(line.contains("LIMITS")){
//				LIMITS:0:240
				limits = new VLimit(parts[1],parts[2]);
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
			else if(line.contains("COORD")) {
				//TODO
				while ((line = br.readLine()).contains("AREA") == false){
					//coordinates.add(shapeFactory.pointXY(0, 0));
				}
//				N038.49.15.000 W008.53.20.000 
//				N038.49.15.000 W008.40.50.000 
//				N038.49.15.000 W008.40.50.000 
//				N038.44.37.000 W008.40.50.000 
//				N038.44.37.000 W008.40.50.000 
//				N038.43.55.000 W008.43.05.000 
//				N038.43.55.000 W008.43.05.000 
//				N038.43.55.000 W008.48.35.000 
//				N038.43.55.000 W008.48.35.000 
//				N038.45.44.000 W008.53.20.000 
//				N038.45.44.000 W008.53.20.000 
			}			
		}
	}
	
	private void unsupportedFunction(String s) {
		System.out.println("Function not supported. Line causing issue: "+s);
	}
}
