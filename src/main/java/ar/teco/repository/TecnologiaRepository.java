package ar.teco.repository;

import ar.teco.domain.Tecnologia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Tecnologia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {}
