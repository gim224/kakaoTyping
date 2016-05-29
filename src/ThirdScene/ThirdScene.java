package ThirdScene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import FourthScene.FourthScene;

@SuppressWarnings("serial")
public class ThirdScene extends JPanel {
	ImageIcon icon = new ImageIcon("images/lion.jpg");
	Image img = icon.getImage();

	public ThirdScene() {
		setLayout(null);
		JButton alone = new JButton("Alone");
		alone.setLocation(350, 50);
		alone.setSize(100, 100);
		add(alone);
		alone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();

				JPanel p = (JPanel) bttn.getParent();
				Container c = bttn.getTopLevelAncestor();

				p.removeAll();
				p.setVisible(false);
				c.remove(p);
				c.add(new FourthScene());
				c.setVisible(true);
			}
		});

		JButton ranking = new JButton("Ranking");
		ranking.setLocation(100, 500);
		ranking.setSize(100, 100);
		add(ranking);

		ranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();

				JPanel p = (JPanel) bttn.getParent();
				Container c = bttn.getTopLevelAncestor();

				p.removeAll();
				p.setVisible(false);
				c.remove(p);
				c.add(new Ranking());
				c.setVisible(true);
			}
		});
		JButton wordInput = new JButton("WordInput");
		wordInput.setLocation(570, 500);
		wordInput.setSize(100, 100);
		add(wordInput);

		wordInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();

				JPanel p = (JPanel) bttn.getParent();
				Container c = bttn.getTopLevelAncestor();

				p.removeAll();
				p.setVisible(false);
				c.remove(p);
				c.add(new WordInput());
				c.setVisible(true);
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}