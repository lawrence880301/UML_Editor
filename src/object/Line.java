package object;


import java.awt.Graphics2D;
import java.awt.Point;




public abstract class Line extends BaseShape{
	protected int objectID_1, objectID_2;
	protected Point startPoint, endPoint, originStartPoint, originEndPoint;
	protected String direction_1, direction_2;
	protected Port ports[];
	
	public Line() {
		startPoint = new Point();
		endPoint = new Point();
		originStartPoint = new Point();
		originEndPoint = new Point();
		ports = new Port[2];
		for(int i = 0; i < ports.length ; i++)
			ports[i] = new Port();
	}
	
	public void setStartPoint(Point s) {startPoint = s;}
	public Point getStartPoint() {return startPoint;}
	
	public void setEndPoint(Point e) {endPoint = e;}
	public Point getEndPoint() {return endPoint;}
	
	public void setObjectID_1(int id) {objectID_1 = id;}
	public int getObjectID_1() {return objectID_1;}
	
	public void setObjectID_2(int id) {objectID_2 = id;}
	public int getObjectID_2() {return objectID_2;}
	
	public void setDirection_1(String s) {direction_1 = s;}
	public String getDirection_1() {return direction_1;}
	
	public void setDirection_2(String s) {direction_2 = s;}
	public String getDirection_2() {return direction_2;}
	
	public void setOriginStartPoint(Point osp) {originStartPoint = osp;}
	public Point getOriginStartPoint() {return originStartPoint;}
	
	public void setOriginEndPoint(Point oep) {originEndPoint = oep;}
	public Point getOriginEndPoint() {return originEndPoint;}
	
	public void setOriginStartAndEndPoint(Point osp, Point oep) {
		originStartPoint = osp;
		originEndPoint = oep;
	}
	
	public void setPortsPosition() {
		ports[0].setPosition(startPoint);
		ports[1].setPosition(endPoint);
	}
	
	public abstract void draw(Graphics2D g);
	@Override
	public void setOriginPosition(Point p) {;}
	@Override
	public Point getOriginPostion() {return null;}
	@Override
	public void setAllThingSelected() {;}
	@Override
	public void setIsSelected(Boolean b) {;}
	@Override
	public void setPosition(Point p, Point bias) {;}
	@Override
	public Point getPosition() {return null;}
	@Override
	public Point getSize() {return null;}
	@Override
	public void setIsInGroup(Boolean b) {}	
}
 