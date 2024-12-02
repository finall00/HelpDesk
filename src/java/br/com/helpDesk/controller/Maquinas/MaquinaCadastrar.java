
package br.com.helpDesk.controller.Maquinas;


import br.com.helpDesk.dao.GenericDAO;
import br.com.helpDesk.dao.MaquinaDAO;
import br.com.helpDesk.model.Laboratorio;
import br.com.helpDesk.model.Maquina;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MaquinaCadastrar", urlPatterns = {"/MaquinaCadastrar"})
public class MaquinaCadastrar extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        int idMaquina = Integer.parseInt(request.getParameter("idmaquina"));
        int idLaboratorio = Integer.parseInt(request.getParameter("idlaboratorio"));
        int numeroMaquina = Integer.parseInt(request.getParameter("numeromaquina"));
        String OS = request.getParameter("OS");
        String mensagem = null;
        
   
        
        try {
           
            Maquina oMaquina = new Maquina(idMaquina, numeroMaquina, OS, new Laboratorio(idLaboratorio, 0, 0, ""));
//             public Maquina(int idMaquina, int numeroMaquina, String OS, Laboratorio laboratorio) {
            
            GenericDAO dao = new MaquinaDAO();
            if (dao.cadastrar(oMaquina)){
                mensagem = "Maquina cadastrada com sucesso!";
            }else{
                mensagem = "Problemas ao cadastrar Maquina. Verique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            
//            response.sendRedirect("MaquinaListar");
              response.sendRedirect("LaboratorioVisualizar?idLaboratorio="+oMaquina.getLaboratorio().getIdLaboratorio());

        } catch (Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Maquina! Erro: "+ex.getMessage());
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
