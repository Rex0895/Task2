package com.tsc;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class TXTReader implements Reader {
    private LinkedHashMap<String, Shape> shapesMap;

    private List<Shape> shapesList;

    public Map<String, Shape> getShapesMap() {
        return shapesMap;
    }

    public List<Shape> getShapesList() {
        return shapesList;
    }

    private Map<Integer, Character> getBracketsMap(String inputString) {
        //FindAllBrackets
        Map<Integer, Character> bracketsMap = new LinkedHashMap<>();
        for (int i = 0; i < inputString.length(); i++) {
            char symbol = inputString.charAt(i);
            if (symbol == '(' || symbol == ')' || symbol == '[' || symbol == ']') {
                bracketsMap.put(i, symbol);
            }
        }
        return bracketsMap;
    }

    private List<Shape> parseString(String inputString, Shape parrent) {
        List<Shape> shapesList = new LinkedList<>();
        Map<Integer, Character> brackets = getBracketsMap(inputString);
        ShapeFactory shapeFactory = new ShapeFactory();
        int beginStrIndex = 0, endStrIndex = 0;
        int localQuadrBracket = 0;

        char prevBracket = ' ';
        Shape complexShape = null;

        for (Map.Entry<Integer, Character> bracket : brackets.entrySet()) {
            char symbol = bracket.getValue();//скобка
            int index = bracket.getKey();//индекс скобки

            if (symbol == '(' && localQuadrBracket == 0) {
                beginStrIndex = index;//следующий элемент за скобкой
                prevBracket = symbol;
                continue;
            }
            if (symbol == '[') {
                //(R1,1,2,3,4,[
                if (localQuadrBracket == 0) {
                    endStrIndex = index;
                    String subString = inputString.substring(beginStrIndex, endStrIndex);//содержимое ( [
                    complexShape = shapeFactory.createShape(subString);

                    if (complexShape.getParrent() == null) {
                        if (parrent == null)
                            complexShape.setParrent(complexShape);
                        else complexShape.setParrent(parrent);
                    }

                    shapesMap.put(complexShape.getId(), complexShape);
                    beginStrIndex = index + 1;
                    localQuadrBracket++;
                    prevBracket = symbol;
                    continue;//символы после [
                }
                localQuadrBracket++;
                prevBracket = symbol;
                continue;
            }
            if (symbol == ']') {
                localQuadrBracket--;
                prevBracket = symbol;
                continue;
            }
            if (symbol == ')') {
                if (prevBracket == '(' && localQuadrBracket == 0) {
                    endStrIndex = index;//элемент до скобки
                    String subString = inputString.substring(beginStrIndex, endStrIndex);//содержимое между скобками
                    Shape tmpShape = shapeFactory.createShape(subString);

                    if (tmpShape.getParrent() == null) {
                        if (parrent == null)
                            tmpShape.setParrent(tmpShape);
                        else tmpShape.setParrent(parrent);
                    }

                    shapesList.add(tmpShape);
                    shapesMap.put(tmpShape.getId(), tmpShape);
                    beginStrIndex = index;
                    prevBracket = symbol;
                    continue;
                }
                if (prevBracket == ']') {
                    if (localQuadrBracket == 0) {
                        endStrIndex = index - 1;
                        String subString = inputString.substring(beginStrIndex, endStrIndex);// [содержимое])
                        parrent = complexShape;
                        List<Shape> childShapes = new LinkedList<>(parseString(subString, parrent));
                        Shape shape = complexShape;//Square || Rectangle
                        //нисходящее преобразование
                        if (shape instanceof Polygon) {
                            ((Polygon) shape).setChildShapesList(childShapes);
                        }
                        parrent = null;
                        shapesList.add(shape);
                    }
                    prevBracket = symbol;
                    continue;
                }
            }
        }
        return shapesList;
    }

    public void readFile(File file) {
        shapesList = new LinkedList<Shape>();
        shapesMap = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufRead = new BufferedReader(new FileReader(file))) {
            //читаем файл в одну строку
            String line;
            while ((line = bufRead.readLine()) != null) {
                if (line.isEmpty()) continue;
                sb.append(line);
            }
            //System.out.println(sb.toString());
            String inputString = sb.toString().replaceAll("\\s|\\n|\\t|\\r", "");
            System.out.println("Входная строка:\n" + inputString);
            shapesList = parseString(inputString, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
