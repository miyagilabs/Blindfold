/*
 * Copyright (C) 2017 Miyagilabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.miyagilabs.blindfold.util;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;

/**
 *
 * @author Görkem Mülayim
 */
public class Tree implements Iterable<Node> {
    private final Node root;
    private int size;

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public Iterator<Node> iterator() {
        return new TreeIterator(this);
    }

    public ListIterator<Node> listIterator() {
        return new TreeListIterator(this);
    }

    public int size() {
        size = 0;
        Iterator<Node> treeIterator = new TreeIterator(this);
        while(treeIterator.hasNext()) {
            size++;
            treeIterator.next();
        }
        return size;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null) {
            return false;
        }
        if(!this.getClass().equals(object.getClass())) {
            return false;
        }
        Tree tree = (Tree) object;
        if(this.hashCode() != tree.hashCode()) {
            return false;
        }
        return this.root.equals(tree.getRoot()) && this.size == tree.size();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.root);
        hash = 23 * hash + this.size;
        return hash;
    }
}
