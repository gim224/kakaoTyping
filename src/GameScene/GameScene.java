package GameScene;

import java.awt.Color;
import java.awt.Container;
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
import thread.FallingLabel;

public class GameScene extends JSplitPane {
	FileInput input = new FileInput("txt/word.txt", "@");
	FallingLabel fl = new FallingLabel(input.getOneWordRandom(), (int) (Math.random() * 5 + 1));
	int lifePoint = 0;

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

			public GamePanel() {
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

					public void paintComponent(Graphics g) {
						super.paintComponent(g);
						ImageIcon background = new ImageIcon("images/gameback.jpg");
						Image img = background.getImage();
						g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
					}
				}

				class InputPanel extends JPanel {
					public InputPanel() {
						add(new JLabel("입력", SwingConstants.CENTER));

						JTextField textField = new JTextField("", 30);
						//
						textField.getText();
						textField.setFocusable(true);
						textField.requestFocus();
						add(textField);

						// textField.addActionListener(new ActionListener(){
						// public void actionPerformed(ActionEvent e){
						// e.getSource();
						// }
						// });
						textField.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent e) {
								int c = e.getKeyCode();

								if (c == KeyEvent.VK_ENTER && textField.getText().equals(fl.getText())) {
									fl.finish();
									lifePoint--;									
									textField.setText("");
								} else if (c == KeyEvent.VK_ENTER && !textField.getText().equals(fl.getText()))
									textField.setText("");
							}
						});
						JButton submit = new JButton("submit");
						add(submit);
						// System.out.println(fl.getText());
						submit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (textField.getText().equals(fl.getText())) {
									fl.finish();
								}
								textField.setText("");

							}
						});
					}
				}

				add(new InputPanel(), BorderLayout.SOUTH);
				add(new CreateWordPanel(), BorderLayout.CENTER);

			}
		}

		class ScorePanel extends JPanel {
			public ScorePanel() {
				this.setLayout(new GridLayout(3, 1));
				// right panel - (0,0)
				class RightUpPanel extends JPanel {
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

						add(Score);
						add(new JLabel("300", SwingConstants.CENTER)); // replace
																		// add(new
																		// JPanel());
					}
				}
				// right panel - (0,1)
				class RightDownPanel extends JPanel {
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

								for (int i = 0; i < life.length - lifePoint; i++) {
									life[i] = new JButton(icon[1]);
									add(life[i]);
								}
								for (int i = life.length - lifePoint; i < life.length; i++) {
									life[i] = new JButton(icon[0]);
									add(life[i]);
								}
							}
						}
						add(Life);
						add(new LifePanel());

						add(Time);
						add(new JLabel("00:13", SwingConstants.CENTER)); // replace
																			// add(new
																			// JPanel());
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
				add(new RightUpPanel());
				add(new RightDownPanel());
				add(new ImagePanel());
			}
		}

		GamePanel gamePanel = new GamePanel();
		setLeftComponent(gamePanel);
		ScorePanel scorePanel = new ScorePanel();
		setRightComponent(scorePanel);
	}
}
