package finalproject;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class DeleteFile
 */
@WebServlet("/DeleteFile")
public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteFile() {
        super();
            
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		
		String currentID = request.getParameter("tempid");		
		String parentID = null;
		
		Connection c = null;
		
		try

		{
    
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu29?serverTimezone=UTC";
			String username = "cs3220stu29";
			String password = "!wCk0F.s";

			c = (Connection) DriverManager.getConnection( url, username, password );
			Statement stmt = (Statement) c.createStatement();
			
			 ResultSet rs = stmt.executeQuery( "select * from files where uid='"+currentID+"';");
             
	            while( rs.next() )
	            {
	                  parentID = rs.getString("parentuid"); 
	            }
	           
	            
	            stmt.executeUpdate("delete from files where uid='"+ currentID+"';");


	            c.close();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
			finally
		        {
		            try
		            {
		                if( c != null ) c.close();
		            }
		            catch( SQLException e )
		            {
		                throw new ServletException( e );
		            }
		        }	
			
			if (parentID.equals("root")){
				response.sendRedirect("finalmain");
			}
			else{
				response.sendRedirect("finalmain?tempid="+parentID);
			}
			
			
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	public int FindFileIndex( List<files> Files, String id){
		int index = 0;
		for (files fi : Files){
			if (fi.getId().equals(id)){
				index =  Files.indexOf(fi);
			}
		}
		return index;
	}

}
