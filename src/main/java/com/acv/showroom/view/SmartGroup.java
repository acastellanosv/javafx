package com.acv.showroom.view;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

public class SmartGroup extends Group {

    private double anchorX, anchorY, anchorAngleX, anchorAngleY;
	private double pivotX, pivotY, pivotZ;
	private int width;
	private int height;
	private MeshView meshView;

    public SmartGroup(int width, int height) {
		this.width = width;
		this.height = height;
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