package lab5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class lab5main
 */
@WebServlet("/lab5main")
public class lab5main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lab5main() {
        super();
               
    }

    public void init(ServletConfig config) throws ServletException{
    		super.init(config);
    		ArrayList<Quotation> quotes = new ArrayList<Quotation>();
    		quotes.add(new Quotation("abcde","Chuchu"));
    		quotes.add(new Quotation("I kkkk","brbrbr"));
    		quotes.add(new Quotation("ddddddde","dkls"));
    		getServletContext().setAttribute("quotes",quotes);
    		boolean recorded = false;
    		getServletContext().setAttribute("recorded",recorded);
    		
    		Random rnd = new Random();
    		Quotation quote;
    		quote = quotes.get(rnd.nextInt(quotes.size()));
    		getServletContext().setAttribute("previousQuote",quote);
    	
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	HttpSession session = request.getSession();
    	
    	Quotation previousQuote =(Quotation)session.getAttribute("previousQuote");
    	
	
    	ArrayList<Quotation> quotes = (ArrayList<Quotation>) getServletContext().getAttribute("quotes");
    	Random rnd = new Random();
    	Quotation quote;
    	
    	boolean recorded = (boolean) getServletContext().getAttribute("recorded");
    	

    	
    	if (recorded ==true) {

    		do {
    			quote = quotes.get(rnd.nextInt(quotes.size()));
    		}while(previousQuote != null && previousQuote == quote);
    	
    		recorded = false;
    		getServletContext().setAttribute("recorded",recorded);
    		
    		session.setAttribute("previousQuote", quote);
    	
    	}else if (recorded==false){
    		
    		
    		session.setAttribute("previousQuote", previousQuote);
    	}
    	//	getServletContext().setAttribute("previousQuote", quote);
    //	quote.incView();
    	
    	RequestDispatcher dispather = request.getRequestDispatcher("WEB-INF/lab5main.jsp");
		dispather.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
