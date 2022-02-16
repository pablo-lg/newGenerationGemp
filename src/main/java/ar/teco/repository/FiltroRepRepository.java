package ar.teco.repository;

import ar.teco.domain.FiltroRep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FiltroRep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FiltroRepRepository extends JpaRepository<FiltroRep, Long> {}
