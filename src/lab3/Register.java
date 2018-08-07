package lab3;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Customer getName(HttpServletRequest request)
    {
		ArrayList<Customer> customers = (ArrayList<Customer>) getServletContext().getAttribute("customers");
		
    	Cookie[] c = request.getCookies();
    	if(c != null)
    	
    		for(Cookie cookie: c)
    		{
    			for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getHashedId().equals(cookie.getValue()));
					return customers.get(i);
				}
    		}
    	return null;
    }
       
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Create a few students 
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			customers.add(new Customer("John"," Doe", "john@doe.com", "abcd"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			customers.add(new Customer("Mary"," Jane", "mary@jane.com", "efgh"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			customers.add(new Customer("Joe"," Boxer", "joe@boxer.com", "ijkl"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Add the students to the application scope (Servlet Context)
		getServletContext().setAttribute("customers", customers);
		
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
				out.println("    <title>Register</title>");
				out.println("</head>");
				
				/* Page Body goes here */
				out.println("<body>");
				out.println("<div class=\"container\">");
				
				out.println("<div class=\"page-header\">");
				out.println("    <h1>Register <small>HttpSessions</small></h1>");
				out.println("</div>");
				
				// Create the login form
				out.println("<form action=\"Register\" method=\"post\">");
				
				
				String nameError = (String) request.getAttribute("nameError");		
				if (nameError != null) {
					out.println("	<div class=\"form-group has-error\">");	
					out.println("		<span class=\"help-block\">" + nameError + "</span>");
				}
				else
					out.println("	<div class=\"form-group\">");	
				
				String name = request.getParameter("name");
				name = name == null ? "" : name;
				
				out.println("		<label class=\"control-label\">Full Name</label>");
				out.println("		<input class=\"form-control\"type=\"text\" value=\"" + name + "\" name=\"name\" placeholder=\"First and Last Names\">");
				out.println("	</div>");
				
				
				
				String usernameError = (String) request.getAttribute("usernameError");		
				if (usernameError != null) {
					out.println("	<div class=\"form-group has-error\">");	
					out.println("		<span class=\"help-block\">" + usernameError + "</span>");
				}
				else
					out.println("	<div class=\"form-group\">");	
				
				String username = request.getParameter("username");
				username = username == null ? "" : username;
				
				out.println("		<label class=\"control-label\">Username (E-mail Address)</label>");
				out.println("		<input class=\"form-control\"type=\"text\" value=\"" + username + "\" name=\"username\" placeholder=\"Email\">");
				out.println("	</div>");
				
				
				
				String passwordError = (String) request.getAttribute("passwordError");		
				if (passwordError != null) {
					out.println("	<div class=\"form-group has-error\">");			
					out.println("		<span class=\"help-block\">" + passwordError + "</span>");
				}
				else {
					out.println("	<div class=\"form-group\">");
				}
				
				String password1 = request.getParameter("password1");
				password1 = password1 == null ? "" : password1;
				out.println("		<label class=\"control-label\">Password</label>");
				out.println("		<input class=\"form-control\"type=\"password\" value=\"" + password1 + "\" name=\"password1\" placeholder=\"Password\">");
				
				String password2 = request.getParameter("password2");
				password2 = password2 == null ? "" : password2;
				out.println("		<label class=\"control-label\">Re-Enter Password</label>");
				out.println("		<input class=\"form-control\"type=\"password\" value=\"" + password2 + "\" name=\"password2\" placeholder=\"Re-Enter Password\">");
				out.println("	</div>");
				
				out.println("	<button type=\"submit\" class=\"btn btn-primary\">Register</button>");
				out.println("</form>");		
				out.println("</div>");
				out.println("</body>");
				
				out.println("</html>");
			}


			boolean usernameInUse(String username) {
				ArrayList<Customer> customers = (ArrayList<Customer>) getServletContext().getAttribute("customers");
				for(Customer customer : customers) {
					if (customer.getEmail().toLowerCase().matches(username.toLowerCase()))
						return true;
				}
				return false;
				}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fullName = request.getParameter("name");
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		String first = null, last = null;
		
		boolean hasError = false;
		
		// Validate the name
		if (fullName == null || !fullName.matches("[a-zA-Z]{2,} [a-zA-Z]{2,}") || !fullName.matches("[a-zA-Z]{2,} [a-zA-Z]{2,} [a-zA-Z]{2,}")) {
			// !fullName.matches("[a-zA-Z]{2,} [a-zA-Z]{2,}")
			request.setAttribute("nameError", "You must enter your full name");
			hasError = true;
		}
		else {
			String[] names = fullName.split(" +");
			first = names[0];
			last = names[1];
		}
		
		// Validate the username
		if (username == null || username.trim().length() == 0) {
			request.setAttribute("usernameError", "You must specify a Username");
			hasError = true;
		}
		else if (usernameInUse(username)) {
			request.setAttribute("usernameError", "That username is already in use.");
			hasError = true;
		}
		
		// Validate the password
		if (password1 == null || password1.trim().length() == 0) {
			request.setAttribute("passwordError", "You must specify a password");
			hasError = true;
		}
		else if (password2 == null || !password1.equals(password2)) {
			request.setAttribute("passwordError", "The password dont match");
			hasError = true;
		}
			
		if (hasError) {
			doGet(request, response);
			return;
		}
		else {
			ArrayList<Customer> customers = (ArrayList<Customer>) getServletContext().getAttribute("customers");
			Customer newCustomer=null;
			try {
				newCustomer = new Customer( first, last, username, password1);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			customers.add(newCustomer);
			
			HttpSession session = request.getSession();
			session.setAttribute("NowCustomer", newCustomer);
			
			response.sendRedirect("CustomerProfile");
			return;
		}
	}
	}


