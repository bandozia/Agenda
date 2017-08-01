package com.andozia.contatolib;


public class ContatoPF extends Contato{

    private String sobrenome;
    private String sexo;
    private String apelido;
    private int idade;
    private String cpf;

    private String cep;
    private String endereco;


    public ContatoPF(String nome, String email, String cpf) {
        super(nome, email);
        this.cpf = cpf;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public String imprime() {
        return super.imprime() + "ContatoPF{" +
                "sobrenome='" + sobrenome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", apelido='" + apelido + '\'' +
                ", idade=" + idade +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
