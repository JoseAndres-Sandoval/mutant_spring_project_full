package com.marea.mutant.service;

import com.marea.mutant.entity.DnaRecord;
import com.marea.mutant.repo.DnaRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Service
public class MutantService {

    private final DnaRecordRepository repo;
    private final MutantDetector detector;

    public MutantService(DnaRecordRepository repo, MutantDetector detector) {
        this.repo = repo;
        this.detector = detector;
    }

    @Transactional
    public boolean verifyAndSave(String[] dna) {
        String hash = hashDna(dna);

        // Verificamos si ya existe en BD (Cache)
        Optional<DnaRecord> existing = repo.findByDnaHash(hash);
        if (existing.isPresent()) {
            return existing.get().isMutant();
        }

        // Si no existe, analizamos
        boolean isMutant = detector.isMutant(dna);

        // CORRECCIÓN AQUÍ: Usamos el Builder de Lombok en lugar de new DnaRecord(...)
        DnaRecord r = DnaRecord.builder()
                .dnaHash(hash)
                .isMutant(isMutant)
                .build();

        repo.save(r);
        return isMutant;
    }

    public String hashDna(String[] dna) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] h = md.digest(String.join("|", dna).getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(h);
        } catch (NoSuchAlgorithmException e) {
            // fallback simple en caso extremo
            return String.valueOf(Arrays.hashCode(dna));
        }
    }

    public long countMutants() { return repo.countByIsMutantTrue(); }
    public long countHumans() { return repo.countByIsMutantFalse(); }
}