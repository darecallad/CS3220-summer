package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AddEntry
 */
@WebServlet("/AddEntry")
public class AddEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
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
				out.println("    <title>Add TVshows</title>");
				out.println("</head>");
				
				/* Page Body goes here */
				out.println("<body>");
				out.println("<div class=\"container\">");
				
				out.println("<div class=\"page-header\">");
				out.println("    <h1>Add Tvshows</h1>");
				out.println("</div>");
				
				out.println("<form action=\"AddEntry\" method=\"post\">");
				out.println("Name: <input type=\"text\" name=\"name\"><br>");
				out.println("Description:");
				out.println("<textarea name=\"description\" rows=\"5\" cols=\"20\"></textarea><br>");
//				out.println("</div>");
				
				out.println("<form method=\"post\" action=\"upload\" enctype=\"multipart/form-data\" >\n" + 
						"            File:\n" + 
						"            <input type=\"file\" name=\"imageFilename\" id=\"file\" /> <br/>\n" + 
						"            Destination:\n" + 
						"            <input type=\"text\" value=\"/tmp\" name=\"destination\"/>\n" + 
						"            </br>\n" + 
						"            <input type=\"submit\" value=\"Upload\" name=\"upload\" id=\"upload\" />\n" + 
						"        </form>\n"); 

				
				out.println("<input type=\"submit\" name=\"addEntry\" value=\"Add Entry\">");
				out.println("</form>");
				
				out.println("</div>");
				out.println("</body>");
				
				out.println("</html>");
				}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
		
//		String name = request.getParameter("name");
//		String description = request.getParameter("description");
//		String url = request.getParameter("url");
//		
//		// Validate the input
//		if (name != null && name.trim().length() > 0 
//				&& description != null && description.trim().length() > 0 && url != null) {
//			
//			// Create a new entry
//			lab4TVshowEntry entry = new lab4TVshowEntry(name, description, url);
//			
//			ArrayList<lab4TVshowEntry> entries = (ArrayList<lab4TVshowEntry>) getServletContext().getAttribute("entries");
//			
//			// Add the new entry to our array list of entries
//			entries.add(entry);
//			
//			response.sendRedirect("Tvshowmain");
//		}
//		
//				
//		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");

	    // Create path components to save the file
	    //final String path = request.getParameter("destination");
	    final String path = getServletContext().getRealPath("/images");
	    final Part filePart = request.getPart("imageFile");
	    final String fileName = getFileName(filePart);

	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();

	    try {
	        out = new FileOutputStream(new File(path + File.separator + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        writer.println("New file " + fileName + " created at " + path);
	        
	    } catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());

	        
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }
	    
	    String name = request.getParameter("name");
		String description = request.getParameter("description");
		String url = request.getParameter("url");
		
		// Validate the input
		if (name != null && name.trim().length() > 0 
				&& description != null && description.trim().length() > 0 && url != null) {
			
			// Create a new entry
			lab4TVshowEntry entry = new lab4TVshowEntry(name, description, url);
			
			ArrayList<lab4TVshowEntry> entries = (ArrayList<lab4TVshowEntry>) getServletContext().getAttribute("entries");
			
			// Add the new entry to our array list of entries
			entries.add(entry);
			
			response.sendRedirect("Tvshowmain");
		}
		
				
		doGet(request, response);
	}

	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}	
}


