package midterm;

import java.util.ArrayList;

public class Quiz {

	private Mathquestion currentQuestion;
	private int correct;
	private int asked;
	private ArrayList<Mathquestion> questions;
	private String name = "friend";
	private Object nameisempty;

	public Quiz() {
		questions = new ArrayList<Mathquestion>();
		
		for(int i =0; i < 4; i++) {
			questions.add(new Mathquestion());
		}
		
		currentQuestion = questions.get(0);
		correct = 0;
		asked = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEmpty() {
		return nameisempty == null;
	}
	public int getNumberCorrect() {
		return correct;
	}
	
	public boolean isComplete() {
		return currentQuestion == null;
	}
	
	public void answerQuestion(int answer) {
		if(currentQuestion != null && currentQuestion.getAddAnswer() == answer) {
			correct++;
		}
		if(currentQuestion != null && currentQuestion.getSubAnswer() == answer) {
			correct++;
		}
		if(currentQuestion != null && currentQuestion.getMulAnswer() == answer) {
			correct++;
		}
		if(currentQuestion != null && currentQuestion.getDivAnswer() == answer) {
			correct++;
		}
		asked++;
		
		if(asked >=4)
			currentQuestion = null;
		else
		currentQuestion = questions.get(asked);
	}
	
	public Mathquestion getCurrentQuestion() {
		return currentQuestion;
	}
	public int getScore() {
		return correct;
	}
}
