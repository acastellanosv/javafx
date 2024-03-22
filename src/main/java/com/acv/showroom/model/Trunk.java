package com.acv.showroom.model;

import java.util.ArrayList;
import java.util.List;

import com.acv.showroom.domain.ComponentDimensions;
import com.acv.showroom.texture.DynamicTextureNet;
import com.acv.showroom.view.DrawComponent;

public class Trunk implements AutoPart {

	private static final float topWidth 		= 250.0f;
	private static final float bottomWidth 		= 250.0f;
	private static final float topWidth2 		= 250.0f;
	private static final float bottomWidth2 	= 250.0f;

	private static final float topHeight 		= -25.0f;
	private static final float bottomHeight 	= 200.0f;
	private static final float topHeight2 		= -75.0f;
	private static final float bottomHeight2 	= 200.0f;

	private static final float shapeDepth 		= 130.0f;
	
	private static final boolean front = false; 
	private static final boolean back = true; 
	private DynamicTextureNet texture;

	public Trunk(DynamicTextureNet texture) {
		this.texture = texture;
	}
	
	@Override
	public List<DrawComponent> getAutoParts() {
		List<DrawComponent> parts = new ArrayList<>();
		parts.add(getTrunk());
		return parts;
	}

	public DrawComponent getTrunk() {
		ComponentDimensions dimensions = new ComponentDimensions(topWidth, topHeight, bottomWidth, bottomHeight
				, topWidth2, topHeight2, bottomWidth2, bottomHeight2, shapeDepth);
		dimensions.setFront(front);
		dimensions.setBack(back);
		DrawComponent trunk = new DrawComponent(dimensions, texture);
		return trunk;
	}

}
