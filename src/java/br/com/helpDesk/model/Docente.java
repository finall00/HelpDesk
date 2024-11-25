package br.com.helpDesk.model;


public class Docente extends Pessoa{
    private int idDocente;
    private int numeroMatricula;
    private String situacao;

    public Docente(int idDocente, int numeroMatricula, String situacao, int idPessoa, String nomePessoa, String contato) {
        super(idPessoa, nomePessoa, contato);
        this.idDocente = idDocente;
        this.numeroMatricula = numeroMatricula;
        this.situacao = situacao;
    }
    
    /*
      public Docente docenteVazio() throws ParseException{
        Docente oDocente = new Docente(0, 0, "A", 0, "", "");
        return  oDocente;
    }
    */
  
    public Docente(){
        super(0, "", ""); // Chamando o construtor de Pessoa com valores padrão
        this.idDocente =0;
        this.numeroMatricula = 0;
        this.situacao = "A";
    }
    
    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}