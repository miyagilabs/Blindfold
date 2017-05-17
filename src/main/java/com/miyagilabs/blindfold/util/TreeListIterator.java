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
public class TreeListIterator implements ListIterator<Node> {
    private final ListIterator<Node> listIterator;
    private Node cursor;

    public TreeListIterator(Tree tree) {
        List<Node> list = new ArrayList<>(tree.size());
        for(Node node : tree) {
            list.add(node);
        }
        listIterator = list.listIterator();
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasNext();
    }

    @Override
    public Node next() {
        cursor = listIterator.next();
        return cursor;
    }

    @Override
    public boolean hasPrevious() {
        return listIterator.hasPrevious();
    }

    @Override
    public Node previous() {
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
    public void set(Node e) {
        throw new UnsupportedOperationException("Read-only.");
    }

    @Override
    public void add(Node e) {
        throw new UnsupportedOperationException("Read-only."); //To change body of generated methods, choose Tools | Templates.
    }
}
