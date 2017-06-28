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
            + "    }\n"
            + "}\n"
            + "\n"
            + "private class secondClass extends firstClass {}"; // Tree 2, Node 1

    /**
     * Test of next method, of class BaseWalker.
     */
    @Test
    public void testNext() {
        Walker walker = new BaseWalker(CODE);
        String expResult = "public class firstClass {";
        String result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "private class secondClass extends firstClass {}";
        result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "public class firstClass {";
        result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.", expResult,
                result);
    }

    /**
     * Test of previous method, of class BaseWalker.
     */
    @Test
    public void testPrevious() {
        Walker walker = new BaseWalker(CODE);
        String expResult = "private class secondClass extends firstClass {}";
        String result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "public class firstClass {";
        result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "private class secondClass extends firstClass {}";
        result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);
    }

    /**
     * Test of next and previous methods, of class BaseWalker at the same time.
     */
    @Test
    public void testNextAndPrevious() {
        Walker walker = new BaseWalker(CODE);
        String expResult = "public class firstClass {";
        walker.previous();
        String result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "private class secondClass extends firstClass {}";
        result = walker.previous();
        assertEquals("previous method, of class BaseWalker's expected result is wrong.", expResult,
                result);

        expResult = "private class secondClass extends firstClass {}";
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
        Walker walker = new BaseWalker(CODE);
        String expResult = "private int x;";
        String result = walker.stepForward();
        assertEquals("stepForward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "public Test() {";
        result = walker.stepForward();
        assertEquals("stepForward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "x = 5;";
        result = walker.stepForward();
        assertEquals("stepForward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "}";
        for(int i = 0; i < 16; i++) {
            result = walker.stepForward();
        }
        assertEquals("stepForward method, of class BaseWalker's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of stepBackward method, of class BaseWalker.
     */
    @Test
    public void testStepBackward() {
        Walker walker = new BaseWalker(CODE);
        String expResult = "public class firstClass {";
        String result = walker.stepBackward();
        assertEquals("stepBackward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "";
        walker.next();
        result = walker.stepBackward();
        assertEquals("stepBackward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "}";
        walker.stepBackward();
        result = walker.stepBackward();
        assertEquals("stepBackward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "public class firstClass {";
        for(int i = 0; i < 18; i++) {
            result = walker.stepBackward();
        }
        assertEquals("stepBackward method, of class BaseWalker's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of stepForward and stepBackward methods, of class BaseWalker at the same time.
     */
    @Test
    public void testStepForwardAndStepBackward() {
        String expResult = "public class firstClass {";
        Walker walker = new BaseWalker(CODE);
        walker.stepForward();
        walker.stepForward();
        walker.stepBackward();
        String result = walker.stepBackward();
        assertEquals("stepBackward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "private int x;";
        walker.stepBackward();
        result = walker.stepForward();
        assertEquals("stepForward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "private int x;";
        walker.stepBackward();
        result = walker.stepForward();
        assertEquals("stepForward method, of class BaseWalker's expected result is wrong.",
                expResult, result);

        expResult = "}";
        for(int i = 0; i < 19; i++) {
            walker.stepForward();
        }
        result = walker.stepBackward();
        assertEquals("stepBackward method, of class BaseWalker's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of next and stepForward methods, of class BaseWalker at the same time.
     */
    @Test
    public void testNextAndStepForward() {
        String expResult = "public void method() {";
        Walker walker = new BaseWalker(CODE);
        walker.stepForward();
        walker.stepForward();
        String result = walker.next();
        assertEquals("next method, of class BaseWalker's expected result is wrong.",
                expResult, result);
    }
}
