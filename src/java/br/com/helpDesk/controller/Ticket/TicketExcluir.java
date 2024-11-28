package br.com.helpDesk.controller.Ticket;

import br.com.helpDesk.dao.GenericDAO;
import br.com.helpDesk.dao.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TicketExcluir", urlPatterns = {"/TicketExcluir"})
public class TicketExcluir extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        int idTicket = Integer.parseInt(request.getParameter("idTicket"));
        String msg = null;
      try {
            GenericDAO dao = new TicketDAO();
            if (dao.excluir(idTicket)) {
                msg = "Ticket Excluido com sucesso!";
                
            } else{
                msg = "Problemas ao excluir ticket";
            }
            request.setAttribute("mensagem", msg);
            response.sendRedirect("TicketListar");
        } catch (Exception e) {
            System.out.println("Erro ao excluir TicketListar! Erro: "+ e.getMessage());
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
