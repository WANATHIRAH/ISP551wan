package com.example.isp551wan;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@MultipartConfig
@WebServlet(name = "LAupdateHouseDetailsServlet", value = "/LAupdateHouseDetailsServlet")
public class LAupdateHouseDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "updHouse":
                    updHouse(request, response);
                    break;
                case "updRoom":
                    updRoom(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void updHouse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Part f=request.getPart("hPic");
        String imageFileName=f.getSubmittedFileName();
        File file = new File("C:/Users/Public/LAB EXERCISE/nonresident/src/main/webapp/images/" + imageFileName);
        System.out.println("my file need upload" + file);


        int hids = Integer.parseInt(request.getParameter("hid"));
        //int landid = Integer.parseInt(request.getParameter("landid"));

        try{

            String hName    = request.getParameter("hName");
            Double hMP     = Double.parseDouble(request.getParameter("Pricepm"));
            String hAddress = request.getParameter("hAddress");
            String hloc    = request.getParameter("hloc");
            String hAvailability = request.getParameter("hAvailability");
            int hNoTenants = Integer.parseInt( request.getParameter("NumOfTenant"));
            int hNoRoom = Integer.parseInt(request.getParameter("NumOfRooms"));
            int hNoToilet = Integer.parseInt(request.getParameter("NumOfToilet"));
            int hNoAC = Integer.parseInt(request.getParameter("NumOfAC"));
            String hWifi = request.getParameter("hWifi");
            int hFurniture = Integer.parseInt(request.getParameter("NumOfSofa"));
            int hWM = Integer.parseInt(request.getParameter("NumOfWM"));
            String desc = request.getParameter("Desc");

            try {

                //for picture
                FileOutputStream fos = new FileOutputStream(file);
                InputStream is = f.getInputStream();

                byte[] data=new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();

            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
                String user = "NRS";
                String pass = "system";
                Connection conn = DriverManager.getConnection(dbURL, user, pass);

                PreparedStatement pst = conn.prepareStatement("UPDATE HOUSEDETAILS SET HOUSENAME=?,HOUSEMONTHLYPRICE=?,HOUSEADDRESS=?,HOUSELOCATION=?,HOUSEPUBLISHDATE=localtimestamp,HOUSEAVAILIBILITY=?,HOUSENOTOILET=?,HOUSENOAC=?,HOUSEWIFI=?,HOUSEFURNITURE=?,HOUSEWM=?,HOUSEDESCRIPTION=?,HOUSEPICNAME=? WHERE HOUSEID = ?");
                pst.setString(1,hName);
                pst.setDouble(2,hMP);
                pst.setString(3,hAddress);
                pst.setString(4,hloc);
                pst.setString(5,hAvailability);
                pst.setInt(6,hNoToilet);
                pst.setInt(7,hNoAC);
                pst.setString(8,hWifi);
                pst.setInt(9,hFurniture);
                pst.setInt(10,hWM);
                pst.setString(11,desc);
                pst.setString(12,imageFileName);
                pst.setInt(13,hids);
                //pst.setInt(1,landid);

                pst.executeUpdate();


                PreparedStatement pst2 = conn.prepareStatement("UPDATE HOUSE SET HOUSENOTENANTS=?,HOUSENOROOM=? WHERE HOUSEID = ?");
                pst2.setInt(1,hNoTenants);
                pst2.setInt(2,hNoRoom);
                pst2.setInt(3,hids);

                pst2.executeUpdate();



            }catch (Exception e){
                e.printStackTrace();
            }

        }catch(Exception e){
            System.out.println(e);
        }

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Your details succesfully updated.');");
        out.println("location='landlord-displayHouseList.jsp';");
        out.println("</script>");

    }


    private void updRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Part f=request.getPart("hPic");
        String imageFileName=f.getSubmittedFileName();
        File file = new File("C:/Users/Public/LAB EXERCISE/nonresident/src/main/webapp/images/" + imageFileName);
        System.out.println("my file need upload" + file);


        int hids = Integer.parseInt(request.getParameter("hid"));
        System.out.println(hids);
        //int landid = Integer.parseInt(request.getParameter("landid"));

        try{

            String hName    = request.getParameter("hName");
            Double hMP     = Double.parseDouble(request.getParameter("Pricepm"));
            String hAddress = request.getParameter("hAddress");
            String hloc    = request.getParameter("hloc");
            String hAvailability = request.getParameter("hAvailability");
            int hNoToilet = Integer.parseInt(request.getParameter("NumOfToilet"));
            int hNoAC = Integer.parseInt(request.getParameter("NumOfAC"));
            String hWifi = request.getParameter("hWifi");
            int hFurniture = Integer.parseInt(request.getParameter("NumOfSofa"));
            int hWM = Integer.parseInt(request.getParameter("NumOfWM"));
            String desc = request.getParameter("Desc");

            try {

                //for picture
                FileOutputStream fos = new FileOutputStream(file);
                InputStream is = f.getInputStream();

                byte[] data=new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();

            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
                String user = "NRS";
                String pass = "system";
                Connection conn = DriverManager.getConnection(dbURL, user, pass);

                PreparedStatement pst = conn.prepareStatement("UPDATE HOUSEDETAILS SET HOUSENAME=?,HOUSEMONTHLYPRICE=?,HOUSEADDRESS=?,HOUSELOCATION=?,HOUSEPUBLISHDATE=localtimestamp,HOUSEAVAILIBILITY=?,HOUSENOTOILET=?,HOUSENOAC=?,HOUSEWIFI=?,HOUSEFURNITURE=?,HOUSEWM=?,HOUSEDESCRIPTION=?,HOUSEPICNAME=? WHERE HOUSEID = ?");
                pst.setString(1,hName);
                pst.setDouble(2,hMP);
                pst.setString(3,hAddress);
                pst.setString(4,hloc);
                pst.setString(5,hAvailability);
                pst.setInt(6,hNoToilet);
                pst.setInt(7,hNoAC);
                pst.setString(8,hWifi);
                pst.setInt(9,hFurniture);
                pst.setInt(10,hWM);
                pst.setString(11,desc);
                pst.setString(12,imageFileName);
                pst.setInt(13,hids);
                //pst.setInt(1,landid);

                pst.executeUpdate();


                PreparedStatement pst2 = conn.prepareStatement("UPDATE ROOM SET HOUSEID WHERE HOUSEID = ?");
                pst2.setInt(1,hids);
                pst2.setInt(2,hids);

                pst2.executeUpdate();



            }catch (Exception e){
                e.printStackTrace();
            }

        }catch(Exception e){
            System.out.println(e);
        }

        out.println("<script type=\"text/javascript\">");
        out.println("alert('Your details succesfully updated.');");
        out.println("location='landlord-displayHouseList.jsp';");
        out.println("</script>");
    }


}
