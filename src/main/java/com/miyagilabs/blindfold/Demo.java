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
package com.miyagilabs.blindfold;

import com.miyagilabs.blindfold.structure.BaseGenerator;
import com.miyagilabs.blindfold.structure.Generator;
import com.miyagilabs.blindfold.util.Forest;
import com.miyagilabs.blindfold.util.Node;
import com.miyagilabs.blindfold.util.Tree;
import java.util.ListIterator;

/**
 *
 * @author Görkem Mülayim
 */
public class Demo {
    public static void main(String[] args) {
        String code = "public class Demo {" // Node 1
                + "        public Demo() { }" // Node 2
                + "        public void method() {" // Node 3
                + "            if(1 == 1) { } " // Node 4
                + "        }"
                + "    }"
                + "public interface Test { }"; // Node 5
        Generator generator = new BaseGenerator();
        Forest forest = generator.generate(code);

        System.out.println("-Iterator-");
        forest.forEach(tree -> {
            tree.forEach(node -> {
                System.out.println(node.getContext().getText());
            });
        });

        System.out.println();

        System.out.println("-List Iterator-");
        ListIterator<Tree> forestListIterator = forest.listIterator();
        while(forestListIterator.hasNext()) {
            Tree tree = forestListIterator.next();
            ListIterator<Node> treeListIterator = tree.listIterator();
            while(treeListIterator.hasNext()) {
                System.out.println(treeListIterator.next().getContext().getText());
            }
        }
    }
}
