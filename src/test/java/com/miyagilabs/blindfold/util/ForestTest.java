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
public class ForestTest {
    private static final String SAMPLE_CLASS_PATH = "sample/SampleClass.java";

    /**
     * Test of equals method, of class Forest.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testEquals() throws IOException {
        Object object = new Forest();
        Forest instance = (Forest) object;
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals("equals method, of class Forest's expected result is wrong.", expResult,
                result);

        object = null;
        instance = new Forest();
        expResult = false;
        result = instance.equals(object);
        assertEquals("equals method, of class Forest's expected result is wrong.", expResult,
                result);

        object = new Object();
        instance = new Forest();
        expResult = false;
        result = instance.equals(object);
        assertEquals("equals method, of class Forest's expected result is wrong.", expResult,
                result);

        Forest obj = new Forest();
        obj.addTree(new Tree(new Node(new ParserRuleContext())));
        instance = new Forest();
        expResult = false;
        result = instance.equals(obj);
        assertEquals("equals method, of class Forest's expected result is wrong.", expResult,
                result);

        ClassLoader classLoader = TreeListIteratorTest.class.getClassLoader();
        File file = new File(classLoader.getResource(SAMPLE_CLASS_PATH).getFile());
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String code = new String(encoded, Charset.defaultCharset());
        Generator generator = new BaseGenerator();

        Forest forest1 = generator.generate(code);
        Forest forest2 = generator.generate(code);
        expResult = true;
        result = forest1.equals(forest2);
        assertEquals("equals method, of class Forest's expected result is wrong.", expResult,
                result);
    }
}
