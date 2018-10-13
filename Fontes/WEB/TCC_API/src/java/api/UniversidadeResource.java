
package api;

import dao.UniversidadeDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Universidade;

@Path("universidade")
public class UniversidadeResource {
    
    UniversidadeDAO UniversidadeDAO = new UniversidadeDAO();
    
    public UniversidadeResource() {
        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Universidade> listar() {
        return UniversidadeDAO.Listar();
    }
    
}
