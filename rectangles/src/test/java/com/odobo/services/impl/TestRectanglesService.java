package com.odobo.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.odobo.domain.Rectangle;
import com.odobo.services.IRectanglesService;

/**
 * Test for {@link IRectanglesService}.
 */
public class TestRectanglesService {
	
	IRectanglesService rectanglesService;

	@Before
	public void setUp() {
		rectanglesService = new RectanglesService();
	}

	/**
	 * test rectangles are indeed horizontally adjacent 
	 */
	@Test
	public void testGetRandomHorizontalyAdjacentRectables() {
		int nrectangles = 10;
		Collection<Rectangle> iterable = rectanglesService.getRandomHorizontalyAdjacentRectangles(nrectangles);
		Assert.assertEquals(iterable.size(), nrectangles);
		Integer previousX = null;		
		for(Rectangle rectangle: iterable) {
			if(previousX == null){
				previousX = rectangle.getX() + rectangle.getWidth();
			} else {
				Assert.assertEquals(rectangle.getX(), previousX.intValue());
				previousX = rectangle.getX() + rectangle.getWidth();
			}
		}
	}
	
	@Test
	public void testGetMinimumNumberOfHorizontalRectangles1() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 2, 7));
		rectangles.add(new Rectangle(2, 0, 2, 6));
		rectangles.add(new Rectangle(4, 0, 2, 5));
		rectangles.add(new Rectangle(6, 0, 2, 4));
		rectangles.add(new Rectangle(8, 0,2, 3));
		Collection<Rectangle> hrectangles = rectanglesService.getMinimumNumberOfHorizontalRectangles(rectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 5);
		Iterator<Rectangle> iterator = hrectangles.iterator();
		Assert.assertEquals("{\"x\":0, \"y\":0, \"width\":10, \"height\":3}", iterator.next().toJSON());
		Assert.assertEquals("{\"x\":0, \"y\":3, \"width\":8, \"height\":1}",iterator.next().toJSON());
		Assert.assertEquals("{\"x\":0, \"y\":4, \"width\":6, \"height\":1}",iterator.next().toJSON());
		Assert.assertEquals("{\"x\":0, \"y\":5, \"width\":4, \"height\":1}", (iterator.next().toJSON()));
		Assert.assertEquals("{\"x\":0, \"y\":6, \"width\":2, \"height\":1}",(iterator.next().toJSON()));
	}
	
	@Test
	public void testGetMinimumNumberOfHorizontalRectangles2() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 2, 3));
		rectangles.add(new Rectangle(2, 0, 2, 4));
		rectangles.add(new Rectangle(4, 0, 2, 5));
		rectangles.add(new Rectangle(6, 0, 2, 6));
		rectangles.add(new Rectangle(8, 0,2, 7));
		Collection<Rectangle> hrectangles = rectanglesService.getMinimumNumberOfHorizontalRectangles(rectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 5);
		Iterator<Rectangle> iterator = hrectangles.iterator();
		Assert.assertEquals("{\"x\":0, \"y\":0, \"width\":10, \"height\":3}",(iterator.next().toJSON()));
		Assert.assertEquals("{\"x\":2, \"y\":3, \"width\":8, \"height\":1}",(iterator.next().toJSON()));
		Assert.assertEquals("{\"x\":4, \"y\":4, \"width\":6, \"height\":1}",(iterator.next().toJSON()));
		Assert.assertEquals("{\"x\":6, \"y\":5, \"width\":4, \"height\":1}",(iterator.next().toJSON()));
		Assert.assertEquals("{\"x\":8, \"y\":6, \"width\":2, \"height\":1}",(iterator.next().toJSON()));
	}
	
	
	
	@Test
	public void testGetMinimumNumberOfHorizontalRectangles3() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 1, 1));
		rectangles.add(new Rectangle(1, 0, 1, 3));
		rectangles.add(new Rectangle(2, 0, 1, 1));
		rectangles.add(new Rectangle(3, 0, 1, 3));
		Collection<Rectangle> hrectangles = rectanglesService.getMinimumNumberOfHorizontalRectangles(rectangles);
		printRectangles(hrectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 3);				
		List<Rectangle> RESULTS = new ArrayList<Rectangle>();
		RESULTS.add(new Rectangle(0, 0, 4, 1));
		RESULTS.add(new Rectangle(1, 1, 1, 2));
		RESULTS.add(new Rectangle(3, 1, 1, 2));
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
