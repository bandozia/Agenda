package com.andozia.contatolib;

/**
 * Created by no3603 on 04/07/2017.
 */

public class Pais {

    private String nome;
    private String sigla;
    private String moeda;
    private String lingua;

    public Pais(String nome, String sigla, String moeda, String lingua) {
        this.nome = nome;
        this.sigla = sigla;
        this.moeda = moeda;
        this.lingua = lingua;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", moeda='" + moeda + '\'' +
                ", lingua='" + lingua + '\'' +
                '}';
    }
}
