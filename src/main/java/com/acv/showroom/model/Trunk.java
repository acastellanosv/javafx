package com.acv.showroom.model;

import com.acv.showroom.view.DrawComponent;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Trunk extends DrawComponent {

	public Trunk(Box container) {
		super(container);
	}
	
	@Override
	public void construct(PhongMaterial material) {
		Box body = new Box((3)*(container.getWidth()/12), 2*(container.getHeight()/3), container.getDepth());
		body.setTranslateX((3)*(container.getWidth()/12));
		body.setTranslateY(container.getHeight()/6);
		body.setMaterial(material);
		addComponent(body);
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}

}
