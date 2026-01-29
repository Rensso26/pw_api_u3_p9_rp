package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> ListAll() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante est:this.estudianteRepository.listAll()){
            list.add(this.mapperToER(est));
        }
       return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToER(this.estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear (Estudiante estu) {
        this.estudianteRepository.persist(estu);
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation estu){
        Estudiante estudiante = this.mapperToEstudiante(this.consultarPorId(id));
        estudiante.apellido = estu.apellido;
        estudiante.nombre = estu.nombre;
        estudiante.fechaNacimiento = estu.fechaNacimiento;
        //se actualiza automaticamente por dirty checking

    }

    @Transactional
    public void  actualizarParcial(Integer id, EstudianteRepresentation estu){
        Estudiante estudiante =  this.mapperToEstudiante(this.consultarPorId(id));
        if( estu.nombre != null){
            estudiante.nombre = estu.nombre;
        }
        if( estu.apellido != null){
            estudiante.apellido = estu.apellido;
        }if( estu.fechaNacimiento != null){
            estudiante.fechaNacimiento = estu.fechaNacimiento;
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante est:this.estudianteRepository.find("provincia = ?1 and genero = ?2",provincia, genero).list()){
            list.add(this.mapperToER(est));
        }
        return list;
    }

    public EstudianteRepresentation mapperToER(Estudiante est){
        EstudianteRepresentation estuR =new EstudianteRepresentation();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.apellido = est.apellido;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;
        return estuR;
    }

    public Estudiante mapperToEstudiante(EstudianteRepresentation est){
        Estudiante estuR =new Estudiante();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.apellido = est.apellido;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;
        return estuR;
    }


}
