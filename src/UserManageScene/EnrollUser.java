package UserManageScene;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Image;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class EnrollUser extends JFrame {
	int currentId;
	JLabel imgLabel = new JLabel();





	
//	ImageIcon icon = new ImageIcon("images/lion.jpg");
//	Image background = icon.getImage();
//	
//	public void paintComponent(Graphics g) {
//		paintComponent(g);
//		
//		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
//	}

	public EnrollUser() throws FileNotFoundException {
		super("KaKao Typing");
		setSize(400, 400);
		this.setLayout(null);


		// this.setLocation(w/2, h/2);
		makeEnrollUser();
		setLocation(super.getWidth() / 2, super.getHeight() / 2);

		
		makeEnrollUser();
		

		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	void makeEnrollUser() {
		final int LOC_X = 200;
		final int LOC_Y = 140;
		final int SIZ_X = 150;

		//


		JLabel titleLabel = new JLabel("Sign Up");

		titleLabel.setSize(320, 65);
		titleLabel.setLocation(30, 30);
		//titleLabel.setVerticalAlignment();
		//titleLabel.setHorizontalAlignment(getY() / 2);
		titleLabel.setOpaque(true);
		titleLabel.setBackground(Color.YELLOW);

		add(titleLabel);
		//
		//
		currentId = 0;
		ImageIcon[] img = new ImageIcon[3];
		img[0] = new ImageIcon("images/mugi.jpg");
		img[1] = new ImageIcon("images/tube.jpg");
		img[2] = new ImageIcon("images/prodo.jpg");
		imgLabel = new JLabel(img[currentId]);

		//
		ImageIcon iconLeft = new ImageIcon("images/left.png");
		JButton imgLeft = new JButton(iconLeft);
		imgLeft.setSize(25, 25);
		imgLeft.setLocation(30, LOC_Y + 97);

		add(imgLeft);

		ImageIcon iconRight = new ImageIcon("images/right.png");
		JButton imgRight = new JButton(iconRight);
		imgRight.setSize(25, 25);
		imgRight.setLocation(115, LOC_Y + 97);
		add(imgRight);
		//

		imgLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentId--; // �̹��� ��ȣ ����. ���� �̹���
				currentId += img.length; // currentId�� ������ �� �� �ֱ� ������ 4�� ����
				currentId %= img.length; // 4 �� �Ѵ� ���� ���� ���� �̹��� ������ ������ ����
				imgLabel.setIcon(img[currentId]); // �̹��� ���̺� �̹��� ����
			}
		});

		// ������ ȭ��ǥ ��ư�� Action ������ �ޱ�
		imgRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentId++; // �̹��� ��ȣ ����. ���� �̹���
				currentId %= img.length; // 4 �� �Ѵ� ���� ���� ���� �̹��� ������ ������ ����
				imgLabel.setIcon(img[currentId]); // �̹��� ���̺� �̹��� ����
			}
		});

		imgLabel.setSize(110, 127);
		imgLabel.setLocation(30, LOC_Y - 30);
		imgLabel.setOpaque(true);
		imgLabel.setBackground(Color.BLACK);
		add(imgLabel);
		//

		JLabel userNameLabel = new JLabel("name");

		userNameLabel.setSize(45, 30);
		userNameLabel.setLocation(LOC_X - 50, LOC_Y - 30);
		userNameLabel.setOpaque(true);
		userNameLabel.setBackground(Color.GRAY);
		//userNameLabel.setVerticalAlignment(getX() / 2);
		//userNameLabel.setHorizontalAlignment(getY() / 2);
		add(userNameLabel);

		//
		JTextField userName = new JTextField("");
		userName.setSize(SIZ_X - 1, 30);
		userName.setLocation(LOC_X, LOC_Y - 30);
		// userName.setOpaque(true);
		// userName.setBackground(Color.GRAY);
		// userName.setVerticalAlignment(getX() / 2);
		// userName.setHorizontalAlignment(getY() / 2);
		add(userName);
		//
		JLabel userInfo1Label = new JLabel("O Ta Ruel");
		

		userInfo1Label.setSize(45, 30);
		userInfo1Label.setLocation(LOC_X - 50, LOC_Y + 15);
		userInfo1Label.setOpaque(true);
		userInfo1Label.setBackground(Color.GRAY);
		//userInfo1Label.setVerticalAlignment(getX() / 2);
		//userInfo1Label.setHorizontalAlignment(getY() / 2);
		add(userInfo1Label);
		//
		JTextField userInfo1 = new JTextField("");
		userInfo1.setSize(SIZ_X - 1, 30);
		userInfo1.setLocation(LOC_X, LOC_Y + 15);
		// userInfo1.setOpaque(true);
		// userInfo1.setBackground(Color.GRAY);
		// userInfo1.setVerticalAlignment(getX() / 2);
		// userInfo1.setHorizontalAlignment(getY() / 2);
		add(userInfo1);
		//

		//
		//


		JLabel userInfo2Label = new JLabel("Goal");

		userInfo2Label.setSize(45, 30);
		userInfo2Label.setLocation(LOC_X - 50, LOC_Y + 60);
		userInfo2Label.setOpaque(true);
		userInfo2Label.setBackground(Color.GRAY);
		//userInfo2Label.setVerticalAlignment(getWidth() / 2);
		//userInfo2Label.setHorizontalAlignment(getHeight() / 2);
		add(userInfo2Label);
		//
		JTextField userInfo2 = new JTextField("");
		userInfo2.setSize(SIZ_X - 1, 30);
		userInfo2.setLocation(LOC_X, LOC_Y + 60);
		// userInfo2.setOpaque(true);
		// userInfo2.setBackground(Color.GRAY);
		 //userInfo2.setVerticalAlignment(this.getWidth()/2);
		 //userInfo2.setHorizontalAlignment(this.getHeight() / 2);
		add(userInfo2);

		///
		JButton confirmBttn = new JButton("Confirm");
		confirmBttn.setSize(100, 50);
		confirmBttn.setLocation(80, 280);
		add(confirmBttn);
		confirmBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton cancelBttn = new JButton("Cancel");
		cancelBttn.setSize(100, 50);
		cancelBttn.setLocation(200, 280);

		add(cancelBttn);
		cancelBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
