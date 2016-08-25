/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544 | tomkral.nl
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.primertopdf.model;

/**
 * 
 * @author tom
 */
public class PrimerPair{
    
    int pair_no;
    
    /*
    Every primer has it's own sequence.
    */
    private String sequenceForward;
    private String sequenceReverse;
    private String sequenceInternal;
    
    /*
    The percentage of Guanine and Cytosine in the respective sequences. Is a
    percentage.
    */
    private double GCcontent_f;
    private double GCcontent_r;
    private double GCcontent_i;
    
    /*
    The temperature on which half of the primer has separated from the sequence.
    In celcius.
    */
    private double MeltingTemp_f;
    private double MeltingTemp_r;
    private double MeltingTemp_i;
    
    /*
    Each primer and pair has been given an penalty by Primer3 (see documentaton
    for the method). The lower the better. The output is ordered from best to 
    worst option.
    */
    private double penalty_f;
    private double penalty_r;
    private double penalty_i;
    private double penalty_p;
    
    /*
    The size of the PCR product.
    */
    private int productSize;
    
    
    
    
    
    

    public PrimerPair(int pair_no) {
        this.pair_no = pair_no;
    }

    public int getPair_no() {
        return pair_no;
    }

    public void setPair_no(int pair_no) {
        this.pair_no = pair_no;
    }

    public String getSequenceForward() {
        return sequenceForward;
    }

    public void setSequenceForward(String sequenceForward) {
        this.sequenceForward = sequenceForward;
    }

    public String getSequenceReverse() {
        return sequenceReverse;
    }

    public void setSequenceReverse(String sequenceReverse) {
        this.sequenceReverse = sequenceReverse;
    }

    public String getSequenceInternal() {
        return sequenceInternal;
    }

    public void setSequenceInternal(String sequenceInternal) {
        this.sequenceInternal = sequenceInternal;
    }

    public double getGCcontent_f() {
        return GCcontent_f;
    }

    public void setGCcontent_f(double GCcontent_f) {
        this.GCcontent_f = GCcontent_f;
    }

    public double getGCcontent_r() {
        return GCcontent_r;
    }

    public void setGCcontent_r(double GCcontent_r) {
        this.GCcontent_r = GCcontent_r;
    }

    public double getGCcontent_i() {
        return GCcontent_i;
    }

    public void setGCcontent_i(double GCcontent_i) {
        this.GCcontent_i = GCcontent_i;
    }

    public double getMeltingTemp_f() {
        return MeltingTemp_f;
    }

    public void setMeltingTemp_f(double MeltingTemp_f) {
        this.MeltingTemp_f = MeltingTemp_f;
    }

    public double getMeltingTemp_r() {
        return MeltingTemp_r;
    }

    public void setMeltingTemp_r(double MeltingTemp_r) {
        this.MeltingTemp_r = MeltingTemp_r;
    }

    public double getMeltingTemp_i() {
        return MeltingTemp_i;
    }

    public void setMeltingTemp_i(double MeltingTemp_i) {
        this.MeltingTemp_i = MeltingTemp_i;
    }

    public double getPenalty_f() {
        return penalty_f;
    }

    public void setPenalty_f(double penalty_f) {
        this.penalty_f = penalty_f;
    }

    public double getPenalty_r() {
        return penalty_r;
    }

    public void setPenalty_r(double penalty_r) {
        this.penalty_r = penalty_r;
    }

    public double getPenalty_i() {
        return penalty_i;
    }

    public void setPenalty_i(double penalty_i) {
        this.penalty_i = penalty_i;
    }

    public double getPenalty_p() {
        return penalty_p;
    }

    public void setPenalty_p(double penalty_p) {
        this.penalty_p = penalty_p;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }

    @Override
    public String toString() {
        return "PrimerPair{" + "pair_no=" + pair_no + ", sequenceForward=" + sequenceForward + ", sequenceReverse=" + sequenceReverse + ", sequenceInternal=" + sequenceInternal + ", GCcontent_f=" + GCcontent_f + ", GCcontent_r=" + GCcontent_r + ", GCcontent_i=" + GCcontent_i + ", MeltingTemp_f=" + MeltingTemp_f + ", MeltingTemp_r=" + MeltingTemp_r + ", MeltingTemp_i=" + MeltingTemp_i + ", penalty_f=" + penalty_f + ", penalty_r=" + penalty_r + ", penalty_i=" + penalty_i + ", penalty_p=" + penalty_p + ", productSize=" + productSize + '}';
    }
    
    
}
