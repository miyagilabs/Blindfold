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
import com.miyagilabs.blindfold.util.TreeNode;
import java.util.LinkedList;

/**
 *
 * @author Görkem Mülayim
 */
public class BaseWalker implements Walker {
    private final String[] code;
    private final LinkedList<TreeNode> linkedList;
    private TreeNode currentTreeNode;

    public BaseWalker(String code) {
        this.code = code.split("\\n");
        Generator generator = new BaseGenerator();
        Forest forest = generator.generate(code);
        linkedList = new LinkedList<>();
        for(int i = 0; i < forest.size(); i++) {
            linkedList.addLast(forest.getTree(i).getRoot());
        }
        currentTreeNode = forest.getTree(0).getRoot();

    }

    @Override
    public String viewCurrent() {
        return code[currentTreeNode.getContext().start.getLine() - 1].trim();
    }

    @Override
    public String stepIn() {
        if(currentTreeNode.getChildCount() == 0) {
            return code[currentTreeNode.getContext().start.getLine() - 1].trim();
        }
        int index = linkedList.indexOf(currentTreeNode);
        for(int i = 0; i < currentTreeNode.getChildCount(); i++) {
            linkedList.add(index + i + 1, currentTreeNode.getChild(i));
        }
        currentTreeNode = currentTreeNode.getChild(0);
        return code[currentTreeNode.getContext().start.getLine() - 1].trim();
    }

    @Override
    public String stepOut() {
        if(currentTreeNode.getParent() == null) {
            return code[currentTreeNode.getContext().start.getLine() - 1].trim();
        }
        currentTreeNode = currentTreeNode.getParent();
        for(int i = 0; i < currentTreeNode.getChildCount(); i++) {
            linkedList.remove(currentTreeNode.getChild(i));
        }
        return code[currentTreeNode.getContext().start.getLine() - 1].trim();
    }

    @Override
    public String stepForward() {
        int index = linkedList.indexOf(currentTreeNode);
        if(index + 1 < linkedList.size()) {
            currentTreeNode = linkedList.get(index + 1);
        }
        return code[currentTreeNode.getContext().start.getLine() - 1].trim();
    }

    @Override
    public String stepBackward() {
        int index = linkedList.indexOf(currentTreeNode);
        if(index - 1 >= 0) {
            currentTreeNode = linkedList.get(index - 1);
        }
        return code[currentTreeNode.getContext().start.getLine() - 1].trim();
    }
}
