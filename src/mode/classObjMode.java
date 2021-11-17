package mode;


import java.awt.Point;
import object.classObj;



public class classObjMode extends BaseMode{
	
	private classObj cl;

	@Override
	public void mousePressed(int x, int y) {
		cl = new classObj();
		cl.setPosition(new Point(x, y), new Point(x, y));
		canvas.addObject(cl);
		canvas.setObjectID();
		canvas.repaint();
	}
	
	@Override
	public void mouseReleased(int x, int y) {}
	
	@Override
	public void mouseDragged(int x, int y) {}

}
