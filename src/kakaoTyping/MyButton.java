package kakaoTyping;

import javax.swing.*;

public class MyButton extends JButton{
	public MyButton(String s, int x, int y) {
		super(s);
		this.setSize(150, 60);
		this.setLocation(x, y);
	}
}
