
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;


public class UsuarioDAO {
    
    private String SQL_SELECIONAR = "select UsuId, CurId, UniId, CidId, UsuNome, UsuEmail, UsuSenha, UsuDireito, UsuAtivo, " +
                                    "       UsuDataCad " +
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
    
    private String SQL_LISTAR_RANKING = "select resposta.UsuId, UsuEmail " +
                                        "from resposta " +
                                        "   left join usuario " +
                                        "      on resposta.UsuId = usuario.UsuId " +
                                        "   left join universidade " +
                                        "      on usuario.UniId = universidade.UniId " +
                                        "   left join curso " +
                                        "      on usuario.CurId = curso.CurId " +
                                        "where (usuario.CurId = ? or ? = 0) " +
                                        "   and (usuario.UniId = ? or ? = 0) " +
                                        "group by UsuId " +
                                        "order by SUM(ResPontos) desc " +
                                        "limit 50";
    
    private String SQL_LISTAR_RANK = "select UsuId " +
                                     "from resposta " +
                                     "group by UsuId " +
                                     "order by SUM(ResPontos) desc ";
    
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
                
                usuario = new Usuario(
                        result.getInt("UsuId"), 
                        result.getString("UsuNome"),
                        result.getString("UsuEmail"),
                        result.getString("UsuSenha"),
                        result.getString("UsuDireito"),
                        ativo,
                        new CidadeDAO().Selecionar(result.getInt("CidId")),
                        new UniversidadeDAO().Selecionar(result.getInt("UniId")),
                        new CursoDAO().Selecionar(result.getInt("CurId")),
                        null,
                        null,
                        result.getDate("UsuDataCad"),
                        this.SelecionarPosicaoRanking(result.getInt("UsuId"))
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
                stmt.setString(4, "Estudante");
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
    
    public int SelecionarPosicaoRanking(int idUsuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        int posicao = 1;
        List<Usuario> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_LISTAR_RANK);
            result = stmt.executeQuery();
            while (result.next()) {
                
                Usuario usuario = new Usuario();
                
                usuario.setId(result.getInt("UsuId"));
                
                lista.add(usuario);
            }
            
            for (Usuario usu : lista){
                if (usu.getId() == idUsuario) break;
                else posicao++;
            }
            
            return posicao;
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
    
    
    public List<Usuario> ListarRanking(int codigoCurso, int codigoUniversidade) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Usuario> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_LISTAR_RANKING);
            
            stmt.setInt(1, codigoCurso);
            stmt.setInt(2, codigoCurso);
            stmt.setInt(3, codigoUniversidade);
            stmt.setInt(4, codigoUniversidade);
            
            result = stmt.executeQuery();
            while (result.next()) {
                
                Usuario usuario = this.Selecionar(result.getString("UsuEmail"));
                               
                lista.add(usuario);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de universidades. Origem=" + ex.getMessage());
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
    
    
    private int getId(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        
        return rs.getInt(1);
    }

    
    
}
