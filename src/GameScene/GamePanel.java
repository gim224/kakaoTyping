package GameScene;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {
	public GamePanel(){
		setLayout(new BorderLayout());
		add(new InputPanel(),BorderLayout.SOUTH);
	}
}

class InputPanel extends JPanel{
	public InputPanel(){
		add(new JLabel("ют╥б"));
		JTextField textField = new JTextField(30);
		//textField.requestFocus();
		add(textField);
		add(new JButton("submit"));
	}
}
