package com.acv.showroom.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.acv.showroom.view.DrawComponent;

import javafx.scene.paint.Color;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Text;

public abstract class Automobile extends TriangleMesh implements AutoPart{

	private int pointsOffset = 0;
	private float depthOffset = 0.0f;
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
	
	public float getDepthOffset() {
		return depthOffset;
	}
	
	public abstract List<DrawComponent> getHood();
	public abstract List<DrawComponent> getCabin();
	public abstract List<DrawComponent> getTrunk();

	@Override
	public List<DrawComponent> getAutoParts() {
		List<DrawComponent> autoParts = new ArrayList<DrawComponent>();
		autoParts.addAll(getHood());
		autoParts.addAll(getCabin());
		autoParts.addAll(getTrunk());
		return autoParts;
	}


	public void stroke() {
		addComponents(getAutoParts());
		this.components.forEach(shape->{
			shape.stroke(pointsOffset, depthOffset);
			this.getPoints().addAll(shape.getPoints());
			this.getTexCoords().addAll(shape.getTexCoords());
			this.getFaces().addAll(shape.getFaces());
			this.getFaceSmoothingGroups().addAll(shape.getFaceSmoothingGroups());
			this.pointsOffset += shape.getPoints().size()/3;
			this.depthOffset += shape.getDepth();
		});
	}
	
	private void addComponents(List<DrawComponent> shapes) {
		shapes.forEach(shape->{
			addComponent(shape);
		});
	}

	private void addComponent(DrawComponent shape) {
		this.components.add(shape);
	}

	public Text getTitle(){
		Text text = new Text();
		text.setText(new StringBuilder().append(this.make.name()).append(" ").append(model).append(" ").append(year).toString());
		return text;
	}
	
}
