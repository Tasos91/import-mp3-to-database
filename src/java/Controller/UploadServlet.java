package Controller;

import Controller.DAOconnection;
import com.beaglebuddy.mp3.MP3;
import org.json.JSONException;
import org.json.JSONObject;
import com.beaglebuddy.id3.enums.PictureType;
 import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Tasos
 */
@WebServlet(name = "myfile", urlPatterns = {"/UploadServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
       maxFileSize = 1024 * 1024 * 14, maxRequestSize = 1024 * 1024 * 14 * 5)//to orio toy megethous toy arxeioy //
public class UploadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        final Part filepart = request.getPart("myfile");
        final String filename = filepart.getSubmittedFileName();
        filepart.write("C:\\Users\\Tasos\\Desktop\\mp3\\"+filename);
        File f = new File("C:\\Users\\Tasos\\Desktop\\mp3\\"+filename);
        MP3 mp3 = new MP3(f);
        String titleOriginal=null;
        String artistOriginal=null;
        titleOriginal=mp3.getTitle();
        String title=titleOriginal.replaceAll(" ", "%20");
        
        artistOriginal=mp3.getBand();
        String artist=artistOriginal.replaceAll(" ", "%20");
        //System.out.println(title);
        URL url = new URL("https://api.lyrics.ovh/v1/"+artist+"/"+title);
        HttpURLConnection conn1=(HttpURLConnection)url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
        String line=null;
               StringBuilder msg= new StringBuilder();
               while((line = br.readLine() ) != null){
                   msg.append(line);
               }
               
               JSONObject obj = new JSONObject(msg.toString());
               String lyrics = obj.getString("lyrics");
               System.out.println(lyrics);
        try{
          Connection conn= DAOconnection.getConnection();
          String sql="insert into blobsfiles (InsertData,title,band,lyrics) values (?,?,?,?)";
          PreparedStatement smt = conn.prepareStatement(sql);
          
          smt.setBlob(1, filepart.getInputStream());
          smt.setString(2,titleOriginal);
          smt.setString(3,artistOriginal);
          smt.setString(4,lyrics);
          smt.executeUpdate();
          conn.close();
                    
          
      } catch (SQLException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
