/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The interface implementation.
 * There's not clear if the child nodes can repeat under node or not, so I decided to allow to be repeated.
 * @author patrik knakal
 */
public class GNodeImpl implements GNode {

    private final String name;
    //want to be able to add nodes without big memory allocation (it will happen when clonnig array)
    private final List<GNode> children = new ArrayList<>();

    public GNodeImpl(String name, GNode[] children) {
        this(name);
        
        if (children != null) {
            this.children.addAll(Arrays.asList(children));
        }
    }

    public GNodeImpl(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("wrong name");
        }
        
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public GNode[] getChildren() {
        return children.toArray(new GNode[children.size()]);
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public void addGNode(GNode node) {
        children.add(node);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.children);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GNodeImpl other = (GNodeImpl) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.children, other.children)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "name=" + name + ", children=" + children.size() + '}';
    }
    
}
