package com.acv.showroom.model;

import java.util.LinkedList;
import java.util.List;

import com.acv.showroom.view.DrawComponent;

import javafx.scene.Group;
import javafx.scene.shape.Shape3D;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public abstract class Automobile extends Group{

	private Rotate r;
	private Transform t = new Rotate();
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
		components.forEach(c->c.stroke(this));
	}
	
	public void addComponent(Shape3D shape) {
		this.getChildren().add(shape);
	}
	
	public void addHood(Hood hood) {
		components.add(hood);
	}
	
	public void addCabin(Cabin cabin) {
		components.add(cabin);
	}
	
	public void addTrunk(Trunk trunk) {
		components.add(trunk);
	}
	
	public Text getTitle(){
		Text text = new Text();
		text.setText(new StringBuilder().append(this.make.name()).append(" ").append(model).append(" ").append(year).toString());
		return text;
	}
	

	public void rotateByX(int ang) {
		r = new Rotate(ang, Rotate.X_AXIS);
		t = t.createConcatenation(r);
		this.getTransforms().clear();
		this.getTransforms().addAll(t);
	}

	public void rotateByY(int ang) {
		r = new Rotate(ang, Rotate.Y_AXIS);
		t = t.createConcatenation(r);
		this.getTransforms().clear();
		this.getTransforms().addAll(t);
	}

}
