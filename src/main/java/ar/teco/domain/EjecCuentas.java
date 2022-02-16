package ar.teco.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A EjecCuentas.
 */
@Entity
@Table(name = "ejec_cuentas")
public class EjecCuentas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "celular")
    private String celular;

    @Column(name = "mail")
    private String mail;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cod_vendedor")
    private String codVendedor;

    @Column(name = "legajo")
    private String legajo;

    @ManyToOne
    private Segmento segmento;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EjecCuentas id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public EjecCuentas telefono(String telefono) {
        this.setTelefono(telefono);
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return this.apellido;
    }

    public EjecCuentas apellido(String apellido) {
        this.setApellido(apellido);
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return this.celular;
    }

    public EjecCuentas celular(String celular) {
        this.setCelular(celular);
        return this;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return this.mail;
    }

    public EjecCuentas mail(String mail) {
        this.setMail(mail);
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return this.nombre;
    }

    public EjecCuentas nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodVendedor() {
        return this.codVendedor;
    }

    public EjecCuentas codVendedor(String codVendedor) {
        this.setCodVendedor(codVendedor);
        return this;
    }

    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getLegajo() {
        return this.legajo;
    }

    public EjecCuentas legajo(String legajo) {
        this.setLegajo(legajo);
        return this;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public Segmento getSegmento() {
        return this.segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

    public EjecCuentas segmento(Segmento segmento) {
        this.setSegmento(segmento);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EjecCuentas)) {
            return false;
        }
        return id != null && id.equals(((EjecCuentas) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EjecCuentas{" +
            "id=" + getId() +
            ", telefono='" + getTelefono() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", celular='" + getCelular() + "'" +
            ", mail='" + getMail() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", codVendedor='" + getCodVendedor() + "'" +
            ", legajo='" + getLegajo() + "'" +
            "}";
    }
}
