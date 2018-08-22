
package daos.usuario;

import java.util.List;
import models.Usuario;


public interface UsuarioDao {
    public List<Usuario> Listar();
    
    public Usuario Selecionar(int id);
    
    public boolean Existe(Usuario usuario, int idAnterior);
    
    public void Atualizar(Usuario usuario);
    
    public void Inserir(Usuario usuario);
}
