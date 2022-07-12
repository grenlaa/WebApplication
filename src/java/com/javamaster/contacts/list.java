/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.javamaster.contacts;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.contacts.contactCRUD;
import mysql.contacts.contactModel;

/**
 *
 * @author Fascan
 */
@WebServlet(name = "list", urlPatterns = {"/list"})
public class list extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<contactModel> contact = contactCRUD.select();
        response.addHeader("Content-Type", "application/json");
        String json = gson.toJson(contact);
        response.getWriter().print(json);
        response.flushBuffer();
//        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
