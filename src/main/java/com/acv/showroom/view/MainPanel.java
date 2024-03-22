package com.acv.showroom.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.acv.showroom.impl.Malibu;
import com.acv.showroom.model.Automobile;
import com.acv.showroom.texture.DynamicTextureNet;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.text.Text;

public class MainPanel extends SmartView{


	public MainPanel(int width, int height, int depth){
		super(width, height, depth);
		this.setWidth(width);
		this.setHeight(height);
//		this.getChildren().add(getImage());

		Automobile auto = getAutomobile();
		Text title = auto.getTitle();
		title.translateXProperty().set(-(width/2)+title.getBoundsInParent().getWidth());
		title.translateYProperty().set(-(height/2)+title.getBoundsInParent().getHeight());
        this.getChildren().add(title);	
		MeshView meshView = super.getMeshView(auto);

		PhongMaterial material = new PhongMaterial(auto.getColor());
		List<Region> textures = generateTextures();
		DynamicTextureNet imageNet = new DynamicTextureNet(Color.BEIGE,textures);
//		ImageView imageView = new ImageView(imageNet.getImage());
//		this.getChildren().add(imageView);
		material.setDiffuseMap(imageNet.getImage());
		meshView.setMaterial(material);
		this.getChildren().add(meshView);
    }

    private Automobile getAutomobile() {
    	Malibu malibu = new Malibu(Color.WHEAT);
    	malibu.stroke();
    	return malibu;
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
