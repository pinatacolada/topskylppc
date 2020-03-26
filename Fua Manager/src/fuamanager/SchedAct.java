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
	private int lower;
	private int upper;
	private String userText;
	
	public SchedAct(String[] parts) {
		//ACTIVE:190101:201231:12345:0700:1630
		rules = "";
		
		schedStartDate = ParseDate(parts[1],parts[4]);
		schedEndDate = ParseDate(parts[2],parts[5]);
		schedWeekdays = parts[3];
		
		if(parts.length > 6) {
			lower = Integer.parseInt(parts[6]);
			upper = Integer.parseInt(parts[7]);
			userText = parts[8];
		}
	}
	
	public LocalDateTime ParseDate(String day, String hour) {
		LocalDate date = null;
		LocalTime time = LocalTime.parse(hour, DateTimeFormatter.ofPattern("HHmm"));
		
		if(day.length() == 6){
			date = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyMMdd"));
		}
		else if(day.length() == 4){
			date = LocalDate.parse(day, DateTimeFormatter.ofPattern("MMdd"));
		}

		LocalDateTime dateTime = date.atTime(time);	
	
		return dateTime;
	}
}
