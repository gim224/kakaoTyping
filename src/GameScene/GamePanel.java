package GameScene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kakaoTyping.FileInput;

public class GamePanel extends JPanel {
	public GamePanel() {
		setLayout(new BorderLayout());
		add(new InputPanel(), BorderLayout.SOUTH);
		add(new CreateWordPanel(), BorderLayout.CENTER);
	}
}

class CreateWordPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/gameback.jpg");
	Image img = background.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	public CreateWordPanel() {
		setLayout(null);
		FileInput input = new FileInput("txt/word.txt");
		
		JLabel[] wordLabel = new JLabel[input.size()];
		
		for (int i = 0; i < input.size(); i++) {
			wordLabel[i] = new JLabel(input.getOneWord(i));
			wordLabel[i].setSize(100, 20);
			wordLabel[i].setLocation(0, i*30);
			wordLabel[i].setOpaque(true);
			wordLabel[i].setBackground(Color.YELLOW);
			add(wordLabel[i]);
		}
		
		
		//add(wordLabel);
		//word.add(wordLabel);


	}

}

class InputPanel extends JPanel {
	public InputPanel() {
		add(new JLabel("Input"));
		JTextField textField = new JTextField(30);
		// textField.requestFocus();
		add(textField);
		add(new JButton("submit"));
	}
}
