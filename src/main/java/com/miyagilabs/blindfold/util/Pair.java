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

/**
 *
 * @author Görkem Mülayim
 */
public class Pair {
    private final Tree tree;
    private final Node node;

    public Pair(Tree tree, Node node) {
        this.tree = tree;
        this.node = node;
    }

    public Tree getTree() {
        return tree;
    }

    public Node getNode() {
        return node;
    }
};
