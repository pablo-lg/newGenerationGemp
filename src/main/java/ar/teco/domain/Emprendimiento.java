package ar.teco.domain;

import ar.teco.domain.enumeration.Estado;
import ar.teco.domain.enumeration.EstadoBC;
import ar.teco.domain.enumeration.EstadoFirma;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Emprendimiento.
 */
@Entity
@Table(name = "emprendimiento")
public class Emprendimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "fecha_fin_obra")
    private LocalDate fechaFinObra;

    @Column(name = "codigo_obra")
    private String codigoObra;

    @Column(name = "elementos_de_red")
    private String elementosDeRed;

    @Column(name = "clientes_catv")
    private String clientesCatv;

    @Column(name = "clientes_fibertel")
    private String clientesFibertel;

    @Column(name = "clientes_fibertel_lite")
    private String clientesFibertelLite;

    @Column(name = "clientes_flow")
    private String clientesFlow;

    @Column(name = "clientes_combo")
    private String clientesCombo;

    @Column(name = "lineas_voz")
    private String lineasVoz;

    @Column(name = "meses_de_finalizado")
    private String mesesDeFinalizado;

    @Column(name = "altas_bc")
    private String altasBC;

    @Column(name = "penetracion_viv_lot")
    private String penetracionVivLot;

    @Column(name = "penetracion_bc")
    private String penetracionBC;

    @Column(name = "demanda_1")
    private String demanda1;

    @Column(name = "demanda_2")
    private String demanda2;

    @Column(name = "demanda_3")
    private String demanda3;

    @Column(name = "demanda_4")
    private String demanda4;

    @Column(name = "demanda_5")
    private String demanda5;

    @Column(name = "lotes")
    private String lotes;

    @Column(name = "viviendas")
    private String viviendas;

    @Column(name = "com_prof")
    private String comProf;

    @Column(name = "habitaciones")
    private String habitaciones;

    @Column(name = "manzanas")
    private String manzanas;

    @Column(name = "demanda")
    private String demanda;

    @Column(name = "fecha_de_relevamiento")
    private LocalDate fechaDeRelevamiento;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "ano_priorizacion")
    private LocalDate anoPriorizacion;

    @Column(name = "contrato_open")
    private String contratoOpen;

    @Column(name = "negociacion")
    private Boolean negociacion;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "codigo_de_firma")
    private String codigoDeFirma;

    @Column(name = "fecha_firma")
    private LocalDate fechaFirma;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "comen_can")
    private String comenCan;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_firma")
    private EstadoFirma estadoFirma;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_bc")
    private EstadoBC estadoBC;

    @ManyToOne
    private GrupoEmp grupoEmp;

    @ManyToOne
    @JsonIgnoreProperties(value = { "segmento" }, allowSetters = true)
    private TipoObra tipoObra;

    @ManyToOne
    private TipoEmp tipoEmp;

    @ManyToOne
    private Despliegue despliegue;

    @ManyToOne
    private NSE nSE;

    @ManyToOne
    private Segmento segmento;

    @ManyToOne
    private Tecnologia tecnologia;

    @ManyToOne
    @JsonIgnoreProperties(value = { "segmento" }, allowSetters = true)
    private EjecCuentas ejecCuentas;

    @ManyToOne
    @ManyToOne
    private Direccion direccion;

    @ManyToOne
    @ManyToOne
    private Competencia direccion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Emprendimiento id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Emprendimiento nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return this.contacto;
    }

    public Emprendimiento contacto(String contacto) {
        this.setContacto(contacto);
        return this;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public LocalDate getFechaFinObra() {
        return this.fechaFinObra;
    }

    public Emprendimiento fechaFinObra(LocalDate fechaFinObra) {
        this.setFechaFinObra(fechaFinObra);
        return this;
    }

    public void setFechaFinObra(LocalDate fechaFinObra) {
        this.fechaFinObra = fechaFinObra;
    }

    public String getCodigoObra() {
        return this.codigoObra;
    }

    public Emprendimiento codigoObra(String codigoObra) {
        this.setCodigoObra(codigoObra);
        return this;
    }

    public void setCodigoObra(String codigoObra) {
        this.codigoObra = codigoObra;
    }

    public String getElementosDeRed() {
        return this.elementosDeRed;
    }

    public Emprendimiento elementosDeRed(String elementosDeRed) {
        this.setElementosDeRed(elementosDeRed);
        return this;
    }

    public void setElementosDeRed(String elementosDeRed) {
        this.elementosDeRed = elementosDeRed;
    }

    public String getClientesCatv() {
        return this.clientesCatv;
    }

    public Emprendimiento clientesCatv(String clientesCatv) {
        this.setClientesCatv(clientesCatv);
        return this;
    }

    public void setClientesCatv(String clientesCatv) {
        this.clientesCatv = clientesCatv;
    }

    public String getClientesFibertel() {
        return this.clientesFibertel;
    }

    public Emprendimiento clientesFibertel(String clientesFibertel) {
        this.setClientesFibertel(clientesFibertel);
        return this;
    }

    public void setClientesFibertel(String clientesFibertel) {
        this.clientesFibertel = clientesFibertel;
    }

    public String getClientesFibertelLite() {
        return this.clientesFibertelLite;
    }

    public Emprendimiento clientesFibertelLite(String clientesFibertelLite) {
        this.setClientesFibertelLite(clientesFibertelLite);
        return this;
    }

    public void setClientesFibertelLite(String clientesFibertelLite) {
        this.clientesFibertelLite = clientesFibertelLite;
    }

    public String getClientesFlow() {
        return this.clientesFlow;
    }

    public Emprendimiento clientesFlow(String clientesFlow) {
        this.setClientesFlow(clientesFlow);
        return this;
    }

    public void setClientesFlow(String clientesFlow) {
        this.clientesFlow = clientesFlow;
    }

    public String getClientesCombo() {
        return this.clientesCombo;
    }

    public Emprendimiento clientesCombo(String clientesCombo) {
        this.setClientesCombo(clientesCombo);
        return this;
    }

    public void setClientesCombo(String clientesCombo) {
        this.clientesCombo = clientesCombo;
    }

    public String getLineasVoz() {
        return this.lineasVoz;
    }

    public Emprendimiento lineasVoz(String lineasVoz) {
        this.setLineasVoz(lineasVoz);
        return this;
    }

    public void setLineasVoz(String lineasVoz) {
        this.lineasVoz = lineasVoz;
    }

    public String getMesesDeFinalizado() {
        return this.mesesDeFinalizado;
    }

    public Emprendimiento mesesDeFinalizado(String mesesDeFinalizado) {
        this.setMesesDeFinalizado(mesesDeFinalizado);
        return this;
    }

    public void setMesesDeFinalizado(String mesesDeFinalizado) {
        this.mesesDeFinalizado = mesesDeFinalizado;
    }

    public String getAltasBC() {
        return this.altasBC;
    }

    public Emprendimiento altasBC(String altasBC) {
        this.setAltasBC(altasBC);
        return this;
    }

    public void setAltasBC(String altasBC) {
        this.altasBC = altasBC;
    }

    public String getPenetracionVivLot() {
        return this.penetracionVivLot;
    }

    public Emprendimiento penetracionVivLot(String penetracionVivLot) {
        this.setPenetracionVivLot(penetracionVivLot);
        return this;
    }

    public void setPenetracionVivLot(String penetracionVivLot) {
        this.penetracionVivLot = penetracionVivLot;
    }

    public String getPenetracionBC() {
        return this.penetracionBC;
    }

    public Emprendimiento penetracionBC(String penetracionBC) {
        this.setPenetracionBC(penetracionBC);
        return this;
    }

    public void setPenetracionBC(String penetracionBC) {
        this.penetracionBC = penetracionBC;
    }

    public String getDemanda1() {
        return this.demanda1;
    }

    public Emprendimiento demanda1(String demanda1) {
        this.setDemanda1(demanda1);
        return this;
    }

    public void setDemanda1(String demanda1) {
        this.demanda1 = demanda1;
    }

    public String getDemanda2() {
        return this.demanda2;
    }

    public Emprendimiento demanda2(String demanda2) {
        this.setDemanda2(demanda2);
        return this;
    }

    public void setDemanda2(String demanda2) {
        this.demanda2 = demanda2;
    }

    public String getDemanda3() {
        return this.demanda3;
    }

    public Emprendimiento demanda3(String demanda3) {
        this.setDemanda3(demanda3);
        return this;
    }

    public void setDemanda3(String demanda3) {
        this.demanda3 = demanda3;
    }

    public String getDemanda4() {
        return this.demanda4;
    }

    public Emprendimiento demanda4(String demanda4) {
        this.setDemanda4(demanda4);
        return this;
    }

    public void setDemanda4(String demanda4) {
        this.demanda4 = demanda4;
    }

    public String getDemanda5() {
        return this.demanda5;
    }

    public Emprendimiento demanda5(String demanda5) {
        this.setDemanda5(demanda5);
        return this;
    }

    public void setDemanda5(String demanda5) {
        this.demanda5 = demanda5;
    }

    public String getLotes() {
        return this.lotes;
    }

    public Emprendimiento lotes(String lotes) {
        this.setLotes(lotes);
        return this;
    }

    public void setLotes(String lotes) {
        this.lotes = lotes;
    }

    public String getViviendas() {
        return this.viviendas;
    }

    public Emprendimiento viviendas(String viviendas) {
        this.setViviendas(viviendas);
        return this;
    }

    public void setViviendas(String viviendas) {
        this.viviendas = viviendas;
    }

    public String getComProf() {
        return this.comProf;
    }

    public Emprendimiento comProf(String comProf) {
        this.setComProf(comProf);
        return this;
    }

    public void setComProf(String comProf) {
        this.comProf = comProf;
    }

    public String getHabitaciones() {
        return this.habitaciones;
    }

    public Emprendimiento habitaciones(String habitaciones) {
        this.setHabitaciones(habitaciones);
        return this;
    }

    public void setHabitaciones(String habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getManzanas() {
        return this.manzanas;
    }

    public Emprendimiento manzanas(String manzanas) {
        this.setManzanas(manzanas);
        return this;
    }

    public void setManzanas(String manzanas) {
        this.manzanas = manzanas;
    }

    public String getDemanda() {
        return this.demanda;
    }

    public Emprendimiento demanda(String demanda) {
        this.setDemanda(demanda);
        return this;
    }

    public void setDemanda(String demanda) {
        this.demanda = demanda;
    }

    public LocalDate getFechaDeRelevamiento() {
        return this.fechaDeRelevamiento;
    }

    public Emprendimiento fechaDeRelevamiento(LocalDate fechaDeRelevamiento) {
        this.setFechaDeRelevamiento(fechaDeRelevamiento);
        return this;
    }

    public void setFechaDeRelevamiento(LocalDate fechaDeRelevamiento) {
        this.fechaDeRelevamiento = fechaDeRelevamiento;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Emprendimiento telefono(String telefono) {
        this.setTelefono(telefono);
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getAnoPriorizacion() {
        return this.anoPriorizacion;
    }

    public Emprendimiento anoPriorizacion(LocalDate anoPriorizacion) {
        this.setAnoPriorizacion(anoPriorizacion);
        return this;
    }

    public void setAnoPriorizacion(LocalDate anoPriorizacion) {
        this.anoPriorizacion = anoPriorizacion;
    }

    public String getContratoOpen() {
        return this.contratoOpen;
    }

    public Emprendimiento contratoOpen(String contratoOpen) {
        this.setContratoOpen(contratoOpen);
        return this;
    }

    public void setContratoOpen(String contratoOpen) {
        this.contratoOpen = contratoOpen;
    }

    public Boolean getNegociacion() {
        return this.negociacion;
    }

    public Emprendimiento negociacion(Boolean negociacion) {
        this.setNegociacion(negociacion);
        return this;
    }

    public void setNegociacion(Boolean negociacion) {
        this.negociacion = negociacion;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public Emprendimiento fecha(LocalDate fecha) {
        this.setFecha(fecha);
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCodigoDeFirma() {
        return this.codigoDeFirma;
    }

    public Emprendimiento codigoDeFirma(String codigoDeFirma) {
        this.setCodigoDeFirma(codigoDeFirma);
        return this;
    }

    public void setCodigoDeFirma(String codigoDeFirma) {
        this.codigoDeFirma = codigoDeFirma;
    }

    public LocalDate getFechaFirma() {
        return this.fechaFirma;
    }

    public Emprendimiento fechaFirma(LocalDate fechaFirma) {
        this.setFechaFirma(fechaFirma);
        return this;
    }

    public void setFechaFirma(LocalDate fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public Emprendimiento observaciones(String observaciones) {
        this.setObservaciones(observaciones);
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getComentario() {
        return this.comentario;
    }

    public Emprendimiento comentario(String comentario) {
        this.setComentario(comentario);
        return this;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComenCan() {
        return this.comenCan;
    }

    public Emprendimiento comenCan(String comenCan) {
        this.setComenCan(comenCan);
        return this;
    }

    public void setComenCan(String comenCan) {
        this.comenCan = comenCan;
    }

    public EstadoFirma getEstadoFirma() {
        return this.estadoFirma;
    }

    public Emprendimiento estadoFirma(EstadoFirma estadoFirma) {
        this.setEstadoFirma(estadoFirma);
        return this;
    }

    public void setEstadoFirma(EstadoFirma estadoFirma) {
        this.estadoFirma = estadoFirma;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public Emprendimiento estado(Estado estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EstadoBC getEstadoBC() {
        return this.estadoBC;
    }

    public Emprendimiento estadoBC(EstadoBC estadoBC) {
        this.setEstadoBC(estadoBC);
        return this;
    }

    public void setEstadoBC(EstadoBC estadoBC) {
        this.estadoBC = estadoBC;
    }

    public GrupoEmp getGrupoEmp() {
        return this.grupoEmp;
    }

    public void setGrupoEmp(GrupoEmp grupoEmp) {
        this.grupoEmp = grupoEmp;
    }

    public Emprendimiento grupoEmp(GrupoEmp grupoEmp) {
        this.setGrupoEmp(grupoEmp);
        return this;
    }

    public TipoObra getTipoObra() {
        return this.tipoObra;
    }

    public void setTipoObra(TipoObra tipoObra) {
        this.tipoObra = tipoObra;
    }

    public Emprendimiento tipoObra(TipoObra tipoObra) {
        this.setTipoObra(tipoObra);
        return this;
    }

    public TipoEmp getTipoEmp() {
        return this.tipoEmp;
    }

    public void setTipoEmp(TipoEmp tipoEmp) {
        this.tipoEmp = tipoEmp;
    }

    public Emprendimiento tipoEmp(TipoEmp tipoEmp) {
        this.setTipoEmp(tipoEmp);
        return this;
    }

    public Despliegue getDespliegue() {
        return this.despliegue;
    }

    public void setDespliegue(Despliegue despliegue) {
        this.despliegue = despliegue;
    }

    public Emprendimiento despliegue(Despliegue despliegue) {
        this.setDespliegue(despliegue);
        return this;
    }

    public NSE getNSE() {
        return this.nSE;
    }

    public void setNSE(NSE nSE) {
        this.nSE = nSE;
    }

    public Emprendimiento nSE(NSE nSE) {
        this.setNSE(nSE);
        return this;
    }

    public Segmento getSegmento() {
        return this.segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

    public Emprendimiento segmento(Segmento segmento) {
        this.setSegmento(segmento);
        return this;
    }

    public Tecnologia getTecnologia() {
        return this.tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Emprendimiento tecnologia(Tecnologia tecnologia) {
        this.setTecnologia(tecnologia);
        return this;
    }

    public EjecCuentas getEjecCuentas() {
        return this.ejecCuentas;
    }

    public void setEjecCuentas(EjecCuentas ejecCuentas) {
        this.ejecCuentas = ejecCuentas;
    }

    public Emprendimiento ejecCuentas(EjecCuentas ejecCuentas) {
        this.setEjecCuentas(ejecCuentas);
        return this;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Emprendimiento direccion(Direccion direccion) {
        this.setDireccion(direccion);
        return this;
    }

    public Competencia getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Competencia competencia) {
        this.direccion = competencia;
    }

    public Emprendimiento direccion(Competencia competencia) {
        this.setDireccion(competencia);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Emprendimiento)) {
            return false;
        }
        return id != null && id.equals(((Emprendimiento) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Emprendimiento{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", contacto='" + getContacto() + "'" +
            ", fechaFinObra='" + getFechaFinObra() + "'" +
            ", codigoObra='" + getCodigoObra() + "'" +
            ", elementosDeRed='" + getElementosDeRed() + "'" +
            ", clientesCatv='" + getClientesCatv() + "'" +
            ", clientesFibertel='" + getClientesFibertel() + "'" +
            ", clientesFibertelLite='" + getClientesFibertelLite() + "'" +
            ", clientesFlow='" + getClientesFlow() + "'" +
            ", clientesCombo='" + getClientesCombo() + "'" +
            ", lineasVoz='" + getLineasVoz() + "'" +
            ", mesesDeFinalizado='" + getMesesDeFinalizado() + "'" +
            ", altasBC='" + getAltasBC() + "'" +
            ", penetracionVivLot='" + getPenetracionVivLot() + "'" +
            ", penetracionBC='" + getPenetracionBC() + "'" +
            ", demanda1='" + getDemanda1() + "'" +
            ", demanda2='" + getDemanda2() + "'" +
            ", demanda3='" + getDemanda3() + "'" +
            ", demanda4='" + getDemanda4() + "'" +
            ", demanda5='" + getDemanda5() + "'" +
            ", lotes='" + getLotes() + "'" +
            ", viviendas='" + getViviendas() + "'" +
            ", comProf='" + getComProf() + "'" +
            ", habitaciones='" + getHabitaciones() + "'" +
            ", manzanas='" + getManzanas() + "'" +
            ", demanda='" + getDemanda() + "'" +
            ", fechaDeRelevamiento='" + getFechaDeRelevamiento() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", anoPriorizacion='" + getAnoPriorizacion() + "'" +
            ", contratoOpen='" + getContratoOpen() + "'" +
            ", negociacion='" + getNegociacion() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", codigoDeFirma='" + getCodigoDeFirma() + "'" +
            ", fechaFirma='" + getFechaFirma() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", comentario='" + getComentario() + "'" +
            ", comenCan='" + getComenCan() + "'" +
            ", estadoFirma='" + getEstadoFirma() + "'" +
            ", estado='" + getEstado() + "'" +
            ", estadoBC='" + getEstadoBC() + "'" +
            "}";
    }
}
