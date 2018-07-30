
package daos.universidade;

import java.util.List;
import models.Universidade;


public interface UniversidadeDao {
    public boolean Existe(Universidade universidade);

    public Universidade Selecionar(int id);
    
    public List<Universidade> Listar();
    
    public void Atualizar(Universidade universidade);
    
    public void Excluir(int id);
    
    public void Inserir(Universidade universidade);
}
