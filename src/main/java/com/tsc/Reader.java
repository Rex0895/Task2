package com.tsc;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface Reader {
    void readFile(File file);

    Map<String, Shape> getShapesMap();

    List<Shape> getShapesList();
}
