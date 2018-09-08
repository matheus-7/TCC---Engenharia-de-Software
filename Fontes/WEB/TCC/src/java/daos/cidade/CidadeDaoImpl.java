
package daos.cidade;

import daos.connection.ConnectionFactory;
import daos.estado.EstadoDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Cidade;
import models.Estado;

public class CidadeDaoImpl implements CidadeDao {

    private final String stmtSelecionar = "select CidId, cidade.EstId, CidNome, EstNome, EstUf " +
                                          "from cidade " +
                                          "   inner join estado " +
                                          "      on cidade.EstId = estado.EstId " +
                                          "where CidId = ?";  
    
    private final String stmtListarPorEstado = "select CidId, cidade.EstId, CidNome, EstNome, EstUf " +
                                               "from cidade " +
                                               "   inner join estado " +
                                               "      on cidade.EstId = estado.EstId " +
                                               "where cidade.EstId = ?"; 
    
    @Override
    public Cidade Selecionar(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelecionar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Cidade cidade = null;
            while (rs.next()) {
                Estado estado = new Estado(
                                        rs.getInt("cidade.EstId"),
                                        rs.getString("EstNome"),
                                        rs.getString("EstUf"),
                                        null
                                );                
                
                cidade = new Cidade(
                        rs.getInt("CidId"), 
                        rs.getString("CidNome"),
                        estado
                );
            }
            
            return cidade;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar uma cidade. Origem=" + ex.getMessage());
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
    public List<Cidade> Listar(int idEstado) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Cidade> cidades = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListarPorEstado);
            stmt.setInt(1, idEstado);
            result = stmt.executeQuery();
            while (result.next()) {
                Estado estado = new Estado(
                                        result.getInt("cidade.EstId"),
                                        result.getString("EstNome"),
                                        result.getString("EstUf"),
                                        null
                                ); 
                
                Cidade cidade = new Cidade(
                        result.getInt("CidId"), 
                        result.getString("CidNome"),
                        estado
                );
                
                cidades.add(cidade);
            }
            
            return cidades;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de cidades. Origem=" + ex.getMessage());
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
