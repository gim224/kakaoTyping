package FourthScene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;




import GameScene.GameScene;

public class SetPanel extends JPanel {
	public SetPanel() {
		setLayout(new GridLayout(3, 1));
		add(new LevelPanel());
		add(new ImagePanel());
		JButton bttn = new JButton("START");		
		
		add(bttn);
		
		bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();
				JPanel p = (JPanel) bttn.getParent();
				JSplitPane p2 = (JSplitPane) p.getParent();
				Container c = p2.getTopLevelAncestor();
				p2.removeAll();
				p2.setVisible(false);
				c.remove(p2);
				c.add(new GameScene());
				c.setVisible(true);
			}
		});
	}
}

class LevelPanel extends JPanel {
	public LevelPanel() {
		setLayout(new BorderLayout());

		JFormattedTextField ftf = null;
		String[] item = { "Level 1", "Level 2", "Level 3", "Level 4" };
		SpinnerListModel listModel = new SpinnerListModel(item);
		JSpinner level = new JSpinner(listModel);
		JComponent editor = level.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			ftf = ((JSpinner.DefaultEditor) editor).getTextField();
		}
		if (ftf != null) {
			ftf.setColumns(8);
			ftf.setHorizontalAlignment(JTextField.CENTER);
			ftf.setEditable(false);
		}
		JLabel level_label = new JLabel();
		level_label.setText("Level Select");
		add(level_label, BorderLayout.NORTH);
		add(level, BorderLayout.CENTER);
	}
}

class ImagePanel extends JPanel {
	ImageIcon icon = new ImageIcon("images/fight.gif");
	Image img = icon.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 30, 30, 170, 170, this);
	}
}