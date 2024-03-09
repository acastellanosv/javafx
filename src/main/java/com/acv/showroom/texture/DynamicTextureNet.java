package com.acv.showroom.texture;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

public class DynamicTextureNet {

	private Image image;
	private int textures;
	private Point2D dimension;
	
	public DynamicTextureNet(List<Region> textures) {
		this.textures = textures.size();
		this.dimension = getColsRows();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		float cols = (float)dimension.getX();
		float rows = (float)dimension.getY();
		int i = 0;
		for(int y=0; y<rows; y++) {
			for(int x=0; x<cols; x++) {
				grid.add(textures.get(i++), x, y);
			}
		}

		grid.setGridLinesVisible(true);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(33.33);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(33.33);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(33.33);
		grid.getColumnConstraints().addAll(col1, col2, col3);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(50);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(50);
		grid.getRowConstraints().addAll(row1, row2);
		grid.setPrefSize(600, 400);

		Scene tmpScene = new Scene(grid);
//		tmpScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		this.image = grid.snapshot(null, null);

	}
	
	public Image getImage() {
		return this.image;
	}
	/*
     *  texture coord:
     *  0     1
     *  --------
     *  |\     |
     *  |  \   |
     *  |    \ |
     *  -------- 
     *  2     3
	 * 
     * -----------------------------------0.0
     * |0      |1      |2      |3      |4
     * |   1   |   2   |   3   |   4   |
     * |       |       |       |       |
     * -----------------------------------0.33
     * |5      |6      |7      |8      |9
     * |   5   |   6   |   7   |   8   |
     * |       |       |       |       |
     * -----------------------------------0.66
     * |10     |11     |12     |13     |14
     * |   9   |  10   |   11  |   12  |
     * |       |       |       |       |
     * -----------------------------------1.0
     * |15     |16     |17     |18     |19
     *       0.25     0.5    0.75     1.0

	 */

	
	/*              1      2       3
     *         ------------------------------0.0
     *         |       |       |       |
     *         |  Top  | Front |  Left |
     *         |       |       |       |
     *         ------------------------------0.50
     *         |       |       |       |
     *     4-> |Bottom | Back  | Right |
     *         |       |       |       |
     *         ------------------------------1.00
     *         |       |       |       |
     *        0.00    0.33    0.66    1.00
    */
	public float[] getTexturePoints() {
		float cols = (float)dimension.getX();
		float rows = (float)dimension.getY();
		while(textures>0 && (cols*rows)<textures) {
			if(rows>=cols) {
				cols++;
			}
			else {
				rows++;
			}
		}
		float[] points = new float[((((int)cols+1)*((int)rows+1)))*2];
		System.out.println(textures+" textures = points("+cols+"*"+rows+")="+points.length);
		int i = 0;
		for(int y=0; y<=rows; y++) {
			for(int x=0; x<=cols; x++) {
//				String point = "point["+i+"]=("+x+","+y+")=coord["+(x*(1/cols))+","+(y*(1/rows))+"]";
				points[i] = (x*(1/cols));
//				if(i%2==0)System.out.println("x["+i+"]="+points[i]);
				i++;
				points[i] = (y*(1/rows));
//				if(i%2==1)System.out.println("y["+i+"]="+points[i]);
				i++;
//				String point = "point"+(i/2)+"=coord["+points[i-2]+","+points[i-1]+"]";
//				System.out.println(point);
			}
		}
		return points;
	}
	
	public Point2D getColsRows() {
		float cols = 0;
		float rows = 0;
		while(textures>0 && (cols*rows)<textures) {
			if(rows>=cols) {
				cols++;
			}
			else {
				rows++;
			}
		}
		return new Point2D(cols,rows);
	}
	
	/*
     *  texture coord:
     *  0     1
     *  --------
     *  |\     |
     *  |  \   |
     *  |    \ |
     *  -------- 
     *  2     3
	 * 
     * ---------------------------------
     * |0      |1      |2      |3      |4
     * |   1   |   2   |   3   |   4   |
     * |       |       |       |       |
     * ---------------------------------
     * |5      |6      |7      |8      |9
     * |   5   |   6   |   7   |   8   |  (cols*y)+1
     * |       |       |       |       |
     * ---------------------------------
     * |10     |11     |12     |13     |14
     * |   9   |  10   |   11  |   12  |
     * |       |       |       |       |
     * ---------------------------------
     * |15     |16     |17     |18     |19
	 *
     * -------------------------
     * |0      |1      |2      |3
     * |       |       |       |
     * |       |       |       |
     * -------------------------
     * |4      |5      |6      |7
     * |       |       |       |
     * |       |       |       |
     * -------------------------
     * |8      |9      |10     |11
     *
	 */
	public int[] getTextCoords() {
		int cols = (int)dimension.getX();
		int rows = (int)dimension.getY();
		int[] coords = new int[6*cols*rows];
		System.out.println("textures="+cols+"x"+rows+" = "+coords.length);
		int i = 0;
		for(int y=0; y<rows; y++) {
			for(int x=0; x<cols; x++) {
				coords[i++] = (x+(y*(cols+1)));//0;
				coords[i++] = (x+((y+1)*(cols+1)));//2;
				coords[i++] = (x+1+((y+1)*(cols+1)));//3;
				coords[i++] = (x+1+((y+1)*(cols+1)));//3;
				coords[i++] = (x+1+(y*(cols+1)));//1;
				coords[i++] = (x+(y*(cols+1)));//0;
//				System.out.println("coord["+(0+(6*y))+"]="+(x+1+((y+1)*(cols+1))));
			}
		}
//		IntStream.range(0, coords.length).forEach(idx->{
//			System.out.println("coord["+idx+"]="+coords[idx]);
//		});
		return coords;
	}
	
}
