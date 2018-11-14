package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOconnection {
  
  private static ResultSet rs;
  public static DAOconnection conn;
  
  
  public static Connection getConnection(){
    Connection conn=null;
    try{
     
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("jdbc/mp3");
      conn=ds.getConnection();
      
      System.out.println(conn);
      
    }catch(NamingException ex){
    
    } catch (SQLException ex) {
          Logger.getLogger(DAOconnection.class.getName()).log(Level.SEVERE, null, ex);
      }
    return conn;
  }
}
