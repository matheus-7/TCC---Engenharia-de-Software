
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Avaliacao;


public class AvaliacaoDAO {
    
    private String SQL_INSERIR = "insert into avaliacao (UsuId, AvaNota, AvaMsg, AvaData) " +
                                 "               values (?,     ?,       ?,      ?      ) ";
    
    public void Inserir(Avaliacao avaliacao) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            if (avaliacao.getUsuario().getId() != 0){
                con = ConnectionFactory.getConnection();
                stmt = con.prepareStatement(SQL_INSERIR, PreparedStatement.RETURN_GENERATED_KEYS);
                
                stmt.setInt(1, avaliacao.getUsuario().getId());
                stmt.setDouble(2, avaliacao.getNota());
                stmt.setString(3, avaliacao.getMensagem());
                stmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
                
                stmt.executeUpdate();
                
                avaliacao.setId(getId(stmt));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir uma avaliação no banco de dados. Origem=" + ex.getMessage());
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
