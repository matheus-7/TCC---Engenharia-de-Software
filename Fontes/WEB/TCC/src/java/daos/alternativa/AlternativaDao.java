
package daos.alternativa;

import java.util.List;
import models.Alternativa;


public interface AlternativaDao {
    
    public List<Alternativa> Listar(int idQuestao);
    
}
