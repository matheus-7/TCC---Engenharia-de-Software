
package daos.universidade;

import daos.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Universidade;

public class UniversidadeDaoImpl implements UniversidadeDao {

    private final String stmtListar = "select UniId, CidId, UniNome, UniDataCad " + 
                                      "from universidade " +
                                      "order by UniNome";
    
    @Override
    public boolean Existe(Universidade universidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Universidade Selecionar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Universidade> Listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        List<Universidade> lista = new ArrayList();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
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
            throw new RuntimeException("Erro ao consultar uma lista de produtos. Origem=" + ex.getMessage());
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
    public void Atualizar(Universidade universidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Inserir(Universidade universidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
