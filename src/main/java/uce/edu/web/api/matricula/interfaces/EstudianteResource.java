package uce.edu.web.api.matricula.interfaces;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;

import java.util.ArrayList;
import java.util.List;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> listAll() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        List<EstudianteRepresentation> listTod = this.estudianteService.ListAll();
        for (EstudianteRepresentation est : listTod) {
            list.add(this.construirLinks(est));
        }
        return list;
    }

    @GET
    @Path("/{id}")
    // @PermitAll
    @RolesAllowed("admin")
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.construirLinks(this.estudianteService.consultarPorId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardar(EstudianteRepresentation estu) {
        this.estudianteService.crear(this.estudianteService.mapperToEstudiante(estu));
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation estu) {
        estu.id = id;
        this.estudianteService.actualizar(id, estu);
        return Response.status(Response.Status.OK).entity(estu).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualuzarParcial(@PathParam("id") Integer id, EstudianteRepresentation estu) {
        this.estudianteService.actualizarParcial(id, estu);
        return Response.status(Response.Status.OK).entity(estu).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response eliminar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
        return Response.status(225).entity(null).build();
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_XML)
    @RolesAllowed("admin")
    public Response buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero) {
        List<EstudianteRepresentation> lista = this.estudianteService.buscarPorProvincia(provincia, genero);
        return Response.status(212).entity(lista).build();

    }

    @GET
    @Path("/{id}/hijos")
    @RolesAllowed("admin")
    public List<HijoRepresentation> buscarPorEstudiante(@PathParam("id") Long id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er) {
        String self = uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id)).build()
                .toString();
        er.links = List.of(new LinkDto(self, "self"));

        String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id))
                .path("hijos")
                .build().toString();

        er.links = List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos"));
        return er;
    }
}
