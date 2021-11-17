package object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;



public class Group extends BaseShape {
	private ArrayList<BaseShape> groupList;
	private int x=99999, y=99999, width, heigh, origin_x, origin_y;
	private Boolean isSelected = false, isInGroup = false;
	
	public Group() {
		groupList = new ArrayList<BaseShape>();
	}

public void addObject(BaseShape obj) {groupList.add(obj);}
	
	public ArrayList<BaseShape> getGroupObjectList() {return groupList;}
	
	public void setIsSelected(Boolean b) {
		isSelected = b;
		for(int i = 0; i < groupList.size(); i++) {
			groupList.get(i).setIsSelected(b);
		}
	}
	public Boolean isSelected() {return isSelected;}
	
	public void setIsInGroup(Boolean b) {isInGroup = b;}
	public Boolean isInGroup() {return isInGroup;}
	
	public Point getPosition() {return new Point(x, y);}
	
	public Point getSize() {return new Point(width, heigh);}
	
	public void setPosition(Point p, Point bias) {
		this.x = p.x;
		this.y = p.y;
		for(int i = 0; i < groupList.size(); i++) {
			Point _objectOP = groupList.get(i).getOriginPostion();
			Point objectOP = new Point(_objectOP.x, _objectOP.y);
			objectOP.x += bias.x;
			objectOP.y += bias.y;
			groupList.get(i).setPosition(objectOP, bias);
		}
	} 
	
	public void setOriginPosition(Point p) {
		origin_x = p.x;
		origin_y = p.y;
		for(int i = 0; i < groupList.size(); i++) {
			Point op = groupList.get(i).getPosition();
			groupList.get(i).setOriginPosition(op);
		}
	}

	public void setAllThingSelected() {
		for(int i = 0; i < groupList.size(); i++) {
			groupList.get(i).setIsSelected(true);
		}
	}
	
	public Point getOriginPostion() {return new Point(origin_x, origin_y);}
	
	public Boolean isInGroupObject(Point p) {
		if(p.x>=x && p.y>=y && p.x<=x+width && p.y<=y+heigh)
			return true;
		return false;
	}
	
	public Boolean isInGroupObject(Point p1, Point p2) {
		if(p1.x<=x && p1.y<=y && p2.x>=x+width && p2.y>=y+heigh)
			return true;
		return false;
	}
	
	public  void findGroupBorder() {
		int max_x = 0, max_y = 0;
		for(int i = 0; i < groupList.size(); i++) {
			Point temp_p = groupList.get(i).getPosition();
			Point temp_psize = groupList.get(i).getSize();
			if(temp_p.x < x)
				x = temp_p.x;
			if(temp_p.y < y)
				y = temp_p.y;
			if(temp_p.x+temp_psize.x > max_x)
				max_x = temp_p.x+temp_psize.x;
			if(temp_p.y+temp_psize.y > max_y)
				max_y = temp_p.y+temp_psize.y;
		}
		width = max_x-x;
		heigh = max_y-y;
	}
	
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(1.5f));
		for(int i = 0; i < groupList.size(); i++)
			groupList.get(i).draw(g);
		if(!isSelected)
			g.setStroke(new BasicStroke(0.5f));
		else
			g.setStroke(new BasicStroke(2.0f));
		g.drawRect(x, y, width, heigh);
	}

}
