
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import model.Usuario;

public class ConquistaConfigDAO {

    private String SQL_INSERIR = "{CALL GerarConquistas(?)}";
    
    public void GerarConquistas(Usuario usuario) {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareCall(SQL_INSERIR);

            stmt.setInt(1, usuario.getId());
            
            stmt.execute();       
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao gerar conquistas no banco de dados. Origem=" + ex.getMessage());
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
    
}
