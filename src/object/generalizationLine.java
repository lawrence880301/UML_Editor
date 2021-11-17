package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class generalizationLine extends Line{
	private int bias_l, bias_s;
	private int trianglePoint_x[], trianglePoint_y[];
	
	public generalizationLine() {
		bias_l = 10;
		bias_s = 15;
		trianglePoint_x = new int [3];
		trianglePoint_y = new int [3];
	}
	
	private void createTrianglePoint() {
		int x = endPoint.x;
		int y = endPoint.y;
		trianglePoint_x[0] = x;
		trianglePoint_y[0] = y;
		if(direction_2.equals("N")) {
			trianglePoint_x[1] = x-bias_l;
			trianglePoint_y[1] = y-bias_s;
			trianglePoint_x[2] = x+bias_l;
			trianglePoint_y[2] = y-bias_s;
		}
		if(direction_2.equals("W")) {
			trianglePoint_x[1] = x-bias_s;
			trianglePoint_y[1] = y-bias_l;
			trianglePoint_x[2] = x-bias_s;
			trianglePoint_y[2] = y+bias_l;
		}
		if(direction_2.equals("S")) {
			trianglePoint_x[1] = x-bias_l;
			trianglePoint_y[1] = y+bias_s;
			trianglePoint_x[2] = x+bias_l;
			trianglePoint_y[2] = y+bias_s;
		}
		if(direction_2.equals("E")) {
			trianglePoint_x[1] = x+bias_s;
			trianglePoint_y[1] = y-bias_l;
			trianglePoint_x[2] = x+bias_s;
			trianglePoint_y[2] = y+bias_l;
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		createTrianglePoint();
		g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		g.setColor(Color.WHITE);
		g.fillPolygon(trianglePoint_x, trianglePoint_y, 3);
		g.setColor(Color.BLACK);
		g.drawPolygon(trianglePoint_x, trianglePoint_y, 3);
	}	
}
