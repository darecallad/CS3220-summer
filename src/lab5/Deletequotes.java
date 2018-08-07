package lab5;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deletequotes
 */
@WebServlet("/Deletequotes")
public class Deletequotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Quotation> quotes =(ArrayList<Quotation>)getServletContext().getAttribute("quotes");
			
			int id = Integer.parseInt(request.getParameter("id"));
			for(Quotation quote : quotes) 
				if(quote.getId() == id) {
					quotes.remove(quote);
					break;
										}
			}catch(Exception e) {
				
								}finally {
											response.sendRedirect("Admin");
										 }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
