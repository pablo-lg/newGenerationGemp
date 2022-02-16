package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.Direccion;
import ar.teco.repository.DireccionRepository;
import ar.teco.service.criteria.DireccionCriteria;
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
 * Integration tests for the {@link DireccionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DireccionResourceIT {

    private static final String DEFAULT_IDENTIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_PAIS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA = "BBBBBBBBBB";

    private static final String DEFAULT_PARTIDO = "AAAAAAAAAA";
    private static final String UPDATED_PARTIDO = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_LOCALIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_CALLE = "AAAAAAAAAA";
    private static final String UPDATED_CALLE = "BBBBBBBBBB";

    private static final Long DEFAULT_ALTURA = 1L;
    private static final Long UPDATED_ALTURA = 2L;
    private static final Long SMALLER_ALTURA = 1L - 1L;

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_SUBREGION = "AAAAAAAAAA";
    private static final String UPDATED_SUBREGION = "BBBBBBBBBB";

    private static final String DEFAULT_HUB = "AAAAAAAAAA";
    private static final String UPDATED_HUB = "BBBBBBBBBB";

    private static final String DEFAULT_BARRIOS_ESPECIALES = "AAAAAAAAAA";
    private static final String UPDATED_BARRIOS_ESPECIALES = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_CALLE = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_CALLE = "BBBBBBBBBB";

    private static final String DEFAULT_ZONA_COMPETENCIA = "AAAAAAAAAA";
    private static final String UPDATED_ZONA_COMPETENCIA = "BBBBBBBBBB";

    private static final String DEFAULT_INTERSECTION_LEFT = "AAAAAAAAAA";
    private static final String UPDATED_INTERSECTION_LEFT = "BBBBBBBBBB";

    private static final String DEFAULT_INTERSECTION_RIGHT = "AAAAAAAAAA";
    private static final String UPDATED_INTERSECTION_RIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_STREET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STREET_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUD = "AAAAAAAAAA";
    private static final String UPDATED_LATITUD = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUD = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUD = "BBBBBBBBBB";

    private static final String DEFAULT_ELEMENTOS_DE_RED = "AAAAAAAAAA";
    private static final String UPDATED_ELEMENTOS_DE_RED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/direccions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDireccionMockMvc;

    private Direccion direccion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Direccion createEntity(EntityManager em) {
        Direccion direccion = new Direccion()
            .identification(DEFAULT_IDENTIFICATION)
            .pais(DEFAULT_PAIS)
            .provincia(DEFAULT_PROVINCIA)
            .partido(DEFAULT_PARTIDO)
            .localidad(DEFAULT_LOCALIDAD)
            .calle(DEFAULT_CALLE)
            .altura(DEFAULT_ALTURA)
            .region(DEFAULT_REGION)
            .subregion(DEFAULT_SUBREGION)
            .hub(DEFAULT_HUB)
            .barriosEspeciales(DEFAULT_BARRIOS_ESPECIALES)
            .codigoPostal(DEFAULT_CODIGO_POSTAL)
            .tipoCalle(DEFAULT_TIPO_CALLE)
            .zonaCompetencia(DEFAULT_ZONA_COMPETENCIA)
            .intersectionLeft(DEFAULT_INTERSECTION_LEFT)
            .intersectionRight(DEFAULT_INTERSECTION_RIGHT)
            .streetType(DEFAULT_STREET_TYPE)
            .latitud(DEFAULT_LATITUD)
            .longitud(DEFAULT_LONGITUD)
            .elementosDeRed(DEFAULT_ELEMENTOS_DE_RED);
        return direccion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Direccion createUpdatedEntity(EntityManager em) {
        Direccion direccion = new Direccion()
            .identification(UPDATED_IDENTIFICATION)
            .pais(UPDATED_PAIS)
            .provincia(UPDATED_PROVINCIA)
            .partido(UPDATED_PARTIDO)
            .localidad(UPDATED_LOCALIDAD)
            .calle(UPDATED_CALLE)
            .altura(UPDATED_ALTURA)
            .region(UPDATED_REGION)
            .subregion(UPDATED_SUBREGION)
            .hub(UPDATED_HUB)
            .barriosEspeciales(UPDATED_BARRIOS_ESPECIALES)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .tipoCalle(UPDATED_TIPO_CALLE)
            .zonaCompetencia(UPDATED_ZONA_COMPETENCIA)
            .intersectionLeft(UPDATED_INTERSECTION_LEFT)
            .intersectionRight(UPDATED_INTERSECTION_RIGHT)
            .streetType(UPDATED_STREET_TYPE)
            .latitud(UPDATED_LATITUD)
            .longitud(UPDATED_LONGITUD)
            .elementosDeRed(UPDATED_ELEMENTOS_DE_RED);
        return direccion;
    }

    @BeforeEach
    public void initTest() {
        direccion = createEntity(em);
    }

    @Test
    @Transactional
    void createDireccion() throws Exception {
        int databaseSizeBeforeCreate = direccionRepository.findAll().size();
        // Create the Direccion
        restDireccionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isCreated());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeCreate + 1);
        Direccion testDireccion = direccionList.get(direccionList.size() - 1);
        assertThat(testDireccion.getIdentification()).isEqualTo(DEFAULT_IDENTIFICATION);
        assertThat(testDireccion.getPais()).isEqualTo(DEFAULT_PAIS);
        assertThat(testDireccion.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
        assertThat(testDireccion.getPartido()).isEqualTo(DEFAULT_PARTIDO);
        assertThat(testDireccion.getLocalidad()).isEqualTo(DEFAULT_LOCALIDAD);
        assertThat(testDireccion.getCalle()).isEqualTo(DEFAULT_CALLE);
        assertThat(testDireccion.getAltura()).isEqualTo(DEFAULT_ALTURA);
        assertThat(testDireccion.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testDireccion.getSubregion()).isEqualTo(DEFAULT_SUBREGION);
        assertThat(testDireccion.getHub()).isEqualTo(DEFAULT_HUB);
        assertThat(testDireccion.getBarriosEspeciales()).isEqualTo(DEFAULT_BARRIOS_ESPECIALES);
        assertThat(testDireccion.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
        assertThat(testDireccion.getTipoCalle()).isEqualTo(DEFAULT_TIPO_CALLE);
        assertThat(testDireccion.getZonaCompetencia()).isEqualTo(DEFAULT_ZONA_COMPETENCIA);
        assertThat(testDireccion.getIntersectionLeft()).isEqualTo(DEFAULT_INTERSECTION_LEFT);
        assertThat(testDireccion.getIntersectionRight()).isEqualTo(DEFAULT_INTERSECTION_RIGHT);
        assertThat(testDireccion.getStreetType()).isEqualTo(DEFAULT_STREET_TYPE);
        assertThat(testDireccion.getLatitud()).isEqualTo(DEFAULT_LATITUD);
        assertThat(testDireccion.getLongitud()).isEqualTo(DEFAULT_LONGITUD);
        assertThat(testDireccion.getElementosDeRed()).isEqualTo(DEFAULT_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void createDireccionWithExistingId() throws Exception {
        // Create the Direccion with an existing ID
        direccion.setId(1L);

        int databaseSizeBeforeCreate = direccionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDireccionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPaisIsRequired() throws Exception {
        int databaseSizeBeforeTest = direccionRepository.findAll().size();
        // set the field null
        direccion.setPais(null);

        // Create the Direccion, which fails.

        restDireccionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProvinciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = direccionRepository.findAll().size();
        // set the field null
        direccion.setProvincia(null);

        // Create the Direccion, which fails.

        restDireccionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPartidoIsRequired() throws Exception {
        int databaseSizeBeforeTest = direccionRepository.findAll().size();
        // set the field null
        direccion.setPartido(null);

        // Create the Direccion, which fails.

        restDireccionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLocalidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = direccionRepository.findAll().size();
        // set the field null
        direccion.setLocalidad(null);

        // Create the Direccion, which fails.

        restDireccionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCalleIsRequired() throws Exception {
        int databaseSizeBeforeTest = direccionRepository.findAll().size();
        // set the field null
        direccion.setCalle(null);

        // Create the Direccion, which fails.

        restDireccionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAlturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = direccionRepository.findAll().size();
        // set the field null
        direccion.setAltura(null);

        // Create the Direccion, which fails.

        restDireccionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDireccions() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList
        restDireccionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(direccion.getId().intValue())))
            .andExpect(jsonPath("$.[*].identification").value(hasItem(DEFAULT_IDENTIFICATION)))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA)))
            .andExpect(jsonPath("$.[*].partido").value(hasItem(DEFAULT_PARTIDO)))
            .andExpect(jsonPath("$.[*].localidad").value(hasItem(DEFAULT_LOCALIDAD)))
            .andExpect(jsonPath("$.[*].calle").value(hasItem(DEFAULT_CALLE)))
            .andExpect(jsonPath("$.[*].altura").value(hasItem(DEFAULT_ALTURA.intValue())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].subregion").value(hasItem(DEFAULT_SUBREGION)))
            .andExpect(jsonPath("$.[*].hub").value(hasItem(DEFAULT_HUB)))
            .andExpect(jsonPath("$.[*].barriosEspeciales").value(hasItem(DEFAULT_BARRIOS_ESPECIALES)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)))
            .andExpect(jsonPath("$.[*].tipoCalle").value(hasItem(DEFAULT_TIPO_CALLE)))
            .andExpect(jsonPath("$.[*].zonaCompetencia").value(hasItem(DEFAULT_ZONA_COMPETENCIA)))
            .andExpect(jsonPath("$.[*].intersectionLeft").value(hasItem(DEFAULT_INTERSECTION_LEFT)))
            .andExpect(jsonPath("$.[*].intersectionRight").value(hasItem(DEFAULT_INTERSECTION_RIGHT)))
            .andExpect(jsonPath("$.[*].streetType").value(hasItem(DEFAULT_STREET_TYPE)))
            .andExpect(jsonPath("$.[*].latitud").value(hasItem(DEFAULT_LATITUD)))
            .andExpect(jsonPath("$.[*].longitud").value(hasItem(DEFAULT_LONGITUD)))
            .andExpect(jsonPath("$.[*].elementosDeRed").value(hasItem(DEFAULT_ELEMENTOS_DE_RED)));
    }

    @Test
    @Transactional
    void getDireccion() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get the direccion
        restDireccionMockMvc
            .perform(get(ENTITY_API_URL_ID, direccion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(direccion.getId().intValue()))
            .andExpect(jsonPath("$.identification").value(DEFAULT_IDENTIFICATION))
            .andExpect(jsonPath("$.pais").value(DEFAULT_PAIS))
            .andExpect(jsonPath("$.provincia").value(DEFAULT_PROVINCIA))
            .andExpect(jsonPath("$.partido").value(DEFAULT_PARTIDO))
            .andExpect(jsonPath("$.localidad").value(DEFAULT_LOCALIDAD))
            .andExpect(jsonPath("$.calle").value(DEFAULT_CALLE))
            .andExpect(jsonPath("$.altura").value(DEFAULT_ALTURA.intValue()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.subregion").value(DEFAULT_SUBREGION))
            .andExpect(jsonPath("$.hub").value(DEFAULT_HUB))
            .andExpect(jsonPath("$.barriosEspeciales").value(DEFAULT_BARRIOS_ESPECIALES))
            .andExpect(jsonPath("$.codigoPostal").value(DEFAULT_CODIGO_POSTAL))
            .andExpect(jsonPath("$.tipoCalle").value(DEFAULT_TIPO_CALLE))
            .andExpect(jsonPath("$.zonaCompetencia").value(DEFAULT_ZONA_COMPETENCIA))
            .andExpect(jsonPath("$.intersectionLeft").value(DEFAULT_INTERSECTION_LEFT))
            .andExpect(jsonPath("$.intersectionRight").value(DEFAULT_INTERSECTION_RIGHT))
            .andExpect(jsonPath("$.streetType").value(DEFAULT_STREET_TYPE))
            .andExpect(jsonPath("$.latitud").value(DEFAULT_LATITUD))
            .andExpect(jsonPath("$.longitud").value(DEFAULT_LONGITUD))
            .andExpect(jsonPath("$.elementosDeRed").value(DEFAULT_ELEMENTOS_DE_RED));
    }

    @Test
    @Transactional
    void getDireccionsByIdFiltering() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        Long id = direccion.getId();

        defaultDireccionShouldBeFound("id.equals=" + id);
        defaultDireccionShouldNotBeFound("id.notEquals=" + id);

        defaultDireccionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDireccionShouldNotBeFound("id.greaterThan=" + id);

        defaultDireccionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDireccionShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDireccionsByIdentificationIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where identification equals to DEFAULT_IDENTIFICATION
        defaultDireccionShouldBeFound("identification.equals=" + DEFAULT_IDENTIFICATION);

        // Get all the direccionList where identification equals to UPDATED_IDENTIFICATION
        defaultDireccionShouldNotBeFound("identification.equals=" + UPDATED_IDENTIFICATION);
    }

    @Test
    @Transactional
    void getAllDireccionsByIdentificationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where identification not equals to DEFAULT_IDENTIFICATION
        defaultDireccionShouldNotBeFound("identification.notEquals=" + DEFAULT_IDENTIFICATION);

        // Get all the direccionList where identification not equals to UPDATED_IDENTIFICATION
        defaultDireccionShouldBeFound("identification.notEquals=" + UPDATED_IDENTIFICATION);
    }

    @Test
    @Transactional
    void getAllDireccionsByIdentificationIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where identification in DEFAULT_IDENTIFICATION or UPDATED_IDENTIFICATION
        defaultDireccionShouldBeFound("identification.in=" + DEFAULT_IDENTIFICATION + "," + UPDATED_IDENTIFICATION);

        // Get all the direccionList where identification equals to UPDATED_IDENTIFICATION
        defaultDireccionShouldNotBeFound("identification.in=" + UPDATED_IDENTIFICATION);
    }

    @Test
    @Transactional
    void getAllDireccionsByIdentificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where identification is not null
        defaultDireccionShouldBeFound("identification.specified=true");

        // Get all the direccionList where identification is null
        defaultDireccionShouldNotBeFound("identification.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByIdentificationContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where identification contains DEFAULT_IDENTIFICATION
        defaultDireccionShouldBeFound("identification.contains=" + DEFAULT_IDENTIFICATION);

        // Get all the direccionList where identification contains UPDATED_IDENTIFICATION
        defaultDireccionShouldNotBeFound("identification.contains=" + UPDATED_IDENTIFICATION);
    }

    @Test
    @Transactional
    void getAllDireccionsByIdentificationNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where identification does not contain DEFAULT_IDENTIFICATION
        defaultDireccionShouldNotBeFound("identification.doesNotContain=" + DEFAULT_IDENTIFICATION);

        // Get all the direccionList where identification does not contain UPDATED_IDENTIFICATION
        defaultDireccionShouldBeFound("identification.doesNotContain=" + UPDATED_IDENTIFICATION);
    }

    @Test
    @Transactional
    void getAllDireccionsByPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where pais equals to DEFAULT_PAIS
        defaultDireccionShouldBeFound("pais.equals=" + DEFAULT_PAIS);

        // Get all the direccionList where pais equals to UPDATED_PAIS
        defaultDireccionShouldNotBeFound("pais.equals=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    void getAllDireccionsByPaisIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where pais not equals to DEFAULT_PAIS
        defaultDireccionShouldNotBeFound("pais.notEquals=" + DEFAULT_PAIS);

        // Get all the direccionList where pais not equals to UPDATED_PAIS
        defaultDireccionShouldBeFound("pais.notEquals=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    void getAllDireccionsByPaisIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where pais in DEFAULT_PAIS or UPDATED_PAIS
        defaultDireccionShouldBeFound("pais.in=" + DEFAULT_PAIS + "," + UPDATED_PAIS);

        // Get all the direccionList where pais equals to UPDATED_PAIS
        defaultDireccionShouldNotBeFound("pais.in=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    void getAllDireccionsByPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where pais is not null
        defaultDireccionShouldBeFound("pais.specified=true");

        // Get all the direccionList where pais is null
        defaultDireccionShouldNotBeFound("pais.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByPaisContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where pais contains DEFAULT_PAIS
        defaultDireccionShouldBeFound("pais.contains=" + DEFAULT_PAIS);

        // Get all the direccionList where pais contains UPDATED_PAIS
        defaultDireccionShouldNotBeFound("pais.contains=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    void getAllDireccionsByPaisNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where pais does not contain DEFAULT_PAIS
        defaultDireccionShouldNotBeFound("pais.doesNotContain=" + DEFAULT_PAIS);

        // Get all the direccionList where pais does not contain UPDATED_PAIS
        defaultDireccionShouldBeFound("pais.doesNotContain=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    void getAllDireccionsByProvinciaIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where provincia equals to DEFAULT_PROVINCIA
        defaultDireccionShouldBeFound("provincia.equals=" + DEFAULT_PROVINCIA);

        // Get all the direccionList where provincia equals to UPDATED_PROVINCIA
        defaultDireccionShouldNotBeFound("provincia.equals=" + UPDATED_PROVINCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByProvinciaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where provincia not equals to DEFAULT_PROVINCIA
        defaultDireccionShouldNotBeFound("provincia.notEquals=" + DEFAULT_PROVINCIA);

        // Get all the direccionList where provincia not equals to UPDATED_PROVINCIA
        defaultDireccionShouldBeFound("provincia.notEquals=" + UPDATED_PROVINCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByProvinciaIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where provincia in DEFAULT_PROVINCIA or UPDATED_PROVINCIA
        defaultDireccionShouldBeFound("provincia.in=" + DEFAULT_PROVINCIA + "," + UPDATED_PROVINCIA);

        // Get all the direccionList where provincia equals to UPDATED_PROVINCIA
        defaultDireccionShouldNotBeFound("provincia.in=" + UPDATED_PROVINCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByProvinciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where provincia is not null
        defaultDireccionShouldBeFound("provincia.specified=true");

        // Get all the direccionList where provincia is null
        defaultDireccionShouldNotBeFound("provincia.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByProvinciaContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where provincia contains DEFAULT_PROVINCIA
        defaultDireccionShouldBeFound("provincia.contains=" + DEFAULT_PROVINCIA);

        // Get all the direccionList where provincia contains UPDATED_PROVINCIA
        defaultDireccionShouldNotBeFound("provincia.contains=" + UPDATED_PROVINCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByProvinciaNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where provincia does not contain DEFAULT_PROVINCIA
        defaultDireccionShouldNotBeFound("provincia.doesNotContain=" + DEFAULT_PROVINCIA);

        // Get all the direccionList where provincia does not contain UPDATED_PROVINCIA
        defaultDireccionShouldBeFound("provincia.doesNotContain=" + UPDATED_PROVINCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByPartidoIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where partido equals to DEFAULT_PARTIDO
        defaultDireccionShouldBeFound("partido.equals=" + DEFAULT_PARTIDO);

        // Get all the direccionList where partido equals to UPDATED_PARTIDO
        defaultDireccionShouldNotBeFound("partido.equals=" + UPDATED_PARTIDO);
    }

    @Test
    @Transactional
    void getAllDireccionsByPartidoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where partido not equals to DEFAULT_PARTIDO
        defaultDireccionShouldNotBeFound("partido.notEquals=" + DEFAULT_PARTIDO);

        // Get all the direccionList where partido not equals to UPDATED_PARTIDO
        defaultDireccionShouldBeFound("partido.notEquals=" + UPDATED_PARTIDO);
    }

    @Test
    @Transactional
    void getAllDireccionsByPartidoIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where partido in DEFAULT_PARTIDO or UPDATED_PARTIDO
        defaultDireccionShouldBeFound("partido.in=" + DEFAULT_PARTIDO + "," + UPDATED_PARTIDO);

        // Get all the direccionList where partido equals to UPDATED_PARTIDO
        defaultDireccionShouldNotBeFound("partido.in=" + UPDATED_PARTIDO);
    }

    @Test
    @Transactional
    void getAllDireccionsByPartidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where partido is not null
        defaultDireccionShouldBeFound("partido.specified=true");

        // Get all the direccionList where partido is null
        defaultDireccionShouldNotBeFound("partido.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByPartidoContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where partido contains DEFAULT_PARTIDO
        defaultDireccionShouldBeFound("partido.contains=" + DEFAULT_PARTIDO);

        // Get all the direccionList where partido contains UPDATED_PARTIDO
        defaultDireccionShouldNotBeFound("partido.contains=" + UPDATED_PARTIDO);
    }

    @Test
    @Transactional
    void getAllDireccionsByPartidoNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where partido does not contain DEFAULT_PARTIDO
        defaultDireccionShouldNotBeFound("partido.doesNotContain=" + DEFAULT_PARTIDO);

        // Get all the direccionList where partido does not contain UPDATED_PARTIDO
        defaultDireccionShouldBeFound("partido.doesNotContain=" + UPDATED_PARTIDO);
    }

    @Test
    @Transactional
    void getAllDireccionsByLocalidadIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where localidad equals to DEFAULT_LOCALIDAD
        defaultDireccionShouldBeFound("localidad.equals=" + DEFAULT_LOCALIDAD);

        // Get all the direccionList where localidad equals to UPDATED_LOCALIDAD
        defaultDireccionShouldNotBeFound("localidad.equals=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLocalidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where localidad not equals to DEFAULT_LOCALIDAD
        defaultDireccionShouldNotBeFound("localidad.notEquals=" + DEFAULT_LOCALIDAD);

        // Get all the direccionList where localidad not equals to UPDATED_LOCALIDAD
        defaultDireccionShouldBeFound("localidad.notEquals=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLocalidadIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where localidad in DEFAULT_LOCALIDAD or UPDATED_LOCALIDAD
        defaultDireccionShouldBeFound("localidad.in=" + DEFAULT_LOCALIDAD + "," + UPDATED_LOCALIDAD);

        // Get all the direccionList where localidad equals to UPDATED_LOCALIDAD
        defaultDireccionShouldNotBeFound("localidad.in=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLocalidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where localidad is not null
        defaultDireccionShouldBeFound("localidad.specified=true");

        // Get all the direccionList where localidad is null
        defaultDireccionShouldNotBeFound("localidad.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByLocalidadContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where localidad contains DEFAULT_LOCALIDAD
        defaultDireccionShouldBeFound("localidad.contains=" + DEFAULT_LOCALIDAD);

        // Get all the direccionList where localidad contains UPDATED_LOCALIDAD
        defaultDireccionShouldNotBeFound("localidad.contains=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLocalidadNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where localidad does not contain DEFAULT_LOCALIDAD
        defaultDireccionShouldNotBeFound("localidad.doesNotContain=" + DEFAULT_LOCALIDAD);

        // Get all the direccionList where localidad does not contain UPDATED_LOCALIDAD
        defaultDireccionShouldBeFound("localidad.doesNotContain=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    void getAllDireccionsByCalleIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where calle equals to DEFAULT_CALLE
        defaultDireccionShouldBeFound("calle.equals=" + DEFAULT_CALLE);

        // Get all the direccionList where calle equals to UPDATED_CALLE
        defaultDireccionShouldNotBeFound("calle.equals=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByCalleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where calle not equals to DEFAULT_CALLE
        defaultDireccionShouldNotBeFound("calle.notEquals=" + DEFAULT_CALLE);

        // Get all the direccionList where calle not equals to UPDATED_CALLE
        defaultDireccionShouldBeFound("calle.notEquals=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByCalleIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where calle in DEFAULT_CALLE or UPDATED_CALLE
        defaultDireccionShouldBeFound("calle.in=" + DEFAULT_CALLE + "," + UPDATED_CALLE);

        // Get all the direccionList where calle equals to UPDATED_CALLE
        defaultDireccionShouldNotBeFound("calle.in=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByCalleIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where calle is not null
        defaultDireccionShouldBeFound("calle.specified=true");

        // Get all the direccionList where calle is null
        defaultDireccionShouldNotBeFound("calle.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByCalleContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where calle contains DEFAULT_CALLE
        defaultDireccionShouldBeFound("calle.contains=" + DEFAULT_CALLE);

        // Get all the direccionList where calle contains UPDATED_CALLE
        defaultDireccionShouldNotBeFound("calle.contains=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByCalleNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where calle does not contain DEFAULT_CALLE
        defaultDireccionShouldNotBeFound("calle.doesNotContain=" + DEFAULT_CALLE);

        // Get all the direccionList where calle does not contain UPDATED_CALLE
        defaultDireccionShouldBeFound("calle.doesNotContain=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByAlturaIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where altura equals to DEFAULT_ALTURA
        defaultDireccionShouldBeFound("altura.equals=" + DEFAULT_ALTURA);

        // Get all the direccionList where altura equals to UPDATED_ALTURA
        defaultDireccionShouldNotBeFound("altura.equals=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    void getAllDireccionsByAlturaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where altura not equals to DEFAULT_ALTURA
        defaultDireccionShouldNotBeFound("altura.notEquals=" + DEFAULT_ALTURA);

        // Get all the direccionList where altura not equals to UPDATED_ALTURA
        defaultDireccionShouldBeFound("altura.notEquals=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    void getAllDireccionsByAlturaIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where altura in DEFAULT_ALTURA or UPDATED_ALTURA
        defaultDireccionShouldBeFound("altura.in=" + DEFAULT_ALTURA + "," + UPDATED_ALTURA);

        // Get all the direccionList where altura equals to UPDATED_ALTURA
        defaultDireccionShouldNotBeFound("altura.in=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    void getAllDireccionsByAlturaIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where altura is not null
        defaultDireccionShouldBeFound("altura.specified=true");

        // Get all the direccionList where altura is null
        defaultDireccionShouldNotBeFound("altura.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByAlturaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where altura is greater than or equal to DEFAULT_ALTURA
        defaultDireccionShouldBeFound("altura.greaterThanOrEqual=" + DEFAULT_ALTURA);

        // Get all the direccionList where altura is greater than or equal to UPDATED_ALTURA
        defaultDireccionShouldNotBeFound("altura.greaterThanOrEqual=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    void getAllDireccionsByAlturaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where altura is less than or equal to DEFAULT_ALTURA
        defaultDireccionShouldBeFound("altura.lessThanOrEqual=" + DEFAULT_ALTURA);

        // Get all the direccionList where altura is less than or equal to SMALLER_ALTURA
        defaultDireccionShouldNotBeFound("altura.lessThanOrEqual=" + SMALLER_ALTURA);
    }

    @Test
    @Transactional
    void getAllDireccionsByAlturaIsLessThanSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where altura is less than DEFAULT_ALTURA
        defaultDireccionShouldNotBeFound("altura.lessThan=" + DEFAULT_ALTURA);

        // Get all the direccionList where altura is less than UPDATED_ALTURA
        defaultDireccionShouldBeFound("altura.lessThan=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    void getAllDireccionsByAlturaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where altura is greater than DEFAULT_ALTURA
        defaultDireccionShouldNotBeFound("altura.greaterThan=" + DEFAULT_ALTURA);

        // Get all the direccionList where altura is greater than SMALLER_ALTURA
        defaultDireccionShouldBeFound("altura.greaterThan=" + SMALLER_ALTURA);
    }

    @Test
    @Transactional
    void getAllDireccionsByRegionIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where region equals to DEFAULT_REGION
        defaultDireccionShouldBeFound("region.equals=" + DEFAULT_REGION);

        // Get all the direccionList where region equals to UPDATED_REGION
        defaultDireccionShouldNotBeFound("region.equals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    void getAllDireccionsByRegionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where region not equals to DEFAULT_REGION
        defaultDireccionShouldNotBeFound("region.notEquals=" + DEFAULT_REGION);

        // Get all the direccionList where region not equals to UPDATED_REGION
        defaultDireccionShouldBeFound("region.notEquals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    void getAllDireccionsByRegionIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where region in DEFAULT_REGION or UPDATED_REGION
        defaultDireccionShouldBeFound("region.in=" + DEFAULT_REGION + "," + UPDATED_REGION);

        // Get all the direccionList where region equals to UPDATED_REGION
        defaultDireccionShouldNotBeFound("region.in=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    void getAllDireccionsByRegionIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where region is not null
        defaultDireccionShouldBeFound("region.specified=true");

        // Get all the direccionList where region is null
        defaultDireccionShouldNotBeFound("region.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByRegionContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where region contains DEFAULT_REGION
        defaultDireccionShouldBeFound("region.contains=" + DEFAULT_REGION);

        // Get all the direccionList where region contains UPDATED_REGION
        defaultDireccionShouldNotBeFound("region.contains=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    void getAllDireccionsByRegionNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where region does not contain DEFAULT_REGION
        defaultDireccionShouldNotBeFound("region.doesNotContain=" + DEFAULT_REGION);

        // Get all the direccionList where region does not contain UPDATED_REGION
        defaultDireccionShouldBeFound("region.doesNotContain=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    void getAllDireccionsBySubregionIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where subregion equals to DEFAULT_SUBREGION
        defaultDireccionShouldBeFound("subregion.equals=" + DEFAULT_SUBREGION);

        // Get all the direccionList where subregion equals to UPDATED_SUBREGION
        defaultDireccionShouldNotBeFound("subregion.equals=" + UPDATED_SUBREGION);
    }

    @Test
    @Transactional
    void getAllDireccionsBySubregionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where subregion not equals to DEFAULT_SUBREGION
        defaultDireccionShouldNotBeFound("subregion.notEquals=" + DEFAULT_SUBREGION);

        // Get all the direccionList where subregion not equals to UPDATED_SUBREGION
        defaultDireccionShouldBeFound("subregion.notEquals=" + UPDATED_SUBREGION);
    }

    @Test
    @Transactional
    void getAllDireccionsBySubregionIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where subregion in DEFAULT_SUBREGION or UPDATED_SUBREGION
        defaultDireccionShouldBeFound("subregion.in=" + DEFAULT_SUBREGION + "," + UPDATED_SUBREGION);

        // Get all the direccionList where subregion equals to UPDATED_SUBREGION
        defaultDireccionShouldNotBeFound("subregion.in=" + UPDATED_SUBREGION);
    }

    @Test
    @Transactional
    void getAllDireccionsBySubregionIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where subregion is not null
        defaultDireccionShouldBeFound("subregion.specified=true");

        // Get all the direccionList where subregion is null
        defaultDireccionShouldNotBeFound("subregion.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsBySubregionContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where subregion contains DEFAULT_SUBREGION
        defaultDireccionShouldBeFound("subregion.contains=" + DEFAULT_SUBREGION);

        // Get all the direccionList where subregion contains UPDATED_SUBREGION
        defaultDireccionShouldNotBeFound("subregion.contains=" + UPDATED_SUBREGION);
    }

    @Test
    @Transactional
    void getAllDireccionsBySubregionNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where subregion does not contain DEFAULT_SUBREGION
        defaultDireccionShouldNotBeFound("subregion.doesNotContain=" + DEFAULT_SUBREGION);

        // Get all the direccionList where subregion does not contain UPDATED_SUBREGION
        defaultDireccionShouldBeFound("subregion.doesNotContain=" + UPDATED_SUBREGION);
    }

    @Test
    @Transactional
    void getAllDireccionsByHubIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where hub equals to DEFAULT_HUB
        defaultDireccionShouldBeFound("hub.equals=" + DEFAULT_HUB);

        // Get all the direccionList where hub equals to UPDATED_HUB
        defaultDireccionShouldNotBeFound("hub.equals=" + UPDATED_HUB);
    }

    @Test
    @Transactional
    void getAllDireccionsByHubIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where hub not equals to DEFAULT_HUB
        defaultDireccionShouldNotBeFound("hub.notEquals=" + DEFAULT_HUB);

        // Get all the direccionList where hub not equals to UPDATED_HUB
        defaultDireccionShouldBeFound("hub.notEquals=" + UPDATED_HUB);
    }

    @Test
    @Transactional
    void getAllDireccionsByHubIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where hub in DEFAULT_HUB or UPDATED_HUB
        defaultDireccionShouldBeFound("hub.in=" + DEFAULT_HUB + "," + UPDATED_HUB);

        // Get all the direccionList where hub equals to UPDATED_HUB
        defaultDireccionShouldNotBeFound("hub.in=" + UPDATED_HUB);
    }

    @Test
    @Transactional
    void getAllDireccionsByHubIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where hub is not null
        defaultDireccionShouldBeFound("hub.specified=true");

        // Get all the direccionList where hub is null
        defaultDireccionShouldNotBeFound("hub.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByHubContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where hub contains DEFAULT_HUB
        defaultDireccionShouldBeFound("hub.contains=" + DEFAULT_HUB);

        // Get all the direccionList where hub contains UPDATED_HUB
        defaultDireccionShouldNotBeFound("hub.contains=" + UPDATED_HUB);
    }

    @Test
    @Transactional
    void getAllDireccionsByHubNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where hub does not contain DEFAULT_HUB
        defaultDireccionShouldNotBeFound("hub.doesNotContain=" + DEFAULT_HUB);

        // Get all the direccionList where hub does not contain UPDATED_HUB
        defaultDireccionShouldBeFound("hub.doesNotContain=" + UPDATED_HUB);
    }

    @Test
    @Transactional
    void getAllDireccionsByBarriosEspecialesIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where barriosEspeciales equals to DEFAULT_BARRIOS_ESPECIALES
        defaultDireccionShouldBeFound("barriosEspeciales.equals=" + DEFAULT_BARRIOS_ESPECIALES);

        // Get all the direccionList where barriosEspeciales equals to UPDATED_BARRIOS_ESPECIALES
        defaultDireccionShouldNotBeFound("barriosEspeciales.equals=" + UPDATED_BARRIOS_ESPECIALES);
    }

    @Test
    @Transactional
    void getAllDireccionsByBarriosEspecialesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where barriosEspeciales not equals to DEFAULT_BARRIOS_ESPECIALES
        defaultDireccionShouldNotBeFound("barriosEspeciales.notEquals=" + DEFAULT_BARRIOS_ESPECIALES);

        // Get all the direccionList where barriosEspeciales not equals to UPDATED_BARRIOS_ESPECIALES
        defaultDireccionShouldBeFound("barriosEspeciales.notEquals=" + UPDATED_BARRIOS_ESPECIALES);
    }

    @Test
    @Transactional
    void getAllDireccionsByBarriosEspecialesIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where barriosEspeciales in DEFAULT_BARRIOS_ESPECIALES or UPDATED_BARRIOS_ESPECIALES
        defaultDireccionShouldBeFound("barriosEspeciales.in=" + DEFAULT_BARRIOS_ESPECIALES + "," + UPDATED_BARRIOS_ESPECIALES);

        // Get all the direccionList where barriosEspeciales equals to UPDATED_BARRIOS_ESPECIALES
        defaultDireccionShouldNotBeFound("barriosEspeciales.in=" + UPDATED_BARRIOS_ESPECIALES);
    }

    @Test
    @Transactional
    void getAllDireccionsByBarriosEspecialesIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where barriosEspeciales is not null
        defaultDireccionShouldBeFound("barriosEspeciales.specified=true");

        // Get all the direccionList where barriosEspeciales is null
        defaultDireccionShouldNotBeFound("barriosEspeciales.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByBarriosEspecialesContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where barriosEspeciales contains DEFAULT_BARRIOS_ESPECIALES
        defaultDireccionShouldBeFound("barriosEspeciales.contains=" + DEFAULT_BARRIOS_ESPECIALES);

        // Get all the direccionList where barriosEspeciales contains UPDATED_BARRIOS_ESPECIALES
        defaultDireccionShouldNotBeFound("barriosEspeciales.contains=" + UPDATED_BARRIOS_ESPECIALES);
    }

    @Test
    @Transactional
    void getAllDireccionsByBarriosEspecialesNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where barriosEspeciales does not contain DEFAULT_BARRIOS_ESPECIALES
        defaultDireccionShouldNotBeFound("barriosEspeciales.doesNotContain=" + DEFAULT_BARRIOS_ESPECIALES);

        // Get all the direccionList where barriosEspeciales does not contain UPDATED_BARRIOS_ESPECIALES
        defaultDireccionShouldBeFound("barriosEspeciales.doesNotContain=" + UPDATED_BARRIOS_ESPECIALES);
    }

    @Test
    @Transactional
    void getAllDireccionsByCodigoPostalIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where codigoPostal equals to DEFAULT_CODIGO_POSTAL
        defaultDireccionShouldBeFound("codigoPostal.equals=" + DEFAULT_CODIGO_POSTAL);

        // Get all the direccionList where codigoPostal equals to UPDATED_CODIGO_POSTAL
        defaultDireccionShouldNotBeFound("codigoPostal.equals=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void getAllDireccionsByCodigoPostalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where codigoPostal not equals to DEFAULT_CODIGO_POSTAL
        defaultDireccionShouldNotBeFound("codigoPostal.notEquals=" + DEFAULT_CODIGO_POSTAL);

        // Get all the direccionList where codigoPostal not equals to UPDATED_CODIGO_POSTAL
        defaultDireccionShouldBeFound("codigoPostal.notEquals=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void getAllDireccionsByCodigoPostalIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where codigoPostal in DEFAULT_CODIGO_POSTAL or UPDATED_CODIGO_POSTAL
        defaultDireccionShouldBeFound("codigoPostal.in=" + DEFAULT_CODIGO_POSTAL + "," + UPDATED_CODIGO_POSTAL);

        // Get all the direccionList where codigoPostal equals to UPDATED_CODIGO_POSTAL
        defaultDireccionShouldNotBeFound("codigoPostal.in=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void getAllDireccionsByCodigoPostalIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where codigoPostal is not null
        defaultDireccionShouldBeFound("codigoPostal.specified=true");

        // Get all the direccionList where codigoPostal is null
        defaultDireccionShouldNotBeFound("codigoPostal.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByCodigoPostalContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where codigoPostal contains DEFAULT_CODIGO_POSTAL
        defaultDireccionShouldBeFound("codigoPostal.contains=" + DEFAULT_CODIGO_POSTAL);

        // Get all the direccionList where codigoPostal contains UPDATED_CODIGO_POSTAL
        defaultDireccionShouldNotBeFound("codigoPostal.contains=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void getAllDireccionsByCodigoPostalNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where codigoPostal does not contain DEFAULT_CODIGO_POSTAL
        defaultDireccionShouldNotBeFound("codigoPostal.doesNotContain=" + DEFAULT_CODIGO_POSTAL);

        // Get all the direccionList where codigoPostal does not contain UPDATED_CODIGO_POSTAL
        defaultDireccionShouldBeFound("codigoPostal.doesNotContain=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void getAllDireccionsByTipoCalleIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where tipoCalle equals to DEFAULT_TIPO_CALLE
        defaultDireccionShouldBeFound("tipoCalle.equals=" + DEFAULT_TIPO_CALLE);

        // Get all the direccionList where tipoCalle equals to UPDATED_TIPO_CALLE
        defaultDireccionShouldNotBeFound("tipoCalle.equals=" + UPDATED_TIPO_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByTipoCalleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where tipoCalle not equals to DEFAULT_TIPO_CALLE
        defaultDireccionShouldNotBeFound("tipoCalle.notEquals=" + DEFAULT_TIPO_CALLE);

        // Get all the direccionList where tipoCalle not equals to UPDATED_TIPO_CALLE
        defaultDireccionShouldBeFound("tipoCalle.notEquals=" + UPDATED_TIPO_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByTipoCalleIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where tipoCalle in DEFAULT_TIPO_CALLE or UPDATED_TIPO_CALLE
        defaultDireccionShouldBeFound("tipoCalle.in=" + DEFAULT_TIPO_CALLE + "," + UPDATED_TIPO_CALLE);

        // Get all the direccionList where tipoCalle equals to UPDATED_TIPO_CALLE
        defaultDireccionShouldNotBeFound("tipoCalle.in=" + UPDATED_TIPO_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByTipoCalleIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where tipoCalle is not null
        defaultDireccionShouldBeFound("tipoCalle.specified=true");

        // Get all the direccionList where tipoCalle is null
        defaultDireccionShouldNotBeFound("tipoCalle.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByTipoCalleContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where tipoCalle contains DEFAULT_TIPO_CALLE
        defaultDireccionShouldBeFound("tipoCalle.contains=" + DEFAULT_TIPO_CALLE);

        // Get all the direccionList where tipoCalle contains UPDATED_TIPO_CALLE
        defaultDireccionShouldNotBeFound("tipoCalle.contains=" + UPDATED_TIPO_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByTipoCalleNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where tipoCalle does not contain DEFAULT_TIPO_CALLE
        defaultDireccionShouldNotBeFound("tipoCalle.doesNotContain=" + DEFAULT_TIPO_CALLE);

        // Get all the direccionList where tipoCalle does not contain UPDATED_TIPO_CALLE
        defaultDireccionShouldBeFound("tipoCalle.doesNotContain=" + UPDATED_TIPO_CALLE);
    }

    @Test
    @Transactional
    void getAllDireccionsByZonaCompetenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where zonaCompetencia equals to DEFAULT_ZONA_COMPETENCIA
        defaultDireccionShouldBeFound("zonaCompetencia.equals=" + DEFAULT_ZONA_COMPETENCIA);

        // Get all the direccionList where zonaCompetencia equals to UPDATED_ZONA_COMPETENCIA
        defaultDireccionShouldNotBeFound("zonaCompetencia.equals=" + UPDATED_ZONA_COMPETENCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByZonaCompetenciaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where zonaCompetencia not equals to DEFAULT_ZONA_COMPETENCIA
        defaultDireccionShouldNotBeFound("zonaCompetencia.notEquals=" + DEFAULT_ZONA_COMPETENCIA);

        // Get all the direccionList where zonaCompetencia not equals to UPDATED_ZONA_COMPETENCIA
        defaultDireccionShouldBeFound("zonaCompetencia.notEquals=" + UPDATED_ZONA_COMPETENCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByZonaCompetenciaIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where zonaCompetencia in DEFAULT_ZONA_COMPETENCIA or UPDATED_ZONA_COMPETENCIA
        defaultDireccionShouldBeFound("zonaCompetencia.in=" + DEFAULT_ZONA_COMPETENCIA + "," + UPDATED_ZONA_COMPETENCIA);

        // Get all the direccionList where zonaCompetencia equals to UPDATED_ZONA_COMPETENCIA
        defaultDireccionShouldNotBeFound("zonaCompetencia.in=" + UPDATED_ZONA_COMPETENCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByZonaCompetenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where zonaCompetencia is not null
        defaultDireccionShouldBeFound("zonaCompetencia.specified=true");

        // Get all the direccionList where zonaCompetencia is null
        defaultDireccionShouldNotBeFound("zonaCompetencia.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByZonaCompetenciaContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where zonaCompetencia contains DEFAULT_ZONA_COMPETENCIA
        defaultDireccionShouldBeFound("zonaCompetencia.contains=" + DEFAULT_ZONA_COMPETENCIA);

        // Get all the direccionList where zonaCompetencia contains UPDATED_ZONA_COMPETENCIA
        defaultDireccionShouldNotBeFound("zonaCompetencia.contains=" + UPDATED_ZONA_COMPETENCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByZonaCompetenciaNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where zonaCompetencia does not contain DEFAULT_ZONA_COMPETENCIA
        defaultDireccionShouldNotBeFound("zonaCompetencia.doesNotContain=" + DEFAULT_ZONA_COMPETENCIA);

        // Get all the direccionList where zonaCompetencia does not contain UPDATED_ZONA_COMPETENCIA
        defaultDireccionShouldBeFound("zonaCompetencia.doesNotContain=" + UPDATED_ZONA_COMPETENCIA);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionLeftIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionLeft equals to DEFAULT_INTERSECTION_LEFT
        defaultDireccionShouldBeFound("intersectionLeft.equals=" + DEFAULT_INTERSECTION_LEFT);

        // Get all the direccionList where intersectionLeft equals to UPDATED_INTERSECTION_LEFT
        defaultDireccionShouldNotBeFound("intersectionLeft.equals=" + UPDATED_INTERSECTION_LEFT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionLeftIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionLeft not equals to DEFAULT_INTERSECTION_LEFT
        defaultDireccionShouldNotBeFound("intersectionLeft.notEquals=" + DEFAULT_INTERSECTION_LEFT);

        // Get all the direccionList where intersectionLeft not equals to UPDATED_INTERSECTION_LEFT
        defaultDireccionShouldBeFound("intersectionLeft.notEquals=" + UPDATED_INTERSECTION_LEFT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionLeftIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionLeft in DEFAULT_INTERSECTION_LEFT or UPDATED_INTERSECTION_LEFT
        defaultDireccionShouldBeFound("intersectionLeft.in=" + DEFAULT_INTERSECTION_LEFT + "," + UPDATED_INTERSECTION_LEFT);

        // Get all the direccionList where intersectionLeft equals to UPDATED_INTERSECTION_LEFT
        defaultDireccionShouldNotBeFound("intersectionLeft.in=" + UPDATED_INTERSECTION_LEFT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionLeftIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionLeft is not null
        defaultDireccionShouldBeFound("intersectionLeft.specified=true");

        // Get all the direccionList where intersectionLeft is null
        defaultDireccionShouldNotBeFound("intersectionLeft.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionLeftContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionLeft contains DEFAULT_INTERSECTION_LEFT
        defaultDireccionShouldBeFound("intersectionLeft.contains=" + DEFAULT_INTERSECTION_LEFT);

        // Get all the direccionList where intersectionLeft contains UPDATED_INTERSECTION_LEFT
        defaultDireccionShouldNotBeFound("intersectionLeft.contains=" + UPDATED_INTERSECTION_LEFT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionLeftNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionLeft does not contain DEFAULT_INTERSECTION_LEFT
        defaultDireccionShouldNotBeFound("intersectionLeft.doesNotContain=" + DEFAULT_INTERSECTION_LEFT);

        // Get all the direccionList where intersectionLeft does not contain UPDATED_INTERSECTION_LEFT
        defaultDireccionShouldBeFound("intersectionLeft.doesNotContain=" + UPDATED_INTERSECTION_LEFT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionRightIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionRight equals to DEFAULT_INTERSECTION_RIGHT
        defaultDireccionShouldBeFound("intersectionRight.equals=" + DEFAULT_INTERSECTION_RIGHT);

        // Get all the direccionList where intersectionRight equals to UPDATED_INTERSECTION_RIGHT
        defaultDireccionShouldNotBeFound("intersectionRight.equals=" + UPDATED_INTERSECTION_RIGHT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionRightIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionRight not equals to DEFAULT_INTERSECTION_RIGHT
        defaultDireccionShouldNotBeFound("intersectionRight.notEquals=" + DEFAULT_INTERSECTION_RIGHT);

        // Get all the direccionList where intersectionRight not equals to UPDATED_INTERSECTION_RIGHT
        defaultDireccionShouldBeFound("intersectionRight.notEquals=" + UPDATED_INTERSECTION_RIGHT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionRightIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionRight in DEFAULT_INTERSECTION_RIGHT or UPDATED_INTERSECTION_RIGHT
        defaultDireccionShouldBeFound("intersectionRight.in=" + DEFAULT_INTERSECTION_RIGHT + "," + UPDATED_INTERSECTION_RIGHT);

        // Get all the direccionList where intersectionRight equals to UPDATED_INTERSECTION_RIGHT
        defaultDireccionShouldNotBeFound("intersectionRight.in=" + UPDATED_INTERSECTION_RIGHT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionRightIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionRight is not null
        defaultDireccionShouldBeFound("intersectionRight.specified=true");

        // Get all the direccionList where intersectionRight is null
        defaultDireccionShouldNotBeFound("intersectionRight.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionRightContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionRight contains DEFAULT_INTERSECTION_RIGHT
        defaultDireccionShouldBeFound("intersectionRight.contains=" + DEFAULT_INTERSECTION_RIGHT);

        // Get all the direccionList where intersectionRight contains UPDATED_INTERSECTION_RIGHT
        defaultDireccionShouldNotBeFound("intersectionRight.contains=" + UPDATED_INTERSECTION_RIGHT);
    }

    @Test
    @Transactional
    void getAllDireccionsByIntersectionRightNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where intersectionRight does not contain DEFAULT_INTERSECTION_RIGHT
        defaultDireccionShouldNotBeFound("intersectionRight.doesNotContain=" + DEFAULT_INTERSECTION_RIGHT);

        // Get all the direccionList where intersectionRight does not contain UPDATED_INTERSECTION_RIGHT
        defaultDireccionShouldBeFound("intersectionRight.doesNotContain=" + UPDATED_INTERSECTION_RIGHT);
    }

    @Test
    @Transactional
    void getAllDireccionsByStreetTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where streetType equals to DEFAULT_STREET_TYPE
        defaultDireccionShouldBeFound("streetType.equals=" + DEFAULT_STREET_TYPE);

        // Get all the direccionList where streetType equals to UPDATED_STREET_TYPE
        defaultDireccionShouldNotBeFound("streetType.equals=" + UPDATED_STREET_TYPE);
    }

    @Test
    @Transactional
    void getAllDireccionsByStreetTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where streetType not equals to DEFAULT_STREET_TYPE
        defaultDireccionShouldNotBeFound("streetType.notEquals=" + DEFAULT_STREET_TYPE);

        // Get all the direccionList where streetType not equals to UPDATED_STREET_TYPE
        defaultDireccionShouldBeFound("streetType.notEquals=" + UPDATED_STREET_TYPE);
    }

    @Test
    @Transactional
    void getAllDireccionsByStreetTypeIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where streetType in DEFAULT_STREET_TYPE or UPDATED_STREET_TYPE
        defaultDireccionShouldBeFound("streetType.in=" + DEFAULT_STREET_TYPE + "," + UPDATED_STREET_TYPE);

        // Get all the direccionList where streetType equals to UPDATED_STREET_TYPE
        defaultDireccionShouldNotBeFound("streetType.in=" + UPDATED_STREET_TYPE);
    }

    @Test
    @Transactional
    void getAllDireccionsByStreetTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where streetType is not null
        defaultDireccionShouldBeFound("streetType.specified=true");

        // Get all the direccionList where streetType is null
        defaultDireccionShouldNotBeFound("streetType.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByStreetTypeContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where streetType contains DEFAULT_STREET_TYPE
        defaultDireccionShouldBeFound("streetType.contains=" + DEFAULT_STREET_TYPE);

        // Get all the direccionList where streetType contains UPDATED_STREET_TYPE
        defaultDireccionShouldNotBeFound("streetType.contains=" + UPDATED_STREET_TYPE);
    }

    @Test
    @Transactional
    void getAllDireccionsByStreetTypeNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where streetType does not contain DEFAULT_STREET_TYPE
        defaultDireccionShouldNotBeFound("streetType.doesNotContain=" + DEFAULT_STREET_TYPE);

        // Get all the direccionList where streetType does not contain UPDATED_STREET_TYPE
        defaultDireccionShouldBeFound("streetType.doesNotContain=" + UPDATED_STREET_TYPE);
    }

    @Test
    @Transactional
    void getAllDireccionsByLatitudIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where latitud equals to DEFAULT_LATITUD
        defaultDireccionShouldBeFound("latitud.equals=" + DEFAULT_LATITUD);

        // Get all the direccionList where latitud equals to UPDATED_LATITUD
        defaultDireccionShouldNotBeFound("latitud.equals=" + UPDATED_LATITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLatitudIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where latitud not equals to DEFAULT_LATITUD
        defaultDireccionShouldNotBeFound("latitud.notEquals=" + DEFAULT_LATITUD);

        // Get all the direccionList where latitud not equals to UPDATED_LATITUD
        defaultDireccionShouldBeFound("latitud.notEquals=" + UPDATED_LATITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLatitudIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where latitud in DEFAULT_LATITUD or UPDATED_LATITUD
        defaultDireccionShouldBeFound("latitud.in=" + DEFAULT_LATITUD + "," + UPDATED_LATITUD);

        // Get all the direccionList where latitud equals to UPDATED_LATITUD
        defaultDireccionShouldNotBeFound("latitud.in=" + UPDATED_LATITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLatitudIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where latitud is not null
        defaultDireccionShouldBeFound("latitud.specified=true");

        // Get all the direccionList where latitud is null
        defaultDireccionShouldNotBeFound("latitud.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByLatitudContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where latitud contains DEFAULT_LATITUD
        defaultDireccionShouldBeFound("latitud.contains=" + DEFAULT_LATITUD);

        // Get all the direccionList where latitud contains UPDATED_LATITUD
        defaultDireccionShouldNotBeFound("latitud.contains=" + UPDATED_LATITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLatitudNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where latitud does not contain DEFAULT_LATITUD
        defaultDireccionShouldNotBeFound("latitud.doesNotContain=" + DEFAULT_LATITUD);

        // Get all the direccionList where latitud does not contain UPDATED_LATITUD
        defaultDireccionShouldBeFound("latitud.doesNotContain=" + UPDATED_LATITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLongitudIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where longitud equals to DEFAULT_LONGITUD
        defaultDireccionShouldBeFound("longitud.equals=" + DEFAULT_LONGITUD);

        // Get all the direccionList where longitud equals to UPDATED_LONGITUD
        defaultDireccionShouldNotBeFound("longitud.equals=" + UPDATED_LONGITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLongitudIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where longitud not equals to DEFAULT_LONGITUD
        defaultDireccionShouldNotBeFound("longitud.notEquals=" + DEFAULT_LONGITUD);

        // Get all the direccionList where longitud not equals to UPDATED_LONGITUD
        defaultDireccionShouldBeFound("longitud.notEquals=" + UPDATED_LONGITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLongitudIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where longitud in DEFAULT_LONGITUD or UPDATED_LONGITUD
        defaultDireccionShouldBeFound("longitud.in=" + DEFAULT_LONGITUD + "," + UPDATED_LONGITUD);

        // Get all the direccionList where longitud equals to UPDATED_LONGITUD
        defaultDireccionShouldNotBeFound("longitud.in=" + UPDATED_LONGITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLongitudIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where longitud is not null
        defaultDireccionShouldBeFound("longitud.specified=true");

        // Get all the direccionList where longitud is null
        defaultDireccionShouldNotBeFound("longitud.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByLongitudContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where longitud contains DEFAULT_LONGITUD
        defaultDireccionShouldBeFound("longitud.contains=" + DEFAULT_LONGITUD);

        // Get all the direccionList where longitud contains UPDATED_LONGITUD
        defaultDireccionShouldNotBeFound("longitud.contains=" + UPDATED_LONGITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByLongitudNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where longitud does not contain DEFAULT_LONGITUD
        defaultDireccionShouldNotBeFound("longitud.doesNotContain=" + DEFAULT_LONGITUD);

        // Get all the direccionList where longitud does not contain UPDATED_LONGITUD
        defaultDireccionShouldBeFound("longitud.doesNotContain=" + UPDATED_LONGITUD);
    }

    @Test
    @Transactional
    void getAllDireccionsByElementosDeRedIsEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where elementosDeRed equals to DEFAULT_ELEMENTOS_DE_RED
        defaultDireccionShouldBeFound("elementosDeRed.equals=" + DEFAULT_ELEMENTOS_DE_RED);

        // Get all the direccionList where elementosDeRed equals to UPDATED_ELEMENTOS_DE_RED
        defaultDireccionShouldNotBeFound("elementosDeRed.equals=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllDireccionsByElementosDeRedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where elementosDeRed not equals to DEFAULT_ELEMENTOS_DE_RED
        defaultDireccionShouldNotBeFound("elementosDeRed.notEquals=" + DEFAULT_ELEMENTOS_DE_RED);

        // Get all the direccionList where elementosDeRed not equals to UPDATED_ELEMENTOS_DE_RED
        defaultDireccionShouldBeFound("elementosDeRed.notEquals=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllDireccionsByElementosDeRedIsInShouldWork() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where elementosDeRed in DEFAULT_ELEMENTOS_DE_RED or UPDATED_ELEMENTOS_DE_RED
        defaultDireccionShouldBeFound("elementosDeRed.in=" + DEFAULT_ELEMENTOS_DE_RED + "," + UPDATED_ELEMENTOS_DE_RED);

        // Get all the direccionList where elementosDeRed equals to UPDATED_ELEMENTOS_DE_RED
        defaultDireccionShouldNotBeFound("elementosDeRed.in=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllDireccionsByElementosDeRedIsNullOrNotNull() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where elementosDeRed is not null
        defaultDireccionShouldBeFound("elementosDeRed.specified=true");

        // Get all the direccionList where elementosDeRed is null
        defaultDireccionShouldNotBeFound("elementosDeRed.specified=false");
    }

    @Test
    @Transactional
    void getAllDireccionsByElementosDeRedContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where elementosDeRed contains DEFAULT_ELEMENTOS_DE_RED
        defaultDireccionShouldBeFound("elementosDeRed.contains=" + DEFAULT_ELEMENTOS_DE_RED);

        // Get all the direccionList where elementosDeRed contains UPDATED_ELEMENTOS_DE_RED
        defaultDireccionShouldNotBeFound("elementosDeRed.contains=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllDireccionsByElementosDeRedNotContainsSomething() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        // Get all the direccionList where elementosDeRed does not contain DEFAULT_ELEMENTOS_DE_RED
        defaultDireccionShouldNotBeFound("elementosDeRed.doesNotContain=" + DEFAULT_ELEMENTOS_DE_RED);

        // Get all the direccionList where elementosDeRed does not contain UPDATED_ELEMENTOS_DE_RED
        defaultDireccionShouldBeFound("elementosDeRed.doesNotContain=" + UPDATED_ELEMENTOS_DE_RED);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDireccionShouldBeFound(String filter) throws Exception {
        restDireccionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(direccion.getId().intValue())))
            .andExpect(jsonPath("$.[*].identification").value(hasItem(DEFAULT_IDENTIFICATION)))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA)))
            .andExpect(jsonPath("$.[*].partido").value(hasItem(DEFAULT_PARTIDO)))
            .andExpect(jsonPath("$.[*].localidad").value(hasItem(DEFAULT_LOCALIDAD)))
            .andExpect(jsonPath("$.[*].calle").value(hasItem(DEFAULT_CALLE)))
            .andExpect(jsonPath("$.[*].altura").value(hasItem(DEFAULT_ALTURA.intValue())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].subregion").value(hasItem(DEFAULT_SUBREGION)))
            .andExpect(jsonPath("$.[*].hub").value(hasItem(DEFAULT_HUB)))
            .andExpect(jsonPath("$.[*].barriosEspeciales").value(hasItem(DEFAULT_BARRIOS_ESPECIALES)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)))
            .andExpect(jsonPath("$.[*].tipoCalle").value(hasItem(DEFAULT_TIPO_CALLE)))
            .andExpect(jsonPath("$.[*].zonaCompetencia").value(hasItem(DEFAULT_ZONA_COMPETENCIA)))
            .andExpect(jsonPath("$.[*].intersectionLeft").value(hasItem(DEFAULT_INTERSECTION_LEFT)))
            .andExpect(jsonPath("$.[*].intersectionRight").value(hasItem(DEFAULT_INTERSECTION_RIGHT)))
            .andExpect(jsonPath("$.[*].streetType").value(hasItem(DEFAULT_STREET_TYPE)))
            .andExpect(jsonPath("$.[*].latitud").value(hasItem(DEFAULT_LATITUD)))
            .andExpect(jsonPath("$.[*].longitud").value(hasItem(DEFAULT_LONGITUD)))
            .andExpect(jsonPath("$.[*].elementosDeRed").value(hasItem(DEFAULT_ELEMENTOS_DE_RED)));

        // Check, that the count call also returns 1
        restDireccionMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDireccionShouldNotBeFound(String filter) throws Exception {
        restDireccionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDireccionMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDireccion() throws Exception {
        // Get the direccion
        restDireccionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDireccion() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();

        // Update the direccion
        Direccion updatedDireccion = direccionRepository.findById(direccion.getId()).get();
        // Disconnect from session so that the updates on updatedDireccion are not directly saved in db
        em.detach(updatedDireccion);
        updatedDireccion
            .identification(UPDATED_IDENTIFICATION)
            .pais(UPDATED_PAIS)
            .provincia(UPDATED_PROVINCIA)
            .partido(UPDATED_PARTIDO)
            .localidad(UPDATED_LOCALIDAD)
            .calle(UPDATED_CALLE)
            .altura(UPDATED_ALTURA)
            .region(UPDATED_REGION)
            .subregion(UPDATED_SUBREGION)
            .hub(UPDATED_HUB)
            .barriosEspeciales(UPDATED_BARRIOS_ESPECIALES)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .tipoCalle(UPDATED_TIPO_CALLE)
            .zonaCompetencia(UPDATED_ZONA_COMPETENCIA)
            .intersectionLeft(UPDATED_INTERSECTION_LEFT)
            .intersectionRight(UPDATED_INTERSECTION_RIGHT)
            .streetType(UPDATED_STREET_TYPE)
            .latitud(UPDATED_LATITUD)
            .longitud(UPDATED_LONGITUD)
            .elementosDeRed(UPDATED_ELEMENTOS_DE_RED);

        restDireccionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDireccion.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDireccion))
            )
            .andExpect(status().isOk());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
        Direccion testDireccion = direccionList.get(direccionList.size() - 1);
        assertThat(testDireccion.getIdentification()).isEqualTo(UPDATED_IDENTIFICATION);
        assertThat(testDireccion.getPais()).isEqualTo(UPDATED_PAIS);
        assertThat(testDireccion.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
        assertThat(testDireccion.getPartido()).isEqualTo(UPDATED_PARTIDO);
        assertThat(testDireccion.getLocalidad()).isEqualTo(UPDATED_LOCALIDAD);
        assertThat(testDireccion.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testDireccion.getAltura()).isEqualTo(UPDATED_ALTURA);
        assertThat(testDireccion.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testDireccion.getSubregion()).isEqualTo(UPDATED_SUBREGION);
        assertThat(testDireccion.getHub()).isEqualTo(UPDATED_HUB);
        assertThat(testDireccion.getBarriosEspeciales()).isEqualTo(UPDATED_BARRIOS_ESPECIALES);
        assertThat(testDireccion.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
        assertThat(testDireccion.getTipoCalle()).isEqualTo(UPDATED_TIPO_CALLE);
        assertThat(testDireccion.getZonaCompetencia()).isEqualTo(UPDATED_ZONA_COMPETENCIA);
        assertThat(testDireccion.getIntersectionLeft()).isEqualTo(UPDATED_INTERSECTION_LEFT);
        assertThat(testDireccion.getIntersectionRight()).isEqualTo(UPDATED_INTERSECTION_RIGHT);
        assertThat(testDireccion.getStreetType()).isEqualTo(UPDATED_STREET_TYPE);
        assertThat(testDireccion.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testDireccion.getLongitud()).isEqualTo(UPDATED_LONGITUD);
        assertThat(testDireccion.getElementosDeRed()).isEqualTo(UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void putNonExistingDireccion() throws Exception {
        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();
        direccion.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDireccionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, direccion.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(direccion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDireccion() throws Exception {
        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();
        direccion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDireccionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(direccion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDireccion() throws Exception {
        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();
        direccion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDireccionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDireccionWithPatch() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();

        // Update the direccion using partial update
        Direccion partialUpdatedDireccion = new Direccion();
        partialUpdatedDireccion.setId(direccion.getId());

        partialUpdatedDireccion
            .identification(UPDATED_IDENTIFICATION)
            .pais(UPDATED_PAIS)
            .provincia(UPDATED_PROVINCIA)
            .partido(UPDATED_PARTIDO)
            .calle(UPDATED_CALLE)
            .altura(UPDATED_ALTURA)
            .region(UPDATED_REGION)
            .subregion(UPDATED_SUBREGION)
            .hub(UPDATED_HUB)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .latitud(UPDATED_LATITUD)
            .elementosDeRed(UPDATED_ELEMENTOS_DE_RED);

        restDireccionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDireccion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDireccion))
            )
            .andExpect(status().isOk());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
        Direccion testDireccion = direccionList.get(direccionList.size() - 1);
        assertThat(testDireccion.getIdentification()).isEqualTo(UPDATED_IDENTIFICATION);
        assertThat(testDireccion.getPais()).isEqualTo(UPDATED_PAIS);
        assertThat(testDireccion.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
        assertThat(testDireccion.getPartido()).isEqualTo(UPDATED_PARTIDO);
        assertThat(testDireccion.getLocalidad()).isEqualTo(DEFAULT_LOCALIDAD);
        assertThat(testDireccion.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testDireccion.getAltura()).isEqualTo(UPDATED_ALTURA);
        assertThat(testDireccion.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testDireccion.getSubregion()).isEqualTo(UPDATED_SUBREGION);
        assertThat(testDireccion.getHub()).isEqualTo(UPDATED_HUB);
        assertThat(testDireccion.getBarriosEspeciales()).isEqualTo(DEFAULT_BARRIOS_ESPECIALES);
        assertThat(testDireccion.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
        assertThat(testDireccion.getTipoCalle()).isEqualTo(DEFAULT_TIPO_CALLE);
        assertThat(testDireccion.getZonaCompetencia()).isEqualTo(DEFAULT_ZONA_COMPETENCIA);
        assertThat(testDireccion.getIntersectionLeft()).isEqualTo(DEFAULT_INTERSECTION_LEFT);
        assertThat(testDireccion.getIntersectionRight()).isEqualTo(DEFAULT_INTERSECTION_RIGHT);
        assertThat(testDireccion.getStreetType()).isEqualTo(DEFAULT_STREET_TYPE);
        assertThat(testDireccion.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testDireccion.getLongitud()).isEqualTo(DEFAULT_LONGITUD);
        assertThat(testDireccion.getElementosDeRed()).isEqualTo(UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void fullUpdateDireccionWithPatch() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();

        // Update the direccion using partial update
        Direccion partialUpdatedDireccion = new Direccion();
        partialUpdatedDireccion.setId(direccion.getId());

        partialUpdatedDireccion
            .identification(UPDATED_IDENTIFICATION)
            .pais(UPDATED_PAIS)
            .provincia(UPDATED_PROVINCIA)
            .partido(UPDATED_PARTIDO)
            .localidad(UPDATED_LOCALIDAD)
            .calle(UPDATED_CALLE)
            .altura(UPDATED_ALTURA)
            .region(UPDATED_REGION)
            .subregion(UPDATED_SUBREGION)
            .hub(UPDATED_HUB)
            .barriosEspeciales(UPDATED_BARRIOS_ESPECIALES)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .tipoCalle(UPDATED_TIPO_CALLE)
            .zonaCompetencia(UPDATED_ZONA_COMPETENCIA)
            .intersectionLeft(UPDATED_INTERSECTION_LEFT)
            .intersectionRight(UPDATED_INTERSECTION_RIGHT)
            .streetType(UPDATED_STREET_TYPE)
            .latitud(UPDATED_LATITUD)
            .longitud(UPDATED_LONGITUD)
            .elementosDeRed(UPDATED_ELEMENTOS_DE_RED);

        restDireccionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDireccion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDireccion))
            )
            .andExpect(status().isOk());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
        Direccion testDireccion = direccionList.get(direccionList.size() - 1);
        assertThat(testDireccion.getIdentification()).isEqualTo(UPDATED_IDENTIFICATION);
        assertThat(testDireccion.getPais()).isEqualTo(UPDATED_PAIS);
        assertThat(testDireccion.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
        assertThat(testDireccion.getPartido()).isEqualTo(UPDATED_PARTIDO);
        assertThat(testDireccion.getLocalidad()).isEqualTo(UPDATED_LOCALIDAD);
        assertThat(testDireccion.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testDireccion.getAltura()).isEqualTo(UPDATED_ALTURA);
        assertThat(testDireccion.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testDireccion.getSubregion()).isEqualTo(UPDATED_SUBREGION);
        assertThat(testDireccion.getHub()).isEqualTo(UPDATED_HUB);
        assertThat(testDireccion.getBarriosEspeciales()).isEqualTo(UPDATED_BARRIOS_ESPECIALES);
        assertThat(testDireccion.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
        assertThat(testDireccion.getTipoCalle()).isEqualTo(UPDATED_TIPO_CALLE);
        assertThat(testDireccion.getZonaCompetencia()).isEqualTo(UPDATED_ZONA_COMPETENCIA);
        assertThat(testDireccion.getIntersectionLeft()).isEqualTo(UPDATED_INTERSECTION_LEFT);
        assertThat(testDireccion.getIntersectionRight()).isEqualTo(UPDATED_INTERSECTION_RIGHT);
        assertThat(testDireccion.getStreetType()).isEqualTo(UPDATED_STREET_TYPE);
        assertThat(testDireccion.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testDireccion.getLongitud()).isEqualTo(UPDATED_LONGITUD);
        assertThat(testDireccion.getElementosDeRed()).isEqualTo(UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void patchNonExistingDireccion() throws Exception {
        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();
        direccion.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDireccionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, direccion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(direccion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDireccion() throws Exception {
        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();
        direccion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDireccionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(direccion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDireccion() throws Exception {
        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();
        direccion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDireccionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(direccion))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDireccion() throws Exception {
        // Initialize the database
        direccionRepository.saveAndFlush(direccion);

        int databaseSizeBeforeDelete = direccionRepository.findAll().size();

        // Delete the direccion
        restDireccionMockMvc
            .perform(delete(ENTITY_API_URL_ID, direccion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
