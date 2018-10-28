
package daos.questao;

import java.util.List;
import models.Questao;
import models.Usuario;


public interface QuestaoDao {

    public List<Questao> Listar();
    
    public Questao Selecionar(int id);
    
    public void Atualizar(Questao questao);
    
    public void Inserir(Questao questao);
    
}
