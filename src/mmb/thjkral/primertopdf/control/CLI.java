/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544 | tomkral.nl
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.primertopdf.control;

import org.apache.commons.cli.*;

/**
 * Class for parsing the commandline.
 * Uses the Commons CLI library from Apache
 * 
 * @author tom
 */
public class CLI {

    String input;
    String output;
    
    Options option = new Options();

    /*
    Constructor
    Makes possible options
    */
    public CLI() {
        
        option.addOption("help",
                false,
                "Gives help and usage information");
        
        option.addOption("input",
                true,
                "Takes the path to a Primer3 output");
        
        option.addOption("output",
                true,
                "Path to the location of the output PDF");
    }   
    
    
    
    void parse(String[] args) {
        
        CommandLineParser parser = new BasicParser();
        
        try {
            CommandLine cl = parser.parse(option, args);
            
            if (cl.hasOption("help")) {
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp("Tool for putting Primer3 output in a PDF file", option);
            }
            
            if (cl.hasOption("input")) {
                if (cl.getOptionValue("input").isEmpty()) {
                    throw new NullPointerException();
                } else {
                    input = cl.getOptionValue("input");
                }                
            }
            
            if (cl.hasOption("output")) {
                if (cl.getOptionValue("output").isEmpty()) {
                    throw new NullPointerException();
                } else {
                    output = cl.getOptionValue("output");
                }            
                
            }
            
            
            
        } catch (ParseException | NullPointerException e) {
            System.out.println("Something went wrong! ERROR: " + e.getMessage());
        }
        
        
    }

   
    
}
