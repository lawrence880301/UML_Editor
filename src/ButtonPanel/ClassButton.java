package ButtonPanel;

//import java.awt.Point;
import java.awt.event.ActionEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;



import javax.swing.ImageIcon;

import mode.classObjMode;


//import objects.Class;


public class ClassButton  extends BaseButton{

	public ClassButton() {
		icon_path = "img/class.png";
		this.setIcon(new ImageIcon(icon_path));
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		mod = new classObjMode();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		canvas.setCurrentMode(mod);
		

	}
}