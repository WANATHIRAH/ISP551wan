package com.example.isp551wan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "DisplayMoreInfoServlet", value = "/DisplayMoreInfoServlet")
public class DisplayMoreInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    displayhouse(request, response);
                    break;
                case "delete":
                    displayroom(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void displayhouse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        response.sendRedirect("landlord-displayMoreInfoHouse.jsp");
    }

    private void displayroom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        response.sendRedirect("Lecturer-displayMoreInfoRoom.jsp");
    }
}


