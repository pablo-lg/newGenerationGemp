package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.Demanda;
import ar.teco.repository.DemandaRepository;
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
 * Integration tests for the {@link DemandaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DemandaResourceIT {

    private static final String DEFAULT_A_1 = "AAAAAAAAAA";
    private static final String UPDATED_A_1 = "BBBBBBBBBB";

    private static final String DEFAULT_A_2 = "AAAAAAAAAA";
    private static final String UPDATED_A_2 = "BBBBBBBBBB";

    private static final String DEFAULT_A_3 = "AAAAAAAAAA";
    private static final String UPDATED_A_3 = "BBBBBBBBBB";

    private static final String DEFAULT_A_4 = "AAAAAAAAAA";
    private static final String UPDATED_A_4 = "BBBBBBBBBB";

    private static final String DEFAULT_A_5 = "AAAAAAAAAA";
    private static final String UPDATED_A_5 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/demandas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DemandaRepository demandaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandaMockMvc;

    private Demanda demanda;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demanda createEntity(EntityManager em) {
        Demanda demanda = new Demanda().a1(DEFAULT_A_1).a2(DEFAULT_A_2).a3(DEFAULT_A_3).a4(DEFAULT_A_4).a5(DEFAULT_A_5);
        return demanda;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demanda createUpdatedEntity(EntityManager em) {
        Demanda demanda = new Demanda().a1(UPDATED_A_1).a2(UPDATED_A_2).a3(UPDATED_A_3).a4(UPDATED_A_4).a5(UPDATED_A_5);
        return demanda;
    }

    @BeforeEach
    public void initTest() {
        demanda = createEntity(em);
    }

    @Test
    @Transactional
    void createDemanda() throws Exception {
        int databaseSizeBeforeCreate = demandaRepository.findAll().size();
        // Create the Demanda
        restDemandaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demanda)))
            .andExpect(status().isCreated());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeCreate + 1);
        Demanda testDemanda = demandaList.get(demandaList.size() - 1);
        assertThat(testDemanda.geta1()).isEqualTo(DEFAULT_A_1);
        assertThat(testDemanda.geta2()).isEqualTo(DEFAULT_A_2);
        assertThat(testDemanda.geta3()).isEqualTo(DEFAULT_A_3);
        assertThat(testDemanda.geta4()).isEqualTo(DEFAULT_A_4);
        assertThat(testDemanda.geta5()).isEqualTo(DEFAULT_A_5);
    }

    @Test
    @Transactional
    void createDemandaWithExistingId() throws Exception {
        // Create the Demanda with an existing ID
        demanda.setId(1L);

        int databaseSizeBeforeCreate = demandaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demanda)))
            .andExpect(status().isBadRequest());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDemandas() throws Exception {
        // Initialize the database
        demandaRepository.saveAndFlush(demanda);

        // Get all the demandaList
        restDemandaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demanda.getId().intValue())))
            .andExpect(jsonPath("$.[*].a1").value(hasItem(DEFAULT_A_1)))
            .andExpect(jsonPath("$.[*].a2").value(hasItem(DEFAULT_A_2)))
            .andExpect(jsonPath("$.[*].a3").value(hasItem(DEFAULT_A_3)))
            .andExpect(jsonPath("$.[*].a4").value(hasItem(DEFAULT_A_4)))
            .andExpect(jsonPath("$.[*].a5").value(hasItem(DEFAULT_A_5)));
    }

    @Test
    @Transactional
    void getDemanda() throws Exception {
        // Initialize the database
        demandaRepository.saveAndFlush(demanda);

        // Get the demanda
        restDemandaMockMvc
            .perform(get(ENTITY_API_URL_ID, demanda.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demanda.getId().intValue()))
            .andExpect(jsonPath("$.a1").value(DEFAULT_A_1))
            .andExpect(jsonPath("$.a2").value(DEFAULT_A_2))
            .andExpect(jsonPath("$.a3").value(DEFAULT_A_3))
            .andExpect(jsonPath("$.a4").value(DEFAULT_A_4))
            .andExpect(jsonPath("$.a5").value(DEFAULT_A_5));
    }

    @Test
    @Transactional
    void getNonExistingDemanda() throws Exception {
        // Get the demanda
        restDemandaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDemanda() throws Exception {
        // Initialize the database
        demandaRepository.saveAndFlush(demanda);

        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();

        // Update the demanda
        Demanda updatedDemanda = demandaRepository.findById(demanda.getId()).get();
        // Disconnect from session so that the updates on updatedDemanda are not directly saved in db
        em.detach(updatedDemanda);
        updatedDemanda.a1(UPDATED_A_1).a2(UPDATED_A_2).a3(UPDATED_A_3).a4(UPDATED_A_4).a5(UPDATED_A_5);

        restDemandaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDemanda.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDemanda))
            )
            .andExpect(status().isOk());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
        Demanda testDemanda = demandaList.get(demandaList.size() - 1);
        assertThat(testDemanda.geta1()).isEqualTo(UPDATED_A_1);
        assertThat(testDemanda.geta2()).isEqualTo(UPDATED_A_2);
        assertThat(testDemanda.geta3()).isEqualTo(UPDATED_A_3);
        assertThat(testDemanda.geta4()).isEqualTo(UPDATED_A_4);
        assertThat(testDemanda.geta5()).isEqualTo(UPDATED_A_5);
    }

    @Test
    @Transactional
    void putNonExistingDemanda() throws Exception {
        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();
        demanda.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demanda.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demanda))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDemanda() throws Exception {
        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();
        demanda.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demanda))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDemanda() throws Exception {
        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();
        demanda.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demanda)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDemandaWithPatch() throws Exception {
        // Initialize the database
        demandaRepository.saveAndFlush(demanda);

        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();

        // Update the demanda using partial update
        Demanda partialUpdatedDemanda = new Demanda();
        partialUpdatedDemanda.setId(demanda.getId());

        partialUpdatedDemanda.a1(UPDATED_A_1).a3(UPDATED_A_3).a4(UPDATED_A_4).a5(UPDATED_A_5);

        restDemandaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemanda.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemanda))
            )
            .andExpect(status().isOk());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
        Demanda testDemanda = demandaList.get(demandaList.size() - 1);
        assertThat(testDemanda.geta1()).isEqualTo(UPDATED_A_1);
        assertThat(testDemanda.geta2()).isEqualTo(DEFAULT_A_2);
        assertThat(testDemanda.geta3()).isEqualTo(UPDATED_A_3);
        assertThat(testDemanda.geta4()).isEqualTo(UPDATED_A_4);
        assertThat(testDemanda.geta5()).isEqualTo(UPDATED_A_5);
    }

    @Test
    @Transactional
    void fullUpdateDemandaWithPatch() throws Exception {
        // Initialize the database
        demandaRepository.saveAndFlush(demanda);

        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();

        // Update the demanda using partial update
        Demanda partialUpdatedDemanda = new Demanda();
        partialUpdatedDemanda.setId(demanda.getId());

        partialUpdatedDemanda.a1(UPDATED_A_1).a2(UPDATED_A_2).a3(UPDATED_A_3).a4(UPDATED_A_4).a5(UPDATED_A_5);

        restDemandaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemanda.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemanda))
            )
            .andExpect(status().isOk());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
        Demanda testDemanda = demandaList.get(demandaList.size() - 1);
        assertThat(testDemanda.geta1()).isEqualTo(UPDATED_A_1);
        assertThat(testDemanda.geta2()).isEqualTo(UPDATED_A_2);
        assertThat(testDemanda.geta3()).isEqualTo(UPDATED_A_3);
        assertThat(testDemanda.geta4()).isEqualTo(UPDATED_A_4);
        assertThat(testDemanda.geta5()).isEqualTo(UPDATED_A_5);
    }

    @Test
    @Transactional
    void patchNonExistingDemanda() throws Exception {
        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();
        demanda.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, demanda.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demanda))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDemanda() throws Exception {
        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();
        demanda.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demanda))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDemanda() throws Exception {
        int databaseSizeBeforeUpdate = demandaRepository.findAll().size();
        demanda.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemandaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(demanda)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Demanda in the database
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDemanda() throws Exception {
        // Initialize the database
        demandaRepository.saveAndFlush(demanda);

        int databaseSizeBeforeDelete = demandaRepository.findAll().size();

        // Delete the demanda
        restDemandaMockMvc
            .perform(delete(ENTITY_API_URL_ID, demanda.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Demanda> demandaList = demandaRepository.findAll();
        assertThat(demandaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
