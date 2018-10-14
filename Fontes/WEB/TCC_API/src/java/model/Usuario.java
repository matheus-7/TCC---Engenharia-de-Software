
package model;

import java.util.Date;
import java.util.List;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String direito;
    private Boolean ativo;
    private String foto;
    private Cidade cidade;
    private Date dataNascimento;
    private Universidade universidade;
    private Curso curso;
    private List<Conquista> conquistas;
    private List<Resposta> respostas;
    private Date dataCadastro;
    private Boolean audioAtivo;
    private int posicaoRanking;
    
    
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
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getDireito(){
        return direito;
    }
    
    public void setDireito(String direito){
        this.direito = direito;
    }
    
    public Boolean getAtivo(){
        return ativo;
    }
    
    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }
    
    public String getFoto(){
        return foto;
    }
    
    public void setFoto(String foto){
        this.foto = foto;
    }
    
    public Cidade getCidade(){
        return cidade;
    }
    
    public void setCidade(Cidade cidade){
        this.cidade = cidade;
    }
    
    public Date getDataNascimento(){
        return dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    public Universidade getUniversidade(){
        return universidade;
    }
    
    public void setUniversidade(Universidade universidade){
        this.universidade = universidade;
    }
    
    public Curso getCurso(){
        return curso;
    }
    
    public void setCurso(Curso curso){
        this.curso = curso;
    }
    
    public List<Conquista> getConquistas(){
        return conquistas;
    }
    
    public List<Resposta> getRespostas(){
        return respostas;
    }
    
    public Date getData(){
        return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro){
        this.dataCadastro = dataCadastro;
    }
    
    public Boolean getAudioAtivo(){
        return audioAtivo;
    }
    
    public void setAudioAtivo(Boolean audioAtivo){
        this.audioAtivo = audioAtivo;
    }
    
    public int getPosicaoRanking(){
        return posicaoRanking;
    }
    
    public void setPosicaoRanking(int posicaoRanking){
        this.posicaoRanking = posicaoRanking;
    }
    
    public int getCodigoAtivo(){
        if (this.getAtivo()) return 1;
        else return 0;
    }
    
    public String getSenhaCriptografada(String password){
        int contador, tamanho,codigoASCII;
        
        String senhaCriptografada = "";
        tamanho = password.length();
        password = password.toUpperCase();
        contador = 0;
        
        while(contador < tamanho)
        {
            codigoASCII = password.charAt(contador) + 130;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }
        
        return senhaCriptografada;
    }
    
    public String getSenhaDescriptografada(String password){
        int contador, tamanho,codigoASCII;
        
        String senhaCriptografada = "";
        tamanho = password.length();
        password = password.toUpperCase();
        contador = 0;
        
        while(contador <tamanho)
        {
            codigoASCII = password.charAt(contador)-130;
            senhaCriptografada = senhaCriptografada +(char) codigoASCII;
            contador++;
        }
        
        return senhaCriptografada;
    }
    
    
    public Usuario(){}
    
    public Usuario(int id, String nome, String email, String senha, String direito, Boolean ativo, String foto, 
                   Cidade cidade, Date dataNascimento, Universidade universidade, Curso curso, List<Conquista> conquistas,
                   List<Resposta> respostas, Date dataCadastro, Boolean audioAtivo, int posicaoRanking){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.direito = direito;
        this.ativo = ativo;
        this.foto = foto;
        this.cidade = cidade;
        this.dataNascimento = dataNascimento;
        this.universidade = universidade;
        this.curso = curso;
        this.conquistas = conquistas;
        this.respostas = respostas;
        this.dataCadastro = dataCadastro;
        this.audioAtivo = audioAtivo;
        this.posicaoRanking = posicaoRanking;
    }
    
}
