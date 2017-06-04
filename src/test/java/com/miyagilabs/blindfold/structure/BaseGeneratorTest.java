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

import static org.junit.Assert.assertEquals;

import com.miyagilabs.blindfold.util.Forest;
import com.miyagilabs.blindfold.util.Tree;
import org.junit.Test;

/**
 *
 * @author Görkem Mülayim
 */
public class BaseGeneratorTest {
    private final static String CODE
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

    /**
     * Test of getForest method, of class BaseGenerator.
     */
    @Test
    public void testGetForest() {
        Generator generator = new BaseGenerator();
        Forest expResult = generator.generate(CODE);
        Forest result = generator.getForest();
        assertEquals(
                "getForest method, of class BaseGenerator's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of generate method, of class BaseGenerator.
     */
    @Test
    public void testGenerate() {
        Generator generator = new BaseGenerator();
        Forest result = generator.generate(CODE);
        int expSize = 2;
        assertEquals("generate method, of class BaseGenerator's expected result is wrong.",
                expSize, result.size());

        Tree tree1 = result.getTree(0);
        expSize = 14;
        assertEquals("generate method, of class BaseGenerator's expected result is wrong.",
                expSize, tree1.size());

        Tree tree2 = result.getTree(1);
        expSize = 1;
        assertEquals("generate method, of class BaseGenerator's expected result is wrong.",
                expSize, tree2.size());
    }
}
