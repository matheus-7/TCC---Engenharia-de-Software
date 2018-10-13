
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Universidade;


public class UniversidadeDAO {
    
    private String SQL_LISTAR = "select UniId, CidId, UniNome, UniDataCad " + 
                                "from universidade " +
                                "order by UniNome";
    
    private String SQL_SELECIONAR = "select UniId, CidId, UniNome, UniDataCad " +
                                    "from universidade " +
                                    "where UniId = ? ";
    
    public List<Universidade> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Universidade> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_LISTAR);
            result = stmt.executeQuery();
            while (result.next()) {
                
                Universidade universidade = new Universidade(
                        result.getInt("UniId"),
                        result.getString("UniNome"),
                        null,
                        null,
                        result.getDate("UniDataCad")
                );
                
                lista.add(universidade);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de universidades. Origem=" + ex.getMessage());
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
    
    public Universidade Selecionar(int idUni) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = dao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_SELECIONAR);
            stmt.setInt(1, idUni);
            result = stmt.executeQuery();
            Universidade universidade = new Universidade();
            if (result.next()) {
                universidade = new Universidade(
                        result.getInt("UniId"),
                        result.getString("UniNome"),
                        null,
                        null,
                        result.getDate("UniDataCad")
                );
            }
            
            return universidade;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar uma universidade. Origem=" + ex.getMessage());
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
