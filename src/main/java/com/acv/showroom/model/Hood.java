package com.acv.showroom.model;

import java.util.ArrayList;
import java.util.List;

import com.acv.showroom.domain.ComponentDimensions;
import com.acv.showroom.texture.DynamicTextureNet;
import com.acv.showroom.view.DrawComponent;

public class Hood implements AutoPart{

	private static final float topWidth 		= 250.0f;
	private static final float bottomWidth 		= 250.0f;
	private static final float topWidth2 		= 250.0f;
	private static final float bottomWidth2 	= 250.0f;

	private static final float topHeight 		= -75.0f;
	private static final float bottomHeight 	= 200.0f;
	private static final float topHeight2 		= -25.0f;
	private static final float bottomHeight2 	= 200.0f;

	private static final float shapeDepth 		= 175.0f;
	private static final boolean front = true; 
	private static final boolean back = false;
	private DynamicTextureNet texture;
	
	public Hood(DynamicTextureNet texture) {
		this.texture = texture;
	}

	@Override
	public List<DrawComponent> getAutoParts() {
		List<DrawComponent> parts = new ArrayList<>();
		parts.add(getHood());
		return parts;
	}
	
	private DrawComponent getHood() {
		ComponentDimensions dimensions = new ComponentDimensions(topWidth, topHeight, bottomWidth, bottomHeight
				, topWidth2, topHeight2, bottomWidth2, bottomHeight2, shapeDepth);
		dimensions.setFront(front);
		dimensions.setBack(back);
		DrawComponent hood  = new DrawComponent(dimensions, texture);
		return hood;
	}
	
}
