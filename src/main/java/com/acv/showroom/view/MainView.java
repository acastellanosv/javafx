package com.acv.showroom.view;

import java.util.function.BiFunction;

import com.acv.showroom.texture.DynamicTextureNet;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
//    	Shape3DRectangle shape3D = new Shape3DRectangle(200, 200, 200);
//		rect = new MeshView(shape3D);
//		setDepthTest(DepthTest.ENABLE);
//		TriangleMesh volume = new GeneratedVolume(0, 200, 10, x->Math.sin(x.doubleValue()), x->Math.cos(x.doubleValue()));
//		TriangleMesh volume = new GeneratedVolume(0, 100, 10, f1 , f2);

	}

    private TriangleMesh getTwoFacesMesh(float width, float height, float depth) {
    
    		TriangleMesh m = new TriangleMesh();
    		float[] thesePoints = {
                    0,  height, 0,
                    0, 0, 0,
                    width/2,  height, 0,
                    width/2, 0, 0,
                    width,  height, 0,
                    width, 0, 0
    			};
    		m.getPoints().addAll(thesePoints);
    				
            float[] texCoords = {
                    1, 1, // idx t0
                    1, 0, // idx t1
                    0, 1, // idx t2
                    0, 0  // idx t3
            };
    		m.getTexCoords().addAll(texCoords);
            int[] faces = {
            		
              	  //p1, p2, p3
              	  //front
            		1, 2, 0, 0, 2, 3,
            		2, 0, 3, 3, 1, 1
            		,
            		3, 2, 2, 0, 4, 3,
            		4, 0, 5, 3, 3, 1
              		
              };
            m.getFaces().addAll(faces);
    		return m ;
    }

    private TriangleMesh generateShape(double shapeWidth, double shapeHeight, double shapeDepth) {
		double step = 1.0;
		BiFunction<Double, Double, Double> f1 = (w,x)->(w.doubleValue());
		BiFunction<Double, Double, Double> f2 = (w,x)->(w.doubleValue());
		TriangleMesh volume = new GeneratedShape(shapeWidth, shapeHeight, shapeDepth, step, f1, f2);
		return volume;
    }
    
    public void render(Color color) {
		PhongMaterial material = new PhongMaterial(color);
//		material.setDiffuseColor(color);
//		material.setSpecularColor(color);
		DynamicTextureNet imageNet = new DynamicTextureNet("FRONT","BACK","TOP","BOTTOM","LEFT","RIGTH");
		imageNet.getTexturePoints(4,3);
		ImageView imageView = new ImageView(imageNet.getImage());
		this.getChildren().add(imageView);
		material.setDiffuseMap(imageNet.getImage());
		double shapeWidth = 200.0;
		double shapeHeight = 200.0;
		double shapeDepth = 200.0;

		TriangleMesh volume = generateShape(shapeWidth, shapeHeight, shapeDepth);
		
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
