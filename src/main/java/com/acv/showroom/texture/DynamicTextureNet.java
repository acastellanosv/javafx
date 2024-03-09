package com.acv.showroom.texture;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class DynamicTextureNet {

	private Image image; 
	
	public DynamicTextureNet(String face1, String face2, String face3, String face4, String face5, String face6) {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		Label label1 = new Label(face1);
//		label1.setRotate(90);
		GridPane.setHalignment(label1, HPos.CENTER);
		Label label2 = new Label(face2);
		GridPane.setHalignment(label2, HPos.CENTER);
		Label label3 = new Label(face3);
		GridPane.setHalignment(label3, HPos.CENTER);
		Label label4 = new Label(face4);
		GridPane.setHalignment(label4, HPos.CENTER);
		Label label5 = new Label(face5);
		GridPane.setHalignment(label5, HPos.CENTER);
		Label label6 = new Label(face6);
//		label6.setRotate(90);
		GridPane.setHalignment(label6, HPos.CENTER);
		grid.add(label1, 0, 0);
		grid.add(label2, 0, 1);
		grid.add(label3, 1, 0);
		grid.add(label4, 1, 1);
		grid.add(label5, 2, 0);
		grid.add(label6, 2, 1);

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
     *         ------------------------------0.0
     *         |       |       |       |
     *         |  Top  | Front |  Left |
     *         |       |       |       |
     *         ------------------------------0.50
     *         |       |       |       |
     *         |Bottom | Back  | Right |
     *         |       |       |       |
     *         ------------------------------1.00
     *         |       |       |       |
     *        0.00    0.33    0.66    1.00
    */
	public float[] getTexturePoints(float cols, float rows) {
		float[] pointsX = new float[(int)cols*(int)rows];
		float[] pointY = new float[(int)cols*(int)rows];
		int i = 0;
		for(int y=0; y<=rows; y++) {
			for(int x=0; x<=cols; x++) {
				String point = "";
				point = "point"+(i++)+"("+x+","+y+")=coord["+(x*(1/cols))+","+(y*(1/rows))+"]";
//				if(i%2==0)point = "point"+(i)+"=x="+(x*(1/cols));
//				if(i%2==1)point = "point"+(i)+"=y="+(y*(1/rows));
//				i++;
				System.out.println(point);
			}
		}
		return pointsX;
	}

}
