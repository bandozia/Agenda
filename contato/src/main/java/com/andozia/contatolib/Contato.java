package com.andozia.contatolib;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Contato implements Serializable {

    private long id;

    private String nome;
    private String email;

    private List<Endereco> enderecos;
    private List<Telefone> telefones;

    private String avatar;

    public Contato(String nome, String email){
        this.nome = nome;
        this.email = email;

        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
    }


    public void addTelefone(Telefone telefone){
        this.telefones.add(telefone);
    }

    public void addEnedereco(Endereco endereco){
        this.enderecos.add(endereco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String imprime() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", enderecos=" + enderecos +
                ", telefones=" + telefones +
                '}';
    }
}
