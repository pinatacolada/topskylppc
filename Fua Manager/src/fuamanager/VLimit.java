package fuamanager;

public class VLimit {
	
	private int high;
	private int low;
	
	public VLimit(int h, int l) {
		high=h;
		low=l;
	}
	
	public VLimit(String sLow, String sHigh){
		low = stringToAlt(sLow);
		high = stringToAlt(sHigh);
		
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
}
