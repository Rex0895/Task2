package com.tsc;

import java.util.ArrayList;

public class Rectangle extends Shape {
    private int weight;
    private int height;
    private ArrayList<Shape> childShapes;

    public Rectangle(String id,int x,int y,int weight,int height){
        super(id,x,y);
        this.weight=weight;
        this.height=height;
    }
    public Rectangle(String id,int x,int y,int weight,int height,ArrayList<Shape>chShapes){
        super(id,x,y);
        this.weight=weight;
        this.height=height;
        childShapes=new ArrayList<Shape>(chShapes);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(childShapes==null)
            sb.append(String.format("S(%s,%d,%d,%d,%d)",super.getID(),super.getX(),super.getY(),weight,height));
        else {
            sb.append(String.format("S(%s,%d,%d,%d,%d [",super.getID(),super.getX(),super.getY(),weight,height));
            for(Shape sh: childShapes)
                sb.append(sh.toString()+", ");
            sb.append("])");
        }
        return sb.toString();
    }
}
