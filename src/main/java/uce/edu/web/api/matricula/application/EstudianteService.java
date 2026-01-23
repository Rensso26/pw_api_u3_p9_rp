package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

import java.util.List;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> ListAll() {
       return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPorId(Integer id) {
        return this.estudianteRepository.findById(id.longValue());
    }

    @Transactional
    public void crear (Estudiante estu) {
        this.estudianteRepository.persist(estu);
    }

    @Transactional
    public void actualizar(Integer id, Estudiante estu){
        Estudiante estudiante = this.consultarPorId(id);
        estudiante.apellido = estu.apellido;
        estudiante.nombre = estu.nombre;
        estudiante.fechaNacimiento = estu.fechaNacimiento;
        //se actualiza automaticamente por dirty checking

    }

    @Transactional
    public void  actualizarParcial(Integer id, Estudiante estu){
        Estudiante estudiante = this.consultarPorId(id);
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

    public List<Estudiante> buscarPorProvincia(String provincia, String genero) {
        return this.estudianteRepository.find("provinciaa = ?1 and genero = ?2",provincia, genero).list();
    }


}
