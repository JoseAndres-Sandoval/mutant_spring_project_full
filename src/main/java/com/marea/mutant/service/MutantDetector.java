
package com.marea.mutant.service;

import org.springframework.stereotype.Component;

@Component
public class MutantDetector {

    // Return true if more than one sequence of 4 equal letters is found
    public boolean isMutant(String[] dna) {
        validate(dna);
        int n = dna.length;
        char[][] mat = new char[n][n];
        for (int i = 0; i < n; i++) mat[i] = dna[i].toUpperCase().toCharArray();

        int sequences = 0;
        // directions: right, down, diag down-right, diag down-left
        int[][] dirs = {{0,1},{1,0},{1,1},{1,-1}};

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                char base = mat[i][j];
                if (!isValidBase(base)) continue;
                for (int[] d : dirs) {
                    if (checkFrom(mat, i, j, d[0], d[1])) {
                        sequences++;
                        if (sequences > 1) return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkFrom(char[][] mat, int i, int j, int di, int dj) {
        int n = mat.length;
        int count = 1;
        int r = i + di, c = j + dj;
        while (r >= 0 && r < n && c >= 0 && c < n && mat[r][c] == mat[i][j]) {
            count++;
            if (count == 4) return true;
            r += di; c += dj;
        }
        return false;
    }

    private boolean isValidBase(char b) {
        return b == 'A' || b == 'T' || b == 'C' || b == 'G';
    }

    private void validate(String[] dna) {
        if (dna == null) throw new IllegalArgumentException("dna is null");
        int n = dna.length;
        if (n == 0) throw new IllegalArgumentException("dna empty");
        for (String s : dna) {
            if (s == null || s.length() != n) throw new IllegalArgumentException("dna must be NxN");
            for (char c : s.toUpperCase().toCharArray()) {
                if (!isValidBase(c)) throw new IllegalArgumentException("invalid base: " + c);
            }
        }
    }
}
