package com.gustav474.versionList;

import java.time.LocalDateTime;
import java.util.*;

public class Element<T> {

    private LocalDateTime time;
    private ArrayList<T> listOfElements = new ArrayList<>();

    public Element(ArrayList<T> list) {
        this.time = LocalDateTime.now();
        this.listOfElements = list;
    }

    public Element(T e) {
        this.time = LocalDateTime.now();
        this.listOfElements.add(e);
    }

    /**
     * Use this method for tests
     * @param time
     * @param list
     */
    public Element(LocalDateTime time, ArrayList list) {
        this.time = time;
        this.listOfElements = list;
    }

    public T getElement() {
        if (listOfElements.size() == 1) return listOfElements.get(0);
        else return (T) this.listOfElements;
    }

    public LocalDateTime getDateTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return getDateTime() + " " + this.listOfElements;
    }
}
