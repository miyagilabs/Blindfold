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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Görkem Mülayim
 */
public class BaseWalkerTest {
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

    public BaseWalkerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of next method, of class BaseWalker.
     */
    @Test
    public void testNextTree() {
        Generator generator = new BaseGenerator();
        Forest forest = generator.generate(CODE);
        Walker walker = new BaseWalker(forest);
        String expResult = "publicclassfirstClass";
        String result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "privateclasssecondClassextendsfirstClass";
        result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "publicclassfirstClass";
        result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.", expResult,
                result);
    }

    /**
     * Test of previous method, of class BaseWalker.
     */
    @Test
    public void testPreviousTree() {
        Generator generator = new BaseGenerator();
        Forest forest = generator.generate(CODE);
        Walker walker = new BaseWalker(forest);
        String expResult = "privateclasssecondClassextendsfirstClass";
        String result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "publicclassfirstClass";
        result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "privateclasssecondClassextendsfirstClass";
        result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);
    }

    /**
     * Test of next and previous method, of class BaseWalker at the same time.
     */
    @Test
    public void testNextAndPreviousTree() {
        Generator generator = new BaseGenerator();
        Forest forest = generator.generate(CODE);
        Walker walker = new BaseWalker(forest);
        String expResult = "publicclassfirstClass";
        walker.previous();
        String result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "privateclasssecondClassextendsfirstClass";
        result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "privateclasssecondClassextendsfirstClass";
        walker.next();
        result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);
    }

    /**
     * Test of stepForward method, of class BaseWalker.
     */
    @Test
    public void testStepForward() {
    }

    /**
     * Test of stepBackward method, of class BaseWalker.
     */
    @Test
    public void testStepBackward() {
    }
}
