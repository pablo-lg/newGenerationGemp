package ar.teco.repository;

import ar.teco.domain.GrupoEmp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GrupoEmp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrupoEmpRepository extends JpaRepository<GrupoEmp, Long> {}
