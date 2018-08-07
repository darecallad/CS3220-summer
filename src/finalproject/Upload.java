package finalproject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Upload() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// temp id and parent id 
		
		String currentID = request.getParameter("tempid");
		String parentID = "";
		
		if(currentID!= null) {
			parentID = currentID;
		}
		else {
			parentID = "root";
		}
		
		getServletContext().setAttribute("UploadParentId", parentID);
		request.setAttribute("parentID", parentID);		
		
		request.getRequestDispatcher("/WEB-INF/Upload.jsp").forward(request, response);
		// this jsp should be a simple upload form. 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// declare
		String fileName = "";
		String extension = "";
		String fileid = null;
		
		String parentID = (String) getServletContext().getAttribute("UploadParentId"); // get upload file's parent id
		
		// create date and change form to user friendly
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss ");
		String dateString = dateFormat.format(date);
		
		
		// example from pervious
		// 
		//
		// Create a factory for disk-based file items
	    DiskFileItemFactory factory = new DiskFileItemFactory();

	    // Configure a repository (to ensure a secure temp location is used)
	    ServletContext servletContext = (ServletContext) this.getServletConfig().getServletContext();
	    File repository = (File) servletContext.getAttribute( "javax.servlet.context.tempdir" );
	    factory.setRepository( repository );

	    // Create a new file upload handler
	    ServletFileUpload upload = new ServletFileUpload( factory );

	    // Count how many files are uploaded
	    //int count = 0;
	       
        // The directory we want to save the uploaded files to.
	    String fileDir = getServletContext().getRealPath( "/WEB-INF/files" );    
	       
	       
	    // Parse the request
	    try
	    {
	           List<FileItem> items = upload.parseRequest(  request );
	           for( FileItem item : items )
	           {
	               // If the item is not a form field - meaning it's an uploaded
	               // file, we save it to the target dir
	               if( !item.isFormField() )
	               {
	                   // item.getName() will return the full path of the uploaded
	                   // file, e.g. "C:/My Documents/files/test.txt", but we only
	                   // want the file name part, which is why we first create a
	                   // File object, then use File.getName() to get the file
	                   // name.
	                   fileName = (new File( item.getName() )).getName();  
	                  
	                   
	                   fileid = GenerateID();
	                   extension = getExtension(fileName);
	                   
	                   
	                   File file = new File( fileDir, fileid+extension );
	                                                           
	                   item.write( file );
	                   
	               }
	           }

	       }
	       catch( Exception e )
	       {
	           throw new IOException( e );
	       }
	    
	    out.println("<h4> parentID is " + parentID + " </h4>");
	    
	    // connect back to JDBC
	    // insert it 
	    
	    Connection c = null;
        
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu29";
            String username = "cs3220stu29";
            String password = "!wCk0F.s";

            c = (Connection) DriverManager.getConnection( url, username, password );
            Statement stmt = (Statement) c.createStatement();
            
            stmt.executeUpdate("insert into files " + "(uid,name,parentuid,dateString,extension)" + "value" 
            + "('"+GenerateID()+"','"+fileName+"','"+parentID+"','"+dateString+"','"+extension+"');");
            
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
        
        //////////////////////////////////
        // check parentID
        // and send it back to main page
        //////////////////////////////////
        if(parentID.equals("root")){
			response.sendRedirect("finalmain");
		}
		else{
			response.sendRedirect("finalmain?tempid="+parentID);
		}
            
		
	}

	/////////
	
	public String getExtension(String fileName){
    	String extension=null;
    	if (fileName !=null){
    		int lastDot = fileName.lastIndexOf(".");    		
    			extension = fileName.substring(lastDot);    		
    	}
		return extension;    	
    }
    
    public String GenerateID(){
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID;
		
	}
}
