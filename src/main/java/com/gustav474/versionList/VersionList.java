package com.gustav474.versionList;

import java.time.LocalDateTime;
import java.util.*;

public class VersionList<T> implements java.util.List<T>{

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
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        Itr () {}

        @Override
        public boolean hasNext() {
//            return cursor != size;
            return cursor < size;
        }

        @Override
        public T next() {
            Element nextElement = elementData[cursor];
            cursor++;
            lastRet++;
            return (T) nextElement.getElement();
        }

        @Override
        public void remove() {
            VersionList.this.remove(lastRet);
        }

    }

    @Override
    public Object[] toArray() {
        return elementData;
    }

    //TODO изменить добавление объекта в лист. Избавиться от явного Element. Передавать можно либо один объект, либо список объектов
    //TODO сделать тест на добавление одного объекта и листа объектов
    @Override
    public boolean add(T o) {
        if (elementData.length == size) grow();
        if (o instanceof Element) {
            elementData[size++] = (Element) o;
            return true;
        }

        elementData[size++] = new Element(o);
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
    public T get(int index) {
        return (T) elementData[index].getElement();
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
            result_list.add(res.getElement());
//            System.out.println(res);
            get(index);
            index--;
        }

        Collections.reverse(result_list);
        return result_list;
    }

    /**
     * Get the state of a list by time
     * @param time
     * @return List from begin to index if we find state by time in List, empty ArrayList if not
     */
    //TODO разобраться с исключением
    public ArrayList getList(LocalDateTime time) throws ArrayIndexOutOfBoundsException{
        result_list.clear();
        if (elementData[0].getDateTime().isAfter(time)) throw new ArrayIndexOutOfBoundsException();

        for (int i = 0; i <= elementData.length-1; i++) {
            if (elementData[i].getDateTime().isEqual(time)) return getList(i);
            if (elementData[i].getDateTime().isAfter(time)) return getList(i - 1);
        }
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
    public T remove(int index) {
        Element deletedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        return (T) deletedElement.getElement();
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
