package sample;

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
/**
 *
 * @author Görkem Mülayim
 */
public class SampleClass { // Tree 1, tree node 1
    private int instanceVariable;

    private SampleClass() { // Tree 1, tree node 2
        instanceVariable = 0;
        if(0 == 0) { // Tree 1, tree node 3
        }
        else { // Tree 1, tree node 4
        }
        if(1 == 1) { // Tree 1, tree node 5
        }
    }

    private int getInstanceVariable() { // Tree 1, tree node 6
        method();
        return instanceVariable;
    }

    private void setInstanceVariable(int number) { // Tree 1, tree node 7
        instanceVariable = number;
    }

    private void method() { // Tree 1, tree node 8
        setInstanceVariable(getInstanceVariable());
        if(0 == 0) { // Tree 1, tree node 9
            if(1 == 1) { // Tree 1, tree node 10
            }
            else { // Tree 1, tree node 11
            }
        }
        else if(4 == 4) { // Tree 1, tree node 12 and tree node 13
        }
        else { // Tree 1, tree node 14
        }
    }

    private static class SampleStaticInnerClass extends SampleClass { // Tree 1, tree node 15
    }

    private interface SampleInnerInterface { // Tree 1, tree node 16
        void method();
    }

    private enum Enum { // Tree 1, tree node 17
    }
}

class SampleSecondClass { // Tree 2, tree node 1
}

interface SampleInterface { // Tree 3, tree node 1
    void method();
}
