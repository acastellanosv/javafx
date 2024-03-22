package com.acv.showroom.view;

import java.util.ArrayList;
import java.util.List;

import com.acv.showroom.domain.ComponentDimensions;
import com.acv.showroom.texture.DynamicTextureNet;

import javafx.scene.shape.TriangleMesh;

public class DrawComponent extends TriangleMesh{
	
	float widthTop1; 
	float heightTop1;
	float widthBottom1;
	float heightBottom1;
	float widthTop2;
	float heightTop2;
	float widthBottom2;
	float heightBottom2;
	float depth;
	boolean front;
	boolean back;
	DynamicTextureNet texture;
	
	public DrawComponent(ComponentDimensions dimensions, DynamicTextureNet texture){
		this.widthTop1 = dimensions.getTopWidth();
		this.heightTop1 = dimensions.getTopHeight();
		this.widthBottom1 = dimensions.getBottomWidth();
		this.heightBottom1 = dimensions.getBottomHeight();
		this.widthTop2 = dimensions.getTopWidth2();
		this.heightTop2 = dimensions.getTopHeight2();
		this.widthBottom2 = dimensions.getBottomWidth2();
		this.heightBottom2 = dimensions.getBottomHeight2();
		this.depth = dimensions.getShapeDepth();
		this.front = dimensions.isFront();
		this.back = dimensions.isBack();

		this.texture = texture;		
	}
	
	public float getDepth() {
		return this.depth;
	}
	
	public void stroke(int pointsOffset, float depthOffset) {
		this.getPoints().addAll(getComponentPoints(depthOffset));
		this.getTexCoords().addAll(texture.getTexturePoints());
        this.getFaces().addAll(getComponentFaces(pointsOffset));
        //this.getFaceSmoothingGroups().addAll(getComponentSmooths());
	}
	
	private float[] getComponentPoints(float offset) {
		float[] thesePoints = {
                -widthBottom1/2, heightBottom1/2, 0-offset, // idx p0
                -widthTop1/2, -heightTop1/2, 0-offset, // idx p1
                widthBottom1/2,  heightBottom1/2, 0-offset, // idx p2
                widthTop1/2, -heightTop1/2, 0-offset  // idx p3
                ,
                widthBottom2/2,  heightBottom2/2, -depth-offset, // idx p4
                widthTop2/2, -heightTop2/2, -depth-offset,  // idx p5
                -widthBottom2/2,  heightBottom2/2, -depth-offset, // idx p6
                -widthTop2/2, -heightTop2/2, -depth-offset // idx p7
        };
		return thesePoints;
	}

	private int[] getComponentFaces(int points) {
		int[] texCoords = texture.getTextCoords();
		//int texIdx = 0;
        List<Integer> faces = new ArrayList<>();
        if(front) 
        {
       	//front
        faces.add(points+1); faces.add(texCoords[0]); faces.add(points+0); faces.add(texCoords[1]); faces.add(points+2); faces.add(texCoords[2]);
        faces.add(points+2); faces.add(texCoords[3]); faces.add(points+3); faces.add(texCoords[4]); faces.add(points+1); faces.add(texCoords[5]);
        }
        //right
        faces.add(points+3); faces.add(texCoords[6]); faces.add(points+2); faces.add(texCoords[7]); faces.add(points+4); faces.add(texCoords[8]);
        faces.add(points+4); faces.add(texCoords[9]); faces.add(points+5); faces.add(texCoords[10]); faces.add(points+3); faces.add(texCoords[11]);
        //back
        if(back) 
        {
        faces.add(points+5); faces.add(texCoords[12]); faces.add(points+4); faces.add(texCoords[13]); faces.add(points+6); faces.add(texCoords[14]);
        faces.add(points+6); faces.add(texCoords[15]); faces.add(points+7); faces.add(texCoords[16]); faces.add(points+5); faces.add(texCoords[17]);
        }
        //left
        faces.add(points+7); faces.add(texCoords[18]); faces.add(points+6); faces.add(texCoords[19]); faces.add(points+0); faces.add(texCoords[20]);
        faces.add(points+0); faces.add(texCoords[21]); faces.add(points+1); faces.add(texCoords[22]); faces.add(points+7); faces.add(texCoords[23]);
        //bottom
        faces.add(points+0); faces.add(texCoords[24]); faces.add(points+6); faces.add(texCoords[25]); faces.add(points+4); faces.add(texCoords[26]);
        faces.add(points+4); faces.add(texCoords[27]); faces.add(points+2); faces.add(texCoords[28]); faces.add(points+0); faces.add(texCoords[29]);
        //top  
        faces.add(points+7); faces.add(texCoords[30]); faces.add(points+1); faces.add(texCoords[31]); faces.add(points+3); faces.add(texCoords[32]);
        faces.add(points+3); faces.add(texCoords[33]); faces.add(points+5); faces.add(texCoords[34]); faces.add(points+7); faces.add(texCoords[35]);
        
        int[] facesPoints = faces.stream().mapToInt(Integer::intValue).toArray();
        return facesPoints;
	}

	private int[] getComponentSmooths() {
        int[] smooths = {
          		0,0,
          		1,1,
          		2,2,
          		3,3,
          		4,4,
          		5,5,
          		6,6
            };
        return smooths;
	}
	
}
