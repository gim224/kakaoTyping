package ThirdScene;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fileIO.FileInput;
import fileIO.FileOutput;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

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
	JButton back;
	FileInput input = new FileInput("txt/word.txt", "@");

	private void isTextFieldUpdate(JButton btn) {
		if ((!(textfield.getText().isEmpty()))) {
			btn.setEnabled(true);
			delBtn.setEnabled(true);
		} else {
			btn.setEnabled(false);
			delBtn.setEnabled(false);
		}
	}

	MyCenterPanel() {
		setLayout(new BorderLayout());
		textfield = new JTextField("", 40);

		btn = new JButton("Add");
		delBtn = new JButton("Del");
		back = new JButton("뒤로가기");
		btn.setEnabled(false);
		delBtn.setEnabled(false);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textarea.append(textfield.getText().trim() + "\n");
				addWord = textfield.getText();
				new FileOutput("txt/word.txt", addWord);
				textfield.setText("");
			}
		});

		textfield.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(btn);
			}

			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(btn);
			}

			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(btn);
			}
		});

		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String area = "";
				new WordOutputDel(textfield.getText(), "@");
				textfield.setText("");
				input = new FileInput("txt/word.txt", "@");
				for (int i = 0; i < input.size(); i++) {
					area += input.getOneWord(i) + "\n";
				}

				textarea.setText(area);

			}
		});

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				JPanel p = (JPanel) btn.getParent().getParent();
				Container c = p.getTopLevelAncestor();

				p.removeAll();
				p.setVisible(false);

				c.add(new ThirdScene());
				c.setVisible(true);

			}
		});

		String area = "";
		textarea = new JTextArea("", 40, 70);

		for (int i = 0; i < input.size(); i++) {
			area += input.getOneWord(i) + "\n";
		}
		textarea.setText(area);
		textarea.setEditable(false);
		
		add(new JScrollPane(textarea), BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		southPanel.add(textfield);
		southPanel.add(btn);
		southPanel.add(delBtn);
		southPanel.add(back);

	}
}