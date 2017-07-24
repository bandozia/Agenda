package com.andozia.contatolib;

/**
 * Created by no3603 on 04/07/2017.
 */

public class UF {

    private String nome;
    private String sigla;
    private Pais pais;

    public UF(String nome, String sigla, Pais pais) {
        this.nome = nome;
        this.sigla = sigla;
        this.pais = pais;
    }

    public UF(String nome, String sigla, String nomePais, String siglaPais, String moeda, String lingua) {
        this(nome,sigla,new Pais(nomePais, siglaPais, moeda, lingua));
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "UF{" +
                "nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", pais=" + pais +
                '}';
    }
}
