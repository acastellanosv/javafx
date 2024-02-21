package com.acv.showroom.view;

import java.util.function.BiFunction;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class MainView extends Pane{

    double anchorX, anchorY, anchorAngleX, anchorAngleY;
	final MeshView rect;
	double pivotX, pivotY, pivotZ;
	private int width;
	private int height;
    
    public MainView(int width, int height) {
		this.width = width;
		this.height = height;
//    	Shape3DRectangle shape3D = new Shape3DRectangle(200, 200, 200);
//		rect = new MeshView(shape3D);
		
//		TriangleMesh volume = new GeneratedVolume(0, 200, 10, x->Math.sin(x.doubleValue()), x->Math.cos(x.doubleValue()));
		BiFunction<Double, Double, Double> f1 = (w,x)->(w.doubleValue());
		BiFunction<Double, Double, Double> f2 = (w,x)->(w.doubleValue());
//		TriangleMesh volume = new GeneratedVolume(0, 100, 10, f1 , f2);
		double shapeWidth = 200.0;
		double shapeHeight = 200.0;
		double shapeDepth = 400.0;
		double step = 10.0;
		TriangleMesh volume = new GeneratedShape(shapeWidth, shapeHeight, shapeDepth, step, f1, f2);
		rect = new MeshView(volume);
		pivotX = shapeWidth/2;
		pivotY = shapeHeight/2;
		pivotZ = shapeDepth/2;
		
	}

	public void render() {

		rect.setMaterial(new PhongMaterial(Color.GREEN));
//		rect.setRotationAxis(Rotate.Y_AXIS);
		rect.setTranslateX((width/2)-pivotX);
		rect.setTranslateY((height/2)-pivotY);
		// try commenting this line out to see what it's effect is . . .
//		rect.setCullFace(CullFace.NONE);



		this.getChildren().add(rect);

	}

	public Rotate rotateByX(double ang) {
		Rotate r = new Rotate(ang, pivotX, pivotY, pivotZ, Rotate.X_AXIS);
		return r;
	}

	public Rotate rotateByY(double ang) {
		Rotate r = new Rotate(ang, pivotX, pivotY, pivotZ, Rotate.Y_AXIS);
		return r;
	}

	public void zoom(int z) {
      	rect.translateZProperty().set(rect.getTranslateZ() + z);
	}
	
	public void onMousePressed(MouseEvent event) {
		anchorX = event.getSceneX();
		anchorY = event.getSceneY();
	}

	public void onMouseDragged(MouseEvent event) {
		Rotate rx = rotateByY((anchorX - event.getSceneX())/10);
		Rotate ry = rotateByX((anchorY - event.getSceneY())/10);
		//rect.getTransforms().clear();
		rect.getTransforms().addAll(rx.createConcatenation(ry));
		
	}


}
