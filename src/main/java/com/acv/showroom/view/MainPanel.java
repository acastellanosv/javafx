package com.acv.showroom.view;

import com.acv.showroom.impl.Malibu;
import com.acv.showroom.model.Automobile;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainPanel extends Pane{

	private Automobile auto;

	public MainPanel(int width, int height){
		super();
		this.setWidth(width);
		this.setHeight(height);
//		this.getChildren().add(getImage());

		auto = getAutomobile();
		Text title = auto.getTitle();
		title.translateXProperty().set(-(width/2)+title.getBoundsInParent().getWidth());
		title.translateYProperty().set(-(height/2)+title.getBoundsInParent().getHeight());
        this.getChildren().add(title);	

    }

    private Automobile getAutomobile() {
    	Malibu malibu = new Malibu(Color.WHEAT);
    	malibu.stroke();
    	return malibu;
    }
    
    /*
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
    */
    
}
