<%@page import="Controller.Mp3"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="Controller.GetData" %> 


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            
table, th, td{
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    border: solid black;   
}
</style> 
    <body><div class="container">
        <table class="table">
            <tr style="border: solid black;"><h1 style="text-align:center; background-color: whitesmoke;">MY SONGS</h1></tr>
  <tr class="success">
    
    <td>Title</td>
    <td>Band</td>
    <td>Lyrics</td>
    <td>Delete</td>
    
  </tr>
  
        
        <% 
           Mp3 mymp3 = new Mp3();
           ArrayList<Mp3> ar = new ArrayList();
           ar=(ArrayList)request.getAttribute("Data");
           
        
            for(Mp3 mp3 : ar){%>
            <tbody>
                <tr class="warning">
                    
                    <td><%= mp3.getTitle() %></td>
                    <td><%= mp3.getBand() %></td>
                    <td><%= mp3.getLyrics()  %></td>
                    <td><a href="Delete?title=<%= mp3.getTitle() %>&Band=<%= mp3.getBand() %>">Delete Song</a></td>
               </tr>
               
            <%}%>
        
        
        </tbody>
          
          
        </table>
        </div>
    </body>
</html>






