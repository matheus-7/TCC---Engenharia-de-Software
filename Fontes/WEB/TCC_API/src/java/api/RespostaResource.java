
package api;

import dao.ConquistaConfigDAO;
import dao.RespostaDAO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Resposta;
import model.Usuario;

@Path("resposta")
public class RespostaResource {
    
    RespostaDAO RespostaDAO = new RespostaDAO();
    ConquistaConfigDAO ConquistaConfigDAO = new ConquistaConfigDAO();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Resposta> inserir(List<Resposta> respostas) {
        RespostaDAO.Inserir(respostas);
        
        Usuario usuario = respostas.get(0).getUsuario();
                
        ConquistaConfigDAO.GerarConquistas(usuario);
        
        return respostas;
    }
    
}
