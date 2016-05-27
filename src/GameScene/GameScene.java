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
import java.awt.event.KeyListener;

import javax.swing.JSplitPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import fileIO.FileInput;

public class GameScene extends JSplitPane {
	private FileInput input = new FileInput("txt/word.txt", "@");
	private FallingLabel fl = new FallingLabel(input.getOneWordRandom(), (int) (Math.random() * 5 + 1));
	private int score = 0;
	private int lifeCount = 2;	

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

			public GamePanel(Container ScoreLabel, Container LifePanel) {
				setLayout(new BorderLayout());

				class CreateWordPanel extends JPanel {
					public CreateWordPanel() {
						setLayout(null);
						add(fl);
						// Exception in thread "main"
						// java.lang.NullPointerException
						// Font currentFont = fl.getGraphics().getFont();//
						// FontMetrics fm = fl.getFontMetrics(currentFont);
						// int w = fm.stringWidth(fl.getText());
						// fl.setSize(w, fm.getHeight());
						// add(fl);

					}

					public Container getCreateWordPanel() {
						return this;
					}

					public void paintComponent(Graphics g) {
						super.paintComponent(g);
						ImageIcon background = new ImageIcon("images/gameback.jpg");
						Image img = background.getImage();
						g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
					}
				}

				class InputPanel extends JPanel {
					Container createWordPanel;

					public InputPanel(Container cT) {
						createWordPanel = cT;
						add(new JLabel("입력", SwingConstants.CENTER));

						JTextField textField = new JTextField("", 30);
						//
						textField.getText();
						textField.setFocusable(true);
						textField.requestFocus();
						add(textField);

						textField.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent e) {
								int c = e.getKeyCode();

								if (c == KeyEvent.VK_ENTER) {
									if (textField.getText().equals(fl.getText()))
										flRestart();
									textField.setText("");
									lifeManage();
									scoreManage();
								}
							}
						});
						JButton submit = new JButton("submit");
						add(submit);

						submit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals(fl.getText())) {
									flRestart();
								}
								textField.setText("");
								lifeManage();
								scoreManage();
								
								textField.requestFocus();

							}
						});
					}

					class GradePanel extends JPanel {
						public GradePanel() {
							setLayout(new BorderLayout());
							JLabel l = new JLabel(Integer.toString(score) + "점", SwingConstants.CENTER);
							add(l, BorderLayout.CENTER);

						}
					}
					
					//score창 관리
					private void scoreManage() {
						ScoreLabel.removeAll();
						ScoreLabel.setVisible(false);
						ScoreLabel.add(new GradePanel());
						ScoreLabel.setVisible(true);

					}

					
					//life창 관리
					private void lifeManage() {
						JButton[] life = new JButton[5];
						ImageIcon[] icon = new ImageIcon[2];

						icon[0] = new ImageIcon("images/DisableHeart.png");
						icon[1] = new ImageIcon("images/EnableHeart.png");
						LifePanel.removeAll();
						LifePanel.setVisible(false);
						for (int i = 0; i < life.length - lifeCount; i++) {
							life[i] = new JButton(icon[1]);
							LifePanel.add(life[i]);
						}
						for (int i = life.length - lifeCount; i < life.length; i++) {
							life[i] = new JButton(icon[0]);
							LifePanel.add(life[i]);
						}
						LifePanel.setVisible(true);
					}
					
					//FallingLabel(fl) 관리
					private void flRestart() {
						fl.finish();
						fl = new FallingLabel(input.getOneWordRandom(), (int) (Math.random() * 5 + 1));
						score += 10;
						System.out.println(score);
						createWordPanel.add(fl);

					}
				}
				
				CreateWordPanel createWordPanel = new CreateWordPanel();
				
				add(new InputPanel(createWordPanel.getCreateWordPanel()), BorderLayout.SOUTH);
				add(createWordPanel, BorderLayout.CENTER);

			}
		}

		class ScorePanel extends JPanel {
			Container cRightDownPanel_LifePanel;
			Container cRightUpPanel_GradePanel;

			public Container getLifePanel() {
				return cRightDownPanel_LifePanel;
			}

			public ScorePanel() {

				this.setLayout(new GridLayout(3, 1));
				// right panel - (0,0)
				class RightUpPanel extends JPanel {
					Container cGradePanel;

					public RightUpPanel() {
						setLayout(new GridLayout(4, 1));

						JLabel Level = new JLabel("LEVEL", SwingConstants.CENTER);
						Level.setBackground(Color.RED);
						Level.setOpaque(true);

						JLabel Score = new JLabel("SCORE", SwingConstants.CENTER);
						Score.setBackground(Color.ORANGE);
						Score.setOpaque(true);

						add(Level);
						add(new JLabel("1 level", SwingConstants.CENTER)); // replace
																			// add(new
																			// JPanel());
						class GradePanel extends JPanel {
							public GradePanel() {
								setLayout(new BorderLayout());
								JLabel l = new JLabel(Integer.toString(score) + "점", SwingConstants.CENTER);
								add(l, BorderLayout.CENTER);
							}

							public Container getGradePanel() {
								return this;
							}
						}
						add(Score);
						GradePanel scorePanel = new GradePanel();
						cGradePanel = scorePanel.getGradePanel();

						add(scorePanel); // replace
											// add(new
											// JPanel());
					}

					public Container getScoreLabel() {
						return cGradePanel;
					}
				}
				// right panel - (0,1)
				class RightDownPanel extends JPanel {
					Container cLifePanel;

					public RightDownPanel() {
						setLayout(new GridLayout(4, 1));

						JLabel Life = new JLabel("LIFE", SwingConstants.CENTER);
						Life.setBackground(Color.YELLOW);
						Life.setOpaque(true);

						JLabel Time = new JLabel("TIME", SwingConstants.CENTER);
						Time.setBackground(Color.GREEN);
						Time.setOpaque(true);

						class LifePanel extends JPanel {
							public LifePanel() {
								setLayout(new GridLayout(1, 5));

								JButton[] life = new JButton[5];
								ImageIcon[] icon = new ImageIcon[2];

								icon[0] = new ImageIcon("images/DisableHeart.png");
								icon[1] = new ImageIcon("images/EnableHeart.png");

								for (int i = 0; i < life.length; i++) {
									life[i] = new JButton(icon[1]);
									add(life[i]);
								}
							}

							public Container getLifePanel() {
								return this;
							}
						}

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

						class Timer extends JPanel {
							public Timer() {
								JLabel timerLabel = new JLabel();
								timerLabel.setFont(new Font("Gothic", Font.BOLD, 40));

								TimerThread timer = new TimerThread(timerLabel);

								Thread thtime = new Thread(timer);

								add(timerLabel);

								thtime.start();
							}
						}
						add(Life);
						LifePanel lifePanel = new LifePanel();
						cLifePanel = lifePanel.getLifePanel();

						add(lifePanel);
						add(Time);
						add(new Timer());
					}

					public Container getLifePanel() {
						return cLifePanel;
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
				}

				RightDownPanel rightDownPanel = new RightDownPanel();
				cRightDownPanel_LifePanel = rightDownPanel.getLifePanel();
				RightUpPanel rightUpPanel = new RightUpPanel();
				cRightUpPanel_GradePanel = rightUpPanel.getScoreLabel();
				add(rightUpPanel);
				add(rightDownPanel);
				add(new ImagePanel());
			}

			public Container getGradePanel() {
				return cRightUpPanel_GradePanel;
			}
		}
		ScorePanel scorePanel = new ScorePanel();
		GamePanel gamePanel = new GamePanel(scorePanel.getGradePanel(), scorePanel.getLifePanel());
		
		setLeftComponent(gamePanel);
		setRightComponent(scorePanel);
	}
}
