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

import static org.junit.Assert.assertEquals;

import com.miyagilabs.blindfold.structure.BaseGenerator;
import com.miyagilabs.blindfold.structure.Generator;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeTest {
    private static final String SAMPLE_CLASS_PATH = "sample/SampleClass.java";

    /**
     * Test of equals method, of class Tree.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testEquals() throws IOException {
        Object object = null;
        Tree instance = new Tree(new Node(new ParserRuleContext()));
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals("equals method, of class Tree's expected result is wrong.", expResult, result);

        object = new Object();
        expResult = false;
        result = instance.equals(object);
        assertEquals("equals method, of class Tree's expected result is wrong.", expResult, result);

        object = instance;
        expResult = true;
        result = instance.equals(object);
        assertEquals("equals method, of class Tree's expected result is wrong.", expResult, result);

        ClassLoader classLoader = TreeListIteratorTest.class.getClassLoader();
        File file = new File(classLoader.getResource(SAMPLE_CLASS_PATH).getFile());
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String code = new String(encoded, Charset.defaultCharset());
        Generator generator = new BaseGenerator();
        Forest forest = generator.generate(code);

        Tree tree1 = forest.getTree(0);
        Tree tree2 = forest.getTree(1);
        result = tree1.equals(tree2);
        expResult = false;
        assertEquals("equals method, of class Tree's expected result is wrong.", expResult, result);

        tree1 = generator.generate(code).getTree(0);
        tree2 = generator.generate(code).getTree(0);
        result = tree1.equals(tree2);
        expResult = true;
        assertEquals("equals method, of class Tree's expected result is wrong.", expResult, result);
    }
}
