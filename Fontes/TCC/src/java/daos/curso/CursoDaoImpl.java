
package daos.curso;

import daos.connection.ConnectionFactory;
import java.util.List;
import models.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDaoImpl implements CursoDao {

    private final String stmtListar = "select CurId, CurNome, CurDataCad " + 
                                      "from cliente";

    
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
    
}
