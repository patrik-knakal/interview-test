/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intview;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * This one reads text file and counts worlds in it. It's case insensitive.
 * Separators are: white-spaces, ~,!@#$%^&*(){}[]:;+-='\"\/_ ... hope that's all.
 * @author patrik knakal
 */
public class WordCount {

    private static final String USAGE = "Usage: java -jar app.jar <file> [<file> <file> ...]\n\tat least one file";
    private static final String TO_REPLACE = "[~,!@#\\$%\\^&*(){}\\[\\]:;+-='\"\\\\/_]";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(USAGE);
        } else {
            File[] files = new File[args.length];
            for (int i = 0 ; i< args.length ; i++) {
                File f = new File(args[i]);
                if (!f.isFile()) {
                    System.err.println("Cannot open file: '" + args[i] + "'.");
                }
                files[i] = f;
            }
            WordCount wc = new WordCount();
            System.out.println(wc.process(files));
            
        }
    }
    
    /**
     * Process given files
     * @param files files to count
     * @return string representation of the result
     */
    String process(File[] files) {
        //keep list of words for each count
        Map<Integer, Set<String>> countToWords = new HashMap<>();
        //keep count for each word
        Map<String, Integer> wordToCount = new HashMap<>();
        
        for (File f : files) {
            processFile(countToWords, wordToCount, f);
        }
        
        return prepareOutput(countToWords);
    }
    
    /**
     * Process single file. The maps are used to count everything in one iteration and to allow single iteration for output preparation.
     * @param countToWords mapping count to words of such count
     * @param wordToCount mapping word to its count
     * @param f file to process
     */
    void processFile(final Map<Integer, Set<String>> countToWords, final Map<String, Integer> wordToCount, File f) {
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                processLine(countToWords, wordToCount, scanner.nextLine());
            }
        } catch (Exception ex) {
            System.err.println("Failed to porcess file: '" + f.getAbsolutePath() + "'.\n\t" + ex.getMessage());
        }
    }
    
    /**
     * Count words in given line. The maps are used to count everything in one iteration and to allow single iteration for output preparation.
     * @param countToWords mapping count to words of such count
     * @param wordToCount mapping word to its count
     * @param line line to process
     */
    void processLine(final Map<Integer, Set<String>> countToWords, final Map<String, Integer> wordToCount, String line) {
        String _line = line.replaceAll(TO_REPLACE, " ").trim(); //removing useless whitespaces
        if (_line.isEmpty()) { //removing empty lines
            return;
        }
        for (String word : _line.split("\\s+")) {
            String _word = word.toLowerCase();
            if (wordToCount.containsKey(_word)) {
                int originCount = wordToCount.get(_word);
                wordToCount.put(_word, originCount+1);
                countToWords.get(originCount).remove(_word);
                if (!countToWords.containsKey(originCount+1)) {
                    countToWords.put(originCount+1, new HashSet<>());
                }
                countToWords.get(originCount+1).add(_word);
            } else { //first time of appearance
                wordToCount.put(_word, 1);
                if (!countToWords.containsKey(1)) {
                    countToWords.put(1, new HashSet<>());
                }
                countToWords.get(1).add(_word);
            }
        }
    }
    
    /**
     * Preparing output to print from given count-to-words map
     * @param countToWords map to prepare output for
     * @return string to print
     */
    String prepareOutput(Map<Integer, Set<String>> countToWords) {
        StringBuilder sb = new StringBuilder();
        
        TreeSet<Integer> counts = new TreeSet<>(countToWords.keySet()); //sorting the counts
        for (Integer next : counts.descendingSet()) {
            for (String word : countToWords.get(next)) {
                sb.append(next).append(" ").append(word).append("\n");
            }
        }
        return sb.toString();
    }
    
}
