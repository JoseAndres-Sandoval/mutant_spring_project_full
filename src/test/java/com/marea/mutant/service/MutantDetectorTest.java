
package com.marea.mutant.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MutantDetectorTest {

    private final MutantDetector detector = new MutantDetector();

    @Test
    public void testMutantExample() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertTrue(detector.isMutant(dna));
    }

    @Test
    public void testHumanExample() {
        String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
        assertFalse(detector.isMutant(dna));
    }

    @Test
    public void testInvalidInput() {
        String[] bad = {"ATG","CAGT","TT"};
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(bad));
    }
}
