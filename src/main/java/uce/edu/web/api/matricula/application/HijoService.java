package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

import java.util.List;

@ApplicationScoped
public class HijoService {

    @Inject
    private HijoRepository hijoRepository;

    public List<Hijo> buscarPorIdEstudiante(Long idEstudiante) {
        return this.hijoRepository.buscarPorIdEstudiante(idEstudiante);
    }
}
