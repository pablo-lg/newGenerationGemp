package ar.teco.service.criteria;

import ar.teco.domain.enumeration.Estado;
import ar.teco.domain.enumeration.Estado;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link ar.teco.domain.HistoWF} entity. This class is used
 * in {@link ar.teco.web.rest.HistoWFResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /histo-wfs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HistoWFCriteria implements Serializable, Criteria {

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

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private EstadoFilter estadoAnterior;

    private EstadoFilter estadoActual;

    private LongFilter emprendimientoId;

    private Boolean distinct;

    public HistoWFCriteria() {}

    public HistoWFCriteria(HistoWFCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.estadoAnterior = other.estadoAnterior == null ? null : other.estadoAnterior.copy();
        this.estadoActual = other.estadoActual == null ? null : other.estadoActual.copy();
        this.emprendimientoId = other.emprendimientoId == null ? null : other.emprendimientoId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public HistoWFCriteria copy() {
        return new HistoWFCriteria(this);
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

    public EstadoFilter getEstadoAnterior() {
        return estadoAnterior;
    }

    public EstadoFilter estadoAnterior() {
        if (estadoAnterior == null) {
            estadoAnterior = new EstadoFilter();
        }
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoFilter estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoFilter getEstadoActual() {
        return estadoActual;
    }

    public EstadoFilter estadoActual() {
        if (estadoActual == null) {
            estadoActual = new EstadoFilter();
        }
        return estadoActual;
    }

    public void setEstadoActual(EstadoFilter estadoActual) {
        this.estadoActual = estadoActual;
    }

    public LongFilter getEmprendimientoId() {
        return emprendimientoId;
    }

    public LongFilter emprendimientoId() {
        if (emprendimientoId == null) {
            emprendimientoId = new LongFilter();
        }
        return emprendimientoId;
    }

    public void setEmprendimientoId(LongFilter emprendimientoId) {
        this.emprendimientoId = emprendimientoId;
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
        final HistoWFCriteria that = (HistoWFCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(estadoAnterior, that.estadoAnterior) &&
            Objects.equals(estadoActual, that.estadoActual) &&
            Objects.equals(emprendimientoId, that.emprendimientoId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estadoAnterior, estadoActual, emprendimientoId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoWFCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (estadoAnterior != null ? "estadoAnterior=" + estadoAnterior + ", " : "") +
            (estadoActual != null ? "estadoActual=" + estadoActual + ", " : "") +
            (emprendimientoId != null ? "emprendimientoId=" + emprendimientoId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
