package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import object.BaseShape;
import object.Line;
import object.compositionLine;



public class compositionLineMode extends BaseMode{

	private compositionLine cl;
	private Boolean isStart = false;

	@Override
	public void mousePressed(int x, int y) {
		cl = new compositionLine();
		int x1 = x;
		int y1 = y;
		isStart = false;
		Point p = canvas.getRightPosition(x1, y1);
		if(p.x != -1 && p.y != -1) {
			cl.setObjectID_1(canvas.getObjectId());
			cl.setDirection_1(canvas.getSituation());
			cl.setStartPoint(new Point(p.x, p.y));
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
			if(p.x != -1 && p.y != -1 && cl.getObjectID_1() != objectID) {
				cl.setObjectID_2(objectID);
				cl.setDirection_2(canvas.getSituation());
				cl.setEndPoint(new Point(p.x, p.y));
				canvas.addConnectionLine(cl);
				cl.setPortsPosition();
				canvas.repaint();
			}
		}
	}
	
	@Override
	public void mouseDragged(int x, int y) {}
}

