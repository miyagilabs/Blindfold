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

import com.miyagilabs.blindfold.antlr4.Java8Lexer;
import com.miyagilabs.blindfold.antlr4.Java8Parser;
import com.miyagilabs.blindfold.util.Forest;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 *
 * @author Görkem Mülayim
 */
public final class TreeViewGenerator {
    private TreeViewGenerator() {
    }

    public static Forest generate(String code) throws SyntaxError {
        Java8Lexer lexer = new Java8Lexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);
        Java8Parser.CompilationUnitContext ast = parser.compilationUnit();
        if(parser.getNumberOfSyntaxErrors() > 0) {
            throw new SyntaxError();
        }
        Forest forest = new Forest();
        TreeViewListener treeViewListener = new TreeViewListener(forest);
        ParseTreeWalker.DEFAULT.walk(treeViewListener, ast);
        return forest;
    }
}
