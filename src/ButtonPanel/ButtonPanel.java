package ButtonPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JToolBar;





public class ButtonPanel{

	

	private ArrayList<BaseButton> buttons;
	private ButtonGroup buttonGroup;
	
	
	public ButtonPanel() {
		buttonGroup = new ButtonGroup();
		buttons = new ArrayList<BaseButton>();
		buttons.add(new SelectButton());
		buttons.add(new AssociationLineButton());
		buttons.add(new GeneralizationLineButton());
		buttons.add(new CompositionLineButton());
		buttons.add(new UseCaseButton());
		buttons.add(new ClassButton());
		


	}
	
	public void setBtnPanel(Container container) {
		for(int i = 0; i < buttons.size(); i++) {
			container.add(buttons.get(i), buttons.get(i).getGridBagConstraints());
			buttonGroup.add(buttons.get(i));	
		}
		buttons.get(0).doClick();
	}

	
	

}
