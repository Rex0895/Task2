package com.tsc.reader;

import com.tsc.Shape;

import java.util.HashMap;

public interface Reader {
    String getPath();
    void setPath(String p);
    HashMap<String, Shape> readData();
    boolean testParams();

}
