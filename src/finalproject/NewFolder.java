package finalproject;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class NewFolder
 */
@WebServlet("/NewFolder")
public class NewFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NewFolder() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentID = request.getParameter("tempid");
		String parentID="";
		
		if(currentID!=null){
			parentID=currentID;
		}
		else{
			parentID="root";
		}
	
		request.setAttribute("parentID", parentID);		
		
		request.getRequestDispatcher("/WEB-INF/NewFolder.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
		HttpSession session = request.getSession();	
		
		
		
		
			String newName = request.getParameter("newName");
			String parentID = request.getParameter("pid");  //parentid from jsp 
			
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
			String dateString = dateFormat.format(date);
			
			
			// similar to upload file
			// insert to folder table
			
			Connection c = null;
	        
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu29";
	            String username = "cs3220stu29";
	            String password = "!wCk0F.s";

	            c = (Connection) DriverManager.getConnection( url, username, password );
	            Statement stmt = (Statement) c.createStatement();
	            
	            stmt.executeUpdate("insert into folder " + "(uid,name,parentuid,dateString)" + "value" 
	            + "('"+GenerateID()+"','"+newName+"','"+parentID+"','"+dateString+"');");
	            
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
	        
	        
	        /// send it back to main 
	        
	        if(parentID.equals("root")){
				response.sendRedirect("finalmain");
			}
			else{
				response.sendRedirect("finalmain?tempid="+parentID);
			}
			
			
			
	}
	
	
	////// copy and paste from pervious 
	// doing same job 
	// get uid and find index
	public String GenerateID(){
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID;
		
	}
	
	public int FindFolderIndex( List<folder> folders, String id){
		int index = 0;
		for (folder fo : folders){
			if (fo.getId().equals(id)){
				index =  folders.indexOf(fo);
			}
		}
		return index;	}



}
