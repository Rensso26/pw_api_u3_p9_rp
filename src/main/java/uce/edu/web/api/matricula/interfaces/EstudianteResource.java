package uce.edu.web.api.matricula.interfaces;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

import java.util.List;

import javax.print.attribute.standard.Media;

@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> listAll() {
        return estudianteService.ListAll();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public  Estudiante consultarPorId(@PathParam("id") Integer id) {
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("/crear")
    public void guardar(Estudiante estu) {
        this.estudianteService.crear(estu);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizar(id, estu);
    }

    @PATCH
    @Path("/azctualizarParcial/{id}")
    public void actualuzarParcial(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizarParcial(id,estu);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/buscarProv")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia,@QueryParam("genero") String genero){
        return this.estudianteService.buscarPorProvincia(provincia,genero);
    }
}
