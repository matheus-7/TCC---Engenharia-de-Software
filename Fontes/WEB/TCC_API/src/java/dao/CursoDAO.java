
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Curso;

public class CursoDAO {
    
    private String SQL_LISTAR = "select curso.CurId, CurNome, CurDataCad " + 
                                "from curso " +
                                "   inner join universidade_curso " +
                                "      on curso.CurId = universidade_curso.CurId " +
                                "where UniId = ? " +
                                "order by CurNome";
    
    private String SQL_SELECIONAR = "select CurId, CurNome, CurDataCad " +
                                    "from curso " +
                                    "where CurId = ? ";
    
    public List<Curso> Listar(int idUni) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Curso> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_LISTAR);
            stmt.setInt(1, idUni);
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
            throw new RuntimeException("Erro ao consultar uma lista de cursos. Origem=" + ex.getMessage());
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
    
    public Curso Selecionar(int idCurso) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = dao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_SELECIONAR);
            stmt.setInt(1, idCurso);
            result = stmt.executeQuery();
            Curso curso = new Curso();
            if (result.next()) {
                curso = new Curso(
                        result.getInt("CurId"),
                        result.getString("CurNome"),
                        result.getDate("CurDataCad")
                );
            }
            
            return curso;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar um curso. Origem=" + ex.getMessage());
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
