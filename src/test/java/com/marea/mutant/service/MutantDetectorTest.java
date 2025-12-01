package com.marea.mutant.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    private final MutantDetector detector = new MutantDetector();

    // --- CASOS POSITIVOS (ES MUTANTE) ---

    @Test
    void testMutantHorizontal() {
        String[] dna = { "AAAA", "CCCC", "TCAG", "GGTC" };
        assertTrue(detector.isMutant(dna));
    }

    @Test
    void testMutantVertical() {
        String[] dna = { "ATCG", "ATCG", "ATCG", "ATCG" };
        assertTrue(detector.isMutant(dna));
    }

    @Test
    void testMutantDiagonal() {
        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        assertTrue(detector.isMutant(dna));
    }

    // --- CASOS NEGATIVOS (ES HUMANO) ---

    @Test
    void testHumanNoSequences() {
        String[] dna = { "ATGC", "CAGT", "TTAT", "AGAC" };
        assertFalse(detector.isMutant(dna));
    }

    @Test
    void testHumanOnlyOneSequence() {
        // Caso clave pedido por el profesor: Solo 1 secuencia NO es mutante
        String[] dna = { "AAAA", "CAGT", "TTAT", "AGAC" };
        assertFalse(detector.isMutant(dna));
    }

    // --- VALIDACIONES DE ERRORES (Punto 1.1 y 2.4) ---

    @Test
    void testNullDna() {
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(null));
    }

    @Test
    void testEmptyDna() {
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(new String[]{}));
    }

    @Test
    void testNonSquareDna() {
        // Matriz de 4x3 (No cuadrada)
        String[] dna = { "ATG", "CAG", "TTA", "AGA" };
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(dna));
    }

    @Test
    void testInvalidCharacters() {
        // Contiene 'X'
        String[] dna = { "ATGC", "CAGT", "TTAT", "AGAX" };
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(dna));
    }

    @Test
    void testNullRow() {
        String[] dna = { "ATGC", null, "TTAT", "AGAC" };
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(dna));
    }
}