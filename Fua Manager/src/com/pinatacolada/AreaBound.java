package com.pinatacolada;

public class AreaBound {
	public AreaBound(String lat, String lon, String rad) {
		// TODO Auto-generated constructor stub
		loc = new FuaCoordinate(lat, lon);
		radius = Float.parseFloat(rad);
	}
	private FuaCoordinate loc;
	private float radius;
}
