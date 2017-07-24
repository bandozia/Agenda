package com.andozia.contatolib;

/**
 * Created by no3603 on 04/07/2017.
 */

public class Telefone {

    private String ddd;
    private String numero;
    private TipoTelefone tipo;
    private String operadora;

    public Telefone(String ddd, String numero, TipoTelefone tipo, String operadora) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
        this.operadora = operadora;
    }

    public Telefone(String ddd, String numero, String nomeTipoTelefone, String operadora) {
        this(ddd,numero, new TipoTelefone(nomeTipoTelefone), operadora);
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                ", tipo=" + tipo +
                ", operadora='" + operadora + '\'' +
                '}';
    }
}
