
package com.marea.mutant.dto;

public class StatsResponse {
    private long count_mutant_dna;
    private long count_human_dna;
    private double ratio;

    public StatsResponse() {}

    public StatsResponse(long m, long h) {
        this.count_mutant_dna = m;
        this.count_human_dna = h;
        this.ratio = (h == 0) ? 0.0 : ((double)m / (double)h);
    }

    public long getCount_mutant_dna() { return count_mutant_dna; }
    public long getCount_human_dna() { return count_human_dna; }
    public double getRatio() { return ratio; }

    public void setCount_mutant_dna(long v) { this.count_mutant_dna = v; }
    public void setCount_human_dna(long v) { this.count_human_dna = v; }
    public void setRatio(double r) { this.ratio = r; }
}
