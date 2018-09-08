package models;

import java.io.Serializable;

public class ConquistaConfig implements Serializable {

    private int id;
    private String titulo;
    private String descricao;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }


    public ConquistaConfig(){}

    public ConquistaConfig(int id, String titulo, String descricao){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

}
