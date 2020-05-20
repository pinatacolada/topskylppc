package fuamanager;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FuaXMLHandler extends DefaultHandler{

	private FuaXMLKml kml = new FuaXMLKml();
	private String elementValue;

	private static final String DOCUMENT = "Document";
	private static final String FOLDER = "Folder";
	private static final String PLACEMARK = "Placemark";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String COORDINATES = "coordinates";
	
	private Stack<FuaXMLDocument> docStack = new Stack<FuaXMLDocument>();
	private Stack<FuaXMLFolder> folStack = new Stack<FuaXMLFolder>();
	private Stack<FuaXMLPlacemark> plaStack = new Stack<FuaXMLPlacemark>();
	private Stack<ArrayList<FuaCoordinate>> coordStack = new Stack<ArrayList<FuaCoordinate>>();
	private Stack<Object> parent = new Stack<Object>();
	

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		elementValue += new String(ch, start, length);
	}

	@Override
    public void startDocument() throws SAXException {
    }
	
	@Override
	public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
		switch (qName) {
		case DOCUMENT:
			FuaXMLDocument doc = new FuaXMLDocument();
			kml.setDocument(doc);
			docStack.push(doc);
			parent.push(doc);
			break;
		case FOLDER:
			FuaXMLFolder f = new FuaXMLFolder();
			parent.push(f);
			folStack.push(f);
			break;
		case PLACEMARK:
			FuaXMLPlacemark p = new FuaXMLPlacemark();
			plaStack.push(p);
			parent.push(p);
			break;
		case COORDINATES:
			
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		Object o = parent.peek();
		FuaXMLDocument kmlDoc = kml.getDocument();

		switch (qName) {
		case NAME:
			elementValue = elementValue.replaceAll("^\\s+","");
			if(o instanceof FuaXMLDocument) {
				kmlDoc.setName(elementValue);
				break;
			}
			else if(o instanceof FuaXMLFolder) {
				FuaXMLFolder f = (FuaXMLFolder) o;
				f.setName(elementValue);
				
				kmlDoc.getFolders().add(f);
				break;
			}
			else if(o instanceof FuaXMLPlacemark) {
				FuaXMLPlacemark p = (FuaXMLPlacemark) o;
				p.setName(elementValue);
				break;
			}
		case DESCRIPTION:
			if(o instanceof FuaXMLDocument) {
				kmlDoc.setDescription(elementValue);
				break;
			}
			else if(o instanceof FuaXMLPlacemark) {
				FuaXMLPlacemark p = (FuaXMLPlacemark) o;
				p.setDescription(elementValue);
				FuaXMLFolder sFolder = folStack.peek();
				FuaXMLFolder kFolder = kmlDoc.getFolderByName(sFolder.getName());
				kFolder.addPlacemark(p);
				break;
			}
		case FOLDER:
			FuaXMLFolder kFolder = folStack.peek();
			kmlDoc.getFolders().add(kFolder);
			break;
		case COORDINATES:
			if(o instanceof FuaXMLPlacemark) {
				FuaXMLPlacemark p = (FuaXMLPlacemark) o;
				p.addCoordinate(elementValue);
				break;
			}
			break;
		}
		elementValue="";
	}

	public FuaXMLKml getKml() {
		return kml;
	}

	public void setKml(FuaXMLKml kml) {
		this.kml = kml;
	}
}
