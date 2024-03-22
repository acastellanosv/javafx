package com.acv.showroom.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.acv.showroom.model.Automobile;
import com.acv.showroom.model.Cabin;
import com.acv.showroom.model.Hood;
import com.acv.showroom.model.Trunk;
import com.acv.showroom.texture.DynamicTextureNet;
import com.acv.showroom.view.DrawComponent;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Malibu extends Automobile{

	private DynamicTextureNet imageNet;
	
	public Malibu(Color color) {
		super(Automobile.MAKE.CHEVROLET, "Malibu Chevelle", 79);
		super.setColor(color);
		List<Region> textures = generateTextures();
		imageNet = new DynamicTextureNet(color, textures);
	}

	public List<Region> generateTextures() {
		String labels[] = {"FN","RG","BK","LF","BM","TP"};
		List<Region> textures = Arrays.stream(labels)
				.map(text->new Label(text))
				.peek(label->GridPane.setHalignment(label, HPos.CENTER))
				.collect(Collectors.toList());
//		label1.setRotate(90);
		return textures;
	}

	@Override
	public List<DrawComponent> getHood() {
		Hood hood = new Hood(imageNet);
		return hood.getAutoParts();
	}

	@Override
	public List<DrawComponent> getCabin() {
		Cabin cabin = new Cabin(imageNet);
		return cabin.getAutoParts();
	}

	@Override
	public List<DrawComponent> getTrunk() {
		Trunk trunk = new Trunk(imageNet);
		return trunk.getAutoParts();
	}


}
