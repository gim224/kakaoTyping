package FirstScene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.*;

import UserManageScene.UserManageScene;

public class FirstScene extends JPanel {
	ImageIcon icon = new ImageIcon("images/back.jpg");
	Image img = icon.getImage();
	int x=-100;
	int y=-100;
	public FirstScene() {
		setLayout(null);

		JButton bttn = new JButton("Start!");
		bttn.setSize(200, 50);
		bttn.setLocation(300, 600);
		add(bttn);

		bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();

				JPanel p = (JPanel) bttn.getParent();
				Container c = bttn.getTopLevelAncestor();

				p.removeAll();
				c.remove(p);
				p.setVisible(false);
				c.add(new UserManageScene());

				c.setVisible(true);
			}
		});
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				x = (int)e.getPoint().getX();
				y = (int)e.getPoint().getY();
				
			}
		});

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.fillRect(getX(), getY() - 40, getWidth(), getHeight() + 10);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		g.drawString("NOPE Typing", 70, 220);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
		//g.drawString("Press AnyKey...!", 550, 300);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
		g.drawString("made by GT&JY", 600, 680);
		
		//g.fillRect(x, y, 100, 100);
			
		
	}
}
