// file class for all the fils under folder


package finalproject;

import java.util.Date;

public class files {

	String id;
	String name;
	String parentID;
	String extension;  // separate file and extension name
	
	Date date;
	String dateString; // user friendly


public files(String id, String name, String parentID, String extension, String dateString ) {
	
	super();
	this.name = name;
	this.id = id;
	this.extension = extension;
	this.parentID = parentID;
	this.dateString = dateString;
	
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getExtension() {
	return extension;
}
public void setExtension(String extension) {
	this.extension = extension;
}
public String getParentID() {
	return parentID;
}
public void setParentID(String parentID) {
	this.parentID = parentID;
}
public String getDateString() {
	return dateString;
}


}