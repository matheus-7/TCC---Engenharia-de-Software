
package daos.alternativa;

import daos.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Alternativa;


public class AlternativaDaoImpl implements AlternativaDao {

    private final String stmtListar = "select AltId, QuesId, AltDesc, AltCorreta " + 
                                      "from alternativa " +
                                      "where QuesId = ? " +
                                      "order by AltId";
    
    @Override
    public List<Alternativa> Listar(int idQuestao) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Alternativa> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            stmt.setInt(1, idQuestao);
            result = stmt.executeQuery();
            while (result.next()) {
                
                Alternativa alternativa = new Alternativa(
                        result.getInt("AltId"),
                        result.getString("AltDesc"),
                        result.getInt("AltCorreta") == 1,
                        null
                );
                
                lista.add(alternativa);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de alternativas. Origem=" + ex.getMessage());
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
