package br.com.helpDesk.controller.Laboratorio;

import br.com.helpDesk.dao.GenericDAO;
import br.com.helpDesk.dao.LaboratorioDAO;
import br.com.helpDesk.dao.MaquinaDAO;
import br.com.helpDesk.model.Laboratorio;
import br.com.helpDesk.model.Maquina;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LaboratorioListar", urlPatterns = {"/LaboratorioListar"})
public class LaboratorioListar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            GenericDAO dao = new LaboratorioDAO();
           String json = request.getParameter("Json");
            if (json != null && json.equals("True")) {

                List<Object> lstLaboratorio = dao.listar();

                Gson gson = new Gson();
                String jsonMaquinas = gson.toJson(lstLaboratorio);

                response.setCharacterEncoding("iso-8859-1");
                response.getWriter().write(jsonMaquinas);
            } else {

                request.setAttribute("laboratorios", dao.listar());
                request.getRequestDispatcher("/cadastros/laboratorio/laboratorio.jsp").forward(request, response);
            }

        } catch (Exception e) {

            System.out.println("Problema no Servlet ao listar laboratorio" + e.getMessage());
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
