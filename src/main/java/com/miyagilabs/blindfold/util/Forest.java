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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 *
 * @author Görkem Mülayim
 */
public class Forest implements Iterable<Tree> {
    private final List<Tree> trees;

    public Forest() {
        trees = new ArrayList<>(1);
    }

    @Override
    public Iterator<Tree> iterator() {
        return trees.iterator();
    }

    public void addTree(Tree tree) {
        trees.add(tree);
    }

    public Tree getTree(int index) {
        return trees.get(index);
    }

    public List<Tree> getTrees() {
        return Collections.unmodifiableList(trees);
    }

    public int size() {
        return trees.size();
    }

    public ListIterator<Tree> listIterator() {
        return trees.listIterator();
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
        Forest obj = (Forest) object;
        if(this.hashCode() != obj.hashCode()) {
            return false;
        }
        return this.trees.equals(obj.getTrees());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.trees);
        return hash;
    }
}
