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

	// Obtain the EJB from the HTTP session
        //WhiteBoardBean wbb = (WhiteBoardBean) request.getSession().getAttribute(SHOPPING_CART_BEAN_SESION_KEY);

        //if (createWhiteBoardBean == null) {
            // EJB is not present in the HTTP session
            // so let's fetch a new one from the container
        
            try {
                InitialContext ic = new InitialContext();
                ub = (UserBeanLocal) ic.lookup("java:comp/env/ejb/WhiteBoardBean");

                // put EJB in HTTP session for future servlet calls
                //request.getSession().setAttribute(SHOPPING_CART_BEAN_SESION_KEY, createWhiteBoardBean);

            } catch (NamingException e) {
                throw new ServletException(e);
            }
        //}
        
        String op = request.getParameter("op");
        String id = request.getParameter("id");
        String userame = request.getParameter("name");
        String userpass = request.getParameter("password");
        Users users = new Users();
        if(op.equals("add")){         
            System.out.println("Name: " + userame + "\nDes: " + userpass);
            if (userame != null && userame.length() > 0) {
                users.setUserName(userame);
                users.setPassword(userpass);
                int size = ub.getAll().size();
                long setid = size == 0 ? 0 : ub.getAll().get(size - 1).getId() + 1;
                users.setId(setid);
                ub.add(users);
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
        }
        
        
        List<Users> li = ub.getAll();
        System.out.println(li.size());
        //request.setAttribute("list", li);
        //        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
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
                            "<button class='btn btn-xs btn-primary' onclick='Delete(" + 
                            u.getId() +
                            ")'>Delete</button>" +
                            "<button class='btn btn-xs btn-primary update'>Update</button>" +
                            "</td>)");   
                out.println("<tr>");
            }
            
            out.flush();
            out.close();
        }
        
        //request.getRequestDispatcher("/index.jsp").forward(request,response);
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ShoppingServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ShoppingServlet</h1>");
//
//            String whiteboardName = request.getParameter("product");
//            if (whiteboardName != null && whiteboardName.length() > 0) {
//		// Product is present in the HTTP request.
//                // Let's add it to the shopping cart
//                WhiteBoard whiteboard = new WhiteBoard();
//                whiteboard.setDescription(whiteboardName);
//                whiteboard.setId((long) 10);
//                createWhiteBoardBean.addWhiteBoard(whiteboard);
//
//                out.println(whiteboardName + " in your tag<br>");
//            }
//
//            String persist = request.getParameter("complete");
//            if (persist != null && persist.equalsIgnoreCase("yes")) {
//                // Request instructs to complete the purchase
//                createWhiteBoardBean.completeCreation(out);
//            }
//            out.println("</body>");
//            out.println("</html>");
//        }
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
