package fuamanager;

import java.time.LocalDateTime;

public class FuaArea{
	private Area area;
	private SchedAct activation;
	private VLimit limits;
	private String label;

	/**
	 * FuaAreas are used to define a daily schedule, allowing for implementation of FUA, Flexible Use Airspace, level 2 (https://www.skybrary.aero/index.php/Flexible_Use_of_Airspace)
	 * @param area Area concerned, must be an already existing area.
	 * @param startDay format MMdd
	 * @param startHour format HHmm
	 * @param endDay format MMdd
	 * @param endHour format HHmm
	 * @param weekdays Days of the week for the activation. Use DAILY to activate continuously from StartTime on SchedStartDate to EndTime on SchedEndDate. Alternatively, using MONDAY+THRUSDAY+FRIDAY will activate on Mondays, Thursdays and Fridays
	 * @param low Lower limit
	 * @param high Higher limit
	 * @param label Text to display over the area. Usually contains SFL (Safe Flight Level)
	 */
	public FuaArea(Area area, String startDay, String startHour, String endDay, String endHour, String weekdays, int low, int high, String label) {
		LocalDateTime start = SchedAct.ParseDate(startDay, startHour);
		LocalDateTime end = SchedAct.ParseDate(endDay, endHour);

		this.setArea(area);
		activation = new SchedAct(start, end, weekdays, low, high, label);
		limits = new VLimit(high, low);
		this.label = label;
	}
	
	public FuaArea(Area area, String startHour, String endHour, int low, int high, String label) {
		LocalDateTime start = SchedAct.ParseDate(startHour);
		LocalDateTime end = SchedAct.ParseDate(endHour);;
		String weekdays = FuaManager.DAILY;
		this.setArea(area);
		
		activation = new SchedAct(start, end, weekdays, low, high, label);
		limits = new VLimit(high, low);
		this.label = label;
	}
	
	public String printFuaArea() {
		//LPD66:200317:200317:0:1100:1700:0:5000:SFL060
		return area.getName()+":"+activation.printActivation()+":"+limits.printVLimit()+":"+label;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
}
