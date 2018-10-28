
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
    
    private final String stmtAtualizar = "update alternativa set AltDesc = ?, AltCorreta = ? " +  
                                         "where AltId = ?";
    
    private final String stmtInserir = "insert into alternativa (QuesId, AltDesc, AltCorreta) values (?, ?, ?)";
    
    
    
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
    
    @Override
    public void Atualizar(List<Alternativa> alternativas) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizar);

            for (Alternativa alternativa : alternativas){
                stmt.setString(1, alternativa.getDescricao());
                stmt.setInt(2, alternativa.getCodigoCorreta());
                stmt.setInt(3, alternativa.getId());

                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar alternativas no banco de dados. Origem=" + ex.getMessage());
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
    public void Inserir(List<Alternativa> alternativas, int idQuestao) {
        Connection con = null;
        PreparedStatement stmt = null;
        try { 
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
                
            for (Alternativa alternativa : alternativas){
                if (alternativa.getDescricao() != null){
                    stmt.setInt(1, idQuestao);
                    stmt.setString(2, alternativa.getDescricao());
                    stmt.setInt(3, alternativa.getCodigoCorreta());

                    stmt.executeUpdate();
                    alternativa.setId(getId(stmt));
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir alternativas no banco de dados. Origem=" + ex.getMessage());
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
