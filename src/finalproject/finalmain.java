package finalproject;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;


/**
 * Servlet implementation class finalmain
 */
@WebServlet("/finalmain")
public class finalmain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public finalmain() {
		super();
	}
	
	// init concent with databse
	 public void init( ServletConfig config ) throws ServletException
	    {
	        super.init( config );

	        try
	        {
	            Class.forName( "com.mysql.jdbc.Driver" );

	        }
	        catch( ClassNotFoundException e )
	        {
	            throw new ServletException( e );
	        }
	    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// get Session 
		HttpSession session = request.getSession();
		// create folder and file arraylist
		List<folder> folders= new ArrayList <folder>();
		List<files> files = new ArrayList<files>();
		
		
		Connection c = null;
	        
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu29?serverTimezone=UTC";
	            String username = "cs3220stu29";
	            String password = "!wCk0F.s";

	            c = (Connection) DriverManager.getConnection( url, username, password );
	            Statement stmt = (Statement) c.createStatement();
	            
	            // after connect.. select all data from folder and files table
	            ResultSet rs = stmt.executeQuery( "SELECT  * FROM folder " );

	            while( rs.next() )
	            {

	            	int id = rs.getInt("id");
	            	// uid for the unique id 
	            	String uid =rs.getString("uid");
	            	String name = rs.getString("name");
	            	String parentuID = rs.getString("parentuID");  // parent unique ID
	            	String dateString = rs.getString("dateString");
	            	
	            	// create object for folder class
	            	folders.add(new folder(uid,name,parentuID,dateString));
	              
	            }
	            
	         // after connect.. select all data from folder and files table
	            rs = stmt.executeQuery( "SELECT  * FROM files " );

	            while( rs.next() )
	            {

	            	int id = rs.getInt("id");
	            	// uid for the unique id 
	            	String uid =rs.getString("uid");
	            	String name = rs.getString("name");
	            	String parentuID = rs.getString("parentuID");  // parent unique ID
	            	String dateString = rs.getString("dateString");
	            	String extension = rs.getString("extension");
	            	
	            	// create object for folder class
	            	files.add(new files(uid,name,parentuID,extension,dateString));
	              
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

	 // after select the data from table and make list for it 
	 // we create temp list for if statement 
	        
	 List<folder> Tempfolders = new ArrayList<folder>();
	 List<files> Tempfiles = new ArrayList<files>();
	 
	 // and we need current id and its parent's id
	 
	 String currentID= request.getParameter("tempid");

	 // find current fold's index 
	 int currentIndex = Findfolderindex(folders, currentID);
	 String parentID = folders.get(currentIndex).getParentID();
	 
	 String currentName; // get match folder/file name
	 String addString = ""; // for add new Folder and upload button
	 String backString= null; // for pervious button
	        
	 if(currentID == null) 
	 {  //current id = null -> in root folder
		 
		 for(folder fo : folders) {
			 if(fo.getParentID().equals("root")) {
				 Tempfolders.add(fo);
			 }
		 						}
		 
		 for(files fi : files) {
			 if(fi.getParentID().equals("root")) {
				 Tempfiles.add(fi);
			 }
		 						}
		 currentName ="root";
	 }
	 
	
	
	 else 
	 { // not in root folder
		 
		 for(folder fo :folders) {
			 if(!(fo.getParentID().equals("root"))) {
				 if(fo.getParentID().equals(currentID)) {
					 Tempfolders.add(fo);
				 }
			 }
		 						}
		 
		 for(files fi : files) {
			 if(!(fi.getParentID().equals("root"))) {
				 if(fi.getParentID().equals(currentID)) {
					 Tempfiles.add(fi);
				 }
			 }
		 						}
		 
		 addString = "?tempid="+currentID;
		 
		 if(parentID.equals("root")) {
			 backString="";
		 }
		 else {
			 backString = "?tempid="+parentID;
		 }
		 
		 currentName =folders.get(currentIndex).getName();
	 }
	 
	 
	 // set attribute;
	 request.setAttribute("addString",addString);
	 request.setAttribute("backString", backString);
	 request.setAttribute("currentName", currentName);
	 request.setAttribute("Tempfolders", Tempfolders);
	 request.setAttribute("Tempfiles", Tempfiles);
	 
	 
	 // JSP
	 request.getRequestDispatcher( "/WEB-INF/finalmain.jsp" ).forward(request, response);
	
	}


	public int Findfolderindex(List<folder> folders, String id) {
		// TODO Auto-generated method stub
		int index = 0;
		for(folder fo:folders) {
			if(fo.getId().equals(id)) {
				index = folders.indexOf(fo);
			}
		}
		return index;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("finalmain");
	}
	
	// uniqueID
	public String GenerateID() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID;
	}

}
