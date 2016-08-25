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
          
//        System.out.println(header.toString());
        doc = makepdf.addHeader(header, doc);
        
        int count = 0;
        
        String regexSeqs = "PRIMER_([LEFT|RIGHT|INTERNAL]+)_(%s)_SEQUENCE=([ATCGN]+)";
        String regexGc = "PRIMER_([LEFT|RIGHT|INTERNAL]+)_(%s)_GC_PERCENT=(.*)\\n";
        String regexTm = "PRIMER_([LEFT|RIGHT|INTERNAL]+)_(%s)_TM=(.*)\\n";
        String regexPenalties = "PRIMER_([LEFT|RIGHT|INTERNAL|PAIR]+)_(%s)_PENALTY=(.*)\\n";
        String regexProductSize = "PRIMER_PAIR_(%s)_PRODUCT_SIZE=([\\d]+)";
        
        int highestCount = calcHighestNumber(rawString) + 1;
        
        for (int i = 0; i < highestCount; i++) {
            
            PrimerPair pp = new PrimerPair(i);
            
            String seq = String.format(regexSeqs, i);
            String gc = String.format(regexGc, i);
            String tm = String.format(regexTm, i);
            String penalty = String.format(regexPenalties, i);
            String product = String.format(regexProductSize, i);
            
            Pattern pSeq = Pattern.compile(seq); // seq
            Matcher mSeq = pSeq.matcher(rawString);            
            Pattern pGc = Pattern.compile(gc); //GC
            Matcher mGc = pGc.matcher(rawString);
            Pattern pTm = Pattern.compile(tm); //TM
            Matcher mTm = pTm.matcher(rawString);
            Pattern pPenalty = Pattern.compile(penalty); //penalty
            Matcher mPenalty = pPenalty.matcher(rawString);
            Pattern pProduct = Pattern.compile(product); //productsize
            Matcher mProduct = pProduct.matcher(rawString);
            
            
            while (mSeq.find()) {//get sequences
                if (mSeq.group(1).equals("LEFT")) {
                    pp.setSequenceForward(mSeq.group(3));
                }
                if (mSeq.group(1).equals("RIGHT")) {
                    pp.setSequenceReverse(mSeq.group(3));
                }
                if (mSeq.group(1).equals("INTERNAL")) {
                    pp.setSequenceInternal(mSeq.group(3));
                }
            }
            
            
            while (mGc.find()) {//get GC
                if (mGc.group(1).equals("LEFT")) {
                    pp.setGCcontent_f(Double.parseDouble(mGc.group(3)));
                }
                if (mGc.group(1).equals("RIGHT")) {
                    pp.setGCcontent_r(Double.parseDouble(mGc.group(3)));
                }
                if (mGc.group(1).equals("INTERNAL")) {
                    pp.setGCcontent_i(Double.parseDouble(mGc.group(3)));
                }
            }
            
            
            while (mTm.find()) {//get Tm
                if (mTm.group(1).equals("LEFT")) {
                    pp.setMeltingTemp_f(Double.parseDouble(mTm.group(3)));
                }
                if (mTm.group(1).equals("RIGHT")) {
                    pp.setMeltingTemp_r(Double.parseDouble(mTm.group(3)));
                }
                if (mTm.group(1).equals("INTERNAL")) {
                    pp.setMeltingTemp_i(Double.parseDouble(mTm.group(3)));
                }
            }
            
            
            while (mPenalty.find()) {//get penalties
                if (mPenalty.group(1).equals("LEFT")) {
                    pp.setPenalty_f(Double.parseDouble(mPenalty.group(3)));
                }
                if (mPenalty.group(1).equals("RIGHT")) {
                    pp.setPenalty_r(Double.parseDouble(mPenalty.group(3)));
                }
                if (mPenalty.group(1).equals("INTERNAL")) {
                    pp.setPenalty_i(Double.parseDouble(mPenalty.group(3)));
                }
                if (mPenalty.group(1).equals("PAIR")) {
                    pp.setPenalty_p(Double.parseDouble(mPenalty.group(3)));
                }
            }
            
            
            while (mProduct.find()) {//get product size
                pp.setProductSize(Integer.parseInt(mProduct.group(2)));
            }
            
            
            System.out.println(pp.toString());
            
        }
        
        
    }
    
    
    /**
     * Determines how many primer pairs one entry has.
     * Used for iteration with for-loop
     * 
     * @param rawString
     * @return The number of primer pairs
     */
    private int calcHighestNumber(String rawString) {
        
        int highestNumber = 0;
        
        Pattern p = Pattern.compile("PRIMER_PAIR_([\\d]+)_PRODUCT_SIZE");
        Matcher m = p.matcher(rawString);
        
        while (m.find()) {
            
            int currNum = Integer.parseInt(m.group(1));
//            System.out.println(currNum);
            
            if (currNum >= highestNumber) {
                highestNumber = currNum;
            }
        }
        
        return highestNumber;
    }
    
    
    
}
