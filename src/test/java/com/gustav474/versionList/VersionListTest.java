package com.gustav474.versionList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
    private Element element4;
    private VersionList actual;
    private VersionList actual2;
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

        timeOutOfExistingRange = LocalDateTime.of(LocalDate.of(2020, 12, 25),
                LocalTime.of(11, 02, 00));

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
//        Times are equals
        Assert.assertEquals(expected, actual.getList(time2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getListShouldReturnIndexOutOfBoundsException() {
//        Time for comparing is out of existing range
        Assert.assertEquals(new ArrayList(), actual.getList(timeOutOfExistingRange));
    }

    @Test
    public void listShouldBeIncreasedIfCurrentSizeIsNotEnought() {
        Assert.assertEquals(3, actual.toArray().length);
    }

    @Test
    public void IteratorShouldReturnElementOfCollection () {
        Assert.assertEquals(testList1, actual.iterator().next());
        Assert.assertEquals(true, actual.iterator().hasNext());
        Assert.assertEquals(testList3, actual.remove(2));
    }

    @Test
    public void VersionListShouldTakeOneElement () {
        VersionList<Integer> actual = new VersionList();
        Integer num = new Integer(5);

        actual.add(num);

        Assert.assertEquals(num, actual.get(0));
    }

    @Test
    public void VersionListShouldTakeListOfElements () {
        VersionList actual = new VersionList();

        List list = Arrays.asList(5, 6, 7);

        actual.add(list);

        Assert.assertEquals(list, actual.get(0));
    }

    @Test
    public void VersionListShouldMakeToArrayMethod () {
        VersionList actual = new VersionList();

        List<Integer> list = Arrays.asList(5, 6, 7);
        Integer num = new Integer(10);

        actual.add(list);
        actual.add(num);

        Object[] array = new Object[2];
        array[0] = list;
        array[1] = num;

        Integer[] array2 = new Integer[1];

        Assert.assertEquals(array, actual.toArray());
        Assert.assertEquals(array2, actual.toArray(array2));
    }

    @Test
    public void VersionListShouldMakeContainsMethod () {
        VersionList<String> actual = new VersionList();

        String str = new String("HellO!");

        actual.add(str);

        Assert.assertEquals(true, actual.contains(str));
    }

    @Test
    public void VersionListShouldMakeRemoveMethod () {
        VersionList<String> actual = new VersionList();

        String str = new String("HellO!");
        String str2 = new String("HI");

        actual.add(str);
        actual.add(str2);

        Assert.assertEquals(2, actual.size());
        Assert.assertEquals(true, actual.remove(str));
        Assert.assertEquals(1, actual.size());
        Assert.assertEquals(str2, actual.remove(0));

    }

    @Test
    public void VersionListShouldMakeAddAllAndGetMethods () {
        VersionList<Integer> actual = new VersionList();
        ArrayList list = new ArrayList();
        list.add(testList1);
        list.add(testList2);

        Assert.assertEquals(true, actual.addAll(testList1));
        Assert.assertEquals(1, actual.size());
        Assert.assertEquals(true, actual.addAll(0, testList2));
        Assert.assertEquals(2, actual.size());
        Assert.assertEquals(testList2, actual.get(0));
    }

    @Test
    public void VersionListShouldMakeClearMethod () {
        VersionList<String> actual = new VersionList();

        actual.add(new String("Hello"));
        actual.add(new String ("word"));

        Assert.assertEquals(2, actual.size());
        actual.clear();
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void VersionListShouldMakeSetMethod () {
        VersionList<String> actual = new VersionList();
        actual.add(new String("Hi!"));

        Assert.assertEquals(new String("Hi!"), actual.set(0, new String("Hello!")));
        Assert.assertEquals(new String("Hello!"), actual.get(0));
    }

    @Test
    public void VersionListShouldMakeIndexOfAndLastIndexOfMethods () {
        VersionList<Integer> actual = new VersionList();

        Integer num1 = new Integer(1);
        Integer num2 = new Integer(2);
        Integer num3 = new Integer(3);
        Integer num4 = new Integer(4);

        actual.add(num1);
        actual.add(num2);
        actual.add(num3);
        actual.add(num4);

        Assert.assertEquals(1, actual.indexOf(num2));
        Assert.assertEquals(2, actual.lastIndexOf(num3));
    }

    @Test
    public void VersionListShouldMakeSublistMethod () {
        VersionList<Integer> actual = new VersionList();

        Integer num1 = new Integer(1);
        Integer num2 = new Integer(2);
        Integer num3 = new Integer(3);
        Integer num4 = new Integer(4);

        actual.add(num1);
        actual.add(num2);
        actual.add(num3);
        actual.add(num4);

        List<Integer> list = new ArrayList<>();
        list.add(num2);
        list.add(num3);

        Assert.assertEquals(4, actual.size());
        Assert.assertEquals(list, actual.subList(1, 3));
        Assert.assertEquals(2, actual.subList(1, 3).size());

    }

    @Test
    public void VersionListShouldMakeRetainAllMethod () {
        VersionList<Integer> actual = new VersionList();

        Integer num1 = new Integer(1);
        Integer num2 = new Integer(2);
        Integer num3 = new Integer(3);
        Integer num4 = new Integer(4);

        actual.add(num1);
        actual.add(num2);
        actual.add(num3);
        actual.add(num4);

        List<Integer> list = new ArrayList<>();
        list.add(num2);
        list.add(num3);

        Assert.assertEquals(true, actual.retainAll(list));
        Assert.assertEquals(list, actual.getList(actual.size() - 1));
    }

    @Test
    public void VersionListShouldMakeRemoveAllMethod () {
        VersionList<Integer> actual = new VersionList();

        Integer num1 = new Integer(1);
        Integer num2 = new Integer(2);
        Integer num3 = new Integer(3);
        Integer num4 = new Integer(4);

        actual.add(num1);
        actual.add(num2);
        actual.add(num3);
        actual.add(num4);

        List<Integer> list = new ArrayList<>();
        list.add(num2);
        list.add(num3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(num1);
        list2.add(num4);

        Assert.assertEquals(true, actual.removeAll(list));
        Assert.assertEquals(list2, actual.getList(actual.size() - 1));
    }

    @Test
    public void VersionListShouldMakeContainsAllMethod () {
        VersionList<Integer> actual = new VersionList();

        Integer num1 = new Integer(1);
        Integer num2 = new Integer(2);
        Integer num3 = new Integer(3);
        Integer num4 = new Integer(4);

        actual.add(num1);
        actual.add(num2);
        actual.add(num3);
        actual.add(num4);

        List<Integer> list = new ArrayList<>();
        list.add(num2);
        list.add(num3);

        Assert.assertEquals(true, actual.containsAll(list));
    }
}