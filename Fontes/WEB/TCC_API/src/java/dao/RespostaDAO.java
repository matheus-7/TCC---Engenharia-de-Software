
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Alternativa;
import model.AreaDeConhecimento;
import model.Questao;
import model.Resposta;


public class RespostaDAO {

    private String SQL_INSERIR = "insert into resposta (AltId, UsuId, ResPontos, ResData) " +
                                 "              values (?,     ?,     ?,         ?      ) ";
    
    private String SQL_LISTAR = "select ResId, resposta.AltId, UsuId, ResPontos, ResData, alternativa.QuesId, " + 
                                "       AltDesc, AltCorreta, questao.AreaId, QuesDesc, QuesAtiva, QuesDataCad, " +
                                "       AreaNome " +
                                "from resposta " +
                                "   inner join alternativa " +
                                "      on resposta.AltId = alternativa.AltId " +
                                "	  inner join questao " +
                                "      on alternativa.QuesId = questao.QuesId " +
                                "	  inner join area_conhecimento " +
                                "      on questao.AreaId = area_conhecimento.AreaId " +
                                "where UsuId = ? " +
                                "order by ResId";
    
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
    
    public List<Resposta> Listar(int idUsuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Resposta> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(SQL_LISTAR);
            stmt.setInt(1, idUsuario);
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
                
                Alternativa alternativa = new Alternativa(
                        result.getInt("AltId"),
                        result.getString("AltDesc"),
                        result.getInt("AltCorreta") == 1,
                        questao
                );
                
                Resposta resposta = new Resposta(
                        result.getInt("ResId"),
                        alternativa,
                        null,
                        result.getDouble("ResPontos"),
                        result.getDate("ResData")  
                );
                
                lista.add(resposta);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de respostas. Origem=" + ex.getMessage());
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
    
    private int getId(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        
        return rs.getInt(1);
    }
    
}
