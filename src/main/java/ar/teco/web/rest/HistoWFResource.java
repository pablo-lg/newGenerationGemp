package ar.teco.web.rest;

import ar.teco.domain.HistoWF;
import ar.teco.repository.HistoWFRepository;
import ar.teco.service.HistoWFQueryService;
import ar.teco.service.HistoWFService;
import ar.teco.service.criteria.HistoWFCriteria;
import ar.teco.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.teco.domain.HistoWF}.
 */
@RestController
@RequestMapping("/api")
public class HistoWFResource {

    private final Logger log = LoggerFactory.getLogger(HistoWFResource.class);

    private static final String ENTITY_NAME = "histoWF";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoWFService histoWFService;

    private final HistoWFRepository histoWFRepository;

    private final HistoWFQueryService histoWFQueryService;

    public HistoWFResource(HistoWFService histoWFService, HistoWFRepository histoWFRepository, HistoWFQueryService histoWFQueryService) {
        this.histoWFService = histoWFService;
        this.histoWFRepository = histoWFRepository;
        this.histoWFQueryService = histoWFQueryService;
    }

    /**
     * {@code POST  /histo-wfs} : Create a new histoWF.
     *
     * @param histoWF the histoWF to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new histoWF, or with status {@code 400 (Bad Request)} if the histoWF has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/histo-wfs")
    public ResponseEntity<HistoWF> createHistoWF(@RequestBody HistoWF histoWF) throws URISyntaxException {
        log.debug("REST request to save HistoWF : {}", histoWF);
        if (histoWF.getId() != null) {
            throw new BadRequestAlertException("A new histoWF cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoWF result = histoWFService.save(histoWF);
        return ResponseEntity
            .created(new URI("/api/histo-wfs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /histo-wfs/:id} : Updates an existing histoWF.
     *
     * @param id the id of the histoWF to save.
     * @param histoWF the histoWF to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated histoWF,
     * or with status {@code 400 (Bad Request)} if the histoWF is not valid,
     * or with status {@code 500 (Internal Server Error)} if the histoWF couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/histo-wfs/{id}")
    public ResponseEntity<HistoWF> updateHistoWF(@PathVariable(value = "id", required = false) final Long id, @RequestBody HistoWF histoWF)
        throws URISyntaxException {
        log.debug("REST request to update HistoWF : {}, {}", id, histoWF);
        if (histoWF.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, histoWF.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!histoWFRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HistoWF result = histoWFService.save(histoWF);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, histoWF.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /histo-wfs/:id} : Partial updates given fields of an existing histoWF, field will ignore if it is null
     *
     * @param id the id of the histoWF to save.
     * @param histoWF the histoWF to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated histoWF,
     * or with status {@code 400 (Bad Request)} if the histoWF is not valid,
     * or with status {@code 404 (Not Found)} if the histoWF is not found,
     * or with status {@code 500 (Internal Server Error)} if the histoWF couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/histo-wfs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HistoWF> partialUpdateHistoWF(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HistoWF histoWF
    ) throws URISyntaxException {
        log.debug("REST request to partial update HistoWF partially : {}, {}", id, histoWF);
        if (histoWF.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, histoWF.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!histoWFRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HistoWF> result = histoWFService.partialUpdate(histoWF);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, histoWF.getId().toString())
        );
    }

    /**
     * {@code GET  /histo-wfs} : get all the histoWFS.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of histoWFS in body.
     */
    @GetMapping("/histo-wfs")
    public ResponseEntity<List<HistoWF>> getAllHistoWFS(
        HistoWFCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get HistoWFS by criteria: {}", criteria);
        Page<HistoWF> page = histoWFQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /histo-wfs/count} : count all the histoWFS.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/histo-wfs/count")
    public ResponseEntity<Long> countHistoWFS(HistoWFCriteria criteria) {
        log.debug("REST request to count HistoWFS by criteria: {}", criteria);
        return ResponseEntity.ok().body(histoWFQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /histo-wfs/:id} : get the "id" histoWF.
     *
     * @param id the id of the histoWF to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the histoWF, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/histo-wfs/{id}")
    public ResponseEntity<HistoWF> getHistoWF(@PathVariable Long id) {
        log.debug("REST request to get HistoWF : {}", id);
        Optional<HistoWF> histoWF = histoWFService.findOne(id);
        return ResponseUtil.wrapOrNotFound(histoWF);
    }

    /**
     * {@code DELETE  /histo-wfs/:id} : delete the "id" histoWF.
     *
     * @param id the id of the histoWF to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/histo-wfs/{id}")
    public ResponseEntity<Void> deleteHistoWF(@PathVariable Long id) {
        log.debug("REST request to delete HistoWF : {}", id);
        histoWFService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
