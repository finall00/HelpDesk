/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.helpDesk.controller.Laboratorio;

import br.com.helpDesk.dao.GenericDAO;
import br.com.helpDesk.dao.LaboratorioDAO;
import br.com.helpDesk.model.Laboratorio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author finall
 */
@WebServlet(name = "LaboratorioCadastrar", urlPatterns = {"/LaboratorioCadastrar"})
public class LaboratorioCadastrar extends HttpServlet {

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
        response.setContentType("text/html;charset=iso-8859-1");
        int idLaboratorio = Integer.parseInt(request.getParameter("idlaboratorio"));
        int numeroSala = Integer.parseInt(request.getParameter("numerosala"));
        int qtnMaquinas = Integer.parseInt(request.getParameter("qtnmaquinas"));
        String Status = request.getParameter("status");
        String msg = null;

        Laboratorio oLaboratorio = new Laboratorio(idLaboratorio, numeroSala, qtnMaquinas, Status);

        try {
            GenericDAO dao = new LaboratorioDAO();
            if (dao.cadastrar(oLaboratorio)) {
                msg = "Estado cadastrar com sucesso!";
            } else {
                msg = "Problemas ao cadastrar Laboratorio. Verifique os dados";
            }
            request.setAttribute("mensagem", msg);
            response.sendRedirect("LaboratorioListar");
        } catch (Exception e) {
            System.out.println("Problema no servlet ao Cadastrar Laboratorio! Erro:" + e.getMessage());
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
