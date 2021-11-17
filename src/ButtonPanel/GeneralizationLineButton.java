package ButtonPanel;


import java.awt.event.ActionEvent;


import javax.swing.ImageIcon;

import mode.generalizationLineMode;



public class GeneralizationLineButton extends BaseButton{
	
	//private Generalization gl;

	public GeneralizationLineButton() {
		icon_path = "img/general.png";
		this.setIcon(new ImageIcon(icon_path));
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		mod = new generalizationLineMode();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		canvas.setCurrentMode(mod);
		
		
	}
}