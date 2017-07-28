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
public class SampleClass { // Tree 1, Node 1
    private int instanceVariable;

    private SampleClass() { // Node 2
        instanceVariable = 0;
        if(0 == 0) { // Node 3
        }
        else { // Node 4
        }
        if(1 == 1) { // Node 5
        }
    }

    private int getInstanceVariable() { // Node 6
        method();
        return instanceVariable;
    }

    private void setInstanceVariable(int number) { // Node 7
        instanceVariable = number;
    }

    private void method() { // Node 8
        setInstanceVariable(getInstanceVariable());
        if(0 == 0) { // Node 9
            if(1 == 1) { // Node 10
            }
            else { // Node 11
            }
        }
        else if(4 == 4) { // Node 12, 13
        }
        else { // Node 14
        }
    }

    private static class SampleStaticInnerClass extends SampleClass { // Node 15
    }
}

class SampleSecondClass { // Tree 2, Node 1
}
