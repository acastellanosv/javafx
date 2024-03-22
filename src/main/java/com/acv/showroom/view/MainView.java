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

	public void render(Color color) {
		PhongMaterial material = new PhongMaterial(color);
//		material.setDiffuseColor(color);
//		material.setSpecularColor(color);
		List<Region> textures = generateTextures();
		DynamicTextureNet imageNet = new DynamicTextureNet(color, textures);
//		ImageView imageView = new ImageView(imageNet.getImage());
//		this.getChildren().add(imageView);
		material.setDiffuseMap(imageNet.getImage());
		double topWidth = 200.0;
		double topHeight = 200.0;
		double bottomWidth = 200.0;
		double bottomHeight = 200.0;
		double shapeDepth = 200.0;
		double step = 2.0;

		TriangleMesh volume = generateShape(topWidth, topHeight, bottomWidth, bottomHeight, shapeDepth, step, imageNet);
		
//		TriangleMesh volume = getTwoFacesMesh((float)shapeWidth, (float)shapeHeight, (float)shapeDepth);
    
		meshView = new MeshView(volume);
		pivotX = 0;
		pivotY = 0;
		pivotZ = -shapeDepth/2;
		meshView.setCullFace(CullFace.BACK);

		meshView.setMaterial(material);
//		rect.setRotationAxis(Rotate.Y_AXIS);
		meshView.setTranslateX((width/2)-pivotX);
		meshView.setTranslateY((height/2)-pivotY);
		// try commenting this line out to see what it's effect is . . .
		meshView.setDrawMode(DrawMode.FILL);
		this.getChildren().add(meshView);

	}

	private TriangleMesh generateShape(double topWidth, double topHeight, double bottomWidth, double bottomHeight 
			, double shapeDepth, double depthStep, DynamicTextureNet imageNet) {
		BiFunction<Double, Double, Double> wtf = (w,x)->(w);
//		BiFunction<Double, Double, Double> htf = (h,x)->(h);
		BiFunction<Double, Double, Double> wbf = (w,x)->(w);
		BiFunction<Double, Double, Double> hbf = (h,x)->(h);
//		BiFunction<Double, Double, Double> wtf = (w,x)->(w+(100*(x+1)));
		BiFunction<Double, Double, Double> htf = (h,x)->(h+(100*(x+1)));
//		BiFunction<Double, Double, Double> wbf = (w,x)->(w+(100*(x+1)));
//		BiFunction<Double, Double, Double> hbf = (h,x)->(h+(100*(x+1)));
		TriangleMesh volume = new GeneratedShape(topWidth, topHeight, bottomWidth, bottomHeight
				, shapeDepth, depthStep, wtf, htf, wbf, hbf, imageNet);
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
