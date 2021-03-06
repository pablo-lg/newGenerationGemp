package ar.teco.repository;

import ar.teco.domain.NSE;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NSE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NSERepository extends JpaRepository<NSE, Long> {}
