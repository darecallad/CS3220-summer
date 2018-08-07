package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Vote
 */
@WebServlet("/Vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected lab4TVshowEntry getEntry(int id) {
	   ArrayList<lab4TVshowEntry> entries = (ArrayList<lab4TVshowEntry>) getServletContext().getAttribute("entries");
	   
	   for(lab4TVshowEntry entry : entries) {
		   if (entry.getId() == id)
			   return entry;
	   }
	   return null;
   }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set the content type
				response.setContentType("text/html");
				
				// Get a reference to the PrintWriter that lets us talk to the client
				PrintWriter out = response.getWriter();
				
				// Generate the HTML
				out.println("<!DOCTYPE html>");
				out.println("<html lang=\"en\">");
				out.println("<head>");
				out.println("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
				out.println("    <meta charset=\"UTF-8\">");
				
				/* Page Title goes here */
				out.println("    <title>Vote Result</title>");
				out.println("</head>");
				
				/* Page Body goes here */
				out.println("<body>");
				out.println("<div class=\"container\">");
				
				out.println("<div class=\"page-header\">");
				out.println("    <h1>Vote Result</h1>");
				out.println("</div>");
				
				// Get the id of the entry we are editing
				int id = Integer.parseInt(request.getParameter("id"));
				lab4TVshowEntry entry = getEntry(id);
				
				if (entry == null) {
					response.sendRedirect("Tvshowmain");
					return;
				}
				
				out.println("<form action=\"EditEntry\" method=\"post\">");
				out.println("Name: <input type=\"text\" name=\"name\" value=\"" + entry.getName() + "\"><br>");		
				out.println("Message:");
				out.println("<textarea name=\"message\" rows=\"5\" cols=\"20\">" + entry.getDescription() + "</textarea><br>");
				out.println("<input type=\"hidden\" name=\"id\" value=\"" + id + "\">");
				out.println("<input type=\"submit\" name=\"saveEntry\" value=\"Save Entry\">");
				out.println("</form>");
				
				out.println("</div>");
				out.println("</body>");
				
				out.println("</html>");
				

			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String url = request.getParameter("url");
		
		lab4TVshowEntry entry = getEntry(id);
		
		entry.setName(name);
		entry.setDescription(description);
		entry.setUrl(url);
		
		response.sendRedirect("Tvshowmain");
	
	}

}
