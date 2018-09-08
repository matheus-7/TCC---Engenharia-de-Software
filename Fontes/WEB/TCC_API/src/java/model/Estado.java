
package model;

import java.util.List;

public class Estado {
    
    private int id;
    private String nome;
    private String uf;
    private List<Cidade> cidades;
    

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
    
    public String getUf(){
        return uf;
    }
    
    public void setUf(String uf){
        this.uf = uf;
    }
    
    public List<Cidade> getCidades(){
        return cidades;
    }
       
    
    public Estado(){}
    
    public Estado(int id, String nome, String uf, List<Cidade> cidades){
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.cidades = cidades;
    }
    
}
