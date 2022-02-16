package ar.teco.service;

import ar.teco.domain.*; // for static metamodels
import ar.teco.domain.HistoWF;
import ar.teco.repository.HistoWFRepository;
import ar.teco.service.criteria.HistoWFCriteria;
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
 * Service for executing complex queries for {@link HistoWF} entities in the database.
 * The main input is a {@link HistoWFCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HistoWF} or a {@link Page} of {@link HistoWF} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HistoWFQueryService extends QueryService<HistoWF> {

    private final Logger log = LoggerFactory.getLogger(HistoWFQueryService.class);

    private final HistoWFRepository histoWFRepository;

    public HistoWFQueryService(HistoWFRepository histoWFRepository) {
        this.histoWFRepository = histoWFRepository;
    }

    /**
     * Return a {@link List} of {@link HistoWF} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HistoWF> findByCriteria(HistoWFCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<HistoWF> specification = createSpecification(criteria);
        return histoWFRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link HistoWF} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HistoWF> findByCriteria(HistoWFCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<HistoWF> specification = createSpecification(criteria);
        return histoWFRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HistoWFCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<HistoWF> specification = createSpecification(criteria);
        return histoWFRepository.count(specification);
    }

    /**
     * Function to convert {@link HistoWFCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<HistoWF> createSpecification(HistoWFCriteria criteria) {
        Specification<HistoWF> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), HistoWF_.id));
            }
            if (criteria.getEstadoAnterior() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoAnterior(), HistoWF_.estadoAnterior));
            }
            if (criteria.getEstadoActual() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoActual(), HistoWF_.estadoActual));
            }
            if (criteria.getEmprendimientoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmprendimientoId(),
                            root -> root.join(HistoWF_.emprendimiento, JoinType.LEFT).get(Emprendimiento_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
