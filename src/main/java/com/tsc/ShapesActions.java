package com.tsc;

import java.util.LinkedList;
import java.util.List;

public class ShapesActions {

    private List<Shape> shapeList;

    public ShapesActions(List<Shape>shapeList){
        this.shapeList=new LinkedList<>(shapeList);
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public void printShapesList(){
        if(shapeList.size()!=0) {
            for (Shape shape : shapeList){
                System.out.println(shape);
            }
        }
    }
    /**Функция, которая определяет, пересекаются ли две фигуры, имена которых переданы в параметрах функции*/
    public boolean crossedShapes(String id1,String id2) {

        return false;
    }
    /**Функция, возвращающая площадь пересечения многоугольников, имена которых переданы в параметрах*/
    public int getCrossedArea(String id1,String id2) {

        return 0;
    }
}
