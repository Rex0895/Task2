package com.tsc;


import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Task2 {

    private static String allShapesToString(List<Shape> shapeList, int counter) {
        StringBuilder sb = new StringBuilder();
        for (Shape shape : shapeList) {
            if (counter != 0) {
                for (int i = 0; i < counter; i++)
                    sb.append("\t");
            }
            sb.append(shape + "\n");
            if (shape instanceof Polygon) {
                List<Shape> tmpList = ((Polygon) shape).getChildShapesList();
                if (tmpList != null) {
                    counter++;
                    sb.append(allShapesToString(tmpList, counter));
                    counter--;
                } else continue;
            }
        }
        return sb.toString();
    }

    public static void printAllShapes(List<Shape> shapeList) {
        System.out.println("Вывод фигур в виде иерархии:\n" + allShapesToString(shapeList, 0));
    }

    public static boolean areCrossed(String id1, String id2, Map<String, Shape> shapes) {
        //Проверяем если верхняя грань первого прямоугольника находится ниже второго,
        // или нижняя выше верхней  грани первого.Тоже самое и для оси X.
        Shape shape1 = shapes.get(id1);
        Shape shape2 = shapes.get(id2);
        //Ay1 By2 Ay2 By1 Ax2 Bx1 Ax1 Bx2
        if (shape1.getGlobalY() > shape2.getSecondY() ||
                shape1.getSecondY() < shape2.getGlobalY()
                || shape1.getSecondX() < shape2.getGlobalX()
                || shape1.getGlobalX() > shape2.getSecondX()) {
            System.out.println("Фигуры " + id1 + " и " + id2 + " НЕ пересекаются");
            return false;
        } else {
            System.out.println("Фигуры " + id1 + " и " + id2 + " пересекаются");
            return true;
        }
    }

    public static void getSquareOf2Shape(String id1, String id2, Map<String, Shape> shapes) {
        int areaSquare = 0;
        Shape shape1 = shapes.get(id1);
        Shape shape2 = shapes.get(id2);
        //Координаты внутреннего прямоугольника - будут 2 и 3 по величине
        int[] X = {shape1.getGlobalX(), shape1.getSecondX(), shape2.getGlobalX(), shape2.getSecondX()};
        int[] Y = {shape1.getGlobalY(), shape1.getSecondY(), shape2.getGlobalY(), shape2.getSecondY()};
        //если пересекаются
        if (areCrossed(id1, id2, shapes)) {
            Arrays.sort(X);
            Arrays.sort(Y);
            areaSquare = (X[2] - X[1]) * (Y[2] - Y[1]);
        }
        System.out.println("Площадь пересечения этих фигур: " + areaSquare);
    }

    public static void main(String[] args) {
        //  Reader reader = createReader("src/main/resources/input.txt");
        Reader reader = new TXTReader();
        reader.readFile(new File("src/main/resources/input.txt"));
        List<Shape> shapesList = reader.getShapesList();
        Map<String, Shape> shapeMap = reader.getShapesMap();
        Shape shape1 = shapeMap.get("P1");
        System.out.println(shape1.getId()+"("+shape1.getGlobalX()+"/"+shape1.getGlobalY()+")");
        Shape shape2 =shapeMap.get("R4");
        System.out.println(shape2.getId()+"("+shape2.getGlobalX()+"/"+shape2.getGlobalY()+")");
        //Действия над фигурами
        printAllShapes(shapesList);
        areCrossed("S1", "P1", shapeMap);
        getSquareOf2Shape("P1", "S1", shapeMap);


    }
}
