package GameScene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileIO.FileInput;
import thread.FallingLabel;

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
	FallingLabel fl;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	public CreateWordPanel() {
		setLayout(null);
		FileInput input = new FileInput("txt/word.txt", "@");

		// FallingLabel[] fl = new FallingLabel[3];
		// for (int i = 0; i < fl.length; i++) {
		// fl[i] = new
		// FallingLabel(input.getOneWordRandom(),(int)(Math.random()*5+1));
		// add(fl[i]);
		// }
		
		fl = new FallingLabel(input.getOneWord(0), (int) (Math.random() * 5 + 1));

		add(fl);

//		Font currentFont = fl.getGraphics().getFont();
//		
//		FontMetrics fm = fl.getFontMetrics(currentFont);
//		int w = fm.stringWidth(fl.getText());
//		fl.setSize(w, fm.getHeight());
//		add(fl);

	}

}

class InputPanel extends JPanel {
	public InputPanel() {

		add(new JLabel("입력"));

		add(new JLabel("Input"));

		JTextField textField = new JTextField(30);
		// textField.requestFocus();
		add(textField);
		add(new JButton("submit"));
	}
}
