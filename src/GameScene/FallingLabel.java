package GameScene;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FallingLabel extends JLabel implements Runnable {

	private int random;
	private int speed = 0;
	private boolean flag = false;
	private ImageIcon icon = new ImageIcon("images/폭죽.gif");
	private Image img = icon.getImage();

	public void finish() {
		flag = true;
	}

	public FallingLabel(String text, int speed) {
		super(text, SwingConstants.CENTER);
		this.setFont(new Font("Jokerman", Font.ITALIC, 20));
		FontMetrics fm = getFontMetrics(this.getFont());
		int w = fm.stringWidth(this.getText());
		this.setSize(w + 8, fm.getHeight());

		setOpaque(true);

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
			
			System.out.println(this.getLocation().getY());
				 
			
			this.setLocation(random, i += speed);
			// if(i>100){
			// this.setVisible(false);
			//
			// }

			try {
				Thread.sleep(100);
				if (flag == true) {
					// 스레드 종료시 일어나야 할일 : score 증가?
					
					this.removeAll();
					this.setVisible(false);
					return;
				}
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	// @Override
	// public void paintComponent(Graphics g){
	// g.drawImage(img, this.getX(), this.getY(), this);
	// }

}