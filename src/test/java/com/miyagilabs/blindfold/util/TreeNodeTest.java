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

import com.miyagilabs.blindfold.structure.TreeViewGenerator;
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
public class TreeNodeTest {
    private static final String SAMPLE_CLASS_PATH = "sample/SampleClass.java";

    /**
     * Test of equals method, of class TreeNode.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testEquals() throws IOException {
        Object object = null;
        TreeNode instance = new TreeNode(new ParserRuleContext());
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals("equals method, of class TreeNode's expected result is wrong.", expResult,
                result);

        object = new Object();
        expResult = false;
        result = instance.equals(object);
        assertEquals("equals method, of class TreeNode's expected result is wrong.", expResult,
                result);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(SAMPLE_CLASS_PATH).getFile());
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String code = new String(encoded, Charset.defaultCharset());

        Forest forest1 = TreeViewGenerator.generate(code);
        Forest forest2 = TreeViewGenerator.generate(code);

        expResult = true;
        object = forest1.getTree(0).getRoot();
        instance = forest2.getTree(0).getRoot();
        result = instance.equals(object);
        assertEquals("equals method, of class TreeNode's expected result is wrong.", expResult,
                result);
    }
}
