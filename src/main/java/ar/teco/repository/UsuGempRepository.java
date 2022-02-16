package ar.teco.repository;

import ar.teco.domain.UsuGemp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UsuGemp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuGempRepository extends JpaRepository<UsuGemp, Long> {}
