/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tasos
 */
public class Mp3 {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String title;
    private String band;
    private String lyrics;

    public Mp3(){
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public  ArrayList getData(){
        ArrayList<Mp3> mylist = new ArrayList();
        ResultSet rset = null;
        
        try{
          Connection conn= DAOconnection.getConnection();
          String sql="Select title,band,lyrics from blobsfiles";
          PreparedStatement smt = conn.prepareStatement(sql);
          rset=smt.executeQuery();
          while(rset.next()){
             Mp3 mp3 = new Mp3();
              mp3.setTitle(rset.getString(1));
              mp3.setBand(rset.getString(2));
              mp3.setLyrics(rset.getString(3));
              mylist.add(mp3);
          }
          
          
          rset.close();
          smt.close();
          
          
                    
          
      } catch (SQLException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
        return mylist;
    }
    
    
    
}
