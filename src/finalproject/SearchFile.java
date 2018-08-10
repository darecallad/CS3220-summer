package finalproject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SearchFile", loadOnStartup = 1)
public class SearchFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchFile() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher( "/WEB-INF/SearchFile.jsp" ).forward( request, response );
		 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<files> fileEntries = new ArrayList<files>();

		String fileName = request.getParameter("fileName");
		
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu29?serverTimezone=UTC";
            String username = "cs3220stu29";
            String password = "!wCk0F.s";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM files WHERE name='"+fileName+ "'");
			
			
			while (rs.next())
				fileEntries.add(new files(
						rs.getString("id"), 
						rs.getString("name"),
						rs.getString("parentuID"),
						rs.getString("extension"),
						rs.getString("dateString")
						));
			c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		
		request.setAttribute("fileName", fileName);
		request.setAttribute("fileEntries", fileEntries);
		request.getRequestDispatcher("/WEB-INF/DisplaySearchedFile.jsp").forward(request, response);
	}
	
	
//	public String getName(String fileName){
//    	String Name=null;
//    	if (fileName !=null){
//    		int lastDot = fileName.lastIndexOf(".");    		
//    			Name = fileName.substring(0,lastDot);    		
//    	} 
//		return Name;    	
//    }


}