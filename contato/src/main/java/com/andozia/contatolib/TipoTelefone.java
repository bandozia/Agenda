package com.andozia.contatolib;

/**
 * Created by no3603 on 04/07/2017.
 */

public class TipoTelefone {
    private String nome;


    public TipoTelefone(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TipoTelefone{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
