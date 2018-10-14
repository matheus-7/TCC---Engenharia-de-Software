
package daos.avaliacao;

import daos.connection.ConnectionFactory;
import daos.usuario.UsuarioDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Avaliacao;


public class AvaliacaoDaoImpl implements AvaliacaoDao {
    
    private final String stmtListar = "select AvaId, UsuId, AvaNota, AvaMsg, AvaData " + 
                                      "from avaliacao " +
                                      "order by AvaId desc";

    @Override
    public List<Avaliacao> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Avaliacao> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            result = stmt.executeQuery();
            while (result.next()) {
                Avaliacao avaliacao = new Avaliacao(
                        result.getInt("AvaId"), 
                        result.getDouble("AvaNota"), 
                        result.getString("AvaMsg"),
                        new UsuarioDaoImpl().Selecionar(result.getInt("UsuId")),
                        result.getDate("AvaData")
                );
                
                lista.add(avaliacao);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de avaliações. Origem=" + ex.getMessage());
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
