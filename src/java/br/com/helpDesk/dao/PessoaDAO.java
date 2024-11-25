package br.com.helpDesk.dao;

import br.com.helpDesk.model.Pessoa;
import br.com.helpDesk.utils.SingleConnection;
import java.sql.Connection;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
        
public class PessoaDAO {
     private Connection conexao;
        
       public PessoaDAO() throws Exception{
           conexao = SingleConnection.getConnection();
       }
       
       public int cadastrar(Object objeto) {
            Pessoa oPessoa = (Pessoa) objeto;
            int retorno = 0;
          
            if(oPessoa.getIdPessoa() == 0){
                retorno = this.inserir(oPessoa);
            }else{
                retorno = this.alterar(oPessoa);
            }
            return retorno;
       }
       
        public int inserir(Object objeto){
            Pessoa oPessoa = (Pessoa) objeto;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Integer idPessoa = null;
            
            String sql = "insert into pessoa(nomePessoa,contato) values (?,?)  RETURNING idpessoa";

            try {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, oPessoa.getNomePessoa());
                stmt.setString(2, oPessoa.getContato());
                rs =stmt.executeQuery();
                conexao.commit();
                
                while(rs.next()){
                    idPessoa = rs.getInt("idPessoa");
                }
            } catch (SQLException e) {
                try {
                    System.out.println("Problemas ao cadastrar o pessoa! ERRO: "+e.getMessage());
                    e.printStackTrace();
                    conexao.rollback();
                } catch (SQLException ex) {
                    System.out.println("Problemas ao executar rollback"+e.getMessage());
                    e.printStackTrace();
                }
                
            }
            return idPessoa;
       }
        
        public int alterar(Object objeto) {
            Pessoa oPessoa = (Pessoa) objeto;
            PreparedStatement stmt = null;
            Integer idPessoa= oPessoa.getIdPessoa();
            
            String sql = "update pessoa set nomePessoa =?,contato=? where idPessoa = ?";
            try {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, oPessoa.getNomePessoa());
                stmt.setString(2, oPessoa.getContato());
                stmt.setInt(3, oPessoa.getIdPessoa());
                stmt.execute();
                conexao.commit();
            } catch (SQLException e) {
                try {
                    System.out.println("Problemas ao alterar pessoa: "+e.getMessage());
                    e.printStackTrace();
                    conexao.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro ao dar rollback"+ex.getMessage());
                    ex.printStackTrace();
                }
            }
         return idPessoa;
    }
        
        public Pessoa carregar(int id) {
            int idPessoa = id;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Pessoa oPessoa = null;
            String sql = "select * from pessoa where idPessoa=?";

            try {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idPessoa);
                rs = stmt.executeQuery();
                while(rs.next()){
                    oPessoa = new Pessoa(
                            rs.getInt("idPessoa"),
                            rs.getString("nomePessoa"),
                            rs.getString("contato")
                    );
                }
                return oPessoa;
            } catch (SQLException ex) {
                System.out.println("Problemas ao carregar pessoa!"+ ex.getMessage());
                return null;
            }
    }
}