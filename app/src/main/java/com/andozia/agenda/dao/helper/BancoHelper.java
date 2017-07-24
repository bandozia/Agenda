package com.andozia.agenda.dao.helper;


public class BancoHelper {

    public static String sqlTabelaContato(){
        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE contato( ");
        sb.append(" _id integer primary key autoincrement, ");
        sb.append(" nome varchar(100) not null, ");
        sb.append(" sobrenome varchar(100) not null, ");
        sb.append(" email varchar(100) not null, ");
        sb.append(" cpf char(14) not null, ");
        sb.append(" avatar varchar(150) ");
        sb.append(" ) ");

        return sb.toString();
    }

}
