package com.gustav474.versionList;

import java.time.LocalDateTime;
import java.util.*;

public class Element {

    private LocalDateTime time;
    private ArrayList list;

    public Element(ArrayList list) {
        this.time = LocalDateTime.now();
        this.list = list;
    }

    /**
     * Use this method for tests
     * @param time
     * @param list
     */
    public Element(LocalDateTime time, ArrayList list) {
        this.time = time;
        this.list = list;
    }

    public ArrayList getList() {
        return this.list;
    }

    public LocalDateTime getDateTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return getDateTime() + " " + this.list;
    }
}
