package com.pinatacolada;

public class NotamAct extends Activation {
	//ACTIVE:NOTAM:LPPC:R39A
	private String icao;
	private String text;
	
	public NotamAct(String fir, String keyword) {
		rules =  "NOTAM";
		icao = fir;
		text = keyword;		
	}
	
	public String printTopSky() {
		return "ACTIVE:"+rules+":"+icao+":"+text;
	}
	
}
