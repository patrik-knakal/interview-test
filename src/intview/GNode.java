/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intview;

/**
 * Defined interface by assignment.
 * By definition: acyclic graph; when there are not children returns an empty list; children list doesn't contain nulls
 * @author patrik knakal
 */
public interface GNode {
    
    /**
     * @return the node name
     */
    public String getName();
    
    /**
     * The children - not clear why returning as array (instead of e.g. list)
     * @return node's children
     */
    public GNode[] getChildren();
    
    /**
     * Adding this method to have to have life easier.
     * @return true if there are children; false otherwise
     */
    public boolean hasChildren();
}
