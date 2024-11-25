package br.com.helpDesk.dao;

import br.com.helpDesk.model.Docente;
import br.com.helpDesk.utils.SingleConnection;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocenteDAO implements GenericDAO{

    private Connection conexao;
        
    public DocenteDAO() throws Exception{
       conexao = SingleConnection.getConnection();
    }
    
    @Override
    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try {
            Docente oDocente = (Docente) objeto; 
            
            if(oDocente.getIdDocente()==0){
                retorno = this.inserir(oDocente);
            }else{
                retorno = this.alterar(oDocente);
            }
            
        } catch (Exception ex) {
            System.out.println("Erro ao incluir codente "+ex.getMessage());
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Docente oDocente  = (Docente) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into docente(numeroMatricula,situacao,fkPessoa) values (?,?,?)";
        
        try {
            PessoaDAO oPessoaDAO = new PessoaDAO();
            
            int idPessoa = oPessoaDAO.cadastrar(oDocente);
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oDocente.getNumeroMatricula());
            stmt.setString(2, "A");
            stmt.setInt(3, idPessoa);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception e) {
            try {
                System.out.println("problemas ao cadastrar docente "+e.getMessage());
                e.printStackTrace();;
                conexao.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro ao executar rollback "+ex.getMessage());
                ex.printStackTrace();
            }
               return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
         Docente oDocente  = (Docente) objeto;
        PreparedStatement stmt = null;
        
        String sql = "update docente set numeroMatricula =?,situacao=? where idDocente = ?";
        try {
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.cadastrar(oDocente);
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oDocente.getNumeroMatricula());
            stmt.setString(2, oDocente.getSituacao());
            stmt.setInt(3, oDocente.getIdDocente());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception e) {
            try {
                System.out.println("Problemas ao alterar docente: "+e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro ao executar rollback "+ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        PreparedStatement stmt = null;
        
        try {
            DocenteDAO oDocenteDAO = new DocenteDAO();
            Docente oDocente = (Docente) oDocenteDAO.carregar(numero);
            String situacao = "A";
            if(oDocente.getSituacao().equals(situacao)){
                situacao ="I";
            }else{
                situacao="A";
            }
            
            String sql = "update docente set situacao=? where idDocente = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1,situacao);
            stmt.setInt(2,oDocente.getIdDocente());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception e) {
            try {
                System.out.println("Problemas ao excluir docente"+e.getMessage());
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
        int idDocente = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Docente oDocente = null;
        String sql = " select * from docente c, pessoa p where c.fkPessoa = p.idPessoa and c.idDocente=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDocente);
            rs = stmt.executeQuery();
            while(rs.next()){
                oDocente = new Docente(
                        rs.getInt("idDocente"),
                        rs.getInt("numeroMatricula"),
                        rs.getString("situacao"),
                        rs.getInt("idPessoa"),
                        rs.getString("nomePessoa"),
                        rs.getString("contato")
                );
            }
        } catch (SQLException e) {
            System.out.println("Problemas ao carregar docente"+e.getMessage());
            e.printStackTrace();
        }
        return oDocente;
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select p.*, c.idDocente, c.numeroMatricula , c.situacao from docente c, pessoa p where c.fkPessoa = p.idPessoa order by idPessoa";
    
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Docente  oDocente = new Docente(
                        rs.getInt("idDocente"),
                        rs.getInt("numeroMatricula"),
                        rs.getString("situacao"),
                        rs.getInt("idPessoa"),
                        rs.getString("nomePessoa"),
                        rs.getString("contato")
                );
                resultado.add(oDocente);    
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar docente"+ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }
    
}