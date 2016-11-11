/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class covers the gnode methods.
 * @author patrik knakal
 */
public class GNodeMethods {
    
    /**
     * Returns all nodes within the (sub)graph represented by given node.
     * Not defined if should keep order or not.
     * Not clear why using ArrayList instead of List interface, but the method signature is defined this way.
     * @param node node to process
     * @return list of all nodes within the (sub)graph
     */
    public ArrayList walkGraph(GNode node) {
        if (node == null) {
            throw new IllegalArgumentException("node is null");
            //or can simply return null or empty result
        }
        
        Set<GNode> nodes = new HashSet<>();
        nodes.add(node);
        addSubNodes(nodes, node);
        return new ArrayList<>(nodes);
    }
    
    private void addSubNodes(Set<GNode> nodes, GNode node) {
        nodes.addAll(Arrays.asList(node.getChildren()));
        for (GNode _node : node.getChildren()) {
            addSubNodes(nodes, _node);
        }
    }
    
    /**
     * This method returns a list of possible paths.
     * Not clear why using ArrayList instead of List interface, but the method signature is defined this way.
     * @param node node to get paths from
     * @return list of paths (nodes list)
     */
    public ArrayList<ArrayList<GNode>> paths(GNode node) {
        if (node == null) {
            throw new IllegalArgumentException("node is null");
            //or can simply return null or empty result
        }
        
        List<GNode> corePath = new ArrayList<>();
        ArrayList<ArrayList<GNode>> paths = new ArrayList<>();
        buildPath(corePath, paths, node);
        return paths;
    }
    
    private void buildPath(List<GNode> corePath, ArrayList<ArrayList<GNode>> paths, GNode node) {
        corePath.add(node);
        if (node.hasChildren()) {
            for (GNode _node : node.getChildren()) {
                buildPath(corePath, paths, _node);
            }
        } else {
            paths.add(new ArrayList<>(corePath));
        }
        corePath.remove(node);
    }
}
