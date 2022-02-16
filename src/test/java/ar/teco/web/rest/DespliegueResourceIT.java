package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.Despliegue;
import ar.teco.repository.DespliegueRepository;
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
 * Integration tests for the {@link DespliegueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DespliegueResourceIT {

    private static final String DEFAULT_REGION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REGION_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/despliegues";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DespliegueRepository despliegueRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDespliegueMockMvc;

    private Despliegue despliegue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Despliegue createEntity(EntityManager em) {
        Despliegue despliegue = new Despliegue().regionName(DEFAULT_REGION_NAME);
        return despliegue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Despliegue createUpdatedEntity(EntityManager em) {
        Despliegue despliegue = new Despliegue().regionName(UPDATED_REGION_NAME);
        return despliegue;
    }

    @BeforeEach
    public void initTest() {
        despliegue = createEntity(em);
    }

    @Test
    @Transactional
    void createDespliegue() throws Exception {
        int databaseSizeBeforeCreate = despliegueRepository.findAll().size();
        // Create the Despliegue
        restDespliegueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(despliegue)))
            .andExpect(status().isCreated());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeCreate + 1);
        Despliegue testDespliegue = despliegueList.get(despliegueList.size() - 1);
        assertThat(testDespliegue.getRegionName()).isEqualTo(DEFAULT_REGION_NAME);
    }

    @Test
    @Transactional
    void createDespliegueWithExistingId() throws Exception {
        // Create the Despliegue with an existing ID
        despliegue.setId(1L);

        int databaseSizeBeforeCreate = despliegueRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDespliegueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(despliegue)))
            .andExpect(status().isBadRequest());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDespliegues() throws Exception {
        // Initialize the database
        despliegueRepository.saveAndFlush(despliegue);

        // Get all the despliegueList
        restDespliegueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(despliegue.getId().intValue())))
            .andExpect(jsonPath("$.[*].regionName").value(hasItem(DEFAULT_REGION_NAME)));
    }

    @Test
    @Transactional
    void getDespliegue() throws Exception {
        // Initialize the database
        despliegueRepository.saveAndFlush(despliegue);

        // Get the despliegue
        restDespliegueMockMvc
            .perform(get(ENTITY_API_URL_ID, despliegue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(despliegue.getId().intValue()))
            .andExpect(jsonPath("$.regionName").value(DEFAULT_REGION_NAME));
    }

    @Test
    @Transactional
    void getNonExistingDespliegue() throws Exception {
        // Get the despliegue
        restDespliegueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDespliegue() throws Exception {
        // Initialize the database
        despliegueRepository.saveAndFlush(despliegue);

        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();

        // Update the despliegue
        Despliegue updatedDespliegue = despliegueRepository.findById(despliegue.getId()).get();
        // Disconnect from session so that the updates on updatedDespliegue are not directly saved in db
        em.detach(updatedDespliegue);
        updatedDespliegue.regionName(UPDATED_REGION_NAME);

        restDespliegueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDespliegue.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDespliegue))
            )
            .andExpect(status().isOk());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
        Despliegue testDespliegue = despliegueList.get(despliegueList.size() - 1);
        assertThat(testDespliegue.getRegionName()).isEqualTo(UPDATED_REGION_NAME);
    }

    @Test
    @Transactional
    void putNonExistingDespliegue() throws Exception {
        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();
        despliegue.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDespliegueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, despliegue.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(despliegue))
            )
            .andExpect(status().isBadRequest());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDespliegue() throws Exception {
        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();
        despliegue.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDespliegueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(despliegue))
            )
            .andExpect(status().isBadRequest());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDespliegue() throws Exception {
        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();
        despliegue.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDespliegueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(despliegue)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDespliegueWithPatch() throws Exception {
        // Initialize the database
        despliegueRepository.saveAndFlush(despliegue);

        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();

        // Update the despliegue using partial update
        Despliegue partialUpdatedDespliegue = new Despliegue();
        partialUpdatedDespliegue.setId(despliegue.getId());

        restDespliegueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDespliegue.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDespliegue))
            )
            .andExpect(status().isOk());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
        Despliegue testDespliegue = despliegueList.get(despliegueList.size() - 1);
        assertThat(testDespliegue.getRegionName()).isEqualTo(DEFAULT_REGION_NAME);
    }

    @Test
    @Transactional
    void fullUpdateDespliegueWithPatch() throws Exception {
        // Initialize the database
        despliegueRepository.saveAndFlush(despliegue);

        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();

        // Update the despliegue using partial update
        Despliegue partialUpdatedDespliegue = new Despliegue();
        partialUpdatedDespliegue.setId(despliegue.getId());

        partialUpdatedDespliegue.regionName(UPDATED_REGION_NAME);

        restDespliegueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDespliegue.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDespliegue))
            )
            .andExpect(status().isOk());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
        Despliegue testDespliegue = despliegueList.get(despliegueList.size() - 1);
        assertThat(testDespliegue.getRegionName()).isEqualTo(UPDATED_REGION_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingDespliegue() throws Exception {
        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();
        despliegue.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDespliegueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, despliegue.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(despliegue))
            )
            .andExpect(status().isBadRequest());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDespliegue() throws Exception {
        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();
        despliegue.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDespliegueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(despliegue))
            )
            .andExpect(status().isBadRequest());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDespliegue() throws Exception {
        int databaseSizeBeforeUpdate = despliegueRepository.findAll().size();
        despliegue.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDespliegueMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(despliegue))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Despliegue in the database
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDespliegue() throws Exception {
        // Initialize the database
        despliegueRepository.saveAndFlush(despliegue);

        int databaseSizeBeforeDelete = despliegueRepository.findAll().size();

        // Delete the despliegue
        restDespliegueMockMvc
            .perform(delete(ENTITY_API_URL_ID, despliegue.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Despliegue> despliegueList = despliegueRepository.findAll();
        assertThat(despliegueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
