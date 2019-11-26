package com.tsc;

import com.tsc.reader.*;

import java.beans.XMLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Task2 {

    public void crossedShapes() {
    }

    public void getCrossedSquare() {
    }

    public static Reader createReader(String path) {
        //Парсинг расширения файла
//        if(path=="txt") return new TXTReader(path);
//        else return new XMLReader();
        return new XMLReader(path);
    }
    public static void readFile(Reader reader,String path)
    {
        reader.setPath(path);
        reader.readData();
    }

    public static void main(String[] args) {
        Reader reader = createReader("src/main/resources/input.xml");
        HashMap<String,Shape>allShapes=reader.readData();
//        Random rand = new Random();
//        ArrayList<Shape> list = new ArrayList<Shape>();
//        for(Shape sh: list)
//            System.out.println(sh.toString());
        //Reader reader =



    }
}
