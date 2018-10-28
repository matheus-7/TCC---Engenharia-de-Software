
package daos.area;

import daos.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.AreaDeConhecimento;


public class AreaDaoImpl implements AreaDao {

    private final String stmtListar = "select AreaId, AreaNome " + 
                                      "from area_conhecimento " +
                                      "order by AreaNome";
    
    private final String stmtSelecionar = "select AreaId, AreaNome " + 
                                          "from area_conhecimento " +
                                          "where AreaId = ?";
    
    @Override
    public List<AreaDeConhecimento> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<AreaDeConhecimento> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            result = stmt.executeQuery();
            while (result.next()) {
                
                AreaDeConhecimento area = new AreaDeConhecimento(
                        result.getInt("AreaId"),
                        result.getString("AreaNome"),
                        null
                );
                                
                lista.add(area);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de áreas de conhecimento. Origem=" + ex.getMessage());
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
    public AreaDeConhecimento Selecionar(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelecionar);
            stmt.setInt(1, id);
            result = stmt.executeQuery();
            AreaDeConhecimento area = null;
            while (result.next()) {
                
                area = new AreaDeConhecimento(
                        result.getInt("AreaId"),
                        result.getString("AreaNome"),
                        null
                );                
            }
            
            return area;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar uma questão. Origem=" + ex.getMessage());
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
