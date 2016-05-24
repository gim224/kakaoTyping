package UserManageScene;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.*;

import ButtonForChange.ButtonForChangePanel;
import ThirdScene.ThirdScene;

import fileIO.ObjInput;
import kakaoTyping.User;

@SuppressWarnings("serial")
public class UserManageScene extends JPanel {
	private Vector<User> user = new Vector<User>();
	private Vector<String> name = new Vector<String>();

	public UserManageScene() throws FileNotFoundException {

		this.setLayout(null);

		makeJList();
	}

	void makeJList() throws FileNotFoundException {
		final int LOC_X = 70;
		final int LOC_Y = 600;
		final int SIZ_X = 200;

		//
		JLabel titleLabel = new JLabel("Select User");
		titleLabel.setSize(743, 50);
		titleLabel.setLocation(20, 10);
		titleLabel.setVerticalAlignment(getX() / 2);
		titleLabel.setHorizontalAlignment(getY() / 2);
		titleLabel.setOpaque(true);
		titleLabel.setBackground(Color.YELLOW);

		add(titleLabel);
		//
		//
		JLabel imgLabel = new JLabel(new ImageIcon("images/userEnroll/all.png"));
		imgLabel.setSize(743, 472);
		imgLabel.setLocation(20, 75);
		imgLabel.setOpaque(true);
		// imgLabel.setBackground(Color.CYAN);
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
		// ObjOutput output = new ObjOutput()
		/////////////////////////////////////////////////////.txt or .dat
		File file = new File("txt/user.txt");
		if (file.length() != 0) {
			ObjInput input = new ObjInput();
			user = input.getUserVector();
			for (int i = 0; i < user.size(); i++) {
				name.add(user.get(i).getName());
				System.out.println(user.get(i).getName());
				System.out.println(user.size());
				System.out.println(user.get(i).getBirth());
				System.out.println("------");

			}
		}
		//
		//
		JList<String> list = new JList<String>(name);

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
		enroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				JPanel p = (JPanel) btn.getParent();
				try {
					new EnrollUser(user, p);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		delete.setSize(SIZ_X - 100, 20);
		delete.setLocation(LOC_X + 100, LOC_Y + 77);
		add(delete);
		//
		//
		ButtonForChangePanel bttn = new ButtonForChangePanel("Select", new ThirdScene());
		bttn.setSize(400, 100);
		bttn.setLocation(LOC_X + 250, LOC_Y - 20);
		add(bttn);

	}
}
