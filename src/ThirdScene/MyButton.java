package ThirdScene;



import javax.swing.*;

@SuppressWarnings("serial")
public class MyButton extends JButton {
	public MyButton(String s, int width, int height) {
		super(s);
		setSize(100, 100);
		setLocation(width, height);
	}
}


