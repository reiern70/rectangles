/**
 * 
 */
package com.odobo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents an immutable rectangle with {@link Integer} sides.
 * 
 * @author (reiern70@gmail.com)
 *
 */
public class Rectangle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Coordinates of the vertices.
	private final int x;
	
	private final int y;
	
	private final int width;	

	private final int height;

	/**
	 * Constructor.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rectangle(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		if(width < 0) {
			throw new IllegalArgumentException("Width has to be a positive number!");
		}
		this.width = width;
		if(height < 0) {
			throw new IllegalArgumentException("Height has to be a positive number!");
		}
		this.height = height;
	}

	/**
	 * @return Returns JSON representation of {@link Rectangle}
	 */
	public String toJSON() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		writeProperty(builder, "x", x);
		builder.append(", ");
		writeProperty(builder, "y", y);
		builder.append(", ");
		writeProperty(builder, "width", width);
		builder.append(", ");
		writeProperty(builder, "height", height);
		builder.append("}");
		return builder.toString();
	}
	
	public static void writeProperty(StringBuilder builder, String name, int value) {
		builder.append("\"");
		builder.append(name);
		builder.append("\":");
		builder.append(value);		
	}
	
	/**
	 * If the rectangle contains another.
	 * @param other
	 * @return
	 */
	public boolean contains(Rectangle other) {
		return other.getX() >= getX() && other.getY() >= getY() 
				&& ((other.getX() + other.getWidth()) <= (getX() + getWidth())) 
				&& ((other.getY() + other.getHeight()) <= (getY() + getHeight())); 
	}
	
	
	/**
	 * Returns the intersection of a rectangle with a List of rectangles.
	 * 
	 * @param rectangles
	 * @return 
	 */
	public List<Rectangle> intersectionWith(Collection<Rectangle> rectangles) {
		List<Rectangle> intersections = new ArrayList<Rectangle>();
		Rectangle current = null;
		for(Rectangle rectangle: rectangles) {
			Rectangle intersection = intersect(rectangle);
			if(intersection != null && intersection.getHeight() != 0) {
				if(current == null) {
					current = intersection;
				} else {
					Rectangle union = current.union(intersection);
					if(union != null) {
						current = union;
					} else {
						intersections.add(current);
						current = intersection;
					}
				}			
			} 
		}
		if(current != null) {
			intersections.add(current);
		}
		return intersections;
	}
	
	/**
	 * Union of consecutive rectangles.
	 * @param rectangle
	 * @return
	 */
	public Rectangle union(Rectangle rectangle) {
		if(rectangle.getY() == getY() && (getX()+getWidth()) == rectangle.getX()) {
			return new Rectangle(getX(), getY(), getWidth()+rectangle.getWidth(), getHeight());
		}
		return null;
	}
	
	/**
	 * 
	 * @param rectangle
	 * @return
	 */
	public Rectangle intersect(Rectangle rectangle) {
		int x = Math.max(getX(), rectangle.getX());
		int y = Math.max(getY(), rectangle.getY());
		int top = Math.min(getY()+getHeight(), rectangle.getY()+rectangle.getHeight());
		if(x <= getX() + getWidth() && (top > y)) {
			return new Rectangle(x, y, rectangle.getWidth(), top-y);
		}
		return null;
	}
	
	@Override
	public String toString() {
		return toJSON();
	}
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
