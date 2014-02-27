/*
 * Copyright (C) 2014 MineStar.de 
 * 
 * This file is part of MinestarLibrary.
 * 
 * MinestarLibrary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MinestarLibrary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MinestarLibrary.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.minestar.minestarlibrary.datastructure;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SortedListTest {

    @Test
    public void comparableObjectTest() {
        List<String> s = new SortedList<String>(String.class);
        s.add("das");
        s.add("pferd");
        s.add("frisst");
        s.add("keinen");
        s.add("gurkensalat");

        Assert.assertArrayEquals(new String[]{"das", "frisst", "gurkensalat", "keinen", "pferd"}, s.toArray());
    }

    @Test
    public void comparatorTest() {

        // Reverse order
        List<String> s = new SortedList<String>(Collections.<String> reverseOrder());
        s.add("das");
        s.add("pferd");
        s.add("frisst");
        s.add("keinen");
        s.add("gurkensalat");

        Assert.assertArrayEquals(new String[]{"pferd", "keinen", "gurkensalat", "frisst", "das"}, s.toArray());
    }

    @Test(expected = ClassCastException.class)
    public void invalidTypeTest() {
        List<NoComparableClass> wrongList = new SortedList<NoComparableClass>(NoComparableClass.class);
        wrongList.add(new NoComparableClass());
    }

    public static class NoComparableClass {

    }

}
