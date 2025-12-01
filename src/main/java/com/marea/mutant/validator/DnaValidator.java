package com.marea.mutant.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, String[]> {

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        if (dna == null) return false; // @NotNull en el DTO ya maneja esto, pero por seguridad
        if (dna.length == 0) return false;

        int n = dna.length;
        for (String row : dna) {
            // Validar que no sea nula y que sea cuadrada (NxN)
            if (row == null || row.length() != n) return false;

            // Validar que solo contenga caracteres válidos (A, T, C, G)
            if (!row.matches("[ATCG]+")) return false;
        }
        return true;
    }
}