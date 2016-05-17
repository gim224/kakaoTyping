package ThirdScene;

import java.awt.*;
import javax.swing.*;

import ButtonForChange.ButtonForChangePanel;
import FourthScene.FourthScene;
import GameScene.GameScene;

public class ThirdScene extends JPanel {
	ImageIcon icon = new ImageIcon("images/lion.jpg");
	Image img = icon.getImage();

	public ThirdScene() {
		setLayout(null);
		ButtonForChangePanel bttn1 = new ButtonForChangePanel("Alone", new GameScene());
		bttn1.setLocation(350, 50);
		bttn1.setSize(100, 100);
		add(bttn1);
		ButtonForChangePanel bttn2 = new ButtonForChangePanel("Ranking", new Ranking());
		bttn2.setLocation(100, 500);
		bttn2.setSize(100, 100);
		add(bttn2);
		ButtonForChangePanel bttn3 = new ButtonForChangePanel("WordInput", new WordInput());
		bttn3.setLocation(570, 500);
		bttn3.setSize(100, 100);
		add(bttn3);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}