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
 * Simple test using the WicketTester
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
		rectangles.add(new Rectangle(3, 0, 2, 6));
		rectangles.add(new Rectangle(6, 0, 2, 5));
		rectangles.add(new Rectangle(9, 0, 2, 4));
		rectangles.add(new Rectangle(12, 0,2, 3));
		Collection<Rectangle> hrectangles = rectanglesService.getMinimumNumberOfHorizontalRectangles(rectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 5);
		Iterator<Rectangle> iterator = hrectangles.iterator();
		Assert.assertTrue("{\"x\":0, \"y\":0, \"width\":10, \"height\":3}".equals(iterator.next().toJSON()));
		Assert.assertTrue("{\"x\":0, \"y\":4, \"width\":8, \"height\":1}".equals(iterator.next().toJSON()));
		Assert.assertTrue("{\"x\":0, \"y\":5, \"width\":6, \"height\":1}".equals(iterator.next().toJSON()));
		Assert.assertTrue("{\"x\":0, \"y\":6, \"width\":4, \"height\":1}".equals(iterator.next().toJSON()));
		Assert.assertTrue("{\"x\":0, \"y\":7, \"width\":2, \"height\":1}".equals(iterator.next().toJSON()));
	}
	
	@Test
	public void testGetMinimumNumberOfHorizontalRectangles2() {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle(0, 0, 2, 3));
		rectangles.add(new Rectangle(3, 0, 2, 4));
		rectangles.add(new Rectangle(6, 0, 2, 5));
		rectangles.add(new Rectangle(9, 0, 2, 6));
		rectangles.add(new Rectangle(12, 0,2, 7));
		Collection<Rectangle> hrectangles = rectanglesService.getMinimumNumberOfHorizontalRectangles(rectangles);
		org.junit.Assert.assertEquals(hrectangles.size(), 5);
		Iterator<Rectangle> iterator = hrectangles.iterator();
		Assert.assertTrue("{\"x\":0, \"y\":0, \"width\":10, \"height\":3}".equals(iterator.next().toJSON()));
		Assert.assertTrue("{\"x\":3, \"y\":4, \"width\":8, \"height\":1}".equals(iterator.next().toJSON()));
		Assert.assertTrue("{\"x\":6, \"y\":5, \"width\":6, \"height\":1}".equals(iterator.next().toJSON()));
		Assert.assertTrue("{\"x\":9, \"y\":6, \"width\":4, \"height\":1}".equals(iterator.next().toJSON()));
		Assert.assertTrue("{\"x\":12, \"y\":7, \"width\":2, \"height\":1}".equals(iterator.next().toJSON()));
	}
}
