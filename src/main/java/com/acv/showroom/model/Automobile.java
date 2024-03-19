package com.acv.showroom.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.acv.showroom.texture.DynamicTextureNet;
import com.acv.showroom.view.DrawComponent;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Text;

public abstract class Automobile extends TriangleMesh{

	private int totalPoints = 0;
	private Color color;
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
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public abstract void addCabin();
	
	public void stroke() {
		addCabin();
		this.components.forEach(t->{
			this.getPoints().addAll(t.getPoints());
//			points = this.getPoints().size()/3;
			this.getTexCoords().addAll(t.getTexCoords());
			this.getFaces().addAll(t.getFaces());
		});
	}
	
	public void addComponent(DrawComponent shape) {
		this.totalPoints += shape.getPoints().size()/3;
		this.components.add(shape);
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

	public int getTotalPoints() {
		return totalPoints;
	}
	
	public Text getTitle(){
		Text text = new Text();
		text.setText(new StringBuilder().append(this.make.name()).append(" ").append(model).append(" ").append(year).toString());
		return text;
	}
	
}
