package fuamanager;

import java.util.ArrayList;

public class FuaXMLDocument {
	private String name;
	private String description;
	private ArrayList<FuaXMLFolder> folders;
	
	public FuaXMLDocument(String name, String description) {
		this.name = name;
		this.description = description;
		folders = new ArrayList<FuaXMLFolder>();
	}
	
	public FuaXMLDocument() {
		this.name = "";
		this.description = "";
		folders = new ArrayList<FuaXMLFolder>();
	}

	public ArrayList<FuaXMLFolder> getFolders() {
		return folders;
	}
	
	public void setFolders(ArrayList<FuaXMLFolder> folders) {
		this.folders = folders;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public FuaXMLFolder getFolderByName(String name) {
		for(FuaXMLFolder f : folders) {
			if(f.getName().contains(name)){
				return f;
			}
		}
		return null;
		
	}

}
