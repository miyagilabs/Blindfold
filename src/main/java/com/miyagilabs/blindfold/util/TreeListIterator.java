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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeListIterator implements ListIterator<TreeNode> {
    private final ListIterator<TreeNode> listIterator;
    private TreeNode cursor;

    public TreeListIterator(Tree tree) {
        List<TreeNode> list = new ArrayList<>(tree.size());
        for(TreeNode treeNode : tree) {
            list.add(treeNode);
        }
        listIterator = list.listIterator();
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasNext();
    }

    @Override
    public TreeNode next() {
        cursor = listIterator.next();
        return cursor;
    }

    @Override
    public boolean hasPrevious() {
        return listIterator.hasPrevious();
    }

    @Override
    public TreeNode previous() {
        cursor = listIterator.previous();
        return cursor;
    }

    @Override
    public int nextIndex() {
        return listIterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return listIterator.previousIndex();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Read-only.");
    }

    @Override
    public void set(TreeNode e) {
        throw new UnsupportedOperationException("Read-only.");
    }

    @Override
    public void add(TreeNode e) {
        throw new UnsupportedOperationException("Read-only.");
    }
}
