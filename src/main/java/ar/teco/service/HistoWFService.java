package ar.teco.service;

import ar.teco.domain.HistoWF;
import ar.teco.repository.HistoWFRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HistoWF}.
 */
@Service
@Transactional
public class HistoWFService {

    private final Logger log = LoggerFactory.getLogger(HistoWFService.class);

    private final HistoWFRepository histoWFRepository;

    public HistoWFService(HistoWFRepository histoWFRepository) {
        this.histoWFRepository = histoWFRepository;
    }

    /**
     * Save a histoWF.
     *
     * @param histoWF the entity to save.
     * @return the persisted entity.
     */
    public HistoWF save(HistoWF histoWF) {
        log.debug("Request to save HistoWF : {}", histoWF);
        return histoWFRepository.save(histoWF);
    }

    /**
     * Partially update a histoWF.
     *
     * @param histoWF the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HistoWF> partialUpdate(HistoWF histoWF) {
        log.debug("Request to partially update HistoWF : {}", histoWF);

        return histoWFRepository
            .findById(histoWF.getId())
            .map(existingHistoWF -> {
                if (histoWF.getEstadoAnterior() != null) {
                    existingHistoWF.setEstadoAnterior(histoWF.getEstadoAnterior());
                }
                if (histoWF.getEstadoActual() != null) {
                    existingHistoWF.setEstadoActual(histoWF.getEstadoActual());
                }

                return existingHistoWF;
            })
            .map(histoWFRepository::save);
    }

    /**
     * Get all the histoWFS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HistoWF> findAll(Pageable pageable) {
        log.debug("Request to get all HistoWFS");
        return histoWFRepository.findAll(pageable);
    }

    /**
     * Get one histoWF by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HistoWF> findOne(Long id) {
        log.debug("Request to get HistoWF : {}", id);
        return histoWFRepository.findById(id);
    }

    /**
     * Delete the histoWF by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete HistoWF : {}", id);
        histoWFRepository.deleteById(id);
    }
}
