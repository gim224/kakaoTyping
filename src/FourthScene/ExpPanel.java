package FourthScene;

import java.awt.*;
import javax.swing.*;

public class ExpPanel extends JPanel{
	ExpPanel(){
		this.setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		add(new MyExpPanel(), BorderLayout.CENTER);
		
	}
}
class MyExpPanel extends JPanel{
	JTextArea textarea;
	MyExpPanel(){
		textarea = new JTextArea("Game 방법");
		textarea.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setPreferredSize(new Dimension(530, 770));
		add(scroll);
	}
}