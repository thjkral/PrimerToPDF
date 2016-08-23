/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544 | tomkral.nl
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.primertopdf.model;

import com.itextpdf.text.*;

/**
 * Creates the PDF file.
 * The file contains the best primers that Primer3 reported
 * @author tom
 */
public class MakePDF {
    
    /**
     * Makes the document.
     * Adds some meta-data too
     * @return 
     */
    public Document makeDocument () {
        
        Document doc = new Document();
        doc.addTitle("Primers - Galaxy output");
        doc.addSubject("Valiable primers");
        doc.addAuthor("Tom Kral (MMB)");
        doc.addCreator("Tom Kral (MMB)");
        doc.addCreationDate();
        
        return doc;        
    }
    
    public void close (Document doc) {
        doc.close();
    }
    
    
    
}
