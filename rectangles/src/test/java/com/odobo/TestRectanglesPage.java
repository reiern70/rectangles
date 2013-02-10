package com.odobo;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.odobo.web.RectanglesPage;
import com.odobo.web.RectanglesApplication;

/**
 * Simple test using the WicketTester
 */
public class TestRectanglesPage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new RectanglesApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(RectanglesPage.class);

		//assert rendered page class
		tester.assertRenderedPage(RectanglesPage.class);
	}
}
