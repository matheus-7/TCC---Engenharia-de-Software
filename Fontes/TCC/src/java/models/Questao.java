
package models;

import java.util.Date;
import java.util.List;

public class Questao {

    private int id;
    private String descricao;
    private Boolean ativa;
    private AreaDeConhecimento areaConhecimento;
    private List<Alternativa> alternativas;
    private Date dataCadastro;
    
}
