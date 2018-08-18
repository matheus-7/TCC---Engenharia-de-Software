
package daos.cidade;

import java.util.List;
import models.Cidade;


public interface CidadeDao {
    public Cidade Selecionar(int id);
    
    public List<Cidade> Listar(int idEstado);
}
