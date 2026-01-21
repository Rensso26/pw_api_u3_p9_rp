package uce.edu.web.api.matricula.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "Materia")
@SequenceGenerator(name = "materia_seq", sequenceName = "materia-secuencia", allocationSize = 1)
public class Materia extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materia_seq")
    public Long id;
    @Column(unique = true)
    public String cod;
    public String nombre;
    public Integer horas;
    public String profesor;
}
