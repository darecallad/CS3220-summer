package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Tvshowmain
 */
@WebServlet("/Tvshowmain")
public class Tvshowmain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ArrayList<lab4TVshowEntry> entries = new ArrayList<lab4TVshowEntry>();
		entries.add(new lab4TVshowEntry("Game of thrones", "game of thronesssssss!", "https://upload.wikimedia.org/wikipedia/en/thumb/d/d8/Game_of_Thrones_title_card.jpg/250px-Game_of_Thrones_title_card.jpg"));
		entries.add(new lab4TVshowEntry("Walking Dead", "walkingggggggg dead", "https://steamcdn-a.akamaihd.net/steam/apps/207610/header.jpg?t=1456281215"));
		entries.add(new lab4TVshowEntry("Breaking bad", "beakingggggg bad", "https://upload.wikimedia.org/wikipedia/en/6/61/Breaking_Bad_title_card.png"));
		
		ServletContext context = this.getServletContext();
		context.setAttribute("entries", entries);
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
				out.println("    <title>TV shows</title>");
				out.println("</head>");
				
				/* Page Body goes here */
				out.println("<body>");
				out.println("<div class=\"container\">");
				
				out.println("<div class=\"page-header\">");
				out.println("    <h1>TV shows</h1>");
				out.println("</div>");
				 
				//Guest table starts here
				out.println("<table class=\"table table-bordered table-striped table-hover\">");
				out.println("<tr>");
				out.println("  <th>Name</th><th>Description</th><th>Picture</th><th>Actions</th>");
				out.println("</tr>");
				
				ArrayList<lab4TVshowEntry> entries 
					= (ArrayList<lab4TVshowEntry>) getServletContext().getAttribute("entries");
				
				//Search Bar
				//out.println("<form class= \"form-inline\">");
				//out.println("<div class=\"form-group\">");
				//out.println("<input type=\"text\" class=\"form-control\" name= \"query\" placeholder=\"Search\">");
				//out.println("</div>");
				//out.println("<button type=\"submit\" class=\"btn btn-primary\">Search</button>");
				//out.println("</form>");
				
				
				if((request.getParameter("query") == null) || request.getParameter("query") == "")
				{
					for (lab4TVshowEntry entry : entries) {
						out.println("<tr>");
						out.println("  <td>" + entry.getName() + "</td>");
						out.println("  <td>" + entry.getDescription() + "</td>");
						out.println("  <td> <img src =\"" + entry.getUrl() + "\" width= \"270\" height= \"170\" </td>");
					
						
						out.println("	<td>");
			            out.println("		<a href=\"Vote?id=" + entry.getId() + "\">Vote</a>");
			           // out.println("		<a href=\"DeleteEntry?id=" + entry.getId() + "\">Delete</a>");
			            out.println("	</td>");
						out.println("</tr>");
					}
					
				}
				else
				{
					String query = request.getParameter("query");
					
						for(int i = 0; i < entries.size(); i++)
			        	{
			        		if(entries.get(i).getName().toLowerCase().contains( query.toLowerCase()) ||
			        				entries.get(i).getDescription().toLowerCase().contains(query.toLowerCase())||
			        					entries.get(i).getUrl().toLowerCase().contains( query.toLowerCase()))
			        		{
			        			out.println("<tr>");
			        			out.println("	<td>" + entries.get(i).getName() + "</td>");
			    	            out.println("	<td>" + entries.get(i).getDescription() + "</td>");
			    	            out.println("	<td> <img src =\"" + entries.get(i).getUrl() + "\" width= \"270\" height= \"170\" </td>");
			    	            
			    	            out.println("	<td>");
			    	            out.println("		<a href=\"Vote?id=" + entries.get(i).getId() + "\">Vote</a>");
			    	            //out.println("		<a href=\"DeleteEntry?id=" + entries.get(i).getId() + "\">Delete</a>");
			    	            out.println("	</td>");
			    	            out.println("</tr");
			        		}
			        	}
						
				}
			
				out.println("</table>");
				
				out.println("<a href=\"AddEntry\">Add a New Tvshows</a>");
				out.println("</div>");
//				
//				out.println("<form method=\"post\" action=\"upload\" enctype=\"multipart/form-data\" >\n" + 
//						"            File:\n" + 
//						"            <input type=\"file\" name=\"imageFilename\" id=\"file\" /> <br/>\n" + 
//						"            Destination:\n" + 
//						"            <input type=\"text\" value=\"/tmp\" name=\"destination\"/>\n" + 
//						"            </br>\n" + 
//						"            <input type=\"submit\" value=\"Upload\" name=\"upload\" id=\"upload\" />\n" + 
//						"        </form>\n"); 
//
				out.println("</body>");
				out.println("</html>");
				}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
//	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    response.setContentType("text/html;charset=UTF-8");
//
//	    // Create path components to save the file
//	    //final String path = request.getParameter("destination");
//	    final String path = getServletContext().getRealPath("/images");
//	    final Part filePart = request.getPart("imageFile");
//	    final String fileName = getFileName(filePart);
//
//	    OutputStream out = null;
//	    InputStream filecontent = null;
//	    final PrintWriter writer = response.getWriter();
//
//	    try {
//	        out = new FileOutputStream(new File(path + File.separator + fileName));
//	        filecontent = filePart.getInputStream();
//
//	        int read = 0;
//	        final byte[] bytes = new byte[1024];
//
//	        while ((read = filecontent.read(bytes)) != -1) {
//	            out.write(bytes, 0, read);
//	        }
//	        writer.println("New file " + fileName + " created at " + path);
//	        
//	    } catch (FileNotFoundException fne) {
//	        writer.println("You either did not specify a file to upload or are "
//	                + "trying to upload a file to a protected or nonexistent "
//	                + "location.");
//	        writer.println("<br/> ERROR: " + fne.getMessage());
//
//	        
//	    } finally {
//	        if (out != null) {
//	            out.close();
//	        }
//	        if (filecontent != null) {
//	            filecontent.close();
//	        }
//	        if (writer != null) {
//	            writer.close();
//	        }
//	    }
//	}
//
//	private String getFileName(final Part part) {
//	    final String partHeader = part.getHeader("content-disposition");
//	    for (String content : part.getHeader("content-disposition").split(";")) {
//	        if (content.trim().startsWith("filename")) {
//	            return content.substring(
//	                    content.indexOf('=') + 1).trim().replace("\"", "");
//	        }
//	    }
//	    return null;
//	}

}


