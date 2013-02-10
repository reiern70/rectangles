package com.odobo.domain;

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
}
