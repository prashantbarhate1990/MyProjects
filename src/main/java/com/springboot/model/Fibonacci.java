package com.springboot.model;

public class Fibonacci {

	private int value;
	private long fibbo=1;
	private long f1=0;
	private long f2=1;
	public Fibonacci() {
        value = 0;
    }
	public Fibonacci(final int i) {
        value = i;
    }
	public int getValue() {
        return value;
    }
	
	public void setValue(final int i) {
        value = i;
    }
	public long getFiboSequence(long number) {
		
		for(int i=1;i<number;i++) {
			fibbo = f1 + f2;
			f1=f2;
			f2= fibbo;
		}
		return fibbo;
		
	}
	
}
