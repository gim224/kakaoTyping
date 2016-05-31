package GameScene;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JSplitPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class GameScene extends JSplitPane {

	static Container s_levelPanel, s_scorePanel, s_lifePanel, s_timePanel, s_imgPanel, s_wordPanel;
	// static int s_level;
	static int s_score = 0; // 처음에 0정부터 시작
	static int s_maxLife = 0; // 최대 목숨 5

	public GameScene() {
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setDividerLocation(600);
		setEnabled(false);
		setDividerSize(5);

		// this.addKeyListener(new KeyAdapter(){
		// public void keyPressed(KeyEvent e){
		// textField.requestFocus();
		//
		// }
		// });
		class GamePanel extends JPanel {

			private Vector<FallingLabel> fall = new Vector<FallingLabel>();

			public GamePanel() {
				setLayout(new BorderLayout());

				class WordPanel extends JPanel {
					public WordPanel() {
						setLayout(null);
						s_wordPanel = this;
						fall.add(new FallingLabel());						
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
						JTextField textField = new JTextField("", 30);
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
											
											fall.add(new FallingLabel());
											fall.add(new FallingLabel());
											//int x=1;
											s_wordPanel.add(fall.get(0));
											s_wordPanel.add(fall.get(1));
										}
									}
									// s_maxLife++;
									// s_lifePanel.removeAll();
									// s_lifePanel.setVisible(false);
									// s_lifePanel.add(new LifePanel());
									// s_lifePanel.setVisible(true);
									textField.setText("");
								}

							}
						});

						//
						// // 서브밋버튼 리스너
						// submit.addActionListener(new ActionListener() {
						// public void actionPerformed(ActionEvent e) {
						// if (textField.getText().equals(fl.getText())) {
						//
						// }
						// textField.setText("");
						//
						// textField.requestFocus();
						//
						// }
						// });
					}

					// score창 관리
					// private void scoreManage() {
					// ScoreLabel.removeAll();
					// ScoreLabel.setVisible(false);
					// ScoreLabel.add(new GradePanel());
					// ScoreLabel.setVisible(true);
					//
					// }
					//
					// // life창 관리
					// private void lifeManage() {
					// JButton[] life = new JButton[5];
					// ImageIcon[] icon = new ImageIcon[2];
					//
					// icon[0] = new ImageIcon("images/DisableHeart.png");
					// icon[1] = new ImageIcon("images/EnableHeart.png");
					// LifePanel.removeAll();
					// LifePanel.setVisible(false);
					// for (int i = 0; i < life.length - lifeCount; i++) {
					// life[i] = new JButton(icon[1]);
					// LifePanel.add(life[i]);
					// }
					// for (int i = life.length - lifeCount; i < life.length;
					// i++) {
					// life[i] = new JButton(icon[0]);
					// LifePanel.add(life[i]);
					// }
					// LifePanel.setVisible(true);
					// }
					//
					// // FallingLabel(fl) 관리
					// private void flRestart() {
					// fl.finish();
					// fl = new FallingLabel(this);
					//
					// score += 10;
					// System.out.println(score);
					// WordPanel.add(fl);
					//
					// }
				}

				WordPanel wordPanel = new WordPanel();
				InputPanel inputPanel = new InputPanel();

				// 왼쪽 단어떨어지는 창
				add(wordPanel, BorderLayout.CENTER);
				// 왼쪽 단어입력창
				add(inputPanel, BorderLayout.SOUTH);
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
						add(new JLabel("1 level", SwingConstants.CENTER)); // replace
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

		InfoPanel infoPanel = new InfoPanel();
		GamePanel gamePanel = new GamePanel();

		setLeftComponent(gamePanel);
		setRightComponent(infoPanel);
	}
}
