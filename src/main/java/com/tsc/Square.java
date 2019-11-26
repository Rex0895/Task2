package com.tsc;

import java.util.ArrayList;

public class Square extends Shape{
    private int length;
    private ArrayList<Shape> childShapes;

    public Square(String id,int x,int y,int len){
        super(id,x,y);
        length=len;
        childShapes=null;
    }
    public Square(String id,int x,int y,int len,ArrayList<Shape>chShapes){
        super(id,x,y);
        length=len;
        childShapes=new ArrayList<Shape>(chShapes);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(childShapes==null)
            sb.append(String.format("S(%s,%d,%d,%d)",super.getID(),super.getX(),super.getY(),length));
        else {
            sb.append(String.format("S(%s,%d,%d,%d, [",super.getID(),super.getX(),super.getY(),length));
            for(Shape sh: childShapes)
                sb.append(sh.toString()+", ");
            sb.append("])");
        }
        return sb.toString();
    }

}
