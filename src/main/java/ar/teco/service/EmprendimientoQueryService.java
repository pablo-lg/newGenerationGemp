package ar.teco.service;

import ar.teco.domain.*; // for static metamodels
import ar.teco.domain.Emprendimiento;
import ar.teco.repository.EmprendimientoRepository;
import ar.teco.service.criteria.EmprendimientoCriteria;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Emprendimiento} entities in the database.
 * The main input is a {@link EmprendimientoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Emprendimiento} or a {@link Page} of {@link Emprendimiento} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmprendimientoQueryService extends QueryService<Emprendimiento> {

    private final Logger log = LoggerFactory.getLogger(EmprendimientoQueryService.class);

    private final EmprendimientoRepository emprendimientoRepository;

    public EmprendimientoQueryService(EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
    }

    /**
     * Return a {@link List} of {@link Emprendimiento} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Emprendimiento> findByCriteria(EmprendimientoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Emprendimiento> specification = createSpecification(criteria);
        return emprendimientoRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Emprendimiento} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Emprendimiento> findByCriteria(EmprendimientoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Emprendimiento> specification = createSpecification(criteria);
        return emprendimientoRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmprendimientoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Emprendimiento> specification = createSpecification(criteria);
        return emprendimientoRepository.count(specification);
    }

    /**
     * Function to convert {@link EmprendimientoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Emprendimiento> createSpecification(EmprendimientoCriteria criteria) {
        Specification<Emprendimiento> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Emprendimiento_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Emprendimiento_.nombre));
            }
            if (criteria.getContacto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContacto(), Emprendimiento_.contacto));
            }
            if (criteria.getFechaFinObra() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaFinObra(), Emprendimiento_.fechaFinObra));
            }
            if (criteria.getCodigoObra() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodigoObra(), Emprendimiento_.codigoObra));
            }
            if (criteria.getElementosDeRed() != null) {
                specification = specification.and(buildStringSpecification(criteria.getElementosDeRed(), Emprendimiento_.elementosDeRed));
            }
            if (criteria.getClientesCatv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientesCatv(), Emprendimiento_.clientesCatv));
            }
            if (criteria.getClientesFibertel() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getClientesFibertel(), Emprendimiento_.clientesFibertel));
            }
            if (criteria.getClientesFibertelLite() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getClientesFibertelLite(), Emprendimiento_.clientesFibertelLite));
            }
            if (criteria.getClientesFlow() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientesFlow(), Emprendimiento_.clientesFlow));
            }
            if (criteria.getClientesCombo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientesCombo(), Emprendimiento_.clientesCombo));
            }
            if (criteria.getLineasVoz() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLineasVoz(), Emprendimiento_.lineasVoz));
            }
            if (criteria.getMesesDeFinalizado() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getMesesDeFinalizado(), Emprendimiento_.mesesDeFinalizado));
            }
            if (criteria.getAltasBC() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAltasBC(), Emprendimiento_.altasBC));
            }
            if (criteria.getPenetracionVivLot() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getPenetracionVivLot(), Emprendimiento_.penetracionVivLot));
            }
            if (criteria.getPenetracionBC() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPenetracionBC(), Emprendimiento_.penetracionBC));
            }
            if (criteria.getDemanda1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDemanda1(), Emprendimiento_.demanda1));
            }
            if (criteria.getDemanda2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDemanda2(), Emprendimiento_.demanda2));
            }
            if (criteria.getDemanda3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDemanda3(), Emprendimiento_.demanda3));
            }
            if (criteria.getDemanda4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDemanda4(), Emprendimiento_.demanda4));
            }
            if (criteria.getDemanda5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDemanda5(), Emprendimiento_.demanda5));
            }
            if (criteria.getLotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLotes(), Emprendimiento_.lotes));
            }
            if (criteria.getViviendas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViviendas(), Emprendimiento_.viviendas));
            }
            if (criteria.getComProf() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComProf(), Emprendimiento_.comProf));
            }
            if (criteria.getHabitaciones() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHabitaciones(), Emprendimiento_.habitaciones));
            }
            if (criteria.getManzanas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getManzanas(), Emprendimiento_.manzanas));
            }
            if (criteria.getDemanda() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDemanda(), Emprendimiento_.demanda));
            }
            if (criteria.getFechaDeRelevamiento() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getFechaDeRelevamiento(), Emprendimiento_.fechaDeRelevamiento));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefono(), Emprendimiento_.telefono));
            }
            if (criteria.getAnoPriorizacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnoPriorizacion(), Emprendimiento_.anoPriorizacion));
            }
            if (criteria.getContratoOpen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContratoOpen(), Emprendimiento_.contratoOpen));
            }
            if (criteria.getNegociacion() != null) {
                specification = specification.and(buildSpecification(criteria.getNegociacion(), Emprendimiento_.negociacion));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), Emprendimiento_.fecha));
            }
            if (criteria.getCodigoDeFirma() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodigoDeFirma(), Emprendimiento_.codigoDeFirma));
            }
            if (criteria.getFechaFirma() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaFirma(), Emprendimiento_.fechaFirma));
            }
            if (criteria.getObservaciones() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservaciones(), Emprendimiento_.observaciones));
            }
            if (criteria.getComentario() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComentario(), Emprendimiento_.comentario));
            }
            if (criteria.getComenCan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComenCan(), Emprendimiento_.comenCan));
            }
            if (criteria.getEstadoFirma() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoFirma(), Emprendimiento_.estadoFirma));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildSpecification(criteria.getEstado(), Emprendimiento_.estado));
            }
            if (criteria.getEstadoBC() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoBC(), Emprendimiento_.estadoBC));
            }
            if (criteria.getGrupoEmpId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getGrupoEmpId(),
                            root -> root.join(Emprendimiento_.grupoEmp, JoinType.LEFT).get(GrupoEmp_.id)
                        )
                    );
            }
            if (criteria.getTipoObraId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTipoObraId(),
                            root -> root.join(Emprendimiento_.tipoObra, JoinType.LEFT).get(TipoObra_.id)
                        )
                    );
            }
            if (criteria.getTipoEmpId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTipoEmpId(),
                            root -> root.join(Emprendimiento_.tipoEmp, JoinType.LEFT).get(TipoEmp_.id)
                        )
                    );
            }
            if (criteria.getDespliegueId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDespliegueId(),
                            root -> root.join(Emprendimiento_.despliegue, JoinType.LEFT).get(Despliegue_.id)
                        )
                    );
            }
            if (criteria.getNSEId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getNSEId(), root -> root.join(Emprendimiento_.nSE, JoinType.LEFT).get(NSE_.id))
                    );
            }
            if (criteria.getSegmentoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getSegmentoId(),
                            root -> root.join(Emprendimiento_.segmento, JoinType.LEFT).get(Segmento_.id)
                        )
                    );
            }
            if (criteria.getTecnologiaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTecnologiaId(),
                            root -> root.join(Emprendimiento_.tecnologia, JoinType.LEFT).get(Tecnologia_.id)
                        )
                    );
            }
            if (criteria.getEjecCuentasId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEjecCuentasId(),
                            root -> root.join(Emprendimiento_.ejecCuentas, JoinType.LEFT).get(EjecCuentas_.id)
                        )
                    );
            }
            if (criteria.getDireccionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDireccionId(),
                            root -> root.join(Emprendimiento_.direccion, JoinType.LEFT).get(Direccion_.id)
                        )
                    );
            }
            if (criteria.getDireccionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDireccionId(),
                            root -> root.join(Emprendimiento_.direccion, JoinType.LEFT).get(Competencia_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
