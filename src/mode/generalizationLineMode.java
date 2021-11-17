package mode;

import java.awt.Point;

import object.generalizationLine;



public class generalizationLineMode extends BaseMode{

	private generalizationLine gl;
	private Boolean isStart = false;

	@Override
	public void mousePressed(int x, int y) {
		gl = new generalizationLine();
		int x1 = x;
		int y1 = y;
		isStart = false;
		Point p = canvas.getRightPosition(x1, y1);
		if(p.x != -1 && p.y != -1) {
			gl.setObjectID_1(canvas.getObjectId());
			gl.setDirection_1(canvas.getSituation());
			gl.setStartPoint(new Point(p.x, p.y));
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
			if(p.x != -1 && p.y != -1 && gl.getObjectID_1() != objectID) {
				gl.setObjectID_2(objectID);
				gl.setDirection_2(canvas.getSituation());
				gl.setEndPoint(new Point(p.x, p.y));
				canvas.addConnectionLine(gl);
				gl.setPortsPosition();
				canvas.repaint();
			}
		}
	}
	
	@Override
	public void mouseDragged(int x, int y) {}
	

	
}

