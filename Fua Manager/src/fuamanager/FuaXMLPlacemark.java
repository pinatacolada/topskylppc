package fuamanager;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuaXMLPlacemark {
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public SchedAct toSchedAct() {
		System.out.println(name);
		LocalDateTime start = null;
		LocalDateTime end = null;
		VLimit limits = null;
		
		String[] bits = description.split("<br>");
		//RESERVA ESPAÇO AÉREO - DRONES UAV

//		500ft/3,500ft AMSL
//		RESERVADA 16 MARÇO até 31 MAIO 2020
//		SEGUNDA FEIRA - SEXTA FEIRA do NASCER DO SOL até 23:50

//		NOTAM A0835/20
		for(String s : bits) {
			if(s.contains("/")) {
				String[] stringLimits = s.split("/");
				String low = stringLimits[0];
				String high = stringLimits[1];
				
				limits = new VLimit(low, high);
				
			}
		}

		System.out.println(bits[bits.length - 1]);
		
		if(bits[bits.length-1].contains("H24")) {
			start = SchedAct.ParseDate("0000");
			end = SchedAct.ParseDate("2359");
		}
		if(bits[bits.length-1].contains("NASCER")) {
			start = SchedAct.ParseDate("0800");
			end = SchedAct.ParseDate("1800");
		}
		if(bits[bits.length-1].contains("UTC")) {
			String schedule = bits[bits.length-1];
			//REGEX: \d\d[:]\d\d[-]\d\d[:]\d\d
			
			System.out.println(schedule);
			

			Pattern pattern = Pattern.compile("\\d\\d[:]\\d\\d[-]\\d\\d[:]\\d\\d");
			Matcher matcher = pattern.matcher(schedule);
			if (matcher.find())
			{
			    System.out.println(matcher.group(0));
			}
			
			String[] sTimes = matcher.group(0).split("-");
			String sStart = sTimes[0].replace(":", "");
			String sEnd = sTimes[1].replace(":", "");
			
			start = SchedAct.ParseDate(sStart);
			end = SchedAct.ParseDate(sEnd);
		}
		
		SchedAct sched = new SchedAct(start, end, "0", limits, "butts");
		System.out.println(sched.printActivation());
		return sched;
		
	}
}
