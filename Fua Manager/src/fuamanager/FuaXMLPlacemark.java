package fuamanager;

import java.time.LocalDateTime;

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
		
		
		if(bits[bits.length-1].contains("H24")) {
			start = SchedAct.ParseDate("0000");
			end = SchedAct.ParseDate("2359");
		}
		if(bits[bits.length-1].contains("NASCER")) {
			start = SchedAct.ParseDate("0800");
			end = SchedAct.ParseDate("1800");
		}
		else {
			start = SchedAct.ParseDate("1111");
			end = SchedAct.ParseDate("1337");
		}
		
		SchedAct sched = new SchedAct(start, end, "0", limits, "butts");
		System.out.println(sched.printActivation());
		return sched;
		
	}
}
