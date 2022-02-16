package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.UsuGemp;
import ar.teco.repository.UsuGempRepository;
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
 * Integration tests for the {@link UsuGempResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UsuGempResourceIT {

    private static final String DEFAULT_USU = "AAAAAAAAAA";
    private static final String UPDATED_USU = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PERFILES = "AAAAAAAAAA";
    private static final String UPDATED_PERFILES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/usu-gemps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UsuGempRepository usuGempRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsuGempMockMvc;

    private UsuGemp usuGemp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuGemp createEntity(EntityManager em) {
        UsuGemp usuGemp = new UsuGemp()
            .usu(DEFAULT_USU)
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .email(DEFAULT_EMAIL)
            .perfiles(DEFAULT_PERFILES);
        return usuGemp;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuGemp createUpdatedEntity(EntityManager em) {
        UsuGemp usuGemp = new UsuGemp()
            .usu(UPDATED_USU)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .email(UPDATED_EMAIL)
            .perfiles(UPDATED_PERFILES);
        return usuGemp;
    }

    @BeforeEach
    public void initTest() {
        usuGemp = createEntity(em);
    }

    @Test
    @Transactional
    void createUsuGemp() throws Exception {
        int databaseSizeBeforeCreate = usuGempRepository.findAll().size();
        // Create the UsuGemp
        restUsuGempMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(usuGemp)))
            .andExpect(status().isCreated());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeCreate + 1);
        UsuGemp testUsuGemp = usuGempList.get(usuGempList.size() - 1);
        assertThat(testUsuGemp.getUsu()).isEqualTo(DEFAULT_USU);
        assertThat(testUsuGemp.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testUsuGemp.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testUsuGemp.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUsuGemp.getPerfiles()).isEqualTo(DEFAULT_PERFILES);
    }

    @Test
    @Transactional
    void createUsuGempWithExistingId() throws Exception {
        // Create the UsuGemp with an existing ID
        usuGemp.setId(1L);

        int databaseSizeBeforeCreate = usuGempRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsuGempMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(usuGemp)))
            .andExpect(status().isBadRequest());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUsuIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuGempRepository.findAll().size();
        // set the field null
        usuGemp.setUsu(null);

        // Create the UsuGemp, which fails.

        restUsuGempMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(usuGemp)))
            .andExpect(status().isBadRequest());

        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllUsuGemps() throws Exception {
        // Initialize the database
        usuGempRepository.saveAndFlush(usuGemp);

        // Get all the usuGempList
        restUsuGempMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuGemp.getId().intValue())))
            .andExpect(jsonPath("$.[*].usu").value(hasItem(DEFAULT_USU)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].perfiles").value(hasItem(DEFAULT_PERFILES)));
    }

    @Test
    @Transactional
    void getUsuGemp() throws Exception {
        // Initialize the database
        usuGempRepository.saveAndFlush(usuGemp);

        // Get the usuGemp
        restUsuGempMockMvc
            .perform(get(ENTITY_API_URL_ID, usuGemp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usuGemp.getId().intValue()))
            .andExpect(jsonPath("$.usu").value(DEFAULT_USU))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.perfiles").value(DEFAULT_PERFILES));
    }

    @Test
    @Transactional
    void getNonExistingUsuGemp() throws Exception {
        // Get the usuGemp
        restUsuGempMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUsuGemp() throws Exception {
        // Initialize the database
        usuGempRepository.saveAndFlush(usuGemp);

        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();

        // Update the usuGemp
        UsuGemp updatedUsuGemp = usuGempRepository.findById(usuGemp.getId()).get();
        // Disconnect from session so that the updates on updatedUsuGemp are not directly saved in db
        em.detach(updatedUsuGemp);
        updatedUsuGemp.usu(UPDATED_USU).nombre(UPDATED_NOMBRE).apellido(UPDATED_APELLIDO).email(UPDATED_EMAIL).perfiles(UPDATED_PERFILES);

        restUsuGempMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUsuGemp.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUsuGemp))
            )
            .andExpect(status().isOk());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
        UsuGemp testUsuGemp = usuGempList.get(usuGempList.size() - 1);
        assertThat(testUsuGemp.getUsu()).isEqualTo(UPDATED_USU);
        assertThat(testUsuGemp.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testUsuGemp.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testUsuGemp.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUsuGemp.getPerfiles()).isEqualTo(UPDATED_PERFILES);
    }

    @Test
    @Transactional
    void putNonExistingUsuGemp() throws Exception {
        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();
        usuGemp.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsuGempMockMvc
            .perform(
                put(ENTITY_API_URL_ID, usuGemp.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(usuGemp))
            )
            .andExpect(status().isBadRequest());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUsuGemp() throws Exception {
        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();
        usuGemp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUsuGempMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(usuGemp))
            )
            .andExpect(status().isBadRequest());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUsuGemp() throws Exception {
        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();
        usuGemp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUsuGempMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(usuGemp)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUsuGempWithPatch() throws Exception {
        // Initialize the database
        usuGempRepository.saveAndFlush(usuGemp);

        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();

        // Update the usuGemp using partial update
        UsuGemp partialUpdatedUsuGemp = new UsuGemp();
        partialUpdatedUsuGemp.setId(usuGemp.getId());

        partialUpdatedUsuGemp
            .usu(UPDATED_USU)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .email(UPDATED_EMAIL)
            .perfiles(UPDATED_PERFILES);

        restUsuGempMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUsuGemp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUsuGemp))
            )
            .andExpect(status().isOk());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
        UsuGemp testUsuGemp = usuGempList.get(usuGempList.size() - 1);
        assertThat(testUsuGemp.getUsu()).isEqualTo(UPDATED_USU);
        assertThat(testUsuGemp.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testUsuGemp.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testUsuGemp.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUsuGemp.getPerfiles()).isEqualTo(UPDATED_PERFILES);
    }

    @Test
    @Transactional
    void fullUpdateUsuGempWithPatch() throws Exception {
        // Initialize the database
        usuGempRepository.saveAndFlush(usuGemp);

        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();

        // Update the usuGemp using partial update
        UsuGemp partialUpdatedUsuGemp = new UsuGemp();
        partialUpdatedUsuGemp.setId(usuGemp.getId());

        partialUpdatedUsuGemp
            .usu(UPDATED_USU)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .email(UPDATED_EMAIL)
            .perfiles(UPDATED_PERFILES);

        restUsuGempMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUsuGemp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUsuGemp))
            )
            .andExpect(status().isOk());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
        UsuGemp testUsuGemp = usuGempList.get(usuGempList.size() - 1);
        assertThat(testUsuGemp.getUsu()).isEqualTo(UPDATED_USU);
        assertThat(testUsuGemp.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testUsuGemp.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testUsuGemp.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUsuGemp.getPerfiles()).isEqualTo(UPDATED_PERFILES);
    }

    @Test
    @Transactional
    void patchNonExistingUsuGemp() throws Exception {
        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();
        usuGemp.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsuGempMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, usuGemp.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(usuGemp))
            )
            .andExpect(status().isBadRequest());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUsuGemp() throws Exception {
        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();
        usuGemp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUsuGempMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(usuGemp))
            )
            .andExpect(status().isBadRequest());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUsuGemp() throws Exception {
        int databaseSizeBeforeUpdate = usuGempRepository.findAll().size();
        usuGemp.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUsuGempMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(usuGemp)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UsuGemp in the database
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUsuGemp() throws Exception {
        // Initialize the database
        usuGempRepository.saveAndFlush(usuGemp);

        int databaseSizeBeforeDelete = usuGempRepository.findAll().size();

        // Delete the usuGemp
        restUsuGempMockMvc
            .perform(delete(ENTITY_API_URL_ID, usuGemp.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UsuGemp> usuGempList = usuGempRepository.findAll();
        assertThat(usuGempList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
