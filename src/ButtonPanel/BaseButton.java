package ButtonPanel;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;*/

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import editor.Canvas;
import mode.BaseMode;




public abstract class BaseButton extends JToggleButton implements ActionListener{
	
	protected String icon_path;
	protected GridBagConstraints gbc;
	protected Canvas canvas;
	protected BaseMode mod;
	
	public BaseButton() {
		canvas = Canvas.getInstance();
		this.addActionListener(this);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBackground(Color.GRAY);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 5);
	}
	
	public abstract void actionPerformed(ActionEvent arg0);
	
	public BaseMode getMod() {return mod;}
	
	public GridBagConstraints getGridBagConstraints() {return gbc;}
	


	

	

}