
package daos.questao;

import daos.alternativa.AlternativaDaoImpl;
import daos.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.AreaDeConhecimento;
import models.Questao;


public class QuestaoDaoImpl implements QuestaoDao {

    private final String stmtListar = "select QuesId, questao.AreaId, QuesDesc, QuesAtiva, QuesDataCad, AreaNome " + 
                                      "from questao " +
                                      "   inner join area_conhecimento " +
                                      "      on questao.AreaId = area_conhecimento.AreaId " +
                                      "order by QuesId";
    
    private final String stmtSelecionar = "select QuesId, questao.AreaId, QuesDesc, QuesAtiva, QuesDataCad, AreaNome " + 
                                          "from questao " +
                                          "   inner join area_conhecimento " +
                                          "      on questao.AreaId = area_conhecimento.AreaId " +
                                          "where QuesId = ?";
    
    private final String stmtAtualizar = "update questao set AreaId = ?, QuesDesc = ?, QuesAtiva = ?, " +
                                         "                   QuesDataCad = ? " +  
                                         "where QuesId = ?";
    
    private final String stmtInserir = "insert into questao (AreaId, QuesDesc, QuesAtiva, QuesDataCad) values (?, ?, ?, ?)";
    
    
    @Override
    public List<Questao> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Questao> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
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
                        new AlternativaDaoImpl().Listar(result.getInt("QuesId")),
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
    
    @Override
    public Questao Selecionar(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelecionar);
            stmt.setInt(1, id);
            result = stmt.executeQuery();
            Questao questao = null;
            while (result.next()) {
                
                AreaDeConhecimento area = new AreaDeConhecimento(
                        result.getInt("AreaId"),
                        result.getString("AreaNome"),
                        null
                );
                
                questao = new Questao(
                    result.getInt("QuesId"),
                    result.getString("QuesDesc"),
                    result.getInt("QuesAtiva") == 1,
                    area,
                    new AlternativaDaoImpl().Listar(result.getInt("QuesId")),
                    result.getDate("QuesDataCad")                        
                );
                
            }
            
            return questao;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar uma questão. Origem=" + ex.getMessage());
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
    public void Atualizar(Questao questao) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizar);
            
            stmt.setInt(1, questao.getAreaConhecimento().getId());
            stmt.setString(2, questao.getDescricao());
            stmt.setInt(3, questao.getCodigoAtiva());
            stmt.setTimestamp(4, new java.sql.Timestamp(questao.getDataCadastro().getTime()));
            stmt.setInt(5, questao.getId());
            
            stmt.executeUpdate();
            
            new AlternativaDaoImpl().Atualizar(questao.getAlternativas());
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar uma questão no banco de dados. Origem=" + ex.getMessage());
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
    public void Inserir(Questao questao) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            if (questao.getDescricao() != null){
                con = ConnectionFactory.getConnection();
                stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
                        
                stmt.setInt(1, questao.getAreaConhecimento().getId());
                stmt.setString(2, questao.getDescricao());
                stmt.setInt(3, questao.getCodigoAtiva());
                stmt.setTimestamp(4, new java.sql.Timestamp(questao.getDataCadastro().getTime()));
                
                stmt.executeUpdate();
                questao.setId(getId(stmt));
                
                new AlternativaDaoImpl().Inserir(questao.getAlternativas(), questao.getId());
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir uma questão no banco de dados. Origem=" + ex.getMessage());
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
