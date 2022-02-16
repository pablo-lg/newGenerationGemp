package ar.teco.web.rest;

import ar.teco.domain.GrupoEmp;
import ar.teco.repository.GrupoEmpRepository;
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
 * REST controller for managing {@link ar.teco.domain.GrupoEmp}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GrupoEmpResource {

    private final Logger log = LoggerFactory.getLogger(GrupoEmpResource.class);

    private static final String ENTITY_NAME = "grupoEmp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GrupoEmpRepository grupoEmpRepository;

    public GrupoEmpResource(GrupoEmpRepository grupoEmpRepository) {
        this.grupoEmpRepository = grupoEmpRepository;
    }

    /**
     * {@code POST  /grupo-emps} : Create a new grupoEmp.
     *
     * @param grupoEmp the grupoEmp to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new grupoEmp, or with status {@code 400 (Bad Request)} if the grupoEmp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grupo-emps")
    public ResponseEntity<GrupoEmp> createGrupoEmp(@RequestBody GrupoEmp grupoEmp) throws URISyntaxException {
        log.debug("REST request to save GrupoEmp : {}", grupoEmp);
        if (grupoEmp.getId() != null) {
            throw new BadRequestAlertException("A new grupoEmp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrupoEmp result = grupoEmpRepository.save(grupoEmp);
        return ResponseEntity
            .created(new URI("/api/grupo-emps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grupo-emps/:id} : Updates an existing grupoEmp.
     *
     * @param id the id of the grupoEmp to save.
     * @param grupoEmp the grupoEmp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grupoEmp,
     * or with status {@code 400 (Bad Request)} if the grupoEmp is not valid,
     * or with status {@code 500 (Internal Server Error)} if the grupoEmp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grupo-emps/{id}")
    public ResponseEntity<GrupoEmp> updateGrupoEmp(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GrupoEmp grupoEmp
    ) throws URISyntaxException {
        log.debug("REST request to update GrupoEmp : {}, {}", id, grupoEmp);
        if (grupoEmp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grupoEmp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!grupoEmpRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GrupoEmp result = grupoEmpRepository.save(grupoEmp);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, grupoEmp.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /grupo-emps/:id} : Partial updates given fields of an existing grupoEmp, field will ignore if it is null
     *
     * @param id the id of the grupoEmp to save.
     * @param grupoEmp the grupoEmp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grupoEmp,
     * or with status {@code 400 (Bad Request)} if the grupoEmp is not valid,
     * or with status {@code 404 (Not Found)} if the grupoEmp is not found,
     * or with status {@code 500 (Internal Server Error)} if the grupoEmp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/grupo-emps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GrupoEmp> partialUpdateGrupoEmp(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GrupoEmp grupoEmp
    ) throws URISyntaxException {
        log.debug("REST request to partial update GrupoEmp partially : {}, {}", id, grupoEmp);
        if (grupoEmp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grupoEmp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!grupoEmpRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GrupoEmp> result = grupoEmpRepository
            .findById(grupoEmp.getId())
            .map(existingGrupoEmp -> {
                if (grupoEmp.getDescripcion() != null) {
                    existingGrupoEmp.setDescripcion(grupoEmp.getDescripcion());
                }
                if (grupoEmp.getEsProtegido() != null) {
                    existingGrupoEmp.setEsProtegido(grupoEmp.getEsProtegido());
                }

                return existingGrupoEmp;
            })
            .map(grupoEmpRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, grupoEmp.getId().toString())
        );
    }

    /**
     * {@code GET  /grupo-emps} : get all the grupoEmps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of grupoEmps in body.
     */
    @GetMapping("/grupo-emps")
    public List<GrupoEmp> getAllGrupoEmps() {
        log.debug("REST request to get all GrupoEmps");
        return grupoEmpRepository.findAll();
    }

    /**
     * {@code GET  /grupo-emps/:id} : get the "id" grupoEmp.
     *
     * @param id the id of the grupoEmp to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the grupoEmp, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grupo-emps/{id}")
    public ResponseEntity<GrupoEmp> getGrupoEmp(@PathVariable Long id) {
        log.debug("REST request to get GrupoEmp : {}", id);
        Optional<GrupoEmp> grupoEmp = grupoEmpRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(grupoEmp);
    }

    /**
     * {@code DELETE  /grupo-emps/:id} : delete the "id" grupoEmp.
     *
     * @param id the id of the grupoEmp to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grupo-emps/{id}")
    public ResponseEntity<Void> deleteGrupoEmp(@PathVariable Long id) {
        log.debug("REST request to delete GrupoEmp : {}", id);
        grupoEmpRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
