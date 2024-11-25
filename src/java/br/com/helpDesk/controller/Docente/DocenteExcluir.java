package br.com.helpDesk.controller.Docente;

import br.com.helpDesk.dao.DocenteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DocenteExcluir", urlPatterns = {"/DocenteExcluir"})
public class DocenteExcluir extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        int idDocente = Integer.parseInt(request.getParameter("idDocente"));
        String mensagem = null;
        
        try {
            DocenteDAO dao = new DocenteDAO();
            if(dao.excluir(idDocente)){
                mensagem ="Doente excluido com sucesso";
            }else{
                mensagem ="Docete excluido com sucesso";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("DocenteListar");
        } catch (Exception e) {
            System.out.println("Problemas na servelet de excluir docente"+e.getMessage());
            e.printStackTrace();
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
