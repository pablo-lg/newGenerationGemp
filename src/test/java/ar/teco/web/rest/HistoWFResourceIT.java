package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.Emprendimiento;
import ar.teco.domain.HistoWF;
import ar.teco.domain.enumeration.Estado;
import ar.teco.domain.enumeration.Estado;
import ar.teco.repository.HistoWFRepository;
import ar.teco.service.criteria.HistoWFCriteria;
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
 * Integration tests for the {@link HistoWFResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HistoWFResourceIT {

    private static final Estado DEFAULT_ESTADO_ANTERIOR = Estado.SIN_ESTADO;
    private static final Estado UPDATED_ESTADO_ANTERIOR = Estado.PROSPECTO;

    private static final Estado DEFAULT_ESTADO_ACTUAL = Estado.SIN_ESTADO;
    private static final Estado UPDATED_ESTADO_ACTUAL = Estado.PROSPECTO;

    private static final String ENTITY_API_URL = "/api/histo-wfs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HistoWFRepository histoWFRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHistoWFMockMvc;

    private HistoWF histoWF;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoWF createEntity(EntityManager em) {
        HistoWF histoWF = new HistoWF().estadoAnterior(DEFAULT_ESTADO_ANTERIOR).estadoActual(DEFAULT_ESTADO_ACTUAL);
        return histoWF;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoWF createUpdatedEntity(EntityManager em) {
        HistoWF histoWF = new HistoWF().estadoAnterior(UPDATED_ESTADO_ANTERIOR).estadoActual(UPDATED_ESTADO_ACTUAL);
        return histoWF;
    }

    @BeforeEach
    public void initTest() {
        histoWF = createEntity(em);
    }

    @Test
    @Transactional
    void createHistoWF() throws Exception {
        int databaseSizeBeforeCreate = histoWFRepository.findAll().size();
        // Create the HistoWF
        restHistoWFMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(histoWF)))
            .andExpect(status().isCreated());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeCreate + 1);
        HistoWF testHistoWF = histoWFList.get(histoWFList.size() - 1);
        assertThat(testHistoWF.getEstadoAnterior()).isEqualTo(DEFAULT_ESTADO_ANTERIOR);
        assertThat(testHistoWF.getEstadoActual()).isEqualTo(DEFAULT_ESTADO_ACTUAL);
    }

    @Test
    @Transactional
    void createHistoWFWithExistingId() throws Exception {
        // Create the HistoWF with an existing ID
        histoWF.setId(1L);

        int databaseSizeBeforeCreate = histoWFRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoWFMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(histoWF)))
            .andExpect(status().isBadRequest());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHistoWFS() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList
        restHistoWFMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(histoWF.getId().intValue())))
            .andExpect(jsonPath("$.[*].estadoAnterior").value(hasItem(DEFAULT_ESTADO_ANTERIOR.toString())))
            .andExpect(jsonPath("$.[*].estadoActual").value(hasItem(DEFAULT_ESTADO_ACTUAL.toString())));
    }

    @Test
    @Transactional
    void getHistoWF() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get the histoWF
        restHistoWFMockMvc
            .perform(get(ENTITY_API_URL_ID, histoWF.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(histoWF.getId().intValue()))
            .andExpect(jsonPath("$.estadoAnterior").value(DEFAULT_ESTADO_ANTERIOR.toString()))
            .andExpect(jsonPath("$.estadoActual").value(DEFAULT_ESTADO_ACTUAL.toString()));
    }

    @Test
    @Transactional
    void getHistoWFSByIdFiltering() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        Long id = histoWF.getId();

        defaultHistoWFShouldBeFound("id.equals=" + id);
        defaultHistoWFShouldNotBeFound("id.notEquals=" + id);

        defaultHistoWFShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultHistoWFShouldNotBeFound("id.greaterThan=" + id);

        defaultHistoWFShouldBeFound("id.lessThanOrEqual=" + id);
        defaultHistoWFShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllHistoWFSByEstadoAnteriorIsEqualToSomething() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList where estadoAnterior equals to DEFAULT_ESTADO_ANTERIOR
        defaultHistoWFShouldBeFound("estadoAnterior.equals=" + DEFAULT_ESTADO_ANTERIOR);

        // Get all the histoWFList where estadoAnterior equals to UPDATED_ESTADO_ANTERIOR
        defaultHistoWFShouldNotBeFound("estadoAnterior.equals=" + UPDATED_ESTADO_ANTERIOR);
    }

    @Test
    @Transactional
    void getAllHistoWFSByEstadoAnteriorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList where estadoAnterior not equals to DEFAULT_ESTADO_ANTERIOR
        defaultHistoWFShouldNotBeFound("estadoAnterior.notEquals=" + DEFAULT_ESTADO_ANTERIOR);

        // Get all the histoWFList where estadoAnterior not equals to UPDATED_ESTADO_ANTERIOR
        defaultHistoWFShouldBeFound("estadoAnterior.notEquals=" + UPDATED_ESTADO_ANTERIOR);
    }

    @Test
    @Transactional
    void getAllHistoWFSByEstadoAnteriorIsInShouldWork() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList where estadoAnterior in DEFAULT_ESTADO_ANTERIOR or UPDATED_ESTADO_ANTERIOR
        defaultHistoWFShouldBeFound("estadoAnterior.in=" + DEFAULT_ESTADO_ANTERIOR + "," + UPDATED_ESTADO_ANTERIOR);

        // Get all the histoWFList where estadoAnterior equals to UPDATED_ESTADO_ANTERIOR
        defaultHistoWFShouldNotBeFound("estadoAnterior.in=" + UPDATED_ESTADO_ANTERIOR);
    }

    @Test
    @Transactional
    void getAllHistoWFSByEstadoAnteriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList where estadoAnterior is not null
        defaultHistoWFShouldBeFound("estadoAnterior.specified=true");

        // Get all the histoWFList where estadoAnterior is null
        defaultHistoWFShouldNotBeFound("estadoAnterior.specified=false");
    }

    @Test
    @Transactional
    void getAllHistoWFSByEstadoActualIsEqualToSomething() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList where estadoActual equals to DEFAULT_ESTADO_ACTUAL
        defaultHistoWFShouldBeFound("estadoActual.equals=" + DEFAULT_ESTADO_ACTUAL);

        // Get all the histoWFList where estadoActual equals to UPDATED_ESTADO_ACTUAL
        defaultHistoWFShouldNotBeFound("estadoActual.equals=" + UPDATED_ESTADO_ACTUAL);
    }

    @Test
    @Transactional
    void getAllHistoWFSByEstadoActualIsNotEqualToSomething() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList where estadoActual not equals to DEFAULT_ESTADO_ACTUAL
        defaultHistoWFShouldNotBeFound("estadoActual.notEquals=" + DEFAULT_ESTADO_ACTUAL);

        // Get all the histoWFList where estadoActual not equals to UPDATED_ESTADO_ACTUAL
        defaultHistoWFShouldBeFound("estadoActual.notEquals=" + UPDATED_ESTADO_ACTUAL);
    }

    @Test
    @Transactional
    void getAllHistoWFSByEstadoActualIsInShouldWork() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList where estadoActual in DEFAULT_ESTADO_ACTUAL or UPDATED_ESTADO_ACTUAL
        defaultHistoWFShouldBeFound("estadoActual.in=" + DEFAULT_ESTADO_ACTUAL + "," + UPDATED_ESTADO_ACTUAL);

        // Get all the histoWFList where estadoActual equals to UPDATED_ESTADO_ACTUAL
        defaultHistoWFShouldNotBeFound("estadoActual.in=" + UPDATED_ESTADO_ACTUAL);
    }

    @Test
    @Transactional
    void getAllHistoWFSByEstadoActualIsNullOrNotNull() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        // Get all the histoWFList where estadoActual is not null
        defaultHistoWFShouldBeFound("estadoActual.specified=true");

        // Get all the histoWFList where estadoActual is null
        defaultHistoWFShouldNotBeFound("estadoActual.specified=false");
    }

    @Test
    @Transactional
    void getAllHistoWFSByEmprendimientoIsEqualToSomething() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);
        Emprendimiento emprendimiento;
        if (TestUtil.findAll(em, Emprendimiento.class).isEmpty()) {
            emprendimiento = EmprendimientoResourceIT.createEntity(em);
            em.persist(emprendimiento);
            em.flush();
        } else {
            emprendimiento = TestUtil.findAll(em, Emprendimiento.class).get(0);
        }
        em.persist(emprendimiento);
        em.flush();
        histoWF.setEmprendimiento(emprendimiento);
        histoWFRepository.saveAndFlush(histoWF);
        Long emprendimientoId = emprendimiento.getId();

        // Get all the histoWFList where emprendimiento equals to emprendimientoId
        defaultHistoWFShouldBeFound("emprendimientoId.equals=" + emprendimientoId);

        // Get all the histoWFList where emprendimiento equals to (emprendimientoId + 1)
        defaultHistoWFShouldNotBeFound("emprendimientoId.equals=" + (emprendimientoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultHistoWFShouldBeFound(String filter) throws Exception {
        restHistoWFMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(histoWF.getId().intValue())))
            .andExpect(jsonPath("$.[*].estadoAnterior").value(hasItem(DEFAULT_ESTADO_ANTERIOR.toString())))
            .andExpect(jsonPath("$.[*].estadoActual").value(hasItem(DEFAULT_ESTADO_ACTUAL.toString())));

        // Check, that the count call also returns 1
        restHistoWFMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultHistoWFShouldNotBeFound(String filter) throws Exception {
        restHistoWFMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restHistoWFMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingHistoWF() throws Exception {
        // Get the histoWF
        restHistoWFMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewHistoWF() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();

        // Update the histoWF
        HistoWF updatedHistoWF = histoWFRepository.findById(histoWF.getId()).get();
        // Disconnect from session so that the updates on updatedHistoWF are not directly saved in db
        em.detach(updatedHistoWF);
        updatedHistoWF.estadoAnterior(UPDATED_ESTADO_ANTERIOR).estadoActual(UPDATED_ESTADO_ACTUAL);

        restHistoWFMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHistoWF.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedHistoWF))
            )
            .andExpect(status().isOk());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
        HistoWF testHistoWF = histoWFList.get(histoWFList.size() - 1);
        assertThat(testHistoWF.getEstadoAnterior()).isEqualTo(UPDATED_ESTADO_ANTERIOR);
        assertThat(testHistoWF.getEstadoActual()).isEqualTo(UPDATED_ESTADO_ACTUAL);
    }

    @Test
    @Transactional
    void putNonExistingHistoWF() throws Exception {
        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();
        histoWF.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoWFMockMvc
            .perform(
                put(ENTITY_API_URL_ID, histoWF.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(histoWF))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHistoWF() throws Exception {
        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();
        histoWF.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoWFMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(histoWF))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHistoWF() throws Exception {
        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();
        histoWF.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoWFMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(histoWF)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHistoWFWithPatch() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();

        // Update the histoWF using partial update
        HistoWF partialUpdatedHistoWF = new HistoWF();
        partialUpdatedHistoWF.setId(histoWF.getId());

        restHistoWFMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistoWF.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHistoWF))
            )
            .andExpect(status().isOk());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
        HistoWF testHistoWF = histoWFList.get(histoWFList.size() - 1);
        assertThat(testHistoWF.getEstadoAnterior()).isEqualTo(DEFAULT_ESTADO_ANTERIOR);
        assertThat(testHistoWF.getEstadoActual()).isEqualTo(DEFAULT_ESTADO_ACTUAL);
    }

    @Test
    @Transactional
    void fullUpdateHistoWFWithPatch() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();

        // Update the histoWF using partial update
        HistoWF partialUpdatedHistoWF = new HistoWF();
        partialUpdatedHistoWF.setId(histoWF.getId());

        partialUpdatedHistoWF.estadoAnterior(UPDATED_ESTADO_ANTERIOR).estadoActual(UPDATED_ESTADO_ACTUAL);

        restHistoWFMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHistoWF.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHistoWF))
            )
            .andExpect(status().isOk());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
        HistoWF testHistoWF = histoWFList.get(histoWFList.size() - 1);
        assertThat(testHistoWF.getEstadoAnterior()).isEqualTo(UPDATED_ESTADO_ANTERIOR);
        assertThat(testHistoWF.getEstadoActual()).isEqualTo(UPDATED_ESTADO_ACTUAL);
    }

    @Test
    @Transactional
    void patchNonExistingHistoWF() throws Exception {
        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();
        histoWF.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoWFMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, histoWF.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(histoWF))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHistoWF() throws Exception {
        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();
        histoWF.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoWFMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(histoWF))
            )
            .andExpect(status().isBadRequest());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHistoWF() throws Exception {
        int databaseSizeBeforeUpdate = histoWFRepository.findAll().size();
        histoWF.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHistoWFMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(histoWF)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HistoWF in the database
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHistoWF() throws Exception {
        // Initialize the database
        histoWFRepository.saveAndFlush(histoWF);

        int databaseSizeBeforeDelete = histoWFRepository.findAll().size();

        // Delete the histoWF
        restHistoWFMockMvc
            .perform(delete(ENTITY_API_URL_ID, histoWF.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistoWF> histoWFList = histoWFRepository.findAll();
        assertThat(histoWFList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
