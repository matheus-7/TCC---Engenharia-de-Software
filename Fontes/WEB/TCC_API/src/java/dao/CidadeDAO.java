
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;
import model.Estado;


public class CidadeDAO {
    
    private String SQL_LISTAR = "select CidId, CidNome " + 
                                "from cidade " +
                                "where EstId = ? " +
                                "order by CidNome";
    
    private String SQL_SELECIONAR = "select CidId, cidade.EstId, CidNome, EstNome, EstUf " + 
                                    "from cidade " +
                                    "   inner join estado " +
                                    "      on cidade.EstId = estado.EstId " +
                                    "where CidId = ? ";
    
    public List<Cidade> Listar(int idEstado) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Cidade> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_LISTAR);
            stmt.setInt(1, idEstado);
            result = stmt.executeQuery();
            while (result.next()) {
                
                Cidade cidade = new Cidade(
                        result.getInt("CidId"),
                        result.getString("CidNome"),
                        null
                );
                
                lista.add(cidade);
            }
            
            return lista;
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
    
    public Cidade Selecionar(int idCidade) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = dao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_SELECIONAR);
            stmt.setInt(1, idCidade);
            result = stmt.executeQuery();
            Cidade cidade = new Cidade();
            if (result.next()) {
                Estado estado = new Estado(
                        result.getInt("EstId"),
                        result.getString("EstNome"),
                        result.getString("EstUf"),
                        null
                );
                
                cidade = new Cidade(
                        result.getInt("CidId"),
                        result.getString("CidNome"),
                        estado
                );
            }
            
            return cidade;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar uma cidade. Origem=" + ex.getMessage());
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
