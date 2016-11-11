/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intview;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrik knakal
 */
public class GNodeMainTest {
    
    public GNodeMainTest() {
    }
    
    /**
     * Test of walkGraph method, of class GNodeMethods.
     */
    @Test
    public void testWalkGraph() {
        System.out.println("walkGraph");
        
        GNodeImpl node000 = new GNodeImpl("node000");
        GNodeImpl node001 = new GNodeImpl("node001");
        GNodeImpl node002 = new GNodeImpl("node002");
        GNodeImpl node011 = new GNodeImpl("node011");
        GNodeImpl node012 = new GNodeImpl("node012");
        GNodeImpl node021 = new GNodeImpl("node021");
        GNodeImpl node0x1 = new GNodeImpl("node0x1");
        
        node000.addGNode(node001);
        node000.addGNode(node002);
        
        node001.addGNode(node011);
        node001.addGNode(node012);
        node001.addGNode(node0x1);
        
        node002.addGNode(node021);
        node002.addGNode(node0x1);
        
        GNodeMethods instance = new GNodeMethods();
        List<GNode> result = instance.walkGraph(node000);
        
        assertTrue(result.size() == 7);
        assertTrue(result.contains(node000));
        assertTrue(result.contains(node001));
        assertTrue(result.contains(node002));
        assertTrue(result.contains(node011));
        assertTrue(result.contains(node012));
        assertTrue(result.contains(node021));
        assertTrue(result.contains(node0x1));
    }
    
    /**
     * Test of paths method, of class GNodeMethods.
     */
    @Test
    public void testPaths() {
        System.out.println("paths");
        
        GNodeImpl node000 = new GNodeImpl("node000");
        GNodeImpl node001 = new GNodeImpl("node001");
        GNodeImpl node002 = new GNodeImpl("node002");
        GNodeImpl node003 = new GNodeImpl("node003");
        GNodeImpl node011 = new GNodeImpl("node011");
        GNodeImpl node012 = new GNodeImpl("node012");
        GNodeImpl node021 = new GNodeImpl("node021");
        GNodeImpl node0x1 = new GNodeImpl("node0x1");
        
        node000.addGNode(node001);
        node000.addGNode(node002);
        node000.addGNode(node003);
        
        node001.addGNode(node011);
        node001.addGNode(node012);
        node001.addGNode(node0x1);
        
        node002.addGNode(node021);
        node002.addGNode(node0x1);
        
        GNodeMethods instance = new GNodeMethods();
        ArrayList<ArrayList<GNode>> result = instance.paths(node000);
        
        assertTrue(result.size() == 6);
        
        ArrayList<GNode> path001 = new ArrayList<>();
        path001.add(node000);
        path001.add(node001);
        path001.add(node011);
        assertTrue(result.contains(path001));
        
        ArrayList<GNode> path002 = new ArrayList<>();
        path002.add(node000);
        path002.add(node001);
        path002.add(node012);
        assertTrue(result.contains(path002));
        
        ArrayList<GNode> path003 = new ArrayList<>();
        path003.add(node000);
        path003.add(node001);
        path003.add(node0x1);
        assertTrue(result.contains(path003));
        
        ArrayList<GNode> path004 = new ArrayList<>();
        path004.add(node000);
        path004.add(node002);
        path004.add(node021);
        assertTrue(result.contains(path004));
        
        ArrayList<GNode> path005 = new ArrayList<>();
        path005.add(node000);
        path005.add(node001);
        path005.add(node0x1);
        assertTrue(result.contains(path005));
        
        ArrayList<GNode> path006 = new ArrayList<>();
        path006.add(node000);
        path006.add(node003);
        assertTrue(result.contains(path006));
    }
    
}
