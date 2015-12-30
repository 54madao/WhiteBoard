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
 * @author wenbog
 */
@WebServlet(name = "WhiteBoardServlet", urlPatterns = {"/WhiteBoardServlet"})
public class WhiteBoardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
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
        try {
            InitialContext ic = new InitialContext();
            wbb = (WhiteBoardBeanLocal) ic.lookup("java:comp/env/ejb/WhiteBoardBean");
            ub = (UserBeanLocal) ic.lookup("java:comp/env/ejb/UserBean");
        } catch (NamingException e) {
            throw new ServletException(e);
        }

        String wbOp = request.getParameter("op"); // operation
        String id = request.getParameter("id"); // white board id
        String wbName = request.getParameter("name"); // white board name
        String wbDes = request.getParameter("description"); // white board description
        String wbUser = request.getParameter("user"); // user
        String wbPersonal = request.getParameter("personal"); // if for personal view
        WhiteBoard whiteboard = new WhiteBoard();
        user = ub.get(wbUser);

        PrintWriter out = response.getWriter();

        if(wbOp.equals("add")){         
            System.out.println("Name: " + wbName + "\nDes: " + wbDes);
            if (wbName != null && wbName.length() > 0) {
                whiteboard.setName(wbName);
                whiteboard.setDescription(wbDes);
                user = ub.get(wbUser);              
                whiteboard.setOwner(user);            
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
        }

        if(wbPersonal.equals("personal")){
            personal(out);
        }else{
            overview(out);
        }

        out.flush();
        out.close();
    }

    private void overview(PrintWriter out){
        List<WhiteBoard> li = wbb.getAll();
        System.out.println(li.size());

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
                        "</td>");
            }
            out.println("<tr>");
        }
    }

    private void personal(PrintWriter out){
        List<WhiteBoard> li = wbb.getAll();
        System.out.println(li.size());

        for(WhiteBoard wb: li){
            if(wb.getOwner().getId().equals(user.getId())){
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
