package com.gustav474.versionList;

import java.time.LocalDateTime;
import java.util.*;

public class Element<T> {

    private LocalDateTime time;
    private ArrayList<T> listOfElements;

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

    public ArrayList<T> getList() {
        return this.listOfElements;
    }

    public LocalDateTime getDateTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return getDateTime() + " " + this.listOfElements;
    }
}
