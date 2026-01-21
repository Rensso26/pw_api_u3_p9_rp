package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

import java.util.List;

@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> ListAll() {
        return materiaRepository.listAll();
    }

    public Materia consultarPorId(long id) {
        return materiaRepository.findById(id);
    }

    @Transactional
    public void crear(Materia materia) {
        materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizar(Materia mat, Long id) {
        Materia materia = materiaRepository.findById(id);
        materia.cod = mat.cod;
        materia.nombre = mat.nombre;
        materia.horas = mat.horas;
        materia.profesor = mat.profesor;
    }

    @Transactional
    public void actualuzarParcial(Long id, Materia mat) {
        Materia materia = this.consultarPorId(id);
        if (mat.cod != null) {
            materia.cod = mat.cod;
        }
        if (mat.nombre != null) {
            materia.nombre = mat.nombre;
        }
        if (mat.horas != null) {
            materia.horas = mat.horas;
        }
        if (mat.profesor != null) {
            materia.profesor = mat.profesor;
        }
    }

    @Transactional
    public void eliminar(Long id) {
        materiaRepository.deleteById(id);
    }

    @Transactional
    public Materia buscarPorCod(String cod) {
        return materiaRepository.findByCod(cod);
    }

    @Transactional
    public void actualizarProf(String prof, Long id) {
        materiaRepository.changeProf(id, prof);
    }

}
