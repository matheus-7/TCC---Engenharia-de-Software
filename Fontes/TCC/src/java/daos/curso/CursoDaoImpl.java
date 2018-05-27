
package daos.curso;

import daos.connection.ConnectionFactory;
import java.util.List;
import models.Curso;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDaoImpl implements CursoDao {

    private final String stmtAtualizar = "update curso set CurNome = ?, CurDataCad = ? " +
                                         "where CurId = ?";
    
    private final String stmtExcluir = "delete from curso where CurId = ?";
    
    private final String stmtExiste = "select CurId" +
                                      "from curso" + 
                                      "where CurNome = ?";
    
    private final String stmtInserir = "insert into curso (CurNome, CurDataCad) values (?, ?)";
    
    private final String stmtListar = "select CurId, CurNome, CurDataCad " + 
                                      "from curso";
    
    private final String stmtSelecionar = "select CurId, CurNome, CurDataCad" +
                                          "from curso" +
                                          "where CurId = ?";
    
    
    @Override
    public void Atualizar(Curso curso) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizar);
            stmt.setString(1, curso.getNome());
            stmt.setDate(2, new java.sql.Date(curso.getDataCadastro().getTime()));
            stmt.setInt(3, curso.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar um curso no banco de dados. Origem=" + ex.getMessage());
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
    public void Excluir(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtExcluir);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir um curso no banco de dados. Origem=" + ex.getMessage());
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
    public boolean Existe(Curso curso) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            boolean existe = false;
            
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtExiste);
            stmt.setString(1, curso.getNome());
            rs = stmt.executeQuery();
            while (rs.next()) {
                existe = true;
            }
            
            return existe;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar um curso. Origem=" + ex.getMessage());
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
    public void Inserir(Curso curso) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, curso.getNome());
            stmt.setDate(2, new java.sql.Date(curso.getDataCadastro().getTime()));
            stmt.executeUpdate();
            curso.setId(getId(stmt));
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um curso no banco de dados. Origem=" + ex.getMessage());
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
    public List<Curso> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Curso> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            result = stmt.executeQuery();
            while (result.next()) {
                Curso curso = new Curso(
                        result.getInt("CurId"), 
                        result.getString("CurNome"), 
                        result.getDate("CurDataCad")
                );
                
                lista.add(curso);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de produtos. Origem=" + ex.getMessage());
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
    public Curso Selecionar(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelecionar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Curso curso = null;
            while (rs.next()) {
                curso = new Curso(rs.getInt("CurId"), rs.getString("CurNome"), rs.getDate("CurDataCad"));
            }
            return curso;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar um curso. Origem=" + ex.getMessage());
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
    
    
    private int getId(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        
        return rs.getInt(1);
    }
    
}
