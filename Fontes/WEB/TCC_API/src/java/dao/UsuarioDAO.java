
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.Usuario;


public class UsuarioDAO {
    
    private String SQL_SELECIONAR = "select UsuId, CurId, UniId, CidId, UsuNome, UsuEmail, UsuSenha, UsuDireito, UsuAtivo, " +
                                    "       UsuDataNasc, UsuDataCad, UsuAudio " +
                                    "from usuario " +
                                    "where UsuEmail = ? " +         
                                    "order by UsuNome";
    
    private String SQL_INSERIR = "insert into usuario (UsuNome, UsuEmail, UsuSenha, UsuDireito, UsuAtivo, UsuDataCad) " +
                                 "             values (?,       ?,        ?,        ?,          ?,        ?         ) ";
    
    private String SQL_ATUALIZAR = "update usuario set UsuNome = ?, " +
                                   "                   CidId = ?, " +
                                   "                   UniId = ?, " +
                                   "                   CurId = ? " +
                                   "where UsuId = ? " ;    
    
    public Usuario Selecionar(String email) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = dao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_SELECIONAR);
            stmt.setString(1, email);
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
                        new CidadeDAO().Selecionar(result.getInt("CidId")),
                        result.getDate("UsuDataNasc"),
                        new UniversidadeDAO().Selecionar(result.getInt("UniId")),
                        new CursoDAO().Selecionar(result.getInt("CurId")),
                        null,
                        null,
                        result.getDate("UsuDataCad"),
                        audioAtivo
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
       
    public void Inserir(Usuario usuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            if (usuario.getNome() != null){
                con = ConnectionFactory.getConnection();
                stmt = con.prepareStatement(SQL_INSERIR, PreparedStatement.RETURN_GENERATED_KEYS);
                
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenhaCriptografada(usuario.getSenha()));
                stmt.setString(4, "Usuário");
                stmt.setInt(5, 1);
                stmt.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
                
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
    
    public void Atualizar(Usuario usuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            if (usuario.getId() != 0){
                con = ConnectionFactory.getConnection();
                stmt = con.prepareStatement(SQL_ATUALIZAR);
                
                stmt.setString(1, usuario.getNome());
                stmt.setInt(2, usuario.getCidade().getId());
                stmt.setInt(3, usuario.getUniversidade().getId());
                stmt.setInt(4, usuario.getCurso().getId());
                stmt.setInt(5, usuario.getId());
                
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um usuário no banco de dados. Origem=" + ex.getMessage());
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
