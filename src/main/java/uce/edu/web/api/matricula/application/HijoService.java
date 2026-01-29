package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class HijoService {

    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorIdEstudiante(Long idEstudiante) {
        List<HijoRepresentation> list = new ArrayList<>();
        for (Hijo h: this.hijoRepository.buscarPorIdEstudiante(idEstudiante)){
            list.add(this.mapperToHijoR(h));
        }

        return list;
    }

    private HijoRepresentation mapperToHijoR(Hijo hijo){
        HijoRepresentation hr = new HijoRepresentation();
        hr.id = hijo.id;
        hr.nombre = hijo.nombre;
        hr.apellido = hijo.apellido;
        return hr;
    }
}
