package br.com.helpDesk.dao;

import br.com.helpDesk.model.Laboratorio;
import br.com.helpDesk.utils.SingleConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LaboratorioDAO implements GenericDAO {

    private Connection conexao;

    public LaboratorioDAO() {
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Laboratorio oLaboratorio = (Laboratorio) objeto;
        Boolean rs = false;
        if (oLaboratorio.getIdLaboratorio() == 0) {
            rs = this.inserir(oLaboratorio);
        } else {
            rs = this.alterar(oLaboratorio);
        }
        return rs;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Laboratorio oLaboratorio = (Laboratorio) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into laboratorio(numeroSala, qtnMaquinas, status) values (?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oLaboratorio.getNumeroSala());
            stmt.setInt(2, oLaboratorio.getQtnMaquinas());
            stmt.setString(3, oLaboratorio.getStatus());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar o Laboratorio! Erro: " + ex.getMessage());
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erros:" + e.getMessage());
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Laboratorio oLaboratorio = (Laboratorio) objeto;
        PreparedStatement stmt = null;

        String sql = "update laboratorio set numeroSala=?, qtnMaquinas=?, status=? where idlaboratorio = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oLaboratorio.getNumeroSala());
            stmt.setInt(2, oLaboratorio.getQtnMaquinas());
            stmt.setString(3, oLaboratorio.getStatus());
            stmt.setInt(4, oLaboratorio.getIdLaboratorio());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar a ");
            } catch (Exception e) {
                System.out.println("Erro:" + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idLab = numero;
        PreparedStatement stmt = null;

        String sql = "delete from laboratorio where idlaboratorio = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idLab);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Laboratorio! Erro" + ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int id = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from laboratorio where idLaboratorio = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Laboratorio oLaboratorio = new Laboratorio();
            while (rs.next()) {
                oLaboratorio.setIdLaboratorio(rs.getInt("idlaboratorio"));
                oLaboratorio.setNumeroSala(rs.getInt("numerosala"));
                oLaboratorio.setQtnMaquinas(rs.getInt("qtnmaquinas"));
                oLaboratorio.setStatus(rs.getString("status"));
            }
            return oLaboratorio;
        } catch (Exception e) {
            System.out.println("Problema ao listar Estado! Erro" + e.getMessage());
            return false;
        }

    }

    @Override
    public List<Object> listar() {
        List<Object> result = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from laboratorio order by idlaboratorio";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Laboratorio oLaboratorio = new Laboratorio();
                oLaboratorio.setIdLaboratorio(rs.getInt("idlaboratorio"));
                oLaboratorio.setNumeroSala(rs.getInt("numerosala"));
                oLaboratorio.setQtnMaquinas(rs.getInt("qtnmaquinas"));
                oLaboratorio.setStatus(rs.getString("status"));
                result.add(oLaboratorio);
            }
        } catch (SQLException e) {
            System.out.println("Problema ao listar Estado! Erro" + e.getMessage());
        }

        return result;
    }

}
