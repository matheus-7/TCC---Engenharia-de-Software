

package api;

import dao.EstadoDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Estado;


@Path("estado")
public class EstadoResource {
    
    EstadoDAO EstadoDAO = new EstadoDAO();
    
    public EstadoResource() {
        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> listar() {
        return EstadoDAO.Listar();
    }
    
}
