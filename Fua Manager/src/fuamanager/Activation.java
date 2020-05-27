package fuamanager;

public class Activation {
	protected String rules;

	public Activation() {
		rules = "1";
	}

	public String getRules() {
		return rules;
	}
	
	public String printTopSky() {
		return "ACTIVE:"+rules;
	}
}
