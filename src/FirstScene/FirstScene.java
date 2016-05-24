package FirstScene;

import java.awt.*;

import java.io.FileNotFoundException;

import javax.swing.*;

import ButtonForChange.ButtonForChangePanel;
import UserManageScene.UserManageScene;

public class FirstScene extends JPanel {
	ImageIcon icon = new ImageIcon("images/kakao.png");
	Image img = icon.getImage();

	public FirstScene() {
		setLayout(null);
		ButtonForChangePanel bttn;
		try {
			bttn = new ButtonForChangePanel("Start!", new UserManageScene());
			bttn.setSize(400, 50);
			bttn.setLocation(200, 250);
			add(bttn);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.fillRect(getX(), getY() - 40, getWidth(), getHeight() + 10);
		g.drawImage(img, 0, 350, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 70));
		g.drawString("KaKao Typing", 170, 150);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
		// g.drawString("Press AnyKey...!", 550, 300);
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
		g.drawString("made by GT JY", 600, 680);
	}
}
