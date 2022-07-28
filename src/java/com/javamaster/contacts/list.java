/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.javamaster.contacts;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

    //Ручная сборка json строки
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<contactModel> contact = contactCRUD.select();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json="[";
        for (int i=0;i<contact.size();i++){
        json +="{\"id\":"+ contact.get(i).getId()+",";
        json +="\"FIO\":\""+ contact.get(i).getFIO()+"\",";
        json +="\"address\":\""+contact.get(i).getaddress()+"\",";
        json +="\"number\":\""+ contact.get(i).getnumber()+"\"}";
        if(i!=contact.size()-1)
        json+=",";            
        }
        json+="]";        
        response.getWriter().print(json);
        response.flushBuffer();
    }
}

// Использование библиотеки gson для преобразования объекта в представление JSON
//
//
//@WebServlet(name = "list", urlPatterns = {"/list"})
//public class list extends HttpServlet {
//
//    private final Gson gson = new Gson();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        ArrayList<contactModel> contact = contactCRUD.select();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        String json = gson.toJson(contact);
//        response.getWriter().print(json);
//        response.flushBuffer();
//    }
//}
//
