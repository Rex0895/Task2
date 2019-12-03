package com.tsc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShapesActions {

    private List<Shape> shapeList;

    public ShapesActions(List<Shape> shapeList) {
        this.shapeList = new LinkedList<>(shapeList);
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public void printShapesList() {
        if (shapeList.size() != 0) {
            System.out.println("Список выделенных фигур:");
            for (Shape shape : shapeList) {
                System.out.println(shape);
            }
        }
    }

    private Shape getShapeWithGlobalCoords(String id, int globalX, int globalY, List<Shape> shapes) {
        Shape result = null;
        for (Shape shape : shapes) {
            if (shape.ID.equals(id)) {
                globalX = globalX + shape.X;
                globalY = globalY + shape.Y;
                if (shape instanceof Point) {
                    return new Point(id, globalX, globalY);

                } else if (shape instanceof Square) {
                    Square square = (Square) shape;
                    return new Square(id, globalX, globalY, square.getLength());
                } else {
                    Rectangle rectangle = (Rectangle) shape;
                    return new Rectangle(id, globalX, globalY, rectangle.getWeight(), rectangle.getHeight());
                }
            } else {
                if (shape instanceof Square) {
                    Square square = (Square) shape;
                    if (square.getChildShapes() != null) {
                        globalX = globalX + shape.X;
                        globalY = globalY + shape.Y;
                        result = getShapeWithGlobalCoords(id, globalX, globalY, square.getChildShapes());
                        if (result != null) {
                            return result;
                        } else {
                            globalX = globalX - shape.X;
                            globalY = globalY - shape.Y;
                        }
                    }
                }
                if (shape instanceof Rectangle) {
                    Rectangle rec = (Rectangle) shape;
                    if (rec.getChildShapes() != null) {
                        globalX = globalX + shape.X;
                        globalY = globalY + shape.Y;
                        result = getShapeWithGlobalCoords(id, globalX, globalY, rec.getChildShapes());
                        if (result != null) {
                            return result;
                        } else {
                            globalX = globalX - shape.X;
                            globalY = globalY - shape.Y;
                        }
                    }
                }
                continue;
            }
        }
        return result;
    }

    private Point getSecondPointOfShape(Shape shape1) {
        int shape1X2 = 0;
        int shape1Y2 = 0;
        if (shape1 instanceof Point) {
            shape1X2 = shape1.X;
            shape1Y2 = shape1.Y;
        }
        if (shape1 instanceof Square) {
            Square square = (Square) shape1;
            shape1X2 = square.getX() + square.getLength();
            shape1Y2 = square.getY() + square.getLength();
        }
        if (shape1 instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape1;
            shape1X2 = rectangle.getX() + rectangle.getWeight();
            shape1Y2 = rectangle.getY() + rectangle.getHeight();
        }
        return new Point(shape1.ID + "(x2,y2)", shape1X2, shape1Y2);
    }
    /**Координаты фигур-наследников задаются относительно родительской фигуры*/
    /**
     * Метод, определяющий пересекаются ли две фигуры, имена которых переданы в параметрах функции
     */
    private int[] getCoordsOf2Shapes(String id1, String id2) {
        int[] coordinates = new int[8];
        //Левая верхняя точка фигуры
        Shape shape1 = getShapeWithGlobalCoords(id1, 0, 0, shapeList);
        coordinates[0] = shape1.getX();
        coordinates[1] = shape1.getY();
        //Правая нижняя точка фигуры
        if (!(shape1 instanceof Point)) {
            Point secondPoint1 = getSecondPointOfShape(shape1);
            coordinates[2] = secondPoint1.getX();
            coordinates[3] = secondPoint1.getY();
        } else {
            coordinates[2] = 0;
            coordinates[3] = 0;
        }
        //Аналогично для 2 фигуры
        Shape shape2 = getShapeWithGlobalCoords(id2, 0, 0, shapeList);
        coordinates[4] = shape2.getX();
        coordinates[5] = shape2.getY();
        if (!(shape1 instanceof Point)) {
            Point secondPoint2 = getSecondPointOfShape(shape2);
            coordinates[6] = secondPoint2.getX();
            coordinates[7] = secondPoint2.getY();
        } else {
            coordinates[6] = 0;
            coordinates[7] = 0;
        }
        return coordinates;
    }

    public boolean areCrossed(String id1, String id2) {
        //Проверяем если верхняя грань первого прямоугольника находится ниже второго,
        // или нижняя выше верхней  грани первого.Тоже самое и для оси X.
        int[] coords = getCoordsOf2Shapes(id1, id2);
        if (coords[1] > coords[7] || coords[3] < coords[5] || coords[2] < coords[4] || coords[0] > coords[6]) {
            System.out.println("Фигуры " + id1 + " и " + id2 + " НЕ пересекаются");
            return false;
        } else {
            System.out.println("Фигуры " + id1 + " и " + id2 + " пересекаются");
            return true;
        }
    }

    /**
     * Метод, возвращающий площадь пересечения многоугольников, имена которых переданы в параметрах
     */
    public void calculateCrossedArea(String id1, String id2) {
        int areaSquare = 0;
        //Координаты внутреннего прямоугольника - будут 2 и 3 по величине
        int[] arr = getCoordsOf2Shapes(id1, id2);
        int[] X = {arr[0], arr[2], arr[4], arr[6]};
        int[] Y = {arr[1], arr[3], arr[5], arr[7]};
        //если пересекаются
        if (areCrossed(id1, id2)) {
            Arrays.sort(X);
            Arrays.sort(Y);
            areaSquare = (X[2] - X[1]) * (Y[2] - Y[1]);
            System.out.println(areaSquare);
        }
    }
}
