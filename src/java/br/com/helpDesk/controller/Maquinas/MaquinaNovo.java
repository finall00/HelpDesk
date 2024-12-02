
package br.com.helpDesk.controller.Maquinas;


import br.com.helpDesk.dao.GenericDAO;
import br.com.helpDesk.dao.LaboratorioDAO;
import br.com.helpDesk.dao.MaquinaDAO;
import br.com.helpDesk.model.Maquina;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MaquinaNovo", urlPatterns = {"/MaquinaNovo"})
public class MaquinaNovo extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=iso-8859-1");
        LaboratorioDAO oLaboratorioDAO = new LaboratorioDAO();
         Maquina oMaquina = new Maquina();
         GenericDAO dao = new MaquinaDAO();
        String idLaboratorio = request.getParameter("laboratorioId");
        if(idLaboratorio != null){
           request.setAttribute("idLab", idLaboratorio);
           request.setAttribute("showModalVizualizar", "true");
           request.setAttribute("maquina", oMaquina);
           request.setAttribute("maquinas", dao.listar());
           request.setAttribute("laboratorios", oLaboratorioDAO.listar());
           request.getRequestDispatcher("/cadastros/laboratorio/laboratorioVisualizar.jsp").forward(request, response);
//           response.sendRedirect("LaboratorioVisualizar?idLaboratorio="+idLaboratorio);
        }else{
            request.setAttribute("maquina", oMaquina);
            request.setAttribute("maquinas", dao.listar());
            request.setAttribute("laboratorios", oLaboratorioDAO.listar());
            request.setAttribute("showModal", "true");
            request.getRequestDispatcher("/cadastros/maquina/maquina.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MaquinaNovo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MaquinaNovo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
