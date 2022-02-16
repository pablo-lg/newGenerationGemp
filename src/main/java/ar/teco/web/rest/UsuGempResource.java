package ar.teco.web.rest;

import ar.teco.domain.UsuGemp;
import ar.teco.repository.UsuGempRepository;
import ar.teco.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.teco.domain.UsuGemp}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UsuGempResource {

    private final Logger log = LoggerFactory.getLogger(UsuGempResource.class);

    private static final String ENTITY_NAME = "usuGemp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsuGempRepository usuGempRepository;

    public UsuGempResource(UsuGempRepository usuGempRepository) {
        this.usuGempRepository = usuGempRepository;
    }

    /**
     * {@code POST  /usu-gemps} : Create a new usuGemp.
     *
     * @param usuGemp the usuGemp to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usuGemp, or with status {@code 400 (Bad Request)} if the usuGemp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/usu-gemps")
    public ResponseEntity<UsuGemp> createUsuGemp(@Valid @RequestBody UsuGemp usuGemp) throws URISyntaxException {
        log.debug("REST request to save UsuGemp : {}", usuGemp);
        if (usuGemp.getId() != null) {
            throw new BadRequestAlertException("A new usuGemp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsuGemp result = usuGempRepository.save(usuGemp);
        return ResponseEntity
            .created(new URI("/api/usu-gemps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /usu-gemps/:id} : Updates an existing usuGemp.
     *
     * @param id the id of the usuGemp to save.
     * @param usuGemp the usuGemp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usuGemp,
     * or with status {@code 400 (Bad Request)} if the usuGemp is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usuGemp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/usu-gemps/{id}")
    public ResponseEntity<UsuGemp> updateUsuGemp(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody UsuGemp usuGemp
    ) throws URISyntaxException {
        log.debug("REST request to update UsuGemp : {}, {}", id, usuGemp);
        if (usuGemp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, usuGemp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!usuGempRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UsuGemp result = usuGempRepository.save(usuGemp);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, usuGemp.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /usu-gemps/:id} : Partial updates given fields of an existing usuGemp, field will ignore if it is null
     *
     * @param id the id of the usuGemp to save.
     * @param usuGemp the usuGemp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usuGemp,
     * or with status {@code 400 (Bad Request)} if the usuGemp is not valid,
     * or with status {@code 404 (Not Found)} if the usuGemp is not found,
     * or with status {@code 500 (Internal Server Error)} if the usuGemp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/usu-gemps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UsuGemp> partialUpdateUsuGemp(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody UsuGemp usuGemp
    ) throws URISyntaxException {
        log.debug("REST request to partial update UsuGemp partially : {}, {}", id, usuGemp);
        if (usuGemp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, usuGemp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!usuGempRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UsuGemp> result = usuGempRepository
            .findById(usuGemp.getId())
            .map(existingUsuGemp -> {
                if (usuGemp.getUsu() != null) {
                    existingUsuGemp.setUsu(usuGemp.getUsu());
                }
                if (usuGemp.getNombre() != null) {
                    existingUsuGemp.setNombre(usuGemp.getNombre());
                }
                if (usuGemp.getApellido() != null) {
                    existingUsuGemp.setApellido(usuGemp.getApellido());
                }
                if (usuGemp.getEmail() != null) {
                    existingUsuGemp.setEmail(usuGemp.getEmail());
                }
                if (usuGemp.getPerfiles() != null) {
                    existingUsuGemp.setPerfiles(usuGemp.getPerfiles());
                }

                return existingUsuGemp;
            })
            .map(usuGempRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, usuGemp.getId().toString())
        );
    }

    /**
     * {@code GET  /usu-gemps} : get all the usuGemps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usuGemps in body.
     */
    @GetMapping("/usu-gemps")
    public List<UsuGemp> getAllUsuGemps() {
        log.debug("REST request to get all UsuGemps");
        return usuGempRepository.findAll();
    }

    /**
     * {@code GET  /usu-gemps/:id} : get the "id" usuGemp.
     *
     * @param id the id of the usuGemp to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usuGemp, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/usu-gemps/{id}")
    public ResponseEntity<UsuGemp> getUsuGemp(@PathVariable Long id) {
        log.debug("REST request to get UsuGemp : {}", id);
        Optional<UsuGemp> usuGemp = usuGempRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(usuGemp);
    }

    /**
     * {@code DELETE  /usu-gemps/:id} : delete the "id" usuGemp.
     *
     * @param id the id of the usuGemp to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/usu-gemps/{id}")
    public ResponseEntity<Void> deleteUsuGemp(@PathVariable Long id) {
        log.debug("REST request to delete UsuGemp : {}", id);
        usuGempRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
