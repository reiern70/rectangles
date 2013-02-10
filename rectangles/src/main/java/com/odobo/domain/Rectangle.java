/**
 * 
 */
package com.odobo.domain;

import java.io.Serializable;

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
	
}
