package GameScene;

import javax.swing.JSplitPane;

public class GameScene extends JSplitPane {
	public GameScene() {
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setDividerLocation(600);
		setEnabled(false);// Á¶Á¤

		GamePanel gamePanel = new GamePanel();
		setLeftComponent(gamePanel);
		ScorePanel scorePanel = new ScorePanel();
		setRightComponent(scorePanel);
	}
}
