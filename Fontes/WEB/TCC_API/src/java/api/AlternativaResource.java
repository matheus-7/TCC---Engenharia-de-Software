
package api;

import dao.AlternativaDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Alternativa;

@Path("alternativa")
public class AlternativaResource {
    
    AlternativaDAO AlternativaDAO = new AlternativaDAO();
    
    public AlternativaResource() {
        
    }

    @GET
    @Path("/{idQuestao}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alternativa> listar(@PathParam("idQuestao") int idQuestao) {
        return AlternativaDAO.Listar(idQuestao);
    }
    
}
