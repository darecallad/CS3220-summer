package lab4;

public class lab4TVshowEntry {

	int id;
	String name;
	String description;
	String url;
	
	static int count = 0;
	
	public lab4TVshowEntry(String name, String description, String url) {
		
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.id = count++;
	}
	
	public lab4TVshowEntry(int id, String name, String description, String url) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.id = id;
	}
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
}
