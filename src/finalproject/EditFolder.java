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
 * Servlet implementation class EditFolder
 */
@WebServlet("/EditFolder")
public class EditFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public EditFolder() {
        super();
     }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentID = request.getParameter("tempid");
		String currentName= request.getParameter("tn");
		
		request.setAttribute("currentID", currentID);
		request.setAttribute("currentName", currentName);
		
		request.getRequestDispatcher("/WEB-INF/EditFolder.jsp").forward(request, response);
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();	
		
		String newName = request.getParameter("newName");
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
            
            stmt.executeUpdate("update folder " + "set name='"+newName+"' where uid='"+currentID+"' ;");
            
            ResultSet rs = stmt.executeQuery( "select * from folder where uid='"+currentID+"';");
            
            
            while( rs.next() )
            {
                  parentID = rs.getString("parentuid"); 
            }
            
            c.close();
        }catch( SQLException e )
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
		
		////////
		
		if(parentID.equals("root")){
			response.sendRedirect("finalmain");
		}
		else
		{
			response.sendRedirect("finalmain?tempid="+parentID);
		}
	}

	
	
	public int FindFolderIndex( List<folder> folder, String id){
		int index = 0;
		for (folder fo : folder){
			if (fo.getId().equals(id)){
				index =  folder.indexOf(fo);
			}
		}
		return index;
	}
}
