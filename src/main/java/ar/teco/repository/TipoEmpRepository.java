package ar.teco.repository;

import ar.teco.domain.TipoEmp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TipoEmp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoEmpRepository extends JpaRepository<TipoEmp, Long> {}
