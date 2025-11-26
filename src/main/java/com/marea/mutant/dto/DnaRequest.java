
package com.marea.mutant.dto;

public class DnaRequest {
    private String[] dna;

    public DnaRequest() {}
    public DnaRequest(String[] dna) { this.dna = dna; }

    public String[] getDna() { return dna; }
    public void setDna(String[] dna) { this.dna = dna; }
}
