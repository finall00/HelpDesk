package br.com.helpDesk.model;

import br.com.helpDesk.utils.Conversao;
import java.util.Date;

public class Ticket {
    private int idTicket;
    private String descricao;
    private String imagem;
    private Laboratorio laboratorio;
    private Docente docente;
    private Date dataCriação;
    private Date dataEncerramento;
    private String status;

    
    public Ticket(){
        this.idTicket=0;
        this.descricao ="";
        this.laboratorio = new Laboratorio();
        this.docente = new Docente();
        this.dataCriação = Conversao.dataAtual();
        this.status = "A";
    }
    
    public Ticket(int idTicket, String descricao, String imagem, Laboratorio laboratorio, Docente docente, Date dataCriação, Date dataEncerramento, String status) {
        this.idTicket = idTicket;
        this.descricao = descricao;
        this.imagem = imagem;
        this.laboratorio = laboratorio;
        this.docente = docente;
        this.dataCriação = dataCriação;
        this.dataEncerramento = dataEncerramento;
        this.status = status;
    }
    
    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Date getDataCriação() {
        return dataCriação;
    }

    public void setDataCriação(Date dataCriação) {
        this.dataCriação = dataCriação;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
