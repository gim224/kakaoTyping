package ThirdScene;

import java.awt.BorderLayout;

import javax.swing.*;

public class Ranking extends JPanel {
	String header[] = { "����", "�̸�", "����", "��ǥ ��Ȯ��", "��Ÿ��", "��¥" };
	String contents[][] = {};

	public Ranking() {
		setLayout(new BorderLayout());
		JTable table = new JTable(contents, header);
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane, BorderLayout.CENTER);
		/*JButton bttn = new JButton("?��Back");
		bttn.setSize(130, 30);
		add(bttn, BorderLayout.SOUTH);*/
	}
}