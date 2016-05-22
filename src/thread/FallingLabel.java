package thread;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FallingLabel extends JLabel implements Runnable {

	int random;
	int speed = 0;
	int interval = 2*1000;

	public FallingLabel(String text, int speed) {
		super(text, SwingConstants.CENTER);
		setOpaque(true);
		this.setSize(60, 30);

		this.speed = speed;
		/////////// size 수정////////

		random = (int) (Math.random() * (600 - 50) + 1);

		// 스레드 객체 생성
		Thread th = new Thread(this);

		// 스레드 실행
		th.start();

	}

	public void delay() {
		try {
			Thread.sleep(interval);
		}
		catch(Exception e){
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int colorSwitch = 0;
		// int speed = 0;
		int i = 0;
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

			this.setLocation(random, i += speed);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
