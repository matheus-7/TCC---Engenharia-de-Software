
package daos.universidade;

import daos.cidade.CidadeDaoImpl;
import daos.connection.ConnectionFactory;
import daos.curso.CursoDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Curso;
import models.Universidade;

public class UniversidadeDaoImpl implements UniversidadeDao {

    private final String stmtAtualizar = "update universidade set CidId = ?, UniNome = ? " +
                                         "where UniId = ?";
    
    private final String stmtExcluir = "delete from universidade " + 
                                       "where UniId = ?";
    
    private final String stmtExiste = "select UniId " +
                                      "from universidade " + 
                                      "where UniNome = ? " + 
                                      "   and UniId != ?";
    
    private final String stmtInserir = "insert into universidade (CidId, UniNome, UniDataCad) values (?, ?, ?)";
    
    private final String stmtListar = "select UniId, CidId, UniNome, UniDataCad " + 
                                      "from universidade " +
                                      "order by UniNome";
    
    private final String stmtSelecionar = "select UniId, CidId, UniNome, UniDataCad " +
                                          "from universidade " +
                                          "where UniId = ?";
    
    private final String stmtInserirCurso = "insert into universidade_curso (CurId, UniId) values (?, ?)";
    
    private final String stmtExcluirCursos = "delete from universidade_curso where UniId = ?";
    
    @Override
    public boolean Existe(Universidade universidade, int idAnterior) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            boolean existe = false;
            
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtExiste);
            
            stmt.setString(1, universidade.getNome());
            stmt.setInt(2, idAnterior);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                existe = true;
            }
            
            return existe;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar uma universidade. Origem=" + ex.getMessage());
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
    public Universidade Selecionar(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelecionar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Universidade universidade = null;
            while (rs.next()) {
                universidade = new Universidade(
                        rs.getInt("UniId"), 
                        rs.getString("UniNome"), 
                        new CidadeDaoImpl().Selecionar(rs.getInt("CidId")),
                        new CursoDaoImpl().Listar(rs.getInt("UniId")),
                        rs.getDate("UniDataCad")
                );
            }
            
            return universidade;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar uma universidade. Origem=" + ex.getMessage());
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
    public List<Universidade> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Universidade> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            result = stmt.executeQuery();
            while (result.next()) {
                Universidade universidade = new Universidade(
                        result.getInt("UniId"), 
                        result.getString("UniNome"), 
                        new CidadeDaoImpl().Selecionar(result.getInt("CidId")),
                        new CursoDaoImpl().Listar(result.getInt("UniId")),
                        result.getDate("UniDataCad")
                );
                
                lista.add(universidade);
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

    @Override
    public void Atualizar(Universidade universidade) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizar);
            stmt.setInt(1, universidade.getCidade().getId());
            stmt.setString(2, universidade.getNome());
            stmt.setInt(3, universidade.getId());
            stmt.executeUpdate();
            
            new UniversidadeDaoImpl().ExcluirCursos(universidade.getId());
            new UniversidadeDaoImpl().InserirCursos(universidade.getCursos(), universidade.getId());
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar uma universidade no banco de dados. Origem=" + ex.getMessage());
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
            throw new RuntimeException("Erro ao excluir uma universidade no banco de dados. Origem=" + ex.getMessage());
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
    public void Inserir(Universidade universidade) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            if (universidade.getNome() != null){
                con = ConnectionFactory.getConnection();
                stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, universidade.getCidade().getId());
                stmt.setString(2, universidade.getNome());
                stmt.setTimestamp(3, new java.sql.Timestamp(universidade.getDataCadastro().getTime()));
                stmt.executeUpdate();
                universidade.setId(getId(stmt));
                
                new UniversidadeDaoImpl().InserirCursos(universidade.getCursos(), universidade.getId());
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir uma universidade no banco de dados. Origem=" + ex.getMessage());
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
    public void InserirCursos(List<Curso> cursos, int idUniversidade) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserirCurso, PreparedStatement.RETURN_GENERATED_KEYS);

            for (Curso curso : cursos){
                stmt.setInt(1, curso.getId());
                stmt.setInt(2, idUniversidade);
                
                stmt.executeUpdate();
            }
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
    public void ExcluirCursos(int idUniversidade) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtExcluirCursos);
            stmt.setInt(1, idUniversidade);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir cursos no banco de dados. Origem=" + ex.getMessage());
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
