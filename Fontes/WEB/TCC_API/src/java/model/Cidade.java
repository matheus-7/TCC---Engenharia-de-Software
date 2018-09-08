
package model;

public class Cidade {

    private int id;
    private String nome;
    private Estado estado;
    
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String Nome){
        this.nome = nome;
    }
    
    public Estado getEstado(){
        return estado;
    }
    
    public void setEstado(Estado estado){
        this.estado = estado;
    }
    
    
    public Cidade(){};
    
    public Cidade(int id, String nome, Estado estado){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
    
}
