package FourthScene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

import ButtonForChange.ButtonForChangePanel;
import ButtonForChange.ButtonForChangeSplitpane;
import GameScene.GameScene;

public class SetPanel extends JPanel {
	public SetPanel() {
		setLayout(new GridLayout(3, 1));
		add(new LevelPanel());
		add(new ImagePanel());
		ButtonForChangeSplitpane bttn = new ButtonForChangeSplitpane("START",new GameScene());
		
		
		// MoveButton bttn = new MoveButton("START", new GameScene());
		add(bttn);
	}
}

class LevelPanel extends JPanel {
	public LevelPanel() {
		setLayout(new BorderLayout());
		String[] item = { "Level 1", "Level 2", "Level 3", "Level 4" };
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