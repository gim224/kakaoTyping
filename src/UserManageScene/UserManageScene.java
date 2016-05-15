package UserManageScene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;

public class UserManageScene extends JFrame {
	public UserManageScene() throws FileNotFoundException {
		super("KaKao Typing");
		setSize(400, 400);
		this.setLayout(null);

		makeJList();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	void makeJList() throws FileNotFoundException {
		final int LOC_X = 150;
		final int LOC_Y = 140;
		final int SIZ_X = 200;

		//
		JLabel titleLabel = new JLabel("Enroll user");
		titleLabel.setSize(320, 65);
		titleLabel.setLocation(30, 30);
		titleLabel.setVerticalAlignment(getX() / 2);
		titleLabel.setHorizontalAlignment(getY() / 2);
		titleLabel.setOpaque(true);
		titleLabel.setBackground(Color.YELLOW);

		add(titleLabel);
		//
		//
		JLabel imgLabel = new JLabel("IMG");
		imgLabel.setSize(110, 127);
		imgLabel.setLocation(30, LOC_Y - 30);
		imgLabel.setOpaque(true);
		imgLabel.setBackground(Color.CYAN);
		add(imgLabel);
		//
		//
		JLabel userList = new JLabel("USER LIST");
		userList.setSize(SIZ_X - 1, 30);
		userList.setLocation(LOC_X, LOC_Y - 30);
		userList.setOpaque(true);
		userList.setBackground(Color.GRAY);
		userList.setVerticalAlignment(getX() / 2);
		userList.setHorizontalAlignment(getY() / 2);
		add(userList);
		//
		//
		File file = new File("txt/user.txt");
		Scanner sc = new Scanner(file);
		sc.useDelimiter("@");
		Vector<String> userEntry = new Vector<String>();
		while (sc.hasNext()) {
			userEntry.add(sc.next());
		}
		//
		//
		JList list = new JList<String>(userEntry);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane pane = new JScrollPane(list);
		pane.setSize(SIZ_X, 77);
		pane.setLocation(LOC_X, LOC_Y);
		add(pane);
		//
		//
		JButton enroll = new JButton("enroll");
		JButton delete = new JButton("delete");
		enroll.setSize(SIZ_X - 100, 20);
		enroll.setLocation(LOC_X, LOC_Y + 77);
		add(enroll);
		delete.setSize(SIZ_X - 100, 20);
		delete.setLocation(LOC_X + 100, LOC_Y + 77);
		add(delete);
		//
		//
		JButton bttn = new JButton("Select");
		bttn.setSize(200, 50);
		bttn.setLocation(90, 260);
		add(bttn);
	}
}
