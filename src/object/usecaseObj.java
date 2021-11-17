package object;

import java.awt.Color;
import java.awt.Graphics2D;

public class usecaseObj extends BaseObject{
	 public usecaseObj() {
		 size.x = 90;
		 size.y = 60;
	 }
	 
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(position.x, position.y, size.x, size.y);
		g.setColor(Color.BLACK);
		g.drawOval(position.x, position.y, size.x, size.y);
		g.drawString(objectName, position.x+25, position.y+30);
	}
}
