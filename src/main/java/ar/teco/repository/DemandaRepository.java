package ar.teco.repository;

import ar.teco.domain.Demanda;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Demanda entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {}
