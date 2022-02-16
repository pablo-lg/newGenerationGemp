package ar.teco.service.criteria;

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
 * Criteria class for the {@link ar.teco.domain.Direccion} entity. This class is used
 * in {@link ar.teco.web.rest.DireccionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /direccions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DireccionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter identification;

    private StringFilter pais;

    private StringFilter provincia;

    private StringFilter partido;

    private StringFilter localidad;

    private StringFilter calle;

    private LongFilter altura;

    private StringFilter region;

    private StringFilter subregion;

    private StringFilter hub;

    private StringFilter barriosEspeciales;

    private StringFilter codigoPostal;

    private StringFilter tipoCalle;

    private StringFilter zonaCompetencia;

    private StringFilter intersectionLeft;

    private StringFilter intersectionRight;

    private StringFilter streetType;

    private StringFilter latitud;

    private StringFilter longitud;

    private StringFilter elementosDeRed;

    private Boolean distinct;

    public DireccionCriteria() {}

    public DireccionCriteria(DireccionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.identification = other.identification == null ? null : other.identification.copy();
        this.pais = other.pais == null ? null : other.pais.copy();
        this.provincia = other.provincia == null ? null : other.provincia.copy();
        this.partido = other.partido == null ? null : other.partido.copy();
        this.localidad = other.localidad == null ? null : other.localidad.copy();
        this.calle = other.calle == null ? null : other.calle.copy();
        this.altura = other.altura == null ? null : other.altura.copy();
        this.region = other.region == null ? null : other.region.copy();
        this.subregion = other.subregion == null ? null : other.subregion.copy();
        this.hub = other.hub == null ? null : other.hub.copy();
        this.barriosEspeciales = other.barriosEspeciales == null ? null : other.barriosEspeciales.copy();
        this.codigoPostal = other.codigoPostal == null ? null : other.codigoPostal.copy();
        this.tipoCalle = other.tipoCalle == null ? null : other.tipoCalle.copy();
        this.zonaCompetencia = other.zonaCompetencia == null ? null : other.zonaCompetencia.copy();
        this.intersectionLeft = other.intersectionLeft == null ? null : other.intersectionLeft.copy();
        this.intersectionRight = other.intersectionRight == null ? null : other.intersectionRight.copy();
        this.streetType = other.streetType == null ? null : other.streetType.copy();
        this.latitud = other.latitud == null ? null : other.latitud.copy();
        this.longitud = other.longitud == null ? null : other.longitud.copy();
        this.elementosDeRed = other.elementosDeRed == null ? null : other.elementosDeRed.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DireccionCriteria copy() {
        return new DireccionCriteria(this);
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

    public StringFilter getIdentification() {
        return identification;
    }

    public StringFilter identification() {
        if (identification == null) {
            identification = new StringFilter();
        }
        return identification;
    }

    public void setIdentification(StringFilter identification) {
        this.identification = identification;
    }

    public StringFilter getPais() {
        return pais;
    }

    public StringFilter pais() {
        if (pais == null) {
            pais = new StringFilter();
        }
        return pais;
    }

    public void setPais(StringFilter pais) {
        this.pais = pais;
    }

    public StringFilter getProvincia() {
        return provincia;
    }

    public StringFilter provincia() {
        if (provincia == null) {
            provincia = new StringFilter();
        }
        return provincia;
    }

    public void setProvincia(StringFilter provincia) {
        this.provincia = provincia;
    }

    public StringFilter getPartido() {
        return partido;
    }

    public StringFilter partido() {
        if (partido == null) {
            partido = new StringFilter();
        }
        return partido;
    }

    public void setPartido(StringFilter partido) {
        this.partido = partido;
    }

    public StringFilter getLocalidad() {
        return localidad;
    }

    public StringFilter localidad() {
        if (localidad == null) {
            localidad = new StringFilter();
        }
        return localidad;
    }

    public void setLocalidad(StringFilter localidad) {
        this.localidad = localidad;
    }

    public StringFilter getCalle() {
        return calle;
    }

    public StringFilter calle() {
        if (calle == null) {
            calle = new StringFilter();
        }
        return calle;
    }

    public void setCalle(StringFilter calle) {
        this.calle = calle;
    }

    public LongFilter getAltura() {
        return altura;
    }

    public LongFilter altura() {
        if (altura == null) {
            altura = new LongFilter();
        }
        return altura;
    }

    public void setAltura(LongFilter altura) {
        this.altura = altura;
    }

    public StringFilter getRegion() {
        return region;
    }

    public StringFilter region() {
        if (region == null) {
            region = new StringFilter();
        }
        return region;
    }

    public void setRegion(StringFilter region) {
        this.region = region;
    }

    public StringFilter getSubregion() {
        return subregion;
    }

    public StringFilter subregion() {
        if (subregion == null) {
            subregion = new StringFilter();
        }
        return subregion;
    }

    public void setSubregion(StringFilter subregion) {
        this.subregion = subregion;
    }

    public StringFilter getHub() {
        return hub;
    }

    public StringFilter hub() {
        if (hub == null) {
            hub = new StringFilter();
        }
        return hub;
    }

    public void setHub(StringFilter hub) {
        this.hub = hub;
    }

    public StringFilter getBarriosEspeciales() {
        return barriosEspeciales;
    }

    public StringFilter barriosEspeciales() {
        if (barriosEspeciales == null) {
            barriosEspeciales = new StringFilter();
        }
        return barriosEspeciales;
    }

    public void setBarriosEspeciales(StringFilter barriosEspeciales) {
        this.barriosEspeciales = barriosEspeciales;
    }

    public StringFilter getCodigoPostal() {
        return codigoPostal;
    }

    public StringFilter codigoPostal() {
        if (codigoPostal == null) {
            codigoPostal = new StringFilter();
        }
        return codigoPostal;
    }

    public void setCodigoPostal(StringFilter codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public StringFilter getTipoCalle() {
        return tipoCalle;
    }

    public StringFilter tipoCalle() {
        if (tipoCalle == null) {
            tipoCalle = new StringFilter();
        }
        return tipoCalle;
    }

    public void setTipoCalle(StringFilter tipoCalle) {
        this.tipoCalle = tipoCalle;
    }

    public StringFilter getZonaCompetencia() {
        return zonaCompetencia;
    }

    public StringFilter zonaCompetencia() {
        if (zonaCompetencia == null) {
            zonaCompetencia = new StringFilter();
        }
        return zonaCompetencia;
    }

    public void setZonaCompetencia(StringFilter zonaCompetencia) {
        this.zonaCompetencia = zonaCompetencia;
    }

    public StringFilter getIntersectionLeft() {
        return intersectionLeft;
    }

    public StringFilter intersectionLeft() {
        if (intersectionLeft == null) {
            intersectionLeft = new StringFilter();
        }
        return intersectionLeft;
    }

    public void setIntersectionLeft(StringFilter intersectionLeft) {
        this.intersectionLeft = intersectionLeft;
    }

    public StringFilter getIntersectionRight() {
        return intersectionRight;
    }

    public StringFilter intersectionRight() {
        if (intersectionRight == null) {
            intersectionRight = new StringFilter();
        }
        return intersectionRight;
    }

    public void setIntersectionRight(StringFilter intersectionRight) {
        this.intersectionRight = intersectionRight;
    }

    public StringFilter getStreetType() {
        return streetType;
    }

    public StringFilter streetType() {
        if (streetType == null) {
            streetType = new StringFilter();
        }
        return streetType;
    }

    public void setStreetType(StringFilter streetType) {
        this.streetType = streetType;
    }

    public StringFilter getLatitud() {
        return latitud;
    }

    public StringFilter latitud() {
        if (latitud == null) {
            latitud = new StringFilter();
        }
        return latitud;
    }

    public void setLatitud(StringFilter latitud) {
        this.latitud = latitud;
    }

    public StringFilter getLongitud() {
        return longitud;
    }

    public StringFilter longitud() {
        if (longitud == null) {
            longitud = new StringFilter();
        }
        return longitud;
    }

    public void setLongitud(StringFilter longitud) {
        this.longitud = longitud;
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
        final DireccionCriteria that = (DireccionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(identification, that.identification) &&
            Objects.equals(pais, that.pais) &&
            Objects.equals(provincia, that.provincia) &&
            Objects.equals(partido, that.partido) &&
            Objects.equals(localidad, that.localidad) &&
            Objects.equals(calle, that.calle) &&
            Objects.equals(altura, that.altura) &&
            Objects.equals(region, that.region) &&
            Objects.equals(subregion, that.subregion) &&
            Objects.equals(hub, that.hub) &&
            Objects.equals(barriosEspeciales, that.barriosEspeciales) &&
            Objects.equals(codigoPostal, that.codigoPostal) &&
            Objects.equals(tipoCalle, that.tipoCalle) &&
            Objects.equals(zonaCompetencia, that.zonaCompetencia) &&
            Objects.equals(intersectionLeft, that.intersectionLeft) &&
            Objects.equals(intersectionRight, that.intersectionRight) &&
            Objects.equals(streetType, that.streetType) &&
            Objects.equals(latitud, that.latitud) &&
            Objects.equals(longitud, that.longitud) &&
            Objects.equals(elementosDeRed, that.elementosDeRed) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            identification,
            pais,
            provincia,
            partido,
            localidad,
            calle,
            altura,
            region,
            subregion,
            hub,
            barriosEspeciales,
            codigoPostal,
            tipoCalle,
            zonaCompetencia,
            intersectionLeft,
            intersectionRight,
            streetType,
            latitud,
            longitud,
            elementosDeRed,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DireccionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (identification != null ? "identification=" + identification + ", " : "") +
            (pais != null ? "pais=" + pais + ", " : "") +
            (provincia != null ? "provincia=" + provincia + ", " : "") +
            (partido != null ? "partido=" + partido + ", " : "") +
            (localidad != null ? "localidad=" + localidad + ", " : "") +
            (calle != null ? "calle=" + calle + ", " : "") +
            (altura != null ? "altura=" + altura + ", " : "") +
            (region != null ? "region=" + region + ", " : "") +
            (subregion != null ? "subregion=" + subregion + ", " : "") +
            (hub != null ? "hub=" + hub + ", " : "") +
            (barriosEspeciales != null ? "barriosEspeciales=" + barriosEspeciales + ", " : "") +
            (codigoPostal != null ? "codigoPostal=" + codigoPostal + ", " : "") +
            (tipoCalle != null ? "tipoCalle=" + tipoCalle + ", " : "") +
            (zonaCompetencia != null ? "zonaCompetencia=" + zonaCompetencia + ", " : "") +
            (intersectionLeft != null ? "intersectionLeft=" + intersectionLeft + ", " : "") +
            (intersectionRight != null ? "intersectionRight=" + intersectionRight + ", " : "") +
            (streetType != null ? "streetType=" + streetType + ", " : "") +
            (latitud != null ? "latitud=" + latitud + ", " : "") +
            (longitud != null ? "longitud=" + longitud + ", " : "") +
            (elementosDeRed != null ? "elementosDeRed=" + elementosDeRed + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
