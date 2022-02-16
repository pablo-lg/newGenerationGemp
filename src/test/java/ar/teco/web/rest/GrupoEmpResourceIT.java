package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.GrupoEmp;
import ar.teco.repository.GrupoEmpRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link GrupoEmpResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GrupoEmpResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ES_PROTEGIDO = false;
    private static final Boolean UPDATED_ES_PROTEGIDO = true;

    private static final String ENTITY_API_URL = "/api/grupo-emps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GrupoEmpRepository grupoEmpRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGrupoEmpMockMvc;

    private GrupoEmp grupoEmp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrupoEmp createEntity(EntityManager em) {
        GrupoEmp grupoEmp = new GrupoEmp().descripcion(DEFAULT_DESCRIPCION).esProtegido(DEFAULT_ES_PROTEGIDO);
        return grupoEmp;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrupoEmp createUpdatedEntity(EntityManager em) {
        GrupoEmp grupoEmp = new GrupoEmp().descripcion(UPDATED_DESCRIPCION).esProtegido(UPDATED_ES_PROTEGIDO);
        return grupoEmp;
    }

    @BeforeEach
    public void initTest() {
        grupoEmp = createEntity(em);
    }

    @Test
    @Transactional
    void createGrupoEmp() throws Exception {
        int databaseSizeBeforeCreate = grupoEmpRepository.findAll().size();
        // Create the GrupoEmp
        restGrupoEmpMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grupoEmp)))
            .andExpect(status().isCreated());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeCreate + 1);
        GrupoEmp testGrupoEmp = grupoEmpList.get(grupoEmpList.size() - 1);
        assertThat(testGrupoEmp.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testGrupoEmp.getEsProtegido()).isEqualTo(DEFAULT_ES_PROTEGIDO);
    }

    @Test
    @Transactional
    void createGrupoEmpWithExistingId() throws Exception {
        // Create the GrupoEmp with an existing ID
        grupoEmp.setId(1L);

        int databaseSizeBeforeCreate = grupoEmpRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrupoEmpMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grupoEmp)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGrupoEmps() throws Exception {
        // Initialize the database
        grupoEmpRepository.saveAndFlush(grupoEmp);

        // Get all the grupoEmpList
        restGrupoEmpMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grupoEmp.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].esProtegido").value(hasItem(DEFAULT_ES_PROTEGIDO.booleanValue())));
    }

    @Test
    @Transactional
    void getGrupoEmp() throws Exception {
        // Initialize the database
        grupoEmpRepository.saveAndFlush(grupoEmp);

        // Get the grupoEmp
        restGrupoEmpMockMvc
            .perform(get(ENTITY_API_URL_ID, grupoEmp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(grupoEmp.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.esProtegido").value(DEFAULT_ES_PROTEGIDO.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingGrupoEmp() throws Exception {
        // Get the grupoEmp
        restGrupoEmpMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGrupoEmp() throws Exception {
        // Initialize the database
        grupoEmpRepository.saveAndFlush(grupoEmp);

        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();

        // Update the grupoEmp
        GrupoEmp updatedGrupoEmp = grupoEmpRepository.findById(grupoEmp.getId()).get();
        // Disconnect from session so that the updates on updatedGrupoEmp are not directly saved in db
        em.detach(updatedGrupoEmp);
        updatedGrupoEmp.descripcion(UPDATED_DESCRIPCION).esProtegido(UPDATED_ES_PROTEGIDO);

        restGrupoEmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedGrupoEmp.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedGrupoEmp))
            )
            .andExpect(status().isOk());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
        GrupoEmp testGrupoEmp = grupoEmpList.get(grupoEmpList.size() - 1);
        assertThat(testGrupoEmp.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testGrupoEmp.getEsProtegido()).isEqualTo(UPDATED_ES_PROTEGIDO);
    }

    @Test
    @Transactional
    void putNonExistingGrupoEmp() throws Exception {
        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();
        grupoEmp.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrupoEmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grupoEmp.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grupoEmp))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGrupoEmp() throws Exception {
        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();
        grupoEmp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrupoEmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grupoEmp))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGrupoEmp() throws Exception {
        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();
        grupoEmp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrupoEmpMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grupoEmp)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGrupoEmpWithPatch() throws Exception {
        // Initialize the database
        grupoEmpRepository.saveAndFlush(grupoEmp);

        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();

        // Update the grupoEmp using partial update
        GrupoEmp partialUpdatedGrupoEmp = new GrupoEmp();
        partialUpdatedGrupoEmp.setId(grupoEmp.getId());

        partialUpdatedGrupoEmp.esProtegido(UPDATED_ES_PROTEGIDO);

        restGrupoEmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrupoEmp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrupoEmp))
            )
            .andExpect(status().isOk());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
        GrupoEmp testGrupoEmp = grupoEmpList.get(grupoEmpList.size() - 1);
        assertThat(testGrupoEmp.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testGrupoEmp.getEsProtegido()).isEqualTo(UPDATED_ES_PROTEGIDO);
    }

    @Test
    @Transactional
    void fullUpdateGrupoEmpWithPatch() throws Exception {
        // Initialize the database
        grupoEmpRepository.saveAndFlush(grupoEmp);

        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();

        // Update the grupoEmp using partial update
        GrupoEmp partialUpdatedGrupoEmp = new GrupoEmp();
        partialUpdatedGrupoEmp.setId(grupoEmp.getId());

        partialUpdatedGrupoEmp.descripcion(UPDATED_DESCRIPCION).esProtegido(UPDATED_ES_PROTEGIDO);

        restGrupoEmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrupoEmp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrupoEmp))
            )
            .andExpect(status().isOk());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
        GrupoEmp testGrupoEmp = grupoEmpList.get(grupoEmpList.size() - 1);
        assertThat(testGrupoEmp.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testGrupoEmp.getEsProtegido()).isEqualTo(UPDATED_ES_PROTEGIDO);
    }

    @Test
    @Transactional
    void patchNonExistingGrupoEmp() throws Exception {
        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();
        grupoEmp.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrupoEmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, grupoEmp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grupoEmp))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGrupoEmp() throws Exception {
        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();
        grupoEmp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrupoEmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grupoEmp))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGrupoEmp() throws Exception {
        int databaseSizeBeforeUpdate = grupoEmpRepository.findAll().size();
        grupoEmp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrupoEmpMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(grupoEmp)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrupoEmp in the database
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGrupoEmp() throws Exception {
        // Initialize the database
        grupoEmpRepository.saveAndFlush(grupoEmp);

        int databaseSizeBeforeDelete = grupoEmpRepository.findAll().size();

        // Delete the grupoEmp
        restGrupoEmpMockMvc
            .perform(delete(ENTITY_API_URL_ID, grupoEmp.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GrupoEmp> grupoEmpList = grupoEmpRepository.findAll();
        assertThat(grupoEmpList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
