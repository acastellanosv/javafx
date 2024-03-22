package com.acv.showroom.view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.acv.showroom.texture.DynamicTextureNet;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.TriangleMesh;

public class GeneratedShape extends TriangleMesh{

	private int points = 0;
	private DynamicTextureNet texture;
	
	public GeneratedShape(double widthTop, double heightTop, double widthBottom, double heightBottom
			, double depth, double step
			, BiFunction<Double,Double,Double> widthTopFx, BiFunction<Double,Double,Double> heightTopFx
			, BiFunction<Double,Double,Double> widthBottomFx, BiFunction<Double,Double,Double> heightBottomFx
			, DynamicTextureNet imageNet) {
		this.texture = imageNet;
		double widthTop1 = widthTop;
		double heightTop1 = heightTop;
		double widthBottom1 = widthBottom;
		double heightBottom1 = heightBottom;
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
			double widthTop2 = widthTopFx.apply(widthTop1,x);
			double heightTop2 = heightTopFx.apply(heightTop1, x);
			double widthBottom2 = widthBottomFx.apply(widthBottom1,x);
			double heightBottom2 = heightBottomFx.apply(heightBottom1, x);
			double d = depth;
			System.out.println("widthTop="+widthTop1+"->"+widthTop2);
			System.out.println("heightTop="+heightTop1+"->"+heightTop2);
			System.out.println("widthBottom="+widthBottom1+"->"+widthBottom2);
			System.out.println("heightBottom="+heightBottom1+"->"+heightBottom2);
			TriangleMesh t = createVolumeSection((float)widthTop1, (float)heightTop1
					, (float)widthBottom1, (float)heightBottom1
					, (float)widthTop2, (float)heightTop2
					, (float)widthBottom2, (float)heightBottom2
					, (float)stepDepth, (float)offset, (float)(x), totalSteps-1);
			this.getPoints().addAll(t.getPoints());
			points = this.getPoints().size()/3;
			this.getTexCoords().addAll(t.getTexCoords());
			this.getFaces().addAll(t.getFaces());

			widthTop1 = widthTop2;
			heightTop1 = heightTop2;
			widthBottom1 = widthBottom2;
			heightBottom1 = heightBottom2;
		}

	}
	
	public void setTexture(List<Region> textures) {
		this.texture = new DynamicTextureNet(Color.BEIGE, textures);
	}

	private TriangleMesh createVolumeSection(float widthTop1, float heightTop1
			, float widthBottom1, float heightBottom1
			, float widthTop2, float heightTop2
			, float widthBottom2, float heightBottom2
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
