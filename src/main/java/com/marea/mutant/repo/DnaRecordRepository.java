
package com.marea.mutant.repo;

import com.marea.mutant.model.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {
    Optional<DnaRecord> findByDnaHash(String dnaHash);
    long countByIsMutantTrue();
    long countByIsMutantFalse();
}
