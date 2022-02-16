package ar.teco.service;

import ar.teco.domain.Emprendimiento;
import ar.teco.repository.EmprendimientoRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Emprendimiento}.
 */
@Service
@Transactional
public class EmprendimientoService {

    private final Logger log = LoggerFactory.getLogger(EmprendimientoService.class);

    private final EmprendimientoRepository emprendimientoRepository;

    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
    }

    /**
     * Save a emprendimiento.
     *
     * @param emprendimiento the entity to save.
     * @return the persisted entity.
     */
    public Emprendimiento save(Emprendimiento emprendimiento) {
        log.debug("Request to save Emprendimiento : {}", emprendimiento);
        return emprendimientoRepository.save(emprendimiento);
    }

    /**
     * Partially update a emprendimiento.
     *
     * @param emprendimiento the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Emprendimiento> partialUpdate(Emprendimiento emprendimiento) {
        log.debug("Request to partially update Emprendimiento : {}", emprendimiento);

        return emprendimientoRepository
            .findById(emprendimiento.getId())
            .map(existingEmprendimiento -> {
                if (emprendimiento.getNombre() != null) {
                    existingEmprendimiento.setNombre(emprendimiento.getNombre());
                }
                if (emprendimiento.getContacto() != null) {
                    existingEmprendimiento.setContacto(emprendimiento.getContacto());
                }
                if (emprendimiento.getFechaFinObra() != null) {
                    existingEmprendimiento.setFechaFinObra(emprendimiento.getFechaFinObra());
                }
                if (emprendimiento.getCodigoObra() != null) {
                    existingEmprendimiento.setCodigoObra(emprendimiento.getCodigoObra());
                }
                if (emprendimiento.getElementosDeRed() != null) {
                    existingEmprendimiento.setElementosDeRed(emprendimiento.getElementosDeRed());
                }
                if (emprendimiento.getClientesCatv() != null) {
                    existingEmprendimiento.setClientesCatv(emprendimiento.getClientesCatv());
                }
                if (emprendimiento.getClientesFibertel() != null) {
                    existingEmprendimiento.setClientesFibertel(emprendimiento.getClientesFibertel());
                }
                if (emprendimiento.getClientesFibertelLite() != null) {
                    existingEmprendimiento.setClientesFibertelLite(emprendimiento.getClientesFibertelLite());
                }
                if (emprendimiento.getClientesFlow() != null) {
                    existingEmprendimiento.setClientesFlow(emprendimiento.getClientesFlow());
                }
                if (emprendimiento.getClientesCombo() != null) {
                    existingEmprendimiento.setClientesCombo(emprendimiento.getClientesCombo());
                }
                if (emprendimiento.getLineasVoz() != null) {
                    existingEmprendimiento.setLineasVoz(emprendimiento.getLineasVoz());
                }
                if (emprendimiento.getMesesDeFinalizado() != null) {
                    existingEmprendimiento.setMesesDeFinalizado(emprendimiento.getMesesDeFinalizado());
                }
                if (emprendimiento.getAltasBC() != null) {
                    existingEmprendimiento.setAltasBC(emprendimiento.getAltasBC());
                }
                if (emprendimiento.getPenetracionVivLot() != null) {
                    existingEmprendimiento.setPenetracionVivLot(emprendimiento.getPenetracionVivLot());
                }
                if (emprendimiento.getPenetracionBC() != null) {
                    existingEmprendimiento.setPenetracionBC(emprendimiento.getPenetracionBC());
                }
                if (emprendimiento.getDemanda1() != null) {
                    existingEmprendimiento.setDemanda1(emprendimiento.getDemanda1());
                }
                if (emprendimiento.getDemanda2() != null) {
                    existingEmprendimiento.setDemanda2(emprendimiento.getDemanda2());
                }
                if (emprendimiento.getDemanda3() != null) {
                    existingEmprendimiento.setDemanda3(emprendimiento.getDemanda3());
                }
                if (emprendimiento.getDemanda4() != null) {
                    existingEmprendimiento.setDemanda4(emprendimiento.getDemanda4());
                }
                if (emprendimiento.getDemanda5() != null) {
                    existingEmprendimiento.setDemanda5(emprendimiento.getDemanda5());
                }
                if (emprendimiento.getLotes() != null) {
                    existingEmprendimiento.setLotes(emprendimiento.getLotes());
                }
                if (emprendimiento.getViviendas() != null) {
                    existingEmprendimiento.setViviendas(emprendimiento.getViviendas());
                }
                if (emprendimiento.getComProf() != null) {
                    existingEmprendimiento.setComProf(emprendimiento.getComProf());
                }
                if (emprendimiento.getHabitaciones() != null) {
                    existingEmprendimiento.setHabitaciones(emprendimiento.getHabitaciones());
                }
                if (emprendimiento.getManzanas() != null) {
                    existingEmprendimiento.setManzanas(emprendimiento.getManzanas());
                }
                if (emprendimiento.getDemanda() != null) {
                    existingEmprendimiento.setDemanda(emprendimiento.getDemanda());
                }
                if (emprendimiento.getFechaDeRelevamiento() != null) {
                    existingEmprendimiento.setFechaDeRelevamiento(emprendimiento.getFechaDeRelevamiento());
                }
                if (emprendimiento.getTelefono() != null) {
                    existingEmprendimiento.setTelefono(emprendimiento.getTelefono());
                }
                if (emprendimiento.getAnoPriorizacion() != null) {
                    existingEmprendimiento.setAnoPriorizacion(emprendimiento.getAnoPriorizacion());
                }
                if (emprendimiento.getContratoOpen() != null) {
                    existingEmprendimiento.setContratoOpen(emprendimiento.getContratoOpen());
                }
                if (emprendimiento.getNegociacion() != null) {
                    existingEmprendimiento.setNegociacion(emprendimiento.getNegociacion());
                }
                if (emprendimiento.getFecha() != null) {
                    existingEmprendimiento.setFecha(emprendimiento.getFecha());
                }
                if (emprendimiento.getCodigoDeFirma() != null) {
                    existingEmprendimiento.setCodigoDeFirma(emprendimiento.getCodigoDeFirma());
                }
                if (emprendimiento.getFechaFirma() != null) {
                    existingEmprendimiento.setFechaFirma(emprendimiento.getFechaFirma());
                }
                if (emprendimiento.getObservaciones() != null) {
                    existingEmprendimiento.setObservaciones(emprendimiento.getObservaciones());
                }
                if (emprendimiento.getComentario() != null) {
                    existingEmprendimiento.setComentario(emprendimiento.getComentario());
                }
                if (emprendimiento.getComenCan() != null) {
                    existingEmprendimiento.setComenCan(emprendimiento.getComenCan());
                }
                if (emprendimiento.getEstadoFirma() != null) {
                    existingEmprendimiento.setEstadoFirma(emprendimiento.getEstadoFirma());
                }
                if (emprendimiento.getEstado() != null) {
                    existingEmprendimiento.setEstado(emprendimiento.getEstado());
                }
                if (emprendimiento.getEstadoBC() != null) {
                    existingEmprendimiento.setEstadoBC(emprendimiento.getEstadoBC());
                }

                return existingEmprendimiento;
            })
            .map(emprendimientoRepository::save);
    }

    /**
     * Get all the emprendimientos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Emprendimiento> findAll(Pageable pageable) {
        log.debug("Request to get all Emprendimientos");
        return emprendimientoRepository.findAll(pageable);
    }

    /**
     * Get one emprendimiento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Emprendimiento> findOne(Long id) {
        log.debug("Request to get Emprendimiento : {}", id);
        return emprendimientoRepository.findById(id);
    }

    /**
     * Delete the emprendimiento by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Emprendimiento : {}", id);
        emprendimientoRepository.deleteById(id);
    }
}
