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

					limits = new VLimit(low, high);

				}
			}
		}



		if(bits[bits.length-1].contains("H24")) {
			start = SchedAct.ParseDate("0000");
			end = SchedAct.ParseDate("2359");
		}
		if(bits[bits.length-1].contains("NASCER")) {
			start = SchedAct.ParseDate("0800");
			end = SchedAct.ParseDate("1800");
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
				int sfl = limits.calculateSFL();
				
				if(name.contains("LPR42BAMC")) {
					sfl = 40;
				}
				else if(name.contains("LPR51B")) {
					sfl = 100;
				}

				SchedAct sched = new SchedAct(start, end, "0", limits, Integer.toString(sfl));
				schedules.add(sched);
			}   

		}

		System.out.println(name+description);
		return schedules;

	}
}
