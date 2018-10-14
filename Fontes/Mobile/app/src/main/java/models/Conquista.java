package models;

import java.io.Serializable;
import java.util.Date;

public class Conquista implements Serializable {

    private int id;
    private ConquistaConfig conquistaConfig;
    private Usuario usuario;
    private Date data;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public ConquistaConfig getConquistaConfig(){
        return conquistaConfig;
    }

    public void setConquistaConfig(ConquistaConfig conquistaConfig){
        this.conquistaConfig = conquistaConfig;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Date getData(){
        return data;
    }

    public void setData(Date data){
        this.data = data;
    }


    public Conquista(){}

    public Conquista(int id, ConquistaConfig conquistaConfig, Usuario usuario, Date data){
        this.id = id;
        this.conquistaConfig = conquistaConfig;
        this.usuario = usuario;
        this.data = data;
    }


    @Override
    public String toString() {
        return this.getConquistaConfig().getTitulo();
    }
}
