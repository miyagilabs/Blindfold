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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Görkem Mülayim
 */
public class TreeViewTraverserTest {
    private static final String SAMPLE_CLASS_PATH = "sample/SampleClass.java";
    private TreeViewTraverser treeViewTraverser;

    public TreeViewTraverserTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(SAMPLE_CLASS_PATH).getFile());
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String code = new String(encoded, Charset.defaultCharset());
        treeViewTraverser = new TreeViewTraverser(code);
    }

    @Before
    public void setUp() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(SAMPLE_CLASS_PATH).getFile());
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String code = new String(encoded, Charset.defaultCharset());
        treeViewTraverser = new TreeViewTraverser(code);
    }

    /**
     * Test of viewCurrent method, of class TreeViewTraverser.
     */
    @Test(expected = SyntaxError.class)
    public void testSyntaxError() {
        new TreeViewTraverser("INTENTIONAL_SYNTAX_ERROR.");
    }

    /**
     * Test of viewCurrent method, of class TreeViewTraverser.
     */
    @Test

    public void testViewCurrent() {
        String expResult = "public class SampleClass { // Tree 1, tree node 1";
        String result = treeViewTraverser.viewCurrent();
        assertEquals("viewCurrent method, of class TreeViewTraverser's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of stepIn method, of class TreeViewTraverser.
     */
    @Test
    public void testStepIn() {
        String expResult = "private SampleClass() { // Tree 1, tree node 2";
        String result = treeViewTraverser.stepIn();
        assertEquals("stepIn method, of class TreeViewTraverser's expected result is wrong.",
                expResult, result);

        expResult = "if(0 == 0) { // Tree 1, tree node 3";
        result = treeViewTraverser.stepIn();
        assertEquals("stepIn method, of class TreeViewTraverser's expected result is wrong.",
                expResult, result);

        expResult = "if(0 == 0) { // Tree 1, tree node 3";
        result = treeViewTraverser.stepIn();
        assertEquals("stepIn method, of class TreeViewTraverser's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of stepOut method, of class TreeViewTraverser.
     */
    @Test
    public void testStepOut() {
        String expResult = "public class SampleClass { // Tree 1, tree node 1";
        String result = treeViewTraverser.stepOut();
        assertEquals("stepOut method, of class TreeViewTraverser's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of stepForward method, of class TreeViewTraverser.
     */
    @Test
    public void testStepForward() {
        String expResult = "class SampleSecondClass { // Tree 2, tree node 1";
        String result = treeViewTraverser.stepForward();
        assertEquals("stepForward method, of class TreeViewTraverser's expected result is wrong.",
                expResult, result);

        expResult = "interface SampleInterface { // Tree 3, tree node 1";

        treeViewTraverser.stepForward(); // interface SampleInterface { // Tree 3, tree node 1

        // Nothing to traverse.
        treeViewTraverser.stepForward(); // interface SampleInterface { // Tree 3, tree node 1
        treeViewTraverser.stepForward(); // interface SampleInterface { // Tree 3, tree node 1
        treeViewTraverser.stepForward(); // interface SampleInterface { // Tree 3, tree node 1

        result = treeViewTraverser.stepForward();
        assertEquals("stepForward method, of class TreeViewTraverser's expected result is wrong.",
                expResult, result);
    }

    /**
     * Test of stepBackward method, of class TreeViewTraverser.
     */
    @Test
    public void testStepBackward() {
        String expResult = "public class SampleClass { // Tree 1, tree node 1";
        String result = treeViewTraverser.stepBackward();
        assertEquals("stepBackward method, of class TreeViewTraverser's expected result is wrong.",
                expResult, result);
    }

    /**
     * This test method combines calls to mimic a real world scenario.
     */
    @Test
    public void testRealWorldScenario1() {
        String expResult = "private SampleClass() { // Tree 1, tree node 2";
        // Move to tree 1, tree node 3.
        treeViewTraverser.stepIn();
        treeViewTraverser.stepIn();
        treeViewTraverser.stepIn();
        // Move back to tree 1, tree node 2.
        String result = treeViewTraverser.stepOut();
        assertEquals("Real world scenario result has faild for TreeViewTraverser.",
                expResult, result);
    }

    /**
     * This test method combines calls to mimic a real world scenario.
     */
    @Test
    public void testRealWorldScenario2() {
        String expResult = "interface SampleInterface { // Tree 3, tree node 1";
        // Move to tree 3, tree node 1.
        treeViewTraverser.stepForward();
        treeViewTraverser.stepForward();
        treeViewTraverser.stepForward();
        treeViewTraverser.stepBackward();
        treeViewTraverser.stepForward();
        String result = treeViewTraverser.stepForward();
        assertEquals("Real world scenario result has faild for TreeViewTraverser.",
                expResult, result);
    }
}
