package ar.teco.repository;

import ar.teco.domain.HistoWF;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the HistoWF entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoWFRepository extends JpaRepository<HistoWF, Long>, JpaSpecificationExecutor<HistoWF> {}
