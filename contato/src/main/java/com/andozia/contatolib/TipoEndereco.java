package com.andozia.contatolib;

/**
 * Created by no3603 on 05/07/2017.
 */

public class TipoEndereco {

    private String nome;

    public TipoEndereco(String nome) {
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
        return "TipoEndereco{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
