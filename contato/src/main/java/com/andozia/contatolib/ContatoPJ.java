package com.andozia.contatolib;

/**
 * Created by no3603 on 06/07/2017.
 */

public class ContatoPJ extends Contato {

    private String rasaoSocial;
    private String cnpj;

    public ContatoPJ(String nome, String email, String rasaoSocial, String cnpj) {
        super(nome, email);

        this.rasaoSocial = rasaoSocial;
        this.cnpj = cnpj;
    }


    public String getRasaoSocial() {
        return rasaoSocial;
    }

    public void setRasaoSocial(String rasaoSocial) {
        this.rasaoSocial = rasaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String imprime() {
        return super.imprime() + "ContatoPJ{" +
                "rasaoSocial='" + rasaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
