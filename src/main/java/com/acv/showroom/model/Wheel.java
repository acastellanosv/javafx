package com.acv.showroom.model;

import com.acv.showroom.view.DrawComponent;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Wheel extends DrawComponent{

	public Wheel(Box container) {
		super(container);
	}
	
	@Override
	public void construct(PhongMaterial material) {
//		Box body = new Box(container.getWidth(), container.getHeight(), container.getDepth());
//		addComponent(body);
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}

}
