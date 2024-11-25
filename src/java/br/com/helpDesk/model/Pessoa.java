package br.com.helpDesk.model;

public class Pessoa {
    private int idPessoa;
    private String nomePessoa;
    private String contato;

    public Pessoa(int idPessoa, String nomePessoa, String contato) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.contato = contato;
    }
    
    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
    
    
}