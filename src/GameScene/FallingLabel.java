package GameScene;

import java.awt.*;
import javax.swing.*;

import fileIO.FileInput;

@SuppressWarnings("serial")
public class FallingLabel extends JLabel implements Runnable {

	private int random;
	private int speed = 0;
	private boolean flag = false;
	private ImageIcon icon = new ImageIcon("images/폭죽.gif");
	private Image img = icon.getImage();
	private int lifePoint = 0;
	private static FileInput input = new FileInput("txt/word.dat", "@");
	private int firstY = 0; // 처음 레이블 위치

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

	public FallingLabel() {
		super(input.getOneWordRandom(), SwingConstants.CENTER);
		
		speed = (int) (Math.random() * 5 + 1);

		setLabelSize(); // Label 가로 size 설정

		setOpaque(true);

		/////////// size 수정////////
		
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
		// int speed = 0;
		
		while (true) {

			// if (colorSwitch == 0)
			// setBackground(Color.yellow);
			// else
			// setBackground(Color.green);
			//
			// if (colorSwitch == 0)
			// colorSwitch = 1;
			// else
			// colorSwitch = 0;

			if (getLocation().getY() >= 672 - getSize().getHeight()) {
				flag = true;

			}
			this.setLocation(random, firstY += speed);
			if (flag == true) {
				// 스레드 종료시 일어나야 할일 : score 증가?

				this.removeAll();
				this.setVisible(false);
				return;
			}
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	// @Override
	// public void paintComponent(Graphics g) {
	// g.drawImage(img, this.getX(), this.getY(), this);
	// }

}