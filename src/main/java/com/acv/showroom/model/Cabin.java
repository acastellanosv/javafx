package com.acv.showroom.model;

import com.acv.showroom.texture.DynamicTextureNet;
import com.acv.showroom.view.DrawComponent;

import javafx.scene.paint.Color;

public class Cabin extends DrawComponent {

	private static final float topWidth = 200.0f;
	private static final float topHeight = 200.0f;
	private static final float bottomWidth = 200.0f;
	private static final float bottomHeight = 200.0f;
	private static final float topWidth2 = 200.0f;
	private static final float topHeight2 = 200.0f;
	private static final float bottomWidth2 = 200.0f;
	private static final float bottomHeight2 = 200.0f;
	private static final float shapeDepth = 200.0f;
	private static final boolean front = true; 
	private static final boolean back = true; 
	
	public Cabin(int points, Color color, DynamicTextureNet texture) {
		super(topWidth, topHeight, bottomWidth, bottomHeight
				, topWidth2, topHeight2, bottomWidth2, bottomHeight2
				, shapeDepth, front, back,  points, texture);
	}
	

}
