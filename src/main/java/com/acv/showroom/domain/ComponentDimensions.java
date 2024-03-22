package com.acv.showroom.domain;

public class ComponentDimensions {

	private float topWidth;
	private float topHeight;
	private float bottomWidth;
	private float bottomHeight;
	private float topWidth2;
	private float topHeight2;
	private float bottomWidth2;
	private float bottomHeight2;
	private float shapeDepth;
	private float offset;
	private boolean front; 
	private boolean back;
	
	public ComponentDimensions(float topWidth, float topHeight, float bottomWidth, float bottomHeight, 
			float topWidth2, float topHeight2, float bottomWidth2, float bottomHeight2,	float shapeDepth) {
		super();
		this.topWidth = topWidth;
		this.topHeight = topHeight;
		this.bottomWidth = bottomWidth;
		this.bottomHeight = bottomHeight;
		this.topWidth2 = topWidth2;
		this.topHeight2 = topHeight2;
		this.bottomWidth2 = bottomWidth2;
		this.bottomHeight2 = bottomHeight2;
		this.shapeDepth = shapeDepth;
	}

	public float getShapeDepth() {
		return shapeDepth;
	}

	public void setShapeDepth(float shapeDepth) {
		this.shapeDepth = shapeDepth;
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}

	public boolean isFront() {
		return front;
	}

	public void setFront(boolean front) {
		this.front = front;
	}

	public boolean isBack() {
		return back;
	}

	public void setBack(boolean back) {
		this.back = back;
	}

	public float getTopWidth() {
		return topWidth;
	}

	public float getTopHeight() {
		return topHeight;
	}

	public float getBottomWidth() {
		return bottomWidth;
	}

	public float getBottomHeight() {
		return bottomHeight;
	}

	public float getTopWidth2() {
		return topWidth2;
	}

	public float getTopHeight2() {
		return topHeight2;
	}

	public float getBottomWidth2() {
		return bottomWidth2;
	}

	public float getBottomHeight2() {
		return bottomHeight2;
	}

	
}
