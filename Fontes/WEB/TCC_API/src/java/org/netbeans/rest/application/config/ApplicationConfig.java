
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(api.AlternativaResource.class);
        resources.add(api.AvaliacaoResource.class);
        resources.add(api.CidadeResource.class);
        resources.add(api.ConquistaResource.class);
        resources.add(api.CursoResource.class);
        resources.add(api.EstadoResource.class);
        resources.add(api.QuestaoResource.class);
        resources.add(api.RespostaResource.class);
        resources.add(api.UniversidadeResource.class);
        resources.add(api.UsuarioResource.class);
    }
    
}
