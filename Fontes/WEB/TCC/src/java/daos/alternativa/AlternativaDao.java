
package daos.alternativa;

import java.util.List;
import models.Alternativa;
import models.Curso;


public interface AlternativaDao {
    
    public List<Alternativa> Listar(int idQuestao);
    
    public void Atualizar(List<Alternativa> alternativas);
    
    public void Inserir(List<Alternativa> alternativas, int idQuestao);
    
}
