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

import com.miyagilabs.blindfold.antlr4.Java8BaseListener;
import com.miyagilabs.blindfold.antlr4.Java8Parser;
import com.miyagilabs.blindfold.util.Forest;
import com.miyagilabs.blindfold.util.Tree;
import com.miyagilabs.blindfold.util.TreeNode;
import java.util.Stack;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeViewListener extends Java8BaseListener {
    private final Forest forest;
    private TreeNode currentTreeNode;
    private final Stack<TreeNode> parentStack;

    public TreeViewListener(Forest forest) {
        this.forest = forest;
        parentStack = new Stack<>();
    }

    @Override
    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        if(currentTreeNode == null) {
            currentTreeNode = new TreeNode(ctx.normalClassDefinition());
            Tree tree = new Tree(currentTreeNode);
            forest.addTree(tree);
            parentStack.push(null);
        }
        else {
            parentStack.push(currentTreeNode);
            currentTreeNode = new TreeNode(ctx.normalClassDefinition());
            parentStack.peek().addChild(currentTreeNode);
        }
    }

    @Override
    public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        currentTreeNode = parentStack.pop();
    }

    @Override
    public void enterNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {
        if(currentTreeNode == null) {
            currentTreeNode = new TreeNode(ctx.normalInterfaceDefinition());
            Tree tree = new Tree(currentTreeNode);
            forest.addTree(tree);
            parentStack.push(null);
        }
        else {
            parentStack.push(currentTreeNode);
            currentTreeNode = new TreeNode(ctx.normalInterfaceDefinition());
            parentStack.peek().addChild(currentTreeNode);
        }
    }

    @Override
    public void exitNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {
        currentTreeNode = parentStack.pop();
    }

    @Override
    public void enterEnumDeclaration(Java8Parser.EnumDeclarationContext ctx) {
        if(currentTreeNode == null) {
            currentTreeNode = new TreeNode(ctx.enumDefinition());
            Tree tree = new Tree(currentTreeNode);
            forest.addTree(tree);
            parentStack.push(null);
        }
        else {
            parentStack.push(currentTreeNode);
            currentTreeNode = new TreeNode(ctx.enumDefinition());
            parentStack.peek().addChild(currentTreeNode);
        }
    }

    @Override
    public void exitEnumDeclaration(Java8Parser.EnumDeclarationContext ctx) {
        currentTreeNode = parentStack.pop();
    }

    @Override
    public void enterConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {
        parentStack.push(currentTreeNode);
        currentTreeNode = new TreeNode(ctx.constructorDefinition());
        parentStack.peek().addChild(currentTreeNode);
    }

    @Override
    public void exitConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {
        currentTreeNode = parentStack.pop();
    }

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        parentStack.push(currentTreeNode);
        currentTreeNode = new TreeNode(ctx.methodDefinition());
        parentStack.peek().addChild(currentTreeNode);
    }

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        currentTreeNode = parentStack.pop();
    }

    @Override
    public void enterIfDefinition(Java8Parser.IfDefinitionContext ctx) {
        parentStack.push(currentTreeNode);
        currentTreeNode = new TreeNode(ctx);
        parentStack.peek().addChild(currentTreeNode);
    }

    @Override
    public void exitIfDefinition(Java8Parser.IfDefinitionContext ctx) {
        currentTreeNode = parentStack.pop();
    }

    @Override
    public void enterElseDefinition(Java8Parser.ElseDefinitionContext ctx) {
        parentStack.push(currentTreeNode);
        currentTreeNode = new TreeNode(ctx);
        parentStack.peek().addChild(currentTreeNode);
    }

    @Override
    public void exitElseDefinition(Java8Parser.ElseDefinitionContext ctx) {
        currentTreeNode = parentStack.pop();
    }
}
