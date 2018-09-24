
package api;

import dao.UsuarioDAO;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Usuario;


@Path("usuario")
public class UsuarioResource {

    UsuarioDAO UsuarioDAO = new UsuarioDAO();
    
    public UsuarioResource() {
        
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario selecionar(@PathParam("email") String email) {
        return UsuarioDAO.Selecionar(email);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario inserir(Usuario usuario) {
        UsuarioDAO.Inserir(usuario);
        
        return usuario;
    }
}
