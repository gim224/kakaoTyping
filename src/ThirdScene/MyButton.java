package ThirdScene;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyButton extends JButton {
	public MyButton(String s, int width, int height) {
		super(s);
		setSize(100, 100);
		setLocation(width, height);
	}
}


