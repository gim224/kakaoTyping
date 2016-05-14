package kakaoTyping;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {
	public MyFrame() {
		super("KaKao Typing");
		this.setSize(800, 800);		
		this.setLayout(new BorderLayout());
		// JMenu
		createMenu();
		// JTooltoolBar
		createTooltoolBar();
		add(new GameScreen());
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

		JButton playBtn = new JButton("Play");
		playBtn.setToolTipText("����");
		toolBar.add(playBtn);

		JButton pauBtn = new JButton("Pause");
		pauBtn.setToolTipText("�Ͻ�����");
		toolBar.add(pauBtn);		
		
		JButton scBtn = new JButton(new ImageIcon("images/camera.png"));
		scBtn.setToolTipText("��ũ����");
		toolBar.add(scBtn);
		
		JButton musicBtn = new JButton(new ImageIcon("images/music.png"));
		scBtn.setToolTipText("����");
		toolBar.add(musicBtn);
		
		this.add(toolBar, BorderLayout.NORTH);
		toolBar.setFloatable(false);	//���� �̵� �Ұ�
	}	
}

