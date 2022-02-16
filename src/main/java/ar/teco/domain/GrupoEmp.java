package ar.teco.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A GrupoEmp.
 */
@Entity
@Table(name = "grupo_emp")
public class GrupoEmp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "es_protegido")
    private Boolean esProtegido;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GrupoEmp id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public GrupoEmp descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEsProtegido() {
        return this.esProtegido;
    }

    public GrupoEmp esProtegido(Boolean esProtegido) {
        this.setEsProtegido(esProtegido);
        return this;
    }

    public void setEsProtegido(Boolean esProtegido) {
        this.esProtegido = esProtegido;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GrupoEmp)) {
            return false;
        }
        return id != null && id.equals(((GrupoEmp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GrupoEmp{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", esProtegido='" + getEsProtegido() + "'" +
            "}";
    }
}
