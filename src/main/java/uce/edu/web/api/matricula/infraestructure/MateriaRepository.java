package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {

    public Materia findByCod(String cod) {
        return find("cod", cod).firstResult();
    }

    public void changeProf(Long id, String prof) {
        update("profesor = ?1 where id = ?2", prof, id);
    }
}
