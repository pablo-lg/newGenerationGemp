package ar.teco.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Parametros.
 */
@Entity
@Table(name = "parametros")
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "clave")
    private String clave;

    @Column(name = "valor")
    private String valor;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Parametros id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return this.clave;
    }

    public Parametros clave(String clave) {
        this.setClave(clave);
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return this.valor;
    }

    public Parametros valor(String valor) {
        this.setValor(valor);
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parametros)) {
            return false;
        }
        return id != null && id.equals(((Parametros) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Parametros{" +
            "id=" + getId() +
            ", clave='" + getClave() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
