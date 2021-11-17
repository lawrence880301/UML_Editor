package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class compositionLine extends Line{
	private int bias;
	private int diamondPoint_x[], diamondPoint_y[];
	
	public compositionLine() {
		bias = 10;
		diamondPoint_x = new int [4];
		diamondPoint_y = new int [4];
	}
	
	private void createDiamondPoint() {
		int x = endPoint.x;
	    int y = endPoint.y;
		diamondPoint_x[0] = x;
		diamondPoint_y[0] = y;
		if(direction_2.equals("N")) {
			diamondPoint_x[1] = x-bias;
			diamondPoint_y[1] = y-bias;
			diamondPoint_x[2] = x;
			diamondPoint_y[2] = y-bias*2;
			diamondPoint_x[3] = x+bias;
			diamondPoint_y[3] = y-bias;
		}
		if(direction_2.equals("W")) {
			diamondPoint_x[1] = x-bias;
			diamondPoint_y[1] = y+bias;
			diamondPoint_x[2] = x-bias*2;
			diamondPoint_y[2] = y;
			diamondPoint_x[3] = x-bias;
			diamondPoint_y[3] = y-bias;
		}
		if(direction_2.equals("S")) {
			diamondPoint_x[1] = x-bias;
			diamondPoint_y[1] = y+bias;
			diamondPoint_x[2] = x;
			diamondPoint_y[2] = y+bias*2;
			diamondPoint_x[3] = x+bias;
			diamondPoint_y[3] = y+bias;
		}
		if(direction_2.equals("E")) {
			diamondPoint_x[1] = x+bias;
			diamondPoint_y[1] = y+bias;
			diamondPoint_x[2] = x+bias*2;
			diamondPoint_y[2] = y;
			diamondPoint_x[3] = x+bias;
			diamondPoint_y[3] = y-bias;
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		createDiamondPoint();
		g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		g.setColor(Color.WHITE);
		g.fillPolygon(diamondPoint_x, diamondPoint_y, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(diamondPoint_x, diamondPoint_y, 4);
	}	
}
