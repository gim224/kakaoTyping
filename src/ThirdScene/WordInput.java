package ThirdScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.RandomAccessFile;

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
	String addWord;
	
	MyCenterPanel() {
		textfield = new JTextField(40);
		btn = new JButton("Add");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textarea.append(textfield.getText() + "\n");
				addWord = textfield.getText();
				new WordOutput(addWord);
			}
		});
		
		textarea = new JTextArea("", 35, 70);
		add(new JScrollPane(textarea));
		add(textfield);
		add(btn);
	}
}
class WordOutput {
	public WordOutput(String s) {
		try {
			String name = "txt/word.txt";
			RandomAccessFile raf = new RandomAccessFile(name, "rw");
			raf.seek(raf.length());
			raf.writeBytes("\r\n" +s+"@");
		}
		catch(IOException e) {
		}
	}
}