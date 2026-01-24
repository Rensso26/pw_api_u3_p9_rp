package uce.edu.web.api.matricula.interfaces;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;

import java.util.List;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> listAll() {
        return estudianteService.ListAll();
    }

    @GET
    @Path("/{id}")
    public  Estudiante consultarPorId(@PathParam("id") Integer id) {
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(Estudiante estu) {
        this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizar(id, estu);
        return Response.status(Response.Status.OK).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualuzarParcial(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizarParcial(id,estu);
        return Response.status(209).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
        return Response.status(225).entity(null).build();
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_XML)
    public Response buscarPorProvincia(@QueryParam("provincia") String provincia,@QueryParam("genero") String genero){
        List<Estudiante> lista = this.estudianteService.buscarPorProvincia(provincia,genero);
        return Response.status(212).entity(lista).build();

    }

    @GET
    @Path("/{id}/hijos")
    public List<Hijo> buscarPorEstudiante(@PathParam("id") Long id){
        return this.hijoService.buscarPorIdEstudiante(id);
    }
}
