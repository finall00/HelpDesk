package br.com.helpDesk.dao;

import br.com.helpDesk.model.Docente;
import br.com.helpDesk.model.Laboratorio;
import br.com.helpDesk.model.Ticket;
import br.com.helpDesk.utils.Conversao;
import br.com.helpDesk.utils.SingleConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class TicketDAO implements GenericDAO{

      private Connection conexao;
    
      public TicketDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

    
    @Override
    public Boolean cadastrar(Object objeto) {
        Ticket oTicket = (Ticket) objeto;
        boolean retorno = false;
        if (oTicket.getIdTicket()== 0) {
            retorno = inserir(objeto);
        } else {
            retorno = alterar(objeto);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Ticket oTicket = (Ticket) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into ticket (descricao,imagem,idlaboratorio,iddocente,datacriacao,status) values (?,?,?,?,?,?)";
        
        try {
            stmt =  conexao.prepareStatement(sql);
            stmt.setString(1,oTicket.getDescricao());
            stmt.setString(2, oTicket.getImagem());
            stmt.setInt(3, oTicket.getLaboratorio().getIdLaboratorio());
            stmt.setInt(4, oTicket.getDocente().getIdDocente());
            stmt.setDate(5, new java.sql.Date(oTicket.getDataCriação().getTime()));
            stmt.setString(6, oTicket.getStatus());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception e) {
            try {
                System.out.println("Problema ao cadastrar ticeket Erro"+e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex) {
                System.out.println("Problema no rollback"+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean alterar(Object objeto) {
        Ticket oTicket = (Ticket) objeto;
        PreparedStatement stmt = null;
        String sql = "update ticket set descricao=?,imagem=?,idlaboratorio=?,iddocente=?,status=? where idticket=?";
        
        try {
            stmt =  conexao.prepareStatement(sql);
            stmt.setString(1,oTicket.getDescricao());
            stmt.setString(2, oTicket.getImagem());
            stmt.setInt(3, oTicket.getLaboratorio().getIdLaboratorio());
            stmt.setInt(4, oTicket.getDocente().getIdDocente());
            stmt.setString(5, oTicket.getStatus());
            stmt.setInt(6, oTicket.getIdTicket());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception e) {
               try {
                System.out.println("Problema ao alterar ticket; Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex) {
                System.out.println("Problema no rollback" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean excluir(int numero) {
           PreparedStatement stmt = null;

          try {
            TicketDAO oTicketDAO = new TicketDAO();
            Ticket oTicket = (Ticket) oTicketDAO.carregar(numero);
            String status = "A";
            if(oTicket.getStatus().equals(status)){
                status ="I";
            }else{
                status="A";
            }
            
            String sql = "update ticket set status=?, dataencerramento=? where idticket = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1,status);
            stmt.setDate(2, new java.sql.Date(Conversao.dataAtual().getTime()));
            stmt.setInt(3,oTicket.getIdTicket());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception e) {
            try {
                System.out.println("Problemas ao excluir tickket"+e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro no rollback "+ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idTicket = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ticket oTicket = null;
        String sql = "Select * from ticket where idticket=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,idTicket);
            rs = stmt.executeQuery();
            while(rs.next()){
                oTicket= new Ticket();
                oTicket.setIdTicket(rs.getInt("idticket"));
                oTicket.setDescricao(rs.getString("descricao"));
                oTicket.setImagem(rs.getString("imagem"));
                oTicket.setDataCriação(rs.getDate("datacriacao"));
                oTicket.setDataEncerramento(rs.getDate("dataencerramento"));
                oTicket.setStatus(rs.getString("status"));
                
                LaboratorioDAO oLaboratorioDAO = null;
                try {
                    oLaboratorioDAO = new LaboratorioDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar Laboratorio " + ex.getMessage());
                    ex.printStackTrace();
                }
                
                oTicket.setLaboratorio((Laboratorio) oLaboratorioDAO.carregar(rs.getInt("idlaboratorio")));
                
                 DocenteDAO oDocenteDAO = null;
                try {
                    oDocenteDAO = new DocenteDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar Docente" + ex.getMessage());
                    ex.printStackTrace();
                }
                
                oTicket.setDocente((Docente) oDocenteDAO.carregar(rs.getInt("iddocente")));
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar ticket! Erro:" + ex.getMessage());
            ex.printStackTrace();
        }  
        
        return oTicket;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> result = new ArrayList<>();
        Ticket oTicket = null;
        String sql = "Select * from ticket order by idticket";
        
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                oTicket= new Ticket();
                oTicket.setIdTicket(rs.getInt("idticket"));
                oTicket.setDescricao(rs.getString("descricao"));
                oTicket.setImagem(rs.getString("imagem"));
                oTicket.setDataCriação(rs.getDate("datacriacao"));
                oTicket.setDataEncerramento(rs.getDate("dataencerramento"));
                oTicket.setStatus(rs.getString("status"));
                
                LaboratorioDAO oLaboratorioDAO = null;
                try {
                    oLaboratorioDAO = new LaboratorioDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar Laboratorio " + ex.getMessage());
                    ex.printStackTrace();
                }
                
                oTicket.setLaboratorio((Laboratorio) oLaboratorioDAO.carregar(rs.getInt("idlaboratorio")));
                
                 DocenteDAO oDocenteDAO = null;
                try {
                    oDocenteDAO = new DocenteDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar Docente" + ex.getMessage());
                    ex.printStackTrace();
                }
                
                oTicket.setDocente((Docente) oDocenteDAO.carregar(rs.getInt("iddocente")));
                result.add(oTicket);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao listar ticket! Erro:" + ex.getMessage());
            ex.printStackTrace();
        }  
        return result;
    }
    
}
