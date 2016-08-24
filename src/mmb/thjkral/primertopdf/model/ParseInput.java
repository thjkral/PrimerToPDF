/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544 | tomkral.nl
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.primertopdf.model;

import com.itextpdf.text.Document;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads the output file from Primer3.
 * File is in the Boulder IO format
 * @author tom
 */
public class ParseInput {
    
    MakePDF makepdf = new MakePDF();
    Document doc = makepdf.makeDocument();
    
    /**
     * Reads every line one by one.
     * Usefull information will be saved
     * @param infile
     */
    public void readLines (String infile) {
        
        infile = "/home/tom/Documents/internship/outputs/Primer3/example_output/output_duplicatedExample";
        Path inPath = Paths.get(infile);
        
        
            
        try (BufferedReader reader = Files.newBufferedReader(inPath,
            Charset.defaultCharset())) {

            String line;
            String rawData = "";
            
            
            while ((line = reader.readLine()) != null){
                
                Pattern pEndMarker = Pattern.compile(line);
                Matcher mEndMarker = pEndMarker.matcher("=\\n");
                
                if (mEndMarker.find()) {
                    getParameters(rawData);
                    rawData = "";
                } else {
                    rawData = rawData + line + "\n";
                }
                
            }
            
        } catch (Exception e) {
                System.out.println("ERROR while reading: " + e.getMessage());
        }
            
        
    }
    
    /**
     * Takes one entry from the Primer3 output and adds it to the PDF.
     * 
     * @param rawString - represents one entry
     */
    public void getParameters (String rawString) {
        
        
        //Regex for extracting headers
        Pattern pSeqID = Pattern.compile("SEQUENCE_ID=(.+)\\nSEQUENCE_TEMPLATE=([ATCGN]+)");
        Matcher mSeqID = pSeqID.matcher(rawString);
        Pattern pStats = Pattern.compile("PRIMER_([LEFT|RIGHT|INTERNAL|PAIR]+)_EXPLAIN=(.+)");
        Matcher mStats = pStats.matcher(rawString);
        
        Header header = new Header();
        
        while (mSeqID.find()) {
            header.id = mSeqID.group(1);
            header.sequence = mSeqID.group(2);
        }
        while (mStats.find()) {
            
            if (mStats.group(1).equals("LEFT")) {
                header.statLeft = mStats.group(2);
            }
            if (mStats.group(1).equals("RIGHT")) {
                header.statRight = mStats.group(2);
            }
            if (mStats.group(1).equals("INTERNAL")) {
                header.statIntern = mStats.group(2);
            }
            if (mStats.group(1).equals("PAIR")) {
                header.statPair = mStats.group(2);
            }
            
        }
          
        System.out.println(header.toString());
        doc = makepdf.addHeader(header, doc);
        
        
    }
    
    
    
}
