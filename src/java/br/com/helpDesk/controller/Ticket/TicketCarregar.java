package br.com.helpDesk.controller.Ticket;

import br.com.helpDesk.dao.DocenteDAO;
import br.com.helpDesk.dao.LaboratorioDAO;
import br.com.helpDesk.dao.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TicketCarregar", urlPatterns = {"/TicketCarregar"})
public class TicketCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            int idTicket = Integer.parseInt(request.getParameter("idticket"));
            TicketDAO oTicketDAO = new TicketDAO();
            request.setAttribute("ticket", oTicketDAO.carregar(idTicket));
            LaboratorioDAO oLaboratorioDAO = new LaboratorioDAO();
            request.setAttribute("laboratorios", oLaboratorioDAO.listar());
            DocenteDAO oDocenteDAO = new DocenteDAO();
            request.setAttribute("docentes", oDocenteDAO.listar());
            request.setAttribute("showModal", "true");
            request.getRequestDispatcher("/cadastros/ticket/ticket.jsp").forward(request, response);
        }catch (Exception ex){
            System.out.println("Erro carrregar ticket "+ex.getMessage());
            ex.printStackTrace();
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
