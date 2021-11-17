package mode;



import editor.Canvas;


public abstract class BaseMode {

	protected Canvas canvas; 

	public BaseMode() {
		canvas = Canvas.getInstance();
	}
	


	public abstract void mousePressed(int x, int y);
	public abstract void mouseReleased(int x, int y);
	
	public abstract void mouseDragged(int x, int y);
	
	
	
}

