/**
 * 
 */
package com.odobo.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.odobo.domain.Rectangle;
import com.odobo.services.IRectanglesService;

/**
 * Implementation of {@link IRectanglesService}.
 * 
 * @author reiern70
 *
 */
public class RectanglesService implements IRectanglesService {

	private static final Random RANDOM = new Random();
	
	/**
	 * Constructor.
	 */
	public RectanglesService() {
	}

	/* (non-Javadoc)
	 * @see com.odobo.services.IRectangleServices#getRandomHorizontalyAdjacentRectables(int)
	 */
	@Override
	public Collection<Rectangle> getRandomHorizontalyAdjacentRectangles(
			int numberOfRectangles) {
		List<Rectangle> rectangles = new ArrayList<Rectangle>();		
		int x = 0;
		for(int i=0; i < numberOfRectangles; i++) {
			int width = RANDOM.nextInt(100)+1; // make sure width is positive
			int height = RANDOM.nextInt(100)+1;// make sure height is positive
			rectangles.add(new Rectangle(x, 0, width, height));
			x += width;			
		}
		return rectangles;
	}

	/* (non-Javadoc)
	 * @see com.odobo.services.IRectangleServices#getMinimumNumberOfHorizontalRectangles(java.lang.Iterable)
	 */
	@Override
	public Collection<Rectangle> getMinimumNumberOfHorizontalRectangles(
			Collection<Rectangle> rectangles) {
		List<Rectangle> hrectangles = new ArrayList<Rectangle>();
		ArrayList<Rectangle> sortedByHeight = new ArrayList<Rectangle>(rectangles);
		Collections.sort(sortedByHeight, new Comparator<Rectangle>() {
			
			@Override
			public int compare(Rectangle o1, Rectangle o2) {
				return new Integer(o1.getHeight()).compareTo(o2.getHeight());
			}
		});
		Rectangle previous = null;
		for(Rectangle rectangle: sortedByHeight) {
			hrectangles.add(computeRectangle(rectangle, previous, rectangles));
			previous = rectangle;	
		}
		return hrectangles;
	}
	
	private Rectangle computeRectangle(Rectangle hrectangle, Rectangle previous, Collection<Rectangle> rectangles) {
		int x = 0;
		int width = 0;
		// find x coordinate of horizontal
		for(Rectangle rectangle: rectangles) {
			if(rectangle.getHeight() >= hrectangle.getHeight()) {
				x = rectangle.getX();
				break;
			}			
		}		
		for(Rectangle rectangle: rectangles) {
			if(rectangle.getHeight() >= hrectangle.getHeight()) {
				width += rectangle.getWidth();
			}
		}
		int pHeight = previous!= null?previous.getHeight():0;
		return new Rectangle(x, previous!= null?(previous.getY()+previous.getHeight()+1):0, width, hrectangle.getHeight()-pHeight);
		
	}

	@Override
	public String getRectanglesAsJSON(
			Collection<Rectangle> rectangles) {
		//TODO: for real application use a JSON producing library.
		StringBuilder builder = new StringBuilder();
		builder.append("{");	
		Rectangle.writeProperty(builder, "numRects", rectangles.size());
		builder.append(", \"sourceRectangles\": [");
		for(Rectangle rectangle: rectangles) {
			builder.append(rectangle.toJSON());
			builder.append(",");
		}
		builder.append("]");
		builder.append("}");
		return builder.toString();
	}

}
