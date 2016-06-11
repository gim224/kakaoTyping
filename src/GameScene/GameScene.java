package GameScene;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import fileIO.FileInput;

public class GameScene extends JSplitPane {

	Container s_levelPanel, s_scorePanel, s_lifePanel, s_timePanel, s_imgPanel, s_wordPanel;
	private int s_score = 0; // 처음에 0정부터 시작
	private int s_maxLife = 0; // 최대 목숨 5
	private int curLevel = 1; // 현재 레벨
	private int MAX_LEVEL = 4; // 최대 레벨
	private int interval; //스레드 생성 간격

	private int SCREEN_WIDTH; // 게임패널의 넓이
	private int SCREEN_HEIGHT; // 게입패널의 높이

	private Vector<FallingLabel1> fall = new Vector<FallingLabel1>();
	
	public int getfallnum() {
		return fall.size();
	}
	
	FileInput input = new FileInput("txt/word.txt", "@");

	JTextField textField = new JTextField("", 30);

	public GameScene() {
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
			public WordPanel() {
				setLayout(null);
				s_wordPanel = this;
				fall.addElement(new FallingLabel1());
				add(fall.get(0));
				// 스레드 단어 생성 넣어라! 
			}

			// 배경화면
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon background = new ImageIcon("images/gameback.jpg");
				Image img = background.getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
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

				// 확인버튼
				JButton submit = new JButton("submit");
				add(submit);

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

						for (int i = 0; i < life.length - s_maxLife; i++) {
							life[i] = new JButton(icon[1]);
							add(life[i]);
						}

						for (int i = life.length - s_maxLife; i < life.length; i++) {
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
							for (int i = 0; i < fall.size(); i++) {
								if (textField.getText().equals(fall.get(i).getText())) {
									fall.get(i).finish();
									fall.remove(i);

									s_score += 10;
									s_scorePanel.removeAll();
									s_scorePanel.setVisible(false);
									s_scorePanel.add(new ScorePanel());
									s_scorePanel.setVisible(true);

									fall.add(new FallingLabel1());
									fall.add(new FallingLabel1());
									// int x=1;
									s_wordPanel.add(fall.get(0));
									s_wordPanel.add(fall.get(1));
									
									break;
								}
								
								if (i==fall.size()-1){
									s_maxLife++;
									s_lifePanel.removeAll();
									s_lifePanel.setVisible(false);
									s_lifePanel.add(new LifePanel());
									s_lifePanel.setVisible(true);
									
									fall.add(new FallingLabel1());
									fall.add(new FallingLabel1());
									// int x=1;
									s_wordPanel.add(fall.get(0));
									s_wordPanel.add(fall.get(1));
									
									break;
								}
									
							}
							textField.setText("");
							if(fall.size()<=1) {
								fall.add(new FallingLabel1());
							s_wordPanel.add(fall.get(fall.size()-1));
							}
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
					add(new JLabel(curLevel + "  level", SwingConstants.CENTER)); // replace
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

							for (int i = 0; i < life.length - s_maxLife; i++) {
								life[i] = new JButton(icon[1]);
								add(life[i]);
							}

							for (int i = life.length - s_maxLife; i < life.length; i++) {
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
							int n = 60;
							while (n >= 0) {
								if (n >= 10)
									timerLabel.setText("00:" + n);
								else
									timerLabel.setText("00:0" + n);
								n--;

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									return;
								}
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
				ImageIcon icon = new ImageIcon("images/gg.png");
				Image img = icon.getImage();

				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				}

				public ImagePanel() {
					s_imgPanel = this;
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
	@SuppressWarnings("serial")
	class FallingLabel1 extends JLabel implements Runnable {

		private int random;
		private int speed = 0;
		private int interval;
		private boolean flag = false;
		private ImageIcon icon = new ImageIcon("images/폭죽.gif");
		private Image img = icon.getImage();
		private int lifePoint = 0;

		Level[] LEVEL = { new Level((int) (Math.random() * 3 + 1), (int) ((Math.random() * 10 + 1) * 100), 200),
				new Level((int) (Math.random() * 5 + 1), (int) ((Math.random() * 8 + 1) * 100), 400),
				new Level((int) (Math.random() * 7 + 1), (int) ((Math.random() * 6 + 1) * 100), 600),
				new Level((int) (Math.random() * 9 + 1), (int) ((Math.random() * 4 + 1) * 100), 800) };

		public void finish() {
			flag = true;
		}

		public int getlifePoint() {
			return lifePoint;
		}

		private void setLabelSize() {
			this.setFont(new Font("Jokerman", Font.ITALIC, 20));
			FontMetrics fm = getFontMetrics(this.getFont());
			int w = fm.stringWidth(this.getText());
			this.setSize(w + 10, fm.getHeight());
		}

		public void delay(int millis) {
			try {
				Thread.sleep(millis);
			} catch (Exception e) {
			}
		}
		
		class LifePanel extends JPanel {
			public LifePanel() {
				setLayout(new GridLayout(1, 5));

				JButton[] life = new JButton[5];
				ImageIcon[] icon = new ImageIcon[2];

				icon[0] = new ImageIcon("images/DisableHeart.png");
				icon[1] = new ImageIcon("images/EnableHeart.png");

				for (int i = 0; i < life.length - s_maxLife; i++) {
					life[i] = new JButton(icon[1]);
					add(life[i]);
				}

				for (int i = life.length - s_maxLife; i < life.length; i++) {
					life[i] = new JButton(icon[0]);
					add(life[i]);
				}
			}
		}


		public FallingLabel1() {
			super(input.getOneWordRandom(), SwingConstants.CENTER);

			setLabelSize(); // Label 가로 size 설정

			setOpaque(true);

			random = (int) (Math.random() * (600 - 150) + 1);

			// 스레드 객체 생성
			Thread th = new Thread(this);

			// 스레드 실행
			th.start();

		}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int colorSwitch = 0;
		interval = (int) (Math.random() * 3 + 1) * 100;
		speed = (int) (Math.random() * 5 + 1);
		int i = 0; // 처음 레이블 위치
		while (true) {
			delay(interval);
			setLocation(random, i += speed);
			
			if(getLocation().y>700) {
				Container c= getParent();
				c.remove(this);
				c.repaint();
				
				s_maxLife++;
				s_lifePanel.removeAll();
				s_lifePanel.setVisible(false);
				s_lifePanel.add(new LifePanel());
				s_lifePanel.setVisible(true);
				
				return;
			}
		
			if (s_maxLife==5)
				flag=true;
			if (flag == true) {
				// 스레드 종료시 일어나야 할일 : score 증가?

				this.removeAll();
				this.setVisible(false);
				return;
			}
		}
	}

		// @Override
		// public void paintComponent(Graphics g) {
		// g.drawImage(img, this.getX(), this.getY(), this);
		// }

		class Level {
			int speed;
			int interval;
			int levelUpScore;

			Level(int speed, int interval, int levelUpScore) {
				this.speed = speed;
				this.interval = interval;
				this.levelUpScore = levelUpScore;
			}

			public Level getLevel(int level) {
				if (level > LEVEL.length)
					level = LEVEL.length;
				if (level < 0)
					level = 0;

				return LEVEL[level];
			}
		}

	}

}

/*
 * package GameScene;
 * 
 * import java.awt.Color; import java.awt.Container; import java.awt.Font;
 * import java.awt.Graphics; import java.awt.GridLayout; import java.awt.Image;
 * import java.awt.BorderLayout; import java.awt.event.ActionEvent; import
 * java.awt.event.ActionListener; import java.awt.event.KeyAdapter; import
 * java.awt.event.KeyEvent; import java.util.Vector;
 * 
 * import javax.swing.JSplitPane; import javax.swing.ImageIcon; import
 * javax.swing.JButton; import javax.swing.JLabel; import javax.swing.JPanel;
 * import javax.swing.SwingConstants; import javax.swing.JTextField;
 * 
 * public class GameScene extends JSplitPane {
 * 
 * Container s_levelPanel, s_scorePanel, s_lifePanel, s_timePanel, s_imgPanel,
 * s_wordPanel; // int s_level; int s_score = 0; // 처음에 0정부터 시작 int s_maxLife =
 * 0; // 최대 목숨 5
 * 
 * public GameScene() { setOrientation(JSplitPane.HORIZONTAL_SPLIT);
 * setDividerLocation(600); setEnabled(false); setDividerSize(5);
 * 
 * // this.addKeyListener(new KeyAdapter(){ // public void keyPressed(KeyEvent
 * e){ // textField.requestFocus(); // // } // }); class GamePanel extends
 * JPanel {
 * 
 * private Vector<FallingLabel1> fall = new Vector<FallingLabel1>();
 * 
 * public GamePanel() { setLayout(new BorderLayout());
 * 
 * class WordPanel extends JPanel { public WordPanel() { setLayout(null);
 * s_wordPanel = this; fall.add(new FallingLabel1()); add(fall.get(0)); // 스레드
 * 단어 생성 넣어라! }
 * 
 * // 배경화면 public void paintComponent(Graphics g) { super.paintComponent(g);
 * ImageIcon background = new ImageIcon("images/gameback.jpg"); Image img =
 * background.getImage(); g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
 * } }
 * 
 * class InputPanel extends JPanel { public InputPanel() { // 입력 : add(new
 * JLabel("입력", SwingConstants.CENTER));
 * 
 * // 텍스트 필드 JTextField textField = new JTextField("", 30);
 * textField.setFocusable(true); textField.requestFocus(); add(textField);
 * 
 * // 확인버튼 JButton submit = new JButton("submit"); add(submit); class ScorePanel
 * extends JPanel { public ScorePanel() { setLayout(new BorderLayout()); JLabel
 * scoreLabel = new JLabel(Integer.toString(s_score) + "점",
 * SwingConstants.CENTER); add(scoreLabel, BorderLayout.CENTER); } } class
 * LifePanel extends JPanel { public LifePanel() { setLayout(new GridLayout(1,
 * 5));
 * 
 * JButton[] life = new JButton[5]; ImageIcon[] icon = new ImageIcon[2];
 * 
 * icon[0] = new ImageIcon("images/DisableHeart.png"); icon[1] = new
 * ImageIcon("images/EnableHeart.png");
 * 
 * for (int i = 0; i < life.length - s_maxLife; i++) { life[i] = new
 * JButton(icon[1]); add(life[i]); }
 * 
 * for (int i = life.length - s_maxLife; i < life.length; i++) { life[i] = new
 * JButton(icon[0]); add(life[i]); } } } // 텍스트 필드 리스너
 * textField.addKeyListener(new KeyAdapter() { public void keyPressed(KeyEvent
 * e) { int c = e.getKeyCode();
 * 
 * if (c == KeyEvent.VK_ENTER) { for (int i = 0; i < fall.size(); i++) { if
 * (textField.getText().equals(fall.get(i).getText())) { fall.get(i).finish();
 * fall.remove(i);
 * 
 * s_score += 10; s_scorePanel.removeAll(); s_scorePanel.setVisible(false);
 * s_scorePanel.add(new ScorePanel()); s_scorePanel.setVisible(true);
 * 
 * fall.add(new FallingLabel1()); fall.add(new FallingLabel1()); //int x=1;
 * s_wordPanel.add(fall.get(0)); s_wordPanel.add(fall.get(1)); } } //
 * s_maxLife++; // s_lifePanel.removeAll(); // s_lifePanel.setVisible(false); //
 * s_lifePanel.add(new LifePanel()); // s_lifePanel.setVisible(true);
 * textField.setText(""); }
 * 
 * } });
 * 
 * // // // 서브밋버튼 리스너 // submit.addActionListener(new ActionListener() { //
 * public void actionPerformed(ActionEvent e) { // if
 * (textField.getText().equals(fl.getText())) { // // } //
 * textField.setText(""); // // textField.requestFocus(); // // } // }); }
 * 
 * // score창 관리 // private void scoreManage() { // ScoreLabel.removeAll(); //
 * ScoreLabel.setVisible(false); // ScoreLabel.add(new GradePanel()); //
 * ScoreLabel.setVisible(true); // // } // // // life창 관리 // private void
 * lifeManage() { // JButton[] life = new JButton[5]; // ImageIcon[] icon = new
 * ImageIcon[2]; // // icon[0] = new ImageIcon("images/DisableHeart.png"); //
 * icon[1] = new ImageIcon("images/EnableHeart.png"); // LifePanel.removeAll();
 * // LifePanel.setVisible(false); // for (int i = 0; i < life.length -
 * lifeCount; i++) { // life[i] = new JButton(icon[1]); //
 * LifePanel.add(life[i]); // } // for (int i = life.length - lifeCount; i <
 * life.length; // i++) { // life[i] = new JButton(icon[0]); //
 * LifePanel.add(life[i]); // } // LifePanel.setVisible(true); // } // // //
 * FallingLabel1(fl) 관리 // private void flRestart() { // fl.finish(); // fl =
 * new FallingLabel1(this); // // score += 10; // System.out.println(score); //
 * WordPanel.add(fl); // // } }
 * 
 * WordPanel wordPanel = new WordPanel(); InputPanel inputPanel = new
 * InputPanel();
 * 
 * // 왼쪽 단어떨어지는 창 add(wordPanel, BorderLayout.CENTER); // 왼쪽 단어입력창
 * add(inputPanel, BorderLayout.SOUTH); } }
 * 
 * // 오른쪽 패널 class InfoPanel extends JPanel {
 * 
 * public InfoPanel() { this.setLayout(new GridLayout(3, 1));
 * 
 * // right panel - (0,0) class RightUpPanel extends JPanel {
 * 
 * public RightUpPanel() { setLayout(new GridLayout(4, 1));
 * 
 * JLabel level = new JLabel("LEVEL", SwingConstants.CENTER);
 * level.setBackground(Color.RED); level.setOpaque(true);
 * 
 * add(level); add(new JLabel("1 level", SwingConstants.CENTER)); // replace //
 * add(new // JPanel());
 * 
 * JLabel scoreLabel = new JLabel("SCORE", SwingConstants.CENTER);
 * scoreLabel.setBackground(Color.ORANGE); scoreLabel.setOpaque(true);
 * 
 * class ScorePanel extends JPanel { public ScorePanel() { setLayout(new
 * BorderLayout()); JLabel scoreLabel = new JLabel(Integer.toString(s_score) +
 * "점", SwingConstants.CENTER); add(scoreLabel, BorderLayout.CENTER);
 * 
 * s_scorePanel = this; }
 * 
 * }
 * 
 * add(scoreLabel); ScorePanel scorePanel = new ScorePanel();
 * 
 * add(scorePanel); }
 * 
 * }
 * 
 * // right panel - (0,1) class RightDownPanel extends JPanel {
 * 
 * public RightDownPanel() { setLayout(new GridLayout(4, 1));
 * 
 * JLabel Life = new JLabel("LIFE", SwingConstants.CENTER);
 * Life.setBackground(Color.YELLOW); Life.setOpaque(true);
 * 
 * class LifePanel extends JPanel { public LifePanel() { setLayout(new
 * GridLayout(1, 5));
 * 
 * JButton[] life = new JButton[5]; ImageIcon[] icon = new ImageIcon[2];
 * 
 * icon[0] = new ImageIcon("images/DisableHeart.png"); icon[1] = new
 * ImageIcon("images/EnableHeart.png");
 * 
 * for (int i = 0; i < life.length - s_maxLife; i++) { life[i] = new
 * JButton(icon[1]); add(life[i]); }
 * 
 * for (int i = life.length - s_maxLife; i < life.length; i++) { life[i] = new
 * JButton(icon[0]); add(life[i]); }
 * 
 * s_lifePanel = this; }
 * 
 * }
 * 
 * JLabel Time = new JLabel("TIME", SwingConstants.CENTER);
 * Time.setBackground(Color.GREEN); Time.setOpaque(true);
 * 
 * //// Timer//////// class TimerThread implements Runnable { JLabel timerLabel;
 * 
 * public TimerThread(JLabel timerLabel) { this.timerLabel = timerLabel; }
 * 
 * public void run() { int n = 60; while (n >= 0) { if (n >= 10)
 * timerLabel.setText("00:" + n); else timerLabel.setText("00:0" + n); n--;
 * 
 * try { Thread.sleep(1000); } catch (InterruptedException e) { return; } } } }
 * 
 * class TimePanel extends JPanel { public TimePanel() { JLabel timerLabel = new
 * JLabel(); timerLabel.setFont(new Font("Gothic", Font.BOLD, 40));
 * 
 * TimerThread timer = new TimerThread(timerLabel);
 * 
 * Thread thtime = new Thread(timer);
 * 
 * add(timerLabel);
 * 
 * thtime.start();
 * 
 * s_timePanel = this; } } add(Life); LifePanel lifePanel = new LifePanel();
 * 
 * add(lifePanel); add(Time); TimePanel timePanel = new TimePanel();
 * 
 * add(timePanel); }
 * 
 * } // right panel - (0,2) class ImagePanel extends JPanel { ImageIcon icon =
 * new ImageIcon("images/gg.png"); Image img = icon.getImage();
 * 
 * public void paintComponent(Graphics g) { super.paintComponent(g);
 * g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); }
 * 
 * public ImagePanel() { s_imgPanel = this; } } RightUpPanel rightUpPanel = new
 * RightUpPanel(); RightDownPanel rightDownPanel = new RightDownPanel();
 * ImagePanel imgPanel = new ImagePanel();
 * 
 * add(rightUpPanel); add(rightDownPanel); add(imgPanel); }
 * 
 * }
 * 
 * InfoPanel infoPanel = new InfoPanel(); GamePanel gamePanel = new GamePanel();
 * 
 * setLeftComponent(gamePanel); setRightComponent(infoPanel); } }
 */