package uce.edu.web.api.matricula.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "Estudiante")
@SequenceGenerator(name = "estudiante_seq", sequenceName = "estudiante-secuencia", allocationSize = 1)
public class Estudiante extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_seq")
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDate fechaNacimiento;
    public String provincia;
    public String genero;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<Hijo> hijos;
}
