
package models;

import java.util.Date;

public class Avaliacao {

    private int id;
    private double nota;
    private String mensagem;
    private Usuario usuario;
    private Date data;
    
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public double getNota(){
        return nota;
    }
    
    public void setNota(double nota){
        this.nota = nota;
    }
    
    public String getMensagem(){
        return mensagem;
    }
    
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
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
    
    
    public Avaliacao(){}
    
    public Avaliacao(int id, double nota, String mensagem, Usuario usuario, Date data){
        this.id = id;
        this.nota = nota;
        this.mensagem = mensagem;
        this.usuario = usuario;
        this.data = data;
    }
    
}
