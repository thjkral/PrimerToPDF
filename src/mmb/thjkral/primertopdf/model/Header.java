/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * contact@tomkral.nl | +31642935544 | tomkral.nl
 */
package mmb.thjkral.primertopdf.model;

/**
 *
 * @author tom
 */
public class Header {
    
    String id;
    String sequence;
    String statLeft;
    String statRight;
    String statIntern;
    String statPair;

    @Override
    public String toString() {
        return "Template{" + "id=" + id + ", sequence=" + sequence + ", statLeft=" + statLeft + ", statRight=" + statRight + ", statIntern=" + statIntern + ", statPair=" + statPair + '}';
    }
    
    
    
    
}
