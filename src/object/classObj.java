package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class classObj extends BaseObject{
	
	 public classObj() {
		 size.x = 60;
		 size.y = 90;
	 }
	 
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(position.x, position.y, size.x, size.y);
		g.setColor(Color.BLACK);
		g.drawRect(position.x, position.y, size.x, size.y);
		g.drawLine(position.x, position.y+size.y*1/3, position.x+size.x, position.y+size.y*1/3);
		g.drawLine(position.x, position.y+size.y*2/3, position.x+size.x, position.y+size.y*2/3);
		g.drawString(objectName, position.x+10, position.y+18);
	}
}
