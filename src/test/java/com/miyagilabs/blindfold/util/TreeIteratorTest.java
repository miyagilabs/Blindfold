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
import static org.junit.Assert.fail;

import com.miyagilabs.blindfold.structure.TreeViewGenerator;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeIteratorTest {
    private static final String SAMPLE_CLASS_PATH = "sample/SampleClass.java";
    private final Tree tree1;
    private final Tree tree2;

    public TreeIteratorTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(SAMPLE_CLASS_PATH).getFile());
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String code = new String(encoded, Charset.defaultCharset());
        Forest forest = TreeViewGenerator.generate(code);
        tree1 = forest.getTree(0);
        tree2 = forest.getTree(1);
    }

    /**
     * Test of hasNext method, of class TreeIterator.
     */
    @Test
    public void testHasNext() {
        Iterator<TreeNode> treeIterator1 = new TreeIterator(tree1);
        boolean expResult = false;
        for(int i = 0; i < 17; i++) {
            treeIterator1.next();
        }
        boolean result = treeIterator1.hasNext();
        assertEquals("hasNext method, of class TreeIterator's expected result is wrong.", expResult,
                result);

        Iterator<TreeNode> treeIterator2 = new TreeIterator(tree2);
        expResult = true;
        result = treeIterator2.hasNext();
        assertEquals("hasNext method, of class TreeIterator's expected result is wrong.", expResult,
                result);
    }

    /**
     * Test of next method, of class TreeIterator.
     */
    @Test
    public void testNext() {
        Iterator<TreeNode> treeIterator1 = new TreeIterator(tree1);
        String expResult = "privatestaticclassSampleStaticInnerClassextendsSampleClass";
        String result = null;
        for(int i = 0; i < 15; i++) {
            result = treeIterator1.next().getContext().getText();
        }
        assertEquals("next method, of class TreeIterator's expected result is worng.", expResult,
                result);
    }

    /**
     * Test of next method, of class TreeIterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextNoSuchElementException() {
        Iterator<TreeNode> treeIterator2 = new TreeIterator(tree2);
        treeIterator2.next();
        treeIterator2.next();
        fail("next method, of class TreeIterator should throw NoSuchElementException.");
    }
}
