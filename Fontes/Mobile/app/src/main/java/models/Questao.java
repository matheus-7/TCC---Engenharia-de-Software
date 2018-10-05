package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Questao implements Serializable {

    private int id;
    private String descricao;
    private Boolean ativa;
    private AreaDeConhecimento areaConhecimento;
    private List<Alternativa> alternativas;
    private Date dataCadastro;


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

    public Boolean getAtiva(){
        return ativa;
    }

    public void setAtiva(Boolean ativa){
        this.ativa = ativa;
    }

    public AreaDeConhecimento getAreaConhecimento(){
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaDeConhecimento areaConhecimento){
        this.areaConhecimento = areaConhecimento;
    }

    public List<Alternativa> getAlternativas(){
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas){
        this.alternativas = alternativas;
    }

    public Date getDataCadastro(){
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro){
        this.dataCadastro = dataCadastro;
    }


    public Questao(){};

    public Questao(int id, String descricao, Boolean ativa, AreaDeConhecimento areaConhecimento,
                   List<Alternativa> alternativas, Date dataCadastro){
        this.id = id;
        this.descricao = descricao;
        this.ativa = ativa;
        this.areaConhecimento = areaConhecimento;
        this.alternativas = alternativas;
        this.dataCadastro = dataCadastro;
    }

}
