package models;

import java.io.Serializable;
import java.util.Date;

public class Resposta implements Serializable {

    private int id;
    private Alternativa alternativa;
    private Usuario usuario;
    private double pontuacao;
    private Date data;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Alternativa getAlternativa(){
        return alternativa;
    }

    public void setAlternativa(Alternativa alternativa){
        this.alternativa = alternativa;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public double getPontuacao(){
        return pontuacao;
    }

    public void setPontuacao(double pontuacao){
        this.pontuacao = pontuacao;
    }

    public Date getData(){
        return data;
    }

    public void setDate(Date data){
        this.data = data;
    }


    public Resposta(){}

    public Resposta(int id, Alternativa alternativa, Usuario usuario, double pontuacao, Date data){
        this.id = id;
        this.alternativa = alternativa;
        this.usuario = usuario;
        this.pontuacao = pontuacao;
        this.data = data;
    }

}
