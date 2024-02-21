package com.acv.showroom.view;

import java.util.function.Function;

import javafx.scene.shape.TriangleMesh;

public class GeneratedVolume extends TriangleMesh{

	private int points = 0;
	
	public GeneratedVolume(double start, double end, double numSteps, Function<Number,Double> base, Function<Number,Double> height) {

//		TriangleMesh m = new TriangleMesh();

		double stepSize = (end-start)/numSteps;

		for(double i = start; i < end; i += stepSize) {

			double x = i;
			double x2 = x+stepSize;
			double gx = height.apply(x);
			double gx2 = height.apply(x2);
			double fx = base.apply(x);
			double fx2 = base.apply(x2);


			TriangleMesh t = createVolumeSection(x,x2,gx,gx2,fx,fx2);
			this.getPoints().addAll(t.getPoints());
			points = this.getPoints().size()/3;
			this.getTexCoords().addAll(t.getTexCoords());
			this.getFaces().addAll(t.getFaces());
		}

//		return m;
	}

	private TriangleMesh createVolumeSection(double xVal, double x2Val, double gxVal, double gx2Val,
			double fxVal, double fx2Val){
		TriangleMesh m = new TriangleMesh();
		float x = ((float)xVal) ;
		float x2 = ((float)x2Val);
		float gx = ((float)gxVal);
		float gx2 = ((float)gx2Val);
		float fx = ((float)fxVal);
		float fx2 = ((float)fx2Val);

		//create Points
		m.getPoints().addAll(
				x,  0,  0,      // A = 0
				x,  0,  gx,     // B = 1
				x2, 0,  0,      // C = 2
				x2, 0,  gx2,    // D = 3
				x,  fx, 0,      // E = 4
				x,  fx, gx,     // F = 5
				x2, fx2,0,      // G = 6
				x2, fx2,gx2     // H = 7
				);

		m.getTexCoords().addAll(0,0);

		 m.getFaces().addAll(
		            points+0 , 0 , points+1 , 0 , points+3 , 0 ,     // A-B-D
		            points+0 , 0 , points+3 , 0 , points+2 , 0 ,     // A-D-C
		            points+0 , 0 , points+2 , 0 , points+6 , 0 ,     // A-C-G
		            points+0 , 0 , points+6 , 0 , points+4 , 0 ,     // A-G-E
		            points+0 , 0 , points+4 , 0 , points+1 , 0 ,     // A-E-B
		            points+1 , 0 , points+4 , 0 , points+5 , 0 ,     // B-E-F
		            points+1 , 0 , points+5 , 0 , points+7 , 0 ,     // B-F-H
		            points+1 , 0 , points+7 , 0 , points+3 , 0 ,     // B-H-D
		            points+3 , 0 , points+7 , 0 , points+6 , 0 ,     // D-H-G
		            points+3 , 0 , points+6 , 0 , points+2 , 0 ,     // D-G-C
		            points+6 , 0 , points+7 , 0 , points+5 , 0 ,     // G-H-F
		            points+6 , 0 , points+5 , 0 , points+4 , 0       // G-F-E
		    );
		 
		return m ;
	}
}
