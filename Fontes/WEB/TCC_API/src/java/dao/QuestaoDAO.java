
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.AreaDeConhecimento;
import model.Questao;


public class QuestaoDAO {
    private String SQL_LISTAR = "select QuesId, questao.AreaId, QuesDesc, QuesAtiva, QuesDataCad, AreaNome " + 
                                "from questao " +
                                "   inner join area_conhecimento " +
                                "      on questao.AreaId = area_conhecimento.AreaId " +
                                "where questao.AreaId = ? " +
                                "   and QuesAtiva = 1 " +
                                "order by RAND() " +
                                "limit 5";  
    
    public List<Questao> Listar(int idArea) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Questao> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_LISTAR);
            stmt.setInt(1, idArea);
            result = stmt.executeQuery();
            while (result.next()) {
                
                AreaDeConhecimento area = new AreaDeConhecimento(
                        result.getInt("AreaId"),
                        result.getString("AreaNome"),
                        null
                );
                
                Questao questao = new Questao(
                        result.getInt("QuesId"),
                        result.getString("QuesDesc"),
                        result.getInt("QuesAtiva") == 1,
                        area,
                        null,
                        result.getDate("QuesDataCad")                        
                );
                
                lista.add(questao);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de questões. Origem=" + ex.getMessage());
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
