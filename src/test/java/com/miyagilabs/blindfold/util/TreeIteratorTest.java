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
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeIteratorTest {
    private final Tree tree1;
    private final Tree tree2;

    public TreeIteratorTest() {
        String code
                = "public class firstClass {\n" // Tree 1, Node 1
                + "    private int x;\n"
                + "    public Test() {\n" // Node 2
                + "        x = 5;\n"
                + "        if(0 == 0) {}\n" // Node 3
                + "        else {}\n" // Node 4
                + "        if(1 == 1) {}\n" // Node 5
                + "    }\n"
                + "    public void method() {\n" // Node 6
                + "        if(2 == 2) {\n" // Node 7
                + "            if(3 == 3) {}\n" // Node 8
                + "            else {}\n" // Node 9
                + "        }\n"
                + "        else if(4 == 4) {}\n" // Node 10, 11
                + "        else {}\n" // Node 12
                + "    }\n"
                + "    private class innerClass {\n" // Node 13
                + "        private void method() {}\n" // Node 14
                + "    }"
                + "}\n"
                + ""
                + "private class secondClass extends firstClass {}"; // Tree 2, Node 1
        Generator generator = new BaseGenerator();
        Forest forest = generator.generate(code);
        tree1 = forest.getTree(0);
        tree2 = forest.getTree(1);
    }

    /**
     * Test of hasNext method, of class TreeIterator.
     */
    @Test
    public void testHasNext() {
        Iterator<Node> treeIterator1 = new TreeIterator(tree1);
        boolean expResult = false;
        for(int i = 0; i < 14; i++) {
            treeIterator1.next();
        }
        boolean result = treeIterator1.hasNext();
        assertEquals("hasNext method, of class TreeIterator's expected result is wrong.", expResult,
                result);

        Iterator<Node> treeIterator2 = new TreeIterator(tree2);
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
        Iterator<Node> treeIterator1 = new TreeIterator(tree1);
        String expResult = "privatevoidmethod()";
        String result = null;
        for(int i = 0; i < 14; i++) {
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
        Iterator<Node> treeIterator2 = new TreeIterator(tree2);
        treeIterator2.next();
        treeIterator2.next();
        fail("next method, of class TreeIterator should throw NoSuchElementException.");
    }
}
