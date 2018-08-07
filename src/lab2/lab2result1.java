package lab2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class lab2result1
 */
@WebServlet("/lab2result1")
public class lab2result1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public lab2result1() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//get a print writer
		javax.servlet.ServletContext servletContext =  getServletContext();

        // get the counter
        Integer counter = (Integer) servletContext.getAttribute( "counter" );
        Integer counter1 = (Integer) servletContext.getAttribute( "counter1" );
        Integer counter2 = (Integer) servletContext.getAttribute( "counter2" );
        ++counter1;
        
        servletContext.setAttribute( "counter", counter );
        servletContext.setAttribute( "counter1", counter1 );
        servletContext.setAttribute( "counter2", counter2 );
        
        double total = counter + counter1 + counter2;
        double per = counter / total * 100;
        double per1 = counter1 / total * 100;
        double per2 = counter2 / total * 100;
        
		PrintWriter out = response.getWriter();
		// template HTML
		out.println("<!DOCTYPE html>");		
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
		out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
		out.println("        <title>Document</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		
		
		// Add page-specific content here
		//out.println("you are the  visitor #" + (++pageCounter));
		out.println("<h1>The one you choose is: </h1> ");
		out.println("<img img src =\"https://steamcdn-a.akamaihd.net/steam/apps/207610/header.jpg?t=1456281215\" alt =\"Walking Dead\" id=\"1\" width= \"270\" height= \"170\">>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th> name </th>");
		out.println("<th> votes </th>");
		out.println("<th> % </th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Game of thrones </td>");
		out.println("<td>" + counter +  " </td>");
		out.println("<td>" + per + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Walking dead </td>");
		out.println("<td>" + counter1 +  " </td>");
		out.println("<td>" + per1 + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td> Breaking bad </td>");
		out.println("<td>" + counter2 +  " </td>");
		out.println("<td>" + per2 + "</td>");
		out.println("</tr>");
		
		
		out.println("<br />");
		out.println("<br />");
		out.println("<a href =\"lab2main\">go back to mainpage</a>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
