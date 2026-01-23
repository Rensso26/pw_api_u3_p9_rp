package uce.edu.web.api.matricula.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

import java.util.List;

@Path("/materias")
@Produces(MediaType.APPLICATION_JSON)
public class MateriaResource {
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response todos() {
        List<Materia> lista = materiaService.ListAll();
        return Response.status(225).entity(lista).build() ;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") long id) {
        Materia materia = this.materiaService.consultarPorId(id);
        return Response.status(225).entity(materia).build() ;

    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(Materia materia) {
        this.materiaService.crear(materia);
        return Response.status(Response.Status.CREATED).entity(materia).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Long id, Materia materia) {
        this.materiaService.actualizar(materia, id);
        return Response.status(Response.Status.ACCEPTED).entity(materia).build();
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@PathParam("id") Long id, Materia materia) {
        this.materiaService.actualuzarParcial(id, materia);
        return Response.status(Response.Status.ACCEPTED).entity(materia).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") Long id) {
        this.materiaService.eliminar(id);
        return Response.status(226).entity(null).build();
    }

    @GET
    @Path("/buscarcod/{cod}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCod(@PathParam("cod") String cod) {
        Materia materia = this.materiaService.buscarPorCod(cod);
        return Response.status(227).entity(materia).build();
    }

    @PATCH
    @Path("/actualizarProf/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProf(@PathParam("id") Long id, String prof) {
        materiaService.actualizarProf(prof, id);
        return Response.status(229).entity(prof).build();
    }
}
