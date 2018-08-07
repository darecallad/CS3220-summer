package lab6;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class lab6
 */
@WebServlet("/lab6")
public class lab6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
		String text = request.getParameter("text");
		request.setAttribute("text", text);
		request.getRequestDispatcher( "/WEB-INF/lab6.jsp" ).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String text = request.getParameter("text");
		
		
		for(int i = 0; i < text.length(); i++) {
			
		}
		
		
	}

}
