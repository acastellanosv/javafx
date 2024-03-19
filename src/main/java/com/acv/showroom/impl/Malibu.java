package com.acv.showroom.impl;

import java.util.List;

import com.acv.showroom.model.Automobile;
import com.acv.showroom.model.Cabin;
import com.acv.showroom.texture.DynamicTextureNet;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Malibu extends Automobile{

	private DynamicTextureNet imageNet;
	
	public Malibu(Color color) {
		super(Automobile.MAKE.CHEVROLET, "Malibu Chevelle", 79);
		super.setColor(color);
		List<Region> textures = generateTextures();
		imageNet = new DynamicTextureNet(textures);
	}
	
	@Override
	public void addCabin() {
		Cabin cabin = new Cabin(getTotalPoints(), getColor(), imageNet);
		addComponent(cabin);
	}
	
	


}
