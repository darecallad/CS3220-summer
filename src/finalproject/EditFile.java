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

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class EditFile
 */
@WebServlet("/EditFile")
public class EditFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditFile() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentID = request.getParameter("tempid");
		String currentName= request.getParameter("tn");

		
		String Name = getName(currentName);
		
	
		String extension =  null;
		

		Connection c = null;
		

		try

		{
    
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu29";
			String username = "cs3220stu29";
			String password = "!wCk0F.s";

			c = (Connection) DriverManager.getConnection( url, username, password );
			Statement stmt = (Statement) c.createStatement();
    
                                  
            
            ResultSet rs = stmt.executeQuery( "select * from files where uid='"+currentID+"';");
          
          
            while( rs.next() )
            {
                  extension = rs.getString("extension"); 
            }
           
            

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

		
		
		
		
		request.setAttribute("currentID", currentID);
		request.setAttribute("Name", Name);
		request.setAttribute("extension", extension);
		
		request.getRequestDispatcher("/WEB-INF/EditFile.jsp").forward(request, response);
		
		}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		

		// get from JSP
		
		String newfileName = request.getParameter("newfileName");
		String currentID = request.getParameter("tempid");

		String parentID=null;
		String newName = newfileName;
		
		
		
		Connection c2 = null;
		
		try

		{
    
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu29";
			String username = "cs3220stu29";
			String password = "!wCk0F.s";

			c2 = (Connection) DriverManager.getConnection( url, username, password );
			Statement stmt = (Statement) c2.createStatement();
			
			stmt.executeUpdate("update files " + "set name='"+newName+"' where uid='"+currentID+"' ;");
            
            
            ResultSet rs = stmt.executeQuery( "select * from files where uid='"+currentID+"';");
          
          
            while( rs.next() )
            {
                  parentID = rs.getString("parentuid"); 
            }
           
            
            

            c2.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
		finally
	        {
	            try
	            {
	                if( c2 != null ) c2.close();
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
	

	public int FindFileIndex( List<files> Files, String id){
		int index = 0;
		for (files fi : Files){
			if (fi.getId().equals(id)){
				index =  Files.indexOf(fi);
			}
		}
		return index;
	}
	

	
	public String getName(String fileName){
    	String Name=null;
    	if (fileName !=null){
    		int lastDot = fileName.lastIndexOf(".");    		
    			Name = fileName.substring(0,lastDot);    		
    	} 
		return Name;    	
    }
}
