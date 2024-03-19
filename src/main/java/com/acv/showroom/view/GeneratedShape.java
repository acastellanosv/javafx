package com.acv.showroom.view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.acv.showroom.texture.DynamicTextureNet;

import javafx.scene.layout.Region;
import javafx.scene.shape.TriangleMesh;

public class GeneratedShape extends TriangleMesh{

	private int points = 0;
	private DynamicTextureNet texture;
	
	public GeneratedShape(double width, double height, double depth, double step
			, BiFunction<Double,Double,Double> wFx, BiFunction<Double,Double,Double> hFx
			, DynamicTextureNet imageNet) {
		this.texture = imageNet;
		double w1 = width;
		double h1 = height;
		double stepSize = depth/step;
		int totalSteps = (int)Math.round(depth/stepSize);
//		System.out.println("totalSteps="+totalSteps);
		for(int i = 0; i < totalSteps; i ++) {
//			if(i!=0)continue;
			double stepDepth = stepSize;
			if(stepSize*i>depth) {
				stepDepth=depth-(stepSize*(i-1));
			}
			double x = i;
			double offset = (i*stepDepth);
			double w2 = wFx.apply(w1,x);
			double h2 = hFx.apply(h1, x);
			double d = depth;
			System.out.println("w="+w1+"->"+w2);
			System.out.println("h="+h1+"->"+h2);
			TriangleMesh t = createVolumeSection((float)w1, (float)h1, (float)w2, (float)h2
					, (float)stepDepth, (float)offset, (float)(x), totalSteps-1);
			this.getPoints().addAll(t.getPoints());
			points = this.getPoints().size()/3;
			this.getTexCoords().addAll(t.getTexCoords());
			this.getFaces().addAll(t.getFaces());

			w1 = w2;
			h1 = h2;
		}

	}
	
	public void setTexture(List<Region> textures) {
		this.texture = new DynamicTextureNet(textures);
	}

	private TriangleMesh createVolumeSection(float width, float height, float w2, float h2
			, float depth, float offset, float x, int total){
//		System.out.println("x="+x);
		TriangleMesh m = new TriangleMesh();
//		System.out.println(String.format("(w,h,d):(%s,%s,%s)",width,height,depth));
//		System.out.println(String.format("(w2,h2,d,o):(%s,%s,%s,%s)",w2,h2,depth,offset));
		//create Points
		//+y->down  +z->front
		//       y /
		//(0,0,0)|/ Front   (w,0,0)
		// x-----1---------3---       
		//      /|         |\       
		//     / |         | \       
		//    /  |(0,0,d)  |  \             Back
		//   z   |   7  -  | - 5(w,0,d)  5---------7
		//(0,h,0)0---------2   |         |         |
		//       |\  (w,h,0)\  |         |         |
		//       | \         \ |         |         |
		//          \|        \|         |         |
		//           6---------4         4---------6
		//      (0,h,d)         (w,h,d)
		//
		//
//		System.out.println("depth="+(depth));
//		System.out.println("offset="+(offset));
//		System.out.println("length="+(0-offset)+" to "+(-depth-offset));
		float[] thesePoints = {
                -width/2, height/2, 0-offset, // idx p0
                -width/2, -height/2, 0-offset, // idx p1
                width/2,  height/2, 0-offset, // idx p2
                width/2, -height/2, 0-offset  // idx p3
                ,
                w2/2,  h2/2, -depth-offset, // idx p4
                w2/2, -h2/2, -depth-offset,  // idx p5
                -w2/2,  h2/2, -depth-offset, // idx p6
                -w2/2, -h2/2, -depth-offset // idx p7
        };
		m.getPoints().addAll(thesePoints);
        /**
         * points:
         * 1      3
         *  -------   texture:    texture coord:
         *  |\    |  0,0    1,0   0       1
         *  | \   |    -------     -------
         *  |  \  |    |     |     |\    |
         *  |   \ |    |     |     | \   |
         *  |    \|    -------     |   \ |
         *  -------  0,1    1,1    -------
         * 0      2				  2       3
         *
         *         ---------      ---     --0.0
         *         |       |
         *         |  Top  |
         *         |       |
         * ---------------------------------0.33
         * |       |       |       |       |
         * |  Left | Front | Right | Back  |
         * |       |       |       |       |
         * ---------------------------------0.66
         *         |       |
         *         |Bottom |       |       |
         *         |       |       |       |
         *         ---------              --1.0
         *       0.25     0.5    0.75     1.0
         */				
		
		m.getTexCoords().addAll(texture.getTexturePoints());
		//create Points
		//+y->down  +z->front
		//       y /
		//(0,0,0)|/ Front   (w,0,0)
		// x-----1---------3---         
		//      /|         |\       
		//     / |         | \       
		//    /  |         |  \             Back
		//   z   |   7  -  |   5(w,0,d)  5---------7
		//(0,h,0)0---------2   |         |         |
		//       |\    (w,h)\  |         |         |
		//       | \ |       \ |         |         |
		//          \         \|         |         |
		//           6---------4         4---------6
		//      (0,h,d)         (w,h,d)
		//
		//
		
		int[] texCoords = texture.getTextCoords();
		int texIdx = 0;
        List<Integer> faces = new ArrayList<>();
        //p1, p2, p3
        //front
//    	System.out.println("x="+x);
        if(x==0) 
        {
//        	System.out.println("front");
        faces.add(points+1); faces.add(texCoords[0]); faces.add(points+0); faces.add(texCoords[1]); faces.add(points+2); faces.add(texCoords[2]);
        faces.add(points+2); faces.add(texCoords[3]); faces.add(points+3); faces.add(texCoords[4]); faces.add(points+1); faces.add(texCoords[5]);
        }
        //right
        faces.add(points+3); faces.add(texCoords[6]); faces.add(points+2); faces.add(texCoords[7]); faces.add(points+4); faces.add(texCoords[8]);
        faces.add(points+4); faces.add(texCoords[9]); faces.add(points+5); faces.add(texCoords[10]); faces.add(points+3); faces.add(texCoords[11]);
        //back
        if(x>=total) 
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
        m.getFaces().addAll(facesPoints);

        
        int[] smooths = {
        		
          		0,0,
          		1,1,
          		2,2,
          		3,3,
          		4,4,
          		5,5,
          		6,6
            };

        m.getFaceSmoothingGroups().addAll(smooths);
        
		return m ;
	}

	
	
}
