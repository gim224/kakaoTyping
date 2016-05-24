package thread;

import java.awt.Container;

import javax.swing.JLabel;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class FallingLabel extends JLabel implements Runnable {

	int random;
	int speed = 0;
	boolean flag = false;

	public void finish() {
		flag = true;
	}

	public FallingLabel(String text, int speed) {
		super(text, SwingConstants.CENTER);
		setOpaque(true);
		this.setSize(150, 30);

		this.speed = speed;
		/////////// size 수정////////

		random = (int) (Math.random() * (600 - 150) + 1);

		// 스레드 객체 생성
		Thread th = new Thread(this);

		// 스레드 실행
		th.start();

	}

	// public void delay() {
	// try {
	// Thread.sleep(interval);
	// }
	// catch(Exception e){
	// }
	// }
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
			// if(i>100){
			// this.setVisible(false);
			//
			// }

			try {
				Thread.sleep(100);
				if (flag == true) {
					// 스레드 종료시 일어나야 할일 : score 증가?
					return;
				}
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
