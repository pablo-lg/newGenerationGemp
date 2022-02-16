package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.Parametros;
import ar.teco.repository.ParametrosRepository;
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
 * Integration tests for the {@link ParametrosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ParametrosResourceIT {

    private static final String DEFAULT_CLAVE = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE = "BBBBBBBBBB";

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/parametros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ParametrosRepository parametrosRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParametrosMockMvc;

    private Parametros parametros;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parametros createEntity(EntityManager em) {
        Parametros parametros = new Parametros().clave(DEFAULT_CLAVE).valor(DEFAULT_VALOR);
        return parametros;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parametros createUpdatedEntity(EntityManager em) {
        Parametros parametros = new Parametros().clave(UPDATED_CLAVE).valor(UPDATED_VALOR);
        return parametros;
    }

    @BeforeEach
    public void initTest() {
        parametros = createEntity(em);
    }

    @Test
    @Transactional
    void createParametros() throws Exception {
        int databaseSizeBeforeCreate = parametrosRepository.findAll().size();
        // Create the Parametros
        restParametrosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isCreated());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeCreate + 1);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getClave()).isEqualTo(DEFAULT_CLAVE);
        assertThat(testParametros.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    void createParametrosWithExistingId() throws Exception {
        // Create the Parametros with an existing ID
        parametros.setId(1L);

        int databaseSizeBeforeCreate = parametrosRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restParametrosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllParametros() throws Exception {
        // Initialize the database
        parametrosRepository.saveAndFlush(parametros);

        // Get all the parametrosList
        restParametrosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parametros.getId().intValue())))
            .andExpect(jsonPath("$.[*].clave").value(hasItem(DEFAULT_CLAVE)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR)));
    }

    @Test
    @Transactional
    void getParametros() throws Exception {
        // Initialize the database
        parametrosRepository.saveAndFlush(parametros);

        // Get the parametros
        restParametrosMockMvc
            .perform(get(ENTITY_API_URL_ID, parametros.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parametros.getId().intValue()))
            .andExpect(jsonPath("$.clave").value(DEFAULT_CLAVE))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR));
    }

    @Test
    @Transactional
    void getNonExistingParametros() throws Exception {
        // Get the parametros
        restParametrosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewParametros() throws Exception {
        // Initialize the database
        parametrosRepository.saveAndFlush(parametros);

        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();

        // Update the parametros
        Parametros updatedParametros = parametrosRepository.findById(parametros.getId()).get();
        // Disconnect from session so that the updates on updatedParametros are not directly saved in db
        em.detach(updatedParametros);
        updatedParametros.clave(UPDATED_CLAVE).valor(UPDATED_VALOR);

        restParametrosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedParametros.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedParametros))
            )
            .andExpect(status().isOk());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getClave()).isEqualTo(UPDATED_CLAVE);
        assertThat(testParametros.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    void putNonExistingParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, parametros.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parametros)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateParametrosWithPatch() throws Exception {
        // Initialize the database
        parametrosRepository.saveAndFlush(parametros);

        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();

        // Update the parametros using partial update
        Parametros partialUpdatedParametros = new Parametros();
        partialUpdatedParametros.setId(parametros.getId());

        partialUpdatedParametros.clave(UPDATED_CLAVE).valor(UPDATED_VALOR);

        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedParametros.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedParametros))
            )
            .andExpect(status().isOk());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getClave()).isEqualTo(UPDATED_CLAVE);
        assertThat(testParametros.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    void fullUpdateParametrosWithPatch() throws Exception {
        // Initialize the database
        parametrosRepository.saveAndFlush(parametros);

        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();

        // Update the parametros using partial update
        Parametros partialUpdatedParametros = new Parametros();
        partialUpdatedParametros.setId(parametros.getId());

        partialUpdatedParametros.clave(UPDATED_CLAVE).valor(UPDATED_VALOR);

        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedParametros.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedParametros))
            )
            .andExpect(status().isOk());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
        Parametros testParametros = parametrosList.get(parametrosList.size() - 1);
        assertThat(testParametros.getClave()).isEqualTo(UPDATED_CLAVE);
        assertThat(testParametros.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    void patchNonExistingParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, parametros.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamParametros() throws Exception {
        int databaseSizeBeforeUpdate = parametrosRepository.findAll().size();
        parametros.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParametrosMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(parametros))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Parametros in the database
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteParametros() throws Exception {
        // Initialize the database
        parametrosRepository.saveAndFlush(parametros);

        int databaseSizeBeforeDelete = parametrosRepository.findAll().size();

        // Delete the parametros
        restParametrosMockMvc
            .perform(delete(ENTITY_API_URL_ID, parametros.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Parametros> parametrosList = parametrosRepository.findAll();
        assertThat(parametrosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
