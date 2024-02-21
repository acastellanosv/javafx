package com.acv.showroom.view;

import javafx.scene.shape.TriangleMesh;

public class Shape3DRectangle extends TriangleMesh {

    public Shape3DRectangle(float width, float height, float depth) {
        float[] points = {
                -width/2,  height/2, -depth/2, // idx p0
                -width/2, -height/2, -depth/2, // idx p1
                width/2,  height/2, -depth/2, // idx p2
                width/2, -height/2, -depth/2,  // idx p3
                width/2,  height/2, depth/2, // idx p4
                width/2, -height/2, depth/2,  // idx p5
                -width/2,  height/2, depth/2, // idx p6
                -width/2, -height/2, depth/2, // idx p7
        };
        float[] texCoords = {
                1, 1, // idx t0
                1, 0, // idx t1
                0, 1, // idx t2
                0, 0  // idx t3
        };
        /**
         * points:
         * 1      3
         *  -------   texture:    texture coord:
         *  |\    |  1,1    1,0   0       1
         *  | \   |    -------     -------
         *  |  \  |    |     |     |\    |
         *  |   \ |    |     |     | \   |
         *  |    \|    -------     |   \ |
         *  -------  0,1    0,0    -------
         * 0      2				  2       3
         *
         * texture[2] 0,1 maps to vertex 0
         * texture[0] 1,1 maps to vertex 1
         * texture[3] 0,0 maps to vertex 2
         * texture[1] 1,0 maps to vertex 3
         *
         * Two triangles define rectangular faces:
         * p0, t0, p1, t1, p2, t2 // First triangle of a textured rectangle
         * p0, t0, p2, t2, p3, t3 // Second triangle of a textured rectangle
         */

// if you use the co-ordinates as defined in the above comment, it will be all messed up
//        int[] faces = {
//                0, 0, 1, 1, 2, 2,
//                0, 0, 2, 2, 3, 3
//        };

// try defining faces in a counter-clockwise order to see what the difference is.
//        int[] faces = {
//                2, 2, 1, 1, 0, 0,
//                2, 2, 3, 3, 1, 1
//        };

// try defining faces in a clockwise order to see what the difference is.
//        int[] faces = {
//                2, 3, 0, 2, 1, 0,
//                2, 3, 1, 0, 3, 1
//        };


        int[] faces = {
        		
        	  //p1, p2, p3
        	  //front
              1, 2, 0, 0, 2, 3,
              2, 0, 3, 3, 1, 1
              ,//right
              3, 2, 2, 0, 4, 3,
              4, 0, 5, 3, 3, 1
              ,//back
              5, 2, 4, 0, 6, 3,
              6, 0, 7, 3, 5, 1
              ,//left
              7, 2, 6, 0, 0, 3,
              0, 0, 1, 3, 7, 1
              ,//top
              1, 2, 3, 0, 7, 3,
              7, 0, 3, 3, 5, 1
              ,//bottom
              0, 2, 6, 0, 2, 3,
              2, 0, 6, 3, 4, 1
        		
        };
        
        
        this.getPoints().setAll(points);
        this.getTexCoords().setAll(texCoords);
        this.getFaces().setAll(faces);
    }
}
