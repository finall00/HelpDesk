package br.com.helpDesk.controller.Docente;

import br.com.helpDesk.dao.DocenteDAO;
import br.com.helpDesk.model.Docente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DocenteCadastrar", urlPatterns = {"/DocenteCadastrar"})
public class DocenteCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");   
            String mensagem = null;
        try {
            int idPessoa = Integer.parseInt(request.getParameter("iddocente"));
            int idDocente = Integer.parseInt(request.getParameter("iddocente"));
            String nomePessoa = request.getParameter("nomepessoa");
            String contato = request.getParameter("contato");
            int numeroMatricula =  Integer.parseInt(request.getParameter("numeromatricula"));
            String situacao = request.getParameter("situacao");
            
            /*
            int numeroMatricula =  231233;
            String situacao = "A";
            */
               
         
            
            Docente oDocente = new Docente(idDocente, numeroMatricula, situacao, idPessoa, nomePessoa, contato);
            //generic dao falta
            DocenteDAO dao = new DocenteDAO();
            
            if(dao.cadastrar(oDocente)){
                mensagem = "Docent cadastrado com sucesso";
            }else{
                mensagem = "Problemas ao cadastrar Dodente Verifique os dados informados e tente novamente";
            }
             request.setAttribute("mensagem", mensagem);
            response.sendRedirect("DocenteListar");
        } catch (Exception e) {
            System.out.println("Problemas na servelet de cadsatrar docente"+e.getMessage());
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
