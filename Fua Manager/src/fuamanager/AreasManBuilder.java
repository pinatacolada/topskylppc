package fuamanager;

import java.time.LocalDateTime;

public class AreasManBuilder {

	//day in MMdd, hour in HHmm
	private static String addManualAreaSched(String areaName, String startDay, String startHour, String endDay, String endHour, int low, int high, String label) {
		// TODO Auto-generated method stub
		//LPD10:1400:1800:0:24000
		//LPD10:200317:200317:0:1400:1800:0:24000:SFL250
		
		/*
		switch(parts[1]) {
			case "1": active.add( new Activation() ); break;
			case "NOTAM": active.add( new NotamAct(parts[2],parts[3]) ); break;
			case "RWY": active.add( new RwyAct(parts) ); break;
			default: active.add( new SchedAct(parts) );
		}
		*/
		
		LocalDateTime start = SchedAct.ParseDate(startDay, startHour);
		LocalDateTime end = SchedAct.ParseDate(endDay, endHour);
		
		SchedAct activation = new SchedAct(parts);
		
		return areaName+":200317:200317"+":0:"+start+":"+end+":"+low+":"+high+":"+label;
	}
}
