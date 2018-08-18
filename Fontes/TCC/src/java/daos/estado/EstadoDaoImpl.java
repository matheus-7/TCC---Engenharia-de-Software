
package daos.estado;

import daos.cidade.CidadeDao;
import daos.cidade.CidadeDaoImpl;
import daos.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Estado;


public class EstadoDaoImpl implements EstadoDao {
    
    private final String stmtSelecionar = "select EstId, EstNome, EstUf " +
                                          "from estado " +
                                          "where EstId = ?";
    
    private final String stmtListar = "select EstId, EstNome, EstUf " + 
                                      "from estado " +
                                      "order by EstNome";
    
    @Override
    public Estado Selecionar(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelecionar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Estado estado = null;
            while (rs.next()) {
                estado = new Estado(
                        rs.getInt("EstId"), 
                        rs.getString("EstNome"),
                        rs.getString("EstUf"),
                        null
                );
            }
            
            return estado;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar um estado. Origem=" + ex.getMessage());
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
    public List<Estado> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Estado> estados = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            result = stmt.executeQuery();
            while (result.next()) {
                Estado estado = new Estado(
                        result.getInt("EstId"), 
                        result.getString("EstNome"), 
                        result.getString("EstUf"),
                        new CidadeDaoImpl().Listar(result.getInt("EstId"))
                );
                
                estados.add(estado);
            }
            
            return estados;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de estados. Origem=" + ex.getMessage());
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
