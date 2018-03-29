
package models;

import java.util.Date;

public class Curso {

    private int id;
    private String nome;
    private Date dataCadastro;
    
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public Date getDataCadastro(){
        return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro){
        this.dataCadastro = dataCadastro;
    }
    
    
    public Curso(){}
    
    public Curso(int id, String nome, Date dataCadastro){
        this.id = id;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
    }
    
}
