/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sev;

import com.bean.Users;
import com.local.service.UserBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wenbog
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    UserBeanLocal ub;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //get a session bean
        try {
            InitialContext ic = new InitialContext();
            ub = (UserBeanLocal) ic.lookup("java:comp/env/ejb/UserBean");

        } catch (NamingException e) {
            throw new ServletException(e);
        }

        String op = request.getParameter("op"); // operation
        String id = request.getParameter("id"); // user id
        String userame = request.getParameter("name"); // user name
        String userpass = request.getParameter("password"); // user password
        Users users = new Users();

        System.out.println("Name: " + userame + "\nPASS: " + userpass);
        System.out.println(op);
        
        PrintWriter out = response.getWriter();
        if(op.equals("add")){         
            if (userame != null && userame.length() > 0) {
                users.setUserName(userame);
                users.setPassword(userpass);
                if(ub.add(users)){
                    out.print("True");
                }
                else{
                    out.print("False");
                }
                out.flush();
                out.close();
            }
        }
        else if(op.equals("update")){
            if (userame != null && userame.length() > 0) {
                users.setId(Long.parseLong(id));
                users.setUserName(userame);
                users.setPassword(userpass);
                ub.update(users);
            }
        }
        else if(op.equals("delete")){
            users.setId(Long.parseLong(id));
            ub.delete(users);
        }else if(op.equals("check")){
            users.setUserName(userame);
            users.setPassword(userpass);

            if(ub.check(users)){
                out.print("True");
            }
            else{
                out.print("False");
            }
            out.flush();
            out.close();
        }


        List<Users> li = ub.getAll();
        System.out.println(li.size());
     
        for(Users u: li){
            out.println("<tr>");
            out.println("<td>" + u.getId() + "</td>");
            out.println("<td>" + 
                        "<input type='text' class='name' value=" +
                        u.getUserName() + "></td>");
            out.println("<td>" + 
                        "<input type='text' class='pass' value=" +
                        u.getPassword() + "></td>");
            out.println("<td>" +
                        "<button class='btn btn-xs btn-primary update'>Update</button>" +
                        "</td>)");   
            out.println("<tr>");
        }

        out.flush();
        out.close();
    }

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        processRequest(request, response);
    }

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        processRequest(request, response);
    }

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
    @Override
	public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    }
