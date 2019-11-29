package com.tsc.reader;

import com.tsc.Shape;

import java.util.List;

public interface Reader {
    String getPath();

    void setPath(String p);

    List<Shape> readFile();

    boolean testParams();

}
