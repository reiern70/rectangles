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
import java.util.Set;
import java.util.TreeSet;

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
				if(o1.getHeight() != o2.getHeight()) {
					return new Integer(o1.getHeight()).compareTo(o2.getHeight());
				} else {
					return new Integer(o1.getX()).compareTo(o2.getX());
				}
			}
		});
		
		Collection<Integer> heights = getHeights(sortedByHeight);
		int width = getMaxWidth(sortedByHeight);
		int y = 0;
		for(Integer height: heights) {
			Rectangle hrectange = new Rectangle(0, y, width, height-y);			
			Collection<Rectangle> intersection = hrectange.intersectionWith(rectangles);
			hrectangles.addAll(intersection);
			y = height;
		}
		return hrectangles;
	}
	
	/**
	 * 
	 * @param rectangles
	 * @return
	 */
	private Collection<Integer> getHeights(Iterable<Rectangle> rectangles) {
		Set<Integer> integers = new TreeSet<Integer>();
		for(Rectangle rectangle: rectangles) {
			integers.add(rectangle.getHeight());
		}
		return integers;
	}
	
	/**
	 * 
	 * @param rectangles
	 * @return
	 */
	private int getMaxWidth(Iterable<Rectangle> rectangles) {
		int width = 0;
		for(Rectangle rectangle: rectangles) {
			width += rectangle.getWidth();
		}
		return width;
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
