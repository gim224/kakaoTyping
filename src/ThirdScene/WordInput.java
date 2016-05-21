package ThirdScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordInput extends JPanel {
	public WordInput() {
		setLayout(new BorderLayout());
		add(new MyCenterPanel(), BorderLayout.CENTER);
	}
}

class MyCenterPanel extends JPanel {
	JTextField textfield;
	JButton btn;
	JTextArea textarea;

	MyCenterPanel() {
		textfield = new JTextField(40);
		btn = new JButton("Add");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textarea.append(textfield.getText() + "\n");
			}
		});
		textarea = new JTextArea("hello\n", 35, 70);
		add(new JScrollPane(textarea));
		add(textfield);
		add(btn);
	}
}
