
package api;

import dao.ConquistaDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Conquista;

@Path("conquista")
public class ConquistaResource {
    
    ConquistaDAO ConquistaDAO = new ConquistaDAO();
    
    @GET
    @Path("/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conquista> listar(@PathParam("idUsuario") int idUsuario) {
        return ConquistaDAO.Listar(idUsuario);
    }
    
}
