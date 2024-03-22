package com.acv.showroom.view;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.AmbientLight;
import javafx.scene.PointLight;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class SmartView extends Pane {

    private double anchorX, anchorY;
	private double pivotX, pivotY, pivotZ;
	private int width;
	private int height;
	private MeshView meshView;

    public SmartView(int width, int height) {
		this.width = width;
		this.height = height;
    	this.meshView = new MeshView();
		pivotX = 0;
		pivotY = 0;
		meshView.setCullFace(CullFace.BACK);
		meshView.setTranslateX((width/2)-pivotX);
		meshView.setTranslateY((height/2)-pivotY);
		meshView.setDrawMode(DrawMode.FILL);
//		meshView.setDrawMode(DrawMode.LINE);
		
//		this.getChildren().add(new AmbientLight(Color.rgb(30, 30, 30)));
//		this.getChildren().add(createPointLight(this.width / 3d, this.height / 13d));
//		this.getChildren().add(createPointLight(2*(this.width / 3d), this.height / 13d));

    }
    
	private PointLight createPointLight(double x, double y) {
		PointLight light = new PointLight(Color.WHITE);
		light.setTranslateX( x );
		light.setTranslateY( y );
		light.setTranslateZ(-this.width);

		return light;
	}
	 
	protected MeshView getMeshView(TriangleMesh triangleMesh) {
    	this.meshView.setMesh(triangleMesh);
    	return this.meshView;
    }
    
    public void setPivotZ(float depth) {
		pivotZ = -depth/2;
    }
    
    public void rotateAroundYAxis() {
        RotateTransition rotate = new RotateTransition(Duration.seconds(4.5), this.meshView);
        rotate.setFromAngle(0);
        rotate.setToAngle(360);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();
    }
    
	public Rotate rotateByX(double ang) {
		Rotate r = new Rotate(ang, pivotX, pivotY, pivotZ, Rotate.X_AXIS);
		//rect.getTransforms().clear();
		meshView.getTransforms().add(r);
		return r;
	}

	public Rotate rotateByY(double ang) {
		Rotate r = new Rotate(ang, pivotX, pivotY, pivotZ, Rotate.Y_AXIS);
		//rect.getTransforms().clear();
		meshView.getTransforms().add(r);
		return r;
	}

	public Rotate rotateByZ(double ang) {
		Rotate r = new Rotate(ang, pivotX, pivotY, pivotZ, Rotate.Z_AXIS);
		//rect.getTransforms().clear();
		meshView.getTransforms().add(r);
		return r;
	}

	public void zoom(int z) {
      	meshView.translateZProperty().set(meshView.getTranslateZ() + z);
	}
	
	public void onMousePressed(MouseEvent event) {
		anchorX = event.getSceneX();
		anchorY = event.getSceneY();
	}

	public void onMouseDragged(MouseEvent event) {
		double xDif = (anchorX - event.getSceneX());
		double yDif = (anchorY - event.getSceneY());
		Rotate rx = rotateByY(xDif<-50?-5:xDif>50?5:0);
		Rotate ry = rotateByX(yDif<-50?-5:yDif>50?5:0);
		//rect.getTransforms().clear();
//		rect.getTransforms().addAll(rx.createConcatenation(ry));
	}

}