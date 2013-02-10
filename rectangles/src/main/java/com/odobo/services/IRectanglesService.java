/**
 * 
 */
package com.odobo.services;

import java.util.Collection;

import com.odobo.domain.Rectangle;

/**
 * Service regarding rectangles.
 * 
 * @author reiern70
 */
public interface IRectanglesService {

	public String getRectanglesAsJSON(Collection<Rectangle> rectangles);
	
	/**
	 * Generates a random number of horizontally adjacent rectangles.
	 * 
	 * @param numberOfRectangles The number of rectangles.
	 * @return An {@link Iterable} over the rectangles.
	 */
	public Collection<Rectangle> getRandomHorizontalyAdjacentRectangles(int numberOfRectangles);
	
	
	/**
	 * This methods computes (and generates) the minimum number of horizontal (i.e width > height) generating the 
	 * same Sky line as <code>rectangles</code>.
	 * @param rectangles An iterable over a set of horizontally adjacent rectangles.
	 * @return
	 */
	public Collection<Rectangle> getMinimumNumberOfHorizontalRectangles(Collection<Rectangle> rectangles);

}
