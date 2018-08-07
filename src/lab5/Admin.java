package lab5;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Quotation> quotes =(ArrayList<Quotation>)getServletContext().getAttribute("quotes");
		getServletContext().setAttribute("quotes",quotes);
		RequestDispatcher dispather = request.getRequestDispatcher("WEB-INF/Admin.jsp");
		dispather.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String text =request.getParameter("text");
		String author = request.getParameter("author");
		
		((ArrayList<Quotation>)getServletContext().getAttribute("quotes")).add(new Quotation(text,author));
		doGet(request, response);
	}

}
