/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544 | tomkral.nl
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.primertopdf.control;

import mmb.thjkral.primertopdf.model.*;


/**
 * Main class of the tool.
 * Executes other Classes and methods in the correct order.
 * 
 * @author tom
 */
public class PrimerToPDF {
    
    
    /**
     * Main method of the Class.
     * Creates instance of the Class and starts the tool
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        PrimerToPDF ptp = new PrimerToPDF();
        ptp.start(args);
        
    }

    private void start(String[] args) {
        
        /*
        Parse the commandline and recieve the location of:
        - Input
        - Output
        */
        
        CLI cli = new CLI();
        cli.parse(args);
        
//        System.out.println("--TEST--\nIn= "
//                + cli.input
//                + "\nout= "
//                + cli.output);
        
        
        ParseInput parse = new ParseInput();
        parse.readLines(cli.input);
            
    }
    
}
