
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
    
    private final String stmtSelecionar = "select UsuId, CurId, UniId, CidId, UsuNome, UsuEmail, UsuSenha, UsuDireito, " +
                                          "       UsuAtivo, UsuDataNasc, UsuDataCad, UsuAudio " +
                                          "from usuario " +
                                          "where UsuId = ?";
    
    private final String stmtExiste = "select UsuId " +
                                      "from usuario " + 
                                      "where UsuEmail = ? " + 
                                      "   and UsuId != ?";
    
    private final String stmtAtualizar = "update usuario set UsuNome = ?, UsuEmail = ?, UsuSenha = ?, " +
                                         "                   UsuDireito = ?, UsuAtivo = ? " +  
                                         "where UsuId = ?";
    
    private final String stmtInserir = "insert into usuario (UsuNome, UsuEmail, UsuSenha, UsuDireito, UsuAtivo, UsuDataCad) values (?, ?, ?, ?, ?, ?)";
    
    private final String stmtLogin = "select UsuId, CurId, UniId, CidId, UsuNome, UsuEmail, UsuSenha, UsuDireito, UsuAtivo, " +
                                      "       UsuDataNasc, UsuDataCad, UsuAudio " +
                                      "from usuario " +
                                      "where UsuEmail = ? " + 
                                      "   and UsuSenha = ? " +
                                      "order by UsuNome";
    
    @Override
    public Usuario Selecionar(String email, String senha) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtLogin);
            stmt.setString(1, email);
            stmt.setString(2, new Usuario().getSenhaCriptografada(senha));
            result = stmt.executeQuery();
            Usuario usuario = new Usuario();
            if (result.next()) {
                Boolean ativo = result.getInt("UsuAtivo") == 1;
                Boolean audioAtivo = result.getInt("UsuAudio") == 1;
                
                usuario = new Usuario(
                        result.getInt("UsuId"), 
                        result.getString("UsuNome"),
                        result.getString("UsuEmail"),
                        result.getString("UsuSenha"),
                        result.getString("UsuDireito"),
                        ativo,
                        null,
                        null,
                        result.getDate("UsuDataNasc"),
                        null,
                        null,
                        null,
                        null,
                        result.getDate("UsuDataCad"),
                        audioAtivo,
                        0
                );       
            }
            
            return usuario;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar um usuário. Origem=" + ex.getMessage());
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
                        audioAtivo,
                        0
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

    @Override
    public Usuario Selecionar(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelecionar);
            stmt.setInt(1, id);
            result = stmt.executeQuery();
            Usuario usuario = null;
            while (result.next()) {
                
                Boolean ativo = result.getInt("UsuAtivo") == 1;
                Boolean audioAtivo = result.getInt("UsuAudio") == 1;
                
                usuario = new Usuario(
                        result.getInt("UsuId"), 
                        result.getString("UsuNome"),
                        result.getString("UsuEmail"),
                        new Usuario().getSenhaDescriptografada(result.getString("UsuSenha")),
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
                        audioAtivo,
                        0
                );
            }
            
            return usuario;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar um usuário. Origem=" + ex.getMessage());
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

    @Override
    public boolean Existe(Usuario usuario, int idAnterior) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            boolean existe = false;
            
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtExiste);
            
            stmt.setString(1, usuario.getEmail());
            stmt.setInt(2, idAnterior);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                existe = true;
            }
            
            return existe;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar um usuário. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
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

    @Override
    public void Atualizar(Usuario usuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizar);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaCriptografada(usuario.getSenha()));
            stmt.setString(4, usuario.getDireito());
            stmt.setInt(5, usuario.getCodigoAtivo());
            stmt.setInt(6, usuario.getId());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar um usuário no banco de dados. Origem=" + ex.getMessage());
        } finally {
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

    @Override
    public void Inserir(Usuario usuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            if (usuario.getNome() != null){
                con = ConnectionFactory.getConnection();
                stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
                
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenhaCriptografada(usuario.getSenha()));
                stmt.setString(4, usuario.getDireito());
                stmt.setInt(5, usuario.getCodigoAtivo());
                stmt.setTimestamp(6, new java.sql.Timestamp(usuario.getData().getTime()));
                
                stmt.executeUpdate();
                usuario.setId(getId(stmt));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um usuário no banco de dados. Origem=" + ex.getMessage());
        } finally {
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

    private int getId(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        
        return rs.getInt(1);
    }
    
}
