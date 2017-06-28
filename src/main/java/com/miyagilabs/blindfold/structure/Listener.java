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
import com.miyagilabs.blindfold.util.Node;
import com.miyagilabs.blindfold.util.Tree;
import java.util.Stack;

/**
 *
 * @author Görkem Mülayim
 */
public class Listener extends Java8BaseListener {
    private final Forest forest;
    private Node currentNode;
    private Stack<Node> parentStack;

    public Listener(Generator structureGenerator) {
        this.forest = structureGenerator.getForest();
        parentStack = new Stack<>();
    }

    @Override
    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        if(currentNode == null) {
            currentNode = new Node(ctx.normalClassDefinition());
            Tree tree = new Tree(currentNode);
            forest.addTree(tree);
            parentStack.push(null);
        }
        else {
            parentStack.push(currentNode);
            currentNode = new Node(ctx.normalClassDefinition());
            parentStack.peek().addChild(currentNode);
        }
    }

    @Override
    public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        currentNode = parentStack.pop();
    }

    @Override
    public void enterNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {
        if(currentNode == null) {
            currentNode = new Node(ctx.normalInterfaceDefinition());
            Tree tree = new Tree(currentNode);
            forest.addTree(tree);
            parentStack.push(null);
        }
        else {
            parentStack.push(currentNode);
            currentNode = new Node(ctx.normalInterfaceDefinition());
            parentStack.peek().addChild(currentNode);
        }
    }

    @Override
    public void exitNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {
        currentNode = parentStack.pop();
    }

    @Override
    public void enterConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {
        parentStack.push(currentNode);
        currentNode = new Node(ctx.constructorDefinition());
        parentStack.peek().addChild(currentNode);
    }

    @Override
    public void exitConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {
        currentNode = parentStack.pop();
    }

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        parentStack.push(currentNode);
        currentNode = new Node(ctx.methodDefinition());
        parentStack.peek().addChild(currentNode);
    }

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        currentNode = parentStack.pop();
    }

    @Override
    public void enterIfDefinition(Java8Parser.IfDefinitionContext ctx) {
        parentStack.push(currentNode);
        currentNode = new Node(ctx);
        parentStack.peek().addChild(currentNode);
    }

    @Override
    public void exitIfDefinition(Java8Parser.IfDefinitionContext ctx) {
        currentNode = parentStack.pop();
    }

    @Override
    public void enterElseDefinition(Java8Parser.ElseDefinitionContext ctx) {
        parentStack.push(currentNode);
        currentNode = new Node(ctx);
        parentStack.peek().addChild(currentNode);
    }

    @Override
    public void exitElseDefinition(Java8Parser.ElseDefinitionContext ctx) {
        currentNode = parentStack.pop();
    }
}
