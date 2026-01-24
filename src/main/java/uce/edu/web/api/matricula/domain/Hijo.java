package uce.edu.web.api.matricula.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

@Entity
@Table(name = "Hijo")
@SequenceGenerator(name = "hijo_seq", sequenceName = "hijo-secuencia", allocationSize = 1)
public class Hijo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hijo_seq")
    public Long id;
    public String nombre;
    public String apellido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id")
    @JsonbTransient
    public Estudiante estudiante;


}
