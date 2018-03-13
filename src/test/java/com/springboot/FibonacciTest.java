package com.springboot;

import static org.junit.Assert.*;

import org.junit.Test;

import com.springboot.model.Fibonacci;
import com.springboot.model.ReverseWord;
import com.springboot.model.TriangleType;


public class FibonacciTest {
	Fibonacci f = new Fibonacci();
	ReverseWord rev = new ReverseWord();
	TriangleType type = new TriangleType();
	
	long number = 10,correctResult=55,incorrectResult=30;
	String s = "ActualString",s1="gnirtSlautcA";
	
	@Test()
	public void testGetFiboSequence() {
		
		assertEquals(correctResult,f.getFiboSequence(number));
		
		assertNotEquals(incorrectResult,f.getFiboSequence(number));
		
		assertNotNull(f.getFiboSequence(number));
	}
	
	@Test()
	public void testReverseWord() {
		
		assertEquals(s1,rev.getReverseString(s));
		
		assertNotEquals(s,rev.getReverseString(s));
		
		assertNotNull(rev.getReverseString(s));
	}
	
	@Test()
	public void testTriangleType() {
		
		assertEquals("EQUILATERAL",type.checkTriangleType(10,10,10));
		
		assertEquals("INVALID TRIANGLE",type.checkTriangleType(0,10,10));
		assertEquals("INVALID TRIANGLE",type.checkTriangleType(10,10,0));
		assertEquals("INVALID TRIANGLE",type.checkTriangleType(10,0,10));
		assertEquals("INVALID TRIANGLE",type.checkTriangleType(0,0,0));
		assertEquals("INVALID TRIANGLE",type.checkTriangleType(0,-5,10));
		
		assertEquals("ISOSCELES",type.checkTriangleType(10,15,10));
		
		assertEquals("SCALENE",type.checkTriangleType(10,7,8));
		
		
	}
	
	@Test
	public void testReverseWordConstructor() {
		
		// test ReverseWord constructor 1
		ReverseWord rev2 = new ReverseWord();
		assertNotNull(rev2.getValue());
		assertEquals("", rev2.getValue());

		// test ReverseWord constructor 2
		ReverseWord rev = new ReverseWord("test");
		assertEquals("test", rev.getValue());
	}
	
	@Test
	public void testTriangleTypeConstructor() {
		
		// test TriangleType constructor 1
		TriangleType type = new TriangleType();
		assertNotNull(type.getSide1());
		assertNotNull(type.getSide2());
		assertNotNull(type.getSide3());
		
		// test TriangleType constructor 2
		TriangleType type2 = new TriangleType(1,2,3);
		assertEquals(1, type2.getSide1());
		assertEquals(2, type2.getSide2());
		assertEquals(3, type2.getSide3());
	}
	
	@Test
	public void testFibonacciConstructor() {
		
		// test Fibonacci constructor 1
		Fibonacci f = new Fibonacci();
		assertNotNull(f.getValue());
		assertEquals(0, f.getValue());

		// test Fibonacci constructor 2
		Fibonacci f2 = new Fibonacci(15);
		assertEquals(15, f2.getValue());
	}
}
