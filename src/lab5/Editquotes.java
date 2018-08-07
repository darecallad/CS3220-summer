package lab5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Editquotes
 */
@WebServlet("/Editquotes")
public class Editquotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private Quotation getEntry(Integer id) {
    		List<Quotation> quotes = (List<Quotation>) getServletContext().getAttribute("quotes");
    		
    		 for( Quotation quote : quotes )
    	            if( quote.getId()== id  ) return quote;

    	        return null;
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
		//List<Quotation> quotes = (List<Quotation>) getServletContext().getAttribute("quotes");
		Quotation quote = getEntry( id );
        

        //pass the entry to jsp using request scope
        request.setAttribute( "quotes", quote );
        request.getRequestDispatcher( "/WEB-INF/Editquotes.jsp" ).forward(request, response );
			
			
											 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the entry to be edited
        Integer id = Integer.valueOf( request.getParameter( "id" ) );
        Quotation quote =getEntry(id);

        // change the entry 
        quote.setText( request.getParameter( "text" ) );
        quote.setAuthor( request.getParameter( "author" ) );

        // send back to Admin
        response.sendRedirect( "Admin" );
	}

}
