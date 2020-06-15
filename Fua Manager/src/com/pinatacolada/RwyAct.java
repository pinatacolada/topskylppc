package com.pinatacolada;

import java.util.ArrayList;

public class RwyAct extends Activation {
	//ACTIVE:RWY:ARR:LPPT21,LPCS17,LPMT26:DEP:*
	private ArrayList<String> arrRwy = new ArrayList<String>();
	private ArrayList<String> depRwy = new ArrayList<String>();
	
	public RwyAct(String[] parts) {
		rules = "RWY";
		
		String[] arrsplit = parts[3].split("[\\,]");
		for(String s:arrsplit) {
			arrRwy.add(s);
		}
		 
		String[] depsplit = parts[4].split("[\\,]");
		for(String s:depsplit) {
			depRwy.add(s);
		}
		 
	}
	
}
