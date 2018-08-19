
package daos.usuario;

import daos.cidade.CidadeDaoImpl;
import daos.connection.ConnectionFactory;
import daos.curso.CursoDaoImpl;
import daos.universidade.UniversidadeDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Usuario;


public class UsuarioDaoImpl implements UsuarioDao {

    private final String stmtListar = "select UsuId, CurId, UniId, CidId, UsuNome, UsuEmail, UsuSenha, UsuDireito, UsuAtivo, " +
                                      "       UsuDataNasc, UsuDataCad, UsuAudio " + 
                                      "from usuario " +
                                      "order by UsuNome";
    
    @Override
    public List<Usuario> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Usuario> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            result = stmt.executeQuery();
            while (result.next()) {
                
                Boolean ativo = result.getInt("UsuAtivo") == 1;
                Boolean audioAtivo = result.getInt("UsuAudio") == 1;
                
                Usuario usuario = new Usuario(
                        result.getInt("UsuId"), 
                        result.getString("UsuNome"),
                        result.getString("UsuEmail"),
                        result.getString("UsuSenha"),
                        result.getString("UsuDireito"),
                        ativo,
                        null,
                        new CidadeDaoImpl().Selecionar(result.getInt("CidId")),
                        result.getDate("UsuDataNasc"),
                        new UniversidadeDaoImpl().Selecionar(result.getInt("UniId")),
                        new CursoDaoImpl().Selecionar(result.getInt("CurId")),
                        null,
                        null,
                        result.getDate("UsuDataCad"),
                        audioAtivo
                );
                
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de usuários. Origem=" + ex.getMessage());
        } finally {
            try {
                result.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            }
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
        }
    }
    
}
