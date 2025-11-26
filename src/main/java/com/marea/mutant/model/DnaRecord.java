
package com.marea.mutant.model;

import javax.persistence.*;

@Entity
@Table(name = "dna_records", uniqueConstraints = @UniqueConstraint(columnNames = {"dna_hash"}))
public class DnaRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_hash", nullable = false, unique = true)
    private String dnaHash;

    @Column(name = "is_mutant", nullable = false)
    private boolean isMutant;

    public DnaRecord() {}

    public DnaRecord(String dnaHash, boolean isMutant) {
        this.dnaHash = dnaHash;
        this.isMutant = isMutant;
    }

    public Long getId() { return id; }
    public String getDnaHash() { return dnaHash; }
    public boolean isMutant() { return isMutant; }

    public void setId(Long id) { this.id = id; }
    public void setDnaHash(String dnaHash) { this.dnaHash = dnaHash; }
    public void setMutant(boolean mutant) { isMutant = mutant; }
}
