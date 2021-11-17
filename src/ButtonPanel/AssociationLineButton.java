package ButtonPanel;

//import java.awt.Point;
import java.awt.event.ActionEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;


import javax.swing.ImageIcon;

import mode.associationLineMode;

//import objects.Association;


public class AssociationLineButton extends BaseButton{

	
	
	public AssociationLineButton() {
		icon_path = "img/associate.png";
		this.setIcon(new ImageIcon(icon_path));
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		mod = new associationLineMode();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		canvas.setCurrentMode(mod);
		

	}
}