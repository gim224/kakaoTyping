package thread;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FallingLabel extends JLabel implements Runnable {

	int random;
	int speed=0;
	
	public FallingLabel(String text,int speed) {
		super(text);
		setOpaque(true);
		this.setSize(50, 30);
		this.speed = speed;
		/////////// size ����////////
		
		random = (int) (Math.random() * (600-50) + 1);
		
		
		// ������ ��ü ����
		Thread th = new Thread(this);
		
		// ������ ����
		th.start();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int colorSwitch = 0;
		//int speed = 0;
		int i =0;
		while (true) {
//			if (colorSwitch == 0)
//				setBackground(Color.yellow);
//			else
//				setBackground(Color.green);
//
//			if (colorSwitch == 0)
//				colorSwitch = 1;
//			else
//				colorSwitch = 0;

			this.setLocation(random, speed+=5);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
