package uce.edu.web.api.matricula.application.representation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import uce.edu.web.api.matricula.domain.Hijo;

import java.time.LocalDateTime;
import java.util.List;

public class EstudianteRepresentation {

    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;
    public String provincia;
    public String genero;
}
