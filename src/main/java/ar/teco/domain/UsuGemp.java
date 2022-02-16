package ar.teco.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A UsuGemp.
 */
@Entity
@Table(name = "usu_gemp")
public class UsuGemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "usu")
    private String usu;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "perfiles")
    private String perfiles;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UsuGemp id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsu() {
        return this.usu;
    }

    public UsuGemp usu(String usu) {
        this.setUsu(usu);
        return this;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getNombre() {
        return this.nombre;
    }

    public UsuGemp nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public UsuGemp apellido(String apellido) {
        this.setApellido(apellido);
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public UsuGemp email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfiles() {
        return this.perfiles;
    }

    public UsuGemp perfiles(String perfiles) {
        this.setPerfiles(perfiles);
        return this;
    }

    public void setPerfiles(String perfiles) {
        this.perfiles = perfiles;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsuGemp)) {
            return false;
        }
        return id != null && id.equals(((UsuGemp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UsuGemp{" +
            "id=" + getId() +
            ", usu='" + getUsu() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", email='" + getEmail() + "'" +
            ", perfiles='" + getPerfiles() + "'" +
            "}";
    }
}
