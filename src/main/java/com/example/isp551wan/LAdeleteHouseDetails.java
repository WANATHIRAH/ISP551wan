package com.example.isp551wan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "LAdeleteHouseDetails", value = "/LAdeleteHouseDetails")
public class LAdeleteHouseDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int hids = Integer.parseInt(request.getParameter("hid"));
        //int landid = Integer.parseInt(request.getParameter("landid"));

        try{

            Class.forName("oracle.jdbc.driver.OracleDriver"); // ni stay
            String dbURL = "jdbc:oracle:thin:@localhost:1521:XE"; //ni url dri heroku database
            String user = "NRS"; //ni user dri heroku database
            String pass = "system"; //ni password dri heroku database
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            PreparedStatement pst = conn.prepareStatement("DELETE FROM housedetails WHERE houseid = ?");
            pst.setInt(1,hids);
            //pst.setInt(1,landid);

           pst.executeUpdate();


        }catch(Exception e){
            System.out.println(e);
        }

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Your house succesfully deleted.');");
        out.println("location='landlord-displayHouseList.jsp';");
        out.println("</script>");
//        response.sendRedirect("landlord-displayHouseList.jsp");
    }
}
