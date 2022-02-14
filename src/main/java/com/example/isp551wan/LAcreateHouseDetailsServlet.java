package com.example.isp551wan;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@MultipartConfig
@WebServlet(name = "LAcreateHouseDetailsServlet", value = "/LAcreateHouseDetailsServlet")
public class LAcreateHouseDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Part f=request.getPart("hPic");
        String imageFileName=f.getSubmittedFileName();
        File file = new File("C:/Users/Public/LAB EXERCISE/nonresident/src/main/webapp/images/" + imageFileName);
        System.out.println("my file need upload" + file);

         int idhouse=0;
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



               FileOutputStream fos = new FileOutputStream(file);
               InputStream is = f.getInputStream();

               byte[] data=new byte[is.available()];
               is.read(data);
               fos.write(data);
               fos.close();


           try {
               Class.forName("oracle.jdbc.driver.OracleDriver");
               String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
               String user = "NRS";
               String pass = "system";
               Connection conn = DriverManager.getConnection(dbURL, user, pass);

               PreparedStatement st = conn.prepareStatement("insert into HOUSEDETAILSS(HOUSEPUBLISHDATE,HOUSENAME," +
                       "HOUSEMONTHLYPRICE,HOUSEADDRESS,HOUSELOCATION,HOUSEAVAILIBILITY," +
                       "HOUSENOTOILET,HOUSENOAC," +
                       "HOUSEWIFI,HOUSEFURNITURE,HOUSEWM,HOUSEDESCRIPTION" +
                       ",HOUSEPICNAME,HOUSEID,LANDLORDID,RENTYPE) " +
                       "values(localtimestamp,?,?,?,?,?,?,?,?,?,?,?,?,HOUSE_SEQ.NEXTVAL,1,'House')");
               st.setString(1,hName);
               st.setDouble(2,hMP);
               st.setString(3,hAddress);
               st.setString(4,hloc);
               st.setString(5,hAvailability);
               st.setInt(6,hNoToilet);
               st.setInt(7,hNoAC);
               st.setString(8,hWifi);
               st.setInt(9,hFurniture);
               st.setInt(10,hWM);
               st.setString(11,desc);
               st.setString(12,imageFileName);

               PreparedStatement sr=conn.prepareStatement("select max(houseid) from housedetails");
               ResultSet rs = sr.executeQuery();
               while(rs.next()){
                   idhouse = rs.getInt(1);
                   System.out.println(idhouse);
               }

               PreparedStatement sp=conn.prepareStatement("insert into house(HOUSEID,HOUSENOTENANTS,HOUSENOROOM) values(?,?,?)");
               sp.setInt(1,idhouse);
               sp.setInt(2,hNoTenants);
               sp.setInt(3,hNoRoom);

               int row= st.executeUpdate();

               if(row>0){
                   response.sendRedirect("landlord-displayHouseList.jsp");
               }else{
                   out.println("Record failed");
               }

               int row2= sp.executeUpdate();

               if(row2>0){
                   response.sendRedirect("landlord-displayHouseList.jsp");
               }else{
                   out.println("Record failed");
               }

           }catch (Exception e){
               e.printStackTrace();
           }



       }catch (Exception e){
           System.out.println(e);
       }
    }
}
