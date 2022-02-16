package ar.teco.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A TipoEmp.
 */
@Entity
@Table(name = "tipo_emp")
public class TipoEmp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "demanda")
    private Boolean demanda;

    @Column(name = "descripcion")
    private String descripcion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TipoEmp id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDemanda() {
        return this.demanda;
    }

    public TipoEmp demanda(Boolean demanda) {
        this.setDemanda(demanda);
        return this;
    }

    public void setDemanda(Boolean demanda) {
        this.demanda = demanda;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public TipoEmp descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoEmp)) {
            return false;
        }
        return id != null && id.equals(((TipoEmp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoEmp{" +
            "id=" + getId() +
            ", demanda='" + getDemanda() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
