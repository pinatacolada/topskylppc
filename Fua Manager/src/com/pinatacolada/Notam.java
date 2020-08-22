package com.pinatacolada;

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
		System.out.println("faulty "+raw);
		
		setId(raw.substring(0, 8));

		opType = "NOTAM"+raw.substring(14, 15);

		if(!opType.equals("N")){
			prevNotam = raw.substring(16, 24);
		}

		String qLine = raw.substring(raw.indexOf("Q) ") + 3, raw.indexOf("A) "));
		String aLine = raw.substring(raw.indexOf("A) ") + 3, raw.indexOf("B) "));
		String bLine = raw.substring(raw.indexOf("B) ") + 3, raw.indexOf("B) ") + 3 + 10);
		String cLine = raw.substring(raw.indexOf("C) ") + 3, raw.indexOf("C) ") + 3 + 10);
		String dLine = null;
		if(raw.contains("\\nD) ")){
			dLine = raw.substring(raw.indexOf("\\nD) ") + 3, raw.indexOf("\\nE) "));
		}
		String eLine = null;
		if(raw.contains("\\nE) ")){
			if(raw.contains("\\nF) ")) {
				eLine = raw.substring(raw.indexOf("\\nE) ") + 3, raw.indexOf("\\nF) "));
			}
			else {
				eLine = raw.substring(raw.indexOf("\\nE) ") + 3, raw.indexOf("CREATED: "));
			}
		}
		String fLine = null;
		if(raw.contains("\\nF) ")){
			fLine = raw.substring(raw.indexOf("\\nF) ") + 3, raw.indexOf("G) "));
		}
		String gLine = null;
		if(raw.contains(" G) ")) {
			gLine = raw.substring(raw.indexOf("G) ") + 4, raw.indexOf("CREATED: "));
		}

		String[] qSplit = qLine.split("/");

		setFir(qSplit[0]);
		setqCode(qSplit[1]);
		traffic = qSplit[2];
		purpose = qSplit[3];
		scope = qSplit[4];

		setLimits(new VLimit(Integer.parseInt(qSplit[6]), Integer.parseInt(qSplit[5])));

		String rawCoord = qSplit[7].substring(0, 11);

		setCoord(new FuaCoordinate(FuaCoordinate.icaoToFuaCoordinate(rawCoord)));
		
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
		
		String qLine = "Q) "+getFir()+"/"+getqCode()+"/"+traffic+"/"+purpose+"/"+scope+"/"+getLimits().printNotamVLString()+"/"+getCoord().printNotam()+String.format("%03d", radius);
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
		
		String result = getId()+" "+opType+"\n"
				+qLine+"\n"
				+aLine+" "+bLine+" "+cLine+"\n"
				+dLine
				+eLine
				+fLine+gLine
				+created;

		return result;
			
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getqCode() {
		return qCode;
	}

	public void setqCode(String qCode) {
		this.qCode = qCode;
	}

	public VLimit getLimits() {
		return limits;
	}

	public void setLimits(VLimit limits) {
		this.limits = limits;
	}

	public String getFir() {
		return fir;
	}

	public void setFir(String fir) {
		this.fir = fir;
	}

	public FuaCoordinate getCoord() {
		return coord;
	}

	public void setCoord(FuaCoordinate coord) {
		this.coord = coord;
	}



}


