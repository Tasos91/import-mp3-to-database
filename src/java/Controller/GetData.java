package Controller;

import Controller.DAOconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetData {
    
    public static ArrayList getData(){
        
        ResultSet rset = null;
        Mp3 mp3 = new Mp3();
        ArrayList<Mp3> mymp3 = new ArrayList();
        try{
          Connection conn= DAOconnection.getConnection();
          String sql="Select title,band,lyrics from blobsfiles";
          PreparedStatement smt = conn.prepareStatement(sql);
          rset=smt.executeQuery();
          while(rset.next()){
              Mp3 mp31 = new Mp3();
              
              mp31.setTitle(rset.getString(1));
              mp31.setBand(rset.getString(2));
              mp31.setLyrics(rset.getString(3));
              mymp3.add(mp31);
          }
          
          rset.close();
          smt.close();
          
          
                    
          
      } catch (SQLException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
        return mymp3;
    }
}
