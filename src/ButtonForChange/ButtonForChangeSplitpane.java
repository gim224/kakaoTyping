package ButtonForChange;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import GameScene.GameScene;

public class ButtonForChangeSplitpane extends JButton {
	public ButtonForChangeSplitpane(String s, JComponent after) {
		super(s);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();
				JPanel p = (JPanel) bttn.getParent();
				JSplitPane p2 = (JSplitPane) p.getParent();
				Container c = p2.getTopLevelAncestor();
				p2.removeAll();
				p2.setVisible(false);
				c.add(new GameScene());
				c.setVisible(true);
			}
		});
	}
}
