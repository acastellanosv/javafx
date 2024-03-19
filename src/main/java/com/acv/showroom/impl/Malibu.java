package com.acv.showroom.impl;

import java.net.URL;

import com.acv.showroom.model.Automobile;
import com.acv.showroom.model.Cabin;
import com.acv.showroom.model.Hood;
import com.acv.showroom.model.Trunk;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Malibu extends Automobile{

	private Color color;
	private static Box container = new Box(600, 150, 300);
	
	public Malibu(Color color) {
		super(Automobile.MAKE.CHEVROLET, "Malibu Chevelle", 79);
		this.color = color;
	}
	
	@Override
	public void stroke() {
		PhongMaterial paint = new PhongMaterial();
		paint.setDiffuseColor(color);
//		material.setSpecularColor(Color.DARKKHAKI);
//		container.setMaterial(material);
//		addComponent(container);
		//addCabin(paint);
	}
	
	


}
