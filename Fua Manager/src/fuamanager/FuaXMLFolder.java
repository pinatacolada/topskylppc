package fuamanager;

import java.util.ArrayList;

public class FuaXMLFolder {
	private String name;
	private ArrayList<FuaXMLPlacemark> placemarks = new ArrayList<FuaXMLPlacemark>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<FuaXMLPlacemark> getPlacemarks() {
		return placemarks;
	}

	public void setPlacemarks(ArrayList<FuaXMLPlacemark> placemarks) {
		this.placemarks = placemarks;
	}
	
	public void addPlacemark(FuaXMLPlacemark p) {
		placemarks.add(p);
	}
}
