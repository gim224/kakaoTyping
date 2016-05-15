package kakaoTyping;

import java.awt.*;
import javax.swing.*;

public class FirstScene extends JPanel {
	ImageIcon icon = new ImageIcon("images/kakao.png");
	Image img = icon.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.fillRect(getX(), getY() -40, getWidth(), getHeight()+10);
		g.drawImage(img, 0, 350, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 70));
		g.drawString("KaKao Typing", 170, 150);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
		g.drawString("Press AnyKey...!", 550, 300);
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
		g.drawString("made by GT�솯JY", 600, 680);
	}	
}