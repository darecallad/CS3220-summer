package lab2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class lab2main
 */
@WebServlet("/lab2main")
public class lab2main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public lab2main(){
		
        super();
	}
	public void init( ServletConfig config ) throws ServletException{
        // When you override init(), don't forget to call the super class
        // init() first.
        super.init( config );

        // Note that Java automatically converts between int and Integer
        // (i.e. the "Autoboxing and Unboxing" feature since JDK 1.5.
        int counter = 0;
        int counter1 = 0;
        int counter2 = 0;
        
        getServletContext().setAttribute( "counter", counter );
        getServletContext().setAttribute( "counter1", counter1 );
        getServletContext().setAttribute( "counter2", counter2 );
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set COntexttype to html
		javax.servlet.ServletContext servletContext =  getServletContext();

        // get the counter
        Integer counter = (Integer) servletContext.getAttribute( "counter" );
        Integer counter1 = (Integer) servletContext.getAttribute( "counter1" );
        Integer counter2 = (Integer) servletContext.getAttribute( "counter2" );

        
        

        // save it back the application scope
        servletContext.setAttribute( "counter", counter );
        servletContext.setAttribute( "counter1", counter1 );
        servletContext.setAttribute( "counter2", counter2 );

        // display the message "the counter is incremented"
		response.setContentType("text/html");
		//get a print writer
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
		out.println("<h1>Choose the tv shows you like </h1> ");
		out.println("<br />");
		out.println("<br />");
		out.println("1.Game of Throne");
		out.println("<a href = \"lab2result\"?id=0> <img src =\"https://upload.wikimedia.org/wikipedia/en/thumb/d/d8/Game_of_Thrones_title_card.jpg/250px-Game_of_Thrones_title_card.jpg\" alt =\"Game of Throne\" id=\"0\" width= \"270\" height= \"170\"> </a>");
		out.println("<br />");
		out.println("<br />");
		out.println("2.Walking Dead");
		out.println("<a href = \"lab2result1\"?id=1> <img src =\"https://steamcdn-a.akamaihd.net/steam/apps/207610/header.jpg?t=1456281215\" alt =\"Walking Dead\" id=\"1\" width= \"270\" height= \"170\"> </a>");
		out.println("<br />");
		out.println("<br />");
		out.println("3.Breaking bad");
		out.println("<a href = \"lab2result2\"?id=2> <img src =\"https://upload.wikimedia.org/wikipedia/en/6/61/Breaking_Bad_title_card.png\" alt =\"Breaking Bad\" id=\"2\" width= \"270\" height= \"170\"> </a>");
		out.println("<br />");
		out.println("<br />");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
