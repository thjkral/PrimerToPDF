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
    
    
    
}
