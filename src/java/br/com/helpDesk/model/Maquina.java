package br.com.helpDesk.model;

public class Maquina {

    private int idMaquina;
    private int numeroMaquina;
    private String OS;
    private Laboratorio laboratorio;

    public Maquina() {
        this.idMaquina = 0;
        this.numeroMaquina = 0;
        this.OS = "";
      this.laboratorio = new Laboratorio();
    }

    public Maquina(int idMaquina, int numeroMaquina, String OS, Laboratorio laboratorio) {
        this.idMaquina = idMaquina;
        this.numeroMaquina = numeroMaquina;
        this.OS = OS;
        this.laboratorio = laboratorio;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getNumeroMaquina() {
        return numeroMaquina;
    }

    public void setNumeroMaquina(int numeroMaquina) {
        this.numeroMaquina = numeroMaquina;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

 

}
