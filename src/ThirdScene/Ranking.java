package ThirdScene;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import fileIO.ObjInput;
import kakaoTyping.User;

public class Ranking extends JPanel implements Comparable {
	
	
	
	String header[] = { "순위", "이름", "점수", "목표오타율", "오타율", "생성날짜" };
	String contents[][];

	Vector<User> userInfo = new Vector<User>();
	Vector<User> user = userInfo;

	ObjInput obip;
	int fsize;

	public Ranking() {

		File file = new File("txt/user.dat");

		if (file.length() != 0) {
			obip = new ObjInput();
			userInfo = obip.getUserVector();

			fsize = userInfo.size();

			contents = new String[fsize][6];

			for (int i = 0; i < fsize; i++)
				for (int j = 0; j < 6; j++)
					contents[i][j] = "";

			Collections.sort(userInfo);

			for (int i = 0; i < fsize; i++) {
				contents[i][0] = Integer.toString(i + 1);
				contents[i][1] = userInfo.get(i).getName();
				contents[i][2] = Integer.toString(userInfo.get(i).getScore());
				contents[i][3] = Integer.toString(userInfo.get(i).getGoal()) + "%";
				contents[i][4] = Double.toString(userInfo.get(i).getMiss()) + "%";
				contents[i][5] = userInfo.get(i).getDate();
			}
		}

		setLayout(new BorderLayout());
		JTable table = new JTable(contents, header);
		table.setShowHorizontalLines(false);
		table.setEnabled(false);

		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane, BorderLayout.CENTER);

		JButton back = new JButton("뒤로가기");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton back = (JButton) e.getSource();
				JPanel p = (JPanel) back.getParent();
				Container c = back.getTopLevelAncestor();

				p.removeAll();
				p.setVisible(false);
				c.remove(p);
				c.add(new ThirdScene());
				c.setVisible(true);
			}
		});
		add(back, BorderLayout.SOUTH);

	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 1;
	}
}