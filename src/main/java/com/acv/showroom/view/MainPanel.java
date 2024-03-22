package com.acv.showroom.view;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.acv.showroom.impl.Malibu;
import com.acv.showroom.model.Automobile;
import com.acv.showroom.texture.DynamicTextureNet;

import javafx.geometry.HPos;
import javafx.scene.AmbientLight;
import javafx.scene.PointLight;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainPanel extends SmartView{


	public MainPanel(int width, int height){
		super(width, height);
		this.setWidth(width);
		this.setHeight(height);
		
		setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		
		//this.getChildren().add(getImage());
		
		Automobile auto = getAutomobile();
		Text title = auto.getTitle();
		title.setFill(auto.getColor());
		title.setFont(Font.font(25));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setWrappingWidth(width);
		title.translateYProperty().set(25);
        this.getChildren().add(title);
		MeshView meshView = super.getMeshView(auto);
		super.setPivotZ(auto.getDepthOffset());
		PhongMaterial material = new PhongMaterial(auto.getColor());
		material.setDiffuseColor(auto.getColor());
//		material.setSpecularColor(auto.getColor());
		List<Region> textures = generateTextures();
		DynamicTextureNet imageNet = new DynamicTextureNet(Color.BEIGE,textures);
//		ImageView imageView = new ImageView(imageNet.getImage());
//		this.getChildren().add(imageView);
		material.setDiffuseMap(imageNet.getImage());
		meshView.setMaterial(material);
		this.getChildren().add(meshView);
//		rotateAroundYAxis();
    }

    private Automobile getAutomobile() {
    	Malibu malibu = new Malibu(Color.WHEAT);
    	malibu.stroke();
    	return malibu;
    }
    
	public List<Region> generateTextures() {
		String labels[] = {"1","2","3","4","5","6"};
		List<Region> textures = Arrays.stream(labels)
				.map(text->new Label(text))
				.peek(label->GridPane.setHalignment(label, HPos.CENTER))
				.collect(Collectors.toList());
//		label1.setRotate(90);
		return textures;
	}

	
    private ImageView getImage() {
		URL url = this.getClass().getClassLoader().getResource("malibu_front.jpg");
		Image image = new Image(url.toString(),this.getWidth(),this.getHeight(),true,true);
		ImageView imageView = new ImageView();
	      imageView.setImage(image);
	      imageView.setX(0);
	      imageView.setY(0);
	      //imageView.setFitWidth(this.getWidth());
	      imageView.setFitWidth(400);
	      imageView.setPreserveRatio(true);
	      return imageView;
    }
    
    
}
