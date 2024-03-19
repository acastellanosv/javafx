package com.acv.showroom.model;

import java.util.LinkedList;
import java.util.List;

import com.acv.showroom.texture.DynamicTextureNet;
import com.acv.showroom.view.DrawComponent;

import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Text;

public abstract class Automobile extends TriangleMesh{

	private int points = 0;
	private DynamicTextureNet texture;
	private List<DrawComponent> components = new LinkedList<DrawComponent>();

	public enum MAKE {CHEVROLET, FORD, TOYOTA, HONDA};
	
	private MAKE make;
	private String model;
	private int year;

	public Automobile(MAKE make, String model, int year) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		
	}
	
	public void stroke() {
	}
	
	public void addComponent(DrawComponent shape) {
		this.components.add(shape);
	}
	
	public Text getTitle(){
		Text text = new Text();
		text.setText(new StringBuilder().append(this.make.name()).append(" ").append(model).append(" ").append(year).toString());
		return text;
	}
	
}
