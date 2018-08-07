package lab5;

public class Quotation {

	static int count =0;
	
	// text and author
	String text,author;
	
	// quatation id, liked #, dislike #, viewed #
	int id;
	int liked;
	int disliked;
	int viewed;
	
	public Quotation(String text, String author) {
		
		super();
		// increase id count and set like dislike viewed as 0"
		this.id = count++;
		liked = disliked = viewed =0;
		
		this.text = text;
		this.author = author;
		
	}
public Quotation( int id, String text, String author) {
		
		super();
		this.text = text;
		this.author = author;
		this.id = id;
		
	}
	
	
	public int getId() {
		return id;
	}

//	public int getViewed() {
//		return viewed;
//	}
//	
//	public void incView() {
//		viewed++;
//	}
	
	public int getLiked() {
		return liked;
	}
	
	public void incLiked() {
		liked ++;
	}
	public int getDisliked() {
		return disliked;
	}
	public void incDisliked() {
		disliked ++ ;
	}
	
	public String getText() {
		return text;
	}
	public String getAuthor() {
		return author;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
