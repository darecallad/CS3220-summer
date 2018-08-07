package lab3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerProfile
 */
@WebServlet("/CustomerProfile")
public class CustomerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Let's get a reference to the student who is currently logged in
				Customer customer = (Customer) request.getSession().getAttribute("NowCustomer");
				
				// If there is no student logged in, redirect back to the login page
				if (customer == null) {
					response.sendRedirect("Register");
					return;
				}
				
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
				out.println("    <title>Customer Profile</title>");
				out.println("</head>");
				
				/* Page Body goes here */
				out.println("<body>");
				out.println("<div class=\"container\">");
				
				out.println("<div class=\"jumbotron\">");
				out.println("    <h1>" + customer.getFirstName() + "'s Profile</small></h1>");
				out.println("    <p class=\"lead\">This is a Customer Only area.</p>");
				out.println("	 <a class=\"btn btn-primary\" href=\"Register\">Go back to Register</a>");
				out.println("</div>");
				
				out.println("<h3>Hello Customer <small>" + customer.getFullName() +  customer.getEmail() + "</small></h3>");
				out.println("<table class=\"table table-bordered table-striped table-hover\">");
				out.println("	<tr>");
				out.println("		<th>Welcome</th>");
				//out.println("		<th>Score</th>");		
				out.println("	</tr>");
						
				// Print all of the student's scores.
				//double[] scores = customer.getScores();
				//for (int i = 0; i < scores.length; i++) {
				//	out.println("	<tr>");
				//	out.println("		<td>Assignment " + (i+1) + "</td>");
				//	out.println("		<td>" + scores[i] + "</td>");
				//	out.println("	</tr>");
				//}
				
				out.println("</table>");
				
				out.println("</div>");
				out.println("</body>");
				
				out.println("</html>");	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
