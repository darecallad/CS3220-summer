// folder class for all the folder lists;

package finalproject;

import java.util.Date;

public class folder {

	
	String id;
	String name;
	String parentID; // parant root ID
	
	Date date;
	String dateString; // user friendly 
	


public folder (String id, String name, String parentID, String dateString) {
	
	this.id = id;
	this.name = name;
	this.parentID = parentID;
	this.dateString = dateString;
}

public String getParentID() {
	return parentID;
}

public void setParentID(String parentID) {
	this.parentID = parentID;
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

// get date 

public String getDateString() {
	return dateString;
}

}