package com.andozia.contatolib;

/**
 * Created by no3603 on 04/07/2017.
 */

public class Cidade {

    private String nome;
    private UF uf;

    public Cidade(String nome, UF uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public Cidade(String nome, String nomeUf, String siglaUf, String nomePais, String siglaPais, String moeda, String lingua) {
        this(nome, new UF(nomeUf,siglaUf,nomePais,siglaPais,moeda,lingua));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "nome='" + nome + '\'' +
                ", uf=" + uf +
                '}';
    }
}
