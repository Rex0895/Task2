package com.tsc;

public class ShapeFactory {
    public Shape createShape(String str) {
        Shape shape = null;
        str = str.replaceAll("\\(|\\)|\\[|\\]| ", "");
        String[] strArr = str.split(",");
        String id = strArr[0];
        int x = Integer.valueOf(strArr[1]);
        int y = Integer.valueOf(strArr[2]);
        //S(S1,5,4,3)
        if (id.matches("^P[0-9]+$") && strArr.length == 3) {
            return new Point(id, x, y);
        }
        if (id.matches("^S[0-9]+$") && strArr.length == 4) {
            int len = Integer.valueOf(strArr[3]);
            return new Square(id, x, y, len);
        }
        //R(R1,5,4,3,2)
        if (id.matches("^R[0-9]+$") && strArr.length == 5) {
            int weight = Integer.valueOf(strArr[3]);
            int height = Integer.valueOf(strArr[4]);
            return new Rectangle(id, x, y, weight, height);
        }
        return shape;
    }
}
