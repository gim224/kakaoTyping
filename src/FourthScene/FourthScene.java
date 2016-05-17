package FourthScene;

import javax.swing.JSplitPane;

public class FourthScene extends JSplitPane{
	public FourthScene() {
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setDividerLocation(550);
		setEnabled(false);
		setDividerSize(20);
		
		ExpPanel expPanel = new ExpPanel();
		setLeftComponent(expPanel);
		SetPanel setPanel = new SetPanel();
		setRightComponent(setPanel);
	}
}
