package ar.teco.web.rest;

import ar.teco.domain.FiltroRep;
import ar.teco.repository.FiltroRepRepository;
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
 * REST controller for managing {@link ar.teco.domain.FiltroRep}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FiltroRepResource {

    private final Logger log = LoggerFactory.getLogger(FiltroRepResource.class);

    private static final String ENTITY_NAME = "filtroRep";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FiltroRepRepository filtroRepRepository;

    public FiltroRepResource(FiltroRepRepository filtroRepRepository) {
        this.filtroRepRepository = filtroRepRepository;
    }

    /**
     * {@code POST  /filtro-reps} : Create a new filtroRep.
     *
     * @param filtroRep the filtroRep to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new filtroRep, or with status {@code 400 (Bad Request)} if the filtroRep has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/filtro-reps")
    public ResponseEntity<FiltroRep> createFiltroRep(@RequestBody FiltroRep filtroRep) throws URISyntaxException {
        log.debug("REST request to save FiltroRep : {}", filtroRep);
        if (filtroRep.getId() != null) {
            throw new BadRequestAlertException("A new filtroRep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FiltroRep result = filtroRepRepository.save(filtroRep);
        return ResponseEntity
            .created(new URI("/api/filtro-reps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /filtro-reps/:id} : Updates an existing filtroRep.
     *
     * @param id the id of the filtroRep to save.
     * @param filtroRep the filtroRep to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated filtroRep,
     * or with status {@code 400 (Bad Request)} if the filtroRep is not valid,
     * or with status {@code 500 (Internal Server Error)} if the filtroRep couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/filtro-reps/{id}")
    public ResponseEntity<FiltroRep> updateFiltroRep(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FiltroRep filtroRep
    ) throws URISyntaxException {
        log.debug("REST request to update FiltroRep : {}, {}", id, filtroRep);
        if (filtroRep.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, filtroRep.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!filtroRepRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FiltroRep result = filtroRepRepository.save(filtroRep);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, filtroRep.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /filtro-reps/:id} : Partial updates given fields of an existing filtroRep, field will ignore if it is null
     *
     * @param id the id of the filtroRep to save.
     * @param filtroRep the filtroRep to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated filtroRep,
     * or with status {@code 400 (Bad Request)} if the filtroRep is not valid,
     * or with status {@code 404 (Not Found)} if the filtroRep is not found,
     * or with status {@code 500 (Internal Server Error)} if the filtroRep couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/filtro-reps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FiltroRep> partialUpdateFiltroRep(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FiltroRep filtroRep
    ) throws URISyntaxException {
        log.debug("REST request to partial update FiltroRep partially : {}, {}", id, filtroRep);
        if (filtroRep.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, filtroRep.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!filtroRepRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FiltroRep> result = filtroRepRepository
            .findById(filtroRep.getId())
            .map(existingFiltroRep -> {
                if (filtroRep.getNombre() != null) {
                    existingFiltroRep.setNombre(filtroRep.getNombre());
                }
                if (filtroRep.getFiltro() != null) {
                    existingFiltroRep.setFiltro(filtroRep.getFiltro());
                }

                return existingFiltroRep;
            })
            .map(filtroRepRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, filtroRep.getId().toString())
        );
    }

    /**
     * {@code GET  /filtro-reps} : get all the filtroReps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of filtroReps in body.
     */
    @GetMapping("/filtro-reps")
    public List<FiltroRep> getAllFiltroReps() {
        log.debug("REST request to get all FiltroReps");
        return filtroRepRepository.findAll();
    }

    /**
     * {@code GET  /filtro-reps/:id} : get the "id" filtroRep.
     *
     * @param id the id of the filtroRep to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the filtroRep, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/filtro-reps/{id}")
    public ResponseEntity<FiltroRep> getFiltroRep(@PathVariable Long id) {
        log.debug("REST request to get FiltroRep : {}", id);
        Optional<FiltroRep> filtroRep = filtroRepRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(filtroRep);
    }

    /**
     * {@code DELETE  /filtro-reps/:id} : delete the "id" filtroRep.
     *
     * @param id the id of the filtroRep to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/filtro-reps/{id}")
    public ResponseEntity<Void> deleteFiltroRep(@PathVariable Long id) {
        log.debug("REST request to delete FiltroRep : {}", id);
        filtroRepRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
