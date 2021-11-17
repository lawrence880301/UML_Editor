package object;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class associationLine extends Line{
	
	public associationLine() {
	}
	 
	@Override
	public void draw(Graphics2D g) {
		g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
	}
	
}
