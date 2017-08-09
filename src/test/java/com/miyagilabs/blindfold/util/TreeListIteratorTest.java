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

import com.miyagilabs.blindfold.structure.BaseGenerator;
import com.miyagilabs.blindfold.structure.Generator;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeListIteratorTest {
    private static final String SAMPLE_CLASS_PATH = "sample/SampleClass.java";
    private final Tree tree1;
    private final Tree tree2;

    public TreeListIteratorTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(SAMPLE_CLASS_PATH).getFile());
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String code = new String(encoded, Charset.defaultCharset());
        Generator generator = new BaseGenerator();
        Forest forest = generator.generate(code);
        tree1 = forest.getTree(0);
        tree2 = forest.getTree(1);
    }

    /**
     * Test of hasNext method, of class TreeListIterator.
     */
    @Test
    public void testHasNext() {
        ListIterator<TreeNode> treeListIterator1 = new TreeListIterator(tree1);
        boolean expResult = false;
        for(int i = 0; i < 15; i++) {
            treeListIterator1.next();
        }
        boolean result = treeListIterator1.hasNext();
        assertEquals(expResult, result);

        ListIterator<TreeNode> treeListIterator2 = new TreeListIterator(tree2);
        expResult = true;
        result = treeListIterator2.hasNext();
        assertEquals("hasNext method, of class TreeListIterator's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of next method, of class TreeListIterator.
     */
    @Test
    public void testNext() {
        ListIterator<TreeNode> treeListIterator1 = new TreeListIterator(tree1);
        String expResult = "privatestaticclassSampleStaticInnerClassextendsSampleClass";
        String result = null;
        for(int i = 0; i < 15; i++) {
            result = treeListIterator1.next().getContext().getText();
        }
        assertEquals("next method, of class TreeListIterator's expected result is worng.",
                expResult, result);
    }

    /**
     * Test of next method, of class TreeListIterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextNoSuchElementException() {
        ListIterator<TreeNode> treeListIterator2 = new TreeListIterator(tree2);
        treeListIterator2.next();
        treeListIterator2.next();
        fail("next method, of class TreeListIterator should throw NoSuchElementException.");
    }

    /**
     * Test of hasPrevious method, of class TreeListIterator.
     */
    @Test
    public void testHasPrevious() {
        ListIterator<TreeNode> treeListIterator1 = new TreeListIterator(tree1);
        boolean expResult = true;
        for(int i = 0; i < 14; i++) {
            treeListIterator1.next();
        }
        boolean result = treeListIterator1.hasPrevious();
        assertEquals("hasPrevious method, of class TreeListIterator's expected result is wrong.",
                expResult, result);

        ListIterator<TreeNode> treeListIterator2 = new TreeListIterator(tree2);
        expResult = false;
        result = treeListIterator2.hasPrevious();
        assertEquals("hasPrevious method, of class TreeListIterator's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of previous method, of class TreeListIterator.
     */
    @Test
    public void testPrevious() {
        ListIterator<TreeNode> treeListIterator1 = new TreeListIterator(tree1);
        String expResult = "publicclassSampleClass";
        String result = null;
        for(int i = 0; i < 15; i++) {
            treeListIterator1.next();
        }
        for(int i = 0; i < 15; i++) {
            result = treeListIterator1.previous().getContext().getText();
        }
        assertEquals("previous method, of class TreeListIterator's expected result is worng.",
                expResult, result);
    }

    /**
     * Test of previous method, of class TreeListIterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void testPreviousNoSuchElementException() {
        ListIterator<TreeNode> treeListIterator2 = new TreeListIterator(tree2);
        treeListIterator2.previous();
        fail("previous method, of class TreeListIterator should throw NoSuchElementException.");
    }

    /**
     * Test of nextIndex method, of class TreeListIterator.
     */
    @Test
    public void testNextIndex() {
        ListIterator<TreeNode> treeListIterator1 = new TreeListIterator(tree1);
        int expResult = 0;
        int result = treeListIterator1.nextIndex();
        assertEquals("nextIndex method, of class TreeListIterator's expected result is worng.",
                expResult, result);

        for(int i = 0; i < 14; i++) {
            treeListIterator1.next();
        }
        expResult = 14; // The size of the list.
        result = treeListIterator1.nextIndex();
        assertEquals("nextIndex method, of class TreeListIterator's expected result is worng.",
                expResult, result);
    }

    /**
     * Test of previousIndex method, of class TreeListIterator.
     */
    @Test
    public void testPreviousIndex() {
        ListIterator<TreeNode> treeListIterator1 = new TreeListIterator(tree1);
        int expResult = -1;
        int result = treeListIterator1.previousIndex();
        assertEquals("previousIndex method, of class TreeListIterator's expected result is worng.",
                expResult, result);

        for(int i = 0; i < 14; i++) {
            treeListIterator1.next();
        }
        expResult = 13;
        result = treeListIterator1.previousIndex();
        assertEquals("previousIndex method, of class TreeListIterator's expected result is worng.",
                expResult, result);
    }

    /**
     * Test of remove method, of class TreeListIterator.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        ListIterator<TreeNode> treeListIterator2 = new TreeListIterator(tree2);
        treeListIterator2.next();
        treeListIterator2.remove();
    }

    /**
     * Test of set method, of class TreeListIterator.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testSet() {
        ListIterator<TreeNode> treeListIterator2 = new TreeListIterator(tree2);
        treeListIterator2.next();
        treeListIterator2.set(null);
    }

    /**
     * Test of add method, of class TreeListIterator.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        ListIterator<TreeNode> treeListIterator2 = new TreeListIterator(tree2);
        treeListIterator2.next();
        treeListIterator2.add(null);
    }
}
