
package model;

import java.util.Date;
import java.util.List;

public class Universidade {

    private int id;
    private String nome;
    private Cidade cidade;
    private List<Curso> cursos;
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
    
    public Cidade getCidade(){
        return cidade;
    }
    
    public void setCidade(Cidade cidade){
        this.cidade = cidade;
    }
    
    public List<Curso> getCursos(){
        return cursos;
    }
        
    public Date getDataCadastro(){
        return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro){
        this.dataCadastro = dataCadastro;
    }
    
    
    public Universidade(){}
    
    public Universidade(int id, String nome, Cidade cidade, List<Curso> cursos, Date dataCadastro){
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.cursos = cursos;
        this.dataCadastro = dataCadastro;
    }

}
