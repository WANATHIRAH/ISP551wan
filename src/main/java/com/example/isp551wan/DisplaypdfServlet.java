package com.example.isp551wan;
import org.postgresql.core.Encoding;

import java.net.URL;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.swing.text.Document;
import java.util.Base64;
@MultipartConfig

@WebServlet(name = "DisplaypdfServlet", value = "/DisplaypdfServlet")
public class DisplaypdfServlet extends HttpServlet

{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String filename = request.getParameter("filename");

        File file = new File("src/main/webapp/images/"+filename);

        try {
            Class.forName("org.postgresql.Driver");

            String dbURL = "jdbc:postgresql://ec2-44-194-101-60.compute-1.amazonaws.com:5432/d2us57cbf117bh";
            String user = "rnscsqosqdtcmz";
            String pass = "0b201fb2e59025b780ce0b4148e508b6747fbaf77f6e8cedc675ee4dbc44638a";
            Connection conn = DriverManager.getConnection(dbURL, user, pass);

            PreparedStatement ps = conn.prepareStatement("SELECT housepic FROM housepic WHERE housename=?");
            ps.setString(1, filename);

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                    byte[] imgBytes = rs.getBytes(1);
                    // use the stream in some way here
                    String encoded = Base64.getEncoder().encodeToString(imgBytes);
//                    String base64 = Base64.getEncoder().encodeToString(imgBytes);
                    FileOutputStream fos = new FileOutputStream(file);

                    byte[] decoder = Base64.getDecoder().decode(encoded);
                    fos.write(decoder);
                    System.out.println("PDF File Saved");


//                    ByteArrayInputStream bput = new ByteArrayInputStream(imgBytes);
////                    InputStream is = rs.getBinaryStream(1);
//                    System.out.println(bput);
//                    bput.read(imgBytes);
//                    File outputFile = new File("C:/" + filename);
//                    try (FileOutputStream fileOuputStream = new FileOutputStream(outputFile)){
//                        fileOuputStream.write(imgBytes);
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }

                }
                rs.close();
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
