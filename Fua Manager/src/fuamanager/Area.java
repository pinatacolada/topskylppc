package fuamanager;

public class Area {
	public Area(String name, CategoryDef category, ALabel label, VLimit limits, Coordinate[] coordinates,
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
	public Area(String line) {
		// TODO Auto-generated constructor stub
		
	}
	private String name;
	private CategoryDef category;
	private ALabel label;
	private VLimit limits;
	private Coordinate[] coordinates;
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
