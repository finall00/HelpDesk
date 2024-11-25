
package br.com.helpDesk.model;


public class Laboratorio {
    private int idLaboratorio;
    private int numeroSala;
    private int qtnMaquinas;
    private String Status;

    public Laboratorio() {
        this.idLaboratorio = 0;
        this.numeroSala = 0;
        this.qtnMaquinas = 0;
        this.Status = "A";
    }

    public Laboratorio(int idLaboratorio, int numeroSala, int qtnMaquinas, String Status) {
        this.idLaboratorio = idLaboratorio;
        this.numeroSala = numeroSala;
        this.qtnMaquinas = qtnMaquinas;
        this.Status = Status;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getQtnMaquinas() {
        return qtnMaquinas;
    }

    public void setQtnMaquinas(int qtnMaquinas) {
        this.qtnMaquinas = qtnMaquinas;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
