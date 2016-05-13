package kakaoTyping;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {
	public MyFrame() {
		this.setSize(800, 800);
		this.setLayout(new BorderLayout());

		// JMenu
		createMenu();
		// JTooltoolBar
		createTooltoolBar();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	void createMenu() {
		JMenuBar menutoolBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu setting = new JMenu("Setting");
		JMenu help = new JMenu("Help");
		JMenu screenShot = new JMenu("ScreenShot");

		// fileMenu
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

		menutoolBar.add(file);
		menutoolBar.add(screenShot);
		menutoolBar.add(setting);
		menutoolBar.add(help);

		setJMenuBar(menutoolBar); // 메뉴바를 프레임에 붙임
	}

	void createTooltoolBar() {
		JToolBar toolBar = new JToolBar("");
		toolBar.setBackground(Color.LIGHT_GRAY);

		JButton playBtn = new JButton("Play");
		playBtn.setToolTipText("시작");
		toolBar.add(playBtn);

		JButton pauBtn = new JButton("Pause");
		pauBtn.setToolTipText("일시정지");
		toolBar.add(pauBtn);

		JButton scBtn = new JButton("PrtSc");
		//JButton scBtn = new JButton(new ImageIcon("images/save.jpg"));
		scBtn.setToolTipText("스크린샷");
		toolBar.add(scBtn);
		this.add(toolBar, BorderLayout.NORTH);
		//toolBar.setFloatable(false);	//툴바 이동 불가
	}
}
