
package daos.estado;

import java.util.List;
import models.Estado;

public interface EstadoDao {
    public Estado Selecionar(int id);
    
    public List<Estado> Listar();
}
