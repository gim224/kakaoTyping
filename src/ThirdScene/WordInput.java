package ThirdScene;

import javax.swing.*;

import fileIO.FileInput;
import fileIO.FileOutput;

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
	JButton delBtn;
	FileInput input = new FileInput("txt/word.txt", "@");

	MyCenterPanel() {
		textfield = new JTextField(40);
		btn = new JButton("Add");
		delBtn = new JButton("Del");
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textarea.append(textfield.getText().trim() + "\n");
				addWord = textfield.getText();
				new FileOutput("txt/word.txt", addWord);
				textfield.setText("");				
				
			}
		});
		
		/////////////
//		delBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				for(int i =0; i<input.size();i++){
//				if(textfield.getText().equals(input.getOneWord(i))){
//					
//				}
//				
//				textarea.append(textfield.getText() + "\n");
//				addWord = textfield.getText();
//				new FileOutput("txt/word.txt", addWord);
//				textfield.setText("");				
//				
//			}
//		});
		
		
		String area="";
		textarea = new JTextArea("", 35, 70);
		
		for(int i=0;i<input.size();i++){
			area += input.getOneWord(i) + "\n";
		}
		textarea.setText(area);
		textarea.setEditable(false);
		add(new JScrollPane(textarea));
		add(textfield);
		add(btn);
		add(delBtn);

	}
}

class WordOutput {
	public WordOutput(String s) {
		try {
			String name = "txt/word.txt";
			RandomAccessFile raf = new RandomAccessFile(name, "rw");
			raf.seek(raf.length());
			raf.writeUTF("\n" + s + "@");
			raf.close();
		} catch (IOException e) {
		}

	}
}