package ThirdScene;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.*;

import fileIO.ObjInput;
import kakaoTyping.User;

public class Ranking extends JPanel {

	String header[] = { "Rank", "Name", "Score", "Goal", "Accuracy", "Date" };
	String contents[][];

	Vector<User> userInfo = new Vector<User>();
	ObjInput obip = new ObjInput();
	int fsize;
	
	public Ranking() {
		
		userInfo = obip.getUserVector();
		fsize = userInfo.size();
		
		contents = new String[fsize][6];
		
		for(int i=0; i<fsize; i++)
			for(int j=0; j<6; j++)
				contents[i][j]="";
		
		for(int i=0; i<fsize; i++) {
			contents[i][1]= userInfo.get(i).getName();
			contents[i][2]= Integer.toString(userInfo.get(i).getScore());
			contents[i][3]= Integer.toString(userInfo.get(i).getGoal());
			contents[i][4]= Double.toString(userInfo.get(i).getMiss());
			contents[i][5]= userInfo.get(i).getDate();
					}
		setLayout(new BorderLayout());
		JTable table = new JTable(contents, header);
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane, BorderLayout.CENTER);
	}
}
