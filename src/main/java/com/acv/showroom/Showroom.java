package com.acv.showroom;

import com.acv.showroom.view.MainPanel;
import com.acv.showroom.view.MainView;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Showroom extends Application{

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	
    private PerspectiveCamera camera;
    private final double sceneWidth = 600;
    private final double sceneHeight = 600;
    private double cameraDistance = 5000;

    private double scenex, sceney, scenez = 0;
    private double fixedXAngle, fixedYAngle, fixedZAngle = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);    
    private final DoubleProperty angleZ = new SimpleDoubleProperty(0);    

    //Add a Mouse Handler for Rotations
    Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
    Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
    Rotate zRotate = new Rotate(0, Rotate.Z_AXIS);

    
    public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

	   boolean is3DSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
        if(!is3DSupported) {
           System.out.println("Sorry, 3D is not supported in JavaFX on this platform.");
           return;
        }

        
//        Group sceneRoot = new Group();
//        Scene scene = new Scene(sceneRoot, sceneWidth, sceneHeight, true, SceneAntialiasing.BALANCED);
//        scene.setFill(Color.BLACK);
//        camera = new PerspectiveCamera(true);        
//        camera.setNearClip(0.1);
//        camera.setFarClip(10000.0);
//        camera.setTranslateZ(-1000);
//        scene.setCamera(camera);
        
//	    MainView group = new MainView(WINDOW_WIDTH, WINDOW_HEIGHT);
//	    group.render(Color.GOLDENROD);
	    MainPanel group = new MainPanel(WINDOW_WIDTH, WINDOW_HEIGHT);
//        group.getChildren().add(label);
	    
//	    var label = new Label("Welcome to Showroom.");

//		MainPanel group = new MainPanel(WINDOW_WIDTH,WINDOW_HEIGHT);
//      root.setRotationAxis(Rotate.X_AXIS);
//      root.setRotate(30);
//      root.setRotationAxis(Rotate.Y_AXIS);
//      root.setRotate(30);
        
		Scene scene = new Scene(group, WINDOW_WIDTH, WINDOW_HEIGHT, false, SceneAntialiasing.BALANCED);
//		Scene scene = new Scene(group, WINDOW_WIDTH, WINDOW_HEIGHT);
		
//		addCamera(scene);

	    /*
        //Add mouse control
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	group.onMousePressed(event);
            }
        });
 
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                group.onMouseDragged(event);
            }
        });
        */
		
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
              case PLUS:
              	group.zoom(100);
                break;
              case MINUS:
              	group.zoom(-100);
                break;
              case RIGHT:
              	group.rotateByY(10);
                break;
              case LEFT:
              	group.rotateByY(-10);
                break;
              case UP:
              	group.rotateByX(10);
                break;
              case DOWN:
              	group.rotateByX(-10);
                break;
              case PAGE_UP:
              	group.rotateByZ(10);
                break;
              case PAGE_DOWN:
              	group.rotateByZ(-10);
                break;
            }
          });        


	    //addMouseActions(group);
	    //addKeyboardActions(stage, group);
        stage.setTitle("Welcome to the Showroom");
        stage.setScene(scene);
        stage.show();
        
	}

    private PerspectiveCamera addCamera(Scene scene) {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(false);
//    camera.setTranslateX(-(box.getWidth()) / 2);
//    camera.setTranslateY(-(box.getHeight()) / 2);
//    camera.setTranslateZ(-400);
        scene.setCamera(perspectiveCamera);
        return perspectiveCamera;
    }

    
    private void addMouseActions(Pane group) {
    	 Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
         Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
         Rotate zRotate = new Rotate(0, Rotate.Z_AXIS);
         group.getTransforms().addAll(xRotate, yRotate, zRotate);
         xRotate.angleProperty().bind(angleX);
         yRotate.angleProperty().bind(angleY);
         zRotate.angleProperty().bind(angleZ);
         
         group.setOnMousePressed(event -> {
             scenex = event.getSceneX();
             sceney = event.getSceneY();
             fixedXAngle = angleX.get();
             fixedYAngle = angleY.get();
             if(event.isMiddleButtonDown()) {
                 scenez = event.getSceneX();
                 fixedZAngle = angleZ.get();
             }
             
         });
         
         group.setOnMouseDragged(event -> {
             if(event.isMiddleButtonDown()) 
                 angleZ.set(fixedZAngle - (scenez - event.getSceneY()));
             else
                 angleX.set(fixedXAngle - (scenex - event.getSceneY()));
             angleY.set(fixedYAngle + sceney - event.getSceneX());
         });     
    }
    
    private void addKeyboardActions(Stage stage, Pane pane) {
        //Add keyboard control.
    	
    	stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
            case PLUS:
              	pane.translateZProperty().set(pane.getTranslateZ() + 100);
              break;
            case MINUS:
              	pane.translateZProperty().set(pane.getTranslateZ() - 100);
              break;
            case RIGHT:
                angleY.set(fixedYAngle+=10);
              break;
            case LEFT:
                angleY.set(fixedYAngle-=10);
              break;
            case UP:
                angleX.set(fixedYAngle+=10);
              break;
            case DOWN:
                angleX.set(fixedYAngle-=10);
              break;
            case PAGE_UP:
                angleZ.set(fixedZAngle+=10);
              break;
            case PAGE_DOWN:
                angleZ.set(fixedZAngle+=10);
              break;
          }
            /*
          double change = 10.0;
            //Add shift modifier to simulate "Running Speed"
            if(event.isShiftDown()) { change = 50.0; }
            //What key did the user press?
            KeyCode keycode = event.getCode();
            //Step 2c: Add Zoom controls
            if(keycode == KeyCode.W) { camera.setTranslateZ(camera.getTranslateZ() + change); }
            if(keycode == KeyCode.S) { camera.setTranslateZ(camera.getTranslateZ() - change); }
            //Step 2d:  Add Strafe controls
            if(keycode == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change); }
            if(keycode == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change); }
        */     

        });   
    	
    }
    
    
}
