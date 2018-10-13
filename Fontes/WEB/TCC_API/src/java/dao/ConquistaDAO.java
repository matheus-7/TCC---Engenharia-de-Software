
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Conquista;
import model.ConquistaConfig;


public class ConquistaDAO {

    private String SQL_LISTAR = "select ConId, conquista.ConConfId, UsuId, ConData, ConConfTitulo, ConConfDesc " + 
                                "from conquista " +
                                "   inner join conquista_config " +
                                "      on conquista.ConConfId = conquista_config.ConConfId " +
                                "where UsuId = ? " +
                                "order by ConId ";
    
    public List<Conquista> Listar(int idUsuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Conquista> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_LISTAR);
            stmt.setInt(1, idUsuario);
            result = stmt.executeQuery();
            while (result.next()) {
                
                ConquistaConfig conquistaConfig = new ConquistaConfig(
                        result.getInt("ConConfId"),
                        result.getString("ConConfTitulo"),
                        result.getString("ConConfDesc")
                );
                
                Conquista conquista = new Conquista(
                        result.getInt("ConId"),
                        conquistaConfig,
                        null,
                        result.getDate("ConData")                        
                );
                
                lista.add(conquista);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de conquistas. Origem=" + ex.getMessage());
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
