package com.acv.showroom.view;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainCanvas extends Canvas{

    private List<DrawComponent> drawComponents = new LinkedList<>();

	public MainCanvas(int width, int height) {
		super(width,height);
	}

	public void addDrawComponent(DrawComponent component) {
		this.drawComponents.add(component);
		this.draw();
	}
	
	public void draw() {
		GraphicsContext g = this.getGraphicsContext2D();
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (DrawComponent drawComponent : drawComponents) {
			drawComponent.draw(g);
		}
	}

}
