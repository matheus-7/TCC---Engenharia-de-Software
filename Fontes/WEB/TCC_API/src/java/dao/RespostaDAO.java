
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Resposta;


public class RespostaDAO {

    private String SQL_INSERIR = "insert into resposta (AltId, UsuId, ResPontos, ResData) " +
                                 "              values (?,     ?,     ?,         ?      ) ";
    
    public void Inserir(List<Resposta> respostas) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            for (Resposta resposta : respostas){
                if (resposta.getAlternativa().getId() != 0){
                    con = ConnectionFactory.getConnection();
                    stmt = con.prepareStatement(SQL_INSERIR, PreparedStatement.RETURN_GENERATED_KEYS);

                    stmt.setInt(1, resposta.getAlternativa().getId());
                    stmt.setInt(2, resposta.getUsuario().getId());
                    stmt.setDouble(3, resposta.getPontuacao());
                    stmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));

                    stmt.executeUpdate();

                    resposta.setId(getId(stmt));
                }
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir uma resposta no banco de dados. Origem=" + ex.getMessage());
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
