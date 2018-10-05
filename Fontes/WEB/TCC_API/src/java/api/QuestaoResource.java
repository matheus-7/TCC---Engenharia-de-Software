
package api;

import dao.QuestaoDAO;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Questao;


@Path("questao")
public class QuestaoResource {

    QuestaoDAO QuestaoDAO = new QuestaoDAO();

    public QuestaoResource() {
        
    }

    @GET
    @Path("/{idArea}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Questao> listar(@PathParam("idArea") int idArea) {
        return QuestaoDAO.Listar(idArea);
    }

}
