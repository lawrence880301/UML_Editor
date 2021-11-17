package ButtonPanel;

//import java.awt.Point;
import java.awt.event.ActionEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;


import javax.swing.ImageIcon;

import mode.compositionLineMode;





public class CompositionLineButton extends BaseButton{
	

	
	public CompositionLineButton() {
		icon_path = "img/composite.png";
		this.setIcon(new ImageIcon(icon_path));
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		mod = new compositionLineMode();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		canvas.setCurrentMode(mod);
	}
		
		
}