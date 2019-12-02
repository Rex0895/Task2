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
        Shape result=null;
        for (Shape shape : shapes) {
            if (shape.ID.equals(id)) {
                globalX = globalX + shape.X;
                globalY = globalY + shape.Y;
                if (shape instanceof Point) {
                    return new Point(id, globalX, globalY);
                }
                else if (shape instanceof Square) {
                    Square square = (Square) shape;
                    return new Square(id,globalX,globalY,square.getLength());
                }
                else{
                    Rectangle rectangle = (Rectangle) shape;
                    return new Rectangle(id,globalX,globalY,rectangle.getWeight(),rectangle.getHeight());
                }
            } else {
                if (shape instanceof Square) {
                    globalX = globalX + shape.X;
                    globalY = globalY + shape.Y;
                    Square square = (Square) shape;
                    if(square.getChildShapes()!=null){
                        result=getShapeWithGlobalCoords(id, globalX, globalY, square.getChildShapes());
                    }
                }
                if (shape instanceof Rectangle) {
                    globalX = globalX + shape.X;
                    globalY = globalY + shape.Y;
                    Rectangle rec = (Rectangle) shape;
                    if(rec.getChildShapes()!=null){
                        result=getShapeWithGlobalCoords(id, globalX, globalY, rec.getChildShapes());
                    }
                }
                continue;
            }
        }
        return result;
    }

        /**Координаты фигур-наследников задаются относительно родительской фигуры*/
        /**
         * Метод, определяющий пересекаются ли две фигуры, имена которых переданы в параметрах функции
         */
        public boolean crossedShapes (String id1, String id2){
           Shape shape1 = getShapeWithGlobalCoords(id1,0,0,shapeList);
           Shape shape2 = getShapeWithGlobalCoords(id2,0,0,shapeList);
            System.out.println(shape1.X +" "+shape1.Y);//+" "+shape2);
            System.out.println(shape2.ID+" "+shape2.X +" "+shape2.Y);

            return false;
        }

        /**
         * Метод, возвращающий площадь пересечения многоугольников, имена которых переданы в параметрах
         */
        public int getCrossedArea (String id1, String id2){

            return 0;
        }
    }
