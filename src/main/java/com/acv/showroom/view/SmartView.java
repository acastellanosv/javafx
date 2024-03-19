package com.acv.showroom.view;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class SmartView extends Pane {

    private double anchorX, anchorY;
	private double pivotX, pivotY, pivotZ;
	private int width;
	private int height;
	private MeshView meshView;

    public SmartView(int width, int height, int depth) {
		this.width = width;
		this.height = height;
    	this.meshView = new MeshView();
		pivotX = 0;
		pivotY = 0;
		pivotZ = -depth/2;
		meshView.setCullFace(CullFace.BACK);
		meshView.setTranslateX((width/2)-pivotX);
		meshView.setTranslateY((height/2)-pivotY);
		meshView.setDrawMode(DrawMode.FILL);
    }
    
    protected MeshView getMeshView(TriangleMesh triangleMesh) {
    	this.meshView.setMesh(triangleMesh);
    	return this.meshView;
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