package com.marea.mutant;

import com.marea.mutant.service.MutantDetector;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PerformanceTest {

    @Test
    void testLargeMatrixPerformance() {
        // Creamos una matriz gigante de 1000x1000
        int N = 1000;
        String[] dna = new String[N];

        // Llenamos con 'A' para que encuentre secuencias rápido
        char[] rowChars = new char[N];
        Arrays.fill(rowChars, 'A');
        String row = new String(rowChars);
        Arrays.fill(dna, row);

        MutantDetector detector = new MutantDetector();

        long start = System.currentTimeMillis();
        boolean isMutant = detector.isMutant(dna);
        long end = System.currentTimeMillis();

        long duration = end - start;
        System.out.println("Tiempo de ejecución para matriz " + N + "x" + N + ": " + duration + "ms");

        // Verificamos que sea mutante y que sea rápido (menos de 2 segundos)
        assertTrue(isMutant);
        assertTrue(duration < 2000, "El algoritmo es demasiado lento: " + duration + "ms");
    }
}
