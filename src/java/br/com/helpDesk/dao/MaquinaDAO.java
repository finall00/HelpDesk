package br.com.helpDesk.dao;

import br.com.helpDesk.model.Laboratorio;
import br.com.helpDesk.model.Maquina;
import br.com.helpDesk.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDAO implements GenericDAO {

    private Connection conexao;

    public MaquinaDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Maquina oMaquina = (Maquina) objeto;
        Boolean retorno = false;
        if (oMaquina.getIdMaquina() == 0) {
            retorno = this.inserir(oMaquina);
        } else {
            retorno = this.alterar(oMaquina);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Maquina oMaquina = (Maquina) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into maquina(numeromaquina, sistemaoperacional, idlaboratorio) values (?,?,?);";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oMaquina.getNumeroMaquina());
            stmt.setString(2, oMaquina.getOS());
            stmt.setInt(3, oMaquina.getLaboratorio().getIdLaboratorio());

            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar a Maquina! Erros: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:" + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }

    }

    @Override
    public Boolean alterar(Object objeto) {
        Maquina oMaquina = (Maquina) objeto;
        PreparedStatement stmt = null;
        String sql = "update maquina set numeromaquina=?, sistemaoperacional=?, idlaboratorio=? where idmaquina=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oMaquina.getNumeroMaquina());
            stmt.setString(2, oMaquina.getOS());
            stmt.setInt(3, oMaquina.getLaboratorio().getIdLaboratorio());
            stmt.setInt(4, oMaquina.getIdMaquina());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar a Maquina! Erros: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:" + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idMaquina = numero;
        PreparedStatement stmt = null;
        String sql = "delete from maquina where idmaquina = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMaquina);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir a Maquina! Erro: " + ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rollback " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idMaquina = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Maquina oMaquina = null;
        String sql = "select * from Maquina where idmaquina=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMaquina);
            rs = stmt.executeQuery();
            while (rs.next()) {
                oMaquina = new Maquina();
                oMaquina.setIdMaquina(rs.getInt("idmaquina"));
                oMaquina.setNumeroMaquina(rs.getInt("numeromaquina"));
                oMaquina.setOS(rs.getString("sistemaoperacional"));

                LaboratorioDAO oLaboratorioDAO = null;
                try {
                    oLaboratorioDAO = new LaboratorioDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar Laboratorio " + ex.getMessage());
                    ex.printStackTrace();
                }
                oMaquina.setLaboratorio((Laboratorio) oLaboratorioDAO.carregar(rs.getInt("idlaboratorio")));
            }
            return oMaquina;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Maquina! Erro:" + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from maquina order by numeroMaquina";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Maquina oMaquina = new Maquina();

                oMaquina.setIdMaquina(rs.getInt("idmaquina"));
                oMaquina.setNumeroMaquina(rs.getInt("numeromaquina"));
                oMaquina.setOS(rs.getString("sistemaoperacional"));

                LaboratorioDAO oLaboratorioDAO = null;
                try {
                    oLaboratorioDAO = new LaboratorioDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar Laboratorio " + ex.getMessage());
                    ex.printStackTrace();
                }
                oMaquina.setLaboratorio((Laboratorio) oLaboratorioDAO.carregar(rs.getInt("idlaboratorio")));
                resultado.add(oMaquina);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Maquina! Erro: " + ex.getMessage());
        }
        return resultado;
    }

    public List<Maquina> listar(int idLaboratorio) {
        List<Maquina> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from maquina where idlaboratorio = ? order by numeromaquina";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idLaboratorio);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Maquina oMaquina = new Maquina();
                oMaquina.setIdMaquina(rs.getInt("idmaquina"));
                oMaquina.setNumeroMaquina(rs.getInt("numeromaquina"));
                oMaquina.setOS(rs.getString("sistemaoperacional"));

                LaboratorioDAO oLaboratorioDAO = null;
                try {
                    oLaboratorioDAO = new LaboratorioDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar Laboratorio " + ex.getMessage());
                    ex.printStackTrace();
                }
                oMaquina.setLaboratorio((Laboratorio) oLaboratorioDAO.carregar(rs.getInt("idlaboratorio")));
                resultado.add(oMaquina);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Maquina! Erro: " + ex.getMessage());
        }
        return resultado;
    }
}
