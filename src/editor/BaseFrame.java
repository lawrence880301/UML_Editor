package editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ButtonPanel.BaseButton;
import ButtonPanel.ButtonPanel;
import ButtonPanel.AssociationLineButton;
import ButtonPanel.ClassButton;
import ButtonPanel.CompositionLineButton;
import ButtonPanel.GeneralizationLineButton;
import ButtonPanel.SelectButton;
import ButtonPanel.BaseButton;
import ButtonPanel.UseCaseButton;






public class BaseFrame{

	private JFrame frmUmlEditer;
	private	Container container;
	private GridBagLayout gridBagLayout;
	private ButtonGroup buttonGroup;
	private ArrayList<BaseButton> buttons;
	private Canvas canvas;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaseFrame frame = new BaseFrame();

				 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BaseFrame() {
		
		init_Frame();
		container = frmUmlEditer.getContentPane();
		
		CreateMenuBar menubar = new CreateMenuBar();
		frmUmlEditer.setJMenuBar(menubar.getMenuBar());

		//initialize layout, using gridbagconstraint
		init_Layout();

		
		canvas = Canvas.getInstance();
		container.add(canvas, canvas.getGridBagConstraints());
		
		
		ButtonPanel btnpanel = new ButtonPanel();
		btnpanel.setBtnPanel(container);
		
		
		getfrmUmlEditer().setVisible(true);
		
	}
	

	private void init_Frame() {
		frmUmlEditer = new JFrame();
		frmUmlEditer.setResizable(true);
		frmUmlEditer.setTitle("UML editer");
		frmUmlEditer.setBounds(100, 100, 800, 600);
		frmUmlEditer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	
	private void init_Layout() {
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {200, 600};
		gridBagLayout.rowHeights = new int[] {100, 100, 100, 100, 100, 100};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		container.setLayout(gridBagLayout);
	}
	

	public JFrame getfrmUmlEditer() {return this.frmUmlEditer;}
	

}



	  


