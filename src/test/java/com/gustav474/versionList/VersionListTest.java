package com.gustav474.versionList;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.*;
import java.util.ArrayList;


public class VersionListTest {

    private LocalDateTime time1;
    private LocalDateTime time2;
    private LocalDateTime time3;
    private LocalDateTime timeInExistingRange;
    private LocalDateTime timeOutOfExistingRange;
    private ArrayList<Integer> testList1;
    private ArrayList<Integer> testList2;
    private ArrayList<Integer> testList3;
    private Element element1;
    private Element element2;
    private Element element3;
    private VersionList actual;
    private ArrayList expected;


    @Before
    public void setUp() {
        time1 = LocalDateTime.of(LocalDate.of(2020, 12, 25),
                LocalTime.of(12, 00, 00));

        time2 = LocalDateTime.of(LocalDate.of(2020, 12, 25),
                LocalTime.of(12, 01, 00));

        time3 = LocalDateTime.of(LocalDate.of(2020, 12, 25),
                LocalTime.of(12, 03, 00));

        timeInExistingRange = LocalDateTime.of(LocalDate.of(2020, 12, 25),
                LocalTime.of(12, 02, 00));

        timeOutOfExistingRange = LocalDateTime.of(LocalDate.of(2020, 12, 24),
                LocalTime.of(12, 02, 00));

        testList1 = new ArrayList();
        testList1.add(1);
        testList1.add(2);
        testList1.add(3);

        testList2 = new ArrayList();
        testList2.add(10);
        testList2.add(20);

        testList3 = new ArrayList();
        testList3.add(100);
        testList3.add(200);

        element1 = new Element(time1, testList1);
        element2 = new Element(time2, testList2);
        element3 = new Element(time3, testList3);

        actual = new VersionList();
        actual.add(element1);
        actual.add(element2);
        actual.add(element3);

        expected = new ArrayList();
        expected.add(testList1);
        expected.add(testList2);
    }

    @Test
    public void getListShouldReturnSublistByTime() {
//        Time for comparing in existing range
        Assert.assertEquals(expected, actual.getList(timeInExistingRange));
//        Time for comparing is out of existing range
        Assert.assertEquals(new ArrayList(), actual.getList(timeOutOfExistingRange));
//        Times are equals
        Assert.assertEquals(expected, actual.getList(time2));
    }

    @Test
    public void listShouldBeIncreasedIfCurrentSizeIsNotEnought() {
        Assert.assertEquals(3, actual.toArray().length);
    }

    @Test
    public void IteratorShouldReturnElementOfCollection () {
        Assert.assertEquals(element1, actual.iterator().next());
        Assert.assertEquals(true, actual.iterator().hasNext());
        Assert.assertEquals(element3, actual.remove(2));
    }
}