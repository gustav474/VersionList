package com.gustav474.versionList;

import java.time.LocalDateTime;
import java.util.*;

public class VersionList<T> extends ArrayList<T> implements java.util.List<T>{

    private final int DEFAULT_LENGTH = 2;

    private static final int DEFAULT_CAPACITY = 10;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

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
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i].getElement() == null) return true;
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i].getElement())) return true;
            }
        }
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
        ArrayList list = new ArrayList();
        for (int i = 0; i < elementData.length; i++) {
            list.add(elementData[i].getElement());
        }
        return list.toArray();
    }

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

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index].getElement() == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index].getElement())) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0)
            this.grow(minCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * Add collection as ONE Element instance with One timestampt
     * @param c
     * @return True if add successfully, False if not
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        Element element = new Element(c);
        int oldLength = elementData.length;
        ensureCapacityInternal(size + 1);  // Increments modCount
        elementData = Arrays.copyOf(elementData, elementData.length + 1);
        int newLength = elementData.length;
        elementData[elementData.length-1] = element;
        size++;
        return oldLength != newLength;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        rangeCheckForAdd(index);
        int oldLength = elementData.length;
        ensureCapacityInternal(size + 1);  // Increments modCount

        elementData = Arrays.copyOf(elementData, elementData.length + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        int newLength = elementData.length;
        elementData[index] = new Element(c);

        size++;
        return oldLength != newLength;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    @Override
    public void clear() {
        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
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
    public T set(int index, T element) {
        rangeCheck(index);

        Element oldValue = elementData[index];

        elementData[index] = new Element<T>(element);
        return (T) oldValue.getElement();
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);

        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = new Element(element);
        size++;
    }

    @Override
    public T remove(int index) {
        Element deletedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        return (T) deletedElement.getElement();
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return this.subList(fromIndex, toIndex);
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return super.toArray(a);
    }

}
