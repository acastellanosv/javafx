package com.acv.showroom.view;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.acv.showroom.texture.DynamicTextureNet;

import javafx.geometry.HPos;
import javafx.scene.DepthTest;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class MainView extends Pane{

    private double anchorX, anchorY, anchorAngleX, anchorAngleY;
	private MeshView meshView;
	private double pivotX, pivotY, pivotZ;
	private int width;
	private int height;
    
    public MainView(int width, int height) {
		this.width = width;
		this.height = height;
//		setDepthTest(DepthTest.ENABLE);
//		TriangleMesh volume = new GeneratedVolume(0, 200, 10, x->Math.sin(x.doubleValue()), x->Math.cos(x.doubleValue()));
//		TriangleMesh volume = new GeneratedVolume(0, 100, 10, f1 , f2);

	}

    private TriangleMesh generateShape(double shapeWidth, double shapeHeight, double shapeDepth, DynamicTextureNet imageNet) {
		double step = 2.0;
		BiFunction<Double, Double, Double> f1 = (w,x)->(w.doubleValue());
		BiFunction<Double, Double, Double> f2 = (w,x)->(w.doubleValue());
		TriangleMesh volume = new GeneratedShape(shapeWidth, shapeHeight, shapeDepth, step, f1, f2, imageNet);
		return volume;
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

	
	public void render(Color color) {
		PhongMaterial material = new PhongMaterial(color);
//		material.setDiffuseColor(color);
//		material.setSpecularColor(color);
		List<Region> textures = generateTextures();
		DynamicTextureNet imageNet = new DynamicTextureNet(textures);
//		ImageView imageView = new ImageView(imageNet.getImage());
//		this.getChildren().add(imageView);
		material.setDiffuseMap(imageNet.getImage());
		double shapeWidth = 200.0;
		double shapeHeight = 200.0;
		double shapeDepth = 200.0;

		TriangleMesh volume = generateShape(shapeWidth, shapeHeight, shapeDepth, imageNet);
		
//		TriangleMesh volume = getTwoFacesMesh((float)shapeWidth, (float)shapeHeight, (float)shapeDepth);
    
		meshView = new MeshView(volume);
		pivotX = shapeWidth/2;
		pivotY = shapeHeight/2;
		pivotZ = shapeDepth/2;
		meshView.setCullFace(CullFace.BACK);

		meshView.setMaterial(material);
//		rect.setRotationAxis(Rotate.Y_AXIS);
		meshView.setTranslateX((width/2)-pivotX);
		meshView.setTranslateY((height/2)-pivotY);
		// try commenting this line out to see what it's effect is . . .
		meshView.setDrawMode(DrawMode.FILL);
		this.getChildren().add(meshView);

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
