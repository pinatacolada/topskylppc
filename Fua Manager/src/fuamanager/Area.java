package fuamanager;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author rtpso
 * unused
 */
public class Area {
	public Area(String name, String category, ALabel label, VLimit limits, Coordinate[] coordinates,
			BufferLat aPWBL, BufferVert aPWBV, String usertext, Activation active, Boundary bound, boolean noMSAW,
			boolean noAPW, boolean noSAP, BufferLat sAPL, BufferVert sAPV) {
		super();
		this.name = name;
		this.category = category;
		this.label = label;
		this.limits = limits;
		this.coordinates = coordinates;
		APWBL = aPWBL;
		APWBV = aPWBV;
		this.usertext = usertext;
		this.active = active;
		this.bound = bound;
		NoMSAW = noMSAW;
		NoAPW = noAPW;
		NoSAP = noSAP;
		SAPL = sAPL;
		SAPV = sAPV;
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
				Coordinate c = new Coordinate(parts[1],parts[2]);
				label = new ALabel(c,parts[3]);
			}
			else if(line.contains("LABEL")){
//				LIMITS:0:240
				limits = new VLimit(parts[1],parts[2]);
			}
			
//			N038.49.15.000 W008.53.20.000 
//			N038.49.15.000 W008.40.50.000 
//			N038.49.15.000 W008.40.50.000 
//			N038.44.37.000 W008.40.50.000 
//			N038.44.37.000 W008.40.50.000 
//			N038.43.55.000 W008.43.05.000 
//			N038.43.55.000 W008.43.05.000 
//			N038.43.55.000 W008.48.35.000 
//			N038.43.55.000 W008.48.35.000 
//			N038.45.44.000 W008.53.20.000 
//			N038.45.44.000 W008.53.20.000  
			
			else if(line.contains("APW_BUFFER_LAT")){
				//APW_BUFFER_LAT:0.0:0.0:0.0
				APWBL = new BufferLat(parts[1],parts[2],parts[3]);
			}
			else if(line.contains("APW_BUFFER_VERT")) {
				//APW_BUFFER_VERT:0:0:0
				APWBV = new BufferVert(parts[1],parts[2],parts[3]);
			}
			else if(line.contains("USERTEXT")){
//				USERTEXT:SFL250
				usertext = parts[1];
			}
			else if(line.contains("ACTIVE")){
				switch(parts[1]) {
					case "1": active = new Activation();
					case "NOTAM": active = new NotamAct(parts[2],parts[3]);
					case //TODO i went to sleep here zzzzzzzzzz
					default: active = new SchedAct();//TODO
				}
				
				
				
				
			}
		}
	}
	private String name;
	private String category;
	private ALabel label;
	private VLimit limits;
	private Coordinate[] coordinates;//TODO
	private BufferLat APWBL;
	private BufferVert APWBV;
	private String usertext;
	private Activation active;
	private Boundary bound; 
	private boolean  NoMSAW;
	private boolean  NoAPW;
	private boolean  NoSAP;
	private BufferLat SAPL;
	private BufferVert SAPV;

	
}
