package br.com.helpDesk.controller.Ticket;

import br.com.helpDesk.dao.TicketDAO;
import br.com.helpDesk.model.Docente;
import br.com.helpDesk.model.Laboratorio;
import br.com.helpDesk.model.Ticket;
import br.com.helpDesk.utils.Conversao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "TicketCadastrar", urlPatterns = {"/TicketCadastrar"})
public class TicketCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String mensagem = null;  
        int idLaboratorio = Integer.parseInt(request.getParameter("idlaboratorio"));
        int idDocente = Integer.parseInt(request.getParameter("iddocente"));
        try {
            Ticket oTicket = new Ticket();
            oTicket.setIdTicket(Integer.parseInt(request.getParameter("idticket")));
            oTicket.setDescricao(request.getParameter("descricao"));
            oTicket.setImagem(request.getParameter("imagem"));
            oTicket.setLaboratorio(new Laboratorio(idLaboratorio,0,0,""));
            oTicket.setDocente(new Docente(idDocente,0,""));     
            oTicket.setImagem(request.getParameter("imagem"));
            String status = request.getParameter("status");
            
            TicketDAO oTicketDAO = new TicketDAO();
            
            if(oTicketDAO.cadastrar(oTicket)){
                mensagem = "Ticket cadastrada com sucesso!";
            }else{
                mensagem = "Problemas ao cadastrar Ticket. Verique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("TicketListar");
        } catch (Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar ticket! Erro: "+ex.getMessage());
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
