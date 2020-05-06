package fuamanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

	public ArrayList<SchedAct> toSchedAct() {
		LocalDateTime start = null;
		LocalDateTime end = null;
		VLimit limits = null;
		ArrayList<SchedAct> schedules = new ArrayList<SchedAct>();

		String[] bits = description.split("<br>");

		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		
		if(name.contains("LP-TRA54") || name.contains("LP-TRA55")) {
			String[] stringLimits = bits[2].split("/");
			String low = stringLimits[0];
			String high = stringLimits[1];
			
			limits = new VLimit(low, high);
			
			description = bits[3];
		}
		else {
			for(String s : bits) {
				if(s.contains("/")) {
					String[] stringLimits = s.split("/");
					String low = stringLimits[0];
					String high = stringLimits[1];

					limits = new VLimit(name, low, high);
					
					if(name.contains("LP-R42B")) {
						limits.setSfl(40);
					}
					else if(name.contains("LP-R51B")) {
						limits.setSfl(100);
					}
				}
			}
		}



		if(bits[bits.length-1].contains("H24")) {
			start = SchedAct.ParseDate("0000");
			end = SchedAct.ParseDate("2359");
		}
		if(bits[bits.length-1].contains("NASCER")) {
			LocalDateTime sr = Tools.getSunrise();
			LocalDateTime ss = Tools.getSunset();
			start = SchedAct.ParseDate(""+sr.getHour()+sr.getMinute());
			end = SchedAct.ParseDate(""+ss.getHour()+ss.getMinute());
		}
		if(description.contains("UTC")) {
			String schedule = description;
			//REGEX: \d\d[:]\d\d[-]\d\d[:]\d\d

			String[] sTimes;

			Matcher m = Pattern.compile("\\d\\d[:]\\d\\d[-]\\d\\d[:]\\d\\d").matcher(schedule);
			while (m.find()) {
				String match = m.group();

				match = match.replaceAll(":", "");
				sTimes = match.split("-");
				String sStart = sTimes[0];
				String sEnd = sTimes[1];
				start = SchedAct.ParseDate(sStart);
				end = SchedAct.ParseDate(sEnd);
			}   
		}

		SchedAct sched = new SchedAct(start, end, "0", limits, limits.printSfl());
		schedules.add(sched);
		
		System.out.println(name+description);
		return schedules;

	}
}
