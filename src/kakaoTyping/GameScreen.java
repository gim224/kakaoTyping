package kakaoTyping;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

public class GameScreen extends JPanel {
	public GameScreen() {
		super();
		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split.setDividerLocation(600);
		split.setEnabled(false);// Á¶Á¤

		GamePanel gamePanel = new GamePanel();
		split.setLeftComponent(gamePanel);
		ScorePanel scorePanel = new ScorePanel();
		split.setRightComponent(scorePanel);

		this.add(split);		
				
	}

}
