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
import org.antlr.v4.runtime.ParserRuleContext;

/**
 *
 * @author Görkem Mülayim
 */
public class Node {
    private Node parent;
    private final List<Node> children;
    private final ParserRuleContext context;

    public Node(ParserRuleContext context) {
        this.children = new ArrayList<>(8);
        this.context = context;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public Node setChild(int index, Node node) {
        return children.set(index, node);
    }

    public Node getChild(int index) {
        return children.get(index);
    }

    public void addChild(Node child) {
        child.setParent(this);
        children.add(child);
    }

    public int getChildCount() {
        return children.size();
    }

    public ParserRuleContext getContext() {
        return this.context;
    }

}