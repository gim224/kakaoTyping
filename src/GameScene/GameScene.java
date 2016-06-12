package GameScene;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import fileIO.FileInput;

public class GameScene extends JSplitPane {

	Container s_levelPanel, s_scorePanel, s_lifePanel, s_timePanel, s_imgPanel, s_wordPanel;
	private int s_score = 0; // 처음에 0정부터 시작
	private int s_deadLife = 0; // 최대 목숨 5
	private int curLevel = 1; // 현재 레벨
	private int MAX_LEVEL = 4; // 최대 레벨
	private int interval = 5000; // 스레드 생성 간격
	private int speed = 100;
	private boolean flag = false;

	private int SCREEN_WIDTH; // 게임패널의 넓이
	private int SCREEN_HEIGHT; // 게입패널의 높이

	private Vector<FallingLabel> fall = new Vector<FallingLabel>(); // 떨어지는 단어들을
																	// 저장

	public int setLevel(int curLevel) { // 레벨업을 저장
		return this.curLevel = curLevel;
	}

	FileInput input = new FileInput("txt/word.txt", "@");

	JTextField textField = new JTextField("", 30);
	JLabel curlevelLabel;

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
		s_wordPanel.removeAll();
		showTitle("L E V E L  " + curLevel, 1000);
		
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

			public WordPanel() {
				setLayout(null);
				s_wordPanel = this;
				GenerateLabel gl = new GenerateLabel();
				gl.start();
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
							for (int i = 0; i < fall.size(); i++) {
								if (textField.getText().equals(fall.get(i).getText())) {

									s_wordPanel.remove(fall.get(i));
									fall.remove(i);
									s_wordPanel.repaint();

									s_score += 20;
									s_scorePanel.removeAll();
									s_scorePanel.setVisible(false);
									s_scorePanel.add(new ScorePanel());
									s_scorePanel.setVisible(true);
									
									if((s_score >= curLevel*200) && curLevel != MAX_LEVEL) 
										levelUp();

									break;
								}

								if (i == fall.size() - 1) {
									s_deadLife++;
									s_lifePanel.removeAll();
									s_lifePanel.setVisible(false);
									s_lifePanel.add(new LifePanel());
									s_lifePanel.setVisible(true);

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

	class GenerateLabel extends Thread {
		public void run() {
			while (flag != true) {
				fall.addElement(new FallingLabel());
				s_wordPanel.add(fall.lastElement());
				delay(interval);
			}
		}
	}

	@SuppressWarnings("serial")
	class FallingLabel extends JLabel implements Runnable {

		private int random = (int) (Math.random() * (600 - 150) + 1);
		private ImageIcon icon = new ImageIcon("images/폭죽.gif");
		private Image img = icon.getImage();

		public void finish() {
			flag = true;
		}

		private void setLabelSize() {
			this.setFont(new Font("굴림", Font.ITALIC, 20));
			FontMetrics fm = getFontMetrics(this.getFont());
			int w = fm.stringWidth(this.getText());
			this.setSize(w + 10, fm.getHeight());
		}

		public FallingLabel() {
			super(input.getOneWordRandom(), SwingConstants.CENTER);

			setLabelSize(); // Label 가로 size 설정
			setOpaque(true);

			// 스레드 객체 생성
			Thread th = new Thread(this);
			// 스레드 실행
			th.start();
		}

		@Override
		public void run() {
			// int colorSwitch = 0;
			int i = 0; // 처음 레이블 위치
			while (true) {
				delay(speed);
				setLocation(random, i++);

				if (getLocation().y > 750) {
					Container c = getParent();
					c.remove(this);
					c.repaint();
					fall.remove(this);
					
					s_deadLife++;
					s_lifePanel.removeAll();
					s_lifePanel.setVisible(false);
					s_lifePanel.add(new LifePanel());
					s_lifePanel.setVisible(true);

					return;
				}

				if (s_deadLife == 5) {
					flag = true;
					showTitle("Game Over", 5000);
				}

				if (flag == true) {
					this.removeAll();
					this.setVisible(false);
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