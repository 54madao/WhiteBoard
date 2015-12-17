/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sev;

import com.bean.Users;
import com.bean.WhiteBoard;
import com.local.service.UserBeanLocal;
import com.local.service.WhiteBoardBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author munehiro
 */
@WebServlet(name = "WhiteBoardServlet", urlPatterns = {"/WhiteBoardServlet"})
    public class WhiteBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    //private static final String SHOPPING_CART_BEAN_SESION_KEY= "shoppingCart";
        WhiteBoardBeanLocal wbb = null;
        UserBeanLocal ub = null;
        Users user = null;

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
                wbb = (WhiteBoardBeanLocal) ic.lookup("java:comp/env/ejb/WhiteBoardBean");
                ub = (UserBeanLocal) ic.lookup("java:comp/env/ejb/UserBean");

                // put EJB in HTTP session for future servlet calls
                //request.getSession().setAttribute(SHOPPING_CART_BEAN_SESION_KEY, createWhiteBoardBean);

            } catch (NamingException e) {
                throw new ServletException(e);
            }
        //}
        
        String wbOp = request.getParameter("op");
        String id = request.getParameter("id");
        String wbName = request.getParameter("name");
        String wbDes = request.getParameter("description");
        String wbUser = request.getParameter("user");
        String wbPersonal = request.getParameter("personal");
        WhiteBoard whiteboard = new WhiteBoard();
        user = ub.get(wbUser);
        
        System.out.println(wbOp);
        System.out.println(wbUser);
        
        PrintWriter out = response.getWriter();
        
        if(wbOp.equals("add")){         
            System.out.println("Name: " + wbName + "\nDes: " + wbDes);
            if (wbName != null && wbName.length() > 0) {
                whiteboard.setName(wbName);
                whiteboard.setDescription(wbDes);
                user = ub.get(wbUser);              
                whiteboard.setOwner(user);            
//                int size = wbb.getAll().size();
//                long setid = size == 0 ? 0 : wbb.getAll().get(size - 1).getId() + 1;
//                whiteboard.setId(setid);

                wbb.add(whiteboard);
            }
        }
        else if(wbOp.equals("update")){
            if (wbName != null && wbName.length() > 0) {
                whiteboard.setId(Long.parseLong(id));
                whiteboard.setName(wbName);
                whiteboard.setDescription(wbDes);
                wbb.update(whiteboard);
            }
        }
        else if(wbOp.equals("delete")){
            whiteboard.setId(Long.parseLong(id));
            wbb.delete(whiteboard);
        }if(wbOp.equals("subscribe")){
            whiteboard.setId(Long.parseLong(id));
            user = ub.get(wbUser);
            wbb.subscribe(whiteboard, user);
        }
        
        if(wbPersonal.equals("personal")){
            personal(out);
        }else{
            overview(out);
        }
            
            out.flush();
            out.close();
        
        
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

        private void overview(PrintWriter out){
            List<WhiteBoard> li = wbb.getAll();
            System.out.println(li.size());
            //request.setAttribute("list", li);
            //        response.setContentType("text/html;charset=UTF-8");
        
            /* TODO output your page here. You may use following sample code. */
            
            for(WhiteBoard wb: li){
                out.println("<tr>");
                out.println("<td>" + wb.getId() + "</td>");
                out.println("<td>" + 
                            "<input type='text' class='name' value=" +
                            wb.getName() + "></td>");
                out.println("<td>" + 
                            "<input type='text' class='des' value=" +
                            wb.getDescription() + "></td>");
                out.println("<td>" +
                            wb.getOwner().getUserName() + "</td>");
                if(wb.getOwner().getId().equals(user.getId())){
                    out.println("<td>" +
                            "<a href='/Prog4-war/pages/wbview.jsp?name=" +
                            wb.getName() +
                            "' target='_blank'>" +
                            "<button class='btn btn-xs btn-primary'>Open</button></a>" +
                            "<button class='btn btn-xs btn-primary' onclick='Delete(" + 
                            wb.getId() +
                            ")'>Delete</button>" +
                            "<button class='btn btn-xs btn-primary update'>Update</button>" +
                            "</td>");
                }else{
                    out.println("<td>" +
                            "<a href='/Prog4-war/pages/wbview.jsp?name='" +
                            wb.getName() +
                            "' target='_blank'>" +
                            "<button class='btn btn-xs btn-primary'>Open</button></a>" +              
                            "<button class='btn btn-xs btn-primary' onclick='Subscribe(" + 
                            wb.getId() +
                            ")'>Subscribe</button>" +
                            "</td>");
                }
                out.println("<tr>");
            }
        }
        
        private void personal(PrintWriter out){
            List<WhiteBoard> li = wbb.getAll();
            System.out.println(li.size());
            //request.setAttribute("list", li);
            //        response.setContentType("text/html;charset=UTF-8");
        
            /* TODO output your page here. You may use following sample code. */
            
            for(WhiteBoard wb: li){
                if(wb.getOwner().getId().equals(user.getId()) || wb.getSubscriber().contains(user)){
                    out.println("<tr>");
                    out.println("<td>" + wb.getId() + "</td>");
                    out.println("<td>" + 
                                "<input type='text' class='name' value=" +
                                wb.getName() + "></td>");
                    out.println("<td>" + 
                                "<input type='text' class='des' value=" +
                                wb.getDescription() + "></td>");
                    out.println("<td>" +
                                wb.getOwner().getUserName() + "</td>");
                    if(wb.getOwner().getId().equals(user.getId())){
                        out.println("<td>" +
                                "<a href='/Prog4-war/pages/wbview.jsp?name=" +
                                wb.getName() +
                                "' target='_blank'>" +
                                "<button class='btn btn-xs btn-primary'>Open</button></a>" +
                                "<button class='btn btn-xs btn-primary' onclick='Delete(" + 
                                wb.getId() +
                                ")'>Delete</button>" +
                                "<button class='btn btn-xs btn-primary update'>Update</button>" +
                                "</td>");
                    }else{
                        out.println("<td>" +
                                "<a href='/Prog4-war/pages/wbview.jsp?name='" +
                                wb.getName() +
                                "' target='_blank'>" +
                                "<button class='btn btn-xs btn-primary'>Open</button></a>" +              
                                "<button class='btn btn-xs btn-primary' onclick='Subscribe(" + 
                                wb.getId() +
                                ")'>Subscribe</button>" +
                                "</td>");
                    }
                    out.println("<tr>");
                }
            }
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
