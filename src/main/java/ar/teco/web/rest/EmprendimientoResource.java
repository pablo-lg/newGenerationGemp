package ar.teco.web.rest;

import ar.teco.domain.Emprendimiento;
import ar.teco.repository.EmprendimientoRepository;
import ar.teco.service.EmprendimientoQueryService;
import ar.teco.service.EmprendimientoService;
import ar.teco.service.criteria.EmprendimientoCriteria;
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
 * REST controller for managing {@link ar.teco.domain.Emprendimiento}.
 */
@RestController
@RequestMapping("/api")
public class EmprendimientoResource {

    private final Logger log = LoggerFactory.getLogger(EmprendimientoResource.class);

    private static final String ENTITY_NAME = "emprendimiento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmprendimientoService emprendimientoService;

    private final EmprendimientoRepository emprendimientoRepository;

    private final EmprendimientoQueryService emprendimientoQueryService;

    public EmprendimientoResource(
        EmprendimientoService emprendimientoService,
        EmprendimientoRepository emprendimientoRepository,
        EmprendimientoQueryService emprendimientoQueryService
    ) {
        this.emprendimientoService = emprendimientoService;
        this.emprendimientoRepository = emprendimientoRepository;
        this.emprendimientoQueryService = emprendimientoQueryService;
    }

    /**
     * {@code POST  /emprendimientos} : Create a new emprendimiento.
     *
     * @param emprendimiento the emprendimiento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emprendimiento, or with status {@code 400 (Bad Request)} if the emprendimiento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/emprendimientos")
    public ResponseEntity<Emprendimiento> createEmprendimiento(@RequestBody Emprendimiento emprendimiento) throws URISyntaxException {
        log.debug("REST request to save Emprendimiento : {}", emprendimiento);
        if (emprendimiento.getId() != null) {
            throw new BadRequestAlertException("A new emprendimiento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Emprendimiento result = emprendimientoService.save(emprendimiento);
        return ResponseEntity
            .created(new URI("/api/emprendimientos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /emprendimientos/:id} : Updates an existing emprendimiento.
     *
     * @param id the id of the emprendimiento to save.
     * @param emprendimiento the emprendimiento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emprendimiento,
     * or with status {@code 400 (Bad Request)} if the emprendimiento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emprendimiento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/emprendimientos/{id}")
    public ResponseEntity<Emprendimiento> updateEmprendimiento(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Emprendimiento emprendimiento
    ) throws URISyntaxException {
        log.debug("REST request to update Emprendimiento : {}, {}", id, emprendimiento);
        if (emprendimiento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emprendimiento.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!emprendimientoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Emprendimiento result = emprendimientoService.save(emprendimiento);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, emprendimiento.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /emprendimientos/:id} : Partial updates given fields of an existing emprendimiento, field will ignore if it is null
     *
     * @param id the id of the emprendimiento to save.
     * @param emprendimiento the emprendimiento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emprendimiento,
     * or with status {@code 400 (Bad Request)} if the emprendimiento is not valid,
     * or with status {@code 404 (Not Found)} if the emprendimiento is not found,
     * or with status {@code 500 (Internal Server Error)} if the emprendimiento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/emprendimientos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Emprendimiento> partialUpdateEmprendimiento(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Emprendimiento emprendimiento
    ) throws URISyntaxException {
        log.debug("REST request to partial update Emprendimiento partially : {}, {}", id, emprendimiento);
        if (emprendimiento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emprendimiento.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!emprendimientoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Emprendimiento> result = emprendimientoService.partialUpdate(emprendimiento);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, emprendimiento.getId().toString())
        );
    }

    /**
     * {@code GET  /emprendimientos} : get all the emprendimientos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emprendimientos in body.
     */
    @GetMapping("/emprendimientos")
    public ResponseEntity<List<Emprendimiento>> getAllEmprendimientos(
        EmprendimientoCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Emprendimientos by criteria: {}", criteria);
        Page<Emprendimiento> page = emprendimientoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /emprendimientos/count} : count all the emprendimientos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/emprendimientos/count")
    public ResponseEntity<Long> countEmprendimientos(EmprendimientoCriteria criteria) {
        log.debug("REST request to count Emprendimientos by criteria: {}", criteria);
        return ResponseEntity.ok().body(emprendimientoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /emprendimientos/:id} : get the "id" emprendimiento.
     *
     * @param id the id of the emprendimiento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emprendimiento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emprendimientos/{id}")
    public ResponseEntity<Emprendimiento> getEmprendimiento(@PathVariable Long id) {
        log.debug("REST request to get Emprendimiento : {}", id);
        Optional<Emprendimiento> emprendimiento = emprendimientoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emprendimiento);
    }

    /**
     * {@code DELETE  /emprendimientos/:id} : delete the "id" emprendimiento.
     *
     * @param id the id of the emprendimiento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/emprendimientos/{id}")
    public ResponseEntity<Void> deleteEmprendimiento(@PathVariable Long id) {
        log.debug("REST request to delete Emprendimiento : {}", id);
        emprendimientoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
