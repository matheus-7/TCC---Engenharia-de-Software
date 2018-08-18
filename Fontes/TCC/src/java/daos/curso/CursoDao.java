
package daos.curso;

import java.util.List;
import models.Curso;

public interface CursoDao {
    
    public boolean Existe(Curso curso);

    public Curso Selecionar(int id);
    
    public List<Curso> Listar();
    
    public List<Curso> Listar(int idUniversidade);
    
    public void Atualizar(Curso curso);
    
    public void Excluir(int id);
    
    public void Inserir(Curso curso);
}
