package ar.teco.service;

import ar.teco.domain.Direccion;
import ar.teco.repository.DireccionRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Direccion}.
 */
@Service
@Transactional
public class DireccionService {

    private final Logger log = LoggerFactory.getLogger(DireccionService.class);

    private final DireccionRepository direccionRepository;

    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    /**
     * Save a direccion.
     *
     * @param direccion the entity to save.
     * @return the persisted entity.
     */
    public Direccion save(Direccion direccion) {
        log.debug("Request to save Direccion : {}", direccion);
        return direccionRepository.save(direccion);
    }

    /**
     * Partially update a direccion.
     *
     * @param direccion the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Direccion> partialUpdate(Direccion direccion) {
        log.debug("Request to partially update Direccion : {}", direccion);

        return direccionRepository
            .findById(direccion.getId())
            .map(existingDireccion -> {
                if (direccion.getIdentification() != null) {
                    existingDireccion.setIdentification(direccion.getIdentification());
                }
                if (direccion.getPais() != null) {
                    existingDireccion.setPais(direccion.getPais());
                }
                if (direccion.getProvincia() != null) {
                    existingDireccion.setProvincia(direccion.getProvincia());
                }
                if (direccion.getPartido() != null) {
                    existingDireccion.setPartido(direccion.getPartido());
                }
                if (direccion.getLocalidad() != null) {
                    existingDireccion.setLocalidad(direccion.getLocalidad());
                }
                if (direccion.getCalle() != null) {
                    existingDireccion.setCalle(direccion.getCalle());
                }
                if (direccion.getAltura() != null) {
                    existingDireccion.setAltura(direccion.getAltura());
                }
                if (direccion.getRegion() != null) {
                    existingDireccion.setRegion(direccion.getRegion());
                }
                if (direccion.getSubregion() != null) {
                    existingDireccion.setSubregion(direccion.getSubregion());
                }
                if (direccion.getHub() != null) {
                    existingDireccion.setHub(direccion.getHub());
                }
                if (direccion.getBarriosEspeciales() != null) {
                    existingDireccion.setBarriosEspeciales(direccion.getBarriosEspeciales());
                }
                if (direccion.getCodigoPostal() != null) {
                    existingDireccion.setCodigoPostal(direccion.getCodigoPostal());
                }
                if (direccion.getTipoCalle() != null) {
                    existingDireccion.setTipoCalle(direccion.getTipoCalle());
                }
                if (direccion.getZonaCompetencia() != null) {
                    existingDireccion.setZonaCompetencia(direccion.getZonaCompetencia());
                }
                if (direccion.getIntersectionLeft() != null) {
                    existingDireccion.setIntersectionLeft(direccion.getIntersectionLeft());
                }
                if (direccion.getIntersectionRight() != null) {
                    existingDireccion.setIntersectionRight(direccion.getIntersectionRight());
                }
                if (direccion.getStreetType() != null) {
                    existingDireccion.setStreetType(direccion.getStreetType());
                }
                if (direccion.getLatitud() != null) {
                    existingDireccion.setLatitud(direccion.getLatitud());
                }
                if (direccion.getLongitud() != null) {
                    existingDireccion.setLongitud(direccion.getLongitud());
                }
                if (direccion.getElementosDeRed() != null) {
                    existingDireccion.setElementosDeRed(direccion.getElementosDeRed());
                }

                return existingDireccion;
            })
            .map(direccionRepository::save);
    }

    /**
     * Get all the direccions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Direccion> findAll(Pageable pageable) {
        log.debug("Request to get all Direccions");
        return direccionRepository.findAll(pageable);
    }

    /**
     * Get one direccion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Direccion> findOne(Long id) {
        log.debug("Request to get Direccion : {}", id);
        return direccionRepository.findById(id);
    }

    /**
     * Delete the direccion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Direccion : {}", id);
        direccionRepository.deleteById(id);
    }
}
