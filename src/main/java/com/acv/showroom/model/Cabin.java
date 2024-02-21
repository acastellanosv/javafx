package com.acv.showroom.model;

import com.acv.showroom.view.DrawComponent;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Cabin extends DrawComponent {

	public Cabin(Box container) {
		super(container);
	}
	
	@Override
	public void construct(PhongMaterial material) {
		Box body = new Box((4)*(container.getWidth()/12), container.getHeight(), container.getDepth());
		body.setTranslateX(-(4)*(container.getWidth()/12));
		body.setMaterial(material);
		addComponent(body);
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}

}
