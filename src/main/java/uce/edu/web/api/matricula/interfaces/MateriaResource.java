package uce.edu.web.api.matricula.interfaces;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

import java.util.List;

@Path("/materias")
@Produces(MediaType.APPLICATION_JSON)
public class MateriaResource {
    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> todos() {
        return materiaService.ListAll();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") long id) {
        return this.materiaService.consultarPorId(id);
    }

    @POST
    @Path("/crear")
    public void guardar(Materia materia) {
        this.materiaService.crear(materia);

    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Long id, Materia materia) {
        this.materiaService.actualizar(materia, id);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Long id, Materia materia) {
        this.materiaService.actualuzarParcial(id, materia);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrar(@PathParam("id") Long id) {
        this.materiaService.eliminar(id);
    }

    @GET
    @Path("/buscarCod/{cod}")
    public Materia buscarCod(@PathParam("cod") String cod) {
        return this.materiaService.buscarPorCod(cod);
    }

    @PATCH
    @Path("/actualizarProf/{id}")
    public void actualizarProf(@PathParam("id") Long id, String prof) {
        materiaService.actualizarProf(prof, id);
    }
}
