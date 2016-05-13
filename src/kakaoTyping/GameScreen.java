package kakaoTyping;

import java.awt.Container;

import javax.swing.*;

public class GameScreen extends MyFrame {
	public GameScreen(){
		super();
		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split.setDividerLocation(600);
		split.setEnabled(false);//	Á¶Á¤		
		
		
		JButton bttn = new JButton("submit");
		split.setLeftComponent(bttn);
		//split.setUI(ui);
		Container c = getContentPane();
		
		c.add(split);		
	}
	
}
