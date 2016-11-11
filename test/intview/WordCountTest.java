/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intview;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrik knakal
 */
public class WordCountTest {
    
    public WordCountTest() {
    }
    
    /**
     * Test of process method, of class WordCount.
     */
    @Test
    public void testProcess() {
        System.out.println("process");

        File f1 = null;
        try {
            f1 = File.createTempFile("data001", "txt");
            try (PrintWriter writer = new PrintWriter(f1, "UTF-8")) {
                writer.println("The first line");
            }
            f1.deleteOnExit();
        } catch (Exception e) {
            fail("Unable to create testing file");
        }
        
        File f2 = null;
        try {
            f2 = File.createTempFile("data002", "txt");
            try (PrintWriter writer = new PrintWriter(f2, "UTF-8")) {
                writer.println("The second line");
            }
            f2.deleteOnExit();
        } catch (Exception e) {
            fail("Unable to create testing file");
        }
        
        WordCount instance = new WordCount();
        String result = instance.process(new File[] {f1, f2});
        
        assertEquals("2 the\n2 line\n1 first\n1 second\n", result);
    }

    /**
     * Test of processFile method, of class WordCount.
     */
    @Test
    public void testProcessFile() {
        System.out.println("processFile");
        Map<Integer, Set<String>> countToWords = new HashMap<>();
        Map<String, Integer> wordToCount = new HashMap<>();

        
        File f = null;
        try {
            f = File.createTempFile("data", "txt");
            try (PrintWriter writer = new PrintWriter(f, "UTF-8")) {
                writer.println("The first line");
                writer.println("The second line");
            }
            f.deleteOnExit();
        } catch (Exception e) {
            fail("Unable to create testing file");
        }
        
        WordCount instance = new WordCount();
        instance.processFile(countToWords, wordToCount, f);

        Set<String> count_1 = new HashSet<>();
        count_1.addAll(Arrays.asList("first", "second"));
        
        Set<String> count_2 = new HashSet<>();
        count_2.addAll(Arrays.asList("the", "line"));
        
        Map<Integer, Set<String>> countToWordsExpected = new HashMap<>();
        countToWordsExpected.put(1, count_1);
        countToWordsExpected.put(2, count_2);
        
        assertEquals(countToWordsExpected, countToWords);
        
        Map<String, Integer> wordToCountExpected = new HashMap<>();
        wordToCountExpected.put("the", 2);
        wordToCountExpected.put("line", 2);
        wordToCountExpected.put("first", 1);
        wordToCountExpected.put("second", 1);
        
        assertEquals(wordToCountExpected, wordToCount);
    }

    /**
     * Test of processLine method, of class WordCount.
     */
    @Test
    public void testProcessLine() {
        System.out.println("processLine");
        String line = "a.b,c/d\\e'f\"g~h!i@j#k$l%m^n&o*p(q)r{s}t[u]v:w;x+y-z=a/b_c";
        
        Map<Integer, Set<String>> countToWords = new HashMap<>();
        Map<String, Integer> wordToCount = new HashMap<>();
        
        WordCount instance = new WordCount();
        instance.processLine(countToWords, wordToCount, line);
        
        Set<String> count_1 = new HashSet<>();
        count_1.addAll(Arrays.asList("d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "y", "x", "z"));
        
        Set<String> count_2 = new HashSet<>();
        count_2.addAll(Arrays.asList("a", "b", "c"));
        
        Map<Integer, Set<String>> countToWordsExpected = new HashMap<>();
        countToWordsExpected.put(1, count_1);
        countToWordsExpected.put(2, count_2);
        
        assertEquals(countToWordsExpected, countToWords);
        
        Map<String, Integer> wordToCountExpected = new HashMap<>();
        wordToCountExpected.put("a", 2);
        wordToCountExpected.put("b", 2);
        wordToCountExpected.put("c", 2);
        wordToCountExpected.put("d", 1);
        wordToCountExpected.put("e", 1);
        wordToCountExpected.put("f", 1);
        wordToCountExpected.put("g", 1);
        wordToCountExpected.put("h", 1);
        wordToCountExpected.put("i", 1);
        wordToCountExpected.put("j", 1);
        wordToCountExpected.put("k", 1);
        wordToCountExpected.put("l", 1);
        wordToCountExpected.put("m", 1);
        wordToCountExpected.put("n", 1);
        wordToCountExpected.put("o", 1);
        wordToCountExpected.put("p", 1);
        wordToCountExpected.put("q", 1);
        wordToCountExpected.put("r", 1);
        wordToCountExpected.put("s", 1);
        wordToCountExpected.put("t", 1);
        wordToCountExpected.put("u", 1);
        wordToCountExpected.put("v", 1);
        wordToCountExpected.put("w", 1);
        wordToCountExpected.put("x", 1);
        wordToCountExpected.put("y", 1);
        wordToCountExpected.put("z", 1);
        
        assertEquals(wordToCountExpected, wordToCount);
    }
    
    /**
     * Test of prepareOutput method, of class WordCount.
     */
    @Test
    public void testPrepareOutput() {
        System.out.println("prepareOutput");
        Map<Integer, Set<String>> countToWords = new HashMap<>();
        countToWords.put(4, new HashSet(Arrays.asList("a", "c")));
        countToWords.put(10, new HashSet(Arrays.asList("b", "d")));
        
        WordCount instance = new WordCount();
        String result = instance.prepareOutput(countToWords);
        
        assertEquals("10 b\n10 d\n4 a\n4 c\n", result);
    }
    
}
