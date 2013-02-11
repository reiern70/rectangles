package com.odobo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestRectangle {
	
	@Before
	public void setUp() {
	}

	/**
	 * test rectangles are indeed horizontally adjacent 
	 */
	@Test
	public void testRectangleJSON() {
		Rectangle rectangle = new Rectangle(0, 0, 1, 1);		
		Assert.assertEquals("{\"x\":0, \"y\":0, \"width\":1, \"height\":1}", rectangle.toJSON());
	}
	
	
	@Test
	public void testIntersectionWith1() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 1, 1));
		rectangles.add(new Rectangle(1, 0, 1, 3));
		rectangles.add(new Rectangle(2, 0, 1, 1));
		rectangles.add(new Rectangle(3, 0, 1, 3));
		
		Rectangle i1 = new Rectangle(0, 0, 4, 1);
		
		Collection<Rectangle> hrectangles = i1.intersectionWith(rectangles);		
		printRectangles(hrectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 1);				
		List<Rectangle> RESULTS = new ArrayList<Rectangle>();
		RESULTS.add(i1);
		Iterator<Rectangle> iterator =RESULTS.iterator();
		for(Rectangle rectangle: hrectangles) {
			Assert.assertEquals(iterator.next(), rectangle);
		}
		
	}
	
	@Test
	public void testIntersectionWith2() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 1, 1));
		rectangles.add(new Rectangle(1, 0, 1, 3));
		rectangles.add(new Rectangle(2, 0, 1, 1));
		rectangles.add(new Rectangle(3, 0, 1, 3));
		
		Rectangle i1 = new Rectangle(0, 1, 4, 2);
		
		Collection<Rectangle> hrectangles = i1.intersectionWith(rectangles);		
		printRectangles(hrectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 2);				
		List<Rectangle> RESULTS = new ArrayList<Rectangle>();
		RESULTS.add(new Rectangle(1, 1, 1, 2));
		RESULTS.add(new Rectangle(3, 1, 1, 2));
		Iterator<Rectangle> iterator =RESULTS.iterator();
		for(Rectangle rectangle: hrectangles) {
			Assert.assertEquals(iterator.next(), rectangle);
		}
		
	}
	
	@Test
	public void testIntersectionWith3() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 1, 1));
		rectangles.add(new Rectangle(1, 0, 1, 3));
		rectangles.add(new Rectangle(2, 0, 1, 1));
		rectangles.add(new Rectangle(3, 0, 1, 2));
		rectangles.add(new Rectangle(4, 0, 1, 3));
		
		Rectangle i1 = new Rectangle(0, 1, 4, 1);
		
		Collection<Rectangle> hrectangles = i1.intersectionWith(rectangles);		
		printRectangles(hrectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 2);				
		List<Rectangle> RESULTS = new ArrayList<Rectangle>();
		RESULTS.add(new Rectangle(1, 1, 1, 1));	
		RESULTS.add(new Rectangle(3, 1, 2, 1));
		Iterator<Rectangle> iterator =RESULTS.iterator();
		for(Rectangle rectangle: hrectangles) {
			Assert.assertEquals(iterator.next(), rectangle);
		}
		
	}
	
	@Test
	public void testIntersectionWith4() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 1, 1));
		rectangles.add(new Rectangle(1, 0, 1, 3));
		rectangles.add(new Rectangle(2, 0, 1, 1));
		rectangles.add(new Rectangle(3, 0, 1, 2));
		rectangles.add(new Rectangle(4, 0, 1, 3));
		
		Rectangle i1 = new Rectangle(0, 2, 4, 1);
		
		Collection<Rectangle> hrectangles = i1.intersectionWith(rectangles);		
		printRectangles(hrectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 2);				
		List<Rectangle> RESULTS = new ArrayList<Rectangle>();
		RESULTS.add(new Rectangle(1, 2, 1, 1));	
		RESULTS.add(new Rectangle(4, 2, 1, 1));
		Iterator<Rectangle> iterator =RESULTS.iterator();
		for(Rectangle rectangle: hrectangles) {
			Assert.assertEquals(iterator.next(), rectangle);
		}
		
	}
	
	@Test
	public void testIntersectionWith5() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 1, 1));
		rectangles.add(new Rectangle(1, 0, 1, 3));
		rectangles.add(new Rectangle(2, 0, 1, 1));
		rectangles.add(new Rectangle(3, 0, 1, 2));
		rectangles.add(new Rectangle(4, 0, 1, 3));
		rectangles.add(new Rectangle(5, 0, 1, 1));
		rectangles.add(new Rectangle(6, 0, 1, 4));
		
		Rectangle i1 = new Rectangle(0, 2, 4, 1);
		
		Collection<Rectangle> hrectangles = i1.intersectionWith(rectangles);		
		printRectangles(hrectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 2);				
		List<Rectangle> RESULTS = new ArrayList<Rectangle>();
		RESULTS.add(new Rectangle(1, 2, 1, 1));	
		RESULTS.add(new Rectangle(4, 2, 1, 1));
		RESULTS.add(new Rectangle(6, 2, 1, 1));
		Iterator<Rectangle> iterator =RESULTS.iterator();
		for(Rectangle rectangle: hrectangles) {
			Assert.assertEquals(iterator.next(), rectangle);
		}
		
	}
	
	@Test
	public void testIntersectionWith6() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 1, 1));
		rectangles.add(new Rectangle(1, 0, 1, 3));
		rectangles.add(new Rectangle(2, 0, 1, 1));
		rectangles.add(new Rectangle(3, 0, 1, 2));
		rectangles.add(new Rectangle(4, 0, 1, 3));
		rectangles.add(new Rectangle(5, 0, 1, 1));
		rectangles.add(new Rectangle(6, 0, 1, 4));
		
		Rectangle i1 = new Rectangle(0, 1, 6, 1);
		
		Collection<Rectangle> hrectangles = i1.intersectionWith(rectangles);		
		printRectangles(hrectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 3);				
		List<Rectangle> RESULTS = new ArrayList<Rectangle>();
		RESULTS.add(new Rectangle(1, 1, 1, 1));	
		RESULTS.add(new Rectangle(3, 1, 2, 1));
		RESULTS.add(new Rectangle(6, 1, 1, 1));
		Iterator<Rectangle> iterator =RESULTS.iterator();
		for(Rectangle rectangle: hrectangles) {
			Assert.assertEquals(iterator.next(), rectangle);
		}
		
	}
	
	private void printRectangles(Iterable<Rectangle> iterator) {
		for(Rectangle rectangle: iterator) {
			System.out.println(rectangle);
		}
	}
}
