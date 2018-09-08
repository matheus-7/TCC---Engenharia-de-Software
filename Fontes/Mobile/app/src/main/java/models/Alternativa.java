package models;

import java.io.Serializable;

public class Alternativa implements Serializable {

    private int id;
    private String descricao;
    private Boolean correta;
    private Questao questao;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Boolean getCorreta(){
        return correta;
    }

    public void setCorreta(Boolean correta){
        this.correta = correta;
    }

    public Questao getQuestao(){
        return questao;
    }

    public void setQuestao(Questao questao){
        this.questao = questao;
    }


    public Alternativa(){}

    public Alternativa(int id, String descricao, Boolean correta, Questao questao){
        this.id = id;
        this.descricao = descricao;
        this.correta = correta;
        this.questao = questao;
    }

}
