package mode;

import java.awt.Point;


import object.associationLine;


public class associationLineMode extends BaseMode{

	private associationLine al;
	private Boolean isStart = false;
	private associationLine templine;
	private Point startPoint;

	public void mousePressed(int x, int y) {
		al = new associationLine();
		int x1 = x;
		int y1 = y;
		isStart = false;
		startPoint = new Point(x,y);
		Point p = canvas.getRightPosition(x1, y1);
		if(p.x != -1 && p.y != -1) {
			al.setObjectID_1(canvas.getObjectId());
			al.setDirection_1(canvas.getSituation());
			al.setStartPoint(new Point(p.x, p.y));
			isStart = true;
		}
	}
	
	@Override
	public void mouseReleased(int x, int y) {
		int x2 = x;
		int y2 = y;
		if(isStart) {
			Point p = canvas.getRightPosition(x2, y2);
			int objectID = canvas.getObjectId();
			if(p.x != -1 && p.y != -1 && al.getObjectID_1() != objectID) {
				al.setObjectID_2(objectID);
				al.setDirection_2(canvas.getSituation());
				al.setEndPoint(new Point(p.x, p.y));
				canvas.addConnectionLine(al);
				al.setPortsPosition();
				canvas.repaint();
			}
		}
	}
	
	@Override
	public void mouseDragged(int x, int y) {
//TODO: add translate
		
//		int x2 = x;
//		int y2 = y;
//		if(isStart) {
//			templine = new associationLine();
//			templine.setObjectID_1(canvas.getObjectId());
//			templine.setDirection_1(canvas.getSituation());
//			templine.setStartPoint(new Point(startPoint.x, startPoint.y));
//			templine.setEndPoint(new Point(x, y));
//			canvas.addConnectionLine(al);
//
//			canvas.repaint();
//			
//		}
	}
}

