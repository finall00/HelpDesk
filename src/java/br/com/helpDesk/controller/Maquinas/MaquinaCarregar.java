
package br.com.helpDesk.controller.Maquinas;


import br.com.helpDesk.dao.LaboratorioDAO;
import br.com.helpDesk.dao.MaquinaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MaquinaCarregar", urlPatterns = {"/MaquinaCarregar"})
public class MaquinaCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            int idMaquina = Integer.parseInt(request.getParameter("idMaquina"));
            MaquinaDAO oMaquinaDAO = new MaquinaDAO();
            request.setAttribute("maquina", oMaquinaDAO.carregar(idMaquina));
            LaboratorioDAO oLaboratorioDAO = new LaboratorioDAO();
            request.setAttribute("laboratorios", oLaboratorioDAO.listar());
             request.setAttribute("showModal", "true");
            request.getRequestDispatcher("/cadastros/maquina/maquina.jsp").forward(request, response);
        } catch (Exception ex){
            System.out.println("Erro carrregar maquina "+ex.getMessage());
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
