package ButtonPanel;

//import java.awt.Point;
import java.awt.event.ActionEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import mode.usecaseObjMode;




public class UseCaseButton extends BaseButton{
	

	
	public UseCaseButton() {
		icon_path = "img/usecase.png";
		this.setIcon(new ImageIcon(icon_path));
		gbc.gridx = 0;
		gbc.gridy = 5;
		
		mod = new usecaseObjMode();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		canvas.setCurrentMode(mod);
		

	}
}