package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import object.Line;
import object.BaseShape;

public class selectMode extends BaseMode {
	private Boolean onObject, moving;
	private int x1, y1, x2, y2, x3, y3;

	public void mousePressed(int x, int y) {
		x1 = x;
		y1 = y;
		onObject = false;
		moving = false;
		canvas.setIsSelectOnGroup(false);
		canvas.setIsSelectOnObject(false);
		if(canvas.isSelectOnObject(x1, y1)) {
			onObject = true;
			canvas.storeOriginPosition();
		}
		canvas.repaint();
	}
	
	@Override
	public void mouseReleased(int x, int y) {
		x2 = x;
		y2 = y;
		if(!onObject) {
			if(canvas.isDraging()) {
				canvas.setIsDraging(false);
				canvas.setPerfectRect(x1, y1, x2, y2);
				canvas.changeSelectAreaObject();
			}
			else
				canvas.setAllObjectUnselected();
		}
		else {
			if(!canvas.isSelectOnGroup()) {
				if(moving) {
					canvas.setAllObjectUnselected();
					canvas.setObjectIsSelected(true);
				}
				else
					canvas.changeObjectIsSelected();
			}
			else {
				canvas.setAllObjectUnselected();
				canvas.setGroupIsSelected(true);
			}
		}
		canvas.repaint();
	}
	
	@Override
	public void mouseDragged(int x, int y) {
		x3 = x;
		y3 = y;
		moving = true;
		canvas.setAllObjectUnselected();
		if(onObject)
			canvas.moveObject(x1, y1, x3, y3);
		else {
			canvas.setIsDraging(true);
			canvas.setPerfectRect(x1, y1, x3, y3);
		}
		canvas.repaint();
	}
}
