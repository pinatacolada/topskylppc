package fuamanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//A1455/20 NOTAMN
//Q) LPPC/QRALW/IV/NBO/W /000/010/3827N00849W003
//A) LPPC 
//B) 2005211030 
//C) 2005211210
//E) AIRSPACE RESERVATION FOR SAR TRAINING WITH A CIRCUIT FOR SMOKE AND MARINE LOCATION MARKERS LAUNCHING 
//   WILL TAKE PLACE WI RADIUS 3NM CENTERED ON 3827N 00849W.
//F) SFC 
//G) 1000FT AMSL
//CREATED: 19 May 2020 20:07:00 SOURCE: EUECYIYN

public class Notam {
	private String id;
	private String opType;
	private String prevNotam = null;

	private String fir;
	private String qCode;
	private String traffic;
	private String purpose;
	private String scope;
	private VLimit limits;
	private FuaCoordinate coord;
	private int radius;

	private String location;

	private LocalDateTime start;

	private LocalDateTime end;

	private String schedule;

	private String description;

	private String fLower;
	private String gUpper;

	private String created;

	public Notam(String raw) {
		id = raw.substring(0, 8);

		opType = "NOTAM"+raw.substring(14, 15);

		if(!opType.equals("N")){
			prevNotam = raw.substring(16, 24);
		}

		String qLine = raw.substring(raw.indexOf("Q) ") + 3, raw.indexOf("A) "));
		String aLine = raw.substring(raw.indexOf("A) ") + 3, raw.indexOf("B) "));
		String bLine = raw.substring(raw.indexOf("B) ") + 3, raw.indexOf("B) ") + 3 + 10);
		String cLine = raw.substring(raw.indexOf("C) ") + 3, raw.indexOf("C) ") + 3 + 10);
		String dLine = null;
		if(raw.contains("D) ")){
			dLine = raw.substring(raw.indexOf("D) ") + 3, raw.indexOf("E) "));
		}
		String eLine = null;
		if(raw.contains("E) ")){
			if(raw.contains("F) ")) {
				eLine = raw.substring(raw.indexOf("E) ") + 3, raw.indexOf("F) "));
			}
			else {
				eLine = raw.substring(raw.indexOf("E) ") + 3, raw.indexOf("CREATED: "));
			}
		}
		String fLine = null;
		if(raw.contains("F) ")){
			fLine = raw.substring(raw.indexOf("F) ") + 3, raw.indexOf("G) "));
		}
		String gLine = null;
		if(raw.contains("G) ")) {
			gLine = raw.substring(raw.indexOf("G) ") + 3, raw.indexOf("CREATED: "));
		}

		String[] qSplit = qLine.split("/");

		fir = qSplit[0];
		qCode = qSplit[1];
		traffic = qSplit[2];
		purpose = qSplit[3];
		scope = qSplit[4];

		limits = new VLimit(Integer.parseInt(qSplit[6]), Integer.parseInt(qSplit[5]));

		String rawCoord = qSplit[7].substring(0, 11);

		coord = new FuaCoordinate(FuaCoordinate.icaoToFuaCoordinate(rawCoord));
		
		radius = Integer.parseInt(qSplit[7].substring(11, 14));

		location = aLine.trim();

		start = LocalDateTime.parse(bLine, DateTimeFormatter.ofPattern("yyMMddHHmm"));
		end = LocalDateTime.parse(cLine, DateTimeFormatter.ofPattern("yyMMddHHmm"));

		schedule = dLine;

		description = eLine;

		fLower = fLine;
		gUpper = gLine;

		created = raw.substring(raw.indexOf("CREATED: "));
	}
	
	public String printNotam() {
		
		String qLine = "Q) "+fir+"/"+qCode+"/"+traffic+"/"+purpose+"/"+scope+"/"+limits.printNotamVLString()+"/"+coord.printNotam()+String.format("%03d", radius);
		String aLine = "A) "+location;
		String bLine = "B) "+start.format(DateTimeFormatter.ofPattern("yyMMddHHmm"));
		String cLine = "C) "+end.format(DateTimeFormatter.ofPattern("yyMMddHHmm"));
		String dLine = "";
		if(schedule != null) {
			dLine = "D) "+schedule;
		}
		String eLine = "E) "+description;
		String fLine = "";
		if(fLower != null) {
			fLine = "F) "+fLower;
		}
		String gLine = "";
		if(gUpper != null) {
			gLine = "G) "+gUpper;
		}
		
		String result = id+" "+opType+"\n"
				+qLine+"\n"
				+aLine+" "+bLine+" "+cLine+"\n"
				+dLine
				+eLine
				+fLine+gLine+"\n"
				+created;

		return result;
			
	}



}


