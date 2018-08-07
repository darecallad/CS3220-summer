package midterm;

public class Mathquestion {

	int no1,no2;
	
	public Mathquestion() {
		
		no1 = (int)(Math.random() * 10 +1);
		no2 = (int)(Math.random() * 10 +1);
	}
	
	public int getNo1() {
		return no1;
	}
	
	public int getNo2() {
		return no2;
	}
	
	public int getAddAnswer() {
		return no1 + no2;
	}
	public int getSubAnswer() {
		return no1 - no2;
	}
	public int getMulAnswer() {
		return no1 * no2;
	}
	public int getDivAnswer() {
		return (int)(no1/no2);
	}
}
