package kakaoTyping;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScorePanel extends JPanel {
	public ScorePanel() {
		this.setLayout(new GridLayout(3, 1));
		add(new NamePanel1("LEVEL", "SCORE", Color.RED, Color.ORANGE));
		add(new NamePanel2("LIFE", "TIME", Color.YELLOW, Color.green));
		add(new ImagePanel());
	}
}

class NamePanel1 extends JPanel {
	public NamePanel1(String s1, String s2, Color bg1, Color bg2) {
		setLayout(new GridLayout(4, 1));

		JLabel label1 = new JLabel(s1);
		label1.setBackground(bg1);
		label1.setOpaque(true);
		label1.setVerticalAlignment(getX() / 2);
		label1.setHorizontalAlignment(getY() / 2);
		JLabel label2 = new JLabel(s2);
		label2.setBackground(bg2);
		label2.setOpaque(true);
		label2.setVerticalAlignment(getX() / 2);
		label2.setHorizontalAlignment(getY() / 2);

		add(label1);
		// add(new JPanel());
		add(new JLabel("몇단계?"));
		add(label2);
		// add(new JPanel());
		add(new JLabel("몇 점?"));
	}
}

class NamePanel2 extends JPanel {
	public NamePanel2(String s1, String s2, Color bg1, Color bg2) {
		setLayout(new GridLayout(4, 1));

		JLabel label1 = new JLabel(s1);
		label1.setBackground(bg1);
		label1.setOpaque(true);
		label1.setVerticalAlignment(getX() / 2);
		label1.setHorizontalAlignment(getY() / 2);
		JLabel label2 = new JLabel(s2);
		label2.setBackground(bg2);
		label2.setOpaque(true);
		label2.setVerticalAlignment(getX() / 2);
		label2.setHorizontalAlignment(getY() / 2);

		add(label1);
		add(new LifePanel());
		add(label2);
		// add(new JPanel());
		add(new JLabel("게임시간?"));
	}
}

class LifePanel extends JPanel {
	public LifePanel() {
		setLayout(new GridLayout(1, 5));

		JButton[] life = new JButton[5];
		ImageIcon[] icon = new ImageIcon[2];

		icon[0] = new ImageIcon("images/DisableHeart.png");
		icon[1] = new ImageIcon("images/EnableHeart.png");

		for (int i = 0; i < life.length; i++) {
			life[i] = new JButton(icon[1]);
			add(life[i]);
		}
	}
}

class ImagePanel extends JPanel {
	ImageIcon icon = new ImageIcon("images/gg.png");
	Image img = icon.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 5, 5, 175, 225, this);
	}
}