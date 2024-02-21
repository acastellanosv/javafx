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
		addHood(paint);
		addTrunk(paint);
		super.stroke();
	}
	
	private void addHood(PhongMaterial paint) {
		Hood hood = new Hood(container);
		hood.construct(paint);
		super.addHood(hood);
	}

	private void addCabin(PhongMaterial paint) {
		Cabin cabin = new Cabin(container);
		cabin.construct(paint);
		super.addCabin(cabin);
	}

	private void addTrunk(PhongMaterial paint) {
		Trunk trunk = new Trunk(container);
		trunk.construct(paint);
		super.addTrunk(trunk);
	}

	private void drawContainer(GraphicsContext g) {
		g.setFill(Color.WHITESMOKE);
		g.setStroke(Color.WHITESMOKE);
		g.setLineWidth(1);
		g.strokeRect(200,200,200,200);
	}
	
	public void draw(GraphicsContext g) {
		URL url = this.getClass().getClassLoader().getResource("malibu_front.jpg");
		Image image = new Image(url.toString(),1000,500,true,true);
		g.drawImage(image, (1000-image.getWidth())/2, 50);
		drawContainer(g);
		//super.draw(g);
	}


}
