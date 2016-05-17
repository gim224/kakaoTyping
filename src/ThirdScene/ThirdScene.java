package ThirdScene;

import java.awt.*;
import javax.swing.*;

import GameScene.GameScene;
import kakaoTyping.MoveButton;

public class ThirdScene extends JPanel {
	ImageIcon icon = new ImageIcon("images/lion.jpg");
	Image img = icon.getImage();

	public ThirdScene() {
		setLayout(null);
		MoveButton bttn1 = new MoveButton("Alone", new GameScene());
		bttn1.setLocation(330, 100);
		bttn1.setSize(100, 100);
		add(bttn1);
		MoveButton bttn2 = new MoveButton("Ranking", new Ranking());
		bttn2.setLocation(130, 500);
		bttn2.setSize(100, 100);
		add(bttn2);
		MoveButton bttn3 = new MoveButton("WordInput", new WordInput());
		bttn3.setLocation(530, 500);
		bttn3.setSize(100, 100);
		add(bttn3);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}