package ar.teco.web.rest;

import ar.teco.domain.Demanda;
import ar.teco.repository.DemandaRepository;
import ar.teco.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.teco.domain.Demanda}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DemandaResource {

    private final Logger log = LoggerFactory.getLogger(DemandaResource.class);

    private static final String ENTITY_NAME = "demanda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandaRepository demandaRepository;

    public DemandaResource(DemandaRepository demandaRepository) {
        this.demandaRepository = demandaRepository;
    }

    /**
     * {@code POST  /demandas} : Create a new demanda.
     *
     * @param demanda the demanda to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demanda, or with status {@code 400 (Bad Request)} if the demanda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demandas")
    public ResponseEntity<Demanda> createDemanda(@RequestBody Demanda demanda) throws URISyntaxException {
        log.debug("REST request to save Demanda : {}", demanda);
        if (demanda.getId() != null) {
            throw new BadRequestAlertException("A new demanda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Demanda result = demandaRepository.save(demanda);
        return ResponseEntity
            .created(new URI("/api/demandas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demandas/:id} : Updates an existing demanda.
     *
     * @param id the id of the demanda to save.
     * @param demanda the demanda to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demanda,
     * or with status {@code 400 (Bad Request)} if the demanda is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demanda couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demandas/{id}")
    public ResponseEntity<Demanda> updateDemanda(@PathVariable(value = "id", required = false) final Long id, @RequestBody Demanda demanda)
        throws URISyntaxException {
        log.debug("REST request to update Demanda : {}, {}", id, demanda);
        if (demanda.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, demanda.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!demandaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Demanda result = demandaRepository.save(demanda);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, demanda.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /demandas/:id} : Partial updates given fields of an existing demanda, field will ignore if it is null
     *
     * @param id the id of the demanda to save.
     * @param demanda the demanda to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demanda,
     * or with status {@code 400 (Bad Request)} if the demanda is not valid,
     * or with status {@code 404 (Not Found)} if the demanda is not found,
     * or with status {@code 500 (Internal Server Error)} if the demanda couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/demandas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Demanda> partialUpdateDemanda(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Demanda demanda
    ) throws URISyntaxException {
        log.debug("REST request to partial update Demanda partially : {}, {}", id, demanda);
        if (demanda.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, demanda.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!demandaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Demanda> result = demandaRepository
            .findById(demanda.getId())
            .map(existingDemanda -> {
                if (demanda.geta1() != null) {
                    existingDemanda.seta1(demanda.geta1());
                }
                if (demanda.geta2() != null) {
                    existingDemanda.seta2(demanda.geta2());
                }
                if (demanda.geta3() != null) {
                    existingDemanda.seta3(demanda.geta3());
                }
                if (demanda.geta4() != null) {
                    existingDemanda.seta4(demanda.geta4());
                }
                if (demanda.geta5() != null) {
                    existingDemanda.seta5(demanda.geta5());
                }

                return existingDemanda;
            })
            .map(demandaRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, demanda.getId().toString())
        );
    }

    /**
     * {@code GET  /demandas} : get all the demandas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandas in body.
     */
    @GetMapping("/demandas")
    public List<Demanda> getAllDemandas() {
        log.debug("REST request to get all Demandas");
        return demandaRepository.findAll();
    }

    /**
     * {@code GET  /demandas/:id} : get the "id" demanda.
     *
     * @param id the id of the demanda to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demanda, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demandas/{id}")
    public ResponseEntity<Demanda> getDemanda(@PathVariable Long id) {
        log.debug("REST request to get Demanda : {}", id);
        Optional<Demanda> demanda = demandaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(demanda);
    }

    /**
     * {@code DELETE  /demandas/:id} : delete the "id" demanda.
     *
     * @param id the id of the demanda to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demandas/{id}")
    public ResponseEntity<Void> deleteDemanda(@PathVariable Long id) {
        log.debug("REST request to delete Demanda : {}", id);
        demandaRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
