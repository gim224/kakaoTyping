package FirstScene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import UserManageScene.UserManageScene;

public class FirstScene extends JPanel {
	ImageIcon icon = new ImageIcon("images/kakao.png");
	Image img = icon.getImage();

	public FirstScene() {
		setLayout(null);

		JButton bttn = new JButton("Start!");
		bttn.setSize(400, 50);
		bttn.setLocation(200, 250);
		add(bttn);

		bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();

				JPanel p = (JPanel) bttn.getParent();
				Container c = bttn.getTopLevelAncestor();

				p.removeAll();
				c.remove(p);
				p.setVisible(false);				
				try {					
					c.add(new UserManageScene());					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				c.setVisible(true);
			}
		});

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
