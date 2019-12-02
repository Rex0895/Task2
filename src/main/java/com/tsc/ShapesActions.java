package com.tsc;

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
                    globalX = globalX + shape.X;
                    globalY = globalY + shape.Y;
                    Square square = (Square) shape;
                    if (square.getChildShapes() != null) {
                        result = getShapeWithGlobalCoords(id, globalX, globalY, square.getChildShapes());
                    }
                }
                if (shape instanceof Rectangle) {
                    globalX = globalX + shape.X;
                    globalY = globalY + shape.Y;
                    Rectangle rec = (Rectangle) shape;
                    if (rec.getChildShapes() != null) {
                        result = getShapeWithGlobalCoords(id, globalX, globalY, rec.getChildShapes());
                    }
                }
                continue;
            }
        }
        return result;
    }
    private Point getSecondPointOfShape(Shape shape1){
        int shape1X2 = 0;
        int shape1Y2 = 0;
        if (shape1 instanceof Point) {
            shape1X2 = shape1.X;
            shape1Y2 = shape1.Y;
        }
        if (shape1 instanceof Square) {
            Square square = (Square) shape1;
            shape1X2 = square.getX() * square.getLength();
            shape1Y2 = square.getY() * square.getLength();
        }
        if (shape1 instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape1;
            shape1X2 = rectangle.getX() * rectangle.getWeight();
            shape1Y2 = rectangle.getY() * rectangle.getHeight();
        }
        return new Point(shape1.ID+"(x2,y2)",shape1X2,shape1Y2);
    }
    /**Координаты фигур-наследников задаются относительно родительской фигуры*/
    /**
     * Метод, определяющий пересекаются ли две фигуры, имена которых переданы в параметрах функции
     */
    public void crossedShapes(String id1, String id2) {
        //( a.y < b.y1 || a.y1 > b.y || a.x1 < b.x || a.x > b.x1 );
        //Проверяем если верхняя грань первого
        // прямоугольника находится ниже второго, или нижняя выше верхней  грани первого.
        // Тоже самое и для оси X.

        //Получаем фигуры с глобальными координатами
        Shape shape1 = getShapeWithGlobalCoords(id1, 0, 0, shapeList);
        int Ax1=shape1.getX();
        int Ay1=shape1.getY();
        Shape shape2 = getShapeWithGlobalCoords(id2, 0, 0, shapeList);
        int Bx1=shape2.getX();
        int By1=shape2.getY();
        //Ищем вторую точку фигуры, завершающую её площадь
        Point secondPoint1=getSecondPointOfShape(shape1);
        int Ax2=secondPoint1.getX();
        int Ay2=secondPoint1.getY();
        Point secondPoint2=getSecondPointOfShape(shape2);
        int Bx2=secondPoint2.getX();
        int By2=secondPoint2.getY();

        if(Ay1 < By2 || Ay2 > By1 || Ax2 < Bx1 || Ax1 > Bx2){
            System.out.println("Фигуры "+id1+" и " +id2+" пересекаются");
        }
        else System.out.println("Фигуры "+id1+" и " +id2+" НЕ пересекаются");
    }

    /**
     * Метод, возвращающий площадь пересечения многоугольников, имена которых переданы в параметрах
     */
    public int getCrossedArea(String id1, String id2) {

        return 0;
    }
}
