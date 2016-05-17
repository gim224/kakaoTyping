package ButtonForChange;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ButtonForChangePanel extends JButton {
	public ButtonForChangePanel(String s, JComponent after) {
		super(s);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();

				JPanel p = (JPanel) bttn.getParent();
				Container c = bttn.getTopLevelAncestor();

				p.removeAll();
				p.setVisible(false);

				c.add(after);
				c.setVisible(true);
			}
		});
	}
}