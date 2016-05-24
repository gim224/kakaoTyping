package GameScene;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fileIO.FileInput;
import thread.FallingLabel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	FileInput input = new FileInput("txt/word.txt", "@");

	public GamePanel() {
		FallingLabel fl = new FallingLabel(input.getOneWordRandom(), (int) (Math.random() * 5 + 1));
		
		setLayout(new BorderLayout());
		add(new InputPanel(fl,input), BorderLayout.SOUTH);
		add(new CreateWordPanel(fl), BorderLayout.CENTER);
	}
}

@SuppressWarnings("serial")
class CreateWordPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/gameback.jpg");
	Image img = background.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	public CreateWordPanel(FallingLabel fl) {
		setLayout(null);

		add(fl);

		// Exception in thread "main" java.lang.NullPointerException
		// Font currentFont = fl.getGraphics().getFont();//
		// FontMetrics fm = fl.getFontMetrics(currentFont);
		// int w = fm.stringWidth(fl.getText());
		// fl.setSize(w, fm.getHeight());
		// add(fl);

	}

}

@SuppressWarnings("serial")
class InputPanel extends JPanel {
	public InputPanel(FallingLabel fl,FileInput input) {
		add(new JLabel("입력", SwingConstants.CENTER));

		JTextField textField = new JTextField("", 30);
		//
		textField.getText();
		add(textField);
		JButton submit = new JButton("submit");
		add(submit);
		// System.out.println(fl.getText());
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals(fl.getText())) {
					fl.finish();					
				}
				textField.setText("");

			}
		});
	}
}
