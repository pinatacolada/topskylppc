package fuamanager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SchedAct extends Activation {
	//ACTIVE:SchedStartDate:SchedEndDate:SchedWeekdays:StartTime:EndTime[:Lower:Upper:UserText]
	private LocalDateTime schedStartDate;
	private LocalDateTime schedEndDate;
	private String schedWeekdays;
	private VLimit limits;
	private String userText;
	
	public SchedAct(String[] parts) {
		//ACTIVE:190101:201231:12345:0700:1630
		rules = "";
		
		schedStartDate = ParseDate(parts[1],parts[4]);
		schedEndDate = ParseDate(parts[2],parts[5]);
		schedWeekdays = parts[3];
		
		if(parts.length > 6) {
			setLimits(new VLimit(Integer.parseInt(parts[7]), Integer.parseInt(parts[6])));
			setUserText(parts[8]);
		}
	}
	
	public SchedAct(LocalDateTime schedStartDate, LocalDateTime schedEndDate, String schedWeekdays, VLimit limits, String userText) {
		rules = "";
		
		this.schedStartDate=schedStartDate;
		this.schedEndDate=schedEndDate;
		this.schedWeekdays=schedWeekdays;
		this.setLimits(limits);
		this.setUserText(userText);
		
		
	}
	
	/**
	 * Creates a LocalDateTime object based on parameters given
	 * @param day In either "yyMMdd" or "MMdd" formats. Current year is assumed if "MMdd" used
	 * @param hour "HHmm" format
	 * @return LocalDateTime object
	 */
	public static LocalDateTime ParseDate(String day, String hour) {
		LocalDate date = null;
		LocalTime time = LocalTime.parse(hour, DateTimeFormatter.ofPattern("HHmm"));
		
		if(day.length() == 6){
			date = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyMMdd"));
		}
		else if(day.length() == 4){
			LocalDate currYear = LocalDate.now();
			
			String sYear = Integer.toString(currYear.getYear()).substring(2);
			date = LocalDate.parse(sYear+day, DateTimeFormatter.ofPattern("yyMMdd"));
		}
		else if(day.isBlank()){
			date = LocalDate.now();
		}

		LocalDateTime dateTime = date.atTime(time);	
	
		return dateTime;
	}
	
	/**
	 * Creates a LocalDateTime object based only on hours. Current day is assumed.
	 * @param hour "HHmm" format
	 * @return LocalDateTime object
	 */
	public static LocalDateTime ParseDate(String hour) {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.parse(hour, DateTimeFormatter.ofPattern("HHmm"));

		LocalDateTime dateTime = date.atTime(time);	
	
		return dateTime;
	}

	public String printActivation() {
		//200317:200317:0:1100:1700
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyMMdd");
		DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HHmm");
		
		return schedStartDate.format(dateFormat)+":"+schedEndDate.format(dateFormat)+":"+schedWeekdays+":"+schedStartDate.format(hourFormat)+":"+schedEndDate.format(hourFormat);
	}

	public VLimit getLimits() {
		return limits;
	}

	public void setLimits(VLimit limits) {
		this.limits = limits;
	}

	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}
}
