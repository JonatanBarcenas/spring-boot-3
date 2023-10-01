package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegsitroMedico datosRegsitroMedico) {
        this.activo = true;
        this.nombre = datosRegsitroMedico.nombre();
        this.email = datosRegsitroMedico.email();
        this.telefono = datosRegsitroMedico.telefono();
        this.documento = datosRegsitroMedico.documento();
        this.especialidad = datosRegsitroMedico.especialidad();
        this.direccion = new Direccion(datosRegsitroMedico.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.nombre()!=null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.documento()!=null){
            this.documento = datosActualizarMedico.documento();
        }
       if (datosActualizarMedico.direccion()!=null){
           this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
       }

    }

    public void descativarMedico() {
        this.activo = false;
    }
}
