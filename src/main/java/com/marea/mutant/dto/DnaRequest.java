package com.marea.mutant.dto;

import com.marea.mutant.validator.ValidDna;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor // Crea el constructor con argumentos: new DnaRequest(dna)
@NoArgsConstructor  // Crea el constructor vacío: new DnaRequest()
public class DnaRequest implements Serializable {

    @NotNull(message = "El ADN no puede ser nulo")
    @ValidDna(message = "El ADN debe ser cuadrado (NxN) y contener solo letras A, T, C, G")
    private String[] dna;
}