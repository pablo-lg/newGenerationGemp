package ar.teco.service.criteria;

import ar.teco.domain.enumeration.Estado;
import ar.teco.domain.enumeration.EstadoBC;
import ar.teco.domain.enumeration.EstadoFirma;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link ar.teco.domain.Emprendimiento} entity. This class is used
 * in {@link ar.teco.web.rest.EmprendimientoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /emprendimientos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmprendimientoCriteria implements Serializable, Criteria {

    /**
     * Class for filtering EstadoFirma
     */
    public static class EstadoFirmaFilter extends Filter<EstadoFirma> {

        public EstadoFirmaFilter() {}

        public EstadoFirmaFilter(EstadoFirmaFilter filter) {
            super(filter);
        }

        @Override
        public EstadoFirmaFilter copy() {
            return new EstadoFirmaFilter(this);
        }
    }

    /**
     * Class for filtering Estado
     */
    public static class EstadoFilter extends Filter<Estado> {

        public EstadoFilter() {}

        public EstadoFilter(EstadoFilter filter) {
            super(filter);
        }

        @Override
        public EstadoFilter copy() {
            return new EstadoFilter(this);
        }
    }

    /**
     * Class for filtering EstadoBC
     */
    public static class EstadoBCFilter extends Filter<EstadoBC> {

        public EstadoBCFilter() {}

        public EstadoBCFilter(EstadoBCFilter filter) {
            super(filter);
        }

        @Override
        public EstadoBCFilter copy() {
            return new EstadoBCFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private StringFilter contacto;

    private LocalDateFilter fechaFinObra;

    private StringFilter codigoObra;

    private StringFilter elementosDeRed;

    private StringFilter clientesCatv;

    private StringFilter clientesFibertel;

    private StringFilter clientesFibertelLite;

    private StringFilter clientesFlow;

    private StringFilter clientesCombo;

    private StringFilter lineasVoz;

    private StringFilter mesesDeFinalizado;

    private StringFilter altasBC;

    private StringFilter penetracionVivLot;

    private StringFilter penetracionBC;

    private StringFilter demanda1;

    private StringFilter demanda2;

    private StringFilter demanda3;

    private StringFilter demanda4;

    private StringFilter demanda5;

    private StringFilter lotes;

    private StringFilter viviendas;

    private StringFilter comProf;

    private StringFilter habitaciones;

    private StringFilter manzanas;

    private StringFilter demanda;

    private LocalDateFilter fechaDeRelevamiento;

    private StringFilter telefono;

    private LocalDateFilter anoPriorizacion;

    private StringFilter contratoOpen;

    private BooleanFilter negociacion;

    private LocalDateFilter fecha;

    private StringFilter codigoDeFirma;

    private LocalDateFilter fechaFirma;

    private StringFilter observaciones;

    private StringFilter comentario;

    private StringFilter comenCan;

    private EstadoFirmaFilter estadoFirma;

    private EstadoFilter estado;

    private EstadoBCFilter estadoBC;

    private LongFilter grupoEmpId;

    private LongFilter tipoObraId;

    private LongFilter tipoEmpId;

    private LongFilter despliegueId;

    private LongFilter nSEId;

    private LongFilter segmentoId;

    private LongFilter tecnologiaId;

    private LongFilter ejecCuentasId;

    private LongFilter direccionId;

    private LongFilter compentenciaId;

    private Boolean distinct;

    public EmprendimientoCriteria() {}

    public EmprendimientoCriteria(EmprendimientoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.contacto = other.contacto == null ? null : other.contacto.copy();
        this.fechaFinObra = other.fechaFinObra == null ? null : other.fechaFinObra.copy();
        this.codigoObra = other.codigoObra == null ? null : other.codigoObra.copy();
        this.elementosDeRed = other.elementosDeRed == null ? null : other.elementosDeRed.copy();
        this.clientesCatv = other.clientesCatv == null ? null : other.clientesCatv.copy();
        this.clientesFibertel = other.clientesFibertel == null ? null : other.clientesFibertel.copy();
        this.clientesFibertelLite = other.clientesFibertelLite == null ? null : other.clientesFibertelLite.copy();
        this.clientesFlow = other.clientesFlow == null ? null : other.clientesFlow.copy();
        this.clientesCombo = other.clientesCombo == null ? null : other.clientesCombo.copy();
        this.lineasVoz = other.lineasVoz == null ? null : other.lineasVoz.copy();
        this.mesesDeFinalizado = other.mesesDeFinalizado == null ? null : other.mesesDeFinalizado.copy();
        this.altasBC = other.altasBC == null ? null : other.altasBC.copy();
        this.penetracionVivLot = other.penetracionVivLot == null ? null : other.penetracionVivLot.copy();
        this.penetracionBC = other.penetracionBC == null ? null : other.penetracionBC.copy();
        this.demanda1 = other.demanda1 == null ? null : other.demanda1.copy();
        this.demanda2 = other.demanda2 == null ? null : other.demanda2.copy();
        this.demanda3 = other.demanda3 == null ? null : other.demanda3.copy();
        this.demanda4 = other.demanda4 == null ? null : other.demanda4.copy();
        this.demanda5 = other.demanda5 == null ? null : other.demanda5.copy();
        this.lotes = other.lotes == null ? null : other.lotes.copy();
        this.viviendas = other.viviendas == null ? null : other.viviendas.copy();
        this.comProf = other.comProf == null ? null : other.comProf.copy();
        this.habitaciones = other.habitaciones == null ? null : other.habitaciones.copy();
        this.manzanas = other.manzanas == null ? null : other.manzanas.copy();
        this.demanda = other.demanda == null ? null : other.demanda.copy();
        this.fechaDeRelevamiento = other.fechaDeRelevamiento == null ? null : other.fechaDeRelevamiento.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.anoPriorizacion = other.anoPriorizacion == null ? null : other.anoPriorizacion.copy();
        this.contratoOpen = other.contratoOpen == null ? null : other.contratoOpen.copy();
        this.negociacion = other.negociacion == null ? null : other.negociacion.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.codigoDeFirma = other.codigoDeFirma == null ? null : other.codigoDeFirma.copy();
        this.fechaFirma = other.fechaFirma == null ? null : other.fechaFirma.copy();
        this.observaciones = other.observaciones == null ? null : other.observaciones.copy();
        this.comentario = other.comentario == null ? null : other.comentario.copy();
        this.comenCan = other.comenCan == null ? null : other.comenCan.copy();
        this.estadoFirma = other.estadoFirma == null ? null : other.estadoFirma.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
        this.estadoBC = other.estadoBC == null ? null : other.estadoBC.copy();
        this.grupoEmpId = other.grupoEmpId == null ? null : other.grupoEmpId.copy();
        this.tipoObraId = other.tipoObraId == null ? null : other.tipoObraId.copy();
        this.tipoEmpId = other.tipoEmpId == null ? null : other.tipoEmpId.copy();
        this.despliegueId = other.despliegueId == null ? null : other.despliegueId.copy();
        this.nSEId = other.nSEId == null ? null : other.nSEId.copy();
        this.segmentoId = other.segmentoId == null ? null : other.segmentoId.copy();
        this.tecnologiaId = other.tecnologiaId == null ? null : other.tecnologiaId.copy();
        this.ejecCuentasId = other.ejecCuentasId == null ? null : other.ejecCuentasId.copy();
        this.direccionId = other.direccionId == null ? null : other.direccionId.copy();
        this.compentenciaId = other.compentenciaId == null ? null : other.compentenciaId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EmprendimientoCriteria copy() {
        return new EmprendimientoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public StringFilter nombre() {
        if (nombre == null) {
            nombre = new StringFilter();
        }
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getContacto() {
        return contacto;
    }

    public StringFilter contacto() {
        if (contacto == null) {
            contacto = new StringFilter();
        }
        return contacto;
    }

    public void setContacto(StringFilter contacto) {
        this.contacto = contacto;
    }

    public LocalDateFilter getFechaFinObra() {
        return fechaFinObra;
    }

    public LocalDateFilter fechaFinObra() {
        if (fechaFinObra == null) {
            fechaFinObra = new LocalDateFilter();
        }
        return fechaFinObra;
    }

    public void setFechaFinObra(LocalDateFilter fechaFinObra) {
        this.fechaFinObra = fechaFinObra;
    }

    public StringFilter getCodigoObra() {
        return codigoObra;
    }

    public StringFilter codigoObra() {
        if (codigoObra == null) {
            codigoObra = new StringFilter();
        }
        return codigoObra;
    }

    public void setCodigoObra(StringFilter codigoObra) {
        this.codigoObra = codigoObra;
    }

    public StringFilter getElementosDeRed() {
        return elementosDeRed;
    }

    public StringFilter elementosDeRed() {
        if (elementosDeRed == null) {
            elementosDeRed = new StringFilter();
        }
        return elementosDeRed;
    }

    public void setElementosDeRed(StringFilter elementosDeRed) {
        this.elementosDeRed = elementosDeRed;
    }

    public StringFilter getClientesCatv() {
        return clientesCatv;
    }

    public StringFilter clientesCatv() {
        if (clientesCatv == null) {
            clientesCatv = new StringFilter();
        }
        return clientesCatv;
    }

    public void setClientesCatv(StringFilter clientesCatv) {
        this.clientesCatv = clientesCatv;
    }

    public StringFilter getClientesFibertel() {
        return clientesFibertel;
    }

    public StringFilter clientesFibertel() {
        if (clientesFibertel == null) {
            clientesFibertel = new StringFilter();
        }
        return clientesFibertel;
    }

    public void setClientesFibertel(StringFilter clientesFibertel) {
        this.clientesFibertel = clientesFibertel;
    }

    public StringFilter getClientesFibertelLite() {
        return clientesFibertelLite;
    }

    public StringFilter clientesFibertelLite() {
        if (clientesFibertelLite == null) {
            clientesFibertelLite = new StringFilter();
        }
        return clientesFibertelLite;
    }

    public void setClientesFibertelLite(StringFilter clientesFibertelLite) {
        this.clientesFibertelLite = clientesFibertelLite;
    }

    public StringFilter getClientesFlow() {
        return clientesFlow;
    }

    public StringFilter clientesFlow() {
        if (clientesFlow == null) {
            clientesFlow = new StringFilter();
        }
        return clientesFlow;
    }

    public void setClientesFlow(StringFilter clientesFlow) {
        this.clientesFlow = clientesFlow;
    }

    public StringFilter getClientesCombo() {
        return clientesCombo;
    }

    public StringFilter clientesCombo() {
        if (clientesCombo == null) {
            clientesCombo = new StringFilter();
        }
        return clientesCombo;
    }

    public void setClientesCombo(StringFilter clientesCombo) {
        this.clientesCombo = clientesCombo;
    }

    public StringFilter getLineasVoz() {
        return lineasVoz;
    }

    public StringFilter lineasVoz() {
        if (lineasVoz == null) {
            lineasVoz = new StringFilter();
        }
        return lineasVoz;
    }

    public void setLineasVoz(StringFilter lineasVoz) {
        this.lineasVoz = lineasVoz;
    }

    public StringFilter getMesesDeFinalizado() {
        return mesesDeFinalizado;
    }

    public StringFilter mesesDeFinalizado() {
        if (mesesDeFinalizado == null) {
            mesesDeFinalizado = new StringFilter();
        }
        return mesesDeFinalizado;
    }

    public void setMesesDeFinalizado(StringFilter mesesDeFinalizado) {
        this.mesesDeFinalizado = mesesDeFinalizado;
    }

    public StringFilter getAltasBC() {
        return altasBC;
    }

    public StringFilter altasBC() {
        if (altasBC == null) {
            altasBC = new StringFilter();
        }
        return altasBC;
    }

    public void setAltasBC(StringFilter altasBC) {
        this.altasBC = altasBC;
    }

    public StringFilter getPenetracionVivLot() {
        return penetracionVivLot;
    }

    public StringFilter penetracionVivLot() {
        if (penetracionVivLot == null) {
            penetracionVivLot = new StringFilter();
        }
        return penetracionVivLot;
    }

    public void setPenetracionVivLot(StringFilter penetracionVivLot) {
        this.penetracionVivLot = penetracionVivLot;
    }

    public StringFilter getPenetracionBC() {
        return penetracionBC;
    }

    public StringFilter penetracionBC() {
        if (penetracionBC == null) {
            penetracionBC = new StringFilter();
        }
        return penetracionBC;
    }

    public void setPenetracionBC(StringFilter penetracionBC) {
        this.penetracionBC = penetracionBC;
    }

    public StringFilter getDemanda1() {
        return demanda1;
    }

    public StringFilter demanda1() {
        if (demanda1 == null) {
            demanda1 = new StringFilter();
        }
        return demanda1;
    }

    public void setDemanda1(StringFilter demanda1) {
        this.demanda1 = demanda1;
    }

    public StringFilter getDemanda2() {
        return demanda2;
    }

    public StringFilter demanda2() {
        if (demanda2 == null) {
            demanda2 = new StringFilter();
        }
        return demanda2;
    }

    public void setDemanda2(StringFilter demanda2) {
        this.demanda2 = demanda2;
    }

    public StringFilter getDemanda3() {
        return demanda3;
    }

    public StringFilter demanda3() {
        if (demanda3 == null) {
            demanda3 = new StringFilter();
        }
        return demanda3;
    }

    public void setDemanda3(StringFilter demanda3) {
        this.demanda3 = demanda3;
    }

    public StringFilter getDemanda4() {
        return demanda4;
    }

    public StringFilter demanda4() {
        if (demanda4 == null) {
            demanda4 = new StringFilter();
        }
        return demanda4;
    }

    public void setDemanda4(StringFilter demanda4) {
        this.demanda4 = demanda4;
    }

    public StringFilter getDemanda5() {
        return demanda5;
    }

    public StringFilter demanda5() {
        if (demanda5 == null) {
            demanda5 = new StringFilter();
        }
        return demanda5;
    }

    public void setDemanda5(StringFilter demanda5) {
        this.demanda5 = demanda5;
    }

    public StringFilter getLotes() {
        return lotes;
    }

    public StringFilter lotes() {
        if (lotes == null) {
            lotes = new StringFilter();
        }
        return lotes;
    }

    public void setLotes(StringFilter lotes) {
        this.lotes = lotes;
    }

    public StringFilter getViviendas() {
        return viviendas;
    }

    public StringFilter viviendas() {
        if (viviendas == null) {
            viviendas = new StringFilter();
        }
        return viviendas;
    }

    public void setViviendas(StringFilter viviendas) {
        this.viviendas = viviendas;
    }

    public StringFilter getComProf() {
        return comProf;
    }

    public StringFilter comProf() {
        if (comProf == null) {
            comProf = new StringFilter();
        }
        return comProf;
    }

    public void setComProf(StringFilter comProf) {
        this.comProf = comProf;
    }

    public StringFilter getHabitaciones() {
        return habitaciones;
    }

    public StringFilter habitaciones() {
        if (habitaciones == null) {
            habitaciones = new StringFilter();
        }
        return habitaciones;
    }

    public void setHabitaciones(StringFilter habitaciones) {
        this.habitaciones = habitaciones;
    }

    public StringFilter getManzanas() {
        return manzanas;
    }

    public StringFilter manzanas() {
        if (manzanas == null) {
            manzanas = new StringFilter();
        }
        return manzanas;
    }

    public void setManzanas(StringFilter manzanas) {
        this.manzanas = manzanas;
    }

    public StringFilter getDemanda() {
        return demanda;
    }

    public StringFilter demanda() {
        if (demanda == null) {
            demanda = new StringFilter();
        }
        return demanda;
    }

    public void setDemanda(StringFilter demanda) {
        this.demanda = demanda;
    }

    public LocalDateFilter getFechaDeRelevamiento() {
        return fechaDeRelevamiento;
    }

    public LocalDateFilter fechaDeRelevamiento() {
        if (fechaDeRelevamiento == null) {
            fechaDeRelevamiento = new LocalDateFilter();
        }
        return fechaDeRelevamiento;
    }

    public void setFechaDeRelevamiento(LocalDateFilter fechaDeRelevamiento) {
        this.fechaDeRelevamiento = fechaDeRelevamiento;
    }

    public StringFilter getTelefono() {
        return telefono;
    }

    public StringFilter telefono() {
        if (telefono == null) {
            telefono = new StringFilter();
        }
        return telefono;
    }

    public void setTelefono(StringFilter telefono) {
        this.telefono = telefono;
    }

    public LocalDateFilter getAnoPriorizacion() {
        return anoPriorizacion;
    }

    public LocalDateFilter anoPriorizacion() {
        if (anoPriorizacion == null) {
            anoPriorizacion = new LocalDateFilter();
        }
        return anoPriorizacion;
    }

    public void setAnoPriorizacion(LocalDateFilter anoPriorizacion) {
        this.anoPriorizacion = anoPriorizacion;
    }

    public StringFilter getContratoOpen() {
        return contratoOpen;
    }

    public StringFilter contratoOpen() {
        if (contratoOpen == null) {
            contratoOpen = new StringFilter();
        }
        return contratoOpen;
    }

    public void setContratoOpen(StringFilter contratoOpen) {
        this.contratoOpen = contratoOpen;
    }

    public BooleanFilter getNegociacion() {
        return negociacion;
    }

    public BooleanFilter negociacion() {
        if (negociacion == null) {
            negociacion = new BooleanFilter();
        }
        return negociacion;
    }

    public void setNegociacion(BooleanFilter negociacion) {
        this.negociacion = negociacion;
    }

    public LocalDateFilter getFecha() {
        return fecha;
    }

    public LocalDateFilter fecha() {
        if (fecha == null) {
            fecha = new LocalDateFilter();
        }
        return fecha;
    }

    public void setFecha(LocalDateFilter fecha) {
        this.fecha = fecha;
    }

    public StringFilter getCodigoDeFirma() {
        return codigoDeFirma;
    }

    public StringFilter codigoDeFirma() {
        if (codigoDeFirma == null) {
            codigoDeFirma = new StringFilter();
        }
        return codigoDeFirma;
    }

    public void setCodigoDeFirma(StringFilter codigoDeFirma) {
        this.codigoDeFirma = codigoDeFirma;
    }

    public LocalDateFilter getFechaFirma() {
        return fechaFirma;
    }

    public LocalDateFilter fechaFirma() {
        if (fechaFirma == null) {
            fechaFirma = new LocalDateFilter();
        }
        return fechaFirma;
    }

    public void setFechaFirma(LocalDateFilter fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public StringFilter getObservaciones() {
        return observaciones;
    }

    public StringFilter observaciones() {
        if (observaciones == null) {
            observaciones = new StringFilter();
        }
        return observaciones;
    }

    public void setObservaciones(StringFilter observaciones) {
        this.observaciones = observaciones;
    }

    public StringFilter getComentario() {
        return comentario;
    }

    public StringFilter comentario() {
        if (comentario == null) {
            comentario = new StringFilter();
        }
        return comentario;
    }

    public void setComentario(StringFilter comentario) {
        this.comentario = comentario;
    }

    public StringFilter getComenCan() {
        return comenCan;
    }

    public StringFilter comenCan() {
        if (comenCan == null) {
            comenCan = new StringFilter();
        }
        return comenCan;
    }

    public void setComenCan(StringFilter comenCan) {
        this.comenCan = comenCan;
    }

    public EstadoFirmaFilter getEstadoFirma() {
        return estadoFirma;
    }

    public EstadoFirmaFilter estadoFirma() {
        if (estadoFirma == null) {
            estadoFirma = new EstadoFirmaFilter();
        }
        return estadoFirma;
    }

    public void setEstadoFirma(EstadoFirmaFilter estadoFirma) {
        this.estadoFirma = estadoFirma;
    }

    public EstadoFilter getEstado() {
        return estado;
    }

    public EstadoFilter estado() {
        if (estado == null) {
            estado = new EstadoFilter();
        }
        return estado;
    }

    public void setEstado(EstadoFilter estado) {
        this.estado = estado;
    }

    public EstadoBCFilter getEstadoBC() {
        return estadoBC;
    }

    public EstadoBCFilter estadoBC() {
        if (estadoBC == null) {
            estadoBC = new EstadoBCFilter();
        }
        return estadoBC;
    }

    public void setEstadoBC(EstadoBCFilter estadoBC) {
        this.estadoBC = estadoBC;
    }

    public LongFilter getGrupoEmpId() {
        return grupoEmpId;
    }

    public LongFilter grupoEmpId() {
        if (grupoEmpId == null) {
            grupoEmpId = new LongFilter();
        }
        return grupoEmpId;
    }

    public void setGrupoEmpId(LongFilter grupoEmpId) {
        this.grupoEmpId = grupoEmpId;
    }

    public LongFilter getTipoObraId() {
        return tipoObraId;
    }

    public LongFilter tipoObraId() {
        if (tipoObraId == null) {
            tipoObraId = new LongFilter();
        }
        return tipoObraId;
    }

    public void setTipoObraId(LongFilter tipoObraId) {
        this.tipoObraId = tipoObraId;
    }

    public LongFilter getTipoEmpId() {
        return tipoEmpId;
    }

    public LongFilter tipoEmpId() {
        if (tipoEmpId == null) {
            tipoEmpId = new LongFilter();
        }
        return tipoEmpId;
    }

    public void setTipoEmpId(LongFilter tipoEmpId) {
        this.tipoEmpId = tipoEmpId;
    }

    public LongFilter getDespliegueId() {
        return despliegueId;
    }

    public LongFilter despliegueId() {
        if (despliegueId == null) {
            despliegueId = new LongFilter();
        }
        return despliegueId;
    }

    public void setDespliegueId(LongFilter despliegueId) {
        this.despliegueId = despliegueId;
    }

    public LongFilter getNSEId() {
        return nSEId;
    }

    public LongFilter nSEId() {
        if (nSEId == null) {
            nSEId = new LongFilter();
        }
        return nSEId;
    }

    public void setNSEId(LongFilter nSEId) {
        this.nSEId = nSEId;
    }

    public LongFilter getSegmentoId() {
        return segmentoId;
    }

    public LongFilter segmentoId() {
        if (segmentoId == null) {
            segmentoId = new LongFilter();
        }
        return segmentoId;
    }

    public void setSegmentoId(LongFilter segmentoId) {
        this.segmentoId = segmentoId;
    }

    public LongFilter getTecnologiaId() {
        return tecnologiaId;
    }

    public LongFilter tecnologiaId() {
        if (tecnologiaId == null) {
            tecnologiaId = new LongFilter();
        }
        return tecnologiaId;
    }

    public void setTecnologiaId(LongFilter tecnologiaId) {
        this.tecnologiaId = tecnologiaId;
    }

    public LongFilter getEjecCuentasId() {
        return ejecCuentasId;
    }

    public LongFilter ejecCuentasId() {
        if (ejecCuentasId == null) {
            ejecCuentasId = new LongFilter();
        }
        return ejecCuentasId;
    }

    public void setEjecCuentasId(LongFilter ejecCuentasId) {
        this.ejecCuentasId = ejecCuentasId;
    }

    public LongFilter getDireccionId() {
        return direccionId;
    }

    public LongFilter direccionId() {
        if (direccionId == null) {
            direccionId = new LongFilter();
        }
        return direccionId;
    }

    public void setDireccionId(LongFilter direccionId) {
        this.direccionId = direccionId;
    }

    public LongFilter getCompentenciaId() {
        return compentenciaId;
    }

    public LongFilter compentenciaId() {
        if (compentenciaId == null) {
            compentenciaId = new LongFilter();
        }
        return compentenciaId;
    }

    public void setCompentenciaId(LongFilter compentenciaId) {
        this.compentenciaId = compentenciaId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmprendimientoCriteria that = (EmprendimientoCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(contacto, that.contacto) &&
            Objects.equals(fechaFinObra, that.fechaFinObra) &&
            Objects.equals(codigoObra, that.codigoObra) &&
            Objects.equals(elementosDeRed, that.elementosDeRed) &&
            Objects.equals(clientesCatv, that.clientesCatv) &&
            Objects.equals(clientesFibertel, that.clientesFibertel) &&
            Objects.equals(clientesFibertelLite, that.clientesFibertelLite) &&
            Objects.equals(clientesFlow, that.clientesFlow) &&
            Objects.equals(clientesCombo, that.clientesCombo) &&
            Objects.equals(lineasVoz, that.lineasVoz) &&
            Objects.equals(mesesDeFinalizado, that.mesesDeFinalizado) &&
            Objects.equals(altasBC, that.altasBC) &&
            Objects.equals(penetracionVivLot, that.penetracionVivLot) &&
            Objects.equals(penetracionBC, that.penetracionBC) &&
            Objects.equals(demanda1, that.demanda1) &&
            Objects.equals(demanda2, that.demanda2) &&
            Objects.equals(demanda3, that.demanda3) &&
            Objects.equals(demanda4, that.demanda4) &&
            Objects.equals(demanda5, that.demanda5) &&
            Objects.equals(lotes, that.lotes) &&
            Objects.equals(viviendas, that.viviendas) &&
            Objects.equals(comProf, that.comProf) &&
            Objects.equals(habitaciones, that.habitaciones) &&
            Objects.equals(manzanas, that.manzanas) &&
            Objects.equals(demanda, that.demanda) &&
            Objects.equals(fechaDeRelevamiento, that.fechaDeRelevamiento) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(anoPriorizacion, that.anoPriorizacion) &&
            Objects.equals(contratoOpen, that.contratoOpen) &&
            Objects.equals(negociacion, that.negociacion) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(codigoDeFirma, that.codigoDeFirma) &&
            Objects.equals(fechaFirma, that.fechaFirma) &&
            Objects.equals(observaciones, that.observaciones) &&
            Objects.equals(comentario, that.comentario) &&
            Objects.equals(comenCan, that.comenCan) &&
            Objects.equals(estadoFirma, that.estadoFirma) &&
            Objects.equals(estado, that.estado) &&
            Objects.equals(estadoBC, that.estadoBC) &&
            Objects.equals(grupoEmpId, that.grupoEmpId) &&
            Objects.equals(tipoObraId, that.tipoObraId) &&
            Objects.equals(tipoEmpId, that.tipoEmpId) &&
            Objects.equals(despliegueId, that.despliegueId) &&
            Objects.equals(nSEId, that.nSEId) &&
            Objects.equals(segmentoId, that.segmentoId) &&
            Objects.equals(tecnologiaId, that.tecnologiaId) &&
            Objects.equals(ejecCuentasId, that.ejecCuentasId) &&
            Objects.equals(direccionId, that.direccionId) &&
            Objects.equals(compentenciaId, that.compentenciaId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            nombre,
            contacto,
            fechaFinObra,
            codigoObra,
            elementosDeRed,
            clientesCatv,
            clientesFibertel,
            clientesFibertelLite,
            clientesFlow,
            clientesCombo,
            lineasVoz,
            mesesDeFinalizado,
            altasBC,
            penetracionVivLot,
            penetracionBC,
            demanda1,
            demanda2,
            demanda3,
            demanda4,
            demanda5,
            lotes,
            viviendas,
            comProf,
            habitaciones,
            manzanas,
            demanda,
            fechaDeRelevamiento,
            telefono,
            anoPriorizacion,
            contratoOpen,
            negociacion,
            fecha,
            codigoDeFirma,
            fechaFirma,
            observaciones,
            comentario,
            comenCan,
            estadoFirma,
            estado,
            estadoBC,
            grupoEmpId,
            tipoObraId,
            tipoEmpId,
            despliegueId,
            nSEId,
            segmentoId,
            tecnologiaId,
            ejecCuentasId,
            direccionId,
            compentenciaId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmprendimientoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nombre != null ? "nombre=" + nombre + ", " : "") +
            (contacto != null ? "contacto=" + contacto + ", " : "") +
            (fechaFinObra != null ? "fechaFinObra=" + fechaFinObra + ", " : "") +
            (codigoObra != null ? "codigoObra=" + codigoObra + ", " : "") +
            (elementosDeRed != null ? "elementosDeRed=" + elementosDeRed + ", " : "") +
            (clientesCatv != null ? "clientesCatv=" + clientesCatv + ", " : "") +
            (clientesFibertel != null ? "clientesFibertel=" + clientesFibertel + ", " : "") +
            (clientesFibertelLite != null ? "clientesFibertelLite=" + clientesFibertelLite + ", " : "") +
            (clientesFlow != null ? "clientesFlow=" + clientesFlow + ", " : "") +
            (clientesCombo != null ? "clientesCombo=" + clientesCombo + ", " : "") +
            (lineasVoz != null ? "lineasVoz=" + lineasVoz + ", " : "") +
            (mesesDeFinalizado != null ? "mesesDeFinalizado=" + mesesDeFinalizado + ", " : "") +
            (altasBC != null ? "altasBC=" + altasBC + ", " : "") +
            (penetracionVivLot != null ? "penetracionVivLot=" + penetracionVivLot + ", " : "") +
            (penetracionBC != null ? "penetracionBC=" + penetracionBC + ", " : "") +
            (demanda1 != null ? "demanda1=" + demanda1 + ", " : "") +
            (demanda2 != null ? "demanda2=" + demanda2 + ", " : "") +
            (demanda3 != null ? "demanda3=" + demanda3 + ", " : "") +
            (demanda4 != null ? "demanda4=" + demanda4 + ", " : "") +
            (demanda5 != null ? "demanda5=" + demanda5 + ", " : "") +
            (lotes != null ? "lotes=" + lotes + ", " : "") +
            (viviendas != null ? "viviendas=" + viviendas + ", " : "") +
            (comProf != null ? "comProf=" + comProf + ", " : "") +
            (habitaciones != null ? "habitaciones=" + habitaciones + ", " : "") +
            (manzanas != null ? "manzanas=" + manzanas + ", " : "") +
            (demanda != null ? "demanda=" + demanda + ", " : "") +
            (fechaDeRelevamiento != null ? "fechaDeRelevamiento=" + fechaDeRelevamiento + ", " : "") +
            (telefono != null ? "telefono=" + telefono + ", " : "") +
            (anoPriorizacion != null ? "anoPriorizacion=" + anoPriorizacion + ", " : "") +
            (contratoOpen != null ? "contratoOpen=" + contratoOpen + ", " : "") +
            (negociacion != null ? "negociacion=" + negociacion + ", " : "") +
            (fecha != null ? "fecha=" + fecha + ", " : "") +
            (codigoDeFirma != null ? "codigoDeFirma=" + codigoDeFirma + ", " : "") +
            (fechaFirma != null ? "fechaFirma=" + fechaFirma + ", " : "") +
            (observaciones != null ? "observaciones=" + observaciones + ", " : "") +
            (comentario != null ? "comentario=" + comentario + ", " : "") +
            (comenCan != null ? "comenCan=" + comenCan + ", " : "") +
            (estadoFirma != null ? "estadoFirma=" + estadoFirma + ", " : "") +
            (estado != null ? "estado=" + estado + ", " : "") +
            (estadoBC != null ? "estadoBC=" + estadoBC + ", " : "") +
            (grupoEmpId != null ? "grupoEmpId=" + grupoEmpId + ", " : "") +
            (tipoObraId != null ? "tipoObraId=" + tipoObraId + ", " : "") +
            (tipoEmpId != null ? "tipoEmpId=" + tipoEmpId + ", " : "") +
            (despliegueId != null ? "despliegueId=" + despliegueId + ", " : "") +
            (nSEId != null ? "nSEId=" + nSEId + ", " : "") +
            (segmentoId != null ? "segmentoId=" + segmentoId + ", " : "") +
            (tecnologiaId != null ? "tecnologiaId=" + tecnologiaId + ", " : "") +
            (ejecCuentasId != null ? "ejecCuentasId=" + ejecCuentasId + ", " : "") +
            (direccionId != null ? "direccionId=" + direccionId + ", " : "") +
            (compentenciaId != null ? "compentenciaId=" + compentenciaId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
