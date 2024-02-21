package com.acv.showroom.view;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;

public abstract class DrawComponent{
	
	protected Box container;
	private List<Shape3D> shapes = new LinkedList<Shape3D>();
	
	public DrawComponent(Box container) {
		this.container = container;
	}
	
	public abstract void construct(PhongMaterial paint);
	
	protected void addComponent(Shape3D shape) {
		this.shapes.add(shape);
	}
	
	public void stroke(Group group) {
		group.getChildren().addAll(shapes);
	}
	
	public abstract void draw(GraphicsContext g);

}
