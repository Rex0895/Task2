package com.tsc.reader;

import com.tsc.Shape;

import java.io.*;
import java.util.HashMap;
//page 243, 256
public class TXTReader implements Reader {
    private String path;

    public TXTReader(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String p) {
        path = p;
    }

    public boolean testParams() {
        return true;
    }
    private HashMap<String, Shape>parseData(String inputString)
    {
        HashMap<String, Shape> allShapes = new HashMap<String, Shape>();
        String con = "([A-Z])([A-Za-z]+)";
        if (!inputString.matches(con)){
            System.out.println("Верно");
    }


        return allShapes;
    }

    public HashMap<String, Shape> readData() {
        int lineCounter = 0;
        HashMap<String, Shape> allShapes = new HashMap<String, Shape>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufRead = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            //читаем файл в одну строку
            while ((line = bufRead.readLine()) != null) {
                if (line.isEmpty()) continue;
                sb.append(line);
            }
            //System.out.println(sb.toString());
            String inputString=sb.toString().replaceAll("\\s\\n\\t\\r","");
            parseData(inputString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  allShapes;
    }

    public static void main(String[] args) {
        TXTReader tr=new TXTReader("src/main/resources/input.txt");
        HashMap<String,Shape>allShapes = tr.readData();
    }
}
