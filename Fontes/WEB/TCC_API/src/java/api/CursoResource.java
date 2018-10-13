
package api;


import dao.CursoDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Curso;


@Path("curso")
public class CursoResource {
    
    CursoDAO CursoDAO = new CursoDAO();
    
    public CursoResource() {
        
    }

    @GET
    @Path("/{idUni}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Curso> listar(@PathParam("idUni") int idUni) {
        return CursoDAO.Listar(idUni);
    }
    
}
