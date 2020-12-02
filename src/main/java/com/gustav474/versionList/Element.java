package com.gustav474.versionList;

import java.util.*;

public class Element {

    private Calendar time;
    private ArrayList list;

    public Element(ArrayList list) {
        this.time = new GregorianCalendar();
        this.list = list;
    }

    public Element(GregorianCalendar time, ArrayList list) {
        this.time = time;
        this.list = list;
    }

    public ArrayList getList() {
        return this.list;
    }

    public String getTime() {
        return time.get(Calendar.HOUR_OF_DAY) + "-" + this.time.get(Calendar.MINUTE);
    }

    @Override
    public String toString() {
        return getTime() + " " + this.list;
    }
}
