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
import com.miyagilabs.blindfold.util.Pair;
import com.miyagilabs.blindfold.util.Tree;

/**
 *
 * @author Görkem Mülayim
 */
public class BaseWalker implements Walker {
    private final String[] code;
    private final Forest forest;
    private Tree currentTree;
    private Node currentNode;
    private int currentLine;

    public BaseWalker(Forest forest, String code) {
        this.code = code.split("\\n");
        this.forest = forest;
        currentLine = 0;
    }

    @Override
    public String next() {
        if(currentNode == null) {
            currentTree = forest.getTree(0);
            currentNode = currentTree.getRoot();
        }
        else if(currentNode.getParent() == null) {
            currentTree = nextTree(currentTree);
            currentNode = currentTree.getRoot();
        }
        else {
            Node parent = currentNode.getParent();
            int index = parent.indexOfChild(currentNode);
            if(index < parent.getChildCount()) {
                currentNode = parent.getChild(index);
            }
            else {
                currentNode = parent.getChild(0);
            }
        }
        currentLine = currentNode.getContext().start.getLine() - 1;
        return code[currentLine].trim();
    }

    @Override
    public String previous() {
        if(currentNode == null) {
            currentTree = forest.getTree(forest.size() - 1);
            currentNode = currentTree.getRoot();
        }
        else if(currentNode.getParent() == null) {
            currentTree = previousTree(currentTree);
            currentNode = currentTree.getRoot();
        }
        else {
            Node parent = currentNode.getParent();
            int index = parent.indexOfChild(currentNode);
            if(index >= parent.getChildCount()) {
                currentNode = parent.getChild(index);
            }
            else {
                currentNode = parent.getChild(parent.getChildCount() - 1);
            }
        }
        currentLine = currentNode.getContext().start.getLine() - 1;
        return code[currentLine].trim();
    }

    @Override
    public String stepForward() {
        if(currentLine + 1 < code.length) {
            currentLine++;
        }
        Pair pair = findNearestPair(currentLine, forest);
        currentTree = pair.getTree();
        currentNode = pair.getNode();
        return code[currentLine].trim();
    }

    @Override
    public String stepBackward() {
        if(currentLine - 1 >= 0) {
            currentLine--;
        }
        Pair pair = findNearestPair(currentLine, forest);
        currentTree = pair.getTree();
        currentNode = pair.getNode();
        return code[currentLine].trim();
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

    private Pair findNearestPair(int line, Forest forest) {
        int minDifference = Integer.MAX_VALUE;
        Tree nearestTree = null;
        Node nearestNode = null;
        for(Tree tree : forest) {
            for(Node node : tree) {
                int difference = line - node.getContext().start.getLine() + 1;
                if(difference < minDifference && difference >= 0) {
                    minDifference = difference;
                    nearestNode = node;
                    nearestTree = tree;
                }
            }
        }
        return new Pair(nearestTree, nearestNode);
    }
}
