package fuamanager;

public class NotamAct extends Activation {
	//ACTIVE:NOTAM:LPPC:R39A
	private String icao;
	private String text;
	
	public NotamAct(String fir, String keyword) {
		// TODO Auto-generated constructor stub
		rules =  "NOTAM";
		icao = fir;
		text = keyword;		
	}
	
}
