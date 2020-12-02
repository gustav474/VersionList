package com.gustav474.versionList;

import java.util.*;

public class VersionList implements java.util.List{

    private final int DEFAULT_LENGTH = 2;

    private Element[] elementData = new Element[DEFAULT_LENGTH];

    private int size;

    private ArrayList result_list = new ArrayList();


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return elementData;
    }

    @Override
    public boolean add(Object o) {
        if (elementData.length == size) grow();
        if (o instanceof Element) {
            elementData[size++] = (Element) o;
            return true;
        }

        elementData[size++] = new Element((ArrayList) o);
        return true;
    }

    /**
     * Increase the length of the array if there is not enough space to store the element
     */
    private void grow() {
        int newLength =  elementData.length + (elementData.length >> 1);
        elementData = Arrays.copyOf(elementData, newLength);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() { }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Element get(int index) {
        return elementData[index];
    }


    /**
     * Get the state of a list of items by int index
     *
     * @param index
     * @return List from begin to index
     */
    public ArrayList getList(int index) {
        result_list.clear();

        while (index >= 0) {
            Element res = elementData[index];
            result_list.add(res.getList());
//            System.out.println(res);
            get(index);
            index--;
        }

        Collections.reverse(result_list);
        return result_list;
    }

    /**
     * Get the state of a list of items by string time in "HH-m" format
     * @param time
     * @return List from begin to index if we find state by time in List, empty ArrayList if not
     */
    public ArrayList getList(String time) {
        result_list.clear();

        for (int i = 0; i <= elementData.length-1; i++) {
            if (elementData[i].getTime().equals(time)) {
                return getList(i);
            }
        }

        System.out.println("Something went wrong, check time and try again");

        return new ArrayList();
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Element remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

}
