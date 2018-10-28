
package daos.universidade;

import java.util.List;
import models.Curso;
import models.Universidade;


public interface UniversidadeDao {
    public boolean Existe(Universidade universidade, int idAnterior);

    public Universidade Selecionar(int id);
    
    public List<Universidade> Listar();
    
    public void Atualizar(Universidade universidade);
    
    public void Excluir(int id);
    
    public void Inserir(Universidade universidade);
    
    public void InserirCursos(List<Curso> cursos, int idUniversidade);
    
    public void ExcluirCursos(int idUniversidade);
}
