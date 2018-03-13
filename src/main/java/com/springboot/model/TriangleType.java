package com.springboot.model;

public class TriangleType {

private int side1;
private int side2;
private int side3;
	
	public TriangleType() {
		side1 = 0;
		side2 = 0;
		side3 = 0;
    }
	public TriangleType(final int a, final int b, final int c) {
		side1 = a;
		side2 = b;
		side3 = c;
    }
	public int getSide1() {
        return side1;
    }
	public int getSide2() {
        return side2;
    }
	public int getSide3() {
        return side3;
    }
	
	public void setSide1(int a) {
         side1 = a;
    }
	public void setSide2(int a) {
        side2 = a;
    }
	
	public void setSide3(int a) {
        side3 = a;
    }
	public String checkTriangleType(int a,int b,int c) {
		
		if (a <= 0 || b <= 0 || c <= 0) return "INVALID TRIANGLE";
		else if (a >= b+c || c >= b+a || b >= a+c) return "INVALID";
		else if (a == b && b == c) return "EQUILATERAL";
		else if (a==b || b==c || c==a) return "ISOSCELES";
		else return "SCALENE";
					
	}
}
