package kakaoTyping;

import java.awt.*;
import javax.swing.*;

public class ThirdScene extends JPanel {
	ImageIcon icon = new ImageIcon("images/라이언.jpg");
	Image img = icon.getImage();
	public ThirdScene() {
		setLayout(null);
		MyButton bttn1 = new MyButton("Alone", 330, 100);
		add(bttn1);
		MyButton bttn2 = new MyButton("Ranking", 130, 500);
		add(bttn2);
		MyButton bttn3 = new MyButton("WordInput", 530, 500);
		add(bttn3);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}