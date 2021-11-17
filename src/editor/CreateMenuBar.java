package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CreateMenuBar{
	private JMenuBar menubar;
	private Canvas canvas;
	private JMenuItem mntmChangeObjectName, mntmGroup, mntmUngroup;
	

	//create menubar and setting eventlistener
	public CreateMenuBar(){
		canvas = Canvas.getInstance();
		menubar = new JMenuBar();
		initialize();
	}
	
	public void initialize() {
		
		JMenu mnNewMenu = new JMenu("File");
		menubar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menubar.add(mnNewMenu_1);
		
		mntmGroup = new JMenuItem("Group");
		mnNewMenu_1.add(mntmGroup);
		
		mntmUngroup = new JMenuItem("Ungroup");
		mnNewMenu_1.add(mntmUngroup);
		
		mntmChangeObjectName = new JMenuItem("Change Object Name");
		mnNewMenu_1.add(mntmChangeObjectName);
		
		mntmUngroup.setEnabled(false);
		mntmChangeObjectName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.changeObjectName();
			}	
		});
		
		mntmGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.group();
				mntmUngroup.setEnabled(true);
			}	
		});
		
		mntmUngroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.ungroup();
			}	
		});
		
		menubar.setVisible(true);
	}
	
	public JMenuBar getMenuBar() {
		return menubar;
	}

}
