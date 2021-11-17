package object;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import object.Port;

public abstract class BaseObject extends BaseShape{
	protected int originObjectID;
	protected Point position, size, originPosition;
	protected Boolean isSelected = false, isInGroup = false;
	protected String objectName = "default";
	protected Port ports[];
	
	public BaseObject() {
		position = new Point();
		size = new Point();
		originPosition = new Point();
		ports = new Port[4];
		for(int i = 0; i < ports.length ; i++)
			ports[i] = new Port();
	}
	
	@Override
	public void setPosition(Point p, Point bias) {
		position = p;
		setPortsPosition();
	}
	@Override
	public Point getPosition() {return position;}
	
	@Override
	public void setOriginPosition(Point p) {
		int x = p.x;
		int y = p.y;
		originPosition = new Point(x, y);
	}
	@Override
	public Point getOriginPostion() {return originPosition;}
	
	@Override
	public void setAllThingSelected(){;}
	
	@Override
	public Point getSize() {return size;}
	
	@Override
	public void setIsSelected(Boolean b) {isSelected = b;}
	public Boolean isSelected() {return isSelected;}
	
	public void setObjectName(String s) {objectName = s;}

	public void setOriginObjectID(int id) {originObjectID = id;}
	public int getOriginObjectID() {return originObjectID;}
	
	public Boolean isInObject(Point p) {
		if(p.x>=position.x && p.y>=position.y && p.x<=position.x+size.x && p.y<=position.y+size.y)
			return true;
		return false;
	}
	
	public Boolean isInObject(Point p1, Point p2) {
		if(p1.x<=position.x && p1.y<=position.y && p2.x>=position.x+size.x && p2.y>=position.y+size.y)
			return true;
		return false;
	}
	
	@Override
	public void setIsInGroup(Boolean b) {isInGroup = b;}
	public Boolean isInGroup() {return isInGroup;}
	
	public String findDirection(Point p) {
		float px = p.x, py = p.y, ox = position.x, oy = position.y, ow = size.x, oh = size.y;
		if(isInObject(p)) {
			//using slope to determine the port
			float m = oh/ow, b1 = oy - m*ox, b2 = oy + m*(ox+ow);
			if(m*px+b1 >= py && -m*px+b2 >= py)
				return "N"; //north
			if(m*px+b1 <= py && -m*px+b2 >= py)
				return "W"; //west
			if(m*px+b1 <= py && -m*px+b2 <= py)
				return "S"; //south
			if(m*px+b1 >= py && -m*px+b2 <= py)
				return "E"; //east
		}
		return "miss";
	}
	
	public void setPortsPosition() {
		int portSize = 8;
		Point north = new Point(position.x + size.x/2 - portSize/2, position.y - portSize);
		Point west = new Point(position.x - portSize, position.y + size.y/2 - portSize/2);
		Point south = new Point(position.x + size.x/2- portSize/2, position.y + size.y);
        Point east = new Point(position.x + size.x, position.y + size.y/2 - portSize/2);
		
		ports[0].setPosition(north);
		ports[1].setPosition(west);
		ports[2].setPosition(south);
		ports[3].setPosition(east);
	}
	
	public void drawPorts(Graphics2D g) {
		for(int i = 0; i < 4; i++) {
			ports[i].draw(g);
		}
	}
	
	public abstract void draw(Graphics2D g);
}
