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
import java.util.List;
import java.util.Objects;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeNode {
    private TreeNode parent;
    private final List<TreeNode> children;
    private final ParserRuleContext context;

    public TreeNode(ParserRuleContext context) {
        this.children = new ArrayList<>(8);
        this.context = context;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public TreeNode getChild(int index) {
        return children.get(index);
    }

    public List<TreeNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(TreeNode child) {
        child.setParent(this);
        children.add(child);
    }

    public int getChildCount() {
        return children.size();
    }

    public ParserRuleContext getContext() {
        return this.context;
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
        TreeNode treeNode = (TreeNode) object;
        if(this.hashCode() != treeNode.hashCode()) {
            return false;
        }
        return this.children.equals(treeNode.getChildren()) && this.context.getText().equals(
                treeNode
                        .getContext().getText());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.children);
        hash = 79 * hash + Objects.hashCode(this.context.getText());
        return hash;
    }
}
