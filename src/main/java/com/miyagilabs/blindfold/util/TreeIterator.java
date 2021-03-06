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
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeIterator implements Iterator<TreeNode> {
    private final Stack<TreeNode> stack;

    public TreeIterator(Tree tree) {
        this.stack = new Stack<>();
        stack.push(tree.getRoot());
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public TreeNode next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        TreeNode treeNode = stack.pop();
        for(int i = treeNode.getChildCount() - 1; i >= 0; i--) {
            stack.push(treeNode.getChild(i));
        }
        return treeNode;
    }
}
