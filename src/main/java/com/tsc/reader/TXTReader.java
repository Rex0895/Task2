package com.tsc.reader;

import com.tsc.Point;
import com.tsc.Rectangle;
import com.tsc.Shape;
import com.tsc.Square;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    private Shape createElementaryShape(String str) {
        //P(P1,5,4)
        str = str.replaceAll("\\(|\\)|\\[|\\]| ", "");
        String[] strArr = str.split(",");
        String id = strArr[0];
        int x = Integer.valueOf(strArr[1]);
        int y = Integer.valueOf(strArr[2]);
        //S(S1,5,4,3)
        if (strArr.length == 4) {
            int len = Integer.valueOf(strArr[3]);
            return new Square(id, x, y, len);
        }
        //R(R1,5,4,3,2)
        if (strArr.length == 5) {
            int weight = Integer.valueOf(strArr[3]);
            int height = Integer.valueOf(strArr[4]);
            return new Rectangle(id, x, y, weight, height);
        }
        return new Point(id, x, y);
    }

    private static Map<Integer, Character> getBracketsMap(String inputString) {
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

    //    private List<Shape> parseString(String inputString) {
//        List<Shape> allShapes = new LinkedList<>();
//        char[] inputStringArray = inputString.toCharArray();
//        int beginStrIndex = 0;
//        char prevBracket = ' ';
//        int endStrIndex = 0;
//        boolean hasChilds = false;
//        Shape complexShape = null;
//
//        //R(R1,10,10,15,3,[S(S1,0,1,2),P(P1,6,1),R(R2,8,0,4,2,[S(S2,1,1,1)])]),R(R3,12,8,10,7,[S(S3,5,1,2)])
//        for (int iteration = 0; iteration < inputStringArray.length; iteration++) {
//            char symbol = inputStringArray[iteration];
//            if (Character.isDigit(symbol) || Character.isLetter(symbol) || symbol == ',') continue;
//            if (symbol == '(' && hasChilds == false) {
//                beginStrIndex = iteration + 1;
//                prevBracket = symbol;
//                continue;
//            }
//            if (symbol == ')' && hasChilds == false) {
//                endStrIndex = iteration + 1;
//                String subString = inputString.substring(beginStrIndex, endStrIndex);
//                allShapes.add(createElementaryShape(subString));
//                continue;
//            }
//            if (symbol == '[' && prevBracket == '(') {
//                //(R1,1,2,3,4,[
//                hasChilds = true;
//                String subString = inputString.substring(beginStrIndex, iteration);
//                complexShape = createElementaryShape(subString);
//                beginStrIndex = iteration + 2;//дальше брать символы после [
//                continue;
//            }
//            if (symbol == ']' && hasChilds == true) {
//                prevBracket = symbol;
//                continue;
//            }
//            if (symbol == ')' && prevBracket == ']' && hasChilds == true) {
//                hasChilds = false;
//                endStrIndex = iteration - 1;
//                String subString = inputString.substring(beginStrIndex, endStrIndex);
//                List<Shape> childShapes = new LinkedList<>(parseString(subString));
//                Shape shape = complexShape;//Square || Rectangle
//                //нисходящее преобразование
//                if (shape instanceof Square) {
////                    Square square = (Square) shape;
////                    square.setChildShapes(childShapes);
//                    ((Square) shape).setChildShapes(childShapes);
//                }
//                if (shape instanceof Rectangle) {
////                    Rectangle rect = (Rectangle) shape;
////                    rect.setChildShapes(childShapes);
//                    ((Rectangle) shape).setChildShapes(childShapes);
//                }
//                allShapes.add(shape);
//                continue;
//            }
//        }
//        return allShapes;
//    }

    private List<Shape> parseString(String inputString) // && localRoundBracket<counterRoundBracket
    {
        List<Shape> allShapes = new LinkedList<>();
        Map<Integer, Character> brackets = getBracketsMap(inputString);
        int beginStrIndex = 0, endStrIndex = 0;
        int localQuadrBracket = 0, localRoundBracket = 0;

        char prevBracket = ' ';
        Shape complexShape = null;

        //S(S1,1,2,3,[P(P1,2,3),R(R1,2,3,5,5,[P(P2,3,4)])]),S(S2,3,4,5)
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
                    complexShape = createElementaryShape(subString);
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
                    allShapes.add(createElementaryShape(subString));
                    beginStrIndex = index;
                    prevBracket = symbol;
                    continue;
                }
                if (prevBracket == ']') {
                    if (localQuadrBracket == 0) {
                        endStrIndex = index - 1;
                        String subString = inputString.substring(beginStrIndex, endStrIndex);// [содержимое])
                        List<Shape> childShapes = new LinkedList<>(parseString(subString));
                        Shape shape = complexShape;//Square || Rectangle
                        //нисходящее преобразование
                        if (shape instanceof Square) {
                            //(Square)shape.newInstance().setChildShapes(childShapes);
                            ((Square) shape).setChildShapes(childShapes);
                        }
                        if (shape instanceof Rectangle) {
                            ((Rectangle) shape).setChildShapes(childShapes);
                        }
                        allShapes.add(shape);
                    }
                    prevBracket = symbol;
                    continue;
                }
            }
        }
        return allShapes;
    }

    public List<Shape> readFile() {
        List<Shape> allShapes = new LinkedList<Shape>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufRead = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            //читаем файл в одну строку
            while ((line = bufRead.readLine()) != null) {
                if (line.isEmpty()) continue;
                sb.append(line);
            }
            //System.out.println(sb.toString());
            String inputString = sb.toString().replaceAll("\\s|\\n|\\t|\\r", "");
            System.out.println("Входная строка: " + inputString);
            allShapes = parseString(inputString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allShapes;
    }
}