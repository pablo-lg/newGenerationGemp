package ar.teco.domain;

import ar.teco.domain.enumeration.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A HistoWF.
 */
@Entity
@Table(name = "histo_wf")
public class HistoWF implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_anterior")
    private Estado estadoAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_actual")
    private Estado estadoActual;

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "grupoEmp", "tipoObra", "tipoEmp", "despliegue", "nSE", "segmento", "tecnologia", "ejecCuentas", "direccion", "compentencia",
        },
        allowSetters = true
    )
    private Emprendimiento emprendimiento;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HistoWF id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstadoAnterior() {
        return this.estadoAnterior;
    }

    public HistoWF estadoAnterior(Estado estadoAnterior) {
        this.setEstadoAnterior(estadoAnterior);
        return this;
    }

    public void setEstadoAnterior(Estado estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public Estado getEstadoActual() {
        return this.estadoActual;
    }

    public HistoWF estadoActual(Estado estadoActual) {
        this.setEstadoActual(estadoActual);
        return this;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Emprendimiento getEmprendimiento() {
        return this.emprendimiento;
    }

    public void setEmprendimiento(Emprendimiento emprendimiento) {
        this.emprendimiento = emprendimiento;
    }

    public HistoWF emprendimiento(Emprendimiento emprendimiento) {
        this.setEmprendimiento(emprendimiento);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoWF)) {
            return false;
        }
        return id != null && id.equals(((HistoWF) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoWF{" +
            "id=" + getId() +
            ", estadoAnterior='" + getEstadoAnterior() + "'" +
            ", estadoActual='" + getEstadoActual() + "'" +
            "}";
    }
}
