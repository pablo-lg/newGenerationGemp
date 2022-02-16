package ar.teco.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A FiltroRep.
 */
@Entity
@Table(name = "filtro_rep")
public class FiltroRep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "filtro")
    private String filtro;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FiltroRep id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public FiltroRep nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFiltro() {
        return this.filtro;
    }

    public FiltroRep filtro(String filtro) {
        this.setFiltro(filtro);
        return this;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FiltroRep)) {
            return false;
        }
        return id != null && id.equals(((FiltroRep) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FiltroRep{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", filtro='" + getFiltro() + "'" +
            "}";
    }
}
