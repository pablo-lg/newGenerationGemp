package ar.teco.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.teco.IntegrationTest;
import ar.teco.domain.Competencia;
import ar.teco.domain.Despliegue;
import ar.teco.domain.Direccion;
import ar.teco.domain.EjecCuentas;
import ar.teco.domain.Emprendimiento;
import ar.teco.domain.GrupoEmp;
import ar.teco.domain.NSE;
import ar.teco.domain.Segmento;
import ar.teco.domain.Tecnologia;
import ar.teco.domain.TipoEmp;
import ar.teco.domain.TipoObra;
import ar.teco.domain.enumeration.Estado;
import ar.teco.domain.enumeration.EstadoBC;
import ar.teco.domain.enumeration.EstadoFirma;
import ar.teco.repository.EmprendimientoRepository;
import ar.teco.service.criteria.EmprendimientoCriteria;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link EmprendimientoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmprendimientoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_FIN_OBRA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN_OBRA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA_FIN_OBRA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_CODIGO_OBRA = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_OBRA = "BBBBBBBBBB";

    private static final String DEFAULT_ELEMENTOS_DE_RED = "AAAAAAAAAA";
    private static final String UPDATED_ELEMENTOS_DE_RED = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENTES_CATV = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTES_CATV = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENTES_FIBERTEL = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTES_FIBERTEL = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENTES_FIBERTEL_LITE = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTES_FIBERTEL_LITE = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENTES_FLOW = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTES_FLOW = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENTES_COMBO = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTES_COMBO = "BBBBBBBBBB";

    private static final String DEFAULT_LINEAS_VOZ = "AAAAAAAAAA";
    private static final String UPDATED_LINEAS_VOZ = "BBBBBBBBBB";

    private static final String DEFAULT_MESES_DE_FINALIZADO = "AAAAAAAAAA";
    private static final String UPDATED_MESES_DE_FINALIZADO = "BBBBBBBBBB";

    private static final String DEFAULT_ALTAS_BC = "AAAAAAAAAA";
    private static final String UPDATED_ALTAS_BC = "BBBBBBBBBB";

    private static final String DEFAULT_PENETRACION_VIV_LOT = "AAAAAAAAAA";
    private static final String UPDATED_PENETRACION_VIV_LOT = "BBBBBBBBBB";

    private static final String DEFAULT_PENETRACION_BC = "AAAAAAAAAA";
    private static final String UPDATED_PENETRACION_BC = "BBBBBBBBBB";

    private static final String DEFAULT_DEMANDA_1 = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDA_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DEMANDA_2 = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDA_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DEMANDA_3 = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDA_3 = "BBBBBBBBBB";

    private static final String DEFAULT_DEMANDA_4 = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDA_4 = "BBBBBBBBBB";

    private static final String DEFAULT_DEMANDA_5 = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDA_5 = "BBBBBBBBBB";

    private static final String DEFAULT_LOTES = "AAAAAAAAAA";
    private static final String UPDATED_LOTES = "BBBBBBBBBB";

    private static final String DEFAULT_VIVIENDAS = "AAAAAAAAAA";
    private static final String UPDATED_VIVIENDAS = "BBBBBBBBBB";

    private static final String DEFAULT_COM_PROF = "AAAAAAAAAA";
    private static final String UPDATED_COM_PROF = "BBBBBBBBBB";

    private static final String DEFAULT_HABITACIONES = "AAAAAAAAAA";
    private static final String UPDATED_HABITACIONES = "BBBBBBBBBB";

    private static final String DEFAULT_MANZANAS = "AAAAAAAAAA";
    private static final String UPDATED_MANZANAS = "BBBBBBBBBB";

    private static final String DEFAULT_DEMANDA = "AAAAAAAAAA";
    private static final String UPDATED_DEMANDA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_DE_RELEVAMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_DE_RELEVAMIENTO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA_DE_RELEVAMIENTO = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ANO_PRIORIZACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANO_PRIORIZACION = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ANO_PRIORIZACION = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_CONTRATO_OPEN = "AAAAAAAAAA";
    private static final String UPDATED_CONTRATO_OPEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NEGOCIACION = false;
    private static final Boolean UPDATED_NEGOCIACION = true;

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_CODIGO_DE_FIRMA = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_DE_FIRMA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_FIRMA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIRMA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA_FIRMA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    private static final String DEFAULT_COMENTARIO = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIO = "BBBBBBBBBB";

    private static final String DEFAULT_COMEN_CAN = "AAAAAAAAAA";
    private static final String UPDATED_COMEN_CAN = "BBBBBBBBBB";

    private static final EstadoFirma DEFAULT_ESTADO_FIRMA = EstadoFirma.FIRMADO;
    private static final EstadoFirma UPDATED_ESTADO_FIRMA = EstadoFirma.NO_APROBADO;

    private static final Estado DEFAULT_ESTADO = Estado.SIN_ESTADO;
    private static final Estado UPDATED_ESTADO = Estado.PROSPECTO;

    private static final EstadoBC DEFAULT_ESTADO_BC = EstadoBC.APROBADO;
    private static final EstadoBC UPDATED_ESTADO_BC = EstadoBC.NO_APROBADO;

    private static final String ENTITY_API_URL = "/api/emprendimientos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmprendimientoMockMvc;

    private Emprendimiento emprendimiento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Emprendimiento createEntity(EntityManager em) {
        Emprendimiento emprendimiento = new Emprendimiento()
            .nombre(DEFAULT_NOMBRE)
            .contacto(DEFAULT_CONTACTO)
            .fechaFinObra(DEFAULT_FECHA_FIN_OBRA)
            .codigoObra(DEFAULT_CODIGO_OBRA)
            .elementosDeRed(DEFAULT_ELEMENTOS_DE_RED)
            .clientesCatv(DEFAULT_CLIENTES_CATV)
            .clientesFibertel(DEFAULT_CLIENTES_FIBERTEL)
            .clientesFibertelLite(DEFAULT_CLIENTES_FIBERTEL_LITE)
            .clientesFlow(DEFAULT_CLIENTES_FLOW)
            .clientesCombo(DEFAULT_CLIENTES_COMBO)
            .lineasVoz(DEFAULT_LINEAS_VOZ)
            .mesesDeFinalizado(DEFAULT_MESES_DE_FINALIZADO)
            .altasBC(DEFAULT_ALTAS_BC)
            .penetracionVivLot(DEFAULT_PENETRACION_VIV_LOT)
            .penetracionBC(DEFAULT_PENETRACION_BC)
            .demanda1(DEFAULT_DEMANDA_1)
            .demanda2(DEFAULT_DEMANDA_2)
            .demanda3(DEFAULT_DEMANDA_3)
            .demanda4(DEFAULT_DEMANDA_4)
            .demanda5(DEFAULT_DEMANDA_5)
            .lotes(DEFAULT_LOTES)
            .viviendas(DEFAULT_VIVIENDAS)
            .comProf(DEFAULT_COM_PROF)
            .habitaciones(DEFAULT_HABITACIONES)
            .manzanas(DEFAULT_MANZANAS)
            .demanda(DEFAULT_DEMANDA)
            .fechaDeRelevamiento(DEFAULT_FECHA_DE_RELEVAMIENTO)
            .telefono(DEFAULT_TELEFONO)
            .anoPriorizacion(DEFAULT_ANO_PRIORIZACION)
            .contratoOpen(DEFAULT_CONTRATO_OPEN)
            .negociacion(DEFAULT_NEGOCIACION)
            .fecha(DEFAULT_FECHA)
            .codigoDeFirma(DEFAULT_CODIGO_DE_FIRMA)
            .fechaFirma(DEFAULT_FECHA_FIRMA)
            .observaciones(DEFAULT_OBSERVACIONES)
            .comentario(DEFAULT_COMENTARIO)
            .comenCan(DEFAULT_COMEN_CAN)
            .estadoFirma(DEFAULT_ESTADO_FIRMA)
            .estado(DEFAULT_ESTADO)
            .estadoBC(DEFAULT_ESTADO_BC);
        return emprendimiento;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Emprendimiento createUpdatedEntity(EntityManager em) {
        Emprendimiento emprendimiento = new Emprendimiento()
            .nombre(UPDATED_NOMBRE)
            .contacto(UPDATED_CONTACTO)
            .fechaFinObra(UPDATED_FECHA_FIN_OBRA)
            .codigoObra(UPDATED_CODIGO_OBRA)
            .elementosDeRed(UPDATED_ELEMENTOS_DE_RED)
            .clientesCatv(UPDATED_CLIENTES_CATV)
            .clientesFibertel(UPDATED_CLIENTES_FIBERTEL)
            .clientesFibertelLite(UPDATED_CLIENTES_FIBERTEL_LITE)
            .clientesFlow(UPDATED_CLIENTES_FLOW)
            .clientesCombo(UPDATED_CLIENTES_COMBO)
            .lineasVoz(UPDATED_LINEAS_VOZ)
            .mesesDeFinalizado(UPDATED_MESES_DE_FINALIZADO)
            .altasBC(UPDATED_ALTAS_BC)
            .penetracionVivLot(UPDATED_PENETRACION_VIV_LOT)
            .penetracionBC(UPDATED_PENETRACION_BC)
            .demanda1(UPDATED_DEMANDA_1)
            .demanda2(UPDATED_DEMANDA_2)
            .demanda3(UPDATED_DEMANDA_3)
            .demanda4(UPDATED_DEMANDA_4)
            .demanda5(UPDATED_DEMANDA_5)
            .lotes(UPDATED_LOTES)
            .viviendas(UPDATED_VIVIENDAS)
            .comProf(UPDATED_COM_PROF)
            .habitaciones(UPDATED_HABITACIONES)
            .manzanas(UPDATED_MANZANAS)
            .demanda(UPDATED_DEMANDA)
            .fechaDeRelevamiento(UPDATED_FECHA_DE_RELEVAMIENTO)
            .telefono(UPDATED_TELEFONO)
            .anoPriorizacion(UPDATED_ANO_PRIORIZACION)
            .contratoOpen(UPDATED_CONTRATO_OPEN)
            .negociacion(UPDATED_NEGOCIACION)
            .fecha(UPDATED_FECHA)
            .codigoDeFirma(UPDATED_CODIGO_DE_FIRMA)
            .fechaFirma(UPDATED_FECHA_FIRMA)
            .observaciones(UPDATED_OBSERVACIONES)
            .comentario(UPDATED_COMENTARIO)
            .comenCan(UPDATED_COMEN_CAN)
            .estadoFirma(UPDATED_ESTADO_FIRMA)
            .estado(UPDATED_ESTADO)
            .estadoBC(UPDATED_ESTADO_BC);
        return emprendimiento;
    }

    @BeforeEach
    public void initTest() {
        emprendimiento = createEntity(em);
    }

    @Test
    @Transactional
    void createEmprendimiento() throws Exception {
        int databaseSizeBeforeCreate = emprendimientoRepository.findAll().size();
        // Create the Emprendimiento
        restEmprendimientoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(emprendimiento))
            )
            .andExpect(status().isCreated());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeCreate + 1);
        Emprendimiento testEmprendimiento = emprendimientoList.get(emprendimientoList.size() - 1);
        assertThat(testEmprendimiento.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testEmprendimiento.getContacto()).isEqualTo(DEFAULT_CONTACTO);
        assertThat(testEmprendimiento.getFechaFinObra()).isEqualTo(DEFAULT_FECHA_FIN_OBRA);
        assertThat(testEmprendimiento.getCodigoObra()).isEqualTo(DEFAULT_CODIGO_OBRA);
        assertThat(testEmprendimiento.getElementosDeRed()).isEqualTo(DEFAULT_ELEMENTOS_DE_RED);
        assertThat(testEmprendimiento.getClientesCatv()).isEqualTo(DEFAULT_CLIENTES_CATV);
        assertThat(testEmprendimiento.getClientesFibertel()).isEqualTo(DEFAULT_CLIENTES_FIBERTEL);
        assertThat(testEmprendimiento.getClientesFibertelLite()).isEqualTo(DEFAULT_CLIENTES_FIBERTEL_LITE);
        assertThat(testEmprendimiento.getClientesFlow()).isEqualTo(DEFAULT_CLIENTES_FLOW);
        assertThat(testEmprendimiento.getClientesCombo()).isEqualTo(DEFAULT_CLIENTES_COMBO);
        assertThat(testEmprendimiento.getLineasVoz()).isEqualTo(DEFAULT_LINEAS_VOZ);
        assertThat(testEmprendimiento.getMesesDeFinalizado()).isEqualTo(DEFAULT_MESES_DE_FINALIZADO);
        assertThat(testEmprendimiento.getAltasBC()).isEqualTo(DEFAULT_ALTAS_BC);
        assertThat(testEmprendimiento.getPenetracionVivLot()).isEqualTo(DEFAULT_PENETRACION_VIV_LOT);
        assertThat(testEmprendimiento.getPenetracionBC()).isEqualTo(DEFAULT_PENETRACION_BC);
        assertThat(testEmprendimiento.getDemanda1()).isEqualTo(DEFAULT_DEMANDA_1);
        assertThat(testEmprendimiento.getDemanda2()).isEqualTo(DEFAULT_DEMANDA_2);
        assertThat(testEmprendimiento.getDemanda3()).isEqualTo(DEFAULT_DEMANDA_3);
        assertThat(testEmprendimiento.getDemanda4()).isEqualTo(DEFAULT_DEMANDA_4);
        assertThat(testEmprendimiento.getDemanda5()).isEqualTo(DEFAULT_DEMANDA_5);
        assertThat(testEmprendimiento.getLotes()).isEqualTo(DEFAULT_LOTES);
        assertThat(testEmprendimiento.getViviendas()).isEqualTo(DEFAULT_VIVIENDAS);
        assertThat(testEmprendimiento.getComProf()).isEqualTo(DEFAULT_COM_PROF);
        assertThat(testEmprendimiento.getHabitaciones()).isEqualTo(DEFAULT_HABITACIONES);
        assertThat(testEmprendimiento.getManzanas()).isEqualTo(DEFAULT_MANZANAS);
        assertThat(testEmprendimiento.getDemanda()).isEqualTo(DEFAULT_DEMANDA);
        assertThat(testEmprendimiento.getFechaDeRelevamiento()).isEqualTo(DEFAULT_FECHA_DE_RELEVAMIENTO);
        assertThat(testEmprendimiento.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testEmprendimiento.getAnoPriorizacion()).isEqualTo(DEFAULT_ANO_PRIORIZACION);
        assertThat(testEmprendimiento.getContratoOpen()).isEqualTo(DEFAULT_CONTRATO_OPEN);
        assertThat(testEmprendimiento.getNegociacion()).isEqualTo(DEFAULT_NEGOCIACION);
        assertThat(testEmprendimiento.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testEmprendimiento.getCodigoDeFirma()).isEqualTo(DEFAULT_CODIGO_DE_FIRMA);
        assertThat(testEmprendimiento.getFechaFirma()).isEqualTo(DEFAULT_FECHA_FIRMA);
        assertThat(testEmprendimiento.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testEmprendimiento.getComentario()).isEqualTo(DEFAULT_COMENTARIO);
        assertThat(testEmprendimiento.getComenCan()).isEqualTo(DEFAULT_COMEN_CAN);
        assertThat(testEmprendimiento.getEstadoFirma()).isEqualTo(DEFAULT_ESTADO_FIRMA);
        assertThat(testEmprendimiento.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testEmprendimiento.getEstadoBC()).isEqualTo(DEFAULT_ESTADO_BC);
    }

    @Test
    @Transactional
    void createEmprendimientoWithExistingId() throws Exception {
        // Create the Emprendimiento with an existing ID
        emprendimiento.setId(1L);

        int databaseSizeBeforeCreate = emprendimientoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmprendimientoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(emprendimiento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEmprendimientos() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList
        restEmprendimientoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emprendimiento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].contacto").value(hasItem(DEFAULT_CONTACTO)))
            .andExpect(jsonPath("$.[*].fechaFinObra").value(hasItem(DEFAULT_FECHA_FIN_OBRA.toString())))
            .andExpect(jsonPath("$.[*].codigoObra").value(hasItem(DEFAULT_CODIGO_OBRA)))
            .andExpect(jsonPath("$.[*].elementosDeRed").value(hasItem(DEFAULT_ELEMENTOS_DE_RED)))
            .andExpect(jsonPath("$.[*].clientesCatv").value(hasItem(DEFAULT_CLIENTES_CATV)))
            .andExpect(jsonPath("$.[*].clientesFibertel").value(hasItem(DEFAULT_CLIENTES_FIBERTEL)))
            .andExpect(jsonPath("$.[*].clientesFibertelLite").value(hasItem(DEFAULT_CLIENTES_FIBERTEL_LITE)))
            .andExpect(jsonPath("$.[*].clientesFlow").value(hasItem(DEFAULT_CLIENTES_FLOW)))
            .andExpect(jsonPath("$.[*].clientesCombo").value(hasItem(DEFAULT_CLIENTES_COMBO)))
            .andExpect(jsonPath("$.[*].lineasVoz").value(hasItem(DEFAULT_LINEAS_VOZ)))
            .andExpect(jsonPath("$.[*].mesesDeFinalizado").value(hasItem(DEFAULT_MESES_DE_FINALIZADO)))
            .andExpect(jsonPath("$.[*].altasBC").value(hasItem(DEFAULT_ALTAS_BC)))
            .andExpect(jsonPath("$.[*].penetracionVivLot").value(hasItem(DEFAULT_PENETRACION_VIV_LOT)))
            .andExpect(jsonPath("$.[*].penetracionBC").value(hasItem(DEFAULT_PENETRACION_BC)))
            .andExpect(jsonPath("$.[*].demanda1").value(hasItem(DEFAULT_DEMANDA_1)))
            .andExpect(jsonPath("$.[*].demanda2").value(hasItem(DEFAULT_DEMANDA_2)))
            .andExpect(jsonPath("$.[*].demanda3").value(hasItem(DEFAULT_DEMANDA_3)))
            .andExpect(jsonPath("$.[*].demanda4").value(hasItem(DEFAULT_DEMANDA_4)))
            .andExpect(jsonPath("$.[*].demanda5").value(hasItem(DEFAULT_DEMANDA_5)))
            .andExpect(jsonPath("$.[*].lotes").value(hasItem(DEFAULT_LOTES)))
            .andExpect(jsonPath("$.[*].viviendas").value(hasItem(DEFAULT_VIVIENDAS)))
            .andExpect(jsonPath("$.[*].comProf").value(hasItem(DEFAULT_COM_PROF)))
            .andExpect(jsonPath("$.[*].habitaciones").value(hasItem(DEFAULT_HABITACIONES)))
            .andExpect(jsonPath("$.[*].manzanas").value(hasItem(DEFAULT_MANZANAS)))
            .andExpect(jsonPath("$.[*].demanda").value(hasItem(DEFAULT_DEMANDA)))
            .andExpect(jsonPath("$.[*].fechaDeRelevamiento").value(hasItem(DEFAULT_FECHA_DE_RELEVAMIENTO.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].anoPriorizacion").value(hasItem(DEFAULT_ANO_PRIORIZACION.toString())))
            .andExpect(jsonPath("$.[*].contratoOpen").value(hasItem(DEFAULT_CONTRATO_OPEN)))
            .andExpect(jsonPath("$.[*].negociacion").value(hasItem(DEFAULT_NEGOCIACION.booleanValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].codigoDeFirma").value(hasItem(DEFAULT_CODIGO_DE_FIRMA)))
            .andExpect(jsonPath("$.[*].fechaFirma").value(hasItem(DEFAULT_FECHA_FIRMA.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].comentario").value(hasItem(DEFAULT_COMENTARIO)))
            .andExpect(jsonPath("$.[*].comenCan").value(hasItem(DEFAULT_COMEN_CAN)))
            .andExpect(jsonPath("$.[*].estadoFirma").value(hasItem(DEFAULT_ESTADO_FIRMA.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].estadoBC").value(hasItem(DEFAULT_ESTADO_BC.toString())));
    }

    @Test
    @Transactional
    void getEmprendimiento() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get the emprendimiento
        restEmprendimientoMockMvc
            .perform(get(ENTITY_API_URL_ID, emprendimiento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emprendimiento.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.contacto").value(DEFAULT_CONTACTO))
            .andExpect(jsonPath("$.fechaFinObra").value(DEFAULT_FECHA_FIN_OBRA.toString()))
            .andExpect(jsonPath("$.codigoObra").value(DEFAULT_CODIGO_OBRA))
            .andExpect(jsonPath("$.elementosDeRed").value(DEFAULT_ELEMENTOS_DE_RED))
            .andExpect(jsonPath("$.clientesCatv").value(DEFAULT_CLIENTES_CATV))
            .andExpect(jsonPath("$.clientesFibertel").value(DEFAULT_CLIENTES_FIBERTEL))
            .andExpect(jsonPath("$.clientesFibertelLite").value(DEFAULT_CLIENTES_FIBERTEL_LITE))
            .andExpect(jsonPath("$.clientesFlow").value(DEFAULT_CLIENTES_FLOW))
            .andExpect(jsonPath("$.clientesCombo").value(DEFAULT_CLIENTES_COMBO))
            .andExpect(jsonPath("$.lineasVoz").value(DEFAULT_LINEAS_VOZ))
            .andExpect(jsonPath("$.mesesDeFinalizado").value(DEFAULT_MESES_DE_FINALIZADO))
            .andExpect(jsonPath("$.altasBC").value(DEFAULT_ALTAS_BC))
            .andExpect(jsonPath("$.penetracionVivLot").value(DEFAULT_PENETRACION_VIV_LOT))
            .andExpect(jsonPath("$.penetracionBC").value(DEFAULT_PENETRACION_BC))
            .andExpect(jsonPath("$.demanda1").value(DEFAULT_DEMANDA_1))
            .andExpect(jsonPath("$.demanda2").value(DEFAULT_DEMANDA_2))
            .andExpect(jsonPath("$.demanda3").value(DEFAULT_DEMANDA_3))
            .andExpect(jsonPath("$.demanda4").value(DEFAULT_DEMANDA_4))
            .andExpect(jsonPath("$.demanda5").value(DEFAULT_DEMANDA_5))
            .andExpect(jsonPath("$.lotes").value(DEFAULT_LOTES))
            .andExpect(jsonPath("$.viviendas").value(DEFAULT_VIVIENDAS))
            .andExpect(jsonPath("$.comProf").value(DEFAULT_COM_PROF))
            .andExpect(jsonPath("$.habitaciones").value(DEFAULT_HABITACIONES))
            .andExpect(jsonPath("$.manzanas").value(DEFAULT_MANZANAS))
            .andExpect(jsonPath("$.demanda").value(DEFAULT_DEMANDA))
            .andExpect(jsonPath("$.fechaDeRelevamiento").value(DEFAULT_FECHA_DE_RELEVAMIENTO.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.anoPriorizacion").value(DEFAULT_ANO_PRIORIZACION.toString()))
            .andExpect(jsonPath("$.contratoOpen").value(DEFAULT_CONTRATO_OPEN))
            .andExpect(jsonPath("$.negociacion").value(DEFAULT_NEGOCIACION.booleanValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.codigoDeFirma").value(DEFAULT_CODIGO_DE_FIRMA))
            .andExpect(jsonPath("$.fechaFirma").value(DEFAULT_FECHA_FIRMA.toString()))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES))
            .andExpect(jsonPath("$.comentario").value(DEFAULT_COMENTARIO))
            .andExpect(jsonPath("$.comenCan").value(DEFAULT_COMEN_CAN))
            .andExpect(jsonPath("$.estadoFirma").value(DEFAULT_ESTADO_FIRMA.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.estadoBC").value(DEFAULT_ESTADO_BC.toString()));
    }

    @Test
    @Transactional
    void getEmprendimientosByIdFiltering() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        Long id = emprendimiento.getId();

        defaultEmprendimientoShouldBeFound("id.equals=" + id);
        defaultEmprendimientoShouldNotBeFound("id.notEquals=" + id);

        defaultEmprendimientoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEmprendimientoShouldNotBeFound("id.greaterThan=" + id);

        defaultEmprendimientoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEmprendimientoShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where nombre equals to DEFAULT_NOMBRE
        defaultEmprendimientoShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the emprendimientoList where nombre equals to UPDATED_NOMBRE
        defaultEmprendimientoShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where nombre not equals to DEFAULT_NOMBRE
        defaultEmprendimientoShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the emprendimientoList where nombre not equals to UPDATED_NOMBRE
        defaultEmprendimientoShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultEmprendimientoShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the emprendimientoList where nombre equals to UPDATED_NOMBRE
        defaultEmprendimientoShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where nombre is not null
        defaultEmprendimientoShouldBeFound("nombre.specified=true");

        // Get all the emprendimientoList where nombre is null
        defaultEmprendimientoShouldNotBeFound("nombre.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNombreContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where nombre contains DEFAULT_NOMBRE
        defaultEmprendimientoShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the emprendimientoList where nombre contains UPDATED_NOMBRE
        defaultEmprendimientoShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where nombre does not contain DEFAULT_NOMBRE
        defaultEmprendimientoShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the emprendimientoList where nombre does not contain UPDATED_NOMBRE
        defaultEmprendimientoShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContactoIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contacto equals to DEFAULT_CONTACTO
        defaultEmprendimientoShouldBeFound("contacto.equals=" + DEFAULT_CONTACTO);

        // Get all the emprendimientoList where contacto equals to UPDATED_CONTACTO
        defaultEmprendimientoShouldNotBeFound("contacto.equals=" + UPDATED_CONTACTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContactoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contacto not equals to DEFAULT_CONTACTO
        defaultEmprendimientoShouldNotBeFound("contacto.notEquals=" + DEFAULT_CONTACTO);

        // Get all the emprendimientoList where contacto not equals to UPDATED_CONTACTO
        defaultEmprendimientoShouldBeFound("contacto.notEquals=" + UPDATED_CONTACTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContactoIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contacto in DEFAULT_CONTACTO or UPDATED_CONTACTO
        defaultEmprendimientoShouldBeFound("contacto.in=" + DEFAULT_CONTACTO + "," + UPDATED_CONTACTO);

        // Get all the emprendimientoList where contacto equals to UPDATED_CONTACTO
        defaultEmprendimientoShouldNotBeFound("contacto.in=" + UPDATED_CONTACTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContactoIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contacto is not null
        defaultEmprendimientoShouldBeFound("contacto.specified=true");

        // Get all the emprendimientoList where contacto is null
        defaultEmprendimientoShouldNotBeFound("contacto.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContactoContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contacto contains DEFAULT_CONTACTO
        defaultEmprendimientoShouldBeFound("contacto.contains=" + DEFAULT_CONTACTO);

        // Get all the emprendimientoList where contacto contains UPDATED_CONTACTO
        defaultEmprendimientoShouldNotBeFound("contacto.contains=" + UPDATED_CONTACTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContactoNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contacto does not contain DEFAULT_CONTACTO
        defaultEmprendimientoShouldNotBeFound("contacto.doesNotContain=" + DEFAULT_CONTACTO);

        // Get all the emprendimientoList where contacto does not contain UPDATED_CONTACTO
        defaultEmprendimientoShouldBeFound("contacto.doesNotContain=" + UPDATED_CONTACTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFinObraIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFinObra equals to DEFAULT_FECHA_FIN_OBRA
        defaultEmprendimientoShouldBeFound("fechaFinObra.equals=" + DEFAULT_FECHA_FIN_OBRA);

        // Get all the emprendimientoList where fechaFinObra equals to UPDATED_FECHA_FIN_OBRA
        defaultEmprendimientoShouldNotBeFound("fechaFinObra.equals=" + UPDATED_FECHA_FIN_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFinObraIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFinObra not equals to DEFAULT_FECHA_FIN_OBRA
        defaultEmprendimientoShouldNotBeFound("fechaFinObra.notEquals=" + DEFAULT_FECHA_FIN_OBRA);

        // Get all the emprendimientoList where fechaFinObra not equals to UPDATED_FECHA_FIN_OBRA
        defaultEmprendimientoShouldBeFound("fechaFinObra.notEquals=" + UPDATED_FECHA_FIN_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFinObraIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFinObra in DEFAULT_FECHA_FIN_OBRA or UPDATED_FECHA_FIN_OBRA
        defaultEmprendimientoShouldBeFound("fechaFinObra.in=" + DEFAULT_FECHA_FIN_OBRA + "," + UPDATED_FECHA_FIN_OBRA);

        // Get all the emprendimientoList where fechaFinObra equals to UPDATED_FECHA_FIN_OBRA
        defaultEmprendimientoShouldNotBeFound("fechaFinObra.in=" + UPDATED_FECHA_FIN_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFinObraIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFinObra is not null
        defaultEmprendimientoShouldBeFound("fechaFinObra.specified=true");

        // Get all the emprendimientoList where fechaFinObra is null
        defaultEmprendimientoShouldNotBeFound("fechaFinObra.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFinObraIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFinObra is greater than or equal to DEFAULT_FECHA_FIN_OBRA
        defaultEmprendimientoShouldBeFound("fechaFinObra.greaterThanOrEqual=" + DEFAULT_FECHA_FIN_OBRA);

        // Get all the emprendimientoList where fechaFinObra is greater than or equal to UPDATED_FECHA_FIN_OBRA
        defaultEmprendimientoShouldNotBeFound("fechaFinObra.greaterThanOrEqual=" + UPDATED_FECHA_FIN_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFinObraIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFinObra is less than or equal to DEFAULT_FECHA_FIN_OBRA
        defaultEmprendimientoShouldBeFound("fechaFinObra.lessThanOrEqual=" + DEFAULT_FECHA_FIN_OBRA);

        // Get all the emprendimientoList where fechaFinObra is less than or equal to SMALLER_FECHA_FIN_OBRA
        defaultEmprendimientoShouldNotBeFound("fechaFinObra.lessThanOrEqual=" + SMALLER_FECHA_FIN_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFinObraIsLessThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFinObra is less than DEFAULT_FECHA_FIN_OBRA
        defaultEmprendimientoShouldNotBeFound("fechaFinObra.lessThan=" + DEFAULT_FECHA_FIN_OBRA);

        // Get all the emprendimientoList where fechaFinObra is less than UPDATED_FECHA_FIN_OBRA
        defaultEmprendimientoShouldBeFound("fechaFinObra.lessThan=" + UPDATED_FECHA_FIN_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFinObraIsGreaterThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFinObra is greater than DEFAULT_FECHA_FIN_OBRA
        defaultEmprendimientoShouldNotBeFound("fechaFinObra.greaterThan=" + DEFAULT_FECHA_FIN_OBRA);

        // Get all the emprendimientoList where fechaFinObra is greater than SMALLER_FECHA_FIN_OBRA
        defaultEmprendimientoShouldBeFound("fechaFinObra.greaterThan=" + SMALLER_FECHA_FIN_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoObraIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoObra equals to DEFAULT_CODIGO_OBRA
        defaultEmprendimientoShouldBeFound("codigoObra.equals=" + DEFAULT_CODIGO_OBRA);

        // Get all the emprendimientoList where codigoObra equals to UPDATED_CODIGO_OBRA
        defaultEmprendimientoShouldNotBeFound("codigoObra.equals=" + UPDATED_CODIGO_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoObraIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoObra not equals to DEFAULT_CODIGO_OBRA
        defaultEmprendimientoShouldNotBeFound("codigoObra.notEquals=" + DEFAULT_CODIGO_OBRA);

        // Get all the emprendimientoList where codigoObra not equals to UPDATED_CODIGO_OBRA
        defaultEmprendimientoShouldBeFound("codigoObra.notEquals=" + UPDATED_CODIGO_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoObraIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoObra in DEFAULT_CODIGO_OBRA or UPDATED_CODIGO_OBRA
        defaultEmprendimientoShouldBeFound("codigoObra.in=" + DEFAULT_CODIGO_OBRA + "," + UPDATED_CODIGO_OBRA);

        // Get all the emprendimientoList where codigoObra equals to UPDATED_CODIGO_OBRA
        defaultEmprendimientoShouldNotBeFound("codigoObra.in=" + UPDATED_CODIGO_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoObraIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoObra is not null
        defaultEmprendimientoShouldBeFound("codigoObra.specified=true");

        // Get all the emprendimientoList where codigoObra is null
        defaultEmprendimientoShouldNotBeFound("codigoObra.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoObraContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoObra contains DEFAULT_CODIGO_OBRA
        defaultEmprendimientoShouldBeFound("codigoObra.contains=" + DEFAULT_CODIGO_OBRA);

        // Get all the emprendimientoList where codigoObra contains UPDATED_CODIGO_OBRA
        defaultEmprendimientoShouldNotBeFound("codigoObra.contains=" + UPDATED_CODIGO_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoObraNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoObra does not contain DEFAULT_CODIGO_OBRA
        defaultEmprendimientoShouldNotBeFound("codigoObra.doesNotContain=" + DEFAULT_CODIGO_OBRA);

        // Get all the emprendimientoList where codigoObra does not contain UPDATED_CODIGO_OBRA
        defaultEmprendimientoShouldBeFound("codigoObra.doesNotContain=" + UPDATED_CODIGO_OBRA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByElementosDeRedIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where elementosDeRed equals to DEFAULT_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldBeFound("elementosDeRed.equals=" + DEFAULT_ELEMENTOS_DE_RED);

        // Get all the emprendimientoList where elementosDeRed equals to UPDATED_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldNotBeFound("elementosDeRed.equals=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByElementosDeRedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where elementosDeRed not equals to DEFAULT_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldNotBeFound("elementosDeRed.notEquals=" + DEFAULT_ELEMENTOS_DE_RED);

        // Get all the emprendimientoList where elementosDeRed not equals to UPDATED_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldBeFound("elementosDeRed.notEquals=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByElementosDeRedIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where elementosDeRed in DEFAULT_ELEMENTOS_DE_RED or UPDATED_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldBeFound("elementosDeRed.in=" + DEFAULT_ELEMENTOS_DE_RED + "," + UPDATED_ELEMENTOS_DE_RED);

        // Get all the emprendimientoList where elementosDeRed equals to UPDATED_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldNotBeFound("elementosDeRed.in=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByElementosDeRedIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where elementosDeRed is not null
        defaultEmprendimientoShouldBeFound("elementosDeRed.specified=true");

        // Get all the emprendimientoList where elementosDeRed is null
        defaultEmprendimientoShouldNotBeFound("elementosDeRed.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByElementosDeRedContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where elementosDeRed contains DEFAULT_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldBeFound("elementosDeRed.contains=" + DEFAULT_ELEMENTOS_DE_RED);

        // Get all the emprendimientoList where elementosDeRed contains UPDATED_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldNotBeFound("elementosDeRed.contains=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByElementosDeRedNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where elementosDeRed does not contain DEFAULT_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldNotBeFound("elementosDeRed.doesNotContain=" + DEFAULT_ELEMENTOS_DE_RED);

        // Get all the emprendimientoList where elementosDeRed does not contain UPDATED_ELEMENTOS_DE_RED
        defaultEmprendimientoShouldBeFound("elementosDeRed.doesNotContain=" + UPDATED_ELEMENTOS_DE_RED);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesCatvIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCatv equals to DEFAULT_CLIENTES_CATV
        defaultEmprendimientoShouldBeFound("clientesCatv.equals=" + DEFAULT_CLIENTES_CATV);

        // Get all the emprendimientoList where clientesCatv equals to UPDATED_CLIENTES_CATV
        defaultEmprendimientoShouldNotBeFound("clientesCatv.equals=" + UPDATED_CLIENTES_CATV);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesCatvIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCatv not equals to DEFAULT_CLIENTES_CATV
        defaultEmprendimientoShouldNotBeFound("clientesCatv.notEquals=" + DEFAULT_CLIENTES_CATV);

        // Get all the emprendimientoList where clientesCatv not equals to UPDATED_CLIENTES_CATV
        defaultEmprendimientoShouldBeFound("clientesCatv.notEquals=" + UPDATED_CLIENTES_CATV);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesCatvIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCatv in DEFAULT_CLIENTES_CATV or UPDATED_CLIENTES_CATV
        defaultEmprendimientoShouldBeFound("clientesCatv.in=" + DEFAULT_CLIENTES_CATV + "," + UPDATED_CLIENTES_CATV);

        // Get all the emprendimientoList where clientesCatv equals to UPDATED_CLIENTES_CATV
        defaultEmprendimientoShouldNotBeFound("clientesCatv.in=" + UPDATED_CLIENTES_CATV);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesCatvIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCatv is not null
        defaultEmprendimientoShouldBeFound("clientesCatv.specified=true");

        // Get all the emprendimientoList where clientesCatv is null
        defaultEmprendimientoShouldNotBeFound("clientesCatv.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesCatvContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCatv contains DEFAULT_CLIENTES_CATV
        defaultEmprendimientoShouldBeFound("clientesCatv.contains=" + DEFAULT_CLIENTES_CATV);

        // Get all the emprendimientoList where clientesCatv contains UPDATED_CLIENTES_CATV
        defaultEmprendimientoShouldNotBeFound("clientesCatv.contains=" + UPDATED_CLIENTES_CATV);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesCatvNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCatv does not contain DEFAULT_CLIENTES_CATV
        defaultEmprendimientoShouldNotBeFound("clientesCatv.doesNotContain=" + DEFAULT_CLIENTES_CATV);

        // Get all the emprendimientoList where clientesCatv does not contain UPDATED_CLIENTES_CATV
        defaultEmprendimientoShouldBeFound("clientesCatv.doesNotContain=" + UPDATED_CLIENTES_CATV);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertel equals to DEFAULT_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldBeFound("clientesFibertel.equals=" + DEFAULT_CLIENTES_FIBERTEL);

        // Get all the emprendimientoList where clientesFibertel equals to UPDATED_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldNotBeFound("clientesFibertel.equals=" + UPDATED_CLIENTES_FIBERTEL);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertel not equals to DEFAULT_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldNotBeFound("clientesFibertel.notEquals=" + DEFAULT_CLIENTES_FIBERTEL);

        // Get all the emprendimientoList where clientesFibertel not equals to UPDATED_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldBeFound("clientesFibertel.notEquals=" + UPDATED_CLIENTES_FIBERTEL);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertel in DEFAULT_CLIENTES_FIBERTEL or UPDATED_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldBeFound("clientesFibertel.in=" + DEFAULT_CLIENTES_FIBERTEL + "," + UPDATED_CLIENTES_FIBERTEL);

        // Get all the emprendimientoList where clientesFibertel equals to UPDATED_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldNotBeFound("clientesFibertel.in=" + UPDATED_CLIENTES_FIBERTEL);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertel is not null
        defaultEmprendimientoShouldBeFound("clientesFibertel.specified=true");

        // Get all the emprendimientoList where clientesFibertel is null
        defaultEmprendimientoShouldNotBeFound("clientesFibertel.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertel contains DEFAULT_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldBeFound("clientesFibertel.contains=" + DEFAULT_CLIENTES_FIBERTEL);

        // Get all the emprendimientoList where clientesFibertel contains UPDATED_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldNotBeFound("clientesFibertel.contains=" + UPDATED_CLIENTES_FIBERTEL);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertel does not contain DEFAULT_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldNotBeFound("clientesFibertel.doesNotContain=" + DEFAULT_CLIENTES_FIBERTEL);

        // Get all the emprendimientoList where clientesFibertel does not contain UPDATED_CLIENTES_FIBERTEL
        defaultEmprendimientoShouldBeFound("clientesFibertel.doesNotContain=" + UPDATED_CLIENTES_FIBERTEL);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelLiteIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertelLite equals to DEFAULT_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldBeFound("clientesFibertelLite.equals=" + DEFAULT_CLIENTES_FIBERTEL_LITE);

        // Get all the emprendimientoList where clientesFibertelLite equals to UPDATED_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldNotBeFound("clientesFibertelLite.equals=" + UPDATED_CLIENTES_FIBERTEL_LITE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelLiteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertelLite not equals to DEFAULT_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldNotBeFound("clientesFibertelLite.notEquals=" + DEFAULT_CLIENTES_FIBERTEL_LITE);

        // Get all the emprendimientoList where clientesFibertelLite not equals to UPDATED_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldBeFound("clientesFibertelLite.notEquals=" + UPDATED_CLIENTES_FIBERTEL_LITE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelLiteIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertelLite in DEFAULT_CLIENTES_FIBERTEL_LITE or UPDATED_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldBeFound(
            "clientesFibertelLite.in=" + DEFAULT_CLIENTES_FIBERTEL_LITE + "," + UPDATED_CLIENTES_FIBERTEL_LITE
        );

        // Get all the emprendimientoList where clientesFibertelLite equals to UPDATED_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldNotBeFound("clientesFibertelLite.in=" + UPDATED_CLIENTES_FIBERTEL_LITE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelLiteIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertelLite is not null
        defaultEmprendimientoShouldBeFound("clientesFibertelLite.specified=true");

        // Get all the emprendimientoList where clientesFibertelLite is null
        defaultEmprendimientoShouldNotBeFound("clientesFibertelLite.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelLiteContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertelLite contains DEFAULT_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldBeFound("clientesFibertelLite.contains=" + DEFAULT_CLIENTES_FIBERTEL_LITE);

        // Get all the emprendimientoList where clientesFibertelLite contains UPDATED_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldNotBeFound("clientesFibertelLite.contains=" + UPDATED_CLIENTES_FIBERTEL_LITE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFibertelLiteNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFibertelLite does not contain DEFAULT_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldNotBeFound("clientesFibertelLite.doesNotContain=" + DEFAULT_CLIENTES_FIBERTEL_LITE);

        // Get all the emprendimientoList where clientesFibertelLite does not contain UPDATED_CLIENTES_FIBERTEL_LITE
        defaultEmprendimientoShouldBeFound("clientesFibertelLite.doesNotContain=" + UPDATED_CLIENTES_FIBERTEL_LITE);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFlowIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFlow equals to DEFAULT_CLIENTES_FLOW
        defaultEmprendimientoShouldBeFound("clientesFlow.equals=" + DEFAULT_CLIENTES_FLOW);

        // Get all the emprendimientoList where clientesFlow equals to UPDATED_CLIENTES_FLOW
        defaultEmprendimientoShouldNotBeFound("clientesFlow.equals=" + UPDATED_CLIENTES_FLOW);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFlowIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFlow not equals to DEFAULT_CLIENTES_FLOW
        defaultEmprendimientoShouldNotBeFound("clientesFlow.notEquals=" + DEFAULT_CLIENTES_FLOW);

        // Get all the emprendimientoList where clientesFlow not equals to UPDATED_CLIENTES_FLOW
        defaultEmprendimientoShouldBeFound("clientesFlow.notEquals=" + UPDATED_CLIENTES_FLOW);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFlowIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFlow in DEFAULT_CLIENTES_FLOW or UPDATED_CLIENTES_FLOW
        defaultEmprendimientoShouldBeFound("clientesFlow.in=" + DEFAULT_CLIENTES_FLOW + "," + UPDATED_CLIENTES_FLOW);

        // Get all the emprendimientoList where clientesFlow equals to UPDATED_CLIENTES_FLOW
        defaultEmprendimientoShouldNotBeFound("clientesFlow.in=" + UPDATED_CLIENTES_FLOW);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFlowIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFlow is not null
        defaultEmprendimientoShouldBeFound("clientesFlow.specified=true");

        // Get all the emprendimientoList where clientesFlow is null
        defaultEmprendimientoShouldNotBeFound("clientesFlow.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFlowContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFlow contains DEFAULT_CLIENTES_FLOW
        defaultEmprendimientoShouldBeFound("clientesFlow.contains=" + DEFAULT_CLIENTES_FLOW);

        // Get all the emprendimientoList where clientesFlow contains UPDATED_CLIENTES_FLOW
        defaultEmprendimientoShouldNotBeFound("clientesFlow.contains=" + UPDATED_CLIENTES_FLOW);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesFlowNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesFlow does not contain DEFAULT_CLIENTES_FLOW
        defaultEmprendimientoShouldNotBeFound("clientesFlow.doesNotContain=" + DEFAULT_CLIENTES_FLOW);

        // Get all the emprendimientoList where clientesFlow does not contain UPDATED_CLIENTES_FLOW
        defaultEmprendimientoShouldBeFound("clientesFlow.doesNotContain=" + UPDATED_CLIENTES_FLOW);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesComboIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCombo equals to DEFAULT_CLIENTES_COMBO
        defaultEmprendimientoShouldBeFound("clientesCombo.equals=" + DEFAULT_CLIENTES_COMBO);

        // Get all the emprendimientoList where clientesCombo equals to UPDATED_CLIENTES_COMBO
        defaultEmprendimientoShouldNotBeFound("clientesCombo.equals=" + UPDATED_CLIENTES_COMBO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesComboIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCombo not equals to DEFAULT_CLIENTES_COMBO
        defaultEmprendimientoShouldNotBeFound("clientesCombo.notEquals=" + DEFAULT_CLIENTES_COMBO);

        // Get all the emprendimientoList where clientesCombo not equals to UPDATED_CLIENTES_COMBO
        defaultEmprendimientoShouldBeFound("clientesCombo.notEquals=" + UPDATED_CLIENTES_COMBO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesComboIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCombo in DEFAULT_CLIENTES_COMBO or UPDATED_CLIENTES_COMBO
        defaultEmprendimientoShouldBeFound("clientesCombo.in=" + DEFAULT_CLIENTES_COMBO + "," + UPDATED_CLIENTES_COMBO);

        // Get all the emprendimientoList where clientesCombo equals to UPDATED_CLIENTES_COMBO
        defaultEmprendimientoShouldNotBeFound("clientesCombo.in=" + UPDATED_CLIENTES_COMBO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesComboIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCombo is not null
        defaultEmprendimientoShouldBeFound("clientesCombo.specified=true");

        // Get all the emprendimientoList where clientesCombo is null
        defaultEmprendimientoShouldNotBeFound("clientesCombo.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesComboContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCombo contains DEFAULT_CLIENTES_COMBO
        defaultEmprendimientoShouldBeFound("clientesCombo.contains=" + DEFAULT_CLIENTES_COMBO);

        // Get all the emprendimientoList where clientesCombo contains UPDATED_CLIENTES_COMBO
        defaultEmprendimientoShouldNotBeFound("clientesCombo.contains=" + UPDATED_CLIENTES_COMBO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByClientesComboNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where clientesCombo does not contain DEFAULT_CLIENTES_COMBO
        defaultEmprendimientoShouldNotBeFound("clientesCombo.doesNotContain=" + DEFAULT_CLIENTES_COMBO);

        // Get all the emprendimientoList where clientesCombo does not contain UPDATED_CLIENTES_COMBO
        defaultEmprendimientoShouldBeFound("clientesCombo.doesNotContain=" + UPDATED_CLIENTES_COMBO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLineasVozIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lineasVoz equals to DEFAULT_LINEAS_VOZ
        defaultEmprendimientoShouldBeFound("lineasVoz.equals=" + DEFAULT_LINEAS_VOZ);

        // Get all the emprendimientoList where lineasVoz equals to UPDATED_LINEAS_VOZ
        defaultEmprendimientoShouldNotBeFound("lineasVoz.equals=" + UPDATED_LINEAS_VOZ);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLineasVozIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lineasVoz not equals to DEFAULT_LINEAS_VOZ
        defaultEmprendimientoShouldNotBeFound("lineasVoz.notEquals=" + DEFAULT_LINEAS_VOZ);

        // Get all the emprendimientoList where lineasVoz not equals to UPDATED_LINEAS_VOZ
        defaultEmprendimientoShouldBeFound("lineasVoz.notEquals=" + UPDATED_LINEAS_VOZ);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLineasVozIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lineasVoz in DEFAULT_LINEAS_VOZ or UPDATED_LINEAS_VOZ
        defaultEmprendimientoShouldBeFound("lineasVoz.in=" + DEFAULT_LINEAS_VOZ + "," + UPDATED_LINEAS_VOZ);

        // Get all the emprendimientoList where lineasVoz equals to UPDATED_LINEAS_VOZ
        defaultEmprendimientoShouldNotBeFound("lineasVoz.in=" + UPDATED_LINEAS_VOZ);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLineasVozIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lineasVoz is not null
        defaultEmprendimientoShouldBeFound("lineasVoz.specified=true");

        // Get all the emprendimientoList where lineasVoz is null
        defaultEmprendimientoShouldNotBeFound("lineasVoz.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLineasVozContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lineasVoz contains DEFAULT_LINEAS_VOZ
        defaultEmprendimientoShouldBeFound("lineasVoz.contains=" + DEFAULT_LINEAS_VOZ);

        // Get all the emprendimientoList where lineasVoz contains UPDATED_LINEAS_VOZ
        defaultEmprendimientoShouldNotBeFound("lineasVoz.contains=" + UPDATED_LINEAS_VOZ);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLineasVozNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lineasVoz does not contain DEFAULT_LINEAS_VOZ
        defaultEmprendimientoShouldNotBeFound("lineasVoz.doesNotContain=" + DEFAULT_LINEAS_VOZ);

        // Get all the emprendimientoList where lineasVoz does not contain UPDATED_LINEAS_VOZ
        defaultEmprendimientoShouldBeFound("lineasVoz.doesNotContain=" + UPDATED_LINEAS_VOZ);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByMesesDeFinalizadoIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where mesesDeFinalizado equals to DEFAULT_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldBeFound("mesesDeFinalizado.equals=" + DEFAULT_MESES_DE_FINALIZADO);

        // Get all the emprendimientoList where mesesDeFinalizado equals to UPDATED_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldNotBeFound("mesesDeFinalizado.equals=" + UPDATED_MESES_DE_FINALIZADO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByMesesDeFinalizadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where mesesDeFinalizado not equals to DEFAULT_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldNotBeFound("mesesDeFinalizado.notEquals=" + DEFAULT_MESES_DE_FINALIZADO);

        // Get all the emprendimientoList where mesesDeFinalizado not equals to UPDATED_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldBeFound("mesesDeFinalizado.notEquals=" + UPDATED_MESES_DE_FINALIZADO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByMesesDeFinalizadoIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where mesesDeFinalizado in DEFAULT_MESES_DE_FINALIZADO or UPDATED_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldBeFound("mesesDeFinalizado.in=" + DEFAULT_MESES_DE_FINALIZADO + "," + UPDATED_MESES_DE_FINALIZADO);

        // Get all the emprendimientoList where mesesDeFinalizado equals to UPDATED_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldNotBeFound("mesesDeFinalizado.in=" + UPDATED_MESES_DE_FINALIZADO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByMesesDeFinalizadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where mesesDeFinalizado is not null
        defaultEmprendimientoShouldBeFound("mesesDeFinalizado.specified=true");

        // Get all the emprendimientoList where mesesDeFinalizado is null
        defaultEmprendimientoShouldNotBeFound("mesesDeFinalizado.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByMesesDeFinalizadoContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where mesesDeFinalizado contains DEFAULT_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldBeFound("mesesDeFinalizado.contains=" + DEFAULT_MESES_DE_FINALIZADO);

        // Get all the emprendimientoList where mesesDeFinalizado contains UPDATED_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldNotBeFound("mesesDeFinalizado.contains=" + UPDATED_MESES_DE_FINALIZADO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByMesesDeFinalizadoNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where mesesDeFinalizado does not contain DEFAULT_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldNotBeFound("mesesDeFinalizado.doesNotContain=" + DEFAULT_MESES_DE_FINALIZADO);

        // Get all the emprendimientoList where mesesDeFinalizado does not contain UPDATED_MESES_DE_FINALIZADO
        defaultEmprendimientoShouldBeFound("mesesDeFinalizado.doesNotContain=" + UPDATED_MESES_DE_FINALIZADO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAltasBCIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where altasBC equals to DEFAULT_ALTAS_BC
        defaultEmprendimientoShouldBeFound("altasBC.equals=" + DEFAULT_ALTAS_BC);

        // Get all the emprendimientoList where altasBC equals to UPDATED_ALTAS_BC
        defaultEmprendimientoShouldNotBeFound("altasBC.equals=" + UPDATED_ALTAS_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAltasBCIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where altasBC not equals to DEFAULT_ALTAS_BC
        defaultEmprendimientoShouldNotBeFound("altasBC.notEquals=" + DEFAULT_ALTAS_BC);

        // Get all the emprendimientoList where altasBC not equals to UPDATED_ALTAS_BC
        defaultEmprendimientoShouldBeFound("altasBC.notEquals=" + UPDATED_ALTAS_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAltasBCIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where altasBC in DEFAULT_ALTAS_BC or UPDATED_ALTAS_BC
        defaultEmprendimientoShouldBeFound("altasBC.in=" + DEFAULT_ALTAS_BC + "," + UPDATED_ALTAS_BC);

        // Get all the emprendimientoList where altasBC equals to UPDATED_ALTAS_BC
        defaultEmprendimientoShouldNotBeFound("altasBC.in=" + UPDATED_ALTAS_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAltasBCIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where altasBC is not null
        defaultEmprendimientoShouldBeFound("altasBC.specified=true");

        // Get all the emprendimientoList where altasBC is null
        defaultEmprendimientoShouldNotBeFound("altasBC.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAltasBCContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where altasBC contains DEFAULT_ALTAS_BC
        defaultEmprendimientoShouldBeFound("altasBC.contains=" + DEFAULT_ALTAS_BC);

        // Get all the emprendimientoList where altasBC contains UPDATED_ALTAS_BC
        defaultEmprendimientoShouldNotBeFound("altasBC.contains=" + UPDATED_ALTAS_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAltasBCNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where altasBC does not contain DEFAULT_ALTAS_BC
        defaultEmprendimientoShouldNotBeFound("altasBC.doesNotContain=" + DEFAULT_ALTAS_BC);

        // Get all the emprendimientoList where altasBC does not contain UPDATED_ALTAS_BC
        defaultEmprendimientoShouldBeFound("altasBC.doesNotContain=" + UPDATED_ALTAS_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionVivLotIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionVivLot equals to DEFAULT_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldBeFound("penetracionVivLot.equals=" + DEFAULT_PENETRACION_VIV_LOT);

        // Get all the emprendimientoList where penetracionVivLot equals to UPDATED_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldNotBeFound("penetracionVivLot.equals=" + UPDATED_PENETRACION_VIV_LOT);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionVivLotIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionVivLot not equals to DEFAULT_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldNotBeFound("penetracionVivLot.notEquals=" + DEFAULT_PENETRACION_VIV_LOT);

        // Get all the emprendimientoList where penetracionVivLot not equals to UPDATED_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldBeFound("penetracionVivLot.notEquals=" + UPDATED_PENETRACION_VIV_LOT);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionVivLotIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionVivLot in DEFAULT_PENETRACION_VIV_LOT or UPDATED_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldBeFound("penetracionVivLot.in=" + DEFAULT_PENETRACION_VIV_LOT + "," + UPDATED_PENETRACION_VIV_LOT);

        // Get all the emprendimientoList where penetracionVivLot equals to UPDATED_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldNotBeFound("penetracionVivLot.in=" + UPDATED_PENETRACION_VIV_LOT);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionVivLotIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionVivLot is not null
        defaultEmprendimientoShouldBeFound("penetracionVivLot.specified=true");

        // Get all the emprendimientoList where penetracionVivLot is null
        defaultEmprendimientoShouldNotBeFound("penetracionVivLot.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionVivLotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionVivLot contains DEFAULT_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldBeFound("penetracionVivLot.contains=" + DEFAULT_PENETRACION_VIV_LOT);

        // Get all the emprendimientoList where penetracionVivLot contains UPDATED_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldNotBeFound("penetracionVivLot.contains=" + UPDATED_PENETRACION_VIV_LOT);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionVivLotNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionVivLot does not contain DEFAULT_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldNotBeFound("penetracionVivLot.doesNotContain=" + DEFAULT_PENETRACION_VIV_LOT);

        // Get all the emprendimientoList where penetracionVivLot does not contain UPDATED_PENETRACION_VIV_LOT
        defaultEmprendimientoShouldBeFound("penetracionVivLot.doesNotContain=" + UPDATED_PENETRACION_VIV_LOT);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionBCIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionBC equals to DEFAULT_PENETRACION_BC
        defaultEmprendimientoShouldBeFound("penetracionBC.equals=" + DEFAULT_PENETRACION_BC);

        // Get all the emprendimientoList where penetracionBC equals to UPDATED_PENETRACION_BC
        defaultEmprendimientoShouldNotBeFound("penetracionBC.equals=" + UPDATED_PENETRACION_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionBCIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionBC not equals to DEFAULT_PENETRACION_BC
        defaultEmprendimientoShouldNotBeFound("penetracionBC.notEquals=" + DEFAULT_PENETRACION_BC);

        // Get all the emprendimientoList where penetracionBC not equals to UPDATED_PENETRACION_BC
        defaultEmprendimientoShouldBeFound("penetracionBC.notEquals=" + UPDATED_PENETRACION_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionBCIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionBC in DEFAULT_PENETRACION_BC or UPDATED_PENETRACION_BC
        defaultEmprendimientoShouldBeFound("penetracionBC.in=" + DEFAULT_PENETRACION_BC + "," + UPDATED_PENETRACION_BC);

        // Get all the emprendimientoList where penetracionBC equals to UPDATED_PENETRACION_BC
        defaultEmprendimientoShouldNotBeFound("penetracionBC.in=" + UPDATED_PENETRACION_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionBCIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionBC is not null
        defaultEmprendimientoShouldBeFound("penetracionBC.specified=true");

        // Get all the emprendimientoList where penetracionBC is null
        defaultEmprendimientoShouldNotBeFound("penetracionBC.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionBCContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionBC contains DEFAULT_PENETRACION_BC
        defaultEmprendimientoShouldBeFound("penetracionBC.contains=" + DEFAULT_PENETRACION_BC);

        // Get all the emprendimientoList where penetracionBC contains UPDATED_PENETRACION_BC
        defaultEmprendimientoShouldNotBeFound("penetracionBC.contains=" + UPDATED_PENETRACION_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByPenetracionBCNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where penetracionBC does not contain DEFAULT_PENETRACION_BC
        defaultEmprendimientoShouldNotBeFound("penetracionBC.doesNotContain=" + DEFAULT_PENETRACION_BC);

        // Get all the emprendimientoList where penetracionBC does not contain UPDATED_PENETRACION_BC
        defaultEmprendimientoShouldBeFound("penetracionBC.doesNotContain=" + UPDATED_PENETRACION_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda1IsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda1 equals to DEFAULT_DEMANDA_1
        defaultEmprendimientoShouldBeFound("demanda1.equals=" + DEFAULT_DEMANDA_1);

        // Get all the emprendimientoList where demanda1 equals to UPDATED_DEMANDA_1
        defaultEmprendimientoShouldNotBeFound("demanda1.equals=" + UPDATED_DEMANDA_1);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda1 not equals to DEFAULT_DEMANDA_1
        defaultEmprendimientoShouldNotBeFound("demanda1.notEquals=" + DEFAULT_DEMANDA_1);

        // Get all the emprendimientoList where demanda1 not equals to UPDATED_DEMANDA_1
        defaultEmprendimientoShouldBeFound("demanda1.notEquals=" + UPDATED_DEMANDA_1);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda1IsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda1 in DEFAULT_DEMANDA_1 or UPDATED_DEMANDA_1
        defaultEmprendimientoShouldBeFound("demanda1.in=" + DEFAULT_DEMANDA_1 + "," + UPDATED_DEMANDA_1);

        // Get all the emprendimientoList where demanda1 equals to UPDATED_DEMANDA_1
        defaultEmprendimientoShouldNotBeFound("demanda1.in=" + UPDATED_DEMANDA_1);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda1IsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda1 is not null
        defaultEmprendimientoShouldBeFound("demanda1.specified=true");

        // Get all the emprendimientoList where demanda1 is null
        defaultEmprendimientoShouldNotBeFound("demanda1.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda1ContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda1 contains DEFAULT_DEMANDA_1
        defaultEmprendimientoShouldBeFound("demanda1.contains=" + DEFAULT_DEMANDA_1);

        // Get all the emprendimientoList where demanda1 contains UPDATED_DEMANDA_1
        defaultEmprendimientoShouldNotBeFound("demanda1.contains=" + UPDATED_DEMANDA_1);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda1NotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda1 does not contain DEFAULT_DEMANDA_1
        defaultEmprendimientoShouldNotBeFound("demanda1.doesNotContain=" + DEFAULT_DEMANDA_1);

        // Get all the emprendimientoList where demanda1 does not contain UPDATED_DEMANDA_1
        defaultEmprendimientoShouldBeFound("demanda1.doesNotContain=" + UPDATED_DEMANDA_1);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda2IsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda2 equals to DEFAULT_DEMANDA_2
        defaultEmprendimientoShouldBeFound("demanda2.equals=" + DEFAULT_DEMANDA_2);

        // Get all the emprendimientoList where demanda2 equals to UPDATED_DEMANDA_2
        defaultEmprendimientoShouldNotBeFound("demanda2.equals=" + UPDATED_DEMANDA_2);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda2 not equals to DEFAULT_DEMANDA_2
        defaultEmprendimientoShouldNotBeFound("demanda2.notEquals=" + DEFAULT_DEMANDA_2);

        // Get all the emprendimientoList where demanda2 not equals to UPDATED_DEMANDA_2
        defaultEmprendimientoShouldBeFound("demanda2.notEquals=" + UPDATED_DEMANDA_2);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda2IsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda2 in DEFAULT_DEMANDA_2 or UPDATED_DEMANDA_2
        defaultEmprendimientoShouldBeFound("demanda2.in=" + DEFAULT_DEMANDA_2 + "," + UPDATED_DEMANDA_2);

        // Get all the emprendimientoList where demanda2 equals to UPDATED_DEMANDA_2
        defaultEmprendimientoShouldNotBeFound("demanda2.in=" + UPDATED_DEMANDA_2);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda2IsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda2 is not null
        defaultEmprendimientoShouldBeFound("demanda2.specified=true");

        // Get all the emprendimientoList where demanda2 is null
        defaultEmprendimientoShouldNotBeFound("demanda2.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda2ContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda2 contains DEFAULT_DEMANDA_2
        defaultEmprendimientoShouldBeFound("demanda2.contains=" + DEFAULT_DEMANDA_2);

        // Get all the emprendimientoList where demanda2 contains UPDATED_DEMANDA_2
        defaultEmprendimientoShouldNotBeFound("demanda2.contains=" + UPDATED_DEMANDA_2);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda2NotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda2 does not contain DEFAULT_DEMANDA_2
        defaultEmprendimientoShouldNotBeFound("demanda2.doesNotContain=" + DEFAULT_DEMANDA_2);

        // Get all the emprendimientoList where demanda2 does not contain UPDATED_DEMANDA_2
        defaultEmprendimientoShouldBeFound("demanda2.doesNotContain=" + UPDATED_DEMANDA_2);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda3IsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda3 equals to DEFAULT_DEMANDA_3
        defaultEmprendimientoShouldBeFound("demanda3.equals=" + DEFAULT_DEMANDA_3);

        // Get all the emprendimientoList where demanda3 equals to UPDATED_DEMANDA_3
        defaultEmprendimientoShouldNotBeFound("demanda3.equals=" + UPDATED_DEMANDA_3);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda3 not equals to DEFAULT_DEMANDA_3
        defaultEmprendimientoShouldNotBeFound("demanda3.notEquals=" + DEFAULT_DEMANDA_3);

        // Get all the emprendimientoList where demanda3 not equals to UPDATED_DEMANDA_3
        defaultEmprendimientoShouldBeFound("demanda3.notEquals=" + UPDATED_DEMANDA_3);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda3IsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda3 in DEFAULT_DEMANDA_3 or UPDATED_DEMANDA_3
        defaultEmprendimientoShouldBeFound("demanda3.in=" + DEFAULT_DEMANDA_3 + "," + UPDATED_DEMANDA_3);

        // Get all the emprendimientoList where demanda3 equals to UPDATED_DEMANDA_3
        defaultEmprendimientoShouldNotBeFound("demanda3.in=" + UPDATED_DEMANDA_3);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda3IsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda3 is not null
        defaultEmprendimientoShouldBeFound("demanda3.specified=true");

        // Get all the emprendimientoList where demanda3 is null
        defaultEmprendimientoShouldNotBeFound("demanda3.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda3ContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda3 contains DEFAULT_DEMANDA_3
        defaultEmprendimientoShouldBeFound("demanda3.contains=" + DEFAULT_DEMANDA_3);

        // Get all the emprendimientoList where demanda3 contains UPDATED_DEMANDA_3
        defaultEmprendimientoShouldNotBeFound("demanda3.contains=" + UPDATED_DEMANDA_3);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda3NotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda3 does not contain DEFAULT_DEMANDA_3
        defaultEmprendimientoShouldNotBeFound("demanda3.doesNotContain=" + DEFAULT_DEMANDA_3);

        // Get all the emprendimientoList where demanda3 does not contain UPDATED_DEMANDA_3
        defaultEmprendimientoShouldBeFound("demanda3.doesNotContain=" + UPDATED_DEMANDA_3);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda4IsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda4 equals to DEFAULT_DEMANDA_4
        defaultEmprendimientoShouldBeFound("demanda4.equals=" + DEFAULT_DEMANDA_4);

        // Get all the emprendimientoList where demanda4 equals to UPDATED_DEMANDA_4
        defaultEmprendimientoShouldNotBeFound("demanda4.equals=" + UPDATED_DEMANDA_4);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda4 not equals to DEFAULT_DEMANDA_4
        defaultEmprendimientoShouldNotBeFound("demanda4.notEquals=" + DEFAULT_DEMANDA_4);

        // Get all the emprendimientoList where demanda4 not equals to UPDATED_DEMANDA_4
        defaultEmprendimientoShouldBeFound("demanda4.notEquals=" + UPDATED_DEMANDA_4);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda4IsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda4 in DEFAULT_DEMANDA_4 or UPDATED_DEMANDA_4
        defaultEmprendimientoShouldBeFound("demanda4.in=" + DEFAULT_DEMANDA_4 + "," + UPDATED_DEMANDA_4);

        // Get all the emprendimientoList where demanda4 equals to UPDATED_DEMANDA_4
        defaultEmprendimientoShouldNotBeFound("demanda4.in=" + UPDATED_DEMANDA_4);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda4IsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda4 is not null
        defaultEmprendimientoShouldBeFound("demanda4.specified=true");

        // Get all the emprendimientoList where demanda4 is null
        defaultEmprendimientoShouldNotBeFound("demanda4.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda4ContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda4 contains DEFAULT_DEMANDA_4
        defaultEmprendimientoShouldBeFound("demanda4.contains=" + DEFAULT_DEMANDA_4);

        // Get all the emprendimientoList where demanda4 contains UPDATED_DEMANDA_4
        defaultEmprendimientoShouldNotBeFound("demanda4.contains=" + UPDATED_DEMANDA_4);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda4NotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda4 does not contain DEFAULT_DEMANDA_4
        defaultEmprendimientoShouldNotBeFound("demanda4.doesNotContain=" + DEFAULT_DEMANDA_4);

        // Get all the emprendimientoList where demanda4 does not contain UPDATED_DEMANDA_4
        defaultEmprendimientoShouldBeFound("demanda4.doesNotContain=" + UPDATED_DEMANDA_4);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda5IsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda5 equals to DEFAULT_DEMANDA_5
        defaultEmprendimientoShouldBeFound("demanda5.equals=" + DEFAULT_DEMANDA_5);

        // Get all the emprendimientoList where demanda5 equals to UPDATED_DEMANDA_5
        defaultEmprendimientoShouldNotBeFound("demanda5.equals=" + UPDATED_DEMANDA_5);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda5 not equals to DEFAULT_DEMANDA_5
        defaultEmprendimientoShouldNotBeFound("demanda5.notEquals=" + DEFAULT_DEMANDA_5);

        // Get all the emprendimientoList where demanda5 not equals to UPDATED_DEMANDA_5
        defaultEmprendimientoShouldBeFound("demanda5.notEquals=" + UPDATED_DEMANDA_5);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda5IsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda5 in DEFAULT_DEMANDA_5 or UPDATED_DEMANDA_5
        defaultEmprendimientoShouldBeFound("demanda5.in=" + DEFAULT_DEMANDA_5 + "," + UPDATED_DEMANDA_5);

        // Get all the emprendimientoList where demanda5 equals to UPDATED_DEMANDA_5
        defaultEmprendimientoShouldNotBeFound("demanda5.in=" + UPDATED_DEMANDA_5);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda5IsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda5 is not null
        defaultEmprendimientoShouldBeFound("demanda5.specified=true");

        // Get all the emprendimientoList where demanda5 is null
        defaultEmprendimientoShouldNotBeFound("demanda5.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda5ContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda5 contains DEFAULT_DEMANDA_5
        defaultEmprendimientoShouldBeFound("demanda5.contains=" + DEFAULT_DEMANDA_5);

        // Get all the emprendimientoList where demanda5 contains UPDATED_DEMANDA_5
        defaultEmprendimientoShouldNotBeFound("demanda5.contains=" + UPDATED_DEMANDA_5);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemanda5NotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda5 does not contain DEFAULT_DEMANDA_5
        defaultEmprendimientoShouldNotBeFound("demanda5.doesNotContain=" + DEFAULT_DEMANDA_5);

        // Get all the emprendimientoList where demanda5 does not contain UPDATED_DEMANDA_5
        defaultEmprendimientoShouldBeFound("demanda5.doesNotContain=" + UPDATED_DEMANDA_5);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLotesIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lotes equals to DEFAULT_LOTES
        defaultEmprendimientoShouldBeFound("lotes.equals=" + DEFAULT_LOTES);

        // Get all the emprendimientoList where lotes equals to UPDATED_LOTES
        defaultEmprendimientoShouldNotBeFound("lotes.equals=" + UPDATED_LOTES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLotesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lotes not equals to DEFAULT_LOTES
        defaultEmprendimientoShouldNotBeFound("lotes.notEquals=" + DEFAULT_LOTES);

        // Get all the emprendimientoList where lotes not equals to UPDATED_LOTES
        defaultEmprendimientoShouldBeFound("lotes.notEquals=" + UPDATED_LOTES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLotesIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lotes in DEFAULT_LOTES or UPDATED_LOTES
        defaultEmprendimientoShouldBeFound("lotes.in=" + DEFAULT_LOTES + "," + UPDATED_LOTES);

        // Get all the emprendimientoList where lotes equals to UPDATED_LOTES
        defaultEmprendimientoShouldNotBeFound("lotes.in=" + UPDATED_LOTES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLotesIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lotes is not null
        defaultEmprendimientoShouldBeFound("lotes.specified=true");

        // Get all the emprendimientoList where lotes is null
        defaultEmprendimientoShouldNotBeFound("lotes.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLotesContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lotes contains DEFAULT_LOTES
        defaultEmprendimientoShouldBeFound("lotes.contains=" + DEFAULT_LOTES);

        // Get all the emprendimientoList where lotes contains UPDATED_LOTES
        defaultEmprendimientoShouldNotBeFound("lotes.contains=" + UPDATED_LOTES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByLotesNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where lotes does not contain DEFAULT_LOTES
        defaultEmprendimientoShouldNotBeFound("lotes.doesNotContain=" + DEFAULT_LOTES);

        // Get all the emprendimientoList where lotes does not contain UPDATED_LOTES
        defaultEmprendimientoShouldBeFound("lotes.doesNotContain=" + UPDATED_LOTES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByViviendasIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where viviendas equals to DEFAULT_VIVIENDAS
        defaultEmprendimientoShouldBeFound("viviendas.equals=" + DEFAULT_VIVIENDAS);

        // Get all the emprendimientoList where viviendas equals to UPDATED_VIVIENDAS
        defaultEmprendimientoShouldNotBeFound("viviendas.equals=" + UPDATED_VIVIENDAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByViviendasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where viviendas not equals to DEFAULT_VIVIENDAS
        defaultEmprendimientoShouldNotBeFound("viviendas.notEquals=" + DEFAULT_VIVIENDAS);

        // Get all the emprendimientoList where viviendas not equals to UPDATED_VIVIENDAS
        defaultEmprendimientoShouldBeFound("viviendas.notEquals=" + UPDATED_VIVIENDAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByViviendasIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where viviendas in DEFAULT_VIVIENDAS or UPDATED_VIVIENDAS
        defaultEmprendimientoShouldBeFound("viviendas.in=" + DEFAULT_VIVIENDAS + "," + UPDATED_VIVIENDAS);

        // Get all the emprendimientoList where viviendas equals to UPDATED_VIVIENDAS
        defaultEmprendimientoShouldNotBeFound("viviendas.in=" + UPDATED_VIVIENDAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByViviendasIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where viviendas is not null
        defaultEmprendimientoShouldBeFound("viviendas.specified=true");

        // Get all the emprendimientoList where viviendas is null
        defaultEmprendimientoShouldNotBeFound("viviendas.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByViviendasContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where viviendas contains DEFAULT_VIVIENDAS
        defaultEmprendimientoShouldBeFound("viviendas.contains=" + DEFAULT_VIVIENDAS);

        // Get all the emprendimientoList where viviendas contains UPDATED_VIVIENDAS
        defaultEmprendimientoShouldNotBeFound("viviendas.contains=" + UPDATED_VIVIENDAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByViviendasNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where viviendas does not contain DEFAULT_VIVIENDAS
        defaultEmprendimientoShouldNotBeFound("viviendas.doesNotContain=" + DEFAULT_VIVIENDAS);

        // Get all the emprendimientoList where viviendas does not contain UPDATED_VIVIENDAS
        defaultEmprendimientoShouldBeFound("viviendas.doesNotContain=" + UPDATED_VIVIENDAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComProfIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comProf equals to DEFAULT_COM_PROF
        defaultEmprendimientoShouldBeFound("comProf.equals=" + DEFAULT_COM_PROF);

        // Get all the emprendimientoList where comProf equals to UPDATED_COM_PROF
        defaultEmprendimientoShouldNotBeFound("comProf.equals=" + UPDATED_COM_PROF);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComProfIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comProf not equals to DEFAULT_COM_PROF
        defaultEmprendimientoShouldNotBeFound("comProf.notEquals=" + DEFAULT_COM_PROF);

        // Get all the emprendimientoList where comProf not equals to UPDATED_COM_PROF
        defaultEmprendimientoShouldBeFound("comProf.notEquals=" + UPDATED_COM_PROF);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComProfIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comProf in DEFAULT_COM_PROF or UPDATED_COM_PROF
        defaultEmprendimientoShouldBeFound("comProf.in=" + DEFAULT_COM_PROF + "," + UPDATED_COM_PROF);

        // Get all the emprendimientoList where comProf equals to UPDATED_COM_PROF
        defaultEmprendimientoShouldNotBeFound("comProf.in=" + UPDATED_COM_PROF);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComProfIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comProf is not null
        defaultEmprendimientoShouldBeFound("comProf.specified=true");

        // Get all the emprendimientoList where comProf is null
        defaultEmprendimientoShouldNotBeFound("comProf.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComProfContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comProf contains DEFAULT_COM_PROF
        defaultEmprendimientoShouldBeFound("comProf.contains=" + DEFAULT_COM_PROF);

        // Get all the emprendimientoList where comProf contains UPDATED_COM_PROF
        defaultEmprendimientoShouldNotBeFound("comProf.contains=" + UPDATED_COM_PROF);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComProfNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comProf does not contain DEFAULT_COM_PROF
        defaultEmprendimientoShouldNotBeFound("comProf.doesNotContain=" + DEFAULT_COM_PROF);

        // Get all the emprendimientoList where comProf does not contain UPDATED_COM_PROF
        defaultEmprendimientoShouldBeFound("comProf.doesNotContain=" + UPDATED_COM_PROF);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByHabitacionesIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where habitaciones equals to DEFAULT_HABITACIONES
        defaultEmprendimientoShouldBeFound("habitaciones.equals=" + DEFAULT_HABITACIONES);

        // Get all the emprendimientoList where habitaciones equals to UPDATED_HABITACIONES
        defaultEmprendimientoShouldNotBeFound("habitaciones.equals=" + UPDATED_HABITACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByHabitacionesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where habitaciones not equals to DEFAULT_HABITACIONES
        defaultEmprendimientoShouldNotBeFound("habitaciones.notEquals=" + DEFAULT_HABITACIONES);

        // Get all the emprendimientoList where habitaciones not equals to UPDATED_HABITACIONES
        defaultEmprendimientoShouldBeFound("habitaciones.notEquals=" + UPDATED_HABITACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByHabitacionesIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where habitaciones in DEFAULT_HABITACIONES or UPDATED_HABITACIONES
        defaultEmprendimientoShouldBeFound("habitaciones.in=" + DEFAULT_HABITACIONES + "," + UPDATED_HABITACIONES);

        // Get all the emprendimientoList where habitaciones equals to UPDATED_HABITACIONES
        defaultEmprendimientoShouldNotBeFound("habitaciones.in=" + UPDATED_HABITACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByHabitacionesIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where habitaciones is not null
        defaultEmprendimientoShouldBeFound("habitaciones.specified=true");

        // Get all the emprendimientoList where habitaciones is null
        defaultEmprendimientoShouldNotBeFound("habitaciones.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByHabitacionesContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where habitaciones contains DEFAULT_HABITACIONES
        defaultEmprendimientoShouldBeFound("habitaciones.contains=" + DEFAULT_HABITACIONES);

        // Get all the emprendimientoList where habitaciones contains UPDATED_HABITACIONES
        defaultEmprendimientoShouldNotBeFound("habitaciones.contains=" + UPDATED_HABITACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByHabitacionesNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where habitaciones does not contain DEFAULT_HABITACIONES
        defaultEmprendimientoShouldNotBeFound("habitaciones.doesNotContain=" + DEFAULT_HABITACIONES);

        // Get all the emprendimientoList where habitaciones does not contain UPDATED_HABITACIONES
        defaultEmprendimientoShouldBeFound("habitaciones.doesNotContain=" + UPDATED_HABITACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByManzanasIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where manzanas equals to DEFAULT_MANZANAS
        defaultEmprendimientoShouldBeFound("manzanas.equals=" + DEFAULT_MANZANAS);

        // Get all the emprendimientoList where manzanas equals to UPDATED_MANZANAS
        defaultEmprendimientoShouldNotBeFound("manzanas.equals=" + UPDATED_MANZANAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByManzanasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where manzanas not equals to DEFAULT_MANZANAS
        defaultEmprendimientoShouldNotBeFound("manzanas.notEquals=" + DEFAULT_MANZANAS);

        // Get all the emprendimientoList where manzanas not equals to UPDATED_MANZANAS
        defaultEmprendimientoShouldBeFound("manzanas.notEquals=" + UPDATED_MANZANAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByManzanasIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where manzanas in DEFAULT_MANZANAS or UPDATED_MANZANAS
        defaultEmprendimientoShouldBeFound("manzanas.in=" + DEFAULT_MANZANAS + "," + UPDATED_MANZANAS);

        // Get all the emprendimientoList where manzanas equals to UPDATED_MANZANAS
        defaultEmprendimientoShouldNotBeFound("manzanas.in=" + UPDATED_MANZANAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByManzanasIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where manzanas is not null
        defaultEmprendimientoShouldBeFound("manzanas.specified=true");

        // Get all the emprendimientoList where manzanas is null
        defaultEmprendimientoShouldNotBeFound("manzanas.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByManzanasContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where manzanas contains DEFAULT_MANZANAS
        defaultEmprendimientoShouldBeFound("manzanas.contains=" + DEFAULT_MANZANAS);

        // Get all the emprendimientoList where manzanas contains UPDATED_MANZANAS
        defaultEmprendimientoShouldNotBeFound("manzanas.contains=" + UPDATED_MANZANAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByManzanasNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where manzanas does not contain DEFAULT_MANZANAS
        defaultEmprendimientoShouldNotBeFound("manzanas.doesNotContain=" + DEFAULT_MANZANAS);

        // Get all the emprendimientoList where manzanas does not contain UPDATED_MANZANAS
        defaultEmprendimientoShouldBeFound("manzanas.doesNotContain=" + UPDATED_MANZANAS);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemandaIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda equals to DEFAULT_DEMANDA
        defaultEmprendimientoShouldBeFound("demanda.equals=" + DEFAULT_DEMANDA);

        // Get all the emprendimientoList where demanda equals to UPDATED_DEMANDA
        defaultEmprendimientoShouldNotBeFound("demanda.equals=" + UPDATED_DEMANDA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemandaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda not equals to DEFAULT_DEMANDA
        defaultEmprendimientoShouldNotBeFound("demanda.notEquals=" + DEFAULT_DEMANDA);

        // Get all the emprendimientoList where demanda not equals to UPDATED_DEMANDA
        defaultEmprendimientoShouldBeFound("demanda.notEquals=" + UPDATED_DEMANDA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemandaIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda in DEFAULT_DEMANDA or UPDATED_DEMANDA
        defaultEmprendimientoShouldBeFound("demanda.in=" + DEFAULT_DEMANDA + "," + UPDATED_DEMANDA);

        // Get all the emprendimientoList where demanda equals to UPDATED_DEMANDA
        defaultEmprendimientoShouldNotBeFound("demanda.in=" + UPDATED_DEMANDA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemandaIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda is not null
        defaultEmprendimientoShouldBeFound("demanda.specified=true");

        // Get all the emprendimientoList where demanda is null
        defaultEmprendimientoShouldNotBeFound("demanda.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemandaContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda contains DEFAULT_DEMANDA
        defaultEmprendimientoShouldBeFound("demanda.contains=" + DEFAULT_DEMANDA);

        // Get all the emprendimientoList where demanda contains UPDATED_DEMANDA
        defaultEmprendimientoShouldNotBeFound("demanda.contains=" + UPDATED_DEMANDA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDemandaNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where demanda does not contain DEFAULT_DEMANDA
        defaultEmprendimientoShouldNotBeFound("demanda.doesNotContain=" + DEFAULT_DEMANDA);

        // Get all the emprendimientoList where demanda does not contain UPDATED_DEMANDA
        defaultEmprendimientoShouldBeFound("demanda.doesNotContain=" + UPDATED_DEMANDA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaDeRelevamientoIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaDeRelevamiento equals to DEFAULT_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldBeFound("fechaDeRelevamiento.equals=" + DEFAULT_FECHA_DE_RELEVAMIENTO);

        // Get all the emprendimientoList where fechaDeRelevamiento equals to UPDATED_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldNotBeFound("fechaDeRelevamiento.equals=" + UPDATED_FECHA_DE_RELEVAMIENTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaDeRelevamientoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaDeRelevamiento not equals to DEFAULT_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldNotBeFound("fechaDeRelevamiento.notEquals=" + DEFAULT_FECHA_DE_RELEVAMIENTO);

        // Get all the emprendimientoList where fechaDeRelevamiento not equals to UPDATED_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldBeFound("fechaDeRelevamiento.notEquals=" + UPDATED_FECHA_DE_RELEVAMIENTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaDeRelevamientoIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaDeRelevamiento in DEFAULT_FECHA_DE_RELEVAMIENTO or UPDATED_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldBeFound("fechaDeRelevamiento.in=" + DEFAULT_FECHA_DE_RELEVAMIENTO + "," + UPDATED_FECHA_DE_RELEVAMIENTO);

        // Get all the emprendimientoList where fechaDeRelevamiento equals to UPDATED_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldNotBeFound("fechaDeRelevamiento.in=" + UPDATED_FECHA_DE_RELEVAMIENTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaDeRelevamientoIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaDeRelevamiento is not null
        defaultEmprendimientoShouldBeFound("fechaDeRelevamiento.specified=true");

        // Get all the emprendimientoList where fechaDeRelevamiento is null
        defaultEmprendimientoShouldNotBeFound("fechaDeRelevamiento.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaDeRelevamientoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaDeRelevamiento is greater than or equal to DEFAULT_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldBeFound("fechaDeRelevamiento.greaterThanOrEqual=" + DEFAULT_FECHA_DE_RELEVAMIENTO);

        // Get all the emprendimientoList where fechaDeRelevamiento is greater than or equal to UPDATED_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldNotBeFound("fechaDeRelevamiento.greaterThanOrEqual=" + UPDATED_FECHA_DE_RELEVAMIENTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaDeRelevamientoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaDeRelevamiento is less than or equal to DEFAULT_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldBeFound("fechaDeRelevamiento.lessThanOrEqual=" + DEFAULT_FECHA_DE_RELEVAMIENTO);

        // Get all the emprendimientoList where fechaDeRelevamiento is less than or equal to SMALLER_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldNotBeFound("fechaDeRelevamiento.lessThanOrEqual=" + SMALLER_FECHA_DE_RELEVAMIENTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaDeRelevamientoIsLessThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaDeRelevamiento is less than DEFAULT_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldNotBeFound("fechaDeRelevamiento.lessThan=" + DEFAULT_FECHA_DE_RELEVAMIENTO);

        // Get all the emprendimientoList where fechaDeRelevamiento is less than UPDATED_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldBeFound("fechaDeRelevamiento.lessThan=" + UPDATED_FECHA_DE_RELEVAMIENTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaDeRelevamientoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaDeRelevamiento is greater than DEFAULT_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldNotBeFound("fechaDeRelevamiento.greaterThan=" + DEFAULT_FECHA_DE_RELEVAMIENTO);

        // Get all the emprendimientoList where fechaDeRelevamiento is greater than SMALLER_FECHA_DE_RELEVAMIENTO
        defaultEmprendimientoShouldBeFound("fechaDeRelevamiento.greaterThan=" + SMALLER_FECHA_DE_RELEVAMIENTO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where telefono equals to DEFAULT_TELEFONO
        defaultEmprendimientoShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the emprendimientoList where telefono equals to UPDATED_TELEFONO
        defaultEmprendimientoShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTelefonoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where telefono not equals to DEFAULT_TELEFONO
        defaultEmprendimientoShouldNotBeFound("telefono.notEquals=" + DEFAULT_TELEFONO);

        // Get all the emprendimientoList where telefono not equals to UPDATED_TELEFONO
        defaultEmprendimientoShouldBeFound("telefono.notEquals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultEmprendimientoShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the emprendimientoList where telefono equals to UPDATED_TELEFONO
        defaultEmprendimientoShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where telefono is not null
        defaultEmprendimientoShouldBeFound("telefono.specified=true");

        // Get all the emprendimientoList where telefono is null
        defaultEmprendimientoShouldNotBeFound("telefono.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTelefonoContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where telefono contains DEFAULT_TELEFONO
        defaultEmprendimientoShouldBeFound("telefono.contains=" + DEFAULT_TELEFONO);

        // Get all the emprendimientoList where telefono contains UPDATED_TELEFONO
        defaultEmprendimientoShouldNotBeFound("telefono.contains=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTelefonoNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where telefono does not contain DEFAULT_TELEFONO
        defaultEmprendimientoShouldNotBeFound("telefono.doesNotContain=" + DEFAULT_TELEFONO);

        // Get all the emprendimientoList where telefono does not contain UPDATED_TELEFONO
        defaultEmprendimientoShouldBeFound("telefono.doesNotContain=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAnoPriorizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where anoPriorizacion equals to DEFAULT_ANO_PRIORIZACION
        defaultEmprendimientoShouldBeFound("anoPriorizacion.equals=" + DEFAULT_ANO_PRIORIZACION);

        // Get all the emprendimientoList where anoPriorizacion equals to UPDATED_ANO_PRIORIZACION
        defaultEmprendimientoShouldNotBeFound("anoPriorizacion.equals=" + UPDATED_ANO_PRIORIZACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAnoPriorizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where anoPriorizacion not equals to DEFAULT_ANO_PRIORIZACION
        defaultEmprendimientoShouldNotBeFound("anoPriorizacion.notEquals=" + DEFAULT_ANO_PRIORIZACION);

        // Get all the emprendimientoList where anoPriorizacion not equals to UPDATED_ANO_PRIORIZACION
        defaultEmprendimientoShouldBeFound("anoPriorizacion.notEquals=" + UPDATED_ANO_PRIORIZACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAnoPriorizacionIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where anoPriorizacion in DEFAULT_ANO_PRIORIZACION or UPDATED_ANO_PRIORIZACION
        defaultEmprendimientoShouldBeFound("anoPriorizacion.in=" + DEFAULT_ANO_PRIORIZACION + "," + UPDATED_ANO_PRIORIZACION);

        // Get all the emprendimientoList where anoPriorizacion equals to UPDATED_ANO_PRIORIZACION
        defaultEmprendimientoShouldNotBeFound("anoPriorizacion.in=" + UPDATED_ANO_PRIORIZACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAnoPriorizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where anoPriorizacion is not null
        defaultEmprendimientoShouldBeFound("anoPriorizacion.specified=true");

        // Get all the emprendimientoList where anoPriorizacion is null
        defaultEmprendimientoShouldNotBeFound("anoPriorizacion.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAnoPriorizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where anoPriorizacion is greater than or equal to DEFAULT_ANO_PRIORIZACION
        defaultEmprendimientoShouldBeFound("anoPriorizacion.greaterThanOrEqual=" + DEFAULT_ANO_PRIORIZACION);

        // Get all the emprendimientoList where anoPriorizacion is greater than or equal to UPDATED_ANO_PRIORIZACION
        defaultEmprendimientoShouldNotBeFound("anoPriorizacion.greaterThanOrEqual=" + UPDATED_ANO_PRIORIZACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAnoPriorizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where anoPriorizacion is less than or equal to DEFAULT_ANO_PRIORIZACION
        defaultEmprendimientoShouldBeFound("anoPriorizacion.lessThanOrEqual=" + DEFAULT_ANO_PRIORIZACION);

        // Get all the emprendimientoList where anoPriorizacion is less than or equal to SMALLER_ANO_PRIORIZACION
        defaultEmprendimientoShouldNotBeFound("anoPriorizacion.lessThanOrEqual=" + SMALLER_ANO_PRIORIZACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAnoPriorizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where anoPriorizacion is less than DEFAULT_ANO_PRIORIZACION
        defaultEmprendimientoShouldNotBeFound("anoPriorizacion.lessThan=" + DEFAULT_ANO_PRIORIZACION);

        // Get all the emprendimientoList where anoPriorizacion is less than UPDATED_ANO_PRIORIZACION
        defaultEmprendimientoShouldBeFound("anoPriorizacion.lessThan=" + UPDATED_ANO_PRIORIZACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByAnoPriorizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where anoPriorizacion is greater than DEFAULT_ANO_PRIORIZACION
        defaultEmprendimientoShouldNotBeFound("anoPriorizacion.greaterThan=" + DEFAULT_ANO_PRIORIZACION);

        // Get all the emprendimientoList where anoPriorizacion is greater than SMALLER_ANO_PRIORIZACION
        defaultEmprendimientoShouldBeFound("anoPriorizacion.greaterThan=" + SMALLER_ANO_PRIORIZACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContratoOpenIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contratoOpen equals to DEFAULT_CONTRATO_OPEN
        defaultEmprendimientoShouldBeFound("contratoOpen.equals=" + DEFAULT_CONTRATO_OPEN);

        // Get all the emprendimientoList where contratoOpen equals to UPDATED_CONTRATO_OPEN
        defaultEmprendimientoShouldNotBeFound("contratoOpen.equals=" + UPDATED_CONTRATO_OPEN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContratoOpenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contratoOpen not equals to DEFAULT_CONTRATO_OPEN
        defaultEmprendimientoShouldNotBeFound("contratoOpen.notEquals=" + DEFAULT_CONTRATO_OPEN);

        // Get all the emprendimientoList where contratoOpen not equals to UPDATED_CONTRATO_OPEN
        defaultEmprendimientoShouldBeFound("contratoOpen.notEquals=" + UPDATED_CONTRATO_OPEN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContratoOpenIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contratoOpen in DEFAULT_CONTRATO_OPEN or UPDATED_CONTRATO_OPEN
        defaultEmprendimientoShouldBeFound("contratoOpen.in=" + DEFAULT_CONTRATO_OPEN + "," + UPDATED_CONTRATO_OPEN);

        // Get all the emprendimientoList where contratoOpen equals to UPDATED_CONTRATO_OPEN
        defaultEmprendimientoShouldNotBeFound("contratoOpen.in=" + UPDATED_CONTRATO_OPEN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContratoOpenIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contratoOpen is not null
        defaultEmprendimientoShouldBeFound("contratoOpen.specified=true");

        // Get all the emprendimientoList where contratoOpen is null
        defaultEmprendimientoShouldNotBeFound("contratoOpen.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContratoOpenContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contratoOpen contains DEFAULT_CONTRATO_OPEN
        defaultEmprendimientoShouldBeFound("contratoOpen.contains=" + DEFAULT_CONTRATO_OPEN);

        // Get all the emprendimientoList where contratoOpen contains UPDATED_CONTRATO_OPEN
        defaultEmprendimientoShouldNotBeFound("contratoOpen.contains=" + UPDATED_CONTRATO_OPEN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByContratoOpenNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where contratoOpen does not contain DEFAULT_CONTRATO_OPEN
        defaultEmprendimientoShouldNotBeFound("contratoOpen.doesNotContain=" + DEFAULT_CONTRATO_OPEN);

        // Get all the emprendimientoList where contratoOpen does not contain UPDATED_CONTRATO_OPEN
        defaultEmprendimientoShouldBeFound("contratoOpen.doesNotContain=" + UPDATED_CONTRATO_OPEN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNegociacionIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where negociacion equals to DEFAULT_NEGOCIACION
        defaultEmprendimientoShouldBeFound("negociacion.equals=" + DEFAULT_NEGOCIACION);

        // Get all the emprendimientoList where negociacion equals to UPDATED_NEGOCIACION
        defaultEmprendimientoShouldNotBeFound("negociacion.equals=" + UPDATED_NEGOCIACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNegociacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where negociacion not equals to DEFAULT_NEGOCIACION
        defaultEmprendimientoShouldNotBeFound("negociacion.notEquals=" + DEFAULT_NEGOCIACION);

        // Get all the emprendimientoList where negociacion not equals to UPDATED_NEGOCIACION
        defaultEmprendimientoShouldBeFound("negociacion.notEquals=" + UPDATED_NEGOCIACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNegociacionIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where negociacion in DEFAULT_NEGOCIACION or UPDATED_NEGOCIACION
        defaultEmprendimientoShouldBeFound("negociacion.in=" + DEFAULT_NEGOCIACION + "," + UPDATED_NEGOCIACION);

        // Get all the emprendimientoList where negociacion equals to UPDATED_NEGOCIACION
        defaultEmprendimientoShouldNotBeFound("negociacion.in=" + UPDATED_NEGOCIACION);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNegociacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where negociacion is not null
        defaultEmprendimientoShouldBeFound("negociacion.specified=true");

        // Get all the emprendimientoList where negociacion is null
        defaultEmprendimientoShouldNotBeFound("negociacion.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fecha equals to DEFAULT_FECHA
        defaultEmprendimientoShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the emprendimientoList where fecha equals to UPDATED_FECHA
        defaultEmprendimientoShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fecha not equals to DEFAULT_FECHA
        defaultEmprendimientoShouldNotBeFound("fecha.notEquals=" + DEFAULT_FECHA);

        // Get all the emprendimientoList where fecha not equals to UPDATED_FECHA
        defaultEmprendimientoShouldBeFound("fecha.notEquals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultEmprendimientoShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the emprendimientoList where fecha equals to UPDATED_FECHA
        defaultEmprendimientoShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fecha is not null
        defaultEmprendimientoShouldBeFound("fecha.specified=true");

        // Get all the emprendimientoList where fecha is null
        defaultEmprendimientoShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fecha is greater than or equal to DEFAULT_FECHA
        defaultEmprendimientoShouldBeFound("fecha.greaterThanOrEqual=" + DEFAULT_FECHA);

        // Get all the emprendimientoList where fecha is greater than or equal to UPDATED_FECHA
        defaultEmprendimientoShouldNotBeFound("fecha.greaterThanOrEqual=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fecha is less than or equal to DEFAULT_FECHA
        defaultEmprendimientoShouldBeFound("fecha.lessThanOrEqual=" + DEFAULT_FECHA);

        // Get all the emprendimientoList where fecha is less than or equal to SMALLER_FECHA
        defaultEmprendimientoShouldNotBeFound("fecha.lessThanOrEqual=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fecha is less than DEFAULT_FECHA
        defaultEmprendimientoShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the emprendimientoList where fecha is less than UPDATED_FECHA
        defaultEmprendimientoShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fecha is greater than DEFAULT_FECHA
        defaultEmprendimientoShouldNotBeFound("fecha.greaterThan=" + DEFAULT_FECHA);

        // Get all the emprendimientoList where fecha is greater than SMALLER_FECHA
        defaultEmprendimientoShouldBeFound("fecha.greaterThan=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoDeFirmaIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoDeFirma equals to DEFAULT_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldBeFound("codigoDeFirma.equals=" + DEFAULT_CODIGO_DE_FIRMA);

        // Get all the emprendimientoList where codigoDeFirma equals to UPDATED_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldNotBeFound("codigoDeFirma.equals=" + UPDATED_CODIGO_DE_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoDeFirmaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoDeFirma not equals to DEFAULT_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldNotBeFound("codigoDeFirma.notEquals=" + DEFAULT_CODIGO_DE_FIRMA);

        // Get all the emprendimientoList where codigoDeFirma not equals to UPDATED_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldBeFound("codigoDeFirma.notEquals=" + UPDATED_CODIGO_DE_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoDeFirmaIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoDeFirma in DEFAULT_CODIGO_DE_FIRMA or UPDATED_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldBeFound("codigoDeFirma.in=" + DEFAULT_CODIGO_DE_FIRMA + "," + UPDATED_CODIGO_DE_FIRMA);

        // Get all the emprendimientoList where codigoDeFirma equals to UPDATED_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldNotBeFound("codigoDeFirma.in=" + UPDATED_CODIGO_DE_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoDeFirmaIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoDeFirma is not null
        defaultEmprendimientoShouldBeFound("codigoDeFirma.specified=true");

        // Get all the emprendimientoList where codigoDeFirma is null
        defaultEmprendimientoShouldNotBeFound("codigoDeFirma.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoDeFirmaContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoDeFirma contains DEFAULT_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldBeFound("codigoDeFirma.contains=" + DEFAULT_CODIGO_DE_FIRMA);

        // Get all the emprendimientoList where codigoDeFirma contains UPDATED_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldNotBeFound("codigoDeFirma.contains=" + UPDATED_CODIGO_DE_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByCodigoDeFirmaNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where codigoDeFirma does not contain DEFAULT_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldNotBeFound("codigoDeFirma.doesNotContain=" + DEFAULT_CODIGO_DE_FIRMA);

        // Get all the emprendimientoList where codigoDeFirma does not contain UPDATED_CODIGO_DE_FIRMA
        defaultEmprendimientoShouldBeFound("codigoDeFirma.doesNotContain=" + UPDATED_CODIGO_DE_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFirmaIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFirma equals to DEFAULT_FECHA_FIRMA
        defaultEmprendimientoShouldBeFound("fechaFirma.equals=" + DEFAULT_FECHA_FIRMA);

        // Get all the emprendimientoList where fechaFirma equals to UPDATED_FECHA_FIRMA
        defaultEmprendimientoShouldNotBeFound("fechaFirma.equals=" + UPDATED_FECHA_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFirmaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFirma not equals to DEFAULT_FECHA_FIRMA
        defaultEmprendimientoShouldNotBeFound("fechaFirma.notEquals=" + DEFAULT_FECHA_FIRMA);

        // Get all the emprendimientoList where fechaFirma not equals to UPDATED_FECHA_FIRMA
        defaultEmprendimientoShouldBeFound("fechaFirma.notEquals=" + UPDATED_FECHA_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFirmaIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFirma in DEFAULT_FECHA_FIRMA or UPDATED_FECHA_FIRMA
        defaultEmprendimientoShouldBeFound("fechaFirma.in=" + DEFAULT_FECHA_FIRMA + "," + UPDATED_FECHA_FIRMA);

        // Get all the emprendimientoList where fechaFirma equals to UPDATED_FECHA_FIRMA
        defaultEmprendimientoShouldNotBeFound("fechaFirma.in=" + UPDATED_FECHA_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFirmaIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFirma is not null
        defaultEmprendimientoShouldBeFound("fechaFirma.specified=true");

        // Get all the emprendimientoList where fechaFirma is null
        defaultEmprendimientoShouldNotBeFound("fechaFirma.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFirmaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFirma is greater than or equal to DEFAULT_FECHA_FIRMA
        defaultEmprendimientoShouldBeFound("fechaFirma.greaterThanOrEqual=" + DEFAULT_FECHA_FIRMA);

        // Get all the emprendimientoList where fechaFirma is greater than or equal to UPDATED_FECHA_FIRMA
        defaultEmprendimientoShouldNotBeFound("fechaFirma.greaterThanOrEqual=" + UPDATED_FECHA_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFirmaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFirma is less than or equal to DEFAULT_FECHA_FIRMA
        defaultEmprendimientoShouldBeFound("fechaFirma.lessThanOrEqual=" + DEFAULT_FECHA_FIRMA);

        // Get all the emprendimientoList where fechaFirma is less than or equal to SMALLER_FECHA_FIRMA
        defaultEmprendimientoShouldNotBeFound("fechaFirma.lessThanOrEqual=" + SMALLER_FECHA_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFirmaIsLessThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFirma is less than DEFAULT_FECHA_FIRMA
        defaultEmprendimientoShouldNotBeFound("fechaFirma.lessThan=" + DEFAULT_FECHA_FIRMA);

        // Get all the emprendimientoList where fechaFirma is less than UPDATED_FECHA_FIRMA
        defaultEmprendimientoShouldBeFound("fechaFirma.lessThan=" + UPDATED_FECHA_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByFechaFirmaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where fechaFirma is greater than DEFAULT_FECHA_FIRMA
        defaultEmprendimientoShouldNotBeFound("fechaFirma.greaterThan=" + DEFAULT_FECHA_FIRMA);

        // Get all the emprendimientoList where fechaFirma is greater than SMALLER_FECHA_FIRMA
        defaultEmprendimientoShouldBeFound("fechaFirma.greaterThan=" + SMALLER_FECHA_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByObservacionesIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where observaciones equals to DEFAULT_OBSERVACIONES
        defaultEmprendimientoShouldBeFound("observaciones.equals=" + DEFAULT_OBSERVACIONES);

        // Get all the emprendimientoList where observaciones equals to UPDATED_OBSERVACIONES
        defaultEmprendimientoShouldNotBeFound("observaciones.equals=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByObservacionesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where observaciones not equals to DEFAULT_OBSERVACIONES
        defaultEmprendimientoShouldNotBeFound("observaciones.notEquals=" + DEFAULT_OBSERVACIONES);

        // Get all the emprendimientoList where observaciones not equals to UPDATED_OBSERVACIONES
        defaultEmprendimientoShouldBeFound("observaciones.notEquals=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByObservacionesIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where observaciones in DEFAULT_OBSERVACIONES or UPDATED_OBSERVACIONES
        defaultEmprendimientoShouldBeFound("observaciones.in=" + DEFAULT_OBSERVACIONES + "," + UPDATED_OBSERVACIONES);

        // Get all the emprendimientoList where observaciones equals to UPDATED_OBSERVACIONES
        defaultEmprendimientoShouldNotBeFound("observaciones.in=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByObservacionesIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where observaciones is not null
        defaultEmprendimientoShouldBeFound("observaciones.specified=true");

        // Get all the emprendimientoList where observaciones is null
        defaultEmprendimientoShouldNotBeFound("observaciones.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByObservacionesContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where observaciones contains DEFAULT_OBSERVACIONES
        defaultEmprendimientoShouldBeFound("observaciones.contains=" + DEFAULT_OBSERVACIONES);

        // Get all the emprendimientoList where observaciones contains UPDATED_OBSERVACIONES
        defaultEmprendimientoShouldNotBeFound("observaciones.contains=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByObservacionesNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where observaciones does not contain DEFAULT_OBSERVACIONES
        defaultEmprendimientoShouldNotBeFound("observaciones.doesNotContain=" + DEFAULT_OBSERVACIONES);

        // Get all the emprendimientoList where observaciones does not contain UPDATED_OBSERVACIONES
        defaultEmprendimientoShouldBeFound("observaciones.doesNotContain=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComentarioIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comentario equals to DEFAULT_COMENTARIO
        defaultEmprendimientoShouldBeFound("comentario.equals=" + DEFAULT_COMENTARIO);

        // Get all the emprendimientoList where comentario equals to UPDATED_COMENTARIO
        defaultEmprendimientoShouldNotBeFound("comentario.equals=" + UPDATED_COMENTARIO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComentarioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comentario not equals to DEFAULT_COMENTARIO
        defaultEmprendimientoShouldNotBeFound("comentario.notEquals=" + DEFAULT_COMENTARIO);

        // Get all the emprendimientoList where comentario not equals to UPDATED_COMENTARIO
        defaultEmprendimientoShouldBeFound("comentario.notEquals=" + UPDATED_COMENTARIO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComentarioIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comentario in DEFAULT_COMENTARIO or UPDATED_COMENTARIO
        defaultEmprendimientoShouldBeFound("comentario.in=" + DEFAULT_COMENTARIO + "," + UPDATED_COMENTARIO);

        // Get all the emprendimientoList where comentario equals to UPDATED_COMENTARIO
        defaultEmprendimientoShouldNotBeFound("comentario.in=" + UPDATED_COMENTARIO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComentarioIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comentario is not null
        defaultEmprendimientoShouldBeFound("comentario.specified=true");

        // Get all the emprendimientoList where comentario is null
        defaultEmprendimientoShouldNotBeFound("comentario.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComentarioContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comentario contains DEFAULT_COMENTARIO
        defaultEmprendimientoShouldBeFound("comentario.contains=" + DEFAULT_COMENTARIO);

        // Get all the emprendimientoList where comentario contains UPDATED_COMENTARIO
        defaultEmprendimientoShouldNotBeFound("comentario.contains=" + UPDATED_COMENTARIO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComentarioNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comentario does not contain DEFAULT_COMENTARIO
        defaultEmprendimientoShouldNotBeFound("comentario.doesNotContain=" + DEFAULT_COMENTARIO);

        // Get all the emprendimientoList where comentario does not contain UPDATED_COMENTARIO
        defaultEmprendimientoShouldBeFound("comentario.doesNotContain=" + UPDATED_COMENTARIO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComenCanIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comenCan equals to DEFAULT_COMEN_CAN
        defaultEmprendimientoShouldBeFound("comenCan.equals=" + DEFAULT_COMEN_CAN);

        // Get all the emprendimientoList where comenCan equals to UPDATED_COMEN_CAN
        defaultEmprendimientoShouldNotBeFound("comenCan.equals=" + UPDATED_COMEN_CAN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComenCanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comenCan not equals to DEFAULT_COMEN_CAN
        defaultEmprendimientoShouldNotBeFound("comenCan.notEquals=" + DEFAULT_COMEN_CAN);

        // Get all the emprendimientoList where comenCan not equals to UPDATED_COMEN_CAN
        defaultEmprendimientoShouldBeFound("comenCan.notEquals=" + UPDATED_COMEN_CAN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComenCanIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comenCan in DEFAULT_COMEN_CAN or UPDATED_COMEN_CAN
        defaultEmprendimientoShouldBeFound("comenCan.in=" + DEFAULT_COMEN_CAN + "," + UPDATED_COMEN_CAN);

        // Get all the emprendimientoList where comenCan equals to UPDATED_COMEN_CAN
        defaultEmprendimientoShouldNotBeFound("comenCan.in=" + UPDATED_COMEN_CAN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComenCanIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comenCan is not null
        defaultEmprendimientoShouldBeFound("comenCan.specified=true");

        // Get all the emprendimientoList where comenCan is null
        defaultEmprendimientoShouldNotBeFound("comenCan.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComenCanContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comenCan contains DEFAULT_COMEN_CAN
        defaultEmprendimientoShouldBeFound("comenCan.contains=" + DEFAULT_COMEN_CAN);

        // Get all the emprendimientoList where comenCan contains UPDATED_COMEN_CAN
        defaultEmprendimientoShouldNotBeFound("comenCan.contains=" + UPDATED_COMEN_CAN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByComenCanNotContainsSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where comenCan does not contain DEFAULT_COMEN_CAN
        defaultEmprendimientoShouldNotBeFound("comenCan.doesNotContain=" + DEFAULT_COMEN_CAN);

        // Get all the emprendimientoList where comenCan does not contain UPDATED_COMEN_CAN
        defaultEmprendimientoShouldBeFound("comenCan.doesNotContain=" + UPDATED_COMEN_CAN);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoFirmaIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estadoFirma equals to DEFAULT_ESTADO_FIRMA
        defaultEmprendimientoShouldBeFound("estadoFirma.equals=" + DEFAULT_ESTADO_FIRMA);

        // Get all the emprendimientoList where estadoFirma equals to UPDATED_ESTADO_FIRMA
        defaultEmprendimientoShouldNotBeFound("estadoFirma.equals=" + UPDATED_ESTADO_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoFirmaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estadoFirma not equals to DEFAULT_ESTADO_FIRMA
        defaultEmprendimientoShouldNotBeFound("estadoFirma.notEquals=" + DEFAULT_ESTADO_FIRMA);

        // Get all the emprendimientoList where estadoFirma not equals to UPDATED_ESTADO_FIRMA
        defaultEmprendimientoShouldBeFound("estadoFirma.notEquals=" + UPDATED_ESTADO_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoFirmaIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estadoFirma in DEFAULT_ESTADO_FIRMA or UPDATED_ESTADO_FIRMA
        defaultEmprendimientoShouldBeFound("estadoFirma.in=" + DEFAULT_ESTADO_FIRMA + "," + UPDATED_ESTADO_FIRMA);

        // Get all the emprendimientoList where estadoFirma equals to UPDATED_ESTADO_FIRMA
        defaultEmprendimientoShouldNotBeFound("estadoFirma.in=" + UPDATED_ESTADO_FIRMA);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoFirmaIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estadoFirma is not null
        defaultEmprendimientoShouldBeFound("estadoFirma.specified=true");

        // Get all the emprendimientoList where estadoFirma is null
        defaultEmprendimientoShouldNotBeFound("estadoFirma.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estado equals to DEFAULT_ESTADO
        defaultEmprendimientoShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the emprendimientoList where estado equals to UPDATED_ESTADO
        defaultEmprendimientoShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estado not equals to DEFAULT_ESTADO
        defaultEmprendimientoShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the emprendimientoList where estado not equals to UPDATED_ESTADO
        defaultEmprendimientoShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultEmprendimientoShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the emprendimientoList where estado equals to UPDATED_ESTADO
        defaultEmprendimientoShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estado is not null
        defaultEmprendimientoShouldBeFound("estado.specified=true");

        // Get all the emprendimientoList where estado is null
        defaultEmprendimientoShouldNotBeFound("estado.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoBCIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estadoBC equals to DEFAULT_ESTADO_BC
        defaultEmprendimientoShouldBeFound("estadoBC.equals=" + DEFAULT_ESTADO_BC);

        // Get all the emprendimientoList where estadoBC equals to UPDATED_ESTADO_BC
        defaultEmprendimientoShouldNotBeFound("estadoBC.equals=" + UPDATED_ESTADO_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoBCIsNotEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estadoBC not equals to DEFAULT_ESTADO_BC
        defaultEmprendimientoShouldNotBeFound("estadoBC.notEquals=" + DEFAULT_ESTADO_BC);

        // Get all the emprendimientoList where estadoBC not equals to UPDATED_ESTADO_BC
        defaultEmprendimientoShouldBeFound("estadoBC.notEquals=" + UPDATED_ESTADO_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoBCIsInShouldWork() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estadoBC in DEFAULT_ESTADO_BC or UPDATED_ESTADO_BC
        defaultEmprendimientoShouldBeFound("estadoBC.in=" + DEFAULT_ESTADO_BC + "," + UPDATED_ESTADO_BC);

        // Get all the emprendimientoList where estadoBC equals to UPDATED_ESTADO_BC
        defaultEmprendimientoShouldNotBeFound("estadoBC.in=" + UPDATED_ESTADO_BC);
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEstadoBCIsNullOrNotNull() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        // Get all the emprendimientoList where estadoBC is not null
        defaultEmprendimientoShouldBeFound("estadoBC.specified=true");

        // Get all the emprendimientoList where estadoBC is null
        defaultEmprendimientoShouldNotBeFound("estadoBC.specified=false");
    }

    @Test
    @Transactional
    void getAllEmprendimientosByGrupoEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        GrupoEmp grupoEmp;
        if (TestUtil.findAll(em, GrupoEmp.class).isEmpty()) {
            grupoEmp = GrupoEmpResourceIT.createEntity(em);
            em.persist(grupoEmp);
            em.flush();
        } else {
            grupoEmp = TestUtil.findAll(em, GrupoEmp.class).get(0);
        }
        em.persist(grupoEmp);
        em.flush();
        emprendimiento.setGrupoEmp(grupoEmp);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long grupoEmpId = grupoEmp.getId();

        // Get all the emprendimientoList where grupoEmp equals to grupoEmpId
        defaultEmprendimientoShouldBeFound("grupoEmpId.equals=" + grupoEmpId);

        // Get all the emprendimientoList where grupoEmp equals to (grupoEmpId + 1)
        defaultEmprendimientoShouldNotBeFound("grupoEmpId.equals=" + (grupoEmpId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTipoObraIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        TipoObra tipoObra;
        if (TestUtil.findAll(em, TipoObra.class).isEmpty()) {
            tipoObra = TipoObraResourceIT.createEntity(em);
            em.persist(tipoObra);
            em.flush();
        } else {
            tipoObra = TestUtil.findAll(em, TipoObra.class).get(0);
        }
        em.persist(tipoObra);
        em.flush();
        emprendimiento.setTipoObra(tipoObra);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long tipoObraId = tipoObra.getId();

        // Get all the emprendimientoList where tipoObra equals to tipoObraId
        defaultEmprendimientoShouldBeFound("tipoObraId.equals=" + tipoObraId);

        // Get all the emprendimientoList where tipoObra equals to (tipoObraId + 1)
        defaultEmprendimientoShouldNotBeFound("tipoObraId.equals=" + (tipoObraId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTipoEmpIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        TipoEmp tipoEmp;
        if (TestUtil.findAll(em, TipoEmp.class).isEmpty()) {
            tipoEmp = TipoEmpResourceIT.createEntity(em);
            em.persist(tipoEmp);
            em.flush();
        } else {
            tipoEmp = TestUtil.findAll(em, TipoEmp.class).get(0);
        }
        em.persist(tipoEmp);
        em.flush();
        emprendimiento.setTipoEmp(tipoEmp);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long tipoEmpId = tipoEmp.getId();

        // Get all the emprendimientoList where tipoEmp equals to tipoEmpId
        defaultEmprendimientoShouldBeFound("tipoEmpId.equals=" + tipoEmpId);

        // Get all the emprendimientoList where tipoEmp equals to (tipoEmpId + 1)
        defaultEmprendimientoShouldNotBeFound("tipoEmpId.equals=" + (tipoEmpId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDespliegueIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Despliegue despliegue;
        if (TestUtil.findAll(em, Despliegue.class).isEmpty()) {
            despliegue = DespliegueResourceIT.createEntity(em);
            em.persist(despliegue);
            em.flush();
        } else {
            despliegue = TestUtil.findAll(em, Despliegue.class).get(0);
        }
        em.persist(despliegue);
        em.flush();
        emprendimiento.setDespliegue(despliegue);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long despliegueId = despliegue.getId();

        // Get all the emprendimientoList where despliegue equals to despliegueId
        defaultEmprendimientoShouldBeFound("despliegueId.equals=" + despliegueId);

        // Get all the emprendimientoList where despliegue equals to (despliegueId + 1)
        defaultEmprendimientoShouldNotBeFound("despliegueId.equals=" + (despliegueId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosByNSEIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        NSE nSE;
        if (TestUtil.findAll(em, NSE.class).isEmpty()) {
            nSE = NSEResourceIT.createEntity(em);
            em.persist(nSE);
            em.flush();
        } else {
            nSE = TestUtil.findAll(em, NSE.class).get(0);
        }
        em.persist(nSE);
        em.flush();
        emprendimiento.setNSE(nSE);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long nSEId = nSE.getId();

        // Get all the emprendimientoList where nSE equals to nSEId
        defaultEmprendimientoShouldBeFound("nSEId.equals=" + nSEId);

        // Get all the emprendimientoList where nSE equals to (nSEId + 1)
        defaultEmprendimientoShouldNotBeFound("nSEId.equals=" + (nSEId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosBySegmentoIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Segmento segmento;
        if (TestUtil.findAll(em, Segmento.class).isEmpty()) {
            segmento = SegmentoResourceIT.createEntity(em);
            em.persist(segmento);
            em.flush();
        } else {
            segmento = TestUtil.findAll(em, Segmento.class).get(0);
        }
        em.persist(segmento);
        em.flush();
        emprendimiento.setSegmento(segmento);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long segmentoId = segmento.getId();

        // Get all the emprendimientoList where segmento equals to segmentoId
        defaultEmprendimientoShouldBeFound("segmentoId.equals=" + segmentoId);

        // Get all the emprendimientoList where segmento equals to (segmentoId + 1)
        defaultEmprendimientoShouldNotBeFound("segmentoId.equals=" + (segmentoId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosByTecnologiaIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Tecnologia tecnologia;
        if (TestUtil.findAll(em, Tecnologia.class).isEmpty()) {
            tecnologia = TecnologiaResourceIT.createEntity(em);
            em.persist(tecnologia);
            em.flush();
        } else {
            tecnologia = TestUtil.findAll(em, Tecnologia.class).get(0);
        }
        em.persist(tecnologia);
        em.flush();
        emprendimiento.setTecnologia(tecnologia);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long tecnologiaId = tecnologia.getId();

        // Get all the emprendimientoList where tecnologia equals to tecnologiaId
        defaultEmprendimientoShouldBeFound("tecnologiaId.equals=" + tecnologiaId);

        // Get all the emprendimientoList where tecnologia equals to (tecnologiaId + 1)
        defaultEmprendimientoShouldNotBeFound("tecnologiaId.equals=" + (tecnologiaId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosByEjecCuentasIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        EjecCuentas ejecCuentas;
        if (TestUtil.findAll(em, EjecCuentas.class).isEmpty()) {
            ejecCuentas = EjecCuentasResourceIT.createEntity(em);
            em.persist(ejecCuentas);
            em.flush();
        } else {
            ejecCuentas = TestUtil.findAll(em, EjecCuentas.class).get(0);
        }
        em.persist(ejecCuentas);
        em.flush();
        emprendimiento.setEjecCuentas(ejecCuentas);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long ejecCuentasId = ejecCuentas.getId();

        // Get all the emprendimientoList where ejecCuentas equals to ejecCuentasId
        defaultEmprendimientoShouldBeFound("ejecCuentasId.equals=" + ejecCuentasId);

        // Get all the emprendimientoList where ejecCuentas equals to (ejecCuentasId + 1)
        defaultEmprendimientoShouldNotBeFound("ejecCuentasId.equals=" + (ejecCuentasId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDireccionIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Direccion direccion;
        if (TestUtil.findAll(em, Direccion.class).isEmpty()) {
            direccion = DireccionResourceIT.createEntity(em);
            em.persist(direccion);
            em.flush();
        } else {
            direccion = TestUtil.findAll(em, Direccion.class).get(0);
        }
        em.persist(direccion);
        em.flush();
        emprendimiento.setDireccion(direccion);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long direccionId = direccion.getId();

        // Get all the emprendimientoList where direccion equals to direccionId
        defaultEmprendimientoShouldBeFound("direccionId.equals=" + direccionId);

        // Get all the emprendimientoList where direccion equals to (direccionId + 1)
        defaultEmprendimientoShouldNotBeFound("direccionId.equals=" + (direccionId + 1));
    }

    @Test
    @Transactional
    void getAllEmprendimientosByDireccionIsEqualToSomething() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Competencia direccion;
        if (TestUtil.findAll(em, Competencia.class).isEmpty()) {
            direccion = CompetenciaResourceIT.createEntity(em);
            em.persist(direccion);
            em.flush();
        } else {
            direccion = TestUtil.findAll(em, Competencia.class).get(0);
        }
        em.persist(direccion);
        em.flush();
        emprendimiento.setDireccion(direccion);
        emprendimientoRepository.saveAndFlush(emprendimiento);
        Long direccionId = direccion.getId();

        // Get all the emprendimientoList where direccion equals to direccionId
        defaultEmprendimientoShouldBeFound("direccionId.equals=" + direccionId);

        // Get all the emprendimientoList where direccion equals to (direccionId + 1)
        defaultEmprendimientoShouldNotBeFound("direccionId.equals=" + (direccionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEmprendimientoShouldBeFound(String filter) throws Exception {
        restEmprendimientoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emprendimiento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].contacto").value(hasItem(DEFAULT_CONTACTO)))
            .andExpect(jsonPath("$.[*].fechaFinObra").value(hasItem(DEFAULT_FECHA_FIN_OBRA.toString())))
            .andExpect(jsonPath("$.[*].codigoObra").value(hasItem(DEFAULT_CODIGO_OBRA)))
            .andExpect(jsonPath("$.[*].elementosDeRed").value(hasItem(DEFAULT_ELEMENTOS_DE_RED)))
            .andExpect(jsonPath("$.[*].clientesCatv").value(hasItem(DEFAULT_CLIENTES_CATV)))
            .andExpect(jsonPath("$.[*].clientesFibertel").value(hasItem(DEFAULT_CLIENTES_FIBERTEL)))
            .andExpect(jsonPath("$.[*].clientesFibertelLite").value(hasItem(DEFAULT_CLIENTES_FIBERTEL_LITE)))
            .andExpect(jsonPath("$.[*].clientesFlow").value(hasItem(DEFAULT_CLIENTES_FLOW)))
            .andExpect(jsonPath("$.[*].clientesCombo").value(hasItem(DEFAULT_CLIENTES_COMBO)))
            .andExpect(jsonPath("$.[*].lineasVoz").value(hasItem(DEFAULT_LINEAS_VOZ)))
            .andExpect(jsonPath("$.[*].mesesDeFinalizado").value(hasItem(DEFAULT_MESES_DE_FINALIZADO)))
            .andExpect(jsonPath("$.[*].altasBC").value(hasItem(DEFAULT_ALTAS_BC)))
            .andExpect(jsonPath("$.[*].penetracionVivLot").value(hasItem(DEFAULT_PENETRACION_VIV_LOT)))
            .andExpect(jsonPath("$.[*].penetracionBC").value(hasItem(DEFAULT_PENETRACION_BC)))
            .andExpect(jsonPath("$.[*].demanda1").value(hasItem(DEFAULT_DEMANDA_1)))
            .andExpect(jsonPath("$.[*].demanda2").value(hasItem(DEFAULT_DEMANDA_2)))
            .andExpect(jsonPath("$.[*].demanda3").value(hasItem(DEFAULT_DEMANDA_3)))
            .andExpect(jsonPath("$.[*].demanda4").value(hasItem(DEFAULT_DEMANDA_4)))
            .andExpect(jsonPath("$.[*].demanda5").value(hasItem(DEFAULT_DEMANDA_5)))
            .andExpect(jsonPath("$.[*].lotes").value(hasItem(DEFAULT_LOTES)))
            .andExpect(jsonPath("$.[*].viviendas").value(hasItem(DEFAULT_VIVIENDAS)))
            .andExpect(jsonPath("$.[*].comProf").value(hasItem(DEFAULT_COM_PROF)))
            .andExpect(jsonPath("$.[*].habitaciones").value(hasItem(DEFAULT_HABITACIONES)))
            .andExpect(jsonPath("$.[*].manzanas").value(hasItem(DEFAULT_MANZANAS)))
            .andExpect(jsonPath("$.[*].demanda").value(hasItem(DEFAULT_DEMANDA)))
            .andExpect(jsonPath("$.[*].fechaDeRelevamiento").value(hasItem(DEFAULT_FECHA_DE_RELEVAMIENTO.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].anoPriorizacion").value(hasItem(DEFAULT_ANO_PRIORIZACION.toString())))
            .andExpect(jsonPath("$.[*].contratoOpen").value(hasItem(DEFAULT_CONTRATO_OPEN)))
            .andExpect(jsonPath("$.[*].negociacion").value(hasItem(DEFAULT_NEGOCIACION.booleanValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].codigoDeFirma").value(hasItem(DEFAULT_CODIGO_DE_FIRMA)))
            .andExpect(jsonPath("$.[*].fechaFirma").value(hasItem(DEFAULT_FECHA_FIRMA.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].comentario").value(hasItem(DEFAULT_COMENTARIO)))
            .andExpect(jsonPath("$.[*].comenCan").value(hasItem(DEFAULT_COMEN_CAN)))
            .andExpect(jsonPath("$.[*].estadoFirma").value(hasItem(DEFAULT_ESTADO_FIRMA.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].estadoBC").value(hasItem(DEFAULT_ESTADO_BC.toString())));

        // Check, that the count call also returns 1
        restEmprendimientoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEmprendimientoShouldNotBeFound(String filter) throws Exception {
        restEmprendimientoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEmprendimientoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingEmprendimiento() throws Exception {
        // Get the emprendimiento
        restEmprendimientoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEmprendimiento() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();

        // Update the emprendimiento
        Emprendimiento updatedEmprendimiento = emprendimientoRepository.findById(emprendimiento.getId()).get();
        // Disconnect from session so that the updates on updatedEmprendimiento are not directly saved in db
        em.detach(updatedEmprendimiento);
        updatedEmprendimiento
            .nombre(UPDATED_NOMBRE)
            .contacto(UPDATED_CONTACTO)
            .fechaFinObra(UPDATED_FECHA_FIN_OBRA)
            .codigoObra(UPDATED_CODIGO_OBRA)
            .elementosDeRed(UPDATED_ELEMENTOS_DE_RED)
            .clientesCatv(UPDATED_CLIENTES_CATV)
            .clientesFibertel(UPDATED_CLIENTES_FIBERTEL)
            .clientesFibertelLite(UPDATED_CLIENTES_FIBERTEL_LITE)
            .clientesFlow(UPDATED_CLIENTES_FLOW)
            .clientesCombo(UPDATED_CLIENTES_COMBO)
            .lineasVoz(UPDATED_LINEAS_VOZ)
            .mesesDeFinalizado(UPDATED_MESES_DE_FINALIZADO)
            .altasBC(UPDATED_ALTAS_BC)
            .penetracionVivLot(UPDATED_PENETRACION_VIV_LOT)
            .penetracionBC(UPDATED_PENETRACION_BC)
            .demanda1(UPDATED_DEMANDA_1)
            .demanda2(UPDATED_DEMANDA_2)
            .demanda3(UPDATED_DEMANDA_3)
            .demanda4(UPDATED_DEMANDA_4)
            .demanda5(UPDATED_DEMANDA_5)
            .lotes(UPDATED_LOTES)
            .viviendas(UPDATED_VIVIENDAS)
            .comProf(UPDATED_COM_PROF)
            .habitaciones(UPDATED_HABITACIONES)
            .manzanas(UPDATED_MANZANAS)
            .demanda(UPDATED_DEMANDA)
            .fechaDeRelevamiento(UPDATED_FECHA_DE_RELEVAMIENTO)
            .telefono(UPDATED_TELEFONO)
            .anoPriorizacion(UPDATED_ANO_PRIORIZACION)
            .contratoOpen(UPDATED_CONTRATO_OPEN)
            .negociacion(UPDATED_NEGOCIACION)
            .fecha(UPDATED_FECHA)
            .codigoDeFirma(UPDATED_CODIGO_DE_FIRMA)
            .fechaFirma(UPDATED_FECHA_FIRMA)
            .observaciones(UPDATED_OBSERVACIONES)
            .comentario(UPDATED_COMENTARIO)
            .comenCan(UPDATED_COMEN_CAN)
            .estadoFirma(UPDATED_ESTADO_FIRMA)
            .estado(UPDATED_ESTADO)
            .estadoBC(UPDATED_ESTADO_BC);

        restEmprendimientoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEmprendimiento.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEmprendimiento))
            )
            .andExpect(status().isOk());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
        Emprendimiento testEmprendimiento = emprendimientoList.get(emprendimientoList.size() - 1);
        assertThat(testEmprendimiento.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testEmprendimiento.getContacto()).isEqualTo(UPDATED_CONTACTO);
        assertThat(testEmprendimiento.getFechaFinObra()).isEqualTo(UPDATED_FECHA_FIN_OBRA);
        assertThat(testEmprendimiento.getCodigoObra()).isEqualTo(UPDATED_CODIGO_OBRA);
        assertThat(testEmprendimiento.getElementosDeRed()).isEqualTo(UPDATED_ELEMENTOS_DE_RED);
        assertThat(testEmprendimiento.getClientesCatv()).isEqualTo(UPDATED_CLIENTES_CATV);
        assertThat(testEmprendimiento.getClientesFibertel()).isEqualTo(UPDATED_CLIENTES_FIBERTEL);
        assertThat(testEmprendimiento.getClientesFibertelLite()).isEqualTo(UPDATED_CLIENTES_FIBERTEL_LITE);
        assertThat(testEmprendimiento.getClientesFlow()).isEqualTo(UPDATED_CLIENTES_FLOW);
        assertThat(testEmprendimiento.getClientesCombo()).isEqualTo(UPDATED_CLIENTES_COMBO);
        assertThat(testEmprendimiento.getLineasVoz()).isEqualTo(UPDATED_LINEAS_VOZ);
        assertThat(testEmprendimiento.getMesesDeFinalizado()).isEqualTo(UPDATED_MESES_DE_FINALIZADO);
        assertThat(testEmprendimiento.getAltasBC()).isEqualTo(UPDATED_ALTAS_BC);
        assertThat(testEmprendimiento.getPenetracionVivLot()).isEqualTo(UPDATED_PENETRACION_VIV_LOT);
        assertThat(testEmprendimiento.getPenetracionBC()).isEqualTo(UPDATED_PENETRACION_BC);
        assertThat(testEmprendimiento.getDemanda1()).isEqualTo(UPDATED_DEMANDA_1);
        assertThat(testEmprendimiento.getDemanda2()).isEqualTo(UPDATED_DEMANDA_2);
        assertThat(testEmprendimiento.getDemanda3()).isEqualTo(UPDATED_DEMANDA_3);
        assertThat(testEmprendimiento.getDemanda4()).isEqualTo(UPDATED_DEMANDA_4);
        assertThat(testEmprendimiento.getDemanda5()).isEqualTo(UPDATED_DEMANDA_5);
        assertThat(testEmprendimiento.getLotes()).isEqualTo(UPDATED_LOTES);
        assertThat(testEmprendimiento.getViviendas()).isEqualTo(UPDATED_VIVIENDAS);
        assertThat(testEmprendimiento.getComProf()).isEqualTo(UPDATED_COM_PROF);
        assertThat(testEmprendimiento.getHabitaciones()).isEqualTo(UPDATED_HABITACIONES);
        assertThat(testEmprendimiento.getManzanas()).isEqualTo(UPDATED_MANZANAS);
        assertThat(testEmprendimiento.getDemanda()).isEqualTo(UPDATED_DEMANDA);
        assertThat(testEmprendimiento.getFechaDeRelevamiento()).isEqualTo(UPDATED_FECHA_DE_RELEVAMIENTO);
        assertThat(testEmprendimiento.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testEmprendimiento.getAnoPriorizacion()).isEqualTo(UPDATED_ANO_PRIORIZACION);
        assertThat(testEmprendimiento.getContratoOpen()).isEqualTo(UPDATED_CONTRATO_OPEN);
        assertThat(testEmprendimiento.getNegociacion()).isEqualTo(UPDATED_NEGOCIACION);
        assertThat(testEmprendimiento.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testEmprendimiento.getCodigoDeFirma()).isEqualTo(UPDATED_CODIGO_DE_FIRMA);
        assertThat(testEmprendimiento.getFechaFirma()).isEqualTo(UPDATED_FECHA_FIRMA);
        assertThat(testEmprendimiento.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testEmprendimiento.getComentario()).isEqualTo(UPDATED_COMENTARIO);
        assertThat(testEmprendimiento.getComenCan()).isEqualTo(UPDATED_COMEN_CAN);
        assertThat(testEmprendimiento.getEstadoFirma()).isEqualTo(UPDATED_ESTADO_FIRMA);
        assertThat(testEmprendimiento.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmprendimiento.getEstadoBC()).isEqualTo(UPDATED_ESTADO_BC);
    }

    @Test
    @Transactional
    void putNonExistingEmprendimiento() throws Exception {
        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();
        emprendimiento.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmprendimientoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, emprendimiento.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(emprendimiento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEmprendimiento() throws Exception {
        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();
        emprendimiento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmprendimientoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(emprendimiento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEmprendimiento() throws Exception {
        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();
        emprendimiento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmprendimientoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(emprendimiento)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEmprendimientoWithPatch() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();

        // Update the emprendimiento using partial update
        Emprendimiento partialUpdatedEmprendimiento = new Emprendimiento();
        partialUpdatedEmprendimiento.setId(emprendimiento.getId());

        partialUpdatedEmprendimiento
            .nombre(UPDATED_NOMBRE)
            .clientesCatv(UPDATED_CLIENTES_CATV)
            .clientesFibertel(UPDATED_CLIENTES_FIBERTEL)
            .mesesDeFinalizado(UPDATED_MESES_DE_FINALIZADO)
            .altasBC(UPDATED_ALTAS_BC)
            .penetracionBC(UPDATED_PENETRACION_BC)
            .demanda1(UPDATED_DEMANDA_1)
            .demanda2(UPDATED_DEMANDA_2)
            .comProf(UPDATED_COM_PROF)
            .demanda(UPDATED_DEMANDA)
            .fechaDeRelevamiento(UPDATED_FECHA_DE_RELEVAMIENTO)
            .contratoOpen(UPDATED_CONTRATO_OPEN)
            .fecha(UPDATED_FECHA)
            .fechaFirma(UPDATED_FECHA_FIRMA)
            .comentario(UPDATED_COMENTARIO)
            .estadoFirma(UPDATED_ESTADO_FIRMA)
            .estado(UPDATED_ESTADO);

        restEmprendimientoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmprendimiento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmprendimiento))
            )
            .andExpect(status().isOk());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
        Emprendimiento testEmprendimiento = emprendimientoList.get(emprendimientoList.size() - 1);
        assertThat(testEmprendimiento.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testEmprendimiento.getContacto()).isEqualTo(DEFAULT_CONTACTO);
        assertThat(testEmprendimiento.getFechaFinObra()).isEqualTo(DEFAULT_FECHA_FIN_OBRA);
        assertThat(testEmprendimiento.getCodigoObra()).isEqualTo(DEFAULT_CODIGO_OBRA);
        assertThat(testEmprendimiento.getElementosDeRed()).isEqualTo(DEFAULT_ELEMENTOS_DE_RED);
        assertThat(testEmprendimiento.getClientesCatv()).isEqualTo(UPDATED_CLIENTES_CATV);
        assertThat(testEmprendimiento.getClientesFibertel()).isEqualTo(UPDATED_CLIENTES_FIBERTEL);
        assertThat(testEmprendimiento.getClientesFibertelLite()).isEqualTo(DEFAULT_CLIENTES_FIBERTEL_LITE);
        assertThat(testEmprendimiento.getClientesFlow()).isEqualTo(DEFAULT_CLIENTES_FLOW);
        assertThat(testEmprendimiento.getClientesCombo()).isEqualTo(DEFAULT_CLIENTES_COMBO);
        assertThat(testEmprendimiento.getLineasVoz()).isEqualTo(DEFAULT_LINEAS_VOZ);
        assertThat(testEmprendimiento.getMesesDeFinalizado()).isEqualTo(UPDATED_MESES_DE_FINALIZADO);
        assertThat(testEmprendimiento.getAltasBC()).isEqualTo(UPDATED_ALTAS_BC);
        assertThat(testEmprendimiento.getPenetracionVivLot()).isEqualTo(DEFAULT_PENETRACION_VIV_LOT);
        assertThat(testEmprendimiento.getPenetracionBC()).isEqualTo(UPDATED_PENETRACION_BC);
        assertThat(testEmprendimiento.getDemanda1()).isEqualTo(UPDATED_DEMANDA_1);
        assertThat(testEmprendimiento.getDemanda2()).isEqualTo(UPDATED_DEMANDA_2);
        assertThat(testEmprendimiento.getDemanda3()).isEqualTo(DEFAULT_DEMANDA_3);
        assertThat(testEmprendimiento.getDemanda4()).isEqualTo(DEFAULT_DEMANDA_4);
        assertThat(testEmprendimiento.getDemanda5()).isEqualTo(DEFAULT_DEMANDA_5);
        assertThat(testEmprendimiento.getLotes()).isEqualTo(DEFAULT_LOTES);
        assertThat(testEmprendimiento.getViviendas()).isEqualTo(DEFAULT_VIVIENDAS);
        assertThat(testEmprendimiento.getComProf()).isEqualTo(UPDATED_COM_PROF);
        assertThat(testEmprendimiento.getHabitaciones()).isEqualTo(DEFAULT_HABITACIONES);
        assertThat(testEmprendimiento.getManzanas()).isEqualTo(DEFAULT_MANZANAS);
        assertThat(testEmprendimiento.getDemanda()).isEqualTo(UPDATED_DEMANDA);
        assertThat(testEmprendimiento.getFechaDeRelevamiento()).isEqualTo(UPDATED_FECHA_DE_RELEVAMIENTO);
        assertThat(testEmprendimiento.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testEmprendimiento.getAnoPriorizacion()).isEqualTo(DEFAULT_ANO_PRIORIZACION);
        assertThat(testEmprendimiento.getContratoOpen()).isEqualTo(UPDATED_CONTRATO_OPEN);
        assertThat(testEmprendimiento.getNegociacion()).isEqualTo(DEFAULT_NEGOCIACION);
        assertThat(testEmprendimiento.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testEmprendimiento.getCodigoDeFirma()).isEqualTo(DEFAULT_CODIGO_DE_FIRMA);
        assertThat(testEmprendimiento.getFechaFirma()).isEqualTo(UPDATED_FECHA_FIRMA);
        assertThat(testEmprendimiento.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testEmprendimiento.getComentario()).isEqualTo(UPDATED_COMENTARIO);
        assertThat(testEmprendimiento.getComenCan()).isEqualTo(DEFAULT_COMEN_CAN);
        assertThat(testEmprendimiento.getEstadoFirma()).isEqualTo(UPDATED_ESTADO_FIRMA);
        assertThat(testEmprendimiento.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmprendimiento.getEstadoBC()).isEqualTo(DEFAULT_ESTADO_BC);
    }

    @Test
    @Transactional
    void fullUpdateEmprendimientoWithPatch() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();

        // Update the emprendimiento using partial update
        Emprendimiento partialUpdatedEmprendimiento = new Emprendimiento();
        partialUpdatedEmprendimiento.setId(emprendimiento.getId());

        partialUpdatedEmprendimiento
            .nombre(UPDATED_NOMBRE)
            .contacto(UPDATED_CONTACTO)
            .fechaFinObra(UPDATED_FECHA_FIN_OBRA)
            .codigoObra(UPDATED_CODIGO_OBRA)
            .elementosDeRed(UPDATED_ELEMENTOS_DE_RED)
            .clientesCatv(UPDATED_CLIENTES_CATV)
            .clientesFibertel(UPDATED_CLIENTES_FIBERTEL)
            .clientesFibertelLite(UPDATED_CLIENTES_FIBERTEL_LITE)
            .clientesFlow(UPDATED_CLIENTES_FLOW)
            .clientesCombo(UPDATED_CLIENTES_COMBO)
            .lineasVoz(UPDATED_LINEAS_VOZ)
            .mesesDeFinalizado(UPDATED_MESES_DE_FINALIZADO)
            .altasBC(UPDATED_ALTAS_BC)
            .penetracionVivLot(UPDATED_PENETRACION_VIV_LOT)
            .penetracionBC(UPDATED_PENETRACION_BC)
            .demanda1(UPDATED_DEMANDA_1)
            .demanda2(UPDATED_DEMANDA_2)
            .demanda3(UPDATED_DEMANDA_3)
            .demanda4(UPDATED_DEMANDA_4)
            .demanda5(UPDATED_DEMANDA_5)
            .lotes(UPDATED_LOTES)
            .viviendas(UPDATED_VIVIENDAS)
            .comProf(UPDATED_COM_PROF)
            .habitaciones(UPDATED_HABITACIONES)
            .manzanas(UPDATED_MANZANAS)
            .demanda(UPDATED_DEMANDA)
            .fechaDeRelevamiento(UPDATED_FECHA_DE_RELEVAMIENTO)
            .telefono(UPDATED_TELEFONO)
            .anoPriorizacion(UPDATED_ANO_PRIORIZACION)
            .contratoOpen(UPDATED_CONTRATO_OPEN)
            .negociacion(UPDATED_NEGOCIACION)
            .fecha(UPDATED_FECHA)
            .codigoDeFirma(UPDATED_CODIGO_DE_FIRMA)
            .fechaFirma(UPDATED_FECHA_FIRMA)
            .observaciones(UPDATED_OBSERVACIONES)
            .comentario(UPDATED_COMENTARIO)
            .comenCan(UPDATED_COMEN_CAN)
            .estadoFirma(UPDATED_ESTADO_FIRMA)
            .estado(UPDATED_ESTADO)
            .estadoBC(UPDATED_ESTADO_BC);

        restEmprendimientoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmprendimiento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmprendimiento))
            )
            .andExpect(status().isOk());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
        Emprendimiento testEmprendimiento = emprendimientoList.get(emprendimientoList.size() - 1);
        assertThat(testEmprendimiento.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testEmprendimiento.getContacto()).isEqualTo(UPDATED_CONTACTO);
        assertThat(testEmprendimiento.getFechaFinObra()).isEqualTo(UPDATED_FECHA_FIN_OBRA);
        assertThat(testEmprendimiento.getCodigoObra()).isEqualTo(UPDATED_CODIGO_OBRA);
        assertThat(testEmprendimiento.getElementosDeRed()).isEqualTo(UPDATED_ELEMENTOS_DE_RED);
        assertThat(testEmprendimiento.getClientesCatv()).isEqualTo(UPDATED_CLIENTES_CATV);
        assertThat(testEmprendimiento.getClientesFibertel()).isEqualTo(UPDATED_CLIENTES_FIBERTEL);
        assertThat(testEmprendimiento.getClientesFibertelLite()).isEqualTo(UPDATED_CLIENTES_FIBERTEL_LITE);
        assertThat(testEmprendimiento.getClientesFlow()).isEqualTo(UPDATED_CLIENTES_FLOW);
        assertThat(testEmprendimiento.getClientesCombo()).isEqualTo(UPDATED_CLIENTES_COMBO);
        assertThat(testEmprendimiento.getLineasVoz()).isEqualTo(UPDATED_LINEAS_VOZ);
        assertThat(testEmprendimiento.getMesesDeFinalizado()).isEqualTo(UPDATED_MESES_DE_FINALIZADO);
        assertThat(testEmprendimiento.getAltasBC()).isEqualTo(UPDATED_ALTAS_BC);
        assertThat(testEmprendimiento.getPenetracionVivLot()).isEqualTo(UPDATED_PENETRACION_VIV_LOT);
        assertThat(testEmprendimiento.getPenetracionBC()).isEqualTo(UPDATED_PENETRACION_BC);
        assertThat(testEmprendimiento.getDemanda1()).isEqualTo(UPDATED_DEMANDA_1);
        assertThat(testEmprendimiento.getDemanda2()).isEqualTo(UPDATED_DEMANDA_2);
        assertThat(testEmprendimiento.getDemanda3()).isEqualTo(UPDATED_DEMANDA_3);
        assertThat(testEmprendimiento.getDemanda4()).isEqualTo(UPDATED_DEMANDA_4);
        assertThat(testEmprendimiento.getDemanda5()).isEqualTo(UPDATED_DEMANDA_5);
        assertThat(testEmprendimiento.getLotes()).isEqualTo(UPDATED_LOTES);
        assertThat(testEmprendimiento.getViviendas()).isEqualTo(UPDATED_VIVIENDAS);
        assertThat(testEmprendimiento.getComProf()).isEqualTo(UPDATED_COM_PROF);
        assertThat(testEmprendimiento.getHabitaciones()).isEqualTo(UPDATED_HABITACIONES);
        assertThat(testEmprendimiento.getManzanas()).isEqualTo(UPDATED_MANZANAS);
        assertThat(testEmprendimiento.getDemanda()).isEqualTo(UPDATED_DEMANDA);
        assertThat(testEmprendimiento.getFechaDeRelevamiento()).isEqualTo(UPDATED_FECHA_DE_RELEVAMIENTO);
        assertThat(testEmprendimiento.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testEmprendimiento.getAnoPriorizacion()).isEqualTo(UPDATED_ANO_PRIORIZACION);
        assertThat(testEmprendimiento.getContratoOpen()).isEqualTo(UPDATED_CONTRATO_OPEN);
        assertThat(testEmprendimiento.getNegociacion()).isEqualTo(UPDATED_NEGOCIACION);
        assertThat(testEmprendimiento.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testEmprendimiento.getCodigoDeFirma()).isEqualTo(UPDATED_CODIGO_DE_FIRMA);
        assertThat(testEmprendimiento.getFechaFirma()).isEqualTo(UPDATED_FECHA_FIRMA);
        assertThat(testEmprendimiento.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testEmprendimiento.getComentario()).isEqualTo(UPDATED_COMENTARIO);
        assertThat(testEmprendimiento.getComenCan()).isEqualTo(UPDATED_COMEN_CAN);
        assertThat(testEmprendimiento.getEstadoFirma()).isEqualTo(UPDATED_ESTADO_FIRMA);
        assertThat(testEmprendimiento.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testEmprendimiento.getEstadoBC()).isEqualTo(UPDATED_ESTADO_BC);
    }

    @Test
    @Transactional
    void patchNonExistingEmprendimiento() throws Exception {
        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();
        emprendimiento.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmprendimientoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, emprendimiento.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(emprendimiento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEmprendimiento() throws Exception {
        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();
        emprendimiento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmprendimientoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(emprendimiento))
            )
            .andExpect(status().isBadRequest());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEmprendimiento() throws Exception {
        int databaseSizeBeforeUpdate = emprendimientoRepository.findAll().size();
        emprendimiento.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmprendimientoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(emprendimiento))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Emprendimiento in the database
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEmprendimiento() throws Exception {
        // Initialize the database
        emprendimientoRepository.saveAndFlush(emprendimiento);

        int databaseSizeBeforeDelete = emprendimientoRepository.findAll().size();

        // Delete the emprendimiento
        restEmprendimientoMockMvc
            .perform(delete(ENTITY_API_URL_ID, emprendimiento.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Emprendimiento> emprendimientoList = emprendimientoRepository.findAll();
        assertThat(emprendimientoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
