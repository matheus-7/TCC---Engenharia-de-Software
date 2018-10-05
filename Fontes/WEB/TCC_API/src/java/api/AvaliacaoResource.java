
package api;

import dao.AvaliacaoDAO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Avaliacao;


@Path("avaliacao")
public class AvaliacaoResource {
    
    AvaliacaoDAO AvaliacaoDAO = new AvaliacaoDAO();
    
    public AvaliacaoResource() {
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Avaliacao inserir(Avaliacao avaliacao) {
        AvaliacaoDAO.Inserir(avaliacao);
        
        return avaliacao;
    }
}
