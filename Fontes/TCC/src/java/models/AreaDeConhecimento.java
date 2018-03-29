
package models;

import java.util.List;

public class AreaDeConhecimento {

    private int id;
    private String nome;
    private List<Questao> questoes;
    
    
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
    
    public List<Questao> getQuestoes(){
        return questoes;
    }
    
    
    public AreaDeConhecimento(){}
    
    public AreaDeConhecimento(int id, String nome, List<Questao> questoes){
        this.id = id;
        this.nome = nome;
        this.questoes = questoes;
    }
    
}
