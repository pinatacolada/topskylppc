package fuamanager;

public class VLimit{

	private int high;
	private int low;
	private int sfl;

	public VLimit(int h, int l) {
		high=h;
		low=l;
		sfl = calculateSFL();
	}

	public VLimit(String sLow, String sHigh){
		low = stringToAlt(sLow);
		high = stringToAlt(sHigh);
		sfl = calculateSFL();

	}

	public VLimit(String name, String sLow, String sHigh) {
		low = stringToAlt(sLow);
		high = stringToAlt(sHigh);
		sfl = calculateSFL();
	}
	
	

	public String printVLimit() {
		return low+":"+high;
	}

	public int stringToAlt(String s) {
		int level = -1;

		if(s.contentEquals("GND") || s.contentEquals("SFC")) {
			level = 0;
		}
		else if(s.contains("ft")) {
			s = s.replace(",", "");
			s = s.replace("ft","");
			s = s.replace("AMSL","");
			s = s.replace("AGL","");
			s = s.replaceAll("\\s","");
			level = Integer.parseInt(s) / 100;

		}
		else if(s.contains("FL")) {
			s = s.replace("FL", "");
			level = Integer.parseInt(s);
		}
		return level;

	}

	public int calculateSFL() {
		int buffer = 10;//buffer of 1000ft of vertical separation
		float alt = ((float) high + buffer) / 10; 
		alt = (int) Math.ceil(alt);
		sfl = (int) alt *10; //next flight level in 1000ft steps
		
		if(sfl > 410) {//if safe level ends up above RVSM, apply CVSM sep
			sfl += 10;
			
			if(sfl % 20 == 0) {//CVSM is only FL430, 450, 470, 490, 510, you get the gist
				sfl += 10;
			}
		}
		
		return sfl;
	}

	public int getSfl() {
		return sfl;
	}

	public void setSfl(int sfl) {
		this.sfl = sfl;
	}
	
	public String printSfl() {
		if(sfl < FuaManager.TRANSITION_LEVEL) {
			return "SFA"+sfl+"00FT";
		}
		return "SFL"+sfl;
	}
}
