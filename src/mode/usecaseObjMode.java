package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import object.BaseObject;
import object.classObj;
import object.usecaseObj;


public class usecaseObjMode extends BaseMode{
	
	private usecaseObj uc;
	
	public void mousePressed(int x, int y) {
		uc = new usecaseObj();
		uc.setPosition(new Point(x, y), new Point(x, y));
		canvas.addObject(uc);
		canvas.setObjectID();
		canvas.repaint();
	}
	
	@Override
	public void mouseReleased(int x, int y) {}
	
	@Override
	public void mouseDragged(int x, int y) {}
}