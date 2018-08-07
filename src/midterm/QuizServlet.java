package midterm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private Object name;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	HttpSession session = request.getSession();
	//getServletContext().setAttribute( "name", name );
	//Integer counter = (Integer) servletContext.getAttribute( "counter" );
	
	
	boolean hasError=false;
	
	//int error = (int) request.getAttribute("error" );
	
	//if (error==1) hasError=true;
	
	

	
	String name = (String)request.getParameter("name");
	if (name == "") {
		name = "friend";
	}
	//getServletContext().setAttribute( "name", name );
	Quiz quiz = (Quiz) session.getAttribute("quiz");
	
	// if didnt find -> create new quiz
	
	if(quiz == null) {
		quiz = new Quiz();
		
		session.setAttribute("quiz", quiz);
	}
	
	//set COntexttype to html
			response.setContentType("text/html");
			//get a print writer
			PrintWriter out = response.getWriter();
			// template HTML
			out.println("<!DOCTYPE html>");		
			out.println("<html lang=\"en\">");
			out.println("<head>");
			out.println("    <meta charset=\"UTF-8\">");
			out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			out.println("        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
			out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
			out.println("        <title>Math Quiz</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"container\">");
			out.println("<P> hi "+ name+ "</p>");
			
		
			
			// Add page-specific content here
			if(!quiz.isComplete()) {
				
				int c = (int)(Math.random() * 10);
				while(c >3) {
					c = (int)(Math.random() * 10);
				}
				Mathquestion question = quiz.getCurrentQuestion();
				switch(c) {
				
				case 0 :
					
					out.println("<form action =\"QuizServlet\" method =\"post\">");
					out.println("<h1>" + question.getNo1() + " + " + question.getNo2() + " = </h1>");
					out.println("<input type =\"text\" name=\"answer\" placeholder=\"Enter your answer\">");
					out.println("<input type =\"submit\" value=\"Submit Answer\">");
					out.println("<input type =\"hidden\" name=\"name\" value="+name+">");
					out.println("<input type =\"hidden\" name=\"op\" value=\"1\">");
					
					out.println("</form>");
					
					break;
				case 1 :
					
					out.println("<form action =\"QuizServlet\" method =\"post\">");
					out.println("<h1>" + question.getNo1() + " - " + question.getNo2() + " = </h1>");
					out.println("<input type =\"text\" name=\"answer\" placeholder=\"Enter your answer\">");
					out.println("<input type =\"submit\" value=\"Submit Answer\">");
					out.println("<input type =\"hidden\" name= \"name\" value="+name+">");
					out.println("<input type =\"hidden\" name =\"op\" value=\"2\">");
					out.println("</form>");
					
					break;
				case 2 :
					out.println("<form action =\"QuizServlet\" method =\"post\">");
					out.println("<h1>" + question.getNo1() + " * " + question.getNo2() + " = </h1>");
					out.println("<input type =\"text\" name=\"answer\" placeholder=\"Enter your answer\">");
					out.println("<input type =\"submit\" value=\"Submit Answer\">");
					out.println("<input type =\"hidden\" name= \"name\" value="+name+">");
					out.println("<input type =\"hidden\" name =\"op\" value=\"3\">");
					out.println("</form>");
					
					break;
				case 3 :
					out.println("<form action =\"QuizServlet\" method =\"post\">");
					out.println("<h1>" + question.getNo1() + " / " + question.getNo2() + " = (integar) only for example 4/3 = 1 </h1>");
					out.println("<input type =\"text\" name=\"answer\" placeholder=\"Enter your answer\">");
					out.println("<input type =\"submit\" value=\"Submit Answer\">");
					out.println("<input type =\"hidden\" name= \"name\" value="+ name +">");
					out.println("<input type =\"hidden\" name =\"op\" value=\"4\">");
					out.println("</form>");
					
					break;
				}
//				Mathquestion question = quiz.getCurrentQuestion();
//				
//				out.println("<form action =\"QuizServlet\" method =\"post\">");
//				out.println("<h1>" + question.getNo1() + " + " + question.getNo2() + " = </h1>");
//				out.println("<input type =\"text\" name=\"answer\" placeholder=\"Enter your answer\">");
//				out.println("<input type =\"submit\" value=\"Submit Answer\">");
//				out.println("</form>");
			}
			
			
			
			else {
				out.println("<p> you answered " + quiz.getNumberCorrect() + " out of 4 questions correctly.");
				
				session.removeAttribute("quiz");
				
				out.println("<p> <a href =\"midtermmain\"> Click to new quiz </a></p>");
			}
			
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean hasError = false;
		
		
		
		String answer =request.getParameter("answer");
		int operation = Integer.parseInt(request.getParameter("op"));
		
		Quiz quiz =(Quiz) request.getSession().getAttribute("quiz");
		Mathquestion question = quiz.getCurrentQuestion();
		
		String name = (String)request.getParameter("name"); 
		//String name = (String)request.getAttribute("name" );
		
		//set COntexttype to html
		response.setContentType("text/html");
		//get a print writer
		PrintWriter out = response.getWriter();
		// template HTML
		out.println("<!DOCTYPE html>");		
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
		out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
		out.println("        <title>Math Quiz</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		
		
		
		try {
			hasError=false;
			int ans = Integer.parseInt(answer);
		}catch(NumberFormatException e) {
			hasError=true;
			
			if (hasError==true) out.println("<P> wrong input </p>");
			
			out.println("<a href=\"QuizServlet?name="+ name +"\"> Next Question </a>");
			
			//quiz.answerQuestion(answer);
			
			
			if (quiz.isComplete()) {
				out.println("<a href=\"QuizServlet?name="+ name +"\"> view Quiz Result</a>");
				}
			else {
				out.println("<a href=\"QuizServlet?name="+ name +"\"> Next one </a>");
			}
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}
		
		
		
		
		// 
		
		int ans = Integer.parseInt(answer);
		
	if(operation== 1 ) {
			if(ans ==question.getAddAnswer()){
				out.println("<p class =\"text-success\"> Correct </p>");
				}
			else {
				out.println("<p class=\"text-danger\">Incorrect</p>");
			}
			}
		else if(operation == 2) {
			if (ans ==question.getSubAnswer()) {
				out.println("<p class =\"text-success\"> Correct </p>");
				}
			else {
				out.println("<p class=\"text-danger\">Incorrect</p>");
			}
		}
		
		else if(operation == 3) {
			if (ans ==question.getMulAnswer()) {
				out.println("<p class =\"text-success\"> Correct </p>");
				}
			else {
				out.println("<p class=\"text-danger\">Incorrect</p>");
			}
		}
		
		else if(operation == 4) {
			if (ans ==question.getDivAnswer()) {
				out.println("<p class =\"text-success\"> Correct </p>");
				}
			else {
				out.println("<p class=\"text-danger\">Incorrect</p>");
			}
		}
		
		
		
//		// Add page-specific content here
//		if (answer ==question.getAddAnswer()) {
//			out.println("<p class =\"text-success\"> Correct </p>");
//			}
//		else if (answer ==question.getSubAnswer()) {
//			out.println("<p class =\"text-success\"> Correct </p>");
//			}
//		else if (answer ==question.getMulAnswer()) {
//			out.println("<p class =\"text-success\"> Correct </p>");
//			}
//		else if (answer ==question.getDivAnswer()) {
//			out.println("<p class =\"text-success\"> Correct </p>");
//			}
//		else {
//			out.println("<p class=\"text-danger\">Incorrect</p>");
//		}
		
		
		
		
		out.println("<a href=\"QuizServlet?name="+ name +"\"> Next Question </a>");
		
		quiz.answerQuestion(ans);
		
		if (quiz.isComplete()) {
			out.println("<a href=\"QuizServlet?name="+ name +"\"> view Quiz Result</a>");
			}
		else {
			out.println("<a href=\"QuizServlet?name="+ name +"\"> Next one </a>");
		}
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
