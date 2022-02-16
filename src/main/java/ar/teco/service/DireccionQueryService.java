package ar.teco.service;

import ar.teco.domain.*; // for static metamodels
import ar.teco.domain.Direccion;
import ar.teco.repository.DireccionRepository;
import ar.teco.service.criteria.DireccionCriteria;
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
 * Service for executing complex queries for {@link Direccion} entities in the database.
 * The main input is a {@link DireccionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Direccion} or a {@link Page} of {@link Direccion} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DireccionQueryService extends QueryService<Direccion> {

    private final Logger log = LoggerFactory.getLogger(DireccionQueryService.class);

    private final DireccionRepository direccionRepository;

    public DireccionQueryService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    /**
     * Return a {@link List} of {@link Direccion} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Direccion> findByCriteria(DireccionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Direccion> specification = createSpecification(criteria);
        return direccionRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Direccion} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Direccion> findByCriteria(DireccionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Direccion> specification = createSpecification(criteria);
        return direccionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DireccionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Direccion> specification = createSpecification(criteria);
        return direccionRepository.count(specification);
    }

    /**
     * Function to convert {@link DireccionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Direccion> createSpecification(DireccionCriteria criteria) {
        Specification<Direccion> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Direccion_.id));
            }
            if (criteria.getIdentification() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdentification(), Direccion_.identification));
            }
            if (criteria.getPais() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPais(), Direccion_.pais));
            }
            if (criteria.getProvincia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProvincia(), Direccion_.provincia));
            }
            if (criteria.getPartido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPartido(), Direccion_.partido));
            }
            if (criteria.getLocalidad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocalidad(), Direccion_.localidad));
            }
            if (criteria.getCalle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCalle(), Direccion_.calle));
            }
            if (criteria.getAltura() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAltura(), Direccion_.altura));
            }
            if (criteria.getRegion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegion(), Direccion_.region));
            }
            if (criteria.getSubregion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSubregion(), Direccion_.subregion));
            }
            if (criteria.getHub() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHub(), Direccion_.hub));
            }
            if (criteria.getBarriosEspeciales() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBarriosEspeciales(), Direccion_.barriosEspeciales));
            }
            if (criteria.getCodigoPostal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodigoPostal(), Direccion_.codigoPostal));
            }
            if (criteria.getTipoCalle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTipoCalle(), Direccion_.tipoCalle));
            }
            if (criteria.getZonaCompetencia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZonaCompetencia(), Direccion_.zonaCompetencia));
            }
            if (criteria.getIntersectionLeft() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIntersectionLeft(), Direccion_.intersectionLeft));
            }
            if (criteria.getIntersectionRight() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIntersectionRight(), Direccion_.intersectionRight));
            }
            if (criteria.getStreetType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStreetType(), Direccion_.streetType));
            }
            if (criteria.getLatitud() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLatitud(), Direccion_.latitud));
            }
            if (criteria.getLongitud() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLongitud(), Direccion_.longitud));
            }
            if (criteria.getElementosDeRed() != null) {
                specification = specification.and(buildStringSpecification(criteria.getElementosDeRed(), Direccion_.elementosDeRed));
            }
        }
        return specification;
    }
}
