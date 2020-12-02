package com.gustav474.versionList;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class VersionListTest {

    @Test
    public void getListShouldReturnSublistByTime() {

        GregorianCalendar time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 12);
        time1.set(Calendar.MINUTE, 00);

        GregorianCalendar time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 12);
        time2.set(Calendar.MINUTE, 01);

        GregorianCalendar time3 = new GregorianCalendar();
        time3.set(Calendar.HOUR_OF_DAY, 12);
        time3.set(Calendar.MINUTE, 03);

        ArrayList<Integer> testList1 = new ArrayList();
        testList1.add(1);
        testList1.add(2);
        testList1.add(3);

        ArrayList<Integer> testList2 = new ArrayList();
        testList2.add(10);
        testList2.add(20);

        ArrayList<Integer> testList3= new ArrayList();
        testList3.add(100);
        testList3.add(200);

        Element element1 = new Element(time1, testList1);
        Element element2 = new Element(time2, testList2);
        Element element3 = new Element(time3, testList3);

        VersionList actual = new VersionList();

        actual.add(element1);
        actual.add(element2);
        actual.add(element3);

        ArrayList expected = new ArrayList();
        expected.add(testList1);
        expected.add(testList2);

        Assert.assertEquals(expected, actual.getList("12-1"));
    }

    @Test
    public void listShouldBeIncreasedIfCurrentSizeIsNotEnought() {
        GregorianCalendar time1 = new GregorianCalendar();
        time1.set(Calendar.HOUR_OF_DAY, 12);
        time1.set(Calendar.MINUTE, 00);

        GregorianCalendar time2 = new GregorianCalendar();
        time2.set(Calendar.HOUR_OF_DAY, 12);
        time2.set(Calendar.MINUTE, 01);

        GregorianCalendar time3 = new GregorianCalendar();
        time3.set(Calendar.HOUR_OF_DAY, 12);
        time3.set(Calendar.MINUTE, 03);

        ArrayList<Integer> testList1 = new ArrayList();
        testList1.add(1);
        testList1.add(2);
        testList1.add(3);

        ArrayList<Integer> testList2 = new ArrayList();
        testList2.add(10);
        testList2.add(20);

        ArrayList<Integer> testList3= new ArrayList();
        testList3.add(100);
        testList3.add(200);

        VersionList actual = new VersionList();

        Element element1 = new Element(time1, testList1);
        Element element2 = new Element(time2, testList2);
        Element element3 = new Element(time3, testList3);

        actual.add(element1);
        actual.add(element2);
        actual.add(element3);

        Assert.assertEquals(3, actual.toArray().length);
    }
}