package ar.teco.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Demanda.
 */
@Entity
@Table(name = "demanda")
public class Demanda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "a_1")
    private String a1;

    @Column(name = "a_2")
    private String a2;

    @Column(name = "a_3")
    private String a3;

    @Column(name = "a_4")
    private String a4;

    @Column(name = "a_5")
    private String a5;

    @ManyToOne
    private TipoEmp tipoEmp;

    @ManyToOne
    private Despliegue despliegue;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Demanda id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String geta1() {
        return this.a1;
    }

    public Demanda a1(String a1) {
        this.seta1(a1);
        return this;
    }

    public void seta1(String a1) {
        this.a1 = a1;
    }

    public String geta2() {
        return this.a2;
    }

    public Demanda a2(String a2) {
        this.seta2(a2);
        return this;
    }

    public void seta2(String a2) {
        this.a2 = a2;
    }

    public String geta3() {
        return this.a3;
    }

    public Demanda a3(String a3) {
        this.seta3(a3);
        return this;
    }

    public void seta3(String a3) {
        this.a3 = a3;
    }

    public String geta4() {
        return this.a4;
    }

    public Demanda a4(String a4) {
        this.seta4(a4);
        return this;
    }

    public void seta4(String a4) {
        this.a4 = a4;
    }

    public String geta5() {
        return this.a5;
    }

    public Demanda a5(String a5) {
        this.seta5(a5);
        return this;
    }

    public void seta5(String a5) {
        this.a5 = a5;
    }

    public TipoEmp getTipoEmp() {
        return this.tipoEmp;
    }

    public void setTipoEmp(TipoEmp tipoEmp) {
        this.tipoEmp = tipoEmp;
    }

    public Demanda tipoEmp(TipoEmp tipoEmp) {
        this.setTipoEmp(tipoEmp);
        return this;
    }

    public Despliegue getDespliegue() {
        return this.despliegue;
    }

    public void setDespliegue(Despliegue despliegue) {
        this.despliegue = despliegue;
    }

    public Demanda despliegue(Despliegue despliegue) {
        this.setDespliegue(despliegue);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demanda)) {
            return false;
        }
        return id != null && id.equals(((Demanda) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Demanda{" +
            "id=" + getId() +
            ", a1='" + geta1() + "'" +
            ", a2='" + geta2() + "'" +
            ", a3='" + geta3() + "'" +
            ", a4='" + geta4() + "'" +
            ", a5='" + geta5() + "'" +
            "}";
    }
}
