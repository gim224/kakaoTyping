package kakaoTyping;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.*;

import FirstScene.FirstScene;
import FourthScene.FourthScene;
import GameScene.GamePanel;
import GameScene.GameScene;
import ThirdScene.ThirdScene;
import ThirdScene.WordInput;
import UserManageScene.UserManageScene;


public class MyFrame extends JFrame {
	public MyFrame() throws FileNotFoundException {
		super("KaKao Typing");
		this.setSize(800, 800);
		this.setLocation(10,10);
		this.setLayout(new BorderLayout());		  
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/EnableHeart.png"));
		
		// JMenu
		createMenu();
		// JTooltoolBar
		createTooltoolBar();
		/**FirstScene*/		
		add(new FirstScene());
		
		/**SecondScene(User Enroll Scene)*/
		//add(new UserManageScene());
		
		/**ThirdScene(MainMenu)*/
		add(new ThirdScene());
		
		/**4ndScene(SelectLevel)*/

		//add(new FourthScene());

		/**GameScreen*/
		//add(new GameScene());
		
		

		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	void createMenu() {
		JMenuBar menuToolBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu setting = new JMenu("Setting");
		JMenu help = new JMenu("Help");
		JMenu screenShot = new JMenu("ScreenShot");

		// file Menu
		file.add(new JMenuItem("New Game"));
		file.addSeparator();
		file.add(new JMenuItem("Score"));
		file.add(new JMenuItem("Pause"));
		file.add(new JMenuItem("Re-Start"));
		file.addSeparator();
		file.add(new JMenuItem("EXIT"));
		// screenShot Menu
		screenShot.add(new JMenuItem("ScreenShot"));
		// setting Menu
		setting.add(new JMenuItem("Frame-Size"));
		setting.add(new JMenuItem("Background"));
		// help Menu
		help.add(new JMenuItem("help"));
		help.addSeparator();
		help.add(new JMenuItem("Information"));

		menuToolBar.add(file);
		menuToolBar.add(screenShot);
		menuToolBar.add(setting);
		menuToolBar.add(help);

		setJMenuBar(menuToolBar); // �޴��ٸ� �����ӿ� ����
	}

	void createTooltoolBar() {
		JToolBar toolBar = new JToolBar("");
		toolBar.setBackground(Color.LIGHT_GRAY);


		


		JButton playBtn = new JButton(new ImageIcon("images/play.png"));
		playBtn.setToolTipText("Play");

		toolBar.add(playBtn);


		JButton pauBtn = new JButton(new ImageIcon("images/pause.png"));
		pauBtn.setToolTipText("Pause");

		toolBar.add(pauBtn);


	

		JButton scBtn = new JButton(new ImageIcon("images/camera.png"));
		scBtn.setToolTipText("Screen Shot");

		toolBar.add(scBtn);


		JButton musicBtn = new JButton(new ImageIcon("images/music.png"));
		scBtn.setToolTipText("Music");

		toolBar.add(musicBtn);

		JButton back = new JButton(new ImageIcon("images/home.png"));
		scBtn.setToolTipText("home");
		toolBar.add(back);
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JButton bttn = (JButton)e.getSource();
				JToolBar bar = (JToolBar)bttn.getParent();
				Container c = bar.getParent();
				c.removeAll();
				c.setVisible(false);
				c.add(bar,BorderLayout.NORTH);
				c.add(new FirstScene());
				c.setVisible(true);
			}
		});

		this.add(toolBar, BorderLayout.NORTH);
		toolBar.setFloatable(false); // ���� �̵� �Ұ�
	}
}
