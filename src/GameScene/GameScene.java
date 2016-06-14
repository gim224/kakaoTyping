package GameScene;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.Vector;
import javax.swing.*;

import org.omg.Messaging.SyncScopeHelper;

import ThirdScene.Ranking;
import UserManageScene.UserManageScene;
import fileIO.FileInput;
import fileIO.ObjInput;
import fileIO.ObjOutput;
import kakaoTyping.User;

public class GameScene extends JSplitPane {

	Container s_levelPanel, s_scorePanel, s_lifePanel, s_timePanel, s_imgPanel, s_wordPanel, s_thisPanel;
	private int s_score = 0; // 처음에 0정부터 시작
	private int s_deadLife = 0; // 최대 목숨 5
	private int curLevel = 1; // 현재 레벨
	private final int MAX_LEVEL = 4; // 최대 레벨
	private int interval = 5000; // 스레드 생성 간격
	private int speed = 100;
	private boolean flag = false;
	private boolean t_flag = false;
	private int userNum = UserManageScene.selectUserNum; // 유저가 누구인지
	private int SCREEN_WIDTH; // 게임패널의 넓이
	private int SCREEN_HEIGHT; // 게입패널의 높이
	
	AudioClip clip_correct = null;
	AudioClip clip_miss = null;
	AudioClip clip_bottom = null;

	private Vector<FallingLabel> fall = new Vector<FallingLabel>(); // 떨어지는 단어들을
	// 저장

	private File file = new File("txt/user.dat");
	private Vector<User> user;
	private ObjInput obip;
	private int countCorrect = 0;
	private int countEnter = 1;

	private JTextField textField = new JTextField("", 30);
	private JLabel curlevelLabel;

	public void setLevel(int curLevel) { // 레벨업을 저장
		this.curLevel = curLevel;
	}

	private FileInput input = new FileInput("txt/word.txt", "@");

	public GameScene() {
		if (file.length() != 0) {
			obip = new ObjInput();
			user = obip.getUserVector();
		}

		URL correctURL = getClass().getResource("correct.WAV");
	    clip_correct = Applet.newAudioClip(correctURL);
		URL missURL = getClass().getResource("miss.WAV");
	    clip_miss = Applet.newAudioClip(missURL);
		URL bottomURL = getClass().getResource("bottom.WAV");
	    clip_bottom = Applet.newAudioClip(bottomURL);
	      
		s_thisPanel = this;
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setDividerLocation(600);
		setEnabled(false);
		setDividerSize(5);

		InfoPanel infoPanel = new InfoPanel();
		GamePanel gamePanel = new GamePanel();

		setLeftComponent(gamePanel);
		setRightComponent(infoPanel);
	}

	public void repaint() {
		super.repaint();
	}

	public void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}

	public void showTitle(String title, int time) {
		Graphics g = this.getGraphics();
		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.setColor(Color.RED);

		FontMetrics fm = getFontMetrics(g.getFont());
		int w = fm.stringWidth(title);

		g.drawString(title, (s_wordPanel.getWidth() - w) / 2, s_wordPanel.getHeight() / 2);
		delay(time);
	}

	public synchronized void levelUp() {
		curLevel++;
		curlevelLabel.setText(curLevel + "  level");
		// s_wordPanel.removeAll();
		showTitle("L E V E L  " + curLevel, 3000);
		s_wordPanel.repaint();
		speed /= 2;
		interval /= 2;
	}

	class GamePanel extends JPanel {

		public GamePanel() {

			setLayout(new BorderLayout());

			WordPanel wordPanel = new WordPanel();
			InputPanel inputPanel = new InputPanel();

			// 왼쪽 단어떨어지는 창
			add(wordPanel, BorderLayout.CENTER);
			// 왼쪽 단어입력창
			add(inputPanel, BorderLayout.SOUTH);
		}

		class WordPanel extends JPanel {
			private boolean isStart = true;

			public WordPanel() {
				setLayout(null);
				s_wordPanel = this;

				GenerateLabel gl = new GenerateLabel();
				gl.start();

			}

			// 배경화면
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon background = new ImageIcon("images/heart.jpg");
				Image img = background.getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
				if (isStart == true) {
					showTitle("ready...", 3000);
					repaint();
					isStart = false;
				}
			}
		}

		class InputPanel extends JPanel {
			public InputPanel() {
				// 입력 :
				add(new JLabel("입력", SwingConstants.CENTER));

				// 텍스트 필드
				textField.setFocusable(true);
				textField.requestFocus();
				add(textField);

				class ScorePanel extends JPanel {
					public ScorePanel() {
						setLayout(new BorderLayout());
						JLabel scoreLabel = new JLabel(Integer.toString(s_score) + "점", SwingConstants.CENTER);
						add(scoreLabel, BorderLayout.CENTER);
					}
				}

				class LifePanel extends JPanel {
					public LifePanel() {
						setLayout(new GridLayout(1, 5));

						JButton[] life = new JButton[5];
						ImageIcon[] icon = new ImageIcon[2];

						icon[0] = new ImageIcon("images/DisableHeart.png");
						icon[1] = new ImageIcon("images/EnableHeart.png");

						for (int i = 0; i < life.length - s_deadLife; i++) {
							life[i] = new JButton(icon[1]);
							add(life[i]);
						}

						for (int i = life.length - s_deadLife; i < life.length; i++) {
							life[i] = new JButton(icon[0]);
							add(life[i]);
						}
					}
				}

				// 텍스트 필드 리스너
				textField.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						int c = e.getKeyCode();

						if (c == KeyEvent.VK_ENTER) {
							countEnter++;
							// System.out.println("엔터" + countEnter);
							for (int i = 0; i < fall.size(); i++) {
								if (textField.getText().equals(fall.get(i).getText())) {
									clip_correct.play();
									fall.get(i).finish2();
									countCorrect++;
									// System.out.println("맞춤" + countCorrect);
									s_score += 20;
									s_scorePanel.removeAll();
									s_scorePanel.setVisible(false);
									s_scorePanel.add(new ScorePanel());
									s_scorePanel.setVisible(true);

									if ((s_score >= curLevel * 200) && curLevel != MAX_LEVEL)
										levelUp();

									break;
								}

								if (i == fall.size() - 1) {
									if (s_deadLife < 5) {
										clip_miss.play();
										s_deadLife++;
										s_lifePanel.removeAll();
										s_lifePanel.setVisible(false);
										s_lifePanel.add(new LifePanel());
										s_lifePanel.setVisible(true);
									}

									break;
								}

							}
							textField.setText("");
						}

					}
				});

			}

		}
	}

	// 오른쪽 패널
	class InfoPanel extends JPanel {
		public InfoPanel() {
			this.setLayout(new GridLayout(3, 1));

			// right panel - (0,0)
			class RightUpPanel extends JPanel {

				public RightUpPanel() {
					setLayout(new GridLayout(4, 1));

					JLabel level = new JLabel("LEVEL", SwingConstants.CENTER);
					level.setBackground(Color.RED);
					level.setOpaque(true);

					add(level);

					curlevelLabel = new JLabel(curLevel + "  level", SwingConstants.CENTER);
					add(curlevelLabel); // replace
					// add(new
					// JPanel());

					JLabel scoreLabel = new JLabel("SCORE", SwingConstants.CENTER);
					scoreLabel.setBackground(Color.ORANGE);
					scoreLabel.setOpaque(true);

					class ScorePanel extends JPanel {
						public ScorePanel() {
							setLayout(new BorderLayout());
							JLabel scoreLabel = new JLabel(Integer.toString(s_score) + "점", SwingConstants.CENTER);
							add(scoreLabel, BorderLayout.CENTER);

							s_scorePanel = this;
						}

					}

					add(scoreLabel);
					ScorePanel scorePanel = new ScorePanel();

					add(scorePanel);
				}

			}

			// right panel - (0,1)
			class RightDownPanel extends JPanel {

				public RightDownPanel() {
					setLayout(new GridLayout(4, 1));

					JLabel Life = new JLabel("LIFE", SwingConstants.CENTER);
					Life.setBackground(Color.YELLOW);
					Life.setOpaque(true);

					class LifePanel extends JPanel {

						public LifePanel() {
							setLayout(new GridLayout(1, 5));

							JButton[] life = new JButton[5];
							ImageIcon[] icon = new ImageIcon[2];

							icon[0] = new ImageIcon("images/DisableHeart.png");
							icon[1] = new ImageIcon("images/EnableHeart.png");

							for (int i = 0; i < life.length - s_deadLife; i++) {
								life[i] = new JButton(icon[1]);
								add(life[i]);
							}

							for (int i = life.length - s_deadLife; i < life.length; i++) {
								life[i] = new JButton(icon[0]);
								add(life[i]);
							}

							s_lifePanel = this;
						}

					}

					JLabel Time = new JLabel("TIME", SwingConstants.CENTER);
					Time.setBackground(Color.GREEN);
					Time.setOpaque(true);

					//// Timer////////
					class TimerThread implements Runnable {
						JLabel timerLabel;

						public TimerThread(JLabel timerLabel) {
							this.timerLabel = timerLabel;
						}

						public void run() {
							int n = -3;
							while (true) {
								if (n % 60 < 10 && n / 60 < 10)
									timerLabel.setText("0" + Integer.toString(n / 60) + ":0" + n % 60);
								else if (n % 60 >= 10 && n / 60 < 10)
									timerLabel.setText("0" + Integer.toString(n / 60) + ":" + n % 60);
								else if (n % 60 < 10 && n / 60 >= 10)
									timerLabel.setText("0" + Integer.toString(n / 60) + ":" + n % 60);
								else
									timerLabel.setText(Integer.toString(n / 60) + ":" + n % 60);

								n++;

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									return;
								}

								if (t_flag == true)
									return;
							}
						}
					}

					class TimePanel extends JPanel {
						public TimePanel() {
							JLabel timerLabel = new JLabel();
							timerLabel.setFont(new Font("Gothic", Font.BOLD, 40));

							TimerThread timer = new TimerThread(timerLabel);
							Thread thtime = new Thread(timer);

							add(timerLabel);

							thtime.start();

							s_timePanel = this;
						}

					}
					add(Life);
					LifePanel lifePanel = new LifePanel();

					add(lifePanel);
					add(Time);
					TimePanel timePanel = new TimePanel();

					add(timePanel);
				}

			}
			// right panel - (0,2)
			class ImagePanel extends JPanel {
				ImageIcon[] icon;
				Image img;
				String name;

				public ImagePanel() {
					s_imgPanel = this;
					int imgNum = user.get(userNum).getImgNum();
					icon = new ImageIcon[2];
					icon[0] = new ImageIcon("images/user-male-icon.png");
					icon[1] = new ImageIcon("images/user-female-icon.png");
					img = icon[imgNum].getImage();
					name = "NAME : " + user.get(userNum).getName();
					JLabel id = new JLabel(name);
					id.setBounds(200, 100, 0, 0);
					id.setForeground(Color.RED);
					// add(id);
				}

				public void paintComponent(Graphics g) {

					super.paintComponent(g);
					g.setColor(Color.cyan);
					g.fillRect(0, 0, this.getWidth(), this.getHeight());
					g.drawImage(img, 0, 40, this.getWidth(), this.getHeight(), this);

					g.setColor(Color.red);
					this.setFont(new Font("굴림", 0, 20));
					FontMetrics fm = getFontMetrics(this.getFont());
					int w = fm.stringWidth(name);
					g.drawString(name, (this.getWidth() - w) / 2, 30);

				}

			}
			RightUpPanel rightUpPanel = new RightUpPanel();
			RightDownPanel rightDownPanel = new RightDownPanel();
			ImagePanel imgPanel = new ImagePanel();

			add(rightUpPanel);
			add(rightDownPanel);
			add(imgPanel);
		}
	}

	class GenerateLabel extends Thread {
		private boolean isStart = true;
		ImageIcon image = new ImageIcon("images/label/heavy-red-heart.png");

		public void run() {
			while (flag != true) {
				fall.addElement(new FallingLabel(image));
				s_wordPanel.add(fall.lastElement());

				delay(interval);

				int i = 0;
				if (flag == true) {
					while (fall.isEmpty()) {
						fall.get(i).finish();
					}
					return;
				}

			}
		}
	}

	class FallingLabel extends JLabel implements Runnable {
		private boolean F_flag = false;
		private boolean F_flag2 = false;
		private int random = (int) (Math.random() * (600 - 150) + 1);
		// private ImageIcon icon = new ImageIcon("images/폭죽.gif");
		// private Image img = icon.getImage();

		public void finish() {
			F_flag = true;
		}

		public void finish2() {
			F_flag2 = true;
		}

		private void setLabelSize() {
			this.setFont(new Font("굴림", Font.ITALIC, 20));
			FontMetrics fm = getFontMetrics(this.getFont());
			int w = fm.stringWidth(this.getText());
			this.setSize(w + 50, fm.getHeight() + 20);
		}

		public FallingLabel(ImageIcon image2) {

			super(input.getOneWordRandom(), image2, SwingConstants.CENTER);

			setLabelSize(); // Label 가로 size 설정
			setOpaque(true);
			this.setBackground(Color.white);

			// 스레드 객체 생성
			Thread th = new Thread(this);
			// 스레드 실행
			th.start();
		}

		@Override
		public void run() {

			int i = 0; // 처음 레이블 위치
			while (true) {
				//delay(speed);
				delay(50);
				setLocation(random, i++);

				if (getLocation().y > s_wordPanel.getSize().getWidth()+40) {
					clip_bottom.play();
					finish();
					if (s_deadLife < 5) {
						s_deadLife++;
						s_lifePanel.removeAll();
						s_lifePanel.setVisible(false);
						s_lifePanel.add(new LifePanel());
						s_lifePanel.setVisible(true);
					}

					// return;
				}

				if (s_deadLife == 5) {
					flag = true;
					finish();
					t_flag = true;
					showTitle("Game Over", 5000);
					User curUser = user.get(userNum);
					
					curUser.setScore(s_score);
					// System.out.println(s_score);

					curUser.setMiss((double) (100 * (countEnter - countCorrect) / countEnter));

					user.add(userNum, curUser);
					user.remove(userNum+1);
					new ObjOutput(user);
					Container c = getParent();
					// c.removeAll();
					c.setVisible(false);
					JButton rank = new JButton("점수판 가기");
					JButton regame = new JButton("게임 더하기");
					rank.setBounds(50, 100, 200, 100);
					rank.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Container c = s_thisPanel.getParent();
							s_thisPanel.removeAll();
							c.remove(s_thisPanel);
							c.setVisible(false);
							c.add(new Ranking());
							c.setVisible(true);

						}
					});

					regame.setBounds(350, 100, 200, 100);
					regame.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JButton bttn = (JButton) e.getSource();
							Container c = bttn.getParent().getParent().getParent();
							c.removeAll();
							c.setVisible(false);
							c.add(new GameScene());
							c.setVisible(true);
							c.repaint();
						}
					});
					c.add(rank);
					c.add(regame);
					c.setVisible(true);

				}
				if (F_flag2 == true) {
					Container c = getParent();
					ImageIcon goodImg = new ImageIcon("images/label/revolving-hearts.png");
					JLabel good = new JLabel(goodImg);
					good.setLocation(this.getLocation());
					good.setSize(this.getSize());
					c.add(good);

					// c.remove(good);
					this.setVisible(false);
					c.remove(this);
					c.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					c.remove(good);
					c.repaint();
					fall.remove(this);

					this.removeAll();

					return;
				}

				if (F_flag == true) {
					Container c = getParent();
					ImageIcon goodImg = new ImageIcon("images/label/broken-heart.png");
					JLabel good = new JLabel(goodImg);
					good.setLocation(this.getLocation());
					good.setSize(this.getSize());
					c.add(good);

					// c.remove(good);
					this.setVisible(false);
					c.remove(this);
					c.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					c.remove(good);
					c.repaint();
					fall.remove(this);

					this.removeAll();

					return;
				}
			}
		}

		class LifePanel extends JPanel {
			public LifePanel() {
				setLayout(new GridLayout(1, 5));

				JButton[] life = new JButton[5];
				ImageIcon[] icon = new ImageIcon[2];

				icon[0] = new ImageIcon("images/DisableHeart.png");
				icon[1] = new ImageIcon("images/EnableHeart.png");

				for (int i = 0; i < life.length - s_deadLife; i++) {
					life[i] = new JButton(icon[1]);
					add(life[i]);
				}

				for (int i = life.length - s_deadLife; i < life.length; i++) {
					life[i] = new JButton(icon[0]);
					add(life[i]);
				}
			}
		}
	}
}