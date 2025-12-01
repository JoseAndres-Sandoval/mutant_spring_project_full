package com.marea.mutant.service;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class MutantDetector {

    // Optimización pedida por el profesor (Uso de Set.of)
    private static final Set<Character> VALID_BASES = Set.of('A', 'T', 'C', 'G');
    private static final int SEQUENCE_LIMIT = 4;

    public boolean isMutant(String[] dna) {
        validate(dna);

        int n = dna.length;
        char[][] matrix = new char[n][n];

        // Convertimos a matriz de caracteres para acceso rápido
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toUpperCase().toCharArray();
        }

        int sequencesFound = 0;

        // Recorremos la matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Si encontramos más de 1 secuencia, cortamos (Early Termination)
                if (sequencesFound > 1) return true;

                // Búsqueda en 4 direcciones
                if (checkHorizontal(matrix, i, j, n)) sequencesFound++;
                if (checkVertical(matrix, i, j, n)) sequencesFound++;
                if (checkDiagonal(matrix, i, j, n)) sequencesFound++;
                if (checkAntiDiagonal(matrix, i, j, n)) sequencesFound++;
            }
        }

        return sequencesFound > 1;
    }

    private void validate(String[] dna) {
        if (dna == null) throw new IllegalArgumentException("El ADN no puede ser nulo");
        if (dna.length == 0) throw new IllegalArgumentException("El ADN no puede estar vacío");

        int n = dna.length;
        for (String row : dna) {
            if (row == null) throw new IllegalArgumentException("El ADN no puede tener filas nulas");
            if (row.length() != n) throw new IllegalArgumentException("El ADN debe ser cuadrado (NxN)");

            // Validación optimizada con Set
            for (char c : row.toUpperCase().toCharArray()) {
                if (!VALID_BASES.contains(c)) {
                    throw new IllegalArgumentException("Carácter inválido en ADN: " + c);
                }
            }
        }
    }

    private boolean checkHorizontal(char[][] mat, int i, int j, int n) {
        if (j + SEQUENCE_LIMIT > n) return false;
        char base = mat[i][j];
        for (int k = 1; k < SEQUENCE_LIMIT; k++) {
            if (mat[i][j + k] != base) return false;
        }
        return true;
    }

    private boolean checkVertical(char[][] mat, int i, int j, int n) {
        if (i + SEQUENCE_LIMIT > n) return false;
        char base = mat[i][j];
        for (int k = 1; k < SEQUENCE_LIMIT; k++) {
            if (mat[i + k][j] != base) return false;
        }
        return true;
    }

    private boolean checkDiagonal(char[][] mat, int i, int j, int n) {
        if (i + SEQUENCE_LIMIT > n || j + SEQUENCE_LIMIT > n) return false;
        char base = mat[i][j];
        for (int k = 1; k < SEQUENCE_LIMIT; k++) {
            if (mat[i + k][j + k] != base) return false;
        }
        return true;
    }

    private boolean checkAntiDiagonal(char[][] mat, int i, int j, int n) {
        if (i + SEQUENCE_LIMIT > n || j - SEQUENCE_LIMIT + 1 < 0) return false;
        char base = mat[i][j];
        for (int k = 1; k < SEQUENCE_LIMIT; k++) {
            if (mat[i + k][j - k] != base) return false;
        }
        return true;
    }
}