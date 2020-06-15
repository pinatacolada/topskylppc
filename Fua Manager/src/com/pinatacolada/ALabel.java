package com.pinatacolada;

public class ALabel {
	public ALabel(FuaCoordinate c, String s) {
		loc=c;
		text=s;
	}
	private FuaCoordinate loc;
	private String text;
	
	public String printTopSky() {
		//LABEL:N039.35.64.000:W010.33.52.000:SFL360
		return "LABEL:"+loc.lat+":"+loc.lon+":"+text;
	}
}
