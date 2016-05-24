package UserManageScene;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

import fileIO.ObjOutput;
import kakaoTyping.User;

public class EnrollUser extends JFrame {
	private int currentId;
	private JLabel imgLabel = new JLabel();
	private JTextField userName;
	private JTextField birth;
	private JTextField goal;
	private JButton confirmBttn;
	// private Vector<User> user = new Vector<User>();

	private void isTextFieldUpdate(JButton btn) {
		if (((userName.getText().isEmpty())) || ((birth.getText().isEmpty())) || ((goal.getText().isEmpty()))) {
			btn.setEnabled(false);
		} else {
			btn.setEnabled(true);
		}
	}

	// ImageIcon icon = new ImageIcon("images/lion.jpg");
	// Image background = icon.getImage();
	//
	// public void paintComponent(Graphics g) {
	// paintComponent(g);
	//
	// g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	// }

	public EnrollUser(Vector<User> user, JPanel p) throws FileNotFoundException {
		super("KaKao Typing");
		setSize(400, 400);
		this.setLayout(null);

		makeEnrollUser(user, p);
		setLocation(super.getWidth() / 2, super.getHeight() / 2);

		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	void makeEnrollUser(Vector<User> user, JPanel p) {
		final int LOC_X = 200;
		final int LOC_Y = 140;
		final int SIZ_X = 150;

		//

		JLabel titleLabel = new JLabel("Enroll User", SwingConstants.CENTER);

		titleLabel.setSize(330, 65);
		titleLabel.setLocation(30, 30);

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
				currentId--;
				currentId += img.length;
				currentId %= img.length;
				imgLabel.setIcon(img[currentId]);
			}
		});

		imgRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentId++;
				currentId %= img.length;
				imgLabel.setIcon(img[currentId]);
			}
		});

		imgLabel.setSize(110, 127);
		imgLabel.setLocation(30, LOC_Y - 30);
		imgLabel.setOpaque(true);
		imgLabel.setBackground(Color.BLACK);
		add(imgLabel);
		//

		JLabel userNameLabel = new JLabel("이름", SwingConstants.CENTER);
		userNameLabel.setFocusable(true);
		userNameLabel.setSize(55, 30);
		userNameLabel.setLocation(LOC_X - 50, LOC_Y - 30);
		userNameLabel.setOpaque(true);
		userNameLabel.setBackground(Color.GRAY);

		add(userNameLabel);

		//
		userName = new JTextField("ex)홍길동");
		userName.setForeground(Color.lightGray); // font color조절
		userName.setSize(SIZ_X - 1, 30);
		userName.setLocation(LOC_X + 10, LOC_Y - 30);
		userName.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (e.getSource() == userName) {
					userName.setText("");
					userName.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (userName.getText().equals("")) {
					userName.setText("ex)홍길동");
					userName.setForeground(Color.LIGHT_GRAY);
				}
			}
		});

		add(userName);
		//
		JLabel birthLabel = new JLabel("생년월일", SwingConstants.CENTER);

		birthLabel.setSize(55, 30);
		birthLabel.setLocation(LOC_X - 50, LOC_Y + 15);
		birthLabel.setOpaque(true);
		birthLabel.setBackground(Color.GRAY);

		add(birthLabel);
		//

		birth = new JTextField("ex)931228");
		birth.setForeground(Color.LIGHT_GRAY);
		birth.setSize(SIZ_X - 1, 30);
		birth.setLocation(LOC_X + 10, LOC_Y + 15);

		birth.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (e.getSource() == birth) {
					birth.setText("");
					birth.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (birth.getText().equals("")) {
					birth.setText("ex)931228");
					birth.setForeground(Color.LIGHT_GRAY);
				}
			}

		});

		add(birth);
		//
		/*SliderChange goalsl = new SliderChange();
	      add(goalsl);*/
		//
		//

		JLabel goalLabel = new JLabel("오타율", SwingConstants.CENTER);

		goalLabel.setSize(55, 30);
		goalLabel.setLocation(LOC_X - 50, LOC_Y + 60);
		goalLabel.setOpaque(true);
		goalLabel.setBackground(Color.GRAY);

		add(goalLabel);
		//
		goal = new JTextField("");
		goal.setSize(SIZ_X - 1, 30);
		goal.setLocation(LOC_X + 10, LOC_Y + 60);

		add(goal);
		userName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(confirmBttn);
			}

			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(confirmBttn);
			}

			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(confirmBttn);
			}
		});

		goal.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(confirmBttn);
			}

			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(confirmBttn);
			}

			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				isTextFieldUpdate(confirmBttn);
			}
		});
		///
		confirmBttn = new JButton("Confirm");
		confirmBttn.setSize(100, 50);
		confirmBttn.setLocation(80, 280);
		confirmBttn.setEnabled(false);
		add(confirmBttn);

		confirmBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.add(new User(currentId, userName.getText(), birth.getText(), goal.getText()));
				new ObjOutput(user);

				p.removeAll();
				p.setVisible(false);
				
				Container c = p.getTopLevelAncestor();
				try {
					c.add(new UserManageScene());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				c.setVisible(true);
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

/*
class SliderChange extends JSlider{
   JSlider sl;
   int slvalue;
   public SliderChange() {
      sl = new JSlider(JSlider.HORIZONTAL, 0, 100, 80);
      sl.setPaintLabels(true);
      sl.setPaintTicks(true);
      sl.setPaintTrack(true);
      sl.setMajorTickSpacing(50);
      sl.setMinorTickSpacing(10);
      
      sl.addChangeListener(new MyChangeListener());
      sl.setForeground(Color.BLUE);
      
      slvalue=sl.getValue();
   }
   class MyChangeListener implements ChangeListener{
      public void stateChanged(ChangeEvent e) {
         slvalue=sl.getValue();
      }
   }
}*/
