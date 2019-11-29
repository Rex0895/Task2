package com.tsc;

import com.tsc.reader.*;

import java.beans.XMLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Task2 {

    public void crossedShapes() {
    }

    public void getCrossedSquare() {
    }

    public static Reader createReader(String path) {
        //Парсинг расширения файла
//        if(path=="txt") return new TXTReader(path);
//        else return new XMLReader();
        return new TXTReader(path);
    }
    public static void readFile(Reader reader,String path)
    {
        reader.readFile();
    }

    public static void main(String[] args) {
        Reader reader = createReader("src/main/resources/input.txt");
        List<Shape> allShapes=reader.readFile();
//        Random rand = new Random();
//        ArrayList<Shape> list = new ArrayList<Shape>();
        for(Shape sh: allShapes)
            System.out.println(sh.toString());
        //Reader reader =



    }
}
