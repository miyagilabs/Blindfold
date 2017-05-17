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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Görkem Mülayim
 */
public class Forest implements Iterable<Tree> {
    private final List<Tree> forest;

    public Forest() {
        forest = new ArrayList<>(1);
    }

    public void addTree(Tree tree) {
        forest.add(tree);
    }

    public Tree getTree(int index) {
        return forest.get(index);
    }

    @Override
    public Iterator<Tree> iterator() {
        return forest.iterator();
    }

    public ListIterator<Tree> listIterator() {
        return forest.listIterator();
    }
}
