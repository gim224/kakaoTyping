package FourthScene;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class SetPanel extends JPanel{
	public SetPanel() {
		setLayout(new GridLayout(3,1));
		add(new LevelPanel());
		JButton bttn = new JButton("START");
		add(bttn);
		add(new ImagePanel());
	}
}

class LevelPanel extends JPanel {
	public LevelPanel(){
		setLayout(new BorderLayout());
		String[] item={"Level 1", "Level 2", "Level 3", "Level 4"};
		SpinnerListModel listModel = new SpinnerListModel(item);
		JSpinner level = new JSpinner();
		level.setModel(listModel);
		
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