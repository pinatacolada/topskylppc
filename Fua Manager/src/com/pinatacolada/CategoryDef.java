package com.pinatacolada;

public class CategoryDef {
	public CategoryDef(String line) {
		// TODO Auto-generated constructor stub
		//CATEGORYDEF:R:7:7:0:6:6:0:0:0:1:0:0
		
		String[] parts = line.split("[\\:]");
		name=parts[1];
		ActBorderColor=parts[2];
		ActFillColor=parts[3];
		ActFillPattern=parts[4];
		PreBorderColor=parts[5];
		PreFillColor=parts[6];
		PreFillPattern=parts[7];
		LabelName=parts[8];
		LabelMapText=parts[9];
		LabelUserText=parts[10];
		LabelLevels=parts[11];
		LabelTimes=parts[12];
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActBorderColor() {
		return ActBorderColor;
	}
	public void setActBorderColor(String actBorderColor) {
		ActBorderColor = actBorderColor;
	}
	public String getActFillPattern() {
		return ActFillPattern;
	}
	public void setActFillPattern(String actFillPattern) {
		ActFillPattern = actFillPattern;
	}
	public String getActFillColor() {
		return ActFillColor;
	}
	public void setActFillColor(String actFillColor) {
		ActFillColor = actFillColor;
	}
	public String getPreBorderColor() {
		return PreBorderColor;
	}
	public void setPreBorderColor(String preBorderColor) {
		PreBorderColor = preBorderColor;
	}
	public String getPreFillColor() {
		return PreFillColor;
	}
	public void setPreFillColor(String preFillColor) {
		PreFillColor = preFillColor;
	}
	public String getPreFillPattern() {
		return PreFillPattern;
	}
	public void setPreFillPattern(String preFillPattern) {
		PreFillPattern = preFillPattern;
	}
	public String getLabelName() {
		return LabelName;
	}
	public void setLabelName(String labelName) {
		LabelName = labelName;
	}
	public String getLabelMapText() {
		return LabelMapText;
	}
	public void setLabelMapText(String labelMapText) {
		LabelMapText = labelMapText;
	}
	public String getLabelUserText() {
		return LabelUserText;
	}
	public void setLabelUserText(String labelUserText) {
		LabelUserText = labelUserText;
	}
	public String getLabelTimes() {
		return LabelTimes;
	}
	public void setLabelTimes(String labelTimes) {
		LabelTimes = labelTimes;
	}
	public String getLabelLevels() {
		return LabelLevels;
	}
	public void setLabelLevels(String labelLevels) {
		LabelLevels = labelLevels;
	}
	private String name;
	private String ActBorderColor;
	private String ActFillColor;
	private String ActFillPattern;
	private String PreBorderColor;
	private String PreFillColor;
	private String PreFillPattern;
	private String LabelName;
	private String LabelMapText;
	private String LabelUserText;
	private String LabelLevels;
	private String LabelTimes;

}
