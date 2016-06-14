package kakaoTyping;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import FirstScene.FirstScene;
import FourthScene.FourthScene;
import GameScene.GameScene;
import ThirdScene.ThirdScene;
import UserManageScene.UserManageScene;

public class MyFrame extends JFrame {

	private int screenCount = 0;
	AudioClip clip = null;
	private boolean play = true;
	private boolean stop = false;

	public MyFrame() throws FileNotFoundException {
		super("NOPE Typing");
		this.setSize(800, 800);
		this.setLocation(10, 10);
		this.setLayout(new BorderLayout());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/EnableHeart.png"));

		URL audioURL = getClass().getResource("LoveLane.WAV");
		clip = Applet.newAudioClip(audioURL);
		clip.loop();
		// JMenu
		createMenu();
		// JTooltoolBar
		createTooltoolBar();
		/** FirstScene */
		add(new FirstScene());

		/** SecondScene(User Enroll Scene) */
		// add(new UserManageScene());

		/** ThirdScene(MainMenu) */
		// add(new ThirdScene());

		/** 4ndScene(SelectLevel) */

		// add(new FourthScene());

		/** GameScreen */
		// add(new GameScene());

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

		setJMenuBar(menuToolBar);
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

		scBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();
				Container f = bttn.getTopLevelAncestor();
				Robot robot = null;

				try {
					robot = new Robot();
				} catch (Exception e1) {
				}
				Rectangle area = new Rectangle(f.getLocation().x, f.getLocation().y, f.getSize().width,
						f.getSize().height);
				BufferedImage image = robot.createScreenCapture(area);
				image.setRGB(0, 0, 100);

				try {
					File file = new File("capture/screen" + screenCount++ + ".jpeg");
					ImageIO.write(image, "jpeg", file);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		JButton musicBtn = new JButton(new ImageIcon("images/music.png"));
		musicBtn.setRolloverIcon(new ImageIcon("images/mute.png"));
		musicBtn.setToolTipText("Music");

		toolBar.add(musicBtn);

		musicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (play == true) {
					clip.stop();
					musicBtn.setIcon(new ImageIcon("images/mute.png"));
					musicBtn.setRolloverIcon(new ImageIcon("images/music.png"));
					play=false;
					return;
				}
				else {
					clip.loop();
					musicBtn.setIcon(new ImageIcon("images/music.png"));
					musicBtn.setRolloverIcon(new ImageIcon("images/mute.png"));
					play=true;
					return;
				}
			}
		});

		JButton back = new JButton(new ImageIcon("images/home.png"));
		back.setToolTipText("home");
		toolBar.add(back);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bttn = (JButton) e.getSource();
				JToolBar bar = (JToolBar) bttn.getParent();
				Container c = (Container) bar.getParent();
				c.removeAll();
				c.setVisible(false);

				c.add(bar, BorderLayout.NORTH);
				c.add(new FirstScene());
				c.setVisible(true);
			}
		});

		this.add(toolBar, BorderLayout.NORTH);
		toolBar.setFloatable(false); // 툴바 못옮김
	}
}