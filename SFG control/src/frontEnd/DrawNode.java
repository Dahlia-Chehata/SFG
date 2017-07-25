package frontEnd;

import java.awt.Color;


import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import backEnd.Node;

public class DrawNode extends Node {
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	private Color color;
	public DrawNode() {
		super();
		edges = new LinkedList<DrawEdge>();

		this.setHeight(55);
		this.setWidth(55);
	}
	
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition=xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition=yPosition;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color=color;
	}
	public void Draw(Graphics g) {

		g.drawOval(xPosition, yPosition, getWidth(), getHeight());
		Iterator<DrawEdge> iterator = edges.iterator();
		DrawEdge edge;
		while (iterator.hasNext()) {
			edge =  iterator.next();
			(edge).Draw(g);
		}
		g.setColor(color);
		g.fillOval(xPosition, yPosition, getWidth(), getHeight());
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width=width;
	}

	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height=height;
	}

}
