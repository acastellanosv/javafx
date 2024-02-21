package com.acv.showroom.view;

import java.util.function.BiFunction;

import javafx.scene.shape.TriangleMesh;

public class GeneratedShape extends TriangleMesh{

	private int points = 0;
	
	public GeneratedShape(double width, double height, double depth, double step, BiFunction<Double,Double,Double> wFx, BiFunction<Double,Double,Double> hFx) {

		double w1 = width;
		double h1 = height;
		double stepSize = depth/step;

		for(double i = 0; i < depth; i += stepSize) {

			double x = i;
			double w2 = wFx.apply(w1,x);
			double h2 = hFx.apply(h1, x);
			double d = depth;

			TriangleMesh t = createVolumeSection((float)w1, (float)h1, (float)w2, (float)h2, (float)stepSize, (float)(x));
			this.getPoints().addAll(t.getPoints());
			points = this.getPoints().size()/3;
			this.getTexCoords().addAll(t.getTexCoords());
			this.getFaces().addAll(t.getFaces());

			w1 = w2;
			h1 = h2;
		}

	}

	private TriangleMesh createVolumeSection(float width, float height, float w2, float h2, float depth, float offset){
		TriangleMesh m = new TriangleMesh();
		System.out.println(String.format("(w,h,d):(%s,%s,%s)",width,height,depth));
		System.out.println(String.format("(w2,h2,d,o):(%s,%s,%s,%s)",w2,h2,depth,offset));
		//create Points
		float[] thesePoints = {
                0,  height, 0+offset, // idx p0
                0, 0, 0+offset, // idx p1
                width,  height, 0+offset, // idx p2
                width, 0, 0+offset,  // idx p3
                width,  height, depth+offset, // idx p4
                width, 0, depth+offset,  // idx p5
                0,  height, depth+offset, // idx p6
                0, 0, depth+offset // idx p7
        };
		m.getPoints().addAll(thesePoints);
				
        float[] texCoords = {
                1, 1, // idx t0
                1, 0, // idx t1
                0, 1, // idx t2
                0, 0  // idx t3
        };
		m.getTexCoords().addAll(texCoords);

        int[] faces = {
        		
          	  //p1, p2, p3
          	  //front
        		points+1, 2, points+0, 0, points+2, 3,
        		points+2, 0, points+3, 3, points+1, 1
                ,//right
                points+3, 2, points+2, 0, points+4, 3,
                points+4, 0, points+5, 3, points+3, 1
                ,//back
                points+5, 2, points+4, 0, points+6, 3,
                points+6, 0, points+7, 3, points+5, 1
                ,//left
                points+7, 2, points+6, 0, points+0, 3,
                points+0, 0, points+1, 3, points+7, 1
                ,//top
                points+1, 2, points+3, 0, points+7, 3,
                points+7, 0, points+3, 3, points+5, 1
                ,//bottom
                points+0, 2, points+6, 0, points+2, 3,
                points+2, 0, points+6, 3, points+4, 1
          		
          };

        
        m.getFaces().addAll(faces);
		 
		return m ;
	}
}
