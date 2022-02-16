package ar.teco.repository;

import ar.teco.domain.EjecCuentas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EjecCuentas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EjecCuentasRepository extends JpaRepository<EjecCuentas, Long> {}
