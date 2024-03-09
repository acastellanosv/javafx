package com.acv.showroom.view;

import java.util.List;
import java.util.function.BiFunction;

import com.acv.showroom.texture.DynamicTextureNet;

import javafx.scene.layout.Region;
import javafx.scene.shape.TriangleMesh;

public class GeneratedShape extends TriangleMesh{

	private int points = 0;
	private DynamicTextureNet texture;
	
	public GeneratedShape(double width, double height, double depth, double step, BiFunction<Double,Double,Double> wFx, BiFunction<Double,Double,Double> hFx, DynamicTextureNet imageNet) {
		this.texture = imageNet;
		double w1 = width;
		double h1 = height;
		double stepSize = depth/step;

		for(double i = 0; i < depth; i += stepSize) {
//			if(i==0)continue;
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
	
	public void setTexture(List<Region> textures) {
		this.texture = new DynamicTextureNet(textures);
	}

	private TriangleMesh createVolumeSection(float width, float height, float w2, float h2, float depth, float step){
		float offset = (step);
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
		float[] thesePoints = {
                0, height, depth+offset, // idx p0
                0, 0, depth+offset, // idx p1
                width,  height, depth+offset, // idx p2
                width, 0, depth+offset  // idx p3
                ,
                width,  height, 0+offset, // idx p4
                width, 0, 0+offset,  // idx p5
                0,  height, 0+offset, // idx p6
                0, 0, 0+offset // idx p7
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
        int[] faces = {
          	  //p1, p2, p3
          	  //front
        		points+1, texCoords[texIdx++], points+0, texCoords[texIdx++], points+2, texCoords[texIdx++],
        		points+2, texCoords[texIdx++], points+3, texCoords[texIdx++], points+1, texCoords[texIdx++]
                ,//right
                points+3, texCoords[texIdx++], points+2, texCoords[texIdx++], points+4, texCoords[texIdx++],
                points+4, texCoords[texIdx++], points+5, texCoords[texIdx++], points+3, texCoords[texIdx++]
                ,//back
                points+5, texCoords[texIdx++], points+4, texCoords[texIdx++], points+6, texCoords[texIdx++],
                points+6, texCoords[texIdx++], points+7, texCoords[texIdx++], points+5, texCoords[texIdx++]
                ,//left
                points+7, texCoords[texIdx++], points+6, texCoords[texIdx++], points+0, texCoords[texIdx++],
                points+0, texCoords[texIdx++], points+1, texCoords[texIdx++], points+7, texCoords[texIdx++]
                ,//bottom
                points+0, texCoords[texIdx++], points+6, texCoords[texIdx++], points+4, texCoords[texIdx++],
                points+4, texCoords[texIdx++], points+2, texCoords[texIdx++], points+0, texCoords[texIdx++]
                ,//top  
                points+7, texCoords[texIdx++], points+1, texCoords[texIdx++], points+3, texCoords[texIdx++],
                points+3, texCoords[texIdx++], points+5, texCoords[texIdx++], points+7, texCoords[texIdx++]
          };

        
        m.getFaces().addAll(faces);

        
        int[] smooths = {
        		
          		0,0,
          		1,1,
          		2,2,
          		3,3,
          		4,4,
          		5,5,
          		6,6
            };

//        m.getFaceSmoothingGroups().addAll(smooths);
        
		return m ;
	}

	
	
}
