package FourthScene;

import java.awt.*;
import javax.swing.*;

import fileIO.FileInput;

public class ExpPanel extends JPanel {
	ExpPanel() {
		this.setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		add(new MyExpPanel(), BorderLayout.CENTER);

	}
}

class MyExpPanel extends JPanel {
	JTextArea textarea;

	MyExpPanel() {

		textarea = new JTextArea("1. 게임 설명" + "\n" + "떨어지는 글자를 입력하여 맞추는 게임입니다." + "\n" + "\n" +

		"정확히 글자를 입력하지 않으면 생명이 깎입니다." + "\n" + "바닥까지 떨어지면 생명이 깎입니다." + "\n" + "\n" +

		"2. 아이템" + "\n" + "F1 키와 F2 키는 위급한 상황에 사용할 수 있습니다." + "\n" + "\n" +

		"F1 => 떨어지는 글자 위로 올리기" + "\n" + "F2 => 화면에 모든 글자 터뜨리기" + "\n" + "\n" +

		"게임당 1회 사용할 수 있으므로 신중히 사용하십시오.");
		textarea.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setPreferredSize(new Dimension(530, 770));
		add(scroll);
	}
}