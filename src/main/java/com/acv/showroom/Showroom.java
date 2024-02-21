package com.acv.showroom;

import com.acv.showroom.view.MainView;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Showroom extends Application{

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	
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

	    MainView group = new MainView(WINDOW_WIDTH, WINDOW_HEIGHT);
        var label = new Label("Welcome to Showroom.");
        group.getChildren().add(label);
        group.render();

//		MainPanel group = new MainPanel(WINDOW_WIDTH,WINDOW_HEIGHT);
//      root.setRotationAxis(Rotate.X_AXIS);
//      root.setRotate(30);
//      root.setRotationAxis(Rotate.Y_AXIS);
//      root.setRotate(30);

		Scene scene = new Scene(group, WINDOW_WIDTH, WINDOW_HEIGHT);
//        scene.setFill(Color.SILVER);
		
		
        PerspectiveCamera camera = new PerspectiveCamera();
//      camera.setTranslateX(-(box.getWidth()) / 2);
//      camera.setTranslateY(-(box.getHeight()) / 2);
//      camera.setTranslateZ(-400);
        scene.setCamera(camera);

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

        
        //Add keyboard control.
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
          switch (event.getCode()) {
            case NUMPAD9:
            	group.zoom(100);
              break;
            case NUMPAD7:
            	group.zoom(-100);
              break;
            case NUMPAD8:
            	group.rotateByX(10);
              break;
            case NUMPAD2:
            	group.rotateByX(-10);
              break;
            case NUMPAD6:
            	group.rotateByY(10);
              break;
            case NUMPAD4:
            	group.rotateByY(-10);
              break;
          }
        });        
        
        stage.setTitle("Welcome to the Showroom");
        stage.setScene(scene);
        stage.show();
        
	}

    private PerspectiveCamera addCamera(Scene scene) {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(false);
        scene.setCamera(perspectiveCamera);
        return perspectiveCamera;
    }

}
