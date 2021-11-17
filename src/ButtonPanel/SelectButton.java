package ButtonPanel;

import java.awt.event.ActionEvent;
/*import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;*/

import javax.swing.ImageIcon;

import mode.selectMode;



public class SelectButton extends BaseButton{
	

	
	public SelectButton() {
		icon_path = "img/select.png";
		this.setIcon(new ImageIcon(icon_path));
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		mod = new selectMode();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		canvas.setCurrentMode(mod);
	}
	
}
