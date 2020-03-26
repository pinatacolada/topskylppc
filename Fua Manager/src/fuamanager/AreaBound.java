package fuamanager;

public class AreaBound {
	public AreaBound(String lat, String lon, String rad) {
		// TODO Auto-generated constructor stub
		loc = new Coordinate(lat, lon);
		radius = Float.parseFloat(rad);
	}
	private Coordinate loc;
	private float radius;
}
