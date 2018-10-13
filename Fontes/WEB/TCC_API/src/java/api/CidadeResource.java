
package api;

import dao.CidadeDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Cidade;


@Path("cidade")
public class CidadeResource {
    
    CidadeDAO CidadeDAO = new CidadeDAO();
    
    public CidadeResource() {
        
    }

    @GET
    @Path("/{idEstado}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidade> listar(@PathParam("idEstado") int idEstado) {
        return CidadeDAO.Listar(idEstado);
    }
    
}
