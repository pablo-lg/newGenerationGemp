package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.FiltroRep;
import ar.teco.repository.FiltroRepRepository;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link FiltroRepResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FiltroRepResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_FILTRO = "AAAAAAAAAA";
    private static final String UPDATED_FILTRO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/filtro-reps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FiltroRepRepository filtroRepRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFiltroRepMockMvc;

    private FiltroRep filtroRep;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FiltroRep createEntity(EntityManager em) {
        FiltroRep filtroRep = new FiltroRep().nombre(DEFAULT_NOMBRE).filtro(DEFAULT_FILTRO);
        return filtroRep;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FiltroRep createUpdatedEntity(EntityManager em) {
        FiltroRep filtroRep = new FiltroRep().nombre(UPDATED_NOMBRE).filtro(UPDATED_FILTRO);
        return filtroRep;
    }

    @BeforeEach
    public void initTest() {
        filtroRep = createEntity(em);
    }

    @Test
    @Transactional
    void createFiltroRep() throws Exception {
        int databaseSizeBeforeCreate = filtroRepRepository.findAll().size();
        // Create the FiltroRep
        restFiltroRepMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(filtroRep)))
            .andExpect(status().isCreated());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeCreate + 1);
        FiltroRep testFiltroRep = filtroRepList.get(filtroRepList.size() - 1);
        assertThat(testFiltroRep.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testFiltroRep.getFiltro()).isEqualTo(DEFAULT_FILTRO);
    }

    @Test
    @Transactional
    void createFiltroRepWithExistingId() throws Exception {
        // Create the FiltroRep with an existing ID
        filtroRep.setId(1L);

        int databaseSizeBeforeCreate = filtroRepRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFiltroRepMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(filtroRep)))
            .andExpect(status().isBadRequest());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFiltroReps() throws Exception {
        // Initialize the database
        filtroRepRepository.saveAndFlush(filtroRep);

        // Get all the filtroRepList
        restFiltroRepMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(filtroRep.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].filtro").value(hasItem(DEFAULT_FILTRO.toString())));
    }

    @Test
    @Transactional
    void getFiltroRep() throws Exception {
        // Initialize the database
        filtroRepRepository.saveAndFlush(filtroRep);

        // Get the filtroRep
        restFiltroRepMockMvc
            .perform(get(ENTITY_API_URL_ID, filtroRep.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(filtroRep.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.filtro").value(DEFAULT_FILTRO.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFiltroRep() throws Exception {
        // Get the filtroRep
        restFiltroRepMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFiltroRep() throws Exception {
        // Initialize the database
        filtroRepRepository.saveAndFlush(filtroRep);

        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();

        // Update the filtroRep
        FiltroRep updatedFiltroRep = filtroRepRepository.findById(filtroRep.getId()).get();
        // Disconnect from session so that the updates on updatedFiltroRep are not directly saved in db
        em.detach(updatedFiltroRep);
        updatedFiltroRep.nombre(UPDATED_NOMBRE).filtro(UPDATED_FILTRO);

        restFiltroRepMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFiltroRep.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFiltroRep))
            )
            .andExpect(status().isOk());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
        FiltroRep testFiltroRep = filtroRepList.get(filtroRepList.size() - 1);
        assertThat(testFiltroRep.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testFiltroRep.getFiltro()).isEqualTo(UPDATED_FILTRO);
    }

    @Test
    @Transactional
    void putNonExistingFiltroRep() throws Exception {
        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();
        filtroRep.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFiltroRepMockMvc
            .perform(
                put(ENTITY_API_URL_ID, filtroRep.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(filtroRep))
            )
            .andExpect(status().isBadRequest());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFiltroRep() throws Exception {
        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();
        filtroRep.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFiltroRepMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(filtroRep))
            )
            .andExpect(status().isBadRequest());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFiltroRep() throws Exception {
        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();
        filtroRep.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFiltroRepMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(filtroRep)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFiltroRepWithPatch() throws Exception {
        // Initialize the database
        filtroRepRepository.saveAndFlush(filtroRep);

        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();

        // Update the filtroRep using partial update
        FiltroRep partialUpdatedFiltroRep = new FiltroRep();
        partialUpdatedFiltroRep.setId(filtroRep.getId());

        partialUpdatedFiltroRep.nombre(UPDATED_NOMBRE);

        restFiltroRepMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiltroRep.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFiltroRep))
            )
            .andExpect(status().isOk());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
        FiltroRep testFiltroRep = filtroRepList.get(filtroRepList.size() - 1);
        assertThat(testFiltroRep.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testFiltroRep.getFiltro()).isEqualTo(DEFAULT_FILTRO);
    }

    @Test
    @Transactional
    void fullUpdateFiltroRepWithPatch() throws Exception {
        // Initialize the database
        filtroRepRepository.saveAndFlush(filtroRep);

        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();

        // Update the filtroRep using partial update
        FiltroRep partialUpdatedFiltroRep = new FiltroRep();
        partialUpdatedFiltroRep.setId(filtroRep.getId());

        partialUpdatedFiltroRep.nombre(UPDATED_NOMBRE).filtro(UPDATED_FILTRO);

        restFiltroRepMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiltroRep.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFiltroRep))
            )
            .andExpect(status().isOk());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
        FiltroRep testFiltroRep = filtroRepList.get(filtroRepList.size() - 1);
        assertThat(testFiltroRep.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testFiltroRep.getFiltro()).isEqualTo(UPDATED_FILTRO);
    }

    @Test
    @Transactional
    void patchNonExistingFiltroRep() throws Exception {
        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();
        filtroRep.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFiltroRepMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, filtroRep.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(filtroRep))
            )
            .andExpect(status().isBadRequest());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFiltroRep() throws Exception {
        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();
        filtroRep.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFiltroRepMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(filtroRep))
            )
            .andExpect(status().isBadRequest());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFiltroRep() throws Exception {
        int databaseSizeBeforeUpdate = filtroRepRepository.findAll().size();
        filtroRep.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFiltroRepMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(filtroRep))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FiltroRep in the database
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFiltroRep() throws Exception {
        // Initialize the database
        filtroRepRepository.saveAndFlush(filtroRep);

        int databaseSizeBeforeDelete = filtroRepRepository.findAll().size();

        // Delete the filtroRep
        restFiltroRepMockMvc
            .perform(delete(ENTITY_API_URL_ID, filtroRep.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FiltroRep> filtroRepList = filtroRepRepository.findAll();
        assertThat(filtroRepList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
