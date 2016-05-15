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
	
	public CreateWordPanel(){
		setLayout(null);
		Vector<JLabel> word = new Vector<JLabel>();
		JLabel wordLabel = new JLabel("zz");
		wordLabel.setSize(100, 20);
		wordLabel.setLocation(0,0);
		wordLabel.setOpaque(true);
		wordLabel.setBackground(Color.YELLOW);
		add(wordLabel);
		word.add(wordLabel);
		
	}

}

class InputPanel extends JPanel {
	public InputPanel() {
		add(new JLabel("ют╥б"));
		JTextField textField = new JTextField(30);
		// textField.requestFocus();
		add(textField);
		add(new JButton("submit"));
	}
}
