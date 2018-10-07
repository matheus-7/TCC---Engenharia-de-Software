
package api;

import dao.RespostaDAO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Resposta;

@Path("resposta")
public class RespostaResource {
    
    RespostaDAO RespostaDAO = new RespostaDAO();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Resposta> inserir(List<Resposta> respostas) {
        RespostaDAO.Inserir(respostas);
        
        return respostas;
    }
    
}
