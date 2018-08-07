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
 * Servlet implementation class midtermmain
 */
@WebServlet("/midtermmain")
public class midtermmain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//String name = "";
		//javax.servlet.ServletContext servletContext =  getServletContext();
		HttpSession session = request.getSession();
		
		int error=1;
		getServletContext().setAttribute( "error", error );
		
		//servletContext.setAttribute( "name", name );
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
		
					
			out.println("<form action =\"QuizServlet\" method =\"get\">");
			out.println("<h1> Enter name </h1>");
			out.println("<input type =\"text\" name=\"name\" placeholder=\"Enter your name\">");
			out.println("<input type =\"submit\" value=\"Submit \">");
			out.println("</form>");

		
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
