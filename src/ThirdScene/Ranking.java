package ThirdScene;

import java.awt.BorderLayout;

import javax.swing.*;

public class Ranking extends JPanel {

	String header[] = { "Rank", "Name", "Score", "Goal", "Accuracy", "Date" };

	String contents[][] = {};

	public Ranking() {
		setLayout(new BorderLayout());
		JTable table = new JTable(contents, header);
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane, BorderLayout.CENTER);
	}
}
