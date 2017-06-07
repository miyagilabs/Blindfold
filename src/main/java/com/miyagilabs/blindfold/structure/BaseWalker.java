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
package com.miyagilabs.blindfold.structure;

import com.miyagilabs.blindfold.util.Forest;
import com.miyagilabs.blindfold.util.Node;
import com.miyagilabs.blindfold.util.Tree;

/**
 *
 * @author Görkem Mülayim
 */
public class BaseWalker implements Walker {
    private final Forest forest;
    private Tree currentTree;
    private Node currentNode;

    public BaseWalker(Forest forest) {
        this.forest = forest;
    }

    @Override
    public String next() {
        if(currentNode == null) {
            currentTree = nextTree(currentTree);
            return currentTree.getRoot().getContext().getText();
        }
        currentNode = nextNode(currentNode);
        return currentNode.getContext().getText();
    }

    @Override
    public String previous() {
        if(currentNode == null) {
            currentTree = previousTree(currentTree);
            return currentTree.getRoot().getContext().getText();
        }
        currentNode = previousNode(currentNode);
        return currentNode.getContext().getText();
    }

    @Override
    public String stepForward() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String stepBackward() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Tree nextTree(Tree tree) {
        if(currentTree == null) {
            return forest.getTree(0);
        }
        int index = forest.indexOfTree(tree) + 1;
        if(index < forest.size()) {
            return forest.getTree(index);
        }
        return forest.getTree(0);
    }

    private Tree previousTree(Tree tree) {
        if(currentTree == null) {
            return forest.getTree(forest.size() - 1);
        }
        int index = forest.indexOfTree(tree) - 1;
        if(index >= 0) {
            return forest.getTree(index);
        }
        return forest.getTree(forest.size() - 1);
    }

    private Node nextNode(Node node) {
        Node parent = node.getParent();
        int index = parent.indexOfChild(node) + 1;
        if(index < parent.getChildCount()) {
            return parent.getChild(index);
        }
        return parent.getChild(0);
    }

    private Node previousNode(Node node) {
        Node parent = node.getParent();
        int index = parent.indexOfChild(node) - 1;
        if(index >= 0) {
            return parent.getChild(index);
        }
        return parent.getChild(parent.getChildCount() - 1);
    }
}
