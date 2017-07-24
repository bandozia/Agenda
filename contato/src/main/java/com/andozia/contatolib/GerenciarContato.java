package com.andozia.contatolib;


public class GerenciarContato {

    public static void main(String args[]){
        ContatoPF contatopf = new ContatoPF("Joesley", "joesley@gmail.com","66.666.666-06");
        contatopf.setSobrenome("Batista");
        contatopf.setApelido("Jojo");
        contatopf.setIdade(66);
        contatopf.setSexo("I");

        Endereco endereco = new Endereco(new TipoLogradouro("Avenida"), new TipoEndereco("Residencial"), "Brasil", "666",
                "Mansão", "Jd Europa", "666-6666",
                new Cidade("São Paulo", "São Paulo","SP", "Brasil", "BR", "R$","pt_br"));

        Telefone telefone = new Telefone("11","6666-6666","Residencial","Telemar");

        contatopf.addTelefone(telefone);
        contatopf.addEnedereco(endereco);

        System.out.println(contatopf.imprime());


        ContatoPJ contatopj = new ContatoPJ("Jojolandia", "jojo@jojocorp.com","Jojo LTDA", "12.666.666/0001-01");


        Endereco enderecoPj = new Endereco(new TipoLogradouro("Avenida"), new TipoEndereco("Comercial"), "Brasil", "666",
                "Mansão", "Jd Europa", "666-6666",
                new Cidade("São Paulo", "São Paulo","SP", "Brasil", "BR", "R$","pt_br"));


        Telefone telefonepj = new Telefone("11","6666-6666","Comercial","Telemar");

        contatopj.addTelefone(telefone);
        contatopj.addEnedereco(endereco);

        System.out.println(contatopj.imprime());

    }

}
