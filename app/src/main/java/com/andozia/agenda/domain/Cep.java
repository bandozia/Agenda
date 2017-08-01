package com.andozia.agenda.domain;

import java.io.Serializable;

/**
 * Created by no3603 on 28/07/2017.
 */

public class Cep implements Serializable {

    private String cep;
    private String endereco;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
