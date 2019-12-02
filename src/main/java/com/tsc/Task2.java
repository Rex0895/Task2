package com.tsc;

import com.tsc.reader.Reader;
import com.tsc.reader.TXTReader;
import com.tsc.reader.XMLReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    public static Reader createReader(String path) {
        //Парсинг расширения файла
        Pattern p = Pattern.compile("\\.\\w+$");
        Matcher m = p.matcher(path);
        m.find();
        String extension = m.group();
        if (extension.equals(".txt")) {
            return new TXTReader(path);
        } else if (extension.equals(".xml")) {
            return new XMLReader(path);
        } else {
            throw new NullPointerException("Неверное расширение файла:" + path);
        }
    }

    public static void readFile(Reader reader, String path) {
        reader.readFile();
    }

    public static void main(String[] args) {
        Reader reader = createReader("src/main/resources/input.txt");
        List<Shape> allShapes = reader.readFile();
        ShapesActions actions = new ShapesActions(allShapes);
        //actions.printShapesList();
        actions.crossedShapes("S1","P11");

    }
}
