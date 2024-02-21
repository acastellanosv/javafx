package com.acv.showroom.view;

import java.net.URL;

import com.acv.showroom.impl.Malibu;
import com.acv.showroom.model.Automobile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainPanel extends Pane{

	private Automobile auto;

	public MainPanel(int width, int height){
		super();
		this.setWidth(width);
		this.setHeight(height);
		this.getChildren().add(getImage());

		auto = getAutomobile();
		Text title = auto.getTitle();
		title.translateXProperty().set(-(width/2)+title.getBoundsInParent().getWidth());
		title.translateYProperty().set(-(height/2)+title.getBoundsInParent().getHeight());
        auto.translateXProperty().set(width / 2);
        auto.translateYProperty().set(height / 2);
        auto.translateZProperty().set(0);
        this.getChildren().add(title);	
        this.getChildren().add(auto);	

    }

    private Automobile getAutomobile() {
    	Malibu malibu = new Malibu(Color.WHEAT);
    	malibu.stroke();
    	return malibu;
    }
    
    private ImageView getImage() {
		URL url = this.getClass().getClassLoader().getResource("malibu_front.jpg");
		Image image = new Image(url.toString(),this.getWidth(),this.getHeight(),true,true);
		ImageView imageView = new ImageView();
	      imageView.setImage(image);
	      imageView.setX(0);
	      imageView.setY(0);
	      imageView.setFitWidth(this.getWidth());
	      imageView.setPreserveRatio(true);
	      return imageView;
    }
    
	public void rotateByX(int ang) {
		auto.rotateByX(ang);
	}

	public void rotateByY(int ang) {
		auto.rotateByY(ang);
	}

	public void zoom(int z) {
      	auto.translateZProperty().set(auto.getTranslateZ() + z);
		
	}
	
}
