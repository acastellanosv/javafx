package com.acv.showroom.model;

import java.util.ArrayList;
import java.util.List;

import com.acv.showroom.domain.ComponentDimensions;
import com.acv.showroom.texture.DynamicTextureNet;
import com.acv.showroom.view.DrawComponent;

public class Cabin implements AutoPart {

	private static final float topWidth 		= 200.0f;
	private static final float bottomWidth 		= 200.0f;
	private static final float topWidth2 		= 200.0f;
	private static final float bottomWidth2 	= 200.0f;

	private static final float topHeight 		= 100.0f;
	private static final float bottomHeight 	= 200.0f;
	private static final float topHeight2 		= 100.0f;
	private static final float bottomHeight2 	= 200.0f;

	private static final float shapeDepth 		= 200.0f;

	private static final boolean front = false; 
	private static final boolean back = false; 
	private DynamicTextureNet texture;
	
	public Cabin(DynamicTextureNet texture) {
		this.texture = texture;
	}
	
	@Override
	public List<DrawComponent> getAutoParts() {
		List<DrawComponent> parts = new ArrayList<>();
		parts.add(getWindshiled());
		parts.add(getCabin());
		parts.add(getRearWindshiled());
		return parts;
	}

	private DrawComponent getCabin() {
		ComponentDimensions dimensions = new ComponentDimensions(topWidth, topHeight, bottomWidth, bottomHeight
				, topWidth2, topHeight2, bottomWidth2, bottomHeight2, shapeDepth);
		dimensions.setFront(front);
		dimensions.setBack(back);
		DrawComponent cabin = new DrawComponent(dimensions, texture);
		return cabin;
	}
	
	private DrawComponent getWindshiled() {
		float shapeDepth 	= 50.0f;

		float topHeight 	= -25.0f;
		float bottomHeight 	= 200.0f;
		float topHeight2 	= 100.0f;
		float bottomHeight2 = 200.0f;

		ComponentDimensions dimensions = new ComponentDimensions(topWidth, topHeight, bottomWidth, bottomHeight
				, topWidth2, topHeight2, bottomWidth2, bottomHeight2, shapeDepth);
		dimensions.setFront(front);
		dimensions.setBack(back);
		DrawComponent component = new DrawComponent(dimensions, texture);
		return component;
	}

	private DrawComponent getRearWindshiled() {
		float shapeDepth 	= 75.0f;

		float topHeight 	= 100.0f;
		float bottomHeight 	= 200.0f;
		float topHeight2 	= -25.0f;
		float bottomHeight2 = 200.0f;

		ComponentDimensions dimensions = new ComponentDimensions(topWidth, topHeight, bottomWidth, bottomHeight
				, topWidth2, topHeight2, bottomWidth2, bottomHeight2, shapeDepth);
		dimensions.setFront(front);
		dimensions.setBack(back);
		DrawComponent component = new DrawComponent(dimensions, texture);
		return component;
	}

}
