package finalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		// Get the path to the file and create a java.io.File object
        
    	String fileName = request.getParameter("tn");
    	String fileId = request.getParameter("tempid");
    	String extension = getExtension(fileName);
    	
    	
    	String filedir = "/WEB-INF/files/";
    	String filepath = filedir + fileId + extension;
    	//    	String path = getServletContext()
    	//            .getRealPath( "/WEB-INF/files/filereader.java" );
    	String path = getServletContext()
                .getRealPath( filepath );
        File file = new File( path );

        // Set the response headers. File.length() returns the size of the file
        // as a long, which we need to convert to a String.
        
        //response.setContentType( "image/jpg" );
       // response.setContentType( "text" );
        response.setHeader( "Content-Length", "" + file.length() );
        response.setHeader( "Content-Disposition", "inline; filename="+fileName );     //use back original name instead of id

        // Binary files need to read/written in bytes.
        FileInputStream in = new FileInputStream( file );
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );
        in.close();
        
        
	}
	

//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

public String getExtension(String fileName){
	String extension=null;
	if (fileName !=null){
		int lastDot = fileName.lastIndexOf(".");    		
			extension = fileName.substring(lastDot);    		
	}
	return extension;    	
}
}

