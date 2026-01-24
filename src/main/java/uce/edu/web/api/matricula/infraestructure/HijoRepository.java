package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Link;
import uce.edu.web.api.matricula.domain.Hijo;

import java.util.List;

@ApplicationScoped
public class HijoRepository implements PanacheRepository<Hijo> {
    public List<Hijo> buscarPorIdEstudiante(Long idEstudiante) {
        return find("estudiante.id",idEstudiante).list();
    }
}
